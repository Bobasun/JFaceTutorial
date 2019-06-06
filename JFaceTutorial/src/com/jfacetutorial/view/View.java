package com.jfacetutorial.view;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jfacetutorial.ResourceUsingLabelProvider;
import com.jfacetutorial.UserContentProvider;
import com.jfacetutorial.modellayer.UserData;
import com.jfacetutorial.modellayer.UserDataImpl;

import swing2swt.layout.BoxLayout;

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
	public Supplier<Map<Long,UserData>> userSupplier;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public View() {
	}

	public Composite createWidgets(Composite parent) {
		Composite mainComposite = new Composite(parent,SWT.NONE);
		mainComposite.setLayout(new FillLayout());
		
		
		SashForm sashForm = createSashForm(mainComposite);
		tableViewer = createTableViewer(sashForm);
		
//		new mytable
//		 new TopMen () 
//		 new Table(sashForm);
//		 new Editor(sashForm, new ButtomPanale());
//		tableViewer = new TableViewer(sashForm, SWT.MULTI | SWT.H_SCROLL
//	            | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
//		tableViewer.setUseHashlookup(true);
//		createTablecolumns(tableViewer);
//		tableViewer.getTable().setHeaderVisible(true);
//		tableViewer.getTable().setLinesVisible(true);
		
		Composite inputComposite = new Composite(sashForm, SWT.NONE);
		inputComposite.setLayout(new FormLayout());
		
		createInputComponents(inputComposite);
		createListeners();
//		tableViewer.setContentProvider(new UserContentProvider());
//		tableViewer.addSelectionChangedListener(createTableViewListener());
	
		return mainComposite;
	}
	
	private void createListeners() {
		getSaveButton().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				saveConsumer.accept(new UserDataImpl(getNameInput().getText(),getGroupInput().getText(), 
						  getCheckButton().getSelection()));
				getTableViewer().setInput(userSupplier.get());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	private TableViewer createTableViewer(SashForm sashForm) {
		// TODO Auto-generated method stub
		TableViewer tableViewer = new TableViewer(sashForm, SWT.MULTI | SWT.H_SCROLL
	            | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		tableViewer.setUseHashlookup(true);
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.getTable().setLinesVisible(true);
		createTablecolumns(tableViewer);
		tableViewer.setContentProvider(new UserContentProvider());
		tableViewer.addSelectionChangedListener(createTableViewListener());
		
		return tableViewer;
	}

	private SashForm createSashForm(Composite mainComposite) {
		// TODO Auto-generated method stub
		SashForm sashForm =new SashForm(mainComposite, SWT.NONE);
		sashForm.setTouchEnabled(true);
		return sashForm;
	}

	private void createInputComponents(Composite inputComposite) {
		nameInput = new Text(inputComposite, SWT.BORDER);
		FormData formNameInput = new FormData();
		formNameInput.right = new FormAttachment(100, -33);
		formNameInput.top = new FormAttachment(0, 34);
		formNameInput.bottom = new FormAttachment(0, 55);
		nameInput.setLayoutData(formNameInput);
		
		groupInput = new Text(inputComposite, SWT.BORDER);
		FormData formGroupInput = new FormData();
		formGroupInput.top = new FormAttachment(nameInput, 18);
		formGroupInput.right = new FormAttachment(nameInput, 0, SWT.RIGHT);
		formGroupInput.left = new FormAttachment(nameInput, 0, SWT.LEFT);
		groupInput.setLayoutData(formGroupInput);
		groupInput.setText("dfdf");
		
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

	private void createTablecolumns(TableViewer tableViewer) {
		TableViewerColumn nameColuumn = new TableViewerColumn(tableViewer, SWT.NONE);
		nameColuumn.getColumn().setWidth(100);
		nameColuumn.getColumn().setText("Name");
		nameColuumn.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				UserData user = (UserData) element;
				return user.getName();
			}
			
		});
		
		TableViewerColumn groupColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		groupColumn.getColumn().setWidth(100);
		groupColumn.getColumn().setText("Group");
		groupColumn.setLabelProvider(new ColumnLabelProvider() {
			
			@Override
			public String getText(Object element) {
				UserData user = (UserData) element;
				return user.getGroup();
			}
			
		});
		
		TableViewerColumn checkColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		checkColumn.getColumn().setWidth(50);
		checkColumn.getColumn().setText("SWT");
		checkColumn.setLabelProvider(new ResourceUsingLabelProvider());		
	}

	private ISelectionChangedListener createTableViewListener() {
		return new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent arg0) {
				IStructuredSelection selection = (IStructuredSelection) arg0.getSelection();
				if(selection.getFirstElement() == null) {
					nameInput.setText("");
					groupInput.setText("");
					checkButton.setSelection(false);
					return;
				}
				localUserData = (UserData) selection.getFirstElement();
				nameInput.setText(localUserData.getName());
				groupInput.setText(localUserData.getGroup());
				checkButton.setSelection(localUserData.isTaskDone());
			}
		};
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
		// TODO Auto-generated method stub
			this.saveConsumer = consumer;
	}

	public void addAllUser(Supplier<Map <Long,UserData>> supplier) {
		// TODO Auto-generated method stub
		this.userSupplier = supplier;
	}
	
	
}
