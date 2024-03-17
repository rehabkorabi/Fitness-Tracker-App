//package com.cloudProject.demo;
//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class CalorieService {
//	
//	private Map<Integer, CalorieIntake> C;
//	
//	public CalorieService()
//	{
//		C = new HashMap<>();
//	}
//	
//	public List<CalorieIntake> getAllFood()
//	{
//		return new ArrayList<CalorieIntake>(C.values());
//	}
//	
//	public List<CalorieIntake> getFood(String name,int userID)
//	{
//		 List<CalorieIntake> matchingItems = new ArrayList<>();
//		 for (CalorieIntake c : C.values())
//		 {
//			 if (c.getFoodName().equals(name))
//			 {
//				 matchingItems.add(c);
//			 }
//		 }
//		 return matchingItems;
//	}
//	
//	public CalorieIntake addCalorieIntake(int userID, CalorieIntake c)
//	{
//		c.setCID(C.size()+1);
//		C.put(c.getCID(), c);
//		return c;
//	}
//	
//	
//	public CalorieIntake updateCalorie(int CID, int userID,CalorieIntake c)
//	{
//		if (c.getCID()<=0)
//		{
//			return null;
//		}
//		
//		if(C.get(c.getCID())!= null)
//		{
//			C.put(c.getCID(), c);
//		}
//		
//		else
//			c = null;
//		return c;
//	}
//	
//	public String deleteCalorie(int CID,int userID)
//	{
//		if(C.get(CID)== null)
//		{
//			return "Error... null";
//		}
//		
//		C.remove(CID);
//		return "Removed";
//	}
//}
