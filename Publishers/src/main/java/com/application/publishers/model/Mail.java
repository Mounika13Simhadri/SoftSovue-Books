package com.application.publishers.model;

import java.util.Arrays;
import java.util.Map;

import jakarta.mail.internet.InternetAddress;

public class Mail {

	private String body;
	private String toEmail;
	private String subject;
	private String attachment;
	private String template;
	private Map<String,Object> model;

	public Mail() {
		
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getBody() {
		return body;
	}
	
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public Mail( String body, String subject, String attachment) {
		super();
		this.body = body;
		this.subject = subject;
		this.attachment = attachment;
	}
	public Mail(String body, String subject, String attachment, Map<String, Object> model,String toEmail) {
		super();
		this.body = body;
		this.subject = subject;
		this.attachment = attachment;
		this.model = model;
		this.toEmail=toEmail;
	}
	public Map<String, Object> getModel() {
		return model;
	}
	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
	@Override
	public String toString() {
		return "Mail [body=" + body + ", subject=" + subject
				+ ", attachment=" + attachment + ", template="+template;
	}

}
