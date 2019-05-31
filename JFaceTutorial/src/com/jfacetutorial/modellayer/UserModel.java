package com.jfacetutorial.modellayer;

import java.util.ArrayList;
import java.util.List;

public class UserModel implements IUserModel{
		
	private List<IUser> users;
	
	public UserModel() {
		users = new ArrayList<>();
	}

	@Override
	public List<IUser> getAllUsers() {
		return users;
	}

	@Override
	public void addUser(IUser user) {
		int id = user.getId();
		users.add(id,user);
	}

	@Override
	public IUser update(IUser user) {
		int id = user.getId();
		IUser localUser = users.get(id);
		localUser = user;
		return localUser;
	}

	@Override
	public void delete(int id) {
		// change to boolean, and write If contains
		users.remove(id);
	}

	@Override
	public IUser getUserById(int id) {
		return users.get(id);
	}

}
