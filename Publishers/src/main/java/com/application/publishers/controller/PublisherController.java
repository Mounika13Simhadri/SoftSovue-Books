package com.application.publishers.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.publishers.model.Publisher;
import com.application.publishers.service.PublisherService;

import jakarta.validation.Valid;



@RestController

public class PublisherController {
	
	@Autowired
	PublisherService publisherService;

	//To Retrieve all publishers
	@GetMapping("/publishers")
	public List<Publisher> getAllPublishers() {
		List<Publisher> publishers=publisherService.getAllPublishers();
		return publishers;
	}
	
	//To Retrieve single publisher by Id
	@GetMapping("/publishers/{id}")
	public Publisher getPublisher(@PathVariable Long id) {
		return publisherService.getPublisher(id);
	}
	
	//To Create publisher
	@PostMapping("/createPublisher")
	public ResponseEntity<?> addPublisher(@Valid @RequestBody Publisher publisher) {

		return publisherService.addPublisher(publisher);

	}
	//To update publisher details by Id
	@PutMapping("/edit-publisher/{id}")
	public Publisher updatePublisher(@PathVariable  Long id,@RequestBody Publisher publisher) {
	  
	    publisherService.updatePublisher(id,publisher);
		return publisherService.getPublisher(id);
	}
	
	//To delete publisher by Id
	@DeleteMapping("/delete-publisher/{id}")
	public String deletePublisher(@PathVariable  Long id) {
		publisherService.deletePublisher(id);
		return "redirect:/publishers";
	}
	
}
