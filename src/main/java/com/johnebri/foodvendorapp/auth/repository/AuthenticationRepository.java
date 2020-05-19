package com.johnebri.foodvendorapp.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnebri.foodvendorapp.auth.data.AuthenticationData;

public interface AuthenticationRepository extends JpaRepository<AuthenticationData, Integer>{
	
	// AuthenticationData findByEmail(String email);
	
	Optional<AuthenticationData> findByEmail(String email);
	

}
