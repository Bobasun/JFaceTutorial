package com.jfacetutorial.view;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
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
	
	public View() {
	}

	public Composite createWidgets(Composite parent) {
		Composite mainComposite = new Composite(parent,SWT.NONE);
		mainComposite.setLayout(new FillLayout());
		
		SashForm sashForm = new SashForm(mainComposite, SWT.NONE);
		sashForm.setTouchEnabled(true);
		
		tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		tableViewer.setUseHashlookup(true);
		createTablecolumns(tableViewer);
		
		Composite inputComposite = new Composite(sashForm, SWT.NONE);
		inputComposite.setLayout(new FormLayout());
		createInputComponents(inputComposite);
		
		tableViewer.setContentProvider(new UserContentProvider());
		tableViewer.addSelectionChangedListener(createTableViewListener());

		return mainComposite;
	}
	
	private void createInputComponents(Composite inputComposite) {
		nameInput = new Text(inputComposite, SWT.BORDER);
		FormData fd_text_1 = new FormData();
		fd_text_1.right = new FormAttachment(100, -33);
		fd_text_1.top = new FormAttachment(0, 34);
		fd_text_1.bottom = new FormAttachment(0, 55);
		nameInput.setLayoutData(fd_text_1);
		
		groupInput = new Text(inputComposite, SWT.BORDER);
		FormData fd_txtDfdf = new FormData();
		fd_txtDfdf.top = new FormAttachment(nameInput, 18);
		fd_txtDfdf.right = new FormAttachment(nameInput, 0, SWT.RIGHT);
		fd_txtDfdf.left = new FormAttachment(nameInput, 0, SWT.LEFT);
		groupInput.setLayoutData(fd_txtDfdf);
		groupInput.setText("dfdf");
		
		checkButton = new Button(inputComposite, SWT.CHECK | SWT.CENTER);
		fd_txtDfdf.bottom = new FormAttachment(checkButton, -58);
		checkButton.setAlignment(SWT.LEFT);
		FormData fd_btnSwtTaskDone = new FormData();
		fd_btnSwtTaskDone.top = new FormAttachment(0, 152);
		fd_btnSwtTaskDone.right = new FormAttachment(100, -52);
		fd_btnSwtTaskDone.left = new FormAttachment(0, 91);
		checkButton.setLayoutData(fd_btnSwtTaskDone);
		checkButton.setText("SWT task done");
		
		newButton = new Button(inputComposite, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.left = new FormAttachment(0, 10);
		fd_btnNewButton.bottom = new FormAttachment(100, -24);
		newButton.setLayoutData(fd_btnNewButton);
		newButton.setText("New");
		
		saveButton = new Button(inputComposite, SWT.NONE);
		fd_btnNewButton.right = new FormAttachment(saveButton, -6);
		fd_btnSwtTaskDone.bottom = new FormAttachment(saveButton, -39);
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.left = new FormAttachment(0, 91);
		fd_btnNewButton_1.bottom = new FormAttachment(100, -24);
		saveButton.setLayoutData(fd_btnNewButton_1);
		saveButton.setText("Save");
		
		deleteButton = new Button(inputComposite, SWT.NONE);
		FormData fd_btnNewButton_2 = new FormData();
		fd_btnNewButton_2.right = new FormAttachment(100);
		fd_btnNewButton_2.top = new FormAttachment(newButton, 0, SWT.TOP);
		fd_btnNewButton_2.bottom = new FormAttachment(100, -24);
		deleteButton.setLayoutData(fd_btnNewButton_2);
		deleteButton.setText("Delete");
		
		cancelButton = new Button(inputComposite, SWT.NONE);
		fd_btnNewButton_1.right = new FormAttachment(cancelButton, -6);
		fd_btnNewButton_2.left = new FormAttachment(0, 246);
		FormData fd_btnNewButton_3 = new FormData();
		fd_btnNewButton_3.top = new FormAttachment(newButton, 0, SWT.TOP);
		fd_btnNewButton_3.right = new FormAttachment(deleteButton, -6);
		fd_btnNewButton_3.bottom = new FormAttachment(100, -24);
		fd_btnNewButton_3.left = new FormAttachment(0, 162);
		cancelButton.setLayoutData(fd_btnNewButton_3);
		cancelButton.setText("Cancel");
		
		nameLabel = new Label(inputComposite, SWT.NONE);
		fd_text_1.left = new FormAttachment(nameLabel, 50);
		nameLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.right = new FormAttachment(100, -276);
		fd_lblNewLabel.top = new FormAttachment(nameInput, -2, SWT.TOP);
		nameLabel.setLayoutData(fd_lblNewLabel);
		nameLabel.setText("Name");
		
		groupLabel = new Label(inputComposite, SWT.NONE);
		groupLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.bottom = new FormAttachment(groupInput, 0, SWT.BOTTOM);
		fd_lblNewLabel_1.top = new FormAttachment(nameLabel, 20);
		fd_lblNewLabel_1.left = new FormAttachment(nameLabel, 0, SWT.LEFT);
		fd_lblNewLabel_1.right = new FormAttachment(groupInput, -2);
		groupLabel.setLayoutData(fd_lblNewLabel_1);
		groupLabel.setText("Group");		
	}

	private void createTablecolumns(TableViewer tableViewer2) {
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
		checkColumn.getColumn().setText("Group");
		checkColumn.setLabelProvider(new ResourceUsingLabelProvider());		
	}

	private ISelectionChangedListener createTableViewListener() {
		return new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent arg0) {
				IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
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
	
	
}
