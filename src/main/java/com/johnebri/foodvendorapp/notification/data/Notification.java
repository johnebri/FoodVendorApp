package com.johnebri.foodvendorapp.notification.data;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Notification {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String subjectUser;
	private int orderId;
	private int customerId;
	private String message;	
	private String messageStatus;
	@CreationTimestamp
	private Timestamp dateTimeCreated;
	
	public Notification() { }

	public Notification(int id, String subjectUser, int orderId, int customerId, String message, String messageStatus,
			Timestamp dateTimeCreated) {
		super();
		this.id = id;
		this.subjectUser = subjectUser;
		this.orderId = orderId;
		this.customerId = customerId;
		this.message = message;
		this.messageStatus = messageStatus;
		this.dateTimeCreated = dateTimeCreated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubjectUser() {
		return subjectUser;
	}

	public void setSubjectUser(String subjectUser) {
		this.subjectUser = subjectUser;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}

	public Timestamp getDateTimeCreated() {
		return dateTimeCreated;
	}

	public void setDateTimeCreated(Timestamp dateTimeCreated) {
		this.dateTimeCreated = dateTimeCreated;
	}	
}
