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
//import org.json.simple.JSONObject;
//import org.json.simple.JSONValue;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.jfacetutorial.controller.Controller;
import com.jfacetutorial.modellayer.TempUsers;
import com.jfacetutorial.modellayer.UserData;
import com.jfacetutorial.modellayer.UserDataImpl;
import com.jfacetutorial.modellayer.UserServiceListImpl;
import com.jfacetutorial.view.View;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
	
	private void writeFile(String path) {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(controller.getAllUsers());
		try {
			FileWriter file = new FileWriter(path);
			mapper.writeValue(file, controller.getAllUsers());
			
			String jsonInString = mapper.writeValueAsString(controller.getAllUsers());
			System.out.println(jsonInString);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	private void createActions() {
		{
			action = new Action("Save") {
				@Override
				public void run() {
				FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
				dialog.setFilterPath("c:\\");
				String[] ext = new String[] {".json"};
	        	dialog.setFilterExtensions(ext);
				dialog.setFileName("*" + ".json");
				String path = dialog.open();
					if(path!=null) {
						writeFile(path);
					}
				}
				
			};
		}
	}
	
	private void readFile(String path) {
		 ObjectMapper mapper = new ObjectMapper();
			 try (FileReader file = new FileReader(path)) {
				 TempUsers temp = mapper.readValue(file, TempUsers.class);
				 controller.setAllUsers(temp.getMap());
				 view.getTableViewer().setInput(controller.getAllUsers());
			 } catch (FileNotFoundException e) {
				 e.printStackTrace();
			 } catch (IOException e) {
				 e.printStackTrace();
			 }				
			}
	
private MenuManager createFileMenu() {
		 MenuManager menu = new MenuManager("&File", "Id01");
	      menu.add(new Action("Open") {
	    	  
	         public void run() {
	        	FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
	        	dialog.setFilterPath("c:\\");
	   	        String path = dialog.open();
	        	 		if(path!=null) {
	        	 			readFile(path);
	        	 		}
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
	protected MenuManager createMenuManager() {
		
//		return new MyMenuManager();
		MenuManager mainMenu = new MenuManager();
	      mainMenu.add(createFileMenu());
	      
	      MenuManager menuManager_1 = createEditMenu();
	      menuManager_1.setVisible(true);
	      mainMenu.add(menuManager_1);
	      
	      MenuManager menuManager_2 = createHelpMenu();
	      menuManager_2.setVisible(true);
	      mainMenu.add(menuManager_2);
	      return mainMenu;
	}
	
	private MenuManager createEditMenu() {
		MenuManager menu = new MenuManager();
		menu.setMenuText("Edit");
		menu.add(new Action("Edit") {
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
		            String[] buttons = { IDialogConstants.OK_LABEL };

		            MessageDialog dialog = new MessageDialog(getShell(), "Help", null,
                                    "Test application done by Volodymyr!",
                                    MessageDialog.INFORMATION, buttons, 0);
		            dialog.open();   
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
		view.addAllUser(controller::getAllUsers);
		view.addSaveAction(controller::save);
		
//	    //action.addSaveListener(controller.createSaveListener());
//		view.getSaveButton().addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				// TODO Auto-generated method stub
//				view.saveConsumer.accept(new UserDataImpl(view.getNameInput().getText(),view.getGroupInput().getText(), 
//						  view.getCheckButton().getSelection()));
//				view.getTableViewer().setInput(controller.getAllUsers());
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
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
