package com.appdeveloperpractice.app.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.appdeveloperpractice.app.ws.exceptions.UserServiceException;
import com.appdeveloperpractice.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.appdeveloperpractice.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appdeveloperpractice.app.ws.ui.model.response.UserRest;


import jakarta.validation.Valid;

//hellohello

@RestController
@RequestMapping("users") // http://localhost:8080/users

public class UserController {
	Map<String, UserRest> users;

	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit,
			@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
		return "get user was called page= " + page + " and limit= " + limit + " and sort " + sort;
	}

	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		
		//** Practice handler and throw custom exception **	
		//if(true) throw new UserServiceException("A user service exception is thrown");
		
		if (users.containsKey(userId))
			return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
		else
			return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);

		//** HARDCode **
		//		UserRest returnValue= new UserRest();
		//		returnValue.setEmail("test@test.com");
		//		returnValue.setLastName("Wu");
		//		returnValue.setFirstName("Joan");
		//		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}

	@PostMapping(
			 consumes = {
					 MediaType.APPLICATION_JSON_VALUE, 
					 MediaType.APPLICATION_XML_VALUE },
			 produces = {
					 MediaType.APPLICATION_JSON_VALUE, 
					 MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		UserRest returnValue=new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		
		String userId=UUID.randomUUID().toString();
		returnValue.setUserId(userId);
		
		if(users==null) users=new HashMap<>();
		users.put(userId, returnValue);
		
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}

	@PutMapping(path= "/{userId}",consumes = {
					 MediaType.APPLICATION_JSON_VALUE, 
					 MediaType.APPLICATION_XML_VALUE },
			 produces = {
					 MediaType.APPLICATION_JSON_VALUE, 
					 MediaType.APPLICATION_XML_VALUE }
			 )
	public UserRest updateUser(@PathVariable String userId, 
			@Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {
		UserRest storedUserDetails=users.get(userId);
		storedUserDetails.setFirstName(userDetails.getFirstName());
		storedUserDetails.setLastName(userDetails.getLastName());
		
		//Updated the mapping-"users"
		users.put(userId, storedUserDetails);
		
		return storedUserDetails;
	}

	@DeleteMapping(path="/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		users.remove(userId);
		return ResponseEntity.noContent().build();
	}
}
