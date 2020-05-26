package com.johnebri.foodvendorapp.orders.data;

public class PaymentRequest {
	
	private double amount;
	
	public PaymentRequest() { }

	public PaymentRequest(double amount) {
		super();
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
