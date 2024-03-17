package com.cloudProject.demo;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Container(containerName = "CalorieIntakes")
public class CalorieIntake {
	
	@Id
	@PartitionKey
	
	private String CID;
	private int pe;
	private String foodName;
	private int calories;
	
	@JsonCreator
	public CalorieIntake(@JsonProperty("CID")String CID, @JsonProperty("foodName") String foodName,@JsonProperty("calories") int calories) {
		this.CID = CID;
		this.foodName = foodName;
		this.calories = calories;
	}
	
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	
	public CalorieIntake(String foodName, int calories) {
		super();
		this.foodName = foodName;
		this.calories = calories;
	}
	public String getCID() {
		return CID;
	}
	public void setCID(String iD) {
		CID = iD;
	}
}