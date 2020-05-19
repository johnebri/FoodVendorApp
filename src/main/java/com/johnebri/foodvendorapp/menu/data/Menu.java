package com.johnebri.foodvendorapp.menu.data;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Menu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String description;
	private double price;
	private Long quantity;	
	private int vendorId;
	private boolean isRecurring;
	private String frequencyOfRecocurrence;
	@CreationTimestamp
	private Timestamp dateTimeCreated;
	
	public Menu() { }

	public Menu(int id, String name, String description, double price, Long quantity, int vendorId, boolean isRecurring,
			String frequencyOfRecocurrence, Timestamp dateTimeCreated) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.vendorId = vendorId;
		this.isRecurring = isRecurring;
		this.frequencyOfRecocurrence = frequencyOfRecocurrence;
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

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public boolean isRecurring() {
		return isRecurring;
	}

	public void setRecurring(boolean isRecurring) {
		this.isRecurring = isRecurring;
	}

	public String getFrequencyOfRecocurrence() {
		return frequencyOfRecocurrence;
	}

	public void setFrequencyOfRecocurrence(String frequencyOfRecocurrence) {
		this.frequencyOfRecocurrence = frequencyOfRecocurrence;
	}

	public Timestamp getDateTimeCreated() {
		return dateTimeCreated;
	}

	public void setDateTimeCreated(Timestamp dateTimeCreated) {
		this.dateTimeCreated = dateTimeCreated;
	}	

}
