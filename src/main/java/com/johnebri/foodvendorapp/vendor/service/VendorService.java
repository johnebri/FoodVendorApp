package com.johnebri.foodvendorapp.vendor.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.johnebri.foodvendorapp.orders.data.Orders;
import com.johnebri.foodvendorapp.orders.repository.OrdersRepository;
import com.johnebri.foodvendorapp.util.data.UtilResponse;
import com.johnebri.foodvendorapp.util.data.UtilStatus;
import com.johnebri.foodvendorapp.util.service.UtilService;
import com.johnebri.foodvendorapp.vendor.data.Vendor;
import com.johnebri.foodvendorapp.vendor.repository.VendorRepository;

@Service
public class VendorService {
	
	@Autowired
	private VendorRepository vendorRepo;
	
	@Autowired
	private OrdersRepository ordersRepo;
	
	@Autowired 
	UtilService utilSvc;
	
	UtilResponse utilResponse = new UtilResponse();
	UtilStatus utilStatus = new UtilStatus();
	
	public UtilResponse save(Vendor vendor) {	
		
		Vendor vendorEmailSearchRes = vendorRepo.findByEmail(vendor.getEmail());
		Vendor vendorBusinessSearchRes = vendorRepo.findByBusinessName(vendor.getBusinessName());
		
		if(vendorEmailSearchRes != null) {
			// email is already in use
			return utilSvc.createResponse(
					null, 
					"400", "Email is name is already in use. Please use a different email"
			);
		}
		
		if(vendorBusinessSearchRes != null) {
			// email is already in use
			return utilSvc.createResponse(null, "400", "Business Name is already in use. Please enter a different business name");
		}
		
		Vendor newVendor = vendorRepo.save(vendor);
		
		// send email to vendor
		try {
			utilSvc.sendEmail(vendor.getEmail(), "Welcome Vendor", "Welcome to FoodVendorApp. Click Here to set up your password");
		} catch (Exception e) {
			System.out.println("You are not connected to the internet, you will not receive a mail");
		}
		
		return utilSvc.createResponse(newVendor, "200", 
				"Your Vendor registration was successful. Check your email (" +vendor.getEmail()+") for instructions to set up your password");
		
	}
	
	public List<Vendor> getVendors() {
		
		return vendorRepo.findAll();
		
	}
	
	public Optional<Vendor> getVendor(int id) {
		
		return vendorRepo.findById(id);
		
	}
	
	public ResponseEntity<Vendor> editVendor(Vendor newVendorDetails, int id) {		
		
		// find the vendor		
		Optional<Vendor> currentVendor = vendorRepo.findById(id);
		if(!currentVendor.isPresent()) {
			// vendor does not exist
			return ResponseEntity.notFound().build();
		}
		
		newVendorDetails.setId(id);
		Vendor updatedVendor = vendorRepo.save(newVendorDetails);
		
		return ResponseEntity.ok().body(updatedVendor);
	}
	
	public List<Orders> viewOrders(HttpServletRequest request) {
		int id = utilSvc.getVendorId(request);
		return ordersRepo.findByVendorId(id);
		
	}

}
