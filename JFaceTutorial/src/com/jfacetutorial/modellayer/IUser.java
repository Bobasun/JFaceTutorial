package com.jfacetutorial.modellayer;

public interface IUser {
	
	String getName();
	int getGroup();
	boolean isTaskDone();
	void setName(String name);
	void setGroup(int group);
	void setTaskDone(boolean taskDone);
	int getId();
}
