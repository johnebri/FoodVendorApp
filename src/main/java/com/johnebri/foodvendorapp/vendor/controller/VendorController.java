package com.johnebri.foodvendorapp.vendor.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.johnebri.foodvendorapp.EmailMessage;
import com.johnebri.foodvendorapp.menu.data.Menu;
import com.johnebri.foodvendorapp.menu.service.MenuService;
import com.johnebri.foodvendorapp.notification.data.Notification;
import com.johnebri.foodvendorapp.notification.service.NotificationService;
import com.johnebri.foodvendorapp.orders.data.Orders;
import com.johnebri.foodvendorapp.orders.service.OrdersService;
import com.johnebri.foodvendorapp.util.data.EmailRequest;
import com.johnebri.foodvendorapp.util.data.UtilResponse;
import com.johnebri.foodvendorapp.util.service.EmailConfig;
import com.johnebri.foodvendorapp.vendor.data.Vendor;
import com.johnebri.foodvendorapp.vendor.service.VendorService;

@RestController
public class VendorController {
	
	@Value("${gmail.username}")
	private String username;
	@Value("${gmail.password}")
	private String password;
	
	private EmailConfig emailConfig;
	
	public VendorController(EmailConfig emailConfig) {
		this.emailConfig = emailConfig;
	}
	
	@Autowired
	private VendorService vendorSvc;
	
	@Autowired
	private MenuService menuSvc;
	
	@Autowired
	private OrdersService ordersSvc;
	
	@Autowired
	private NotificationService notificationSvc;
	
	
	@GetMapping("/")
	public String hello() {
		return "Now you have started";
	}
	
	// vendor registration
	@PostMapping("/vendors")
	public UtilResponse signup(@RequestBody Vendor vendor) {
		return vendorSvc.save(vendor);		
	}
	
	// all vendors
	@GetMapping("/vendors")
	public List<Vendor> getVendors() {
		return vendorSvc.getVendors();
	}	
	
	// get a vendor
	@GetMapping("/vendors/{vendorId}")
	public Optional<Vendor> getVendor(@PathVariable(value="vendorId") int id, HttpServletRequest request) {
		return vendorSvc.getVendor(id);
	}	
	
	// edit vendor information
	@PutMapping("/vendors/{vendorId}")
	public ResponseEntity<Vendor> editVendor(@PathVariable(value="vendorId") int id, @RequestBody Vendor vendor) {
		return vendorSvc.editVendor(vendor, id);
	}
	
	@PostMapping("/vendors/menu")
	public UtilResponse createMenu(@RequestBody Menu menu, HttpServletRequest request) {
		return menuSvc.createMenu(menu, request);
	}
	
	@GetMapping("/vendors/menu")
	public List<Menu> vendorMenu(HttpServletRequest request) {
		return menuSvc.getVendorMenu(request);
	}
	
	@PutMapping("/vendors/menu")
	public UtilResponse updateMenu(@RequestBody Menu menu, HttpServletRequest request) {
		return menuSvc.updateVendorMenu(menu, request);
	}	
	
	@GetMapping("/vendors/orders")
	public List<Orders> viewOrders(HttpServletRequest request) {
		return vendorSvc.viewOrders(request);		
	}
	
	@PutMapping("/vendors/orders/{orderId}/update")
	public UtilResponse updateOrderStatus(HttpServletRequest request, @PathVariable(value="orderId") int id) {
		return ordersSvc.updateOrderStatus(request, id);
	}
	
	@GetMapping("/vendors/report")
	public List<Orders> dailySalesReport(HttpServletRequest request) throws ParseException {
		return ordersSvc.dailySalesReport(request);
	}
	
	@PostMapping("/vendors/notification/{orderId}")
	public UtilResponse sendNotification(
			HttpServletRequest request, 
			@RequestBody Notification notification, 
			@PathVariable(value="orderId") int orderId ) {
		return notificationSvc.sendNotification(request, notification, orderId);
	}
	
	@PostMapping("/sendmail")
	public void sendMail(@RequestBody EmailRequest emailReq) {
		// create a mail sender
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(this.emailConfig.getHost());
		mailSender.setPort(this.emailConfig.getPort());
		mailSender.setUsername(this.emailConfig.getUsername());
		mailSender.setPassword(this.emailConfig.getPassword());
	
		// create an email instance
		SimpleMailMessage mailMessage = new SimpleMailMessage();
	
		mailMessage.setFrom(emailReq.getSenderEmail());
		mailMessage.setTo("john.ebri@yahoo.com");
		mailMessage.setSubject("New Feedback from " + emailReq.getSenderName());
		mailMessage.setText(emailReq.getMessage());
		
		// send mail
		mailSender.send(mailMessage);
	}
	
	@PostMapping("/send")
	public String sendEmail(@RequestBody EmailMessage emailMessage) throws MessagingException {
		sendMail(emailMessage);
		return "Email sent";
	}
	
	private void sendMail(EmailMessage emailMessage) throws MessagingException {
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
		
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailMessage.getTo_address()));
		msg.setSubject(emailMessage.getSubject());
		msg.setContent(emailMessage.getBody(), "text/html");
		msg.setSentDate(new Date());
		
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(emailMessage.getBody(), "text/html");
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		MimeBodyPart attachPart = new MimeBodyPart();
		
		try {
			attachPart.attachFile("C:/Users/John/Pictures/java.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		multipart.addBodyPart(attachPart);
		msg.setContent(multipart);
		// send the email
		Transport.send(msg);
	}
		

}
