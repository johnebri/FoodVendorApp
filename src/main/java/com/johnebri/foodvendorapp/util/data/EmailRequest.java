package com.johnebri.foodvendorapp.util.data;


public class EmailRequest {
	
	private String senderName;
	private String senderEmail;
	private String message;
	
	public EmailRequest() { }

	public EmailRequest(String senderName, String senderEmail, String message) {
		super();
		this.senderName = senderName;
		this.senderEmail = senderEmail;
		this.message = message;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	

}
