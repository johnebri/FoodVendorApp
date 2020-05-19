package com.johnebri.foodvendorapp.menu.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnebri.foodvendorapp.menu.data.Menu;
import com.johnebri.foodvendorapp.menu.repository.MenuRepository;
import com.johnebri.foodvendorapp.security.JwtUtil;
import com.johnebri.foodvendorapp.util.data.UtilResponse;
import com.johnebri.foodvendorapp.util.service.UtilService;

@Service
public class MenuService {
	
	@Autowired
	private MenuRepository menuRepo;
	
	@Autowired
	JwtUtil jwtUtil;
	
	UtilService utilSvc;
	
	public UtilResponse createMenu(Menu newMenu, HttpServletRequest request) {
		// get id of user
		final String authorizationHeader = request.getHeader("Authorization");
		String jwt = authorizationHeader.substring(7);
		String username = jwtUtil.extractUsername(jwt);
		System.out.println("From menu service: " + username);
		Menu menu = menuRepo.save(newMenu);
		return utilSvc.createResponse(menu, "200", "Menu created successfully");
	}

}
