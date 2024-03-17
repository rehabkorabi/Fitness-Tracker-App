package com.cloudProject.demo;




public class CalorieRecService {
	
	public CalorieRecService()
	{

	}
	
	public String getCalorieRec(int userID, User u )
	{

		float maintCal;
		float actFact;
		float BMR;
		float dailyIntake;
		
		if (u.getActivityLevel() == "Light")
			actFact = (float) 1.375;
		
		else if (u.getActivityLevel() == "Heavy")
			actFact = (float) 1.55;
		
		else
			actFact = (float) 1.725;
			
		
		if  (u.getGender() == "Male")
		{
			BMR = (float) (88.362 + (13.397*u.getWeight()) + (4.799*u.getHeight())-(5.677*u.getAge()));
			maintCal = BMR * actFact;
			dailyIntake = (float) (maintCal - ((3500*u.getWeightGoal())/u.getTimeFrame()));
			String rec = "Your recommended daily calorie intake is " + dailyIntake;
			return rec;	
		}
		

		else if  (u.getGender() == "Female")
		{
			BMR = (float) (447.593 + (9.247*u.getWeight()) + (3.098*u.getHeight())-(4.330*u.getAge()));
			maintCal = BMR * actFact;
			dailyIntake = (float) (maintCal - ((3500*u.getWeightGoal())/u.getTimeFrame()));
			String rec = "Your recommended daily calorie intake is " + dailyIntake;
			return rec;	
		}
		
		return null;
	
	}
	
}
