package com.application.publishers.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import freemarker.template.Configuration;
import com.application.publishers.Event.Event;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class MailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	Configuration fmConfiguration;
	
//	public String sendSimpleEmail(Mail mail) {
//		
//		SimpleMailMessage message=new SimpleMailMessage();
//		message.setFrom("boot123spring@gmail.com");
//		message.setTo(mail.getToEmail());
//		message.setText(mail.getBody());
//		message.setSubject(mail.getSubject());
//		mailSender.send(message);		
//		return "Mail sent Successfully!";
//		
//	}
//	
//	public String sendEmailwithAttachment(Mail mail) throws MessagingException {
//		
//		MimeMessage mimeMessage=mailSender.createMimeMessage();
//		
//		MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage, true);
//		
//		messageHelper.setFrom("boot123spring@gmail.com");
//		messageHelper.setTo(mail.getToEmail());
//		messageHelper.setText(mail.getBody());
//		messageHelper.setSubject(mail.getSubject());
//		messageHelper.addCc("mounika.ht@gmail.com");
//		
//		FileSystemResource filesystem=new FileSystemResource(new File(mail.getAttachment()));
//		messageHelper.addAttachment(filesystem.getFilename(),filesystem);
//		
//		mailSender.send(mimeMessage);
//		
//		return "Mail sent Successfully!";
//		
//	}
	 @EventListener(Event.class)     
	public String sendEmail(Event event) {
		
	     MimeMessage mimeMessage =mailSender.createMimeMessage();
	        try {
	 
	            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
	 
	            mimeMessageHelper.setSubject(event.getMail().getSubject());
	            mimeMessageHelper.setFrom("boot123spring@gmail.com");
	            mimeMessageHelper.setTo(event.getMail().getToEmail());
	              event.getMail().setBody(getContentFromTemplate(event.getMail().getModel(), event));
	            mimeMessageHelper.setText(event.getMail().getBody(),true);
	           

//	            FileSystemResource filesystem=new FileSystemResource(new File("D:/NEW-WORKSPACE/panda.png"));
//	    		mimeMessageHelper.addAttachment(filesystem.getFilename(),filesystem);
	    		
	            mailSender.send(mimeMessageHelper.getMimeMessage());
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    	return "Mail sent Successfully!";
	    }
	 
	    public String getContentFromTemplate(Map < String, Object >model,Event event)     { 
	        StringBuffer content = new StringBuffer();
	 
	        try {
	            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(fmConfiguration.getTemplate(event.getMail().getTemplate()), model));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return content.toString();
	    }
 }
