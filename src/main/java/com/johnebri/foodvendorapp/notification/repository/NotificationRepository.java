package com.johnebri.foodvendorapp.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.johnebri.foodvendorapp.notification.data.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
	
	@Query(value = "SELECT * FROM notification n WHERE n.customer_id = ?1", nativeQuery = true)	
	List<Notification> getCustomerNotifications(int cusomterId); 

}
