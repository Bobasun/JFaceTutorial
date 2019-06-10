package com.jfacetutorial.controller;

import java.util.Map;


import com.jfacetutorial.modellayer.UserData;
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

	public void save(UserData userData) {

		if (getAllUsers().containsKey(userData.getId())) {
			userService.update(userData);
		} else {
			userService.add(userData);
		}
		
	}

	private UserData convertFromInput(String name, String group, boolean task) {
		UserData user = view.getLocalUserData();
		user.setName(name);
		user.setGroup(group);
		user.setTaskDone(task);
		return user;
	}

	public void delete(Long id) {
		userService.delete(id);
	}

	public Map<Long, UserData> getAllUsers() {
		return userService.getAllUsers();
	}

	public void delete(long id) {
		userService.delete(id);
	}

	public void setAllUsers(Map<Long, UserData> map) {
		userService.setAllUsers(map);
	}
}
