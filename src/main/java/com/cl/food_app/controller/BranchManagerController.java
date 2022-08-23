package com.cl.food_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cl.food_app.dto.BranchManager;
import com.cl.food_app.service.BranchManagerService;

@RestController
public class BranchManagerController {

	@Autowired
	private BranchManagerService service;
	
	@PostMapping("/saveBranchManager/{branchId}")
	public BranchManager saveBranchManager(@RequestBody BranchManager branchManager, @PathVariable int branchId) {
		return service.saveBranchManager(branchManager,branchId);
	}
}
