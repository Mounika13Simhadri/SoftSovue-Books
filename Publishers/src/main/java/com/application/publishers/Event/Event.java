package com.application.publishers.Event;

import org.springframework.context.ApplicationEvent;

import com.application.publishers.model.Mail;
import com.application.publishers.model.Publisher;
import com.application.publishers.model.User;

public class Event extends ApplicationEvent{
	private User user;
	private Mail mail;
	private Publisher publisher;

	public Event(Object source,  Publisher publisher,Mail mail) {
		super(source);
		this.mail = mail;
		this.publisher = publisher;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	public Event(Object source) {
		super(source);
	}
	public Event(Object source, User user, Mail mail) {
		super(source);
		this.user = user;
		this.mail = mail;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Mail getMail() {
		return mail;
	}
	public void setMail(Mail mail) {
		this.mail = mail;
	}


}
