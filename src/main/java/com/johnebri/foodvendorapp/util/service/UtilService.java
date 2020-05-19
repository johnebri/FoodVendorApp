package com.johnebri.foodvendorapp.util.service;

import org.springframework.stereotype.Service;

import com.johnebri.foodvendorapp.util.data.UtilResponse;
import com.johnebri.foodvendorapp.util.data.UtilStatus;

@Service
public class UtilService {
	
	UtilResponse utilResponse = new UtilResponse();
	UtilStatus utilStatus = new UtilStatus();
	
	public UtilResponse createResponse(Object data, String responseCode, String responseMessage) {
		
		utilResponse.setData(data);
		utilStatus.setResponseCode(responseCode);
		utilStatus.setResponseMessage(responseMessage);
		utilResponse.setStatus(utilStatus);
		return utilResponse;		
	}

}
