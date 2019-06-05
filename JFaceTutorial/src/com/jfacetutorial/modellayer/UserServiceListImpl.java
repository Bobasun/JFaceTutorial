package com.jfacetutorial.modellayer;

import java.util.HashMap;
import java.util.Map;

public class UserServiceListImpl implements UserService{
		
	private Map<Long, UserData> users;
	 
	public UserServiceListImpl() {
		users = new HashMap<>();
	}

	@Override
	public Map<Long, UserData> getAllUsers() {
		return users;
	}

	@Override
	public void addUser(UserData user) {
		long id = user.getId();
		users.put(id, user);		
	}

	@Override
	public void update(UserData user) {
		long id = user.getId();
			users.put(id, user);	
	}

	@Override
	public void delete(long id) {
		users.remove((Long)id);
	}

	@Override
	public UserData getUserById(long id) {
		return users.get(id);
	}
	
}
