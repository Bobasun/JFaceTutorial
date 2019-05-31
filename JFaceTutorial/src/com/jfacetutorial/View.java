package com.jfacetutorial;


import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import swing2swt.layout.BoxLayout;


public class View extends ApplicationWindow {
	
	
	private static class ContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			return new Object[0];
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	
	
	private Table table;
	private Text txtDfdf;
	private Text text_1;
	private Action action;

	/**
	 * Create the application window.
	 */
	public View() {
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
		
//		Composite c = new Composite(parent, SWT.NONE);
//		c.setLayout(new FillLayout());
		
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		
		
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setTouchEnabled(true);
		
		TableViewer tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		tableViewer.setUseHashlookup(true);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Name");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("Group");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("SWT done");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FormLayout());
		
		text_1 = new Text(composite_1, SWT.BORDER);
		FormData fd_text_1 = new FormData();
		fd_text_1.right = new FormAttachment(100, -33);
		fd_text_1.top = new FormAttachment(0, 34);
		fd_text_1.bottom = new FormAttachment(0, 55);
		text_1.setLayoutData(fd_text_1);
		
		txtDfdf = new Text(composite_1, SWT.BORDER);
		FormData fd_txtDfdf = new FormData();
		fd_txtDfdf.top = new FormAttachment(text_1, 18);
		fd_txtDfdf.right = new FormAttachment(text_1, 0, SWT.RIGHT);
		fd_txtDfdf.left = new FormAttachment(text_1, 0, SWT.LEFT);
		txtDfdf.setLayoutData(fd_txtDfdf);
		txtDfdf.setText("dfdf");
		
		Button btnSwtTaskDone = new Button(composite_1, SWT.CHECK | SWT.CENTER);
		fd_txtDfdf.bottom = new FormAttachment(btnSwtTaskDone, -58);
		btnSwtTaskDone.setAlignment(SWT.LEFT);
		FormData fd_btnSwtTaskDone = new FormData();
		fd_btnSwtTaskDone.top = new FormAttachment(0, 152);
		fd_btnSwtTaskDone.right = new FormAttachment(100, -52);
		fd_btnSwtTaskDone.left = new FormAttachment(0, 91);
		btnSwtTaskDone.setLayoutData(fd_btnSwtTaskDone);
		btnSwtTaskDone.setText("SWT task done");
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.left = new FormAttachment(0, 10);
		fd_btnNewButton.bottom = new FormAttachment(100, -24);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("New");
		
		Button btnNewButton_1 = new Button(composite_1, SWT.NONE);
		fd_btnNewButton.right = new FormAttachment(btnNewButton_1, -6);
		fd_btnSwtTaskDone.bottom = new FormAttachment(btnNewButton_1, -39);
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.left = new FormAttachment(0, 91);
		fd_btnNewButton_1.bottom = new FormAttachment(100, -24);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.setText("Save");
		
		Button btnNewButton_2 = new Button(composite_1, SWT.NONE);
		FormData fd_btnNewButton_2 = new FormData();
		fd_btnNewButton_2.right = new FormAttachment(100);
		fd_btnNewButton_2.top = new FormAttachment(btnNewButton, 0, SWT.TOP);
		fd_btnNewButton_2.bottom = new FormAttachment(100, -24);
		btnNewButton_2.setLayoutData(fd_btnNewButton_2);
		btnNewButton_2.setText("Delete");
		
		Button btnNewButton_3 = new Button(composite_1, SWT.NONE);
		fd_btnNewButton_1.right = new FormAttachment(btnNewButton_3, -6);
		fd_btnNewButton_2.left = new FormAttachment(0, 246);
		FormData fd_btnNewButton_3 = new FormData();
		fd_btnNewButton_3.top = new FormAttachment(btnNewButton, 0, SWT.TOP);
		fd_btnNewButton_3.right = new FormAttachment(btnNewButton_2, -6);
		fd_btnNewButton_3.bottom = new FormAttachment(100, -24);
		fd_btnNewButton_3.left = new FormAttachment(0, 162);
		btnNewButton_3.setLayoutData(fd_btnNewButton_3);
		btnNewButton_3.setText("Cancel");
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		fd_text_1.left = new FormAttachment(lblNewLabel, 37);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.right = new FormAttachment(100, -276);
		fd_lblNewLabel.top = new FormAttachment(text_1, -2, SWT.TOP);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("Name");
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.bottom = new FormAttachment(txtDfdf, 0, SWT.BOTTOM);
		fd_lblNewLabel_1.top = new FormAttachment(lblNewLabel, 20);
		fd_lblNewLabel_1.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		fd_lblNewLabel_1.right = new FormAttachment(txtDfdf, -2);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		lblNewLabel_1.setText("Group");
		
		tableViewer.setContentProvider(new ContentProvider());
		sashForm.setWeights(new int[] {420, 420});
	    return composite;
	}

	public static void main(String args[]) {
			new View().run();
	}


	private void run() {
//		MenuManager m = this.createMenuManager();
		setBlockOnOpen(true);
		open();
		Display.getCurrent().dispose();
	}
}
