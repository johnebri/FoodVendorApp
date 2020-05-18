package com.johnebri.foodvendorapp.vendor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.johnebri.foodvendorapp.vendor.data.Vendor;
import com.johnebri.foodvendorapp.vendor.data.VendorSignupResponse;
import com.johnebri.foodvendorapp.vendor.repository.VendorRepository;

@Service
public class VendorService {
	
	@Autowired
	private VendorRepository vendorRepository;
	
	VendorSignupResponse vendorSignpuResponse = new VendorSignupResponse();	
	
	public Vendor save(Vendor vendor) {	
		
		Vendor vendorEmailSearchRes = vendorRepository.findByEmail(vendor.getEmail());
		Vendor vendorBusinessSearchRes = vendorRepository.findByBusinessName(vendor.getBusinessName());
		
		if(vendorEmailSearchRes != null) {
			// email is already in use
			return null;
		}
		
		if(vendorBusinessSearchRes != null) {
			// email is already in use
			return null;
		}
		
		return vendorRepository.save(vendor);	
		
	}
	
	public List<Vendor> getVendors() {
		
		return vendorRepository.findAll();
		
	}
	
	public Optional<Vendor> getVendor(int id) {
		
		return vendorRepository.findById(id);
		
	}
	
	public ResponseEntity<Vendor> editVendor(Vendor newVendorDetails, int id) {
		
		
		// find the vendor		
		Optional<Vendor> currentVendor = vendorRepository.findById(id);
		if(!currentVendor.isPresent()) {
			// vendor does not exist
			return ResponseEntity.notFound().build();
		}
		
		newVendorDetails.setId(id);
		Vendor updatedVendor = vendorRepository.save(newVendorDetails);
		
		return ResponseEntity.ok().body(updatedVendor);
	}

}
