package com.jfacetutorial.modellayer;

import java.util.UUID;

public class UserDataImpl implements UserData{

	private long id;
	private String name;
	private String group;
	private boolean taskDone;
	
	public UserDataImpl(String name, String group, boolean taskDone) {
		this.name = name;
		this.group = group;
		this.taskDone = taskDone;
		this.id = GenerateUtils.generateId(name,group,taskDone);
	}
	
//	private long generateId(String name, String group, boolean taskDone) { // util
//		long result;
//		result = this.name.hashCode() + this.group.hashCode() 
//		+ String.valueOf(this.taskDone).hashCode();
//		System.out.println(result);
//		return result;
//	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (taskDone ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDataImpl other = (UserDataImpl) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (taskDone != other.taskDone)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserDataImpl [id=" + id + ", name=" + name + ", group=" + group + ", taskDone=" + taskDone + "]";
	}

	public UserDataImpl() {
	}

	public String getName() {
		return name;
	}

	public String getGroup() {
		return group;
	}

	public boolean isTaskDone() {
		return taskDone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public void setTaskDone(boolean taskDone) {
		this.taskDone = taskDone;
	}

	public long getId() {
		return id;
	}

	
	
	

}
