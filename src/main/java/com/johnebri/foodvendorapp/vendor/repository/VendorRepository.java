package com.johnebri.foodvendorapp.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.johnebri.foodvendorapp.vendor.data.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
	
	Vendor findByEmail(String email);
	Vendor findByBusinessName(String businessName);
	
//	@Modifying
//	@Query("UPDATE Vendor set phoneNumber = ?1, businessName = ?2 WHERE id = ?3")
//	void editVendor(String phoneNumber, String businessName, Integer vendorId);
	

}
