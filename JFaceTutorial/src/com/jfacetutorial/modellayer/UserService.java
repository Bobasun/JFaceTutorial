package com.jfacetutorial.modellayer;

import java.util.HashMap;
import java.util.Map;

public interface UserService {

	Map<Long, UserData> getAllUsers();

	void add(UserData user);

	void delete(long id);

	UserData getUserById(long id);

	void update(UserData user);

	void setAllUsers(Map<Long, UserData> map);

}
