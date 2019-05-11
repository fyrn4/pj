package com.example.demo.service;

import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMail(String recipient,String content) throws MessagingException{
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject("금빛주야간보호센터-임시비밀번호 안내");
		message.setSender(new InternetAddress("fyrn0558@gmail.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		message.setContent(content,"text/html; charset=utf-8");
		message.setSentDate(new Date());
		javaMailSender.send(message);
	}
	
	
}
