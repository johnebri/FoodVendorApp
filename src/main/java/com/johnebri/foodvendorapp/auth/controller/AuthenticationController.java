package com.johnebri.foodvendorapp.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.johnebri.foodvendorapp.auth.data.AuthenticationData;
import com.johnebri.foodvendorapp.auth.service.AuthService;
import com.johnebri.foodvendorapp.util.data.UtilResponse;

@RestController
public class AuthenticationController {
	
	@Autowired
	AuthService authSvc;	
	
	@PostMapping("/auth/setpassword")
	public UtilResponse setPassword(@RequestBody AuthenticationData authData) {
		
		return authSvc.setPassword(authData);
	}
	
	@PostMapping("/auth/login")
	public UtilResponse createAuthenticationToken(@RequestBody AuthenticationData authRequest) throws Exception {
		return authSvc.login(authRequest);
	}

}
