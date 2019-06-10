package com.jfacetutorial.view;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jfacetutorial.ResourceUsingLabelProvider;
import com.jfacetutorial.UserContentProvider;
import com.jfacetutorial.modellayer.UserData;
import com.jfacetutorial.modellayer.UserDataImpl;

public class View {

	private Table table;
	private Text groupInput;
	private Text nameInput;
	private Button deleteButton;
	private Button saveButton;
	private Button checkButton;
	private Button newButton;
	private Button cancelButton;
	private TableViewer tableViewer;
	private UserData localUserData;
	private Label groupLabel;
	private Label nameLabel;
	public Consumer<UserData> saveConsumer;
	public Supplier<Map<Long, UserData>> userSupplier;
	private Consumer<Long> deleteConsumer;

	public View() {
	}

	public Composite createWidgets(Composite parent) {
		Composite mainComposite = new Composite(parent, SWT.NONE);
		mainComposite.setLayout(new FillLayout());
		SashForm sashForm = createSashForm(mainComposite);
		tableViewer = createTableViewer(sashForm);
		createInputComposite(sashForm);
		return mainComposite;
	}

	private SashForm createSashForm(Composite mainComposite) {
		SashForm sashForm = new SashForm(mainComposite, SWT.NONE);
		sashForm.setTouchEnabled(true);
		return sashForm;
	}

	private TableViewer createTableViewer(SashForm sashForm) {
		TableViewer tableViewer = new TableViewer(sashForm,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		tableViewer.setUseHashlookup(true);
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.getTable().setLinesVisible(true);
		createTablecolumns(tableViewer);
		tableViewer.setContentProvider(new UserContentProvider());
		tableViewer.addSelectionChangedListener(createTableViewListener());
		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		return tableViewer;
	}

	private void createTablecolumns(TableViewer tableViewer) {
		createNameColumn(tableViewer);
		createGroupColumn(tableViewer);
		createCheckColumn(tableViewer);
	}

	private void createNameColumn(TableViewer tableViewer) {
		TableViewerColumn nameColuumn = new TableViewerColumn(tableViewer, SWT.NONE);
		nameColuumn.getColumn().setWidth(100);
		nameColuumn.getColumn().setText("Name");
		nameColuumn.getColumn().setAlignment(SWT.CENTER);
		nameColuumn.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				UserData user = (UserData) element;
				return user.getName();
			}
		});
		createNameComparator(tableViewer, nameColuumn);
	}

	private void createNameComparator(TableViewer tableViewer, TableViewerColumn nameColuumn) {
		new ColumnViewerComparator(tableViewer, nameColuumn) {
			@Override
			protected int doCompare(Viewer viewer, Object e1, Object e2) {
				UserData user1 = (UserData) e1;
				UserData user2 = (UserData) e2;
				return user1.getName().compareToIgnoreCase(user2.getName());
			}
		};
	}

	private void createGroupColumn(TableViewer tableViewer) {
		TableViewerColumn groupColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		groupColumn.getColumn().setWidth(100);
		groupColumn.getColumn().setText("Group");
		groupColumn.getColumn().setAlignment(SWT.CENTER);
		groupColumn.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				UserData user = (UserData) element;
				return user.getGroup();
			}
		});
		createGroupComparator(tableViewer, groupColumn);
	}

	private void createGroupComparator(TableViewer tableViewer, TableViewerColumn groupColumn) {
		new ColumnViewerComparator(tableViewer, groupColumn) {

			@Override
			protected int doCompare(Viewer viewer, Object e1, Object e2) {
				UserData user1 = (UserData) e1;
				UserData user2 = (UserData) e2;
				return user1.getGroup().compareToIgnoreCase(user2.getGroup());
			}
		};
	}

	private void createCheckColumn(TableViewer tableViewer) {
		TableViewerColumn checkColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		checkColumn.getColumn().setWidth(50);
		checkColumn.getColumn().setText("SWT");
		checkColumn.getColumn().setAlignment(SWT.CENTER);
		checkColumn.setLabelProvider(new ResourceUsingLabelProvider());
	}

	private ISelectionChangedListener createTableViewListener() {
		return new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent arg0) {
				IStructuredSelection selection = (IStructuredSelection) arg0.getSelection();
				if (selection.getFirstElement() == null) {
					setEmptyInput();
					return;
				}
				deleteStar();
				localUserData = (UserData) selection.getFirstElement();
				nameInput.setText(localUserData.getName());
				groupInput.setText(localUserData.getGroup());
				checkButton.setSelection(localUserData.isTaskDone());
			}
		};
	}

	private void setEmptyInput() {
		nameInput.setText("");
		groupInput.setText("");
		checkButton.setSelection(false);
	}

	private void createInputComposite(SashForm sashForm) {
		Composite inputComposite = new Composite(sashForm, SWT.NONE);
		inputComposite.setLayout(new FormLayout());
		createInputComponents(inputComposite);
		createListeners();
	}

//	public static void main(String[] args) {
//		  Display display = new Display();
//		    Shell shell = new Shell(display);
//		    GridLayout layout = new GridLayout();
//		    layout.numColumns = 4;
//		    layout.makeColumnsEqualWidth = true;
//		    shell.setLayout(layout);
//
//		    // Create the big button in the upper left
//		    GridData data = new GridData(GridData.FILL_BOTH);
//		    data.widthHint = 110;
//		    
//		  
////		    
////		    Button one = new Button(shell, SWT.PUSH);
////		    one.setText("one");
////		    one.setLayoutData(data);
//
//		    // Create a composite to hold the three buttons in the upper right
////		    Composite composite = new Composite(shell, SWT.NONE);
////		    data = new GridData(GridData.FILL_BOTH);
////		    data.horizontalSpan = 2;
////		    composite.setLayoutData(data);
////		    layout = new GridLayout();
////		    layout.numColumns = 1;
////		    layout.marginHeight = 15;
////		    composite.setLayout(layout);//////
//
//		    Composite compositeLabTex = new Composite(shell, SWT.NONE);
////		    data = new GridData(GridData.VERTICAL_ALIGN_CENTER);
////		    compositeLabTex.setLayoutData(data);
//		    layout = new GridLayout();
//		    layout.numColumns = 2;
//		    layout.marginHeight = 50;
//		    compositeLabTex.setLayout(layout);//my
//		    
//		    Label nameLabel = new Label(compositeLabTex, SWT.NONE);//my
//		    nameLabel.setText("sss");
//		    
//		    
//		    
//		    // Create button "two"
////		    data = new GridData(GridData.FILL_BOTH);
////		    Button two = new Button(composite, SWT.PUSH);
////		    two.setText("two");
////		    two.setLayoutData(data);
//		    
//		    
//		    Text nameText = new Text(compositeLabTex, SWT.BORDER);//my
//		    data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
//		    nameText.setLayoutData(data);//my
//		    
//		    
//		    // Create button "three"
////		    data = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
////		    Button three = new Button(composite, SWT.PUSH);
////		    three.setText("three");
////		    three.setLayoutData(data);
////		    
//		    
//
//		    // Create button "four"
////		    data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
////		    Button four = new Button(composite, SWT.PUSH);
////		    four.setText("four");
////		    four.setLayoutData(data);
//
//		    // Create the long button across the bottom
////		    data = new GridData();
////		    data.horizontalAlignment = GridData.FILL;
////		    data.grabExcessHorizontalSpace = true;
////		    data.horizontalSpan = 3;
////		    data.heightHint = 150;
////		    Button five = new Button(shell, SWT.PUSH);
////		    five.setText("five");
////		    five.setLayoutData(data);
//
//		    shell.pack();
//		    shell.open();
//		    while (!shell.isDisposed()) {
//		      if (!display.readAndDispatch()) {
//		        display.sleep();
//		      }
//		    }
//		    display.dispose();
//	}

	private void createInputComponents(Composite inputComposite) {

		nameInput = new Text(inputComposite, SWT.BORDER);
		FormData formNameInput = new FormData();
		formNameInput.right = new FormAttachment(100, -33);
		formNameInput.top = new FormAttachment(0, 34);
		formNameInput.bottom = new FormAttachment(0, 55);
		nameInput.setText("Volodymyr");
		nameInput.setLayoutData(formNameInput);

		groupInput = new Text(inputComposite, SWT.BORDER);
		FormData formGroupInput = new FormData();
		formGroupInput.top = new FormAttachment(nameInput, 18);
		formGroupInput.right = new FormAttachment(nameInput, 0, SWT.RIGHT);
		formGroupInput.left = new FormAttachment(nameInput, 0, SWT.LEFT);
		groupInput.setLayoutData(formGroupInput);
		groupInput.setText("15");

		checkButton = new Button(inputComposite, SWT.CHECK | SWT.CENTER);
		formGroupInput.bottom = new FormAttachment(checkButton, -58);
		checkButton.setAlignment(SWT.LEFT);
		FormData formCheckButton = new FormData();
		formCheckButton.top = new FormAttachment(0, 152);
		formCheckButton.right = new FormAttachment(100, -52);
		formCheckButton.left = new FormAttachment(0, 91);
		checkButton.setLayoutData(formCheckButton);
		checkButton.setText("SWT task done");

		newButton = new Button(inputComposite, SWT.NONE);
		FormData formNewButton = new FormData();
		formNewButton.left = new FormAttachment(0, 10);
		formNewButton.bottom = new FormAttachment(100, -24);
		newButton.setLayoutData(formNewButton);
		newButton.setText("New");

		saveButton = new Button(inputComposite, SWT.NONE);
		formNewButton.right = new FormAttachment(saveButton, -6);
		formCheckButton.bottom = new FormAttachment(saveButton, -39);
		FormData formSaveButton = new FormData();
		formSaveButton.left = new FormAttachment(0, 91);
		formSaveButton.bottom = new FormAttachment(100, -24);
		saveButton.setLayoutData(formSaveButton);
		saveButton.setText("Save");

		deleteButton = new Button(inputComposite, SWT.NONE);
		FormData formDeleteButton = new FormData();
		formDeleteButton.right = new FormAttachment(100);
		formDeleteButton.top = new FormAttachment(newButton, 0, SWT.TOP);
		formDeleteButton.bottom = new FormAttachment(100, -24);
		deleteButton.setLayoutData(formDeleteButton);
		deleteButton.setText("Delete");

		cancelButton = new Button(inputComposite, SWT.NONE);
		formSaveButton.right = new FormAttachment(cancelButton, -6);
		formDeleteButton.left = new FormAttachment(0, 246);
		FormData formCancelButton = new FormData();
		formCancelButton.top = new FormAttachment(newButton, 0, SWT.TOP);
		formCancelButton.right = new FormAttachment(deleteButton, -6);
		formCancelButton.bottom = new FormAttachment(100, -24);
		formCancelButton.left = new FormAttachment(0, 162);
		cancelButton.setLayoutData(formCancelButton);
		cancelButton.setText("Cancel");

		nameLabel = new Label(inputComposite, SWT.NONE);
		formNameInput.left = new FormAttachment(nameLabel, 50);
		nameLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		FormData formNameLabel = new FormData();
		formNameLabel.right = new FormAttachment(100, -276);
		formNameLabel.top = new FormAttachment(nameInput, -2, SWT.TOP);
		nameLabel.setLayoutData(formNameLabel);
		nameLabel.setText("Name   ");

		groupLabel = new Label(inputComposite, SWT.NONE);
		groupLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		FormData formGroupLabel = new FormData();
		formGroupLabel.bottom = new FormAttachment(groupInput, 0, SWT.BOTTOM);
		formGroupLabel.top = new FormAttachment(nameLabel, 20);
		formGroupLabel.left = new FormAttachment(nameLabel, 0, SWT.LEFT);
		formGroupLabel.right = new FormAttachment(groupInput, -2);
		groupLabel.setLayoutData(formGroupLabel);
		groupLabel.setText("Group");
	}

	private void createListeners() {
		createSaveButtonListener();
		createNewButtonListener();
		createCancelButtonListener();
		createDeleteButtonListener();
	}

	private void createSaveButtonListener() {
		saveButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (getNameInput().getText() != "" && getGroupInput().getText() != "") {

					saveConsumer.accept(new UserDataImpl(getNameInput().getText(), getGroupInput().getText(),
							getCheckButton().getSelection()));

					deleteStar();
				} else {
					createErrorSaveMessage(arg0.widget.getDisplay().getActiveShell());
				}
				getTableViewer().setInput(userSupplier.get());
			}

		});
	}

	private void createErrorSaveMessage(Shell activeShell) {
		String[] button = { IDialogConstants.OK_LABEL };
		MessageDialog message = new MessageDialog(activeShell, "Error", null, "Please, fill all fields with a star!!!",
				MessageDialog.ERROR, button, 0);
		message.open();
	}

	private void deleteStar() {
		getNameLabel().setText("Name");
		getGroupLabel().setText("Group");
	}

	private void setStar() {
		getNameLabel().setText("Name *");
		getGroupLabel().setText("Group *");
	}

	private void createNewButtonListener() {
		newButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setStar();
				setEmptyInput();
				localUserData = null;
			}
		});
	}

	private void createCancelButtonListener() {
		cancelButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				setStar();
				setEmptyInput();
			}
		});
	}

	private void createDeleteButtonListener() {
		deleteButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (localUserData == null) {
					createErrorDeleteMessage(e.display.getActiveShell());
					return;
				}
				deleteConsumer.accept(localUserData.getId());
				setStar();
				getTableViewer().setInput(userSupplier.get());
			}
		});
	}

	private void createErrorDeleteMessage(Shell activeShell) {
		String[] buttons = { IDialogConstants.OK_LABEL };
		MessageDialog message = new MessageDialog(activeShell, "Error", null,
				"Please, select or add item before delete:)", MessageDialog.ERROR, buttons, 0);
		message.open();
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public Text getGroupInput() {
		return groupInput;
	}

	public Text getNameInput() {
		return nameInput;
	}

	public Button getCheckButton() {
		return checkButton;
	}

	public Button getNewButton() {
		return newButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public Table getTable() {
		return table;
	}

	public TableViewer getTableViewer() {
		return tableViewer;
	}

	public UserData getLocalUserData() {
		return localUserData;
	}

	public void setLocalUserData(UserData userData) {
		localUserData = userData;
	}

	public Label getGroupLabel() {
		return groupLabel;
	}

	public Label getNameLabel() {
		return nameLabel;
	}

	public void addSaveAction(Consumer<UserData> consumer) {
		this.saveConsumer = consumer;
	}

	public void addAllUser(Supplier<Map<Long, UserData>> supplier) {
		this.userSupplier = supplier;
	}

	public void addDeleteAction(Consumer<Long> consumer) {
		this.deleteConsumer = consumer;
	}

}
