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

	public void save (UserData userData) {
		if(view.getLocalUserData() == null) {
			view.setLocalUserData(userData);
			userService.addUser(view.getLocalUserData());
		} else if(getAllUsers().containsValue(userData)) {
			return;
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
	
	public void delete(Long id) {
		userService.delete(id);
	}
	
		
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
