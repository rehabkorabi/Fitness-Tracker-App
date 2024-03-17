package com.cloudProject.demo;
import java.util.ArrayList;


import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Container(containerName = "Users")
public class User {
	
	@Id
	@PartitionKey
	
	private String userID;
	private int pe;
    private String username;
    private String password;
    private String name;
    private int age;
    private String gender;
    private String email;
	private String number;
    private double weight;
    private double height;
    private String cardiovascularDiseases;
    private String respiratoryConditions;
    private double weightGoal;
    private int dailyactivitymins;
    private double timeFrame;
    private String activityLevel;
//    private ArrayList<CalorieIntake> calorieIntakes = new ArrayList<CalorieIntake>();
//    private Activity activity;

    //Constructor
    @JsonCreator
    public User(@JsonProperty("username")String username, @JsonProperty("password")String password, @JsonProperty("name")String name,@JsonProperty("age") int age,@JsonProperty("gender") String gender,
    		@JsonProperty("email")String email, @JsonProperty("number")String number,@JsonProperty("weight") double weight, @JsonProperty("height")double height,@JsonProperty("cardiovascularDiseases") String cardiovascularDiseases,
    		@JsonProperty("respiratoryConditions")String respiratoryConditions, @JsonProperty("weightGoal")double weightGoal,@JsonProperty("dailyactivitymins") int dailyactivitymins,@JsonProperty("timeFrame") double timeFrame,
    		@JsonProperty("activityLevel")String activityLevel) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.email = email;
    this.number = number;
    this.weight = weight;
    this.height = height;
    this.cardiovascularDiseases = cardiovascularDiseases;
    this.respiratoryConditions = respiratoryConditions;
    this.weightGoal = weightGoal;
    this.dailyactivitymins = dailyactivitymins;
    this.timeFrame = timeFrame;
    this.activityLevel = activityLevel;
// this.calorieIntakes = new ArrayList<>();
//    this.activity = new Activity();
}
	
	public User() {
	    // Default constructor
	}
	//Setters and Getters
//    
//public void setActivity(Activity activity) {
//		this.activity = activity;	}
   
	public String getUserID() {
		return userID;
	}
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String getCardiovascularDiseases() {
		return cardiovascularDiseases;
	}
	public void setCardiovascularDiseases(String cardiovascularDiseases) {
		this.cardiovascularDiseases = cardiovascularDiseases;
	}
	public String getRespiratoryConditions() {
		return respiratoryConditions;
	}
	public void setRespiratoryConditions(String respiratoryConditions) {
		this.respiratoryConditions = respiratoryConditions;
	}
	public double getWeightGoal() {
		return weightGoal;
	}
	public void setWeightGoal(double weightGoal) {
		this.weightGoal = weightGoal;
	}
	public double getTimeFrame() {
		return timeFrame;
	}
	public void setTimeFrame(double timeFrame) {
		this.timeFrame = timeFrame;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
	public int getDailyactivitymins() {
		return dailyactivitymins;
	}
	public void setDailyactivitymins(int dailyactivitymins) {
		this.dailyactivitymins = dailyactivitymins;
	}


//
//	public CalorieIntake getCalorieIntakes(int index) {
//		return calorieIntakes.get(index);
//	}
//	public CalorieIntake getCalorieIntakes(String foodName) {
//	    for (CalorieIntake intake : calorieIntakes) {
//	        if (intake.getFoodName().equals(foodName)) {
//	            return intake;
//	        }
//	    }
//	    return null; // Return null if the foodName is not found in the list
//	}
//	 public ArrayList<CalorieIntake> getCalorieIntakes() {
//	        return calorieIntakes;
//	    }
//	
//	public ArrayList<CalorieIntake> getCalorieIntakesList() {
//		return calorieIntakes;
//	}
//
////
//	public void setCalorieIntakes(CalorieIntake c) {
//		calorieIntakes.add(c);
//		
//	}
//
//
//
//	public Activity getActivity() {
//		return activity;
//}



	
	
   
    
}