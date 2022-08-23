package com.cl.food_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cl.food_app.dao.BranchDao;
import com.cl.food_app.dto.Admin;
import com.cl.food_app.dto.Branch;

@Service
public class BranchService {

	@Autowired
	private BranchDao dao;
	
	@Autowired
	private AdminService adminService;
	
	public Branch saveBranch(Branch branch, int adminId) {
		Admin admin = adminService.getAdminById(adminId);
		branch.setAdmin(admin);
		return dao.saveBranch(branch);
	}
	
	public Branch getBranchById(int id) {
		return dao.getBranchById(id).get();
	}
}
