package com.cl.food_app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.food_app.dto.BranchManager;
import com.cl.food_app.dto.Staff;
import com.cl.food_app.repository.StaffRepository;

@Repository
public class StaffDao {

	@Autowired
	private StaffRepository repository;

	public Staff saveStaff(Staff staff) {
		return repository.save(staff);
	}
	
	public Staff loginStaff(Staff staff) {
		return repository.findStaffByEmailAndPassword(staff.getEmail(), staff.getPassword());
	}

	public Optional<Staff> getStaffById(int id) {
		return repository.findById(id);
	}

	public Staff updateStaff(Staff staff, int id) {
		if (repository.findById(id).isEmpty()) {
			return null;
		} else {
			staff.setId(id);
			BranchManager branchManager = getStaffById(id).get().getBranchManager();
			staff.setBranchManager(branchManager);
			return repository.save(staff);
		}
	}

	public Staff deleteStaff(int id) {
		Staff staff = getStaffById(id).get();
		repository.delete(staff);
		return staff;
	}

	public List<Staff> findAllStaff() {
		return repository.findAll();
	}
}
