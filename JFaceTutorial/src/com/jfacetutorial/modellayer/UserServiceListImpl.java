package com.jfacetutorial.modellayer;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class UserServiceListImpl implements UserService {

	@JsonSerialize(keyUsing = UserDataSerializer.class)
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
		users.remove((Long) id);
	}

	@Override
	public UserData getUserById(long id) {
		return users.get(id);
	}

	@Override
	public void setAllUsers(Map<Long, UserData> map) {
		this.users = map;
	}

}
