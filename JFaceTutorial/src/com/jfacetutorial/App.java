package com.jfacetutorial;


import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
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
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.internal.dialogs.FileExtensionDialog;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jfacetutorial.controller.Controller;
import com.jfacetutorial.modellayer.UserData;
import com.jfacetutorial.modellayer.UserServiceListImpl;
import com.jfacetutorial.view.View;

import java.io.DataOutputStream;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;




public class App extends ApplicationWindow {

	private Action action;
	private Controller controller;
	private View view;

	
	public App() {
		super(null);
		createActions();
		addMenuBar();
	
	}
	private void createActions() {
		{
			action = new Action("Save") {
				@Override
				public void run() {
				FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
				dialog.setFilterPath("c:\\");
				System.out.println("RESULT=" + dialog.open());
				
				
//					DirectoryDialog dialog = new DirectoryDialog(getShell());
//		            dialog.setFilterPath("c:\\");
//		            System.out.println("RESULT=" + dialog.open());
//		            
//		            new FileUtils();
//					FileUtils.writeFile(dialog.getText(), controller.getAllUsers());
		            
		            super.run();
				}
				
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
	      menu.add(new Action("Open") {
	    	  
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
	            return "Exit";
	         }
	          
	         public void run() {
	            getShell().dispose();
	         }
	      });
	        
	      return menu;
	}

	@Override
	protected Control createContents(Composite parent) {
		
		view = new View();
		Composite composite = view.createWidgets(parent);
		controller = new Controller(view);
		createButtonsListeners();
	    return composite;
	}

	private void createButtonsListeners() {
		view.getSaveButton().addSelectionListener(controller.createSaveListener());
		view.getNewButton().addSelectionListener(controller.createNewListener());
		view.getDeleteButton().addSelectionListener(controller.createDeleteListener());
		view.getCancelButton().addSelectionListener(controller.createCancelListener());		
	}
	public void run() {
		
		setBlockOnOpen(true);
		open();
		Display.getCurrent().dispose();
	}	
}
