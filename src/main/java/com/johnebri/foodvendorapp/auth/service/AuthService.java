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
import com.johnebri.foodvendorapp.customer.data.Customer;
import com.johnebri.foodvendorapp.customer.repository.CustomerRepository;
import com.johnebri.foodvendorapp.security.AuthenticationResponse;
import com.johnebri.foodvendorapp.security.JwtUtil;
import com.johnebri.foodvendorapp.security.MyUserDetailsService;
import com.johnebri.foodvendorapp.util.data.UtilResponse;
import com.johnebri.foodvendorapp.util.data.UtilStatus;
import com.johnebri.foodvendorapp.util.service.UtilService;
import com.johnebri.foodvendorapp.vendor.data.Vendor;
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
	private UtilService utilSvc;
	
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
			
			return utilSvc.createResponse( null, "400", 
					"You must create an account to set a password"
			);
		}
		
		// check if email already exist
		Optional<AuthenticationData> authData = authRepo.findByEmail(newAuthData.getEmail());
		if(authData.isPresent()) {
			// email is already in use				
			return utilSvc.createResponse( null, "400", 
					"A user already exist with the email you entered"
			);
		}
		
		String ROLE = null;
		boolean vendorFound = false;
		boolean customerFound = false;
		
		// check if email is vendor or customer
		if(vendorRepo.findByEmail(newAuthData.getEmail()) != null) {
			// found a vendor
			ROLE = "ROLE_VENDOR";
			vendorFound = true;
		}
		
		if(customerRepo.findByEmail(newAuthData.getEmail()) != null) {
			// found a customer
			ROLE = "ROLE_CUSTOMER";
			customerFound = true;
		}	
		
		if (vendorFound && customerFound) {
			// error: email exist as customer and email
			return utilSvc.createResponse( null, "400", 
					"A user already exist with the email you entered"
			);
		}
		
		newAuthData.setRole(ROLE);	
		
		// go ahead and create a password
		
		AuthenticationData authDataSaved = authRepo.save(newAuthData);
		
		// send email to vendor/customer
		try {
			utilSvc.sendEmail(newAuthData.getEmail(), "Welcome Vendor", "You have successfully setup your password");
		} catch (Exception e) {
			System.out.println("You are not connected to the internet, you will not receive a mail");
		}
		
		return utilSvc.createResponse( authDataSaved, "200", 
				"Your password was set successfully"
		);
		
	}
	
	public UtilResponse login(AuthenticationData authRequest) throws Exception {
		
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
			);	
		} catch(BadCredentialsException e) {
			return utilSvc.createResponse( null, "403", 
					"Incorrect username or password"
			);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByEmail(authRequest.getEmail());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		// send email to vendor/customer
		
		return utilSvc.createResponse( new AuthenticationResponse(jwt), "200", 
				"You login was successful"
		);
		
	}

}
