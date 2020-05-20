package com.johnebri.foodvendorapp.orders.data;

public class CustomerOrdersResponse {
	
	private int orderId;
	private String vendorBusinessName;
	private double amountdue;
	private double amountPaid;
	private double amountOutstanding;
	private String orderStatus;
	private String DateNeeded;
	private String DateAndTimeOfOrder;
	
	public CustomerOrdersResponse() {}

	public CustomerOrdersResponse(int orderId, String vendorBusinessName, double amountdue, double amountPaid,
			double amountOutstanding, String orderStatus, String dateNeeded, String dateAndTimeOfOrder) {
		super();
		this.orderId = orderId;
		this.vendorBusinessName = vendorBusinessName;
		this.amountdue = amountdue;
		this.amountPaid = amountPaid;
		this.amountOutstanding = amountOutstanding;
		this.orderStatus = orderStatus;
		DateNeeded = dateNeeded;
		DateAndTimeOfOrder = dateAndTimeOfOrder;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getVendorBusinessName() {
		return vendorBusinessName;
	}

	public void setVendorBusinessName(String vendorBusinessName) {
		this.vendorBusinessName = vendorBusinessName;
	}

	public double getAmountdue() {
		return amountdue;
	}

	public void setAmountdue(double amountdue) {
		this.amountdue = amountdue;
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

	public String getDateNeeded() {
		return DateNeeded;
	}

	public void setDateNeeded(String dateNeeded) {
		DateNeeded = dateNeeded;
	}

	public String getDateAndTimeOfOrder() {
		return DateAndTimeOfOrder;
	}

	public void setDateAndTimeOfOrder(String dateAndTimeOfOrder) {
		DateAndTimeOfOrder = dateAndTimeOfOrder;
	}	

}
