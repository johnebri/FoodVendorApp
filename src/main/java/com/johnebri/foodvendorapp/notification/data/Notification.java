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
	private String messag;	
	private int messageStatus;
	@CreationTimestamp
	private Timestamp dateTimeCreated;

}
