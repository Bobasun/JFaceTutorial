package com.jfacetutorial;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfacetutorial.modellayer.TempUsers;
import com.jfacetutorial.modellayer.UserData;

public class TopMenu extends MenuManager {
		private Shell shell;
		private MenuManager mainMenu;
		private Supplier<Map<Long, UserData>> userSupplier;
		private Consumer<Map<Long, UserData>> userConsumer;
		private Supplier<TableViewer> tableSupplier;

		
	public TopMenu(Shell shell) {
		this.shell = shell;
	}

	public MenuManager createMenuManager() {
		mainMenu = new MenuManager();
		addMenuItems();
	    return mainMenu;
	}
	
	private void addMenuItems() {
		mainMenu.add(createFileMenu());
	    mainMenu.add(createEditMenu());
	    mainMenu.add(createHelpMenu());		
	}

	private Shell getShell() {
		return this.shell;
	}
		
private MenuManager createFileMenu() {
		 MenuManager menu = new MenuManager("File");
		 menu.add(createOpenAction());
		 menu.add(createSaveAction());
		 menu.add(createExitAction());
	     return menu;
	}

	private Action createOpenAction() {
		return new Action("Open") {
  	  
			public void run() {
				System.out.println(getShell());
				FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
				dialog.setFilterPath("c:\\");
				String path = dialog.open();
       	 		if(path!=null) {
       	 			readFile(path);
       	 		}
			}
        
		};
	}

	private void readFile(String path) {
		 ObjectMapper mapper = new ObjectMapper();
			 try (FileReader file = new FileReader(path)) {
				 TempUsers temp = mapper.readValue(file, TempUsers.class);
				 userConsumer.accept(temp.getMap());
				 tableSupplier.get().setInput(userSupplier.get());
			 } catch (FileNotFoundException e) {
				 e.printStackTrace();
			 } catch (IOException e) {
				 e.printStackTrace();
			 }				
	}

	private Action createSaveAction() {
		return new Action("Save") {
			@Override
			public void run() {
				saveToJson();
			}
		};
	}
	

	private void writeFile(String path) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			FileWriter file = new FileWriter(path);
			mapper.writeValue(file, userSupplier.get());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private IAction createExitAction() {
		return new Action("Exit") {
			public void run() {
				   
		            boolean res = MessageDialog.openConfirm(getShell(), "Exit", "Do you want to save data?");
		            if(res) {
		            	saveToJson();
		            	getShell().getDisplay().dispose();
		            } else {
		            	getShell().getDisplay().dispose();
		            }
			}

			
		};
	}
	
	private void saveToJson() {
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

public void addAllUser(Supplier<Map <Long,UserData>> supplier) {
	this.userSupplier = supplier;
}

public void setAllUser(Consumer<Map <Long,UserData>> consumer) {
	this.userConsumer = consumer;
}

public void addTableViewer(Supplier<TableViewer> supplier) {
	this.tableSupplier = supplier;
}

}
