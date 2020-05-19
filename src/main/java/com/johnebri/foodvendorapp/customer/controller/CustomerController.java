package com.johnebri.foodvendorapp.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.johnebri.foodvendorapp.customer.data.Customer;
import com.johnebri.foodvendorapp.customer.service.CustomerService;
import com.johnebri.foodvendorapp.util.data.UtilResponse;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerSvc;
	
	@PostMapping("/customers")
	public UtilResponse customerSignup(@RequestBody Customer customer) {
		return customerSvc.save(customer);
	}
	
	@GetMapping("/customers")
	public List<Customer> allCustomers() {
		return customerSvc.getAllCustomers();
	}
	

}
