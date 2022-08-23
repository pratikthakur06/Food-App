package com.cl.food_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cl.food_app.dto.Staff;
import com.cl.food_app.service.StaffService;

@RestController
public class StaffController {

	@Autowired
	private StaffService service;
	
	@PostMapping("/saveStaff/{branchManagerId}")
	public Staff saveStaff(@RequestBody Staff staff, @PathVariable int branchManagerId) {
		return service.saveStaff(staff,branchManagerId);
	}
}
