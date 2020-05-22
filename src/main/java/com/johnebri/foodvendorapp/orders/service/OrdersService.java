package com.johnebri.foodvendorapp.orders.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.johnebri.foodvendorapp.menu.data.Menu;
import com.johnebri.foodvendorapp.menu.repository.MenuRepository;
import com.johnebri.foodvendorapp.orders.data.Orders;
import com.johnebri.foodvendorapp.orders.repository.OrdersRepository;
import com.johnebri.foodvendorapp.util.data.UtilResponse;
import com.johnebri.foodvendorapp.util.service.UtilService;
import com.johnebri.foodvendorapp.vendor.data.Vendor;
import com.johnebri.foodvendorapp.vendor.repository.VendorRepository;

@Service
public class OrdersService {
	
	UtilResponse utilResponse = new UtilResponse();
	
	@Autowired
	private MenuRepository menuRepo;
	
	@Autowired
	private OrdersRepository ordersRepo;
	
	@Autowired
	private VendorRepository vendorRepo;
	
	@Autowired
	private UtilService utilSvc;
	
	
	public List<Menu> getAllMenu() {
		return menuRepo.findAll();
	}
	
	public UtilResponse makeOrder(HttpServletRequest request, Orders order) throws ParseException {
		
		// get customer id
		int id = utilSvc.getCustomerId(request);
		order.setCustomerId(id);
		
		// check if vendor exist
		int vendorId = order.getVendorId();
		Optional<Vendor> vendor = vendorRepo.findById(vendorId);
		if(!vendor.isPresent()) {
			// vendor does not exist
			return utilSvc.createResponse(null, "400",
				"Vendor does not exist"
			);					
		}
		
		// check if menu exist
		int menuId = order.getMenuId();
		Menu menu = menuRepo.findById(menuId);
		if(menu == null) {
			// menu does not exist
			return utilSvc.createResponse(null, "400",
					"Menu does not exist"
			);				
		}		
		
		// check if menu belongs to vendor
		int vendorIdFromMenu = menu.getVendorId();
		if(vendorIdFromMenu != vendorId) {
			// menu does not belong to vendor
			return utilSvc.createResponse(null, "400",
					"Menu does belong to vendor you have specified"
			);	
		}
		
		// validate order date
		LocalDateTime now = LocalDateTime.now();
		
		// Create SimpleDateFormat object 
        SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd");
        
        String dateNeeded1 = order.getDateNeeded().toString();
        String todaysDate1 = now.toString();
		
		// Get the two dates to be compared  
        Date dateNeeded = sdfo.parse(dateNeeded1); 
        Date todaysDate = sdfo.parse(todaysDate1);
        
        if (dateNeeded.after(todaysDate)) { 
            // When Date d1 > Date d2 
            System.out.println("this is a preorder"); 
        } 
        
        if (todaysDate.after(dateNeeded)) { 
            // order date cannot be in the past
        	return utilSvc.createResponse(null, "400",
    				"Order date cannot be in the past");
        } 
        
        if (todaysDate.equals(dateNeeded)) { 
            // order is on the same date, you can check time difference here
            System.out.println("Same Date"); 
        } 
		
		double menuPrice = menu.getPrice();
		order.setAmountDue(menuPrice);		
		
		ordersRepo.save(order);	
		
		return utilSvc.createResponse(order, "200",
				"Order sent successfully");
	}

	public List<Orders> getMyOrders(HttpServletRequest request) {
		int id = utilSvc.getCustomerId(request);
		return ordersRepo.findByCustomerId(id);
	}
	
	public UtilResponse getOrder(HttpServletRequest request, int orderId) {
		int id = utilSvc.getCustomerId(request);
		Orders order = ordersRepo.findById(orderId);
		
		if(order == null) {
			return utilSvc.createResponse(null, "400",
					"No Order found");
		}
		
		// check if order belongs to customer making request
		if(id != order.getCustomerId()) {
			return utilSvc.createResponse(null, "400",
					"Order does not belong to customer");
		}
		
		return utilSvc.createResponse(order, "200",
				"success");
		
	}
	
	@Transactional
	public UtilResponse cancelOrder(HttpServletRequest request, int orderId) {
		
		// check if order exist 
		int id = utilSvc.getCustomerId(request);
		Orders order = ordersRepo.findById(orderId);
		
//		if(order == null) {
//			return utilSvc.createResponse(null, "400",
//					"No Order found");
//		}
		
		// check if order belongs to customer making request
//		if(id != order.getCustomerId()) {
//			return utilSvc.createResponse(null, "400",
//					"Order does not belong to customer");
//		}
		
		// check if time after order is more than 10 minutes
		
		SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Date today = new Date();
        String todaysDate = now.toString();
        
        String str = "abcdDCBA123";
        String newDate = todaysDate.replace("T", " "); // strNew is 'bcdDCBA123'
        
        String orderDate = order.getDataAndTimeOfOrder().toString();
        
        System.out.println("Today : " + newDate);
        System.out.println("Order : " + orderDate);
        
        //HH converts hour in 24 hours format (0-23), day calculation
		
	
		Date d1 = null;
		Date d2 = null;
	
		try {
			d1 = format.parse(orderDate);
			d2 = format.parse(newDate);
	
			//in milliseconds
			long diff = d2.getTime() - d1.getTime();
	
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
			
			
	
			System.out.println(diffDays + " days, ");
			System.out.println(diffHours + " hours, ");
			System.out.println(diffMinutes + " minutes, ");
			System.out.println(diffSeconds + " seconds.");
			System.out.println("diff " + diff);
	
		} catch (Exception e) {
			System.out.println(e.getMessage());;
		}
		
        	
		
		ordersRepo.setOrdersInfoById("cancelled", orderId);
		
		
		return utilSvc.createResponse(null, "200",
				"Order Cancelled Successfully");
		
	}
	
	@Transactional
	public UtilResponse payForOrder(HttpServletRequest request, int orderId) {
		
		// check if order exist 
		int id = utilSvc.getCustomerId(request);
		Orders order = ordersRepo.findById(orderId);
		
		if(order == null) {
			return utilSvc.createResponse(null, "400",
					"No Order found");
		}
		
		// check if order belongs to customer making request
		if(id != order.getCustomerId()) {
			return utilSvc.createResponse(null, "400",
					"Order does not belong to customer");
		}
		
		// check status or order	
		String orderStatus = order.getOrderStatus();
		
		if(orderStatus.equals("cancelled")) {
			return utilSvc.createResponse(null, "200",
					"You cannot pay for an order you have cancelled");
		}
		
		if(orderStatus.equals("paid")) {
			return utilSvc.createResponse(null, "200",
					"You have already paid for this order");
		}		
		
		ordersRepo.payForOrder("paid", orderId);		
		
		return utilSvc.createResponse(null, "200",
				"Order Paid Successfully");		
	}
	
	@Transactional
	public UtilResponse updateOrderStatus(HttpServletRequest request, int orderId) {
		
		// check if order exist 
		int id = utilSvc.getVendorId(request);
		Orders order = ordersRepo.findById(orderId);
		
		if(order == null) {
			return utilSvc.createResponse(null, "400",
					"No Order found");
		}
		
		// check if order belongs to vendor making request
		if(id != order.getVendorId()) {
			return utilSvc.createResponse(null, "400",
					"Order does not belong to vendor");
		}
		
		// check status or order	
		String orderStatus = order.getOrderStatus();
		
		if(orderStatus.equals("cancelled")) {
			return utilSvc.createResponse(null, "200",
					"Order is already cancelled");
		}
		
//		if(orderStatus.equals("paid")) {
//			return utilSvc.createResponse(null, "200",
//					"You have already paid for this order");
//		}	
		
		ordersRepo.vendorUpdateOrderStatus("delivered", orderId);
		return utilSvc.createResponse(null, "200",
				"Order updated successfully");
		
	}
	
	public List<Orders> dailySalesReport(HttpServletRequest request) throws ParseException {
		
		// get vendor id
		int id = utilSvc.getVendorId(request);
		
		// get todays date
		LocalDateTime now = LocalDateTime.now();
		
		SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd");
        String todaysDate = now.toString();
        Date orderDate = sdfo.parse(todaysDate);
  
		System.out.println(now);
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		Date todayWithZeroTime = formatter.parse(formatter.format(today));
		
		System.out.println("Date without time : " + todayWithZeroTime);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(dateFormat.format(new Date()));
		
		return ordersRepo.getVendorOrders(id, dateFormat.format(new Date()));
	}
}
