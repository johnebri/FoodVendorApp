package com.johnebri.foodvendorapp.notification.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnebri.foodvendorapp.notification.data.Notification;
import com.johnebri.foodvendorapp.notification.repository.NotificationRepository;
import com.johnebri.foodvendorapp.orders.data.Orders;
import com.johnebri.foodvendorapp.orders.repository.OrdersRepository;
import com.johnebri.foodvendorapp.util.data.UtilResponse;
import com.johnebri.foodvendorapp.util.service.UtilService;

@Service
public class NotificationService {
	
	@Autowired
	UtilService utilSvc;
	
	@Autowired
	OrdersRepository ordersRepo;
	
	@Autowired
	NotificationRepository notificationRepo;
	
	public UtilResponse sendNotification(
			HttpServletRequest request, 
			Notification notification, 
			int orderId) {
		
		// get the vendor id
		int vendorId = utilSvc.getVendorId(request);
		
		// check if order exist
		Orders order = ordersRepo.findById(orderId);
		if(order == null) {
			return utilSvc.createResponse(null, "400", "Order does not exist");
		}
		System.out.println(order.getId());
		
		// check order belongs to vendor
		if (vendorId != order.getVendorId()) {
			return utilSvc.createResponse(null, "400", "You can not send a notification for an order that is not yours");
		}	
		
		// send notification
		notification.setOrderId(orderId);
		notification.setCustomerId(order.getCustomerId());
		Notification savedNotification = notificationRepo.save(notification);
		
		return utilSvc.createResponse(savedNotification, "200", "Notification sent successfully");
		
	}
	
	public List<Notification> getNotifications(HttpServletRequest request) {
		int customerId = utilSvc.getCustomerId(request);
		return notificationRepo.getCustomerNotifications(customerId);
	}

}
