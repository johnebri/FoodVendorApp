package com.johnebri.foodvendorapp.menu.data;

public class AvailableMenuResponse {
	 private int id;
     private String name;
     private String description;
     private double price;
     private Long quantity;
     private String vendor;
     private String isRecurring;
     private String frequencyOfRecurrence;
     private String dateTimeCreated;
     
     public AvailableMenuResponse() { }

	

	public AvailableMenuResponse(int id, String name, String description, double price, Long quantity, String vendor,
			String isRecurring, String frequencyOfRecurrence, String dateTimeCreated) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.vendor = vendor;
		this.isRecurring = isRecurring;
		this.frequencyOfRecurrence = frequencyOfRecurrence;
		this.dateTimeCreated = dateTimeCreated;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public Long getQuantity() {
		return quantity;
	}



	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}



	public String getVendor() {
		return vendor;
	}



	public void setVendor(String vendor) {
		this.vendor = vendor;
	}



	public String getIsRecurring() {
		return isRecurring;
	}



	public void setIsRecurring(String isRecurring) {
		this.isRecurring = isRecurring;
	}



	public String getFrequencyOfRecurrence() {
		return frequencyOfRecurrence;
	}



	public void setFrequencyOfRecurrence(String frequencyOfRecurrence) {
		this.frequencyOfRecurrence = frequencyOfRecurrence;
	}



	public String getDateTimeCreated() {
		return dateTimeCreated;
	}



	public void setDateTimeCreated(String dateTimeCreated) {
		this.dateTimeCreated = dateTimeCreated;
	}


     
    
}
