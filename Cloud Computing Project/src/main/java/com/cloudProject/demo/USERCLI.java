package com.cloudProject.demo;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;


public class USERCLI {
	
	private static final String BASE_URL = "https://webapp-users.azurewebsites.net";

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			

System.out.println("Choose an option:");
System.out.println("1. Register User");
System.out.println("2. Delete User");
System.out.println("3. Update User");
System.out.println("4. Exit");

int choice = scanner.nextInt();
scanner.nextLine(); // Consume the newline character left-over.

switch (choice) {
    case 1:
        registerUser();
        break;
    case 2:
        deleteUser();
        break;
	case 3: 
		updateUser() ;
		break;
    case 4:
        System.exit(0);
    default:
        System.out.println("Invalid option, please try again.");
		}

		}
	}
//	private static Object[] extractDataFromResponse(String jsonResponse) {
//		JSONObject user = new JSONObject(jsonResponse);
//		String password = user.optString("password", "");
//		int userType = user.optInt("type", -1);
//		return new Object[] {password, userType};
//		// This will return an empty string if the password field is not found.
//	}
	private static void registerUser() {
	 System.out.print("Enter Account Information:\n");
     System.out.print("Username: ");
     String username = scanner.nextLine();

     System.out.print("Password: ");
     String password = scanner.nextLine();
	 System.out.println("Enter Personaal Information:");

     System.out.print("Name: ");
     String name = scanner.nextLine();
     
     System.out.print("ID: ");
     String userID = scanner.nextLine();

     System.out.print("Age: ");
     int age = getIntInput();

     System.out.print("Gender: ");
     String gender = scanner.nextLine();

     System.out.print("Email: ");
     String email = scanner.nextLine();

     System.out.print("Phone Number: ");
     String number = scanner.nextLine();

     System.out.print("Weight: ");
     double weight = getDoubleInput();

     System.out.print("Height: ");
     double height = getDoubleInput();

     System.out.print("Cardiovascular Diseases: ");
     String cardiovascularDiseases = scanner.nextLine();

     System.out.print("Respiratory Conditions: ");
     String respiratoryConditions = scanner.nextLine();

     System.out.print("Weight Goal: ");
     double weightGoal = getDoubleInput();

     System.out.print("Daily Activity Minutes: ");
     int dailyactivitymins = getIntInput();

     System.out.print("Time Frame (in days): ");
     double timeFrame = getDoubleInput();

     System.out.print("Activity Level: ");
     String activityLevel = scanner.nextLine();
     
 	String jsonInputString =String.format("{\"username\":\"%s\",\"password\":\"%s\",\"name\":\"%s\",\"userID\":\"%s\",\"age\":%d,\"gender\":\"%s\",\"email\":\"%s\",\"number\":\"%s\",\"weight\":%f,\"height\":%f,\"cardiovascularDiseases\":\"%s\",\"respiratoryConditions\":\"%s\",\"weightGoal\":%f,\"dailyactivitymins\":%d,\"timeFrame\":\"%s\",\"activityLevel\":\"%s\"}",
    		    username, password, name,userID, age, gender, email, number, weight, height, cardiovascularDiseases,
    		    respiratoryConditions, weightGoal, dailyactivitymins, timeFrame, activityLevel);
 	try {
		URL url = new URL(BASE_URL +"/"+"users");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);

		try (OutputStream os = con.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		int code = con.getResponseCode();
		System.out.println("HTTP Response Code: " + code);
		if (code == 200 || code == 201) {
			System.out.println("User registered successfully.");
			displayMenuNormal(userID);
		} else {
			System.out.println("Registration failed: HTTP " + code);
		}
	} catch (Exception e) {
		System.out.println("An error occurred: " + e.getMessage());
		}
}


private static void deleteUser() {
    System.out.println("Enter user ID to delete:");
    String userToDelete = scanner.nextLine();

    try {
        // Construct the URL for the delete user endpoint
        URL url = new URL(BASE_URL + "/users/" + userToDelete);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // Set up the connection to send a DELETE request
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("userID", userToDelete); 

        int code = con.getResponseCode();
        if (code == HttpURLConnection.HTTP_NO_CONTENT) {
            System.out.println("User deleted successfully.");
        } else if (code == HttpURLConnection.HTTP_NOT_FOUND) {
            System.out.println("User not found.");
        } else {
            System.out.println("User deleted successfully.");
        }
    } catch (Exception e) {
        System.out.println("An error occurred while trying to delete the user: " + e.getMessage());
    }
}

	private static boolean displayMenuNormal(String userID) {
	    boolean continueMenu = true;
	    while (continueMenu) {
	        System.out.println("---- Logged In As a User: Welcome FitMate user!! ----");
	        System.out.println("1. Add Activity");
	        System.out.println("2. Update Activity");
	        System.out.println("3. Delete Activity");
	        System.out.println("4. Add Calories Intake");
	        System.out.println("5. Update Calories Intake");
	        System.out.println("6. Delete Calories Intake");
	        System.out.println("7. Logout");

	        int choice = scanner.nextInt();
	        scanner.nextLine(); // Consume the rest of the line
	        switch (choice) {
	            case 1:
	                addActivity(userID);
	                break;
	            case 2:
	                updateActivity(userID);
	                break;
	            case 3:
	                deleteActivity(userID);
	                break;
	            case 4:
	                addCaloriesIntake(userID);
	                break;
	            case 5:
	                updateCaloriesIntake(userID);
	                break;
	            case 6:
	                deleteCaloriesIntake(userID);
	                break;
	            case 7:
	                return false; // Log out and return to the main menu
	            default:
	                System.out.println("Invalid option, please try again.");
	        }
	    }

	    return true; // Stay in the menu
	}
	private static void updateUser() {
    // Get updated user details from the user
	System.out.println("Enter User id to update:");
	String userID= scanner.nextLine();
    System.out.println("Enter updated name:");
    String name = scanner.nextLine();

    System.out.println("Enter updated age:");
    int age = getIntInput();

    System.out.println("Enter updated gender:");
    String gender = scanner.nextLine();

    System.out.println("Enter updated email:");
    String email = scanner.nextLine();

    System.out.println("Enter updated phone number:");
    String number = scanner.nextLine();

    System.out.println("Enter updated weight:");
    double weight = getDoubleInput();

    System.out.println("Enter updated height:");
    double height = getDoubleInput();

    System.out.println("Enter updated cardiovascular diseases:");
    String cardiovascularDiseases = scanner.nextLine();

    System.out.println("Enter updated respiratory conditions:");
    String respiratoryConditions = scanner.nextLine();

    System.out.println("Enter updated weight goal:");
    double weightGoal = getDoubleInput();

    System.out.println("Enter updated daily activity minutes:");
    int dailyactivitymins = getIntInput();

    System.out.println("Enter updated time frame (in days):");
    double timeFrame = getDoubleInput();

    System.out.println("Enter updated activity level:");
    String activityLevel = scanner.nextLine();

    String jsonInputString = String.format("{\"name\":\"%s\",\"age\":%d,\"gender\":\"%s\",\"email\":\"%s\",\"number\":\"%s\",\"weight\":%f,\"height\":%f,\"cardiovascularDiseases\":\"%s\",\"respiratoryConditions\":\"%s\",\"weightGoal\":%f,\"dailyactivitymins\":%d,\"timeFrame\":\"%s\",\"activityLevel\":\"%s\"}",
            name, age, gender, email, number, weight, height, cardiovascularDiseases, respiratoryConditions, weightGoal, dailyactivitymins, timeFrame, activityLevel);

    try {
        URL url = new URL(BASE_URL + "/users/" + userID);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Accept", "application/json");

        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = con.getResponseCode();
        System.out.println("HTTP Response Code: " + code);
        if (code == 200 || code == 204) {
            System.out.println("User updated successfully.");
        } else if (code == HttpURLConnection.HTTP_NOT_FOUND) {
            System.out.println("User not found.");
        } else {
            System.out.println("Failed to update user: HTTP " + code);
        }
    } catch (Exception e) {
        System.out.println("An error occurred while trying to update the user: " + e.getMessage());
    }
}

	private static void addActivity(String userID) {
	    // Get activity details from the user
		System.out.println("Enter activity number:");
	    String activityID = scanner.nextLine();
	    System.out.println("Enter activity duration (minutes):");
	    int duration = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter activity name:");
	    String activityName = scanner.nextLine();
	    // Consume the rest of the line
		
	    // Create a JSON object for the activity
	    JSONObject activityJson = new JSONObject();
	    
	   
	    activityJson.put("activityID", activityID);
		activityJson.put("minutes", duration);
	    activityJson.put("activity_type", activityName);

	    try {
	        // Construct the URL for the add activity endpoint
	        URL url = new URL(BASE_URL +"/users/"+userID+"/activities");
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestMethod("POST");
	        con.setRequestProperty("Content-Type", "application/json; utf-8");
	        con.setRequestProperty("Accept", "application/json");
	        con.setDoOutput(true);

	        // Write the JSON object to the output stream
	        try (OutputStream os = con.getOutputStream()) {
	            byte[] input = activityJson.toString().getBytes("utf-8");
	            os.write(input, 0, input.length);
	        }

	        // Get the response code
	        int responseCode = con.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_CREATED) {
	            System.out.println("Activity added successfully.");
	        } else {
	            System.out.println("Failed to add activity: HTTP " + responseCode);
	        }
	    } catch (Exception e) {
	        System.out.println("An error occurred while trying to add the activity: " + e.getMessage());
	    }
	}
	private static void updateActivity(String userID) {
	    System.out.println("Enter activity ID to update:");
	   	String activityId= scanner.nextLine();
	     // Consume the newline character left-over

	    // Get the updated activity details from the user
	    System.out.println("Enter new activity name:");
	    String newActivityName = scanner.nextLine();
	    System.out.println("Enter new activity duration (in minutes):");
	    int newDuration = scanner.nextInt();
	    scanner.nextLine(); 
//	    System.out.println("Enter new activity number:");
//	    String newActivityID = scanner.nextLine();// Consume the newline character left-over

	    // Construct the JSON object with the updated activity details
	    JSONObject activityJson = new JSONObject();
		activityJson.put("activityID", activityId);
	    activityJson.put("minutes", newDuration);
		activityJson.put("activity_type", newActivityName);

		 try {
		        // Construct the URL for the update activity endpoint
		        URL url = new URL(BASE_URL + "/users/"+userID+"/activities/" + activityId);
		        HttpURLConnection con = (HttpURLConnection) url.openConnection();
		        con.setRequestMethod("PUT");
		        con.setRequestProperty("Content-Type", "application/json; utf-8");
		        con.setRequestProperty("Accept", "application/json");
		        con.setDoOutput(true);

		        // Convert the JSON object to a byte array
		        byte[] input = activityJson.toString().getBytes("utf-8");

		        // Write the JSON object bytes to the output stream
		        try (OutputStream os = con.getOutputStream()) {
		            os.write(input, 0, input.length);
		        }

		        // Get the response code to determine if the update was successful
		        int code = con.getResponseCode();
				//System.out.println("Registration failed: HTTP " + code);

		        // If the response code is HTTP OK, then return true for success
		        if( code == HttpURLConnection.HTTP_OK)
		        	 System.out.println("Activity updated successfully.");
		        else  System.out.println("Failed to update activity. HTTP " + code);

		    } catch (Exception e) {
		        System.out.println("An error occurred while trying to update the activity: " + e.getMessage());
		    }

	    // Call the function to update the activity
//	    boolean success = updateActivityDetails(userID, activityId, activityJson);
//	    if (success) {
//	        System.out.println("Activity updated successfully.");
//	    } else {
//	        System.out.println("Failed to update activity.");
//	    }
	}

//	private static boolean updateActivityDetails(String userID, String activityId, JSONObject activityJson) {
//	    try {
//	        // Construct the URL for the update activity endpoint
//	        URL url = new URL(BASE_URL + "/users/"+userID+"/activities/" + activityId);
//	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//	        con.setRequestMethod("PUT");
//	        con.setRequestProperty("Content-Type", "application/json; utf-8");
//	        con.setRequestProperty("Accept", "application/json");
//	        con.setDoOutput(true);
//
//	        // Convert the JSON object to a byte array
//	        byte[] input = activityJson.toString().getBytes("utf-8");
//
//	        // Write the JSON object bytes to the output stream
//	        try (OutputStream os = con.getOutputStream()) {
//	            os.write(input, 0, input.length);
//	        }
//
//	        // Get the response code to determine if the update was successful
//	        int code = con.getResponseCode();
//			System.out.println("Registration failed: HTTP " + code);
//
//	        // If the response code is HTTP OK, then return true for success
//	        return code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_NO_CONTENT;
//
//	    } catch (Exception e) {
//	        System.out.println("An error occurred while trying to update the activity: " + e.getMessage());
//	    }
//	    return false;
//	}
	private static void deleteActivity(String userID) {
    System.out.println("Enter activity ID to delete:");
    String activityId = scanner.nextLine();
    scanner.nextLine(); // Consume the newline character left-over

    try {
        // Construct the URL for the delete activity endpoint
        URL url = new URL(BASE_URL + "/users"+userID+"/activities/" + activityId);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("activityID", activityId); // Pass the username as a request header

        // Get the response code
        int code = con.getResponseCode();

        // If the response code is HTTP NO CONTENT, then return true for success
        if(code == HttpURLConnection.HTTP_NO_CONTENT)
        	 System.out.println("Activity deleted successfully.");
        else System.out.println("Failed to delete activity.");
        
    } catch (Exception e) {
        System.out.println("An error occurred while trying to delete the activity: " + e.getMessage());
    }

}

//private static boolean deleteActivityDetails(String activityId, String userID) {
//    try {
//        // Construct the URL for the delete activity endpoint
//        URL url = new URL(BASE_URL + "/users"+userID+"/activities/" + activityId);
//        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
//        con.setRequestMethod("DELETE");
//        con.setRequestProperty("Content-Type", "application/json; utf-8");
//        con.setRequestProperty("Accept", "application/json");
//        con.setRequestProperty("username", userID); // Pass the username as a request header
//
//        // Get the response code
//        int code = con.getResponseCode();
//
//        // If the response code is HTTP NO CONTENT, then return true for success
//        return code == HttpURLConnection.HTTP_NO_CONTENT;
//    } catch (Exception e) {
//        System.out.println("An error occurred while trying to delete the activity: " + e.getMessage());
//    }
//    return false;
//}
	
	private static void addCaloriesIntake(String userID) {
	    System.out.println("Enter calories intake details:");
	    
	    System.out.println("Enter food number (id):");
	    String CID = scanner.nextLine();
	    
	    System.out.println("Enter food name:");
	    String foodName = scanner.nextLine();

	    System.out.println("Enter calories amount per food:");
	    int caloriesAmount = scanner.nextInt();
	    scanner.nextLine(); // Consume the newline character left-over

	    // Create a JSON object to represent calories intake details
	  JSONObject caloriesIntake = new JSONObject();
	    caloriesIntake.put("foodName", foodName);
	    caloriesIntake.put("calories", caloriesAmount);
	    caloriesIntake.put("CID", CID);

	    try {
	        // Construct the URL for the add calories intake endpoint
	        URL url = new URL(BASE_URL + "/users/"+userID+"/calorieIntakes");
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestMethod("POST");
	        con.setRequestProperty("Content-Type", "application/json; utf-8");
	        con.setRequestProperty("Accept", "application/json");
	        con.setRequestProperty("userID", userID); // Pass the username as a request header
	        con.setDoOutput(true);

	        // Write the JSON object bytes to the output stream
	        try (OutputStream os = con.getOutputStream()) {
	            byte[] input = caloriesIntake.toString().getBytes(StandardCharsets.UTF_8);
	            os.write(input, 0, input.length);
	        }

	        // Get the response code to determine if the add operation was successful
	        int responseCode = con.getResponseCode();
	       if( responseCode == HttpURLConnection.HTTP_CREATED)
	    	   System.out.println("Calories intake added successfully.");
	       else
	    	   System.out.println("Failed to add calories intake.");
	    } catch (Exception e) {
	        System.out.println("An error occurred while trying to add calories intake: " + e.getMessage());
	       
	    }

	 
	}

//	private static boolean addCaloriesIntakeDetails(JSONObject caloriesIntake, String userID) {
//	    try {
//	        // Construct the URL for the add calories intake endpoint
//	        URL url = new URL(BASE_URL + "/users/"+userID+"/calorieIntakes");
//	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//	        con.setRequestMethod("POST");
//	        con.setRequestProperty("Content-Type", "application/json; utf-8");
//	        con.setRequestProperty("Accept", "application/json");
//	        con.setRequestProperty("userID", userID); // Pass the username as a request header
//	        con.setDoOutput(true);
//
//	        // Write the JSON object bytes to the output stream
//	        try (OutputStream os = con.getOutputStream()) {
//	            byte[] input = caloriesIntake.toString().getBytes(StandardCharsets.UTF_8);
//	            os.write(input, 0, input.length);
//	        }
//
//	        // Get the response code to determine if the add operation was successful
//	        int responseCode = con.getResponseCode();
//	        return responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED;
//	    } catch (Exception e) {
//	        System.out.println("An error occurred while trying to add calories intake: " + e.getMessage());
//	        return false;
//	    }
//	}
	private static void updateCaloriesIntake(String userID) {
	    System.out.println("Enter calories intake details to update:");

	    System.out.println("Enter food number (id):");
	    String CID = scanner.nextLine();
	    // Consume the newline character left-over

	    System.out.println("Enter updated food name:");
	    String foodName = scanner.nextLine();

	    System.out.println("Enter updated calories amount per food:");
	    int caloriesAmount = scanner.nextInt();
	    scanner.nextLine(); // Consume the newline character left-over

	    // Create a JSON object to represent updated calories intake details
	    JSONObject updatedCaloriesIntake = new JSONObject();
	    updatedCaloriesIntake.put("foodName", foodName);
	    updatedCaloriesIntake.put("calories", caloriesAmount);
	    updatedCaloriesIntake.put("CID", CID);
	    try {
	        // Construct the URL for the update calories intake endpoint
	        URL url = new URL(BASE_URL + "/users/" + userID + "/calorieIntakes/"+CID);
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestMethod("PUT");
	        con.setRequestProperty("Content-Type", "application/json; utf-8");
	        con.setRequestProperty("Accept", "application/json");
	        con.setDoOutput(true);

	        // Convert the JSON object to a byte array
//	        byte[] input = updatedCaloriesIntake.toString().getBytes("utf-8");

	        // Write the JSON object bytes to the output stream
	        try (OutputStream os = con.getOutputStream()) {
	        	byte[] input = updatedCaloriesIntake.toString().getBytes(StandardCharsets.UTF_8);
	            os.write(input, 0, input.length);
	        }
	        int responseCode = con.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            String line;
	            StringBuilder responseOutput = new StringBuilder();
	            while ((line = br.readLine()) != null) {
	                responseOutput.append(line);
	            }
	            br.close();

	            System.out.println("Calories intake updated successfully." + responseOutput.toString());
	        } else {
	        	System.out.println("Failed to update calories intake. HTTP " + responseCode);
	        }
	    } catch (Exception e) {
	    	System.out.println("An error occurred while trying to update calories intake: " + e.getMessage());

	    }

	        // Get the response code to determine if the update was successful
//	        int code = con.getResponseCode();
//
//	        // If the response code is HTTP OK, then return true for success
//	        if (code == HttpURLConnection.HTTP_OK ) {
//	        	System.out.println("Calories intake updated successfully.");
//	        } else {
//	            // Log the server's response for debugging purposes
//	            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
//	            StringBuilder response = new StringBuilder();
//	            String responseLine;
//	            while ((responseLine = br.readLine()) != null) {
//	                response.append(responseLine.trim());
//	            }
//	            System.out.println("Failed to update calories intake: " + response.toString());
//	        }
//	    } catch (Exception e) {
//	        System.out.println("An error occurred while trying to update calories intake: " + e.getMessage());
//	    }
	     //Call the function to update calories intake
//	    boolean success = updateCaloriesIntakeDetails(CID,updatedCaloriesIntake, userID);
//	    if (success) {
//	        System.out.println("Calories intake updated successfully.");
//	    } else {
//	        System.out.println("Failed to update calories intake.");
//	    }
	}
	
//	private static boolean updateCaloriesIntakeDetails(String CID, JSONObject updatedCaloriesIntake, String userID) {
//	    try {
//	        // Construct the URL for the update calories intake endpoint
//	        URL url = new URL(BASE_URL + "/users/" + userID + "/calorieintakes/"+CID);
//	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//	        con.setRequestMethod("PUT");
//	        con.setRequestProperty("Content-Type", "application/json; utf-8");
//	        con.setRequestProperty("Accept", "application/json");
//	        con.setDoOutput(true);
//
//	        // Convert the JSON object to a byte array
//	        byte[] input = updatedCaloriesIntake.toString().getBytes("utf-8");
//
//	        // Write the JSON object bytes to the output stream
//	        try (OutputStream os = con.getOutputStream()) {
//	            os.write(input, 0, input.length);
//	        }
//
//	        // Get the response code to determine if the update was successful
//	        int code = con.getResponseCode();
//
//	        // If the response code is HTTP OK, then return true for success
//	        if (code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_NO_CONTENT) {
//	            return true;
//	        } else {
//	            // Log the server's response for debugging purposes
//	            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
//	            StringBuilder response = new StringBuilder();
//	            String responseLine;
//	            while ((responseLine = br.readLine()) != null) {
//	                response.append(responseLine.trim());
//	            }
//	            System.out.println("Failed to update calories intake: " + response.toString());
//	        }
//	    } catch (Exception e) {
//	        System.out.println("An error occurred while trying to update calories intake: " + e.getMessage());
//	    }
//	    return false;
//	}
	
	private static void deleteCaloriesIntake(String userID) {
	    System.out.println("Enter food number (id) to delete calories intake:");
	    String CID = scanner.nextLine();
	    // Consume the newline character left-over

	    try {
	        // Construct the URL for the delete calories intake endpoint
	        URL url = new URL(BASE_URL + "/users/" + userID + "/calorieIntakes/" + CID);
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();

	        // Set up the connection to send a DELETE request
	        con.setRequestMethod("DELETE");
	        con.setRequestProperty("Content-Type", "application/json; utf-8");
	        con.setRequestProperty("Accept", "application/json");
	        con.setRequestProperty("CID", CID); // Pass the userID as a request header

	        int code = con.getResponseCode();
	        if(code == HttpURLConnection.HTTP_NO_CONTENT)
	        	System.out.println("Calories intake deleted successfully.");
	        else System.out.println("Failed to delete calories intake.");
	    } catch (Exception e) {
	        System.out.println("An error occurred while trying to delete the calories intake: " + e.getMessage());
	       
	    }
	    // Call the function to delete calories intake
//	    boolean success = deleteCaloriesIntakeDetails(CID, userID);
//	    if (success) {
//	        System.out.println("Calories intake deleted successfully.");
//	    } else {
//	        System.out.println("Failed to delete calories intake.");
//	    }
	}
//	private static boolean deleteCaloriesIntakeDetails(String CID, String userID) {
//	    try {
//	        // Construct the URL for the delete calories intake endpoint
//	        URL url = new URL(BASE_URL + "/users/" + userID + "/calorieintakes/" + CID);
//	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//
//	        // Set up the connection to send a DELETE request
//	        con.setRequestMethod("DELETE");
//	        con.setRequestProperty("Content-Type", "application/json; utf-8");
//	        con.setRequestProperty("Accept", "application/json");
//	        con.setRequestProperty("userID", userID); // Pass the userID as a request header
//
//	        int code = con.getResponseCode();
//	        return code == HttpURLConnection.HTTP_NO_CONTENT;
//	    } catch (Exception e) {
//	        System.out.println("An error occurred while trying to delete the calories intake: " + e.getMessage());
//	        return false;
//	    }
//	}

	 private static int getIntInput() {
	        while (!scanner.hasNextInt()) {
	            System.out.println("That's not a valid input. Please enter an integer.");
	            scanner.next(); // Consume the non-integer input
	        }
	        int input = scanner.nextInt();
	        scanner.nextLine(); // Consume the newline character
	        return input;
	    }

	    private static double getDoubleInput() {
	        while (!scanner.hasNextDouble()) {
	            System.out.println("That's not a valid input. Please enter a double.");
	            scanner.next(); // Consume the non-double input
	        }
	        double input = scanner.nextDouble();
	        scanner.nextLine(); // Consume the newline character
	        return input;
	    }
}