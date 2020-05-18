package com.johnebri.foodvendorapp.vendor.data;

public class VendorSignupResponse {
	
	private Vendor vendor;
	private Status status;
	
	public VendorSignupResponse() { }

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}	

}
