package com.johnebri.foodvendorapp.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnebri.foodvendorapp.customer.data.Customer;
import com.johnebri.foodvendorapp.customer.repository.CustomerRepository;
import com.johnebri.foodvendorapp.util.data.UtilResponse;
import com.johnebri.foodvendorapp.util.data.UtilStatus;
import com.johnebri.foodvendorapp.util.service.UtilService;
import com.johnebri.foodvendorapp.vendor.repository.VendorRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private VendorRepository vendorRepo;
	
	@Autowired 
	UtilService utilService;
	
	UtilResponse utilResponse = new UtilResponse();
	UtilStatus utilStatus = new UtilStatus();
	
	public UtilResponse save(Customer newCustomer) {
		
		// check if fields are empty
		if(newCustomer.getFirstname().length()<2 || newCustomer.getLastname().length() < 2 || 
				newCustomer.getEmail().length() < 6 || newCustomer.getPhoneNumber().length() < 11) {
			return utilService.createResponse(null, "400", "Error: Fill in the fields correctly");
		}
		
		// check if email already exists
		if( customerRepo.findByEmail(newCustomer.getEmail()) != null || vendorRepo.existsByEmail(newCustomer.getEmail()) ) {
			// customer already exist with this email
			return utilService.createResponse(null, "400", "The email is already in use");
		}
		
		// check if phone number already exist
		if( customerRepo.findByPhoneNumber(newCustomer.getPhoneNumber()) != null ) {
			// customer already exist with this phone number
			return utilService.createResponse(null, "400", 
					"Phone number is already in use");
		}
		
		// send email to customer
		try {
			String message = "<h1>Welcome</h1> <h3>Welcome to FoodVendorApp. Click Here to set up your password</h3>";
			utilService.sendMail(newCustomer.getEmail(), "Welcome Customer", message);
		} catch (Exception e) {
			System.out.println("You are not connected to the internet, you will not receive a mail");
		}
		
		// everything is fine, save customer
		Customer customer = customerRepo.save(newCustomer);
		return utilService.createResponse(customer, "200", 
				"Registration was successful. An email sent to (" + newCustomer.getEmail() + ") contains instructions to set your password");
	}
	
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}
	
	public List<Customer> getCustomer(int id) {
		return customerRepo.findAllById(id);
	}
	

}
