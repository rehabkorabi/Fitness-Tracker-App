package com.cloudProject.demo;


import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.cloudProject.demo.Activity;
import com.cloudProject.demo.ActivitiesRepository;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/users")
public class ActivitiesController {
	
	 @Autowired
	 private ActivitiesRepository activities2;
	
//private ActivityService Activityservice =new ActivityService();
//private  UserService userService=new UserService();


	@GetMapping(path="{userID}/activities")
	public ResponseEntity<List<Activity>> getAllActivities(@PathVariable("userID") String userID) 
	{
	       System.out.println("listing all activities...");
	
	       List<Activity> result = new ArrayList<>();
	       activities2.findAll().iterator().forEachRemaining(result::add);
	       return new ResponseEntity<List<Activity>>(result, HttpStatus.OK);
	}

//	@GetMapping(path= "{userID}/activities")
//	public List<Activity> getAllActivities(@PathVariable("userID")int userID){
//		return Activityservice.getAllActivities();
//	}
	

//@GetMapping(path="{userID}/activities/by-type/{activity_type}") 
	@GetMapping(path="{userID}/activities/{activity_type}")
	public ResponseEntity<Activity> getActivity(@PathVariable("userID")String userID, @PathVariable("activity_type")String type) {
	
	        Optional<Activity> maybe = activities2.findById(type, new PartitionKey(type));
	        return maybe.isPresent() ? new ResponseEntity<Activity>(maybe.get(), HttpStatus.OK)
	                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	
	    }
	
//	@GetMapping(path= "{userID}/activities/{activity_type}")
//	
//	public Activity getActivitybyType(@PathVariable("activity_type")String type,@PathVariable("userID")int userID) {
//		return Activityservice.getActivityByType(userID, type);
//	}
	
		 @PostMapping(path="{userID}/activities")
	    public ResponseEntity<Activity> addActivity(@PathVariable("userID")String userID,@RequestBody Activity activity) {
	        if (activities2.existsById(activity.getID())) {
	            return new ResponseEntity<>(HttpStatus.CONFLICT);
	        }
	        Activity saved = activities2.save(activity);
	        return new ResponseEntity<>(saved, HttpStatus.CREATED);
	    }
	
	
//	@PostMapping
//	public ResponseEntity<Activity> addActivity(@RequestBody Activity activity)
//	{
//		//return hostService.addHost(ahost);
//		 System.out.println("add/update " + activity);
//
//	        Activity saved = activities2.save(activity);
//	        return new ResponseEntity<>(saved, HttpStatus.CREATED);
//	  
//	}
//	@PostMapping (path= "{userID}/activities")
//	public Activity addActivity(@PathVariable("userID") int userID,@RequestBody Activity anActivity) {
//		
//		return Activityservice.addActivity(userID, anActivity);
//	}



//    @PutMapping(path="{userID}/activities/{activityID}")  
//	 //@PutMapping(path="{userID}/activities/by-id/{activityID}")
//
//	 public ResponseEntity<Activity> replace(@PathVariable String activityID, @RequestBody Activity activity) {
//        if (activityID != activity.getID()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        Activity saved = activities2.save(activity);
//        return new ResponseEntity<>(saved, HttpStatus.OK);
//    }

	
	@PutMapping(path="{userID}/activities/{activityID}")  
   public ResponseEntity<Activity> replace(@PathVariable("userID")String userID,@RequestBody Activity activity,@PathVariable("activityID")String activityID)
	{
        Optional<Activity> maybe = activities2.findById(activity.getID(), new PartitionKey(activityID));
        if (!maybe.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Activity saved = activities2.save(activity);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }
	
//	@PutMapping(path="{userID}/activities/{activityID}")
//	public Activity updateActivity(@PathVariable("userID")int userID,@PathVariable("activityID") int activityID, @RequestBody Activity newActivity) {
//		newActivity.setID(activityID);
//		return Activityservice.updateActivity(userID,newActivity);
//		
//	}
	
	@DeleteMapping(path = "{userID}/activities/{activityID}")
	 //@DeleteMapping(path = "{userID}/activities/by-id/{activityID}")
	 public ResponseEntity<Activity> deleteUser(@PathVariable("userID")String userID,@PathVariable("activityID")String activityID, @RequestBody Activity activity) {

        activities2.deleteById(activityID, new PartitionKey(activityID));
        return new ResponseEntity<>(HttpStatus.OK);
    }
//	@DeleteMapping(path="{userID}/activities/{activityID}")
//	public String deleteActivity(@PathVariable("userID")int userID,@PathVariable("activityID") int activityID) {
//		return Activityservice.deleteActivity(userID,activityID);
//	}
}
