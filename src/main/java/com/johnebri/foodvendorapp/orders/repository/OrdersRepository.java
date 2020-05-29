package com.johnebri.foodvendorapp.orders.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.johnebri.foodvendorapp.orders.data.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{
	
	List<Orders> findByCustomerId(int customerId);
	List<Orders> findByVendorId(int vendorId);
	Orders findById(int id);
	
	@Modifying
	@Query("UPDATE Orders set order_status = ?1 WHERE id = ?2")
	void setOrdersInfoById(String status, int id);
	
	@Modifying
	@Query("UPDATE Orders set order_status = ?1, amountOutstanding = 0 WHERE id = ?2")
	void payForOrder(String status, int id);
	
	@Modifying
	@Query("UPDATE Orders set order_status = ?1 WHERE id = ?2")
	void vendorUpdateOrderStatus(String status, int id);
	
	// @Query(value = "SELECT * FROM Orders WHERE vendor_id = ?1 AND date_needed = ?2")	
	// List<Orders> getVendorOrders(int vendorId, String dateNeeded); 
	// List<Orders> findByVendorId(int vendoroId);

}
