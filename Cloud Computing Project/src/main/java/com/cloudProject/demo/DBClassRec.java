package com.cloudProject.demo;

import java.util.HashMap;
import java.util.Map;



public class DBClassRec {
	private static Map<Integer, ActivityRec> recommendations= new HashMap<>();
	public static Map<Integer, ActivityRec> getActivityRec(){
		return recommendations;
	}
}
