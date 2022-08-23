package com.cl.food_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cl.food_app.dao.StaffDao;
import com.cl.food_app.dto.BranchManager;
import com.cl.food_app.dto.Staff;
import com.cl.food_app.util.AES;

@Service
public class StaffService {

	@Autowired
	private StaffDao dao;
	
	@Autowired
	private AES aes;
	
	@Autowired
	private BranchManagerService branchManagerService;
	
	public Staff saveStaff(Staff staff, int branchManagerId) {
		BranchManager branchManager = branchManagerService.getBranchManagerById(branchManagerId);
		staff.setBranchManager(branchManager);
		staff.setPassword(aes.encrypt(staff.getPassword(), "secretpratik"));
		return dao.saveStaff(staff);
	}
	
	public Staff getStaffById(int id) {
		return dao.getStaffById(id).get();
	}
	
}
