package com.jfacetutorial.modellayer;

public class GenerateUtils {

	public static long generateId(String name, String group, boolean taskDone) {
		long result;
		result = name.hashCode() + group.hashCode() 
		+ String.valueOf(taskDone).hashCode();
		return result;
	}
}
