package com.jfacetutorial.modellayer;

public class UserData implements IUser{

	private int id;
	private String name;
	private int group;
	private boolean taskDone;
	
	public UserData(String name, int group, boolean taskDone) {
		this.name = name;
		this.group = group;
		this.taskDone = taskDone;
	}
	
	public UserData() {
	}

	public String getName() {
		return name;
	}

	public int getGroup() {
		return group;
	}

	public boolean isTaskDone() {
		return taskDone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public void setTaskDone(boolean taskDone) {
		this.taskDone = taskDone;
	}

	public int getId() {
		return id;
	}
}
