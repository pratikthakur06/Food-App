package com.cl.food_app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.food_app.dto.Admin;
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

	public BranchManager loginBranchManager(BranchManager branchManager) {
		return repository.findBranchManagerByEmailAndPassword(branchManager.getEmail(), branchManager.getPassword());
	}

	public Optional<BranchManager> getBranchManagerById(int id) {
		return repository.findById(id);
	}

	public BranchManager updateBranchManager(BranchManager branchManager, int id) {
		if (repository.findById(id).isEmpty()) {
			return null;
		} else {
			branchManager.setId(id);
			Branch branch = getBranchManagerById(id).get().getBranch();
			branchManager.setBranch(branch);
			return repository.save(branchManager);
		}
	}

	public BranchManager deleteBranchManager(int id) {
		BranchManager branchManager = getBranchManagerById(id).get();
		repository.delete(branchManager);
		return branchManager;
	}

	public List<BranchManager> findAllBranchManager() {
		return repository.findAll();
	}
}
