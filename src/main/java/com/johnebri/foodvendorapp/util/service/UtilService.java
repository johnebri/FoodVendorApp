package com.johnebri.foodvendorapp.util.service;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.johnebri.foodvendorapp.customer.data.Customer;
import com.johnebri.foodvendorapp.customer.repository.CustomerRepository;
import com.johnebri.foodvendorapp.security.JwtUtil;
import com.johnebri.foodvendorapp.util.data.UtilResponse;
import com.johnebri.foodvendorapp.util.data.UtilStatus;
import com.johnebri.foodvendorapp.vendor.data.Vendor;
import com.johnebri.foodvendorapp.vendor.repository.VendorRepository;

@Service
public class UtilService {
	
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	VendorRepository vendorRepo;
	@Autowired
	CustomerRepository customerRepo;
	
	UtilResponse utilResponse = new UtilResponse();
	UtilStatus utilStatus = new UtilStatus();	
	
	public UtilResponse createResponse(Object data, String responseCode, String responseMessage) {
		
		utilResponse.setData(data);
		utilStatus.setResponseCode(responseCode);
		utilStatus.setResponseMessage(responseMessage);
		utilResponse.setStatus(utilStatus);
		return utilResponse;		
	}
	
	public int getVendorId(HttpServletRequest request) {
		// get email from token
		final String authorizationHeader = request.getHeader("Authorization");
		String jwt = authorizationHeader.substring(7);
		String email = jwtUtil.extractUsername(jwt);
		
		// get id
		Vendor vendor = vendorRepo.findByEmail(email);
		return vendor.getId();
	}
	
	public int getCustomerId(HttpServletRequest request) {
		// get email from token
		final String authorizationHeader = request.getHeader("Authorization");
		String jwt = authorizationHeader.substring(7);
		String email = jwtUtil.extractUsername(jwt);
		
		// get id
		Customer customer = customerRepo.findByEmail(email);
		return customer.getId();
	}
	
	@Value("${gmail.username}")
	private String username;
	@Value("${gmail.password}")
	private String password;
	
	public void sendMail(String receiverEmail, String subject, String message) throws MessagingException {
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(username, false));
		
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
		msg.setSubject(subject);
		msg.setContent(message, "text/html");
		msg.setSentDate(new Date());
		
		// add an attachment
//		MimeBodyPart messageBodyPart = new MimeBodyPart();
//		messageBodyPart.setContent(emailMessage.getBody(), "text/html");
//		Multipart multipart = new MimeMultipart();
//		multipart.addBodyPart(messageBodyPart);
//		MimeBodyPart attachPart = new MimeBodyPart();
//		try {
//			attachPart.attachFile("C:/Users/John/Pictures/java.jpg");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		multipart.addBodyPart(attachPart);
//		msg.setContent(multipart);
		
		// send the email
		try {
			Transport.send(msg);
		} catch(Exception e) {
			System.out.println("Sending email failed: " + e.getMessage());
		}
		
	}
	
//	public void sendEmail(String receiverMail, String subject, String message) {		
//		
//		// create a mail sender
//		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//		mailSender.setHost(this.emailConfig.getHost());
//		mailSender.setPort(this.emailConfig.getPort());
//		mailSender.setUsername(this.emailConfig.getUsername());
//		mailSender.setPassword(this.emailConfig.getPassword());	
//		
//		// create an email instance
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//		
//		mailMessage.setFrom("fva@gmail.com");
//		mailMessage.setTo(receiverMail);
//		mailMessage.setSubject(subject);
//		mailMessage.setText(message);
//		
//		// send email
//		mailSender.send(mailMessage);
//	}

}
