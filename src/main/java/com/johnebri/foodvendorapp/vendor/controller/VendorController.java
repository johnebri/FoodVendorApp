package com.johnebri.foodvendorapp.vendor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnebri.foodvendorapp.vendor.data.Vendor;
import com.johnebri.foodvendorapp.vendor.service.VendorService;

@RestController
@RequestMapping("/api/v1")
public class VendorController {
	
	@Autowired
	private VendorService vendorSvc;
	
	@GetMapping("/")
	public String hello() {
		return "Now you have started";
	}
	
	// vendor registration
	@PostMapping("/vendors")
	public Vendor signup(@RequestBody Vendor vendor) {
		return vendorSvc.save(vendor);		
	}
	
	// all vendors
	@GetMapping("/vendors")
	public List<Vendor> getVendors() {
		return vendorSvc.getVendors();
	}	
	
	// get a vendor
	@GetMapping("/vendors/{vendorId}")
	public Optional<Vendor> getVendor(@PathVariable(value="vendorId") int id) {
		return vendorSvc.getVendor(id);
	}
	
	
	// edit vendor information
	@PutMapping("/vendors/{vendorId}")
	public ResponseEntity<Vendor> editVendor(@PathVariable(value="vendorId") int id, @RequestBody Vendor vendor) {
		return vendorSvc.editVendor(vendor, id);
	}	

}
