package com.johnebri.foodvendorapp.orders.data;

public class VendorReportResponse {
	
	 private int id;
	 private String Customer;
	 private String menu;
	 private String description;
	 private double amountDue;
	 private double amountPaid;
	 private double amountOutstanding;
	 private String orderStatus;
	 private String dateNeeded;
	 private String dataAndTimeOfOrder;
	 
	 public VendorReportResponse() { }
     
	public VendorReportResponse(int id, String customer, String menu, String description, double amountDue,
			double amountPaid, double amountOutstanding, String orderStatus, String dateNeeded,
			String dataAndTimeOfOrder) {
		super();
		this.id = id;
		Customer = customer;
		this.menu = menu;
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

	public String getCustomer() {
		return Customer;
	}

	public void setCustomer(String customer) {
		Customer = customer;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
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

	public String getDateNeeded() {
		return dateNeeded;
	}

	public void setDateNeeded(String dateNeeded) {
		this.dateNeeded = dateNeeded;
	}

	public String getDataAndTimeOfOrder() {
		return dataAndTimeOfOrder;
	}

	public void setDataAndTimeOfOrder(String dataAndTimeOfOrder) {
		this.dataAndTimeOfOrder = dataAndTimeOfOrder;
	}     
     

}
