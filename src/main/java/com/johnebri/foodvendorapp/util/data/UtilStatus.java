package com.johnebri.foodvendorapp.util.data;

public class UtilStatus {
	
	private String responseCode;
	private String responseMessage;
	
	public UtilStatus() {
		
	}

	public UtilStatus(String responseCode, String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}	

}
