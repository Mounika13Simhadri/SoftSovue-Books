package com.application.publishers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.publishers.model.Mail;
import com.application.publishers.model.User;
import com.application.publishers.service.AdminService;
@RestController
public class FirstUserController {

	@Autowired
	AdminService adminService;
	private final ApplicationEventPublisher eventPublisher;
	
	public FirstUserController(ApplicationEventPublisher eventPublisher) {		
		this.eventPublisher = eventPublisher;
	}
	
	//To create First Admin-User
	@GetMapping("/ADMIN")
	public ResponseEntity<?> addUser() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Mail mail=new Mail();	
		Map<String,Object> model=new HashMap();
		List<User> users=new ArrayList<>();
		users=adminService.getAllUsers();
		if(users.size()==0) {
			User user= new User("Admin","boot123spring@gmail.com","ADMIN");
			adminService.addUser(user);
			map.put("status", 1);
			map.put("message", "User Created Successfully!");
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		}
		else {
			map.put("status", 0);
			map.put("message", "Users Already exists!");
			return new ResponseEntity<>(map, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		}

	}
}
