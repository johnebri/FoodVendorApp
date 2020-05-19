package com.johnebri.foodvendorapp.menu.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Menu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String description;
	private String price;
	private String quantity;
	private String dateTimeCreated;
	private int vendorId;
	private boolean isRecurring;
	private String frequencyOfRecocurrence;

}
