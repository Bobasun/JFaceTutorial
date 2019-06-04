package com.jfacetutorial.modellayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserServiceListImpl implements UserService{
		
//	private List<UserData> users;
	private Map<Long, UserData> users;
	 
	 
	public UserServiceListImpl() {
//		users = new ArrayList<>();
		users = new HashMap<>();
//		users.put((long) 1000,new UserDataImpl("Jack","Polov",false));
//		users.add(new UserDataImpl("Jack","Polov",false));
//		users.add(new UserDataImpl("Jack","Polov",false));
//		users.add(new UserDataImpl("Jack","Polov",true));
//		users.add(new UserDataImpl("Jack","Polov",true));
//		users.add(new UserDataImpl("Jack","Polov",true));
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
	public void update(long id, UserData user) {
			users.put(id, user);	
	}


	@Override
	public void delete(long id) {
		users.remove(id);
	}


	@Override
	public UserData getUserById(long id) {
		return users.get(id);
	}

	
	
//	@Override
//	public List<UserData> getAllUsers() {
//		return users;
//	}
	

//	@Override
//	public void addUser(UserData user) {
////		int id = user.getId();
//		users.add(user);
//	}

//	@Override
//	public UserData update(UserData user) {
////		int id = user.getId();
////		UserData localUser = users.get(id);
////		localUser = user;
//		return user;
//	}

//	@Override
//	public void delete(int id) {
//		// change to boolean, and write If contains
//		users.remove(id);
//	}

//	@Override
//	public UserData getUserById(int id) {
//		return users.get(id);
//	}

	
	
}
