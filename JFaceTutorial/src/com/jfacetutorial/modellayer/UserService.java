package com.jfacetutorial.modellayer;

import java.util.HashMap;
import java.util.Map;

public interface UserService {

	Map<Long, UserData> getAllUsers();
	void addUser(UserData user);
	void delete(long id);
	UserData getUserById (long id);
	void update(UserData user);
	void setAllUsers(Map<Long, UserData> map);
//	void setAllUsers(HashMap<Long, UserData> map);
	
}
