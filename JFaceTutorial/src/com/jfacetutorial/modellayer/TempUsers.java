package com.jfacetutorial.modellayer;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class TempUsers {

	@JsonProperty("map")
	@JsonDeserialize(keyUsing = MyUserDataDeserializer.class)
	private Map<Long, UserData> usersFromJson;

	@JsonCreator
	public TempUsers(Map<Long, UserData> map) {
		this.usersFromJson = map;
	}

	public Map<Long, UserData> getMap() {
		return usersFromJson;
	}

	public void setMap(Map<Long, UserData> map) {
		this.usersFromJson = map;
	}

}
