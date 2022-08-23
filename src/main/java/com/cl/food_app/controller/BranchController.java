package com.cl.food_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cl.food_app.dto.Branch;
import com.cl.food_app.service.BranchService;

@RestController
public class BranchController {

	@Autowired
	private BranchService service;
	
	@PostMapping("/saveBranch/{adminId}")
	public Branch saveBranch(@RequestBody Branch branch, @PathVariable int adminId) {
		return service.saveBranch(branch,adminId);
	}
}
