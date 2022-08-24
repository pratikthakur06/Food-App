package com.cl.food_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.food_app.dao.BranchManagerDao;
import com.cl.food_app.dao.StaffDao;
import com.cl.food_app.dto.BranchManager;
import com.cl.food_app.dto.Staff;
import com.cl.food_app.exception.IdNotFoundException;
import com.cl.food_app.util.AES;
import com.cl.food_app.util.ResponseStructure;

@Service
public class StaffService {

	@Autowired
	private StaffDao dao;

	@Autowired
	private AES aes;

	@Autowired
	private BranchManagerDao branchManagerDao;

	public ResponseEntity<ResponseStructure<Staff>> saveStaff(Staff staff, int branchManagerId) {
		BranchManager branchManager = branchManagerDao.getBranchManagerById(branchManagerId).get();
		staff.setBranchManager(branchManager);
		staff.setPassword(aes.encrypt(staff.getPassword(), "secretpratik"));
		ResponseStructure<Staff> structure = new ResponseStructure<Staff>();
		structure.setMessage("Staff Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveStaff(staff));
		return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Staff>> getStaffById(int id) {
		Staff staff = dao.getStaffById(id).get();
		if (staff == null) {
			throw new IdNotFoundException();
		} else {
			ResponseStructure<Staff> structure = new ResponseStructure<Staff>();
			structure.setMessage("Staff Found Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(staff);
			return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<Staff>> updateStaff(Staff staff, int id) {
		ResponseStructure<Staff> structure = new ResponseStructure<Staff>();
		Staff staff2 = dao.updateStaff(staff, id);
		if (staff2 != null) {
			staff2.setPassword(aes.encrypt(staff.getPassword(), "secretpratik"));
			structure.setMessage("Staff Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.saveStaff(staff2));
			return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("ID is not valid");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Staff>> deleteStaff(int id) {
		ResponseStructure<Staff> structure = new ResponseStructure<Staff>();
		Staff staff = dao.getStaffById(id).get();
		if (staff != null) {
			structure.setMessage("Staff Deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.deleteStaff(id));
			return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<Staff>>> findAllStaff() {
		ResponseStructure<List<Staff>> structure = new ResponseStructure<List<Staff>>();
		structure.setMessage("Branch Found Successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllStaff());
		return new ResponseEntity<ResponseStructure<List<Staff>>>(structure, HttpStatus.OK);
	}
}
