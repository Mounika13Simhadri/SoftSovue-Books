package com.application.publishers.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.application.publishers.Event.Event;
import com.application.publishers.dto.LoginDto;
import com.application.publishers.model.Mail;
import com.application.publishers.model.User;
import com.application.publishers.repository.AdminRepository;
import com.application.publishers.repository.PublisherRepository;


@Service
public class AdminService {
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	PublisherRepository publisherRepository;
	private final ApplicationEventPublisher eventPublisher;
	  static BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();	
	
		
		public AdminService(ApplicationEventPublisher eventPublisher) {		
			this.eventPublisher = eventPublisher;
		}
	
	//Admin Login 
	public String login(User user) {
		User user1;
//		String password=passwordEncoder.encode(user.getPassword());
		user1=adminRepository.findOneByEmailAndPassword(user.getEmail(),user.getPassword());
		if(user1!=null) {
			return "Login Successfull!";
		}
		else
			return "Invalid username or password";
		
	}
	
	//To Retrieve all admins 
	public List<User> getAllUsers(){
		List<User> users=new ArrayList<>();
		adminRepository.findAll()
		.forEach(users::add);
		return users;
	}

	//To Create Admin
	public void addUser(User user) {
		Map<String,Object> model=new HashMap();
		model.put("name",user.getEmail());
		model.put("email",user.getEmail());
		model.put("password",user.getPassword());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		adminRepository.save(user);
		Mail mail=new Mail();	
		//mail.setModel(model[{"name":""},{"email":""},{"password":""}])
		mail.setModel(model);
		mail.setSubject("SUPER ADMIN DETAILS");
		mail.setTemplate("superAdmin.flth");
		mail.setToEmail(user.getEmail());	
		
		eventPublisher.publishEvent(new Event(this,user,mail));
		
	}
	
	//To Retrieve single admin by Id
	public User getUser(Long id) {		
		return adminRepository.findById(id).get();
	}

	//To update admin details|profile setting|Password changing
	public User updateUser( Long id, User user) {
		User user1=adminRepository.findById(id).orElseThrow(null);
		if(user.getName()==null&&user.getEmail()==null) {
			user1.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		else {
			user1.setName(user.getName());
			user1.setEmail(user.getEmail());
		}
		adminRepository.save(user1);
		return user1;
	}
	
	//To delete admin by Id.
	public void deleteUser(Long id) {
		adminRepository.deleteById(id);
	}
	
	//To set forgot password
	public ResponseEntity<?> setPassword(User user) {
		User user1=adminRepository.findByEmail(user.getEmail());
		Mail mail=new Mail();	
		String password=user1.createPassword();
		Map<String,Object> model=new HashMap();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if(user1!=null) {
			map.put("status", 1);
			map.put("message", "Mail sent Successfully!");
			
			model.put("name",user1.getEmail());
			model.put("password",password);
			mail.setModel(model);
			mail.setSubject("New Password");
			mail.setTemplate("ForgotPassword.flth");
			mail.setToEmail(user1.getEmail());	
			user1.setPassword(password);
			adminRepository.save(user1);
			eventPublisher.publishEvent(new Event(this,user,mail));
		}
		else {
			map.put("status", 0);
			map.put("message", "The entered email address does not exist.");
		}
		
		return new ResponseEntity<>(map, HttpStatus.CREATED);
		}
}
