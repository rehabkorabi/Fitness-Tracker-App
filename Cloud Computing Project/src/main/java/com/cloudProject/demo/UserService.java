//package com.cloudProject.demo;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//public class UserService {
//
//    private Map<Integer, User> users;
//
//    public UserService() {
//    	users=new HashMap<>();
//
//    }
//
//    public User addUser(User user) {
//    	user.setUserID(users.size()+1);
//    	users.put(user.getUserID(), user);
//		return user;
//    }
//
//    public User updateUser(User user) {
//    	 if(user.getUserID()<=0) {
//	    	   return null;
//	    }
//	       if(users.get(user.getUserID())!= null) {
//	    	   users.put(user.getUserID(), user);
//	       }
//	       else user=null;
//	       
//	       return user;
//    }
//  
//    public String deleteUser(int userID) {
//    	if(users.get(userID)==null){
//        	return "Error...User does not exist";
//        }
//        users.remove(userID);
//        return "User got deleted";    }
//   
//    
//    public User getUser(int userID) {
//    	 User user = users.get(userID);
//         if (user == null) {
//             System.out.println("Error...User does not exist");
//         }
//         return user;
//    }
//
//    
//}