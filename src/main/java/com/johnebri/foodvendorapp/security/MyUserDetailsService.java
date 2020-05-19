package com.johnebri.foodvendorapp.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.johnebri.foodvendorapp.auth.data.AuthenticationData;
import com.johnebri.foodvendorapp.auth.repository.AuthenticationRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	AuthenticationRepository authRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<AuthenticationData> user = authRepo.findByEmail(email);
		
		user.orElseThrow(() -> new UsernameNotFoundException("Not Found" + email));
		
		return user.map(MyUserDetails::new).get();
	}
	
	public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
		Optional<AuthenticationData> user = authRepo.findByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));
		return user.map(MyUserDetails::new).get();
	}
	
	

}
