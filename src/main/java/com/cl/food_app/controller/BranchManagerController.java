package com.cl.food_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cl.food_app.dto.Admin;
import com.cl.food_app.dto.BranchManager;
import com.cl.food_app.service.BranchManagerService;
import com.cl.food_app.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class BranchManagerController {

	@Autowired
	private BranchManagerService service;

	@PostMapping("/branchManager/{branchId}")
	public ResponseEntity<ResponseStructure<BranchManager>> saveBranchManager(@RequestBody BranchManager branchManager,
			@PathVariable int branchId) {
		return service.saveBranchManager(branchManager, branchId);
	}
	
	@PostMapping("/branchManagerLogin")
	public ResponseEntity<ResponseStructure<BranchManager>> loginBranchManager(@RequestBody BranchManager branchManager) {
		return service.loginBranchManager(branchManager);
	}

	@PutMapping("/branchManager")
	public ResponseEntity<ResponseStructure<BranchManager>> updateBranchManager(
			@RequestBody BranchManager branchManager, @RequestParam int id) {
		return service.updateBranchManager(branchManager, id);
	}

	@DeleteMapping("/branchManager/{branchManagerId}")
	public ResponseEntity<ResponseStructure<BranchManager>> deleteBranchManager(@PathVariable int branchManagerId) {
		return service.deleteBranchManager(branchManagerId);
	}

	@GetMapping("/branchManager/{id}")
	public ResponseEntity<ResponseStructure<BranchManager>> getBranchManagerById(@PathVariable int id) {
		return service.getBranchManagerById(id);
	}

	@GetMapping("/branchManager")
	public ResponseEntity<ResponseStructure<List<BranchManager>>> findAllBranchManager() {
		return service.findAllBranchManager();
	}
}
