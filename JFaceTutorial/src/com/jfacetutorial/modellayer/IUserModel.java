package com.jfacetutorial.modellayer;

import java.util.List;

public interface IUserModel {

	List<IUser> getAllUsers();
	void addUser(IUser user);
	IUser update(IUser user);
	void delete(int id);
	IUser getUserById (int id);
	
}
