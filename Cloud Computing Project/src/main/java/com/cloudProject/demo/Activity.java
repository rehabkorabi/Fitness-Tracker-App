package com.cloudProject.demo;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Container(containerName = "Activities")
public class Activity {

	@Id
	@PartitionKey
	
	String activityID;
	private int pe;
	int minutes;
	String activity_type;
	
	public Activity() {
		
	}
	@JsonCreator
	public Activity(@JsonProperty("activityID")String activityID, @JsonProperty("minutes")int minutes, @JsonProperty("activity_type")String activity_type) {
		this.activityID = activityID;
		this.minutes = minutes;
		this.activity_type = activity_type;
	}
	public String getID() {
		return activityID;
	}
	public void setID(String ID) {
		activityID = ID;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public String getActivity_type() {
		return activity_type;
	}
	public void setActivity_type(String activity_type) {
		this.activity_type = activity_type;
	}
	
}
