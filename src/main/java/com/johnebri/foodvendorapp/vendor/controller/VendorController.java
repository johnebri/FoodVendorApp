package com.johnebri.foodvendorapp.vendor.controller;

import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.johnebri.foodvendorapp.menu.data.Menu;
import com.johnebri.foodvendorapp.menu.service.MenuService;
import com.johnebri.foodvendorapp.notification.data.Notification;
import com.johnebri.foodvendorapp.notification.service.NotificationService;
import com.johnebri.foodvendorapp.orders.data.ShowOrdersResponse;
import com.johnebri.foodvendorapp.orders.data.VendorReportResponse;
import com.johnebri.foodvendorapp.orders.service.OrdersService;
import com.johnebri.foodvendorapp.util.data.UtilResponse;
import com.johnebri.foodvendorapp.vendor.data.Vendor;
import com.johnebri.foodvendorapp.vendor.service.VendorService;

@RestController
public class VendorController {
	
	@Autowired
	private VendorService vendorSvc;
	
	@Autowired
	private MenuService menuSvc;
	
	@Autowired
	private OrdersService ordersSvc;
	
	@Autowired
	private NotificationService notificationSvc;
	
	
	@GetMapping("/")
	public String hello() {
		return (
				"<h1 style=text-align:center;>Welcome to FoodVendorApp RESTApi</h1>"
				+ "<center>"
				+ "<a href='https://documenter.getpostman.com/view/9082520/SztD477f?version=latest'"
				+ "<button style=background:#6CA76E;color:white;padding:12px;text-decoration:none;>Go to Documentation</button></a></center>"	
		);
	}
	
	// vendor registration
	@PostMapping("/vendors")
	public UtilResponse signup(@RequestBody Vendor vendor) {
		return vendorSvc.save(vendor);		
	}
	
	// all vendors
	@GetMapping("/vendors")
	public List<Vendor> getVendors() {
		return vendorSvc.getVendors();
	}	
	
	// get a vendor
	@GetMapping("/vendors/{vendorId}")
	public Vendor getVendor(@PathVariable(value="vendorId") int id, HttpServletRequest request) {
		return vendorSvc.getVendor(id);
	}	
	
	// edit vendor information
	@PutMapping("/vendors/{vendorId}")
	public ResponseEntity<Vendor> editVendor(@PathVariable(value="vendorId") int id, @RequestBody Vendor vendor) {
		return vendorSvc.editVendor(vendor, id);
	}
	
	@PostMapping("/vendors/menu")
	public UtilResponse createMenu(@RequestBody Menu menu, HttpServletRequest request) {
		return menuSvc.createMenu(menu, request);
	}
	
	@GetMapping("/vendors/menu")
	public List<Menu> vendorMenu(HttpServletRequest request) {
		return menuSvc.getVendorMenu(request);
	}
	
	@PutMapping("/vendors/menu")
	public UtilResponse updateMenu(@RequestBody Menu menu, HttpServletRequest request) {
		return menuSvc.updateVendorMenu(menu, request);
	}	
	
	@GetMapping("/vendors/orders")
	public List<ShowOrdersResponse> viewOrders(HttpServletRequest request) {
		return vendorSvc.viewOrders(request);		
	}
	
	@PutMapping("/vendors/orders/{orderId}/update")
	public UtilResponse updateOrderStatus(HttpServletRequest request, @PathVariable(value="orderId") int id) {
		return ordersSvc.updateOrderStatus(request, id);
	}
	
	@GetMapping("/vendors/report")
	public List<VendorReportResponse> dailySalesReport(HttpServletRequest request) throws ParseException {
		return ordersSvc.dailySalesReport(request);
	}
	
	@PostMapping("/vendors/notification/{orderId}")
	public UtilResponse sendNotification(
			HttpServletRequest request, 
			@RequestBody Notification notification, 
			@PathVariable(value="orderId") int orderId ) {
		return notificationSvc.sendNotification(request, notification, orderId);
	}
		

}
