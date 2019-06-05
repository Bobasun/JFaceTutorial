package com.jfacetutorial.controller;

import java.util.Map;


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

	public void save (String name, String group, boolean check) {
		if(view.getLocalUserData() == null) {
			view.setLocalUserData(new UserDataImpl(name,group,check));
			userService.addUser(view.getLocalUserData());
		} else {
				view.setLocalUserData(convertFromInput(name,group,check));
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
	
	public SelectionListener createSaveListener() {
		return new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
			  save(view.getNameInput().getText(),view.getGroupInput().getText(), 
					  view.getCheckButton().getSelection());
			  view.getTableViewer().setInput(userService.getAllUsers());
			}
		};
	}
	
	public SelectionListener createNewListener() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				view.getNameInput().setText("");
				view.getGroupInput().setText("");
				view.getCheckButton().setSelection(false);
				view.getGroupLabel().setText("Group *");
				view.getNameLabel().setText("Name *");
				view.setLocalUserData(null);
			}
		};
	}
	
	public SelectionListener createDeleteListener() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				userService.delete(view.getLocalUserData().getId());
				view.getTableViewer().setInput(userService.getAllUsers());
			}
		};
	}
	
	public SelectionListener createCancelListener() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				view.getNameInput().setText("");
				view.getGroupInput().setText("");
				view.getCheckButton().setSelection(false);
			}
			
		};
	}
		
	public Map <Long,UserData> getAllUsers() {
		return userService.getAllUsers();
	}

	public void delete(long id) {
		userService.delete(id);
	}
}
