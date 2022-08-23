package com.cl.food_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cl.food_app.dao.BranchManagerDao;
import com.cl.food_app.dto.Branch;
import com.cl.food_app.dto.BranchManager;
import com.cl.food_app.util.AES;

@Service
public class BranchManagerService {
	
	@Autowired
	private BranchManagerDao dao;
	
	@Autowired
	private AES aes;
	
	@Autowired
	BranchService branchService;

	public BranchManager saveBranchManager(BranchManager branchManager, int branchId) {
		Branch branch = branchService.getBranchById(branchId);
		branchManager.setBranch(branch);
		branchManager.setPassword(aes.encrypt(branchManager.getPassword(), "secretpratik"));
		return dao.saveBranchManager(branchManager);
	}
	
	public BranchManager getBranchManagerById(int id) {
		return dao.getBranchManagerById(id).get();
	}
}
