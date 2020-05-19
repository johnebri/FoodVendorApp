package com.johnebri.foodvendorapp.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.johnebri.foodvendorapp.auth.data.AuthenticationData;
import com.johnebri.foodvendorapp.auth.repository.AuthenticationRepository;
import com.johnebri.foodvendorapp.customer.repository.CustomerRepository;
import com.johnebri.foodvendorapp.security.AuthenticationResponse;
import com.johnebri.foodvendorapp.security.JwtUtil;
import com.johnebri.foodvendorapp.security.MyUserDetailsService;
import com.johnebri.foodvendorapp.util.data.UtilResponse;
import com.johnebri.foodvendorapp.util.data.UtilStatus;
import com.johnebri.foodvendorapp.util.service.UtilService;
import com.johnebri.foodvendorapp.vendor.repository.VendorRepository;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationRepository authRepo;
	
	@Autowired
	private VendorRepository vendorRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private UtilService utilService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	AuthenticationData authData;
	
	UtilResponse utilResponse = new UtilResponse();
	UtilStatus utilStatus = new UtilStatus();
	
	public UtilResponse setPassword(AuthenticationData newAuthData) {
		
		// check if email has already signed up as vendor or customer
		if( vendorRepo.findByEmail(newAuthData.getEmail()) == null && 
			customerRepo.findByEmail(newAuthData.getEmail()) == null ) {
			
			return utilService.createResponse( null, "400", 
					"You must create an account to set a password"
			);
		}
		
		// check if email already exist
		Optional<AuthenticationData> authData = authRepo.findByEmail(newAuthData.getEmail());
		if(authData.isPresent()) {
			// email is already in use				
			return utilService.createResponse( null, "400", 
					"A user already exist with the email you entered"
			);
		}
		
		// go ahead and create a password
		
		AuthenticationData authDataSaved = authRepo.save(newAuthData);
		
		return utilService.createResponse( authDataSaved, "200", 
				"Your password was set successfully"
		);
		
	}
	
	public UtilResponse login(AuthenticationData authRequest) throws Exception {
		
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
			);	
		} catch(BadCredentialsException e) {
			return utilService.createResponse( null, "403", 
					"Incorrect username or password"
			);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByEmail(authRequest.getEmail());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return utilService.createResponse( new AuthenticationResponse(jwt), "200", 
				"You login was successful"
		);
		
	}

}
