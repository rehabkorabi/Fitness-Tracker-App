package com.cloudProject.demo;

import java.util.HashMap;
import java.util.Map;



public class DBClass {
	private static Map<Integer, Activity> activities= new HashMap<>();
	public static Map<Integer, Activity> getActivities(){
		return activities;
	}
}
