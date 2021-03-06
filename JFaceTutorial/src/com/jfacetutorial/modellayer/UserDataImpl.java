package com.jfacetutorial.modellayer;

import com.fasterxml.jackson.annotation.JsonValue;

public class UserDataImpl implements UserData {

	private long id;
	private String name;
	private String group;
	private boolean taskDone;

	
	public UserDataImpl()  {
		this.id = GenerateUtils.generateId();
	}

	public UserDataImpl(String name, String group, boolean taskDone) {
		this.name = name;
		this.group = group;
		this.taskDone = taskDone;
		this.id = GenerateUtils.generateId();
	}

	public UserDataImpl(String both) {
		String[] users = both.split("and");
		this.id = Long.parseLong(users[0].trim());
		this.name = users[1].trim();
		this.group = users[2].trim();
		this.taskDone = Boolean.valueOf(users[3].trim());
	}


	@Override
	@JsonValue
	public String toString() {
		return id + " and " + name + " and " + group + " and " + taskDone;
	}



	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		return true;
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
