package com.cl.food_app.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.food_app.dto.Branch;
import com.cl.food_app.dto.BranchManager;
import com.cl.food_app.repository.BranchManagerRepository;

@Repository
public class BranchManagerDao {

	@Autowired
	BranchManagerRepository repository;
	
	public BranchManager saveBranchManager(BranchManager branchManager) {
		return repository.save(branchManager);
	}
	
	public Optional<BranchManager> getBranchManagerById(int id) {
		return repository.findById(id);
	}
}
