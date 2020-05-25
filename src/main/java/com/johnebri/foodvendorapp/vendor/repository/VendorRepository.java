package com.johnebri.foodvendorapp.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.johnebri.foodvendorapp.vendor.data.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
	
	Vendor findByEmail(String email);
	Vendor findByBusinessName(String businessName);
	boolean existsByEmail(String email);
	Vendor findById(int id);
	

}
