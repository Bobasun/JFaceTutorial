package com.jfacetutorial.controller;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import com.jfacetutorial.modellayer.UserData;
import com.jfacetutorial.modellayer.UserDataImpl;
import com.jfacetutorial.modellayer.UserService;
import com.jfacetutorial.modellayer.UserServiceListImpl;
import com.jfacetutorial.view.View;



public class Controller {

	private UserService userService;
	private View view;
	
	public Controller(View view) {
		this.view = view;
		userService = new UserServiceListImpl();
		
	}

//	public void save (String name, String group, boolean check) {
//		if(view.getLocalUserData() == null) {
//			view.setLocalUserData(new UserDataImpl(name,group,check));
//			userService.addUser(view.getLocalUserData());
//		} else {
//			view.setLocalUserData(convertFromInput(name,group,check));
//			userService.update(view.getLocalUserData());
//		}
//	}
	
	public void save (UserData userData) {
		if(view.getLocalUserData() == null) {
			view.setLocalUserData(userData);
			userService.addUser(view.getLocalUserData());
		} else {
			view.setLocalUserData(convertFromInput(userData.getName(),userData.getGroup(),userData.isTaskDone()));
			userService.update(view.getLocalUserData());
		}
	}
	
	
	private UserData convertFromInput(String name,String group, boolean task) {
		UserData user = view.getLocalUserData();
		user.setName(name);
		user.setGroup(group);
		user.setTaskDone(task);
		return user;
	}
	
//	public SelectionListener createSaveListener() {
//		return new SelectionAdapter() {
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				if(view.getNameInput().getText()!="" && view.getGroupInput().getText() != "") {
//					 save(view.getNameInput().getText(),view.getGroupInput().getText(), 
//							  view.getCheckButton().getSelection());
//					  deleteStar();
//				} else {
//					String[] button = { IDialogConstants.OK_LABEL };
//					MessageDialog message = new MessageDialog(e.widget.getDisplay().getActiveShell(), "Error", null, 
//							"Please, fill all fields with a star!!!", MessageDialog.ERROR, button, 0);
//					message.open();
//				}
//			 	view.getTableViewer().setInput(userService.getAllUsers());
//			}
//
//			
//		};
//	}
	
	
//	private void deleteStar() {
//		 view.getNameLabel().setText("Name");
//		 view.getGroupLabel().setText("Group");				
//	}
	
//	private void setStar() {
//		view.getNameLabel().setText("Name *");
//		view.getGroupLabel().setText("Group *");	
//	}
	
//	public SelectionListener createNewListener() {
//		return new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				view.getNameInput().setText("");
//				view.getGroupInput().setText("");
//				view.getCheckButton().setSelection(false);
//				setStar();
//				view.setLocalUserData(null);
//			}
//		};
//	}
	
//	public SelectionListener createDeleteListener() {
//		return new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				if(view.getLocalUserData()==null) {
//					String[] buttons = { IDialogConstants.OK_LABEL };
//					MessageDialog message = new MessageDialog(e.display.getActiveShell(), "Error", null, 
//							"Please, select or add item before delete:)", MessageDialog.ERROR, buttons, 0);
//					message.open();
//					return;
//				}
//				userService.delete(view.getLocalUserData().getId());
//				setStar();
//				view.getTableViewer().setInput(userService.getAllUsers());
//			
//			}
//		};
//	}
	
	public void delete(Long id) {
		userService.delete(id);
	}
	
//	public SelectionListener createCancelListener() {
//		return new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				view.getNameInput().setText("");
//				view.getGroupInput().setText("");
//				setStar();
//				view.getCheckButton().setSelection(false);
//			}
//			
//		};
//	}
		
	public Map <Long,UserData> getAllUsers() {
		return userService.getAllUsers();
	}

	public void delete(long id) {
		userService.delete(id);
	}

	public void setAllUsers(Map<Long, UserData> map) {
		userService.setAllUsers(map);
	}
}
