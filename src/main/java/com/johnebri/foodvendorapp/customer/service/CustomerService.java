package com.johnebri.foodvendorapp.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnebri.foodvendorapp.customer.data.Customer;
import com.johnebri.foodvendorapp.customer.repository.CustomerRepository;
import com.johnebri.foodvendorapp.util.data.UtilResponse;
import com.johnebri.foodvendorapp.util.data.UtilStatus;
import com.johnebri.foodvendorapp.util.service.UtilService;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired 
	UtilService utilService;
	
	UtilResponse utilResponse = new UtilResponse();
	UtilStatus utilStatus = new UtilStatus();
	
	public UtilResponse save(Customer newCustomer) {
		
		// check if email already exists
		if( customerRepo.findByEmail(newCustomer.getEmail()) != null ) {
			// customer already exist with this email
			return utilService.createResponse(null, "400", "A customer with the email you provided already exist");
		}
		
		// check if phone number already exist
		if( customerRepo.findByPhoneNumber(newCustomer.getPhoneNumber()) != null ) {
			// customer already exist with this phone number
			return utilService.createResponse(null, "400", "A customer with the phone number you provided already exist");
		}
		
		Customer customer = customerRepo.save(newCustomer);
		return utilService.createResponse(customer, "200", "Registration was successful. You can now set your password");
	}

}
