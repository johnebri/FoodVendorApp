package com.johnebri.foodvendorapp.orders.data;

public class ShowOrdersResponse {
	
	private int orderId;
	private String customer;
	private String menu;
	private double amountDue;
	private double amountPaid;
	private double amountOutstanding;
	private String orderStatus;
	private String dateNeeded;
	private String dateAndTimeOfOrder;
	
	public ShowOrdersResponse() { }
	
	public ShowOrdersResponse(int orderId, String customer, String menu, double amountDue, double amountPaid,
			double amountOutstanding, String orderStatus, String dateNeeded, String dateAndTimeOfOrder) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.menu = menu;
		this.amountDue = amountDue;
		this.amountPaid = amountPaid;
		this.amountOutstanding = amountOutstanding;
		this.orderStatus = orderStatus;
		this.dateNeeded = dateNeeded;
		this.dateAndTimeOfOrder = dateAndTimeOfOrder;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
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

	public String getDateNeeded() {
		return dateNeeded;
	}

	public void setDateNeeded(String dateNeeded) {
		this.dateNeeded = dateNeeded;
	}

	public String getDateAndTimeOfOrder() {
		return dateAndTimeOfOrder;
	}

	public void setDateAndTimeOfOrder(String dateAndTimeOfOrder) {
		this.dateAndTimeOfOrder = dateAndTimeOfOrder;
	}

}
