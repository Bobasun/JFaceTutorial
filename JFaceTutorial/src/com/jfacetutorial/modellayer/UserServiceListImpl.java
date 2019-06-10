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
	public void add(UserData user) {
		if (!users.containsValue(user)) {
		 users.put(user.getId(), user);
		}
	}

	@Override
	public void update(UserData user) {
//		if (!users.containsValue(user)) {
			UserData userDataFromStore = users.get(user.getId());
			userDataFromStore.setName(user.getName());
			userDataFromStore.setGroup(user.getGroup());
			userDataFromStore.setTaskDone(user.isTaskDone());
//		}
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
