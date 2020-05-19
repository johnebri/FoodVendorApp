package com.johnebri.foodvendorapp.vendor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.johnebri.foodvendorapp.util.data.UtilResponse;
import com.johnebri.foodvendorapp.util.data.UtilStatus;
import com.johnebri.foodvendorapp.util.service.UtilService;
import com.johnebri.foodvendorapp.vendor.data.Vendor;
import com.johnebri.foodvendorapp.vendor.repository.VendorRepository;

@Service
public class VendorService {
	
	@Autowired
	private VendorRepository vendorRepository;
	
	@Autowired 
	UtilService utilService;
	
	UtilResponse utilResponse = new UtilResponse();
	UtilStatus utilStatus = new UtilStatus();
	
	public UtilResponse save(Vendor vendor) {	
		
		Vendor vendorEmailSearchRes = vendorRepository.findByEmail(vendor.getEmail());
		Vendor vendorBusinessSearchRes = vendorRepository.findByBusinessName(vendor.getBusinessName());
		
		if(vendorEmailSearchRes != null) {
			// email is already in use
			return utilService.createResponse(
					null, 
					"400", "A user already exist with that business name"
			);
		}
		
		if(vendorBusinessSearchRes != null) {
			// email is already in use
			return utilService.createResponse(null, "400", " A user already exist with this email");
		}
		
		Vendor newVendor = vendorRepository.save(vendor);
		return utilService.createResponse(newVendor, "200", "success");
		
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
