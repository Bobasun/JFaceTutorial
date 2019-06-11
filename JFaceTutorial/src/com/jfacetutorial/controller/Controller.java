package com.jfacetutorial.controller;

import java.util.Map;

import com.jfacetutorial.modellayer.UserData;
import com.jfacetutorial.modellayer.UserService;
import com.jfacetutorial.modellayer.UserServiceListImpl;

public class Controller {

	private UserService userService;

	public Controller() {
		userService = new UserServiceListImpl();
	}

	public void save(UserData userData) {

		if (getAllUsers().containsKey(userData.getId())) {
			userService.update(userData);
		} else {
			userService.add(userData);
		}

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
