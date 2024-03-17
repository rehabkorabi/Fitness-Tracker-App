//package com.cloudProject.demo;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//
//
//public class ActivityService {
//private Map<Integer,Activity> activities;
//	
//	public ActivityService() {
//		activities=DBClass.getActivities();
//	}
//
//	
////	public Activity addActivity(User user, Activity anActivity) {
////		anActivity.setID(activities.size()+1);
////		activities.put(anActivity.getID(),user.getActivity());
////		return anActivity;
////	}
//	public Activity addActivity(int userID, Activity anActivity) {
//		anActivity.setID(activities.size()+1);
//		activities.put(anActivity.getID(),anActivity);
//		return anActivity;
//	}
//	
//	public List<Activity> getAllActivities(){
//		return new ArrayList<Activity>(activities.values());
//	}
//	
//	public Activity getActivityByType(int userID,String type) {
//        for (Activity activity : activities.values()) {
//            if (activity.getActivity_type().equals(type)) {
//                return activity;
//            }
//        }
//        return null;
//    }
//	
//	
//	public String deleteActivity(int userID,int activityID) {
//        if(activities.get(activityID)==null) return "Error, Null";
//        activities.remove(activityID);
//        return "removed";
//    }
//	
//	
//	 public Activity updateActivity(int userID,Activity anActivity) {
//	        if (anActivity.getID()<=0) return null;
//	        if(activities.get(anActivity.getID())!=null)
//	        {
//	        	activities.put(anActivity.getID(), anActivity);
//	        	
//	        } 
//	        else anActivity=null;
//			return anActivity;
//	        
//	            
//	    }
//}
