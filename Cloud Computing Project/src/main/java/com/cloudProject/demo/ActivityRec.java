package com.cloudProject.demo;

import java.util.ArrayList;

public class ActivityRec {
	
	

	private Activity activity;
	private ArrayList<String> recommendations=new ArrayList<String>();

	public ActivityRec(ArrayList<String> recommendations) {
		super();
		this.recommendations = recommendations;
		recommendations.add("Congrats! You have exceed your goal by!");
		recommendations.add("You need  to reach you daily activity goal!");
		recommendations.add("You have reached your daily activity goal!");
	}

	public ArrayList<String> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(ArrayList<String> recommendations) {
		this.recommendations = recommendations;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	private User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
