package com.johnebri.foodvendorapp.util.data;

public class UtilResponse {
	
	private Object data;
	private UtilStatus status;
	
	public UtilResponse() { }	
	
	public UtilResponse(Object data, UtilStatus status) {
		super();
		this.data = data;
		this.status = status;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public UtilStatus getStatus() {
		return status;
	}
	public void setStatus(UtilStatus status) {
		this.status = status;
	}
	

}
