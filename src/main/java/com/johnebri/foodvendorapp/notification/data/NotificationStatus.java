package com.johnebri.foodvendorapp.notification.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NotificationStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String status;
	
	public NotificationStatus() { }

	public NotificationStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
