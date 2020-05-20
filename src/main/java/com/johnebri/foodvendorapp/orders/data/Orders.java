package com.johnebri.foodvendorapp.orders.data;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int customerId;
	private int vendorId;
	private int menuId;
	private String description;
	private double amountDue;
	private double amountPaid;
	private double amountOutstanding;
	private String orderStatus;
	private Date dateNeeded;
	@CreationTimestamp
	private Timestamp dataAndTimeOfOrder;
	
	public Orders() {}

	public Orders(int id, int customerId, int vendorId, int menuId, String description, double amountDue,
			double amountPaid, double amountOutstanding, String orderStatus, Date dateNeeded,
			Timestamp dataAndTimeOfOrder) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.vendorId = vendorId;
		this.menuId = menuId;
		this.description = description;
		this.amountDue = amountDue;
		this.amountPaid = amountPaid;
		this.amountOutstanding = amountOutstanding;
		this.orderStatus = orderStatus;
		this.dateNeeded = dateNeeded;
		this.dataAndTimeOfOrder = dataAndTimeOfOrder;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public double getAmountOutstanding() {
		return amountOutstanding;
	}

	public void setAmountOutstanding(double amountOutstanding) {
		this.amountOutstanding = amountOutstanding;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getDateNeeded() {
		return dateNeeded;
	}

	public void setDateNeeded(Date dateNeeded) {
		this.dateNeeded = dateNeeded;
	}

	public Timestamp getDataAndTimeOfOrder() {
		return dataAndTimeOfOrder;
	}

	public void setDataAndTimeOfOrder(Timestamp dataAndTimeOfOrder) {
		this.dataAndTimeOfOrder = dataAndTimeOfOrder;
	}
	
	

}
