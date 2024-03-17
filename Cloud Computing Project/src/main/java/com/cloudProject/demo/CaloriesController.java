package com.cloudProject.demo;




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
import com.cloudProject.demo.CalorieIntake;
import com.cloudProject.demo.CalorieRepository;


import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/users")


public class CaloriesController {
	
	@Autowired
    private CalorieRepository calories2;
	
	//private CalorieService calserv = new CalorieService();
//	private UserService userService = new UserService();
	
	@GetMapping(path="{userID}/calorieIntakes")
	 public ResponseEntity<List<CalorieIntake>> getAllFood() 
	{
	        System.out.println("listing all foods...");

	        List<CalorieIntake> result = new ArrayList<>();
	        calories2.findAll().iterator().forEachRemaining(result::add);
	        return new ResponseEntity<List<CalorieIntake>>(result, HttpStatus.OK);
	 }
	
//	@GetMapping(path = "{userID}/calorieIntakes")
//	public List<CalorieIntake>getAllFood(@PathVariable("userID")int userID)
//	{
//		return calserv.getAllFood();
//	}
	
	
	
	 @PostMapping(path= "{userID}/calorieIntakes")
    public ResponseEntity<CalorieIntake> addCalorieIntake(@PathVariable("userID")String userID,@RequestBody CalorieIntake c) {
		 
		 if (calories2.existsById(c.getCID())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } 
		 CalorieIntake saved = calories2.save(c);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }
        

    
	//@PostMapping
	//public ResponseEntity<CalorieIntake> addCalorieIntake(@PathVariable("userID")String userID,@RequestBody CalorieIntake c)
//	{
		//return hostService.addHost(ahost);
	//	 System.out.println("add/update " + c);
//
	      //  CalorieIntake saved = calories2.save(c);
	    //    return new ResponseEntity<>(saved, HttpStatus.CREATED);
	  //
//	}
	
//	@PostMapping(path = "{userID}/calorieIntakes")
//	public CalorieIntake addCalorieIntake(@PathVariable("userID")int userID,@RequestBody CalorieIntake c)
//	{
//		return calserv.addCalorieIntake(userID,c);
//	}
	
	@GetMapping(path="{userID}/calorieIntakes/{foodName}")
    public ResponseEntity<CalorieIntake> getFood(@PathVariable("userID")String userID,@PathVariable("foodName")String Name) {

        System.out.println("searching food " + Name);

        Optional<CalorieIntake> maybe = calories2.findById(Name, new PartitionKey(Name));
        return maybe.isPresent() ? new ResponseEntity<CalorieIntake>(maybe.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
	
//	@GetMapping(path = "{userID}/calorieIntakes/{foodName}")
//
//	public List<CalorieIntake> getCalorieIntake(@PathVariable("foodName")String Name,@PathVariable("userID")int userID)
//	{
//		return calserv.getFood(Name,userID);
//	}
	
//	 @PutMapping(path="{userID}/calorieIntakes/{CID}")  
//    public ResponseEntity<CalorieIntake> replace(@PathVariable String CID, @RequestBody CalorieIntake c) {
//        if (CID != c.getCID()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        CalorieIntake saved = calories2.save(c);
//        return new ResponseEntity<>(saved, HttpStatus.OK);
//    }
	
	
@PutMapping(path="{userID}/calorieIntakes/{CID}")  
	   public ResponseEntity<CalorieIntake> replace(@PathVariable("CID")String CID,@PathVariable("userID")String userID ,@RequestBody CalorieIntake c) {

	       System.out.println("replacing food " + CID);

	       Optional<CalorieIntake> maybe = calories2.findById(CID, new PartitionKey(CID));
	        if (!maybe.isPresent()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        CalorieIntake saved = calories2.save(c);
	        return new ResponseEntity<>(saved, HttpStatus.OK);
	    }
	 
//	@PutMapping(path = "{userID}/calorieIntakes/{CID}")
//	public CalorieIntake updateItem(@PathVariable("CID")int CID,@PathVariable("userID")int userID ,@RequestBody CalorieIntake c)
//	{
//		c.setCID(CID);
//		return calserv.updateCalorie(CID, userID, c);
//	}
	
	 @DeleteMapping(path="{userID}/calorieIntakes/{CID}")
	    public ResponseEntity<CalorieIntake> deleteUser(@PathVariable("CID")String CID,@PathVariable("userID")String userID ,@RequestBody CalorieIntake c) 
	 {

	        System.out.println("deleting food " + CID );

	        calories2.deleteById(CID, new PartitionKey(CID));
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
//	//@DeleteMapping(path = "/accounts/users/calorieIntakes/{CID}")
//	@DeleteMapping(path = "{userID}/calorieIntakes/{CID}")
//	public String getItem(@PathVariable("CID")int CID, @PathVariable("userID")int userID)
//	{
//	return calserv.deleteCalorie(CID,userID);
//}
//	}
}
