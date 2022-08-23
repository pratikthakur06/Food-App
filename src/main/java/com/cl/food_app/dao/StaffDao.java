package com.cl.food_app.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.food_app.dto.Staff;
import com.cl.food_app.repository.StaffRepository;

@Repository
public class StaffDao {

	@Autowired
	private StaffRepository repository;
	
	public Staff saveStaff(Staff staff) {
		return repository.save(staff);
	}
	
	public Optional<Staff> getStaffById(int id) {
		return repository.findById(id);
	}
}
