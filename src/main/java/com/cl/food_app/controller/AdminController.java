package com.cl.food_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cl.food_app.dto.Admin;
import com.cl.food_app.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	AdminService service;
	
	@PostMapping("/saveAdmin")
	public Admin saveAdmin(@RequestBody Admin admin) {
		return service.saveAdmin(admin);
	}
	
	@PutMapping("/updateAdmin")
	public Admin updateAdmin(@RequestBody Admin admin, @RequestParam int id) {
		return service.updateAdmin(admin,id);
	}
	
	@DeleteMapping("/deleteAdmin")
	public Admin deleteAdmin(@RequestParam int id) {
		return service.deleteAdmin(id);
	}
	
	@GetMapping("/admin/{id}")
	public Admin getAdminById(@PathVariable int id) {
		return service.getAdminById(id);
	}
	
	@GetMapping("/admin")
	public List<Admin> findAllAdmin() {
		return service.findAllAdmin();
	}
}
