package com.jfacetutorial.controller;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.jfacetutorial.App;
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
			view.getTableViewer().setInput(userService.getAllUsers());
			
		
		} else {
//			while(!view.getLocalUserData().equals(new UserDataImpl(name,group,check))) {
				view.setLocalUserData(convertFromInput(name,group,check));
				userService.update(view.getLocalUserData().getId(), view.getLocalUserData());
				view.getTableViewer().setInput(userService.getAllUsers());
//				view.setLocalUserData(null);
//			}
//				view.getSaveButton().setEnabled(false);
		}
		
		
		
		//String name = view.getNameInput().getText();
		//String group = view.getGroupInput().getText();
		//boolean check = view.getCheckButton().getSelection();
//		UserData userData = new UserDataImpl(name,group,check);
		
//		System.out.println(1);
//		System.out.println(userData);
//		System.out.println(view.getLocalUserData());
//		if(view.getLocalUserData() == null) {
//			view.setLocalUserData(userData);
//		}
//		
	
//			System.out.println(2);
//			System.out.println(userData);
//			System.out.println(view.getLocalUserData());
//		}
//		
//		if(view.getLocalUserData().equals(userData)) {
		
//			userService.update(view.getLocalUserData().getId(),userData);
//		} else {
//			userService.addUser(userData);
//		}
//		if(view.getLocalUserData().getId() != userData.getId()) {
//			System.out.println(3);
//			System.out.println(userData);
//			System.out.println(view.getLocalUserData());
//			userService.addUser(userData);
//		} else {
//			System.out.println(4);
//			System.out.println(userData);
//			System.out.println(view.getLocalUserData());
//			userService.addUser(view.getLocalUserData());
//		}
		
		
//		view.setLocalUserData(userData);
//		if(userData.getId() == )
//		userService.addUser(new UserDataImpl(name,group,check));
		
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
			
//				save(view.getLocalUserData());
				
			  view.getTableViewer().setInput(userService.getAllUsers());
//			  Map<Long,UserData> map = (Map<Long,UserData>) view.getTableViewer().getInput();
			  
//			  view.localUserData = (UserData) view.getTableViewer().getInput();
//			  System.out.println(view.localUserData);
			}

//			private void save(UserData localUserData) {
//				if(view.getLocalUserData().getId() == localUserData.getId())
//				userService.addUser(localUserData);
//			}
		
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
				view.getNameLabel().setText("Name *s");
				view.setLocalUserData(null);
			}
			
		};
	}
	
	public SelectionListener createDeleteListener() {
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
	
//	private void showTextInTable() {
//		
//		Controller controller = this;
//		view.setSelectedListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				System.out.println("event");
//				//saveTableTextToService(view.getNameInput().getText(),view.getGroupInput().getText(),view.getCheckButton().getSelection() );
//				//addInfoToTableFromUserData();
//			}
//		});
//	}
//
//	public void run() {
//		showTextInTable();
//		view.run();
//		
//	}
	

	
}
