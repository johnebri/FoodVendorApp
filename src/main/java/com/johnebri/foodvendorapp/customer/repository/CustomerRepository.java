package com.johnebri.foodvendorapp.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnebri.foodvendorapp.customer.data.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Customer findByEmail(String email);
	Customer findByPhoneNumber(String phoneNumber);
}
