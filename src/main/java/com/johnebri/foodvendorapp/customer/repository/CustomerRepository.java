package com.johnebri.foodvendorapp.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnebri.foodvendorapp.customer.data.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Customer findByEmail(String email);
	Customer findById(int id);
	List<Customer> findAllById(int id);
	Customer findByPhoneNumber(String phoneNumber);
}
