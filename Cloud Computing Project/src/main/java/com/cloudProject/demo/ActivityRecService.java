//package com.cloudProject.demo;
//
//import java.util.Map;
//
//public class ActivityRecService {
//	
//
//	private Map<Integer,ActivityRec> activityRecs;
//	//private Map<Integer,Activity> activities;
//	
//	public ActivityRecService() {
//		activityRecs=DBClassRec.getActivityRec();
//	}
//	public String getActivityRecommendation(int userID,User user) {
//        for (ActivityRec Recs : activityRecs.values()) {
//            if (Recs.getActivity().getMinutes()> user.getDailyactivitymins()) {
//                return Recs.getRecommendations().get(0); //exceed goal
//            } 
//            else if (Recs.getActivity().getMinutes()< user.getDailyactivitymins()) {
//                return Recs.getRecommendations().get(1); //less than goal
//        }
//            else return Recs.getRecommendations().get(2); // neither
//    }
//        return null;
//}
//
//}