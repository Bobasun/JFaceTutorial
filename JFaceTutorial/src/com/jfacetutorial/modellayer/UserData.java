package com.jfacetutorial.modellayer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = UserDataImpl.class)
public interface UserData extends Cloneable {

	String getName();

	String getGroup();

	boolean isTaskDone();

	void setName(String name);

	void setGroup(String group);

	void setTaskDone(boolean taskDone);

	long getId();

}
