package com.johnebri.foodvendorapp.menu.service;

import java.util.List;

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
	
	@Autowired
	UtilService utilSvc;
	
	UtilResponse utilResponse = new UtilResponse();
	
	public UtilResponse createMenu(Menu newMenu, HttpServletRequest request) {
		// get id of vendor
		int id = utilSvc.getVendorId(request);
		newMenu.setVendorId(id);
		Menu menu = menuRepo.save(newMenu);
		return utilSvc.createResponse(menu, "200", "Menu created successfully");
	}
	
	public List<Menu> allMenu() {
		return menuRepo.findAll();
	}
	
	public List<Menu> getVendorMenu(HttpServletRequest request) {
		int id = utilSvc.getVendorId(request);
		return menuRepo.findByVendorId(id);
	}
	
	public UtilResponse updateVendorMenu(Menu newMenu, HttpServletRequest request) {
		// check if menu belongs to the vendor
		int id = utilSvc.getVendorId(request);
		int menuId = newMenu.getId();
		Menu searchedMenu = menuRepo.findById(menuId);
		
		// check if menu exist
		if(searchedMenu == null) {
			return utilSvc.createResponse(null, "400", 
				"Menu does not exist");
		}
		
		if(searchedMenu.getVendorId() != id) {
			// menu does not belong to vendor
			return utilSvc.createResponse(null, "400", 
					"You do not have the priviledge to update another vendor's menu");
		}		
		
		Menu editedMenu = menuRepo.save(newMenu);
		return utilSvc.createResponse(editedMenu, "200", "Menu updates successfully");
	}
	
	

}
