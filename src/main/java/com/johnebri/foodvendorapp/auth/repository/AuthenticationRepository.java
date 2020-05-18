package com.johnebri.foodvendorapp.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnebri.foodvendorapp.auth.data.AuthenticationData;

public interface AuthenticationRepository extends JpaRepository<AuthenticationData, Integer>{

}
