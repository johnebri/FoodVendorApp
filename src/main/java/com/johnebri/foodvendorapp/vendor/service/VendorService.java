package com.johnebri.foodvendorapp.vendor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.johnebri.foodvendorapp.customer.data.Customer;
import com.johnebri.foodvendorapp.customer.repository.CustomerRepository;
import com.johnebri.foodvendorapp.menu.data.Menu;
import com.johnebri.foodvendorapp.menu.repository.MenuRepository;
import com.johnebri.foodvendorapp.orders.data.Orders;
import com.johnebri.foodvendorapp.orders.data.ShowOrdersResponse;
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
	private MenuRepository menuRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired 
	UtilService utilSvc;
	
	UtilResponse utilResponse = new UtilResponse();
	UtilStatus utilStatus = new UtilStatus();
	
	public UtilResponse save(Vendor vendor) {	
		
		Vendor vendorEmailSearchRes = vendorRepo.findByEmail(vendor.getEmail());
		Vendor vendorBusinessSearchRes = vendorRepo.findByBusinessName(vendor.getBusinessName());
		
		if(vendor.getEmail().length() < 6 || vendor.getBusinessName().length() < 5 || vendor.getPhoneNumber().length() < 11) {
			return utilSvc.createResponse(
					null, 
					"400", "Error: Fill in the fields correctly"
			);
		}
		
		if(vendorEmailSearchRes != null) {
			// email is already in use
			return utilSvc.createResponse(
					null, 
					"400", "Email is already in use. Please use a different email"
			);
		}
		
		if(vendorBusinessSearchRes != null) {
			// email is already in use
			return utilSvc.createResponse(null, "400", "Business Name is already in use. Please enter a different business name");
		}
		
		Vendor newVendor = vendorRepo.save(vendor);
		
		// send email to vendor
		try {
			String message = "<h1>Welcome</h1> <h3>Welcome to FoodVendorApp. Click (linkToSetupPassword) to set up your password</h3>";
			utilSvc.sendMail(vendor.getEmail(), "Welcome Vendor", message);
		} catch (Exception e) {
			System.out.println("You are not connected to the internet, you will not receive a mail");
		}
		
		return utilSvc.createResponse(newVendor, "200", 
				"Your Vendor registration was successful. Check your email (" +vendor.getEmail()+") for instructions to set up your password");
		
	}
	
	public List<Vendor> getVendors() {
		
		return vendorRepo.findAll();
		
	}
	
	public Vendor getVendor(int id) {
		
		return vendorRepo.findById(id);
		
	}
	
	public ResponseEntity<Vendor> editVendor(Vendor newVendorDetails, int id) {		
		
		// find the vendor		
		Vendor currentVendor = vendorRepo.findById(id);
		if(currentVendor != null) {
			// vendor does not exist
			return ResponseEntity.notFound().build();
		}
		
		newVendorDetails.setId(id);
		Vendor updatedVendor = vendorRepo.save(newVendorDetails);
		
		return ResponseEntity.ok().body(updatedVendor);
	}
	
	public List<ShowOrdersResponse> viewOrders(HttpServletRequest request) {
		int id = utilSvc.getVendorId(request);

		List<Orders> orders = ordersRepo.findByVendorId(id);
		
		List<ShowOrdersResponse> showOrdersResponse = new ArrayList<>();
		
		for(int x=0; x<orders.size(); x++) {
			ShowOrdersResponse resp = new ShowOrdersResponse();
			resp.setOrderId(orders.get(x).getId());
			
			// get customer name
			Customer customer = customerRepo.findById(orders.get(x).getCustomerId());
			resp.setCustomer(customer.getFirstname() + " " + customer.getLastname());
			
			// get menu name
			Menu menu = menuRepo.findById(orders.get(x).getMenuId());
			resp.setMenu(menu.getName());
			resp.setAmountDue(orders.get(x).getAmountDue());
			resp.setAmountPaid(orders.get(x).getAmountPaid());
			resp.setAmountOutstanding(orders.get(x).getAmountOutstanding());
			resp.setOrderStatus(orders.get(x).getOrderStatus());
			resp.setDateNeeded(orders.get(x).getDateNeeded().toString());
			resp.setDateAndTimeOfOrder(orders.get(x).getDataAndTimeOfOrder().toString());
			
			showOrdersResponse.add(resp);
		}
		
		return showOrdersResponse;
		
	}

}
