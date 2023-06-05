package com.application.publishers.service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.application.publishers.Event.Event;
import com.application.publishers.model.Mail;
import com.application.publishers.model.Publisher;
import com.application.publishers.repository.PublisherRepository;

@Service
public class PublisherService {

	@Autowired
	PublisherRepository publisherRepository;
	 private final ApplicationEventPublisher eventPublisher;
		
	public PublisherService(ApplicationEventPublisher eventPublisher) {		
			this.eventPublisher = eventPublisher;
	}
	
	//To Retrieve all publishers
	public List<Publisher> getAllPublishers(){
		List<Publisher> publishers=new ArrayList<>();
		publisherRepository.findAll()
		.forEach(publishers::add);
		return publishers;
	}
	
	//To Retrieve single publisher by Id
	public Publisher getPublisher(Long id) {		
		return publisherRepository.findById(id).get();
	}
	

	//To Create publisher
	public ResponseEntity<?> addPublisher(Publisher publisher) {
		publisherRepository.save(publisher);
		Mail mail=new Mail();
		Map<String,Object> model=new HashMap();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("status", 1);
		map.put("message", "Publisher Created Successfully!");
		
		model.put("name",publisher.getName());
		model.put("suffix",publisher.getSuffix());
		mail.setModel(model);
		mail.setSubject("New Publisher Request");
		mail.setTemplate("newPublisher.flth");
		mail.setToEmail("boot123spring@gmail.com");
		
		eventPublisher.publishEvent(new Event(this,publisher,mail));
		return new ResponseEntity<>(map, HttpStatus.CREATED);
		
	}
	//To update publisher details by Id
	public void updatePublisher( Long id, Publisher publisher) {
		Publisher pu1=publisherRepository.findById(id).orElseThrow(null);
		pu1.setName(publisher.getName());
		pu1.setSuffix(publisher.getSuffix());
		pu1.setURL(publisher.getURL());
		pu1.setLogo(publisher.getLogo());
		publisherRepository.save(pu1);
	}
	
	//To delete publisher by Id
	public void deletePublisher(Long id) {
		publisherRepository.deleteById(id);	
	}
	
}
