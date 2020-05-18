package com.johnebri.foodvendorapp.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnebri.foodvendorapp.auth.data.AuthenticationData;
import com.johnebri.foodvendorapp.auth.repository.AuthenticationRepository;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationRepository authRepo;
	
	public AuthenticationData setPassword(AuthenticationData authData) {
		return authRepo.save(authData);
	}

}
