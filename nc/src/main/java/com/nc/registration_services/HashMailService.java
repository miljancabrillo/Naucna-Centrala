package com.nc.registration_services;

import javax.inject.Named;

import org.apache.commons.lang.RandomStringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nc.dto.RegistrationDTO;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

@Named
public class HashMailService implements JavaDelegate {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		HashMap<String, Object> map = (HashMap<String, Object>) execution.getVariable("registrationData");
		ObjectMapper mapper = new ObjectMapper();
		RegistrationDTO rdto = mapper.convertValue(map, RegistrationDTO.class);
		
		String random = RandomStringUtils.randomAlphanumeric(10);
		System.out.println(random);
		System.out.println(random);
		System.out.println(random);
		//sendMail("Verification code: "+random, rdto.getEmail());
		
		execution.setVariable("hashValue", random);
	}
	
	//prebacit dole da se salje email pravom korisniku kad im dodamo prave adrese
	public void sendMail(String content, String email) throws AddressException, MessagingException, IOException {
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
			   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("miljanupp@gmail.com", "uppuppupp");
		      }
		   });
		   
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("miljanupp@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		   msg.setSubject("NC registration!");
		   msg.setContent("NC registration", "text/html");
		   msg.setSentDate(new Date());
		   
		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent(content, "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
			  
		   msg.setContent(multipart);
		   Transport.send(msg);   
	}
}
