package com.cloudProject.demo;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azure.cosmos.models.PartitionKey;
import com.cloudProject.demo.User;
import com.cloudProject.demo.UserRepository1;

@RestController
@RequestMapping("/users")
public class UserController {
	
//	private  UserService userService=new UserService();
//    private ActivityRecService ActivityRecservice =new ActivityRecService();
//    private CalorieRecService CalorieRecservice =new CalorieRecService();
    
	@Autowired
	private UserRepository1 users2;
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() 
	{
	       System.out.println("listing all users...");

	       List<User> result = new ArrayList<>();
	       users2.findAll().iterator().forEachRemaining(result::add);
	       return new ResponseEntity<List<User>>(result, HttpStatus.OK);
	}
	@GetMapping(path="{userID}")
    public ResponseEntity<User> getUser(@PathVariable("userID")String id) {

        System.out.println("searching user " + id);

        Optional<User> maybe = users2.findById(id, new PartitionKey(id));
        return maybe.isPresent() ? new ResponseEntity<User>(maybe.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
//  @GetMapping(path="{userID}")
//  public User getUser(@PathVariable("userID") int userID) {
//      return userService.getUser(userID);
//  }

// @PostMapping
//	public ResponseEntity<User> addUser(@RequestBody User user)
//	{	u
//		 System.out.println("add/update " + user);
//
//	        User saved = users2.save(user);
//	        return new ResponseEntity<>(saved, HttpStatus.CREATED);
//	  
//	}

	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user) {
	    // Check if a user with the given ID already exists
	    if (users2.existsById(user.getUserID())) {
	        // If user exists, return a conflict status
	        return new ResponseEntity<>(HttpStatus.CONFLICT);
	    }
	    // Save the new user
	    User saved = users2.save(user);
	    return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	
	
//    @PostMapping
//    public User addUser(@RequestBody User user) {
//        return userService.addUser(user);
//    }
	
	@PutMapping(path="{userID}")  
		public ResponseEntity<User> replace(@PathVariable String userID, @RequestBody User user)
		 {	System.out.println("updating user " + userID);
		    // Check if the ID in the path and the user's ID match
		 Optional<User> maybe = users2.findById(userID,new PartitionKey(userID));
	        if (!maybe.isPresent()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
		    // Replace the existing user
		    User saved = users2.save(user);
		    return new ResponseEntity<>(saved, HttpStatus.OK);
		}
	
	
//	
//	   @PutMapping(path="{userID}")  
//	    public ResponseEntity<User> replace(@RequestBody User user) {
//		   
//		   if (users2.existsById(user.getUserID())) {
//		        // If user exists, return a conflict status
//		        return newh ResponseEntity<>(HttpStatus.CONFLICT);
//		    }
//
//	        System.out.println("replacing User " + user.getUserID());
//
//	        Optional<User> maybe = users2.findById(user.getUserID(), new PartitionKey(user.getUserID()));
//	        if (!maybe.isPresent()) {
//	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	        }
//
//	        User saved = users2.save(user);
//	        return new ResponseEntity<>(saved, HttpStatus.OK);
//	    }
//    @PutMapping(path="{userID}")
//    public User updateUser(@PathVariable("userID") int userID, @RequestBody User user) {
//    	user.setUserID(userID);
//        return userService.updateUser(user);
//    }

	   @DeleteMapping(path = "{userID}")
	    public ResponseEntity<User> deleteUser(@PathVariable("userID") String id) {

	        System.out.println("deleting user " + id);

	        users2.deleteById(id, new PartitionKey(id));
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
	   
//    @DeleteMapping(path="{userID}")
//    public String deleteUser(@PathVariable("userID") int userID) {
//        return userService.deleteUser(userID);
//    }
//    

//
//    @GetMapping(path= "{userID}/activityRecs")
//	public String getActivityRec(@PathVariable("userID")int userID,User user) {
//		return ActivityRecservice.getActivityRecommendation(userID, user);
//	}
//    @GetMapping(path= "{userID}/calorieRecs")
//	public String getCalorieRec(@PathVariable("userID")int userID, User user) {
//		return CalorieRecservice.getCalorieRec(userID, user);}
	

    
}