package com.jfacetutorial;


import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jfacetutorial.controller.Controller;
import com.jfacetutorial.modellayer.UserData;
import com.jfacetutorial.modellayer.UserServiceListImpl;
import com.jfacetutorial.view.View;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import swing2swt.layout.BoxLayout;


public class App extends ApplicationWindow {
	
//	private Table table;
//	private Text groupInput;
//	private Text nameInput;
	private Action action;
//	private Button deleteButton;
//	private Button saveButton;
//	private Button checkButton;
//	private Button newButton;
//	private Button cancelButton;
//	private TableViewer tableViewer;
	private SelectionListener _selectionListener;
	private Controller controller;
	private View view;
	/**
	 * Create the application window.
	 */
	public App() {
		super(null);
		createActions();
		addMenuBar();
	
	}
	private void createActions() {
		{
			action = new Action("Save") {
			};
		}
	}


	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager();
	      menuManager.add(createFileMenu());
	      
	      MenuManager menuManager_1 = createEditMenu();
	      menuManager_1.setVisible(true);
	      menuManager.add(menuManager_1);
	      
	      MenuManager menuManager_2 = createHelpMenu();
	      menuManager_2.setVisible(true);
	      menuManager.add(menuManager_2);
	      return menuManager;
	}
	
	private MenuManager createEditMenu() {
		MenuManager menu = new MenuManager();
		menu.setMenuText("Edit");
		menu.add(new Action() {
	         public String getText() {
	            return "Edit";
	         }
	          
	         public void run() {
	            
	         }
	      });
		return menu;
	}

	private MenuManager createHelpMenu() {
		MenuManager menu = new MenuManager();
		menu.setMenuText("Help");
		menu.add(new Action() {
	         public String getText() {
	            return "About";
	         }
	          
	         public void run() {
	            
	         }
	      });
		return menu;
	}
	
	private MenuManager createFileMenu() {
		 MenuManager menu = new MenuManager("&File", "Id01");
		 System.out.println("sssda");
	      menu.add(new Action() {
	         public String getText() {
	            return "&Open";
	         }
	          
	         public void run() {
	            String[] buttons = { IDialogConstants.OK_LABEL,
	                                  IDialogConstants.CANCEL_LABEL };
	            MessageDialog dialog = new MessageDialog(getShell(), "Title", null,
	                                                     "File/Open selected!",
	                                                     MessageDialog.INFORMATION, buttons, 0);
	            dialog.open();   // blocking call
	         }
	      });
	      menu.add(action);
	 
	      menu.add(new Action() {
	         public String getText() {
	            return "E&xit";
	         }
	          
	         public void run() {
	            
	         }
	      });
	        
	      return menu;
	}


	@Override
	protected Control createContents(Composite parent) {
		
		view = new View();
		Composite composite = view.createWidgets(parent);
		controller = new Controller(view);

		view.getSaveButton().addSelectionListener(controller.createSaveListener());
		view.getNewButton().addSelectionListener(controller.createNewListener());
		
////		Composite c = new Composite(parent, SWT.NONE);
////		c.setLayout(new FillLayout());
//		
////		Composite composite = new Composite(parent,SWT.NONE);
////		composite.setLayout(new BoxLayout(BoxLayout.X_AXIS));
////		
////		System.out.print("start");
////		
////		SashForm sashForm = new SashForm(composite, SWT.NONE);
////		sashForm.setTouchEnabled(true);
////		
////		tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
////		tableViewer.setUseHashlookup(true);
//		
////		table = tableViewer.getTable();
////		table.setHeaderVisible(true);
////		table.setLinesVisible(true);
//		
//		TableViewerColumn nameColuumn = new TableViewerColumn(tableViewer, SWT.NONE);
//		nameColuumn.getColumn().setWidth(100);
//		nameColuumn.getColumn().setText("Name");
//		nameColuumn.setLabelProvider(new ColumnLabelProvider() {
//
//			@Override
//			public String getText(Object element) {
//				UserData user = (UserData) element;
//				return user.getName();
//			}
//			
//		});
//		
//		TableViewerColumn groupColumn = new TableViewerColumn(tableViewer, SWT.NONE);
//		groupColumn.getColumn().setWidth(100);
//		groupColumn.getColumn().setText("Group");
//		groupColumn.setLabelProvider(new ColumnLabelProvider() {
//			
//			@Override
//			public String getText(Object element) {
//				UserData user = (UserData) element;
//				return user.getGroup();
//			}
//			
//		});
//		
//		TableViewerColumn checkColumn = new TableViewerColumn(tableViewer, SWT.NONE);
//		checkColumn.getColumn().setWidth(30);
//		checkColumn.getColumn().setText("Group");
//		checkColumn.setLabelProvider(new ResourceUsingLabelProvider());
//		
//		Composite composite_1 = new Composite(sashForm, SWT.NONE);
//		composite_1.setLayout(new FormLayout());
//		
//		nameInput = new Text(composite_1, SWT.BORDER);
//		FormData fd_text_1 = new FormData();
//		fd_text_1.right = new FormAttachment(100, -33);
//		fd_text_1.top = new FormAttachment(0, 34);
//		fd_text_1.bottom = new FormAttachment(0, 55);
//		nameInput.setLayoutData(fd_text_1);
//		
//		groupInput = new Text(composite_1, SWT.BORDER);
//		FormData fd_txtDfdf = new FormData();
//		fd_txtDfdf.top = new FormAttachment(nameInput, 18);
//		fd_txtDfdf.right = new FormAttachment(nameInput, 0, SWT.RIGHT);
//		fd_txtDfdf.left = new FormAttachment(nameInput, 0, SWT.LEFT);
//		groupInput.setLayoutData(fd_txtDfdf);
//		groupInput.setText("dfdf");
//		
//		checkButton = new Button(composite_1, SWT.CHECK | SWT.CENTER);
//		fd_txtDfdf.bottom = new FormAttachment(checkButton, -58);
//		checkButton.setAlignment(SWT.LEFT);
//		FormData fd_btnSwtTaskDone = new FormData();
//		fd_btnSwtTaskDone.top = new FormAttachment(0, 152);
//		fd_btnSwtTaskDone.right = new FormAttachment(100, -52);
//		fd_btnSwtTaskDone.left = new FormAttachment(0, 91);
//		checkButton.setLayoutData(fd_btnSwtTaskDone);
//		checkButton.setText("SWT task done");
//		
//		newButton = new Button(composite_1, SWT.NONE);
//		FormData fd_btnNewButton = new FormData();
//		fd_btnNewButton.left = new FormAttachment(0, 10);
//		fd_btnNewButton.bottom = new FormAttachment(100, -24);
//		newButton.setLayoutData(fd_btnNewButton);
//		newButton.setText("New");
//		
//		saveButton = new Button(composite_1, SWT.NONE);
//		fd_btnNewButton.right = new FormAttachment(saveButton, -6);
//		fd_btnSwtTaskDone.bottom = new FormAttachment(saveButton, -39);
//		FormData fd_btnNewButton_1 = new FormData();
//		fd_btnNewButton_1.left = new FormAttachment(0, 91);
//		fd_btnNewButton_1.bottom = new FormAttachment(100, -24);
//		saveButton.setLayoutData(fd_btnNewButton_1);
//		saveButton.setText("Save");
//		
//		deleteButton = new Button(composite_1, SWT.NONE);
//		FormData fd_btnNewButton_2 = new FormData();
//		fd_btnNewButton_2.right = new FormAttachment(100);
//		fd_btnNewButton_2.top = new FormAttachment(newButton, 0, SWT.TOP);
//		fd_btnNewButton_2.bottom = new FormAttachment(100, -24);
//		deleteButton.setLayoutData(fd_btnNewButton_2);
//		deleteButton.setText("Delete");
//		
//		cancelButton = new Button(composite_1, SWT.NONE);
//		fd_btnNewButton_1.right = new FormAttachment(cancelButton, -6);
//		fd_btnNewButton_2.left = new FormAttachment(0, 246);
//		FormData fd_btnNewButton_3 = new FormData();
//		fd_btnNewButton_3.top = new FormAttachment(newButton, 0, SWT.TOP);
//		fd_btnNewButton_3.right = new FormAttachment(deleteButton, -6);
//		fd_btnNewButton_3.bottom = new FormAttachment(100, -24);
//		fd_btnNewButton_3.left = new FormAttachment(0, 162);
//		cancelButton.setLayoutData(fd_btnNewButton_3);
//		cancelButton.setText("Cancel");
//		
//		Label nameLabel = new Label(composite_1, SWT.NONE);
//		fd_text_1.left = new FormAttachment(nameLabel, 37);
//		nameLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
//		FormData fd_lblNewLabel = new FormData();
//		fd_lblNewLabel.right = new FormAttachment(100, -276);
//		fd_lblNewLabel.top = new FormAttachment(nameInput, -2, SWT.TOP);
//		nameLabel.setLayoutData(fd_lblNewLabel);
//		nameLabel.setText("Name");
//		
//		Label groupLabel = new Label(composite_1, SWT.NONE);
//		groupLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
//		FormData fd_lblNewLabel_1 = new FormData();
//		fd_lblNewLabel_1.bottom = new FormAttachment(groupInput, 0, SWT.BOTTOM);
//		fd_lblNewLabel_1.top = new FormAttachment(nameLabel, 20);
//		fd_lblNewLabel_1.left = new FormAttachment(nameLabel, 0, SWT.LEFT);
//		fd_lblNewLabel_1.right = new FormAttachment(groupInput, -2);
//		groupLabel.setLayoutData(fd_lblNewLabel_1);
//		groupLabel.setText("Group");
//		
//		tableViewer.setContentProvider(new UserContentProvider());
////		tableViewer.setInput(new UserServiceListImpl());
//		System.out.print("finish");
//		sashForm.setWeights(new int[] {420, 420});
		
		
	
		
//		this.view.addSaveButtonListener(saveButton.addSelectionListener(createSaveListener());
		
		
	    return composite;
	}
	
	

//	public static void main(String args[]) {
//			new View().run();
//	}


	public void run() {
		
		setBlockOnOpen(true);
		open();
		Display.getCurrent().dispose();
	}
	
	public void setSelectedListener(SelectionAdapter selectionAdapter) {
		// TODO Auto-generated method stub
		this._selectionListener = selectionAdapter;
	}
	
	
	
}
