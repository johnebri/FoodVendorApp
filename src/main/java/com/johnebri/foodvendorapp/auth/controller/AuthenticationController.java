package com.johnebri.foodvendorapp.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnebri.foodvendorapp.auth.data.AuthenticationData;
import com.johnebri.foodvendorapp.auth.service.AuthService;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {
	
	@Autowired
	AuthService authSvc;
	
	@PostMapping("/auth/setpassword")
	public AuthenticationData setPassword(@RequestBody AuthenticationData authData) {
		
		return authSvc.setPassword(authData);
	}

}
