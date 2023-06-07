package com.application.publishers.controller;

import java.lang.annotation.Target;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.publishers.dto.LoginDto;
import com.application.publishers.model.User;
import com.application.publishers.service.AdminService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
public class AdminController{

	@Autowired
	AdminService adminService;
   
	//Admin Login 
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		    String message=adminService.login(user);
			return message;	
	}
	//To Retrieve all admins
	@GetMapping("/admins")
	public List<User> getAllUsers() {
		List<User> users=adminService.getAllUsers();
		return users;
	}
	
	//To Retrieve single admin by Id
	@GetMapping("/admins/{id}")
	public User getUser(@PathVariable Long id) {
		return adminService.getUser(id);
	}
	
	//To Create Admin
	@PostMapping({"/create/super-admin","/create/publisher-admin"})
	  @Valid @ModelAttribute("email")
	public  ResponseEntity<?> addUser(@RequestBody User user){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		adminService.addUser(user);
		map.put("status", 1);
		map.put("message", "User Created Successfully!");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}
	//To update admin details|profile setting|Password changing
	@PutMapping({"/edit-admin/{id}","/settings/profile-settings/{id}","/settings/ChangePassword/{id}"})
	public User updateUser(@Valid @PathVariable  Long id,@RequestBody User user) {  
		return adminService.updateUser(id,user);
	}
	
	//To delete admin by Id.
	@DeleteMapping("/delete-admin/{id}")
	public String deleteUser(@PathVariable  Long id) {
		adminService.deleteUser(id);
		return "User Deleted Successfully!";
	}
	
	//Forgot password
	@PostMapping("/forgotPassword")
	public  ResponseEntity<?> setPassword(@RequestBody User user){
		return adminService.setPassword(user);	
	}

	
}
