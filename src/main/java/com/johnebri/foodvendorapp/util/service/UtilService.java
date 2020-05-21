package com.johnebri.foodvendorapp.util.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

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
	
	private EmailConfig emailConfig;
	
	public UtilService(EmailConfig emailConfig) {
		this.emailConfig = emailConfig;
	}
	
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
	
	public void sendEmail(String receiverMail, String subject, String message) {		
		
		// create a mail sender
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(this.emailConfig.getHost());
		mailSender.setPort(this.emailConfig.getPort());
		mailSender.setUsername(this.emailConfig.getUsername());
		mailSender.setPassword(this.emailConfig.getPassword());	
		
		// create an email instance
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setFrom("fva@gmail.com");
		mailMessage.setTo(receiverMail);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		
		// send email
		mailSender.send(mailMessage);
	}

}
