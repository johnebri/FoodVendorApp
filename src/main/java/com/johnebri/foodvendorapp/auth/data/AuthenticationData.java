package com.johnebri.foodvendorapp.auth.data;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="authentication")
public class AuthenticationData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String email;
	private String password;
	@CreationTimestamp //this adds the default timestamp on save
	private Timestamp dateTimeCreated;
	
	public AuthenticationData() {
		
	}

	public AuthenticationData(int id, String email, String password, Timestamp dateTimeCreated) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.dateTimeCreated = dateTimeCreated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getDateTimeCreated() {
		return dateTimeCreated;
	}

	public void setDateTimeCreated(Timestamp dateTimeCreated) {
		this.dateTimeCreated = dateTimeCreated;
	}	

}
