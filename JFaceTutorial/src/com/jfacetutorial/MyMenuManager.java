package com.jfacetutorial;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfacetutorial.modellayer.TempUsers;

public class MyMenuManager {

//	private MenuManager mainMenu;
//	
//	public MyMenuManager() {
//	
//		this.mainMenu = new MenuManager();
//		conpositeMenuManager();
//	}
//
//	private void conpositeMenuManager() {
//	    mainMenu.add(createFileMenu());
//	      
//	      MenuManager menuManager_1 = createEditMenu();
//	      menuManager_1.setVisible(true);
//	      mainMenu.add(menuManager_1);
//	      
//	      MenuManager menuManager_2 = createHelpMenu();
//	      menuManager_2.setVisible(true);
//	      mainMenu.add(menuManager_2);
//	}
//
//	private MenuManager createEditMenu() {
//		MenuManager menu = new MenuManager();
//		menu.setMenuText("Edit");
//		menu.add(new Action("Edit") {
//	         public void run() {
//	          
//	         }
//	      });
//		return menu;
//	}
//
//	private MenuManager createHelpMenu() {
//		MenuManager menu = new MenuManager();
//		menu.setMenuText("Help");
//		menu.add(new Action() {
//	         public String getText() {
//	            return "About";
//	         }
//	          
//	         public void run() {
//		            String[] buttons = { IDialogConstants.OK_LABEL };
//
//		            MessageDialog dialog = new MessageDialog(getShell(), "Help", null,
//                                    "Test application done by Volodymyr!",
//                                    MessageDialog.INFORMATION, buttons, 0);
//		            dialog.open();   
//	         }
//	      });
//		return menu;
//	}
//	private void writeFile(String path) {
//		ObjectMapper mapper = new ObjectMapper();
//		System.out.println(controller.getAllUsers());
//		try {
//			FileWriter file = new FileWriter(path);
//			mapper.writeValue(file, controller.getAllUsers());
//			
//			String jsonInString = mapper.writeValueAsString(controller.getAllUsers());
//			System.out.println(jsonInString);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//	}
//	private void createActions() {
//		{
//			action = new Action("Save") {
//				@Override
//				public void run() {
//				FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
//				dialog.setFilterPath("c:\\");
//				String[] ext = new String[] {".json"};
//	        	dialog.setFilterExtensions(ext);
//				dialog.setFileName("*" + ".json");
//				String path = dialog.open();
//					if(path!=null) {
//						writeFile(path);
//					}
//				}
//				
//			};
//		}
//	}
//	
//	private void readFile(String path) {
//		 ObjectMapper mapper = new ObjectMapper();
//			 try (FileReader file = new FileReader(path)) {
//				 TempUsers temp = mapper.readValue(file, TempUsers.class);
//				 controller.setAllUsers(temp.getMap());
//				 view.getTableViewer().setInput(controller.getAllUsers());
//			 } catch (FileNotFoundException e) {
//				 e.printStackTrace();
//			 } catch (IOException e) {
//				 e.printStackTrace();
//			 }				
//			}
//	
//private MenuManager createFileMenu() {
//		 MenuManager menu = new MenuManager("&File", "Id01");
//	      menu.add(new Action("Open") {
//	    	  
//	         public void run() {
//	        	FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
//	        	dialog.setFilterPath("c:\\");
//	   	        String path = dialog.open();
//	        	 		if(path!=null) {
//	        	 			readFile(path);
//	        	 		}
//	         }
//	      });
//	      menu.add(action);
//	 
//	      menu.add(new Action() {
//	         public String getText() {
//	            return "Exit";
//	         }
//	          
//	         public void run() {
//	            getShell().dispose();
//	         }
//	      });
//	        
//	      return menu;
//	}
	
}
