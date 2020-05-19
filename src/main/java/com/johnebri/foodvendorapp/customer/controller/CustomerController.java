package com.johnebri.foodvendorapp.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnebri.foodvendorapp.customer.data.Customer;
import com.johnebri.foodvendorapp.customer.service.CustomerService;
import com.johnebri.foodvendorapp.util.data.UtilResponse;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
	
	@Autowired
	CustomerService customerSvc;
	
	@PostMapping("/customers")
	public UtilResponse customerSignup(@RequestBody Customer customer) {
		return customerSvc.save(customer);
	}
	

}
