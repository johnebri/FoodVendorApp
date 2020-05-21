package com.johnebri.foodvendorapp.customer.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.johnebri.foodvendorapp.customer.data.Customer;
import com.johnebri.foodvendorapp.customer.service.CustomerService;
import com.johnebri.foodvendorapp.menu.data.Menu;
import com.johnebri.foodvendorapp.notification.data.Notification;
import com.johnebri.foodvendorapp.notification.service.NotificationService;
import com.johnebri.foodvendorapp.orders.data.Orders;
import com.johnebri.foodvendorapp.orders.service.OrdersService;
import com.johnebri.foodvendorapp.util.data.UtilResponse;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerSvc;
	
	@Autowired
	OrdersService ordersSvc;
	
	@Autowired
	NotificationService notificationSvc;
	
	
	
	@PostMapping("/customers")
	public UtilResponse customerSignup(@RequestBody Customer customer) {
		return customerSvc.save(customer);
	}
	
	@GetMapping("/customers")
	public List<Customer> allCustomers() {
		return customerSvc.getAllCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	public Optional<Customer> getCustomer(@PathVariable(value="customerId") int id) {
		return customerSvc.getCustomer(id);
	}
	
	@GetMapping("/customers/menu")
	public List<Menu> test() {
		return ordersSvc.getAllMenu();
	}
	
	@PostMapping("/customers/makeorder")
	public UtilResponse makeOrder(HttpServletRequest request, @RequestBody Orders order) throws ParseException {
		return ordersSvc.makeOrder(request, order);		
	}
	
	@GetMapping("/customers/orders")
	public List<Orders> getMyOrders(HttpServletRequest request) {
		return ordersSvc.getMyOrders(request);
	}
	
	@GetMapping("/customers/orders/{orderId}")
	public UtilResponse getOrder(HttpServletRequest request, @PathVariable(value="orderId") int id) {
		return ordersSvc.getOrder(request, id);
	}
	
	@PutMapping("/customers/orders/{orderId}/cancel")
	public UtilResponse cancelOrder(HttpServletRequest request, @PathVariable(value="orderId") int id) {
		return ordersSvc.cancelOrder(request, id);
	}
	
	@PutMapping("/customers/orders/{orderId}/pay")
	public UtilResponse payForOrder(HttpServletRequest request, @PathVariable(value="orderId") int id) {
		return ordersSvc.payForOrder(request, id);
	}
	
	@GetMapping("/customers/notifications")
	public List<Notification> getMyNotifications(HttpServletRequest request) {
		return notificationSvc.getNotifications(request);
	}
	
	
	

}
