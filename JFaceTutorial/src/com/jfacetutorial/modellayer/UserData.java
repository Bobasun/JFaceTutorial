package com.jfacetutorial.modellayer;

public interface UserData {
	
	String getName();
	String getGroup();
	boolean isTaskDone();
	void setName(String name);
	void setGroup(String group);
	void setTaskDone(boolean taskDone);
	long getId();
}
