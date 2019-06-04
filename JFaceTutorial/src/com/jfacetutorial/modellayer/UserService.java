package com.jfacetutorial.modellayer;

import java.util.List;
import java.util.Map;

public interface UserService {

//	List<UserData> getAllUsers();
	Map<Long, UserData> getAllUsers();
	void addUser(UserData user);
//	void update(UserData user);
	void delete(long id);
	UserData getUserById (long id);
	void update(long id, UserData user);
	
}
