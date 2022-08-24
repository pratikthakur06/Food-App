package com.cl.food_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.food_app.dao.AdminDao;
import com.cl.food_app.dao.BranchDao;
import com.cl.food_app.dto.Admin;
import com.cl.food_app.dto.Branch;
import com.cl.food_app.exception.IdNotFoundException;
import com.cl.food_app.util.ResponseStructure;

@Service
public class BranchService {

	@Autowired
	private BranchDao dao;

	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<Branch>> saveBranch(Branch branch, int adminId) {
		Admin admin = adminDao.getAdminById(adminId).get();
		branch.setAdmin(admin);
		ResponseStructure<Branch> structure = new ResponseStructure<Branch>();
		structure.setMessage("Branch Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveBranch(branch));
		return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Branch>> getBranchById(int id) {
		Branch branch = dao.getBranchById(id).get();
		if (branch == null) {
			throw new IdNotFoundException();
		} else {
			ResponseStructure<Branch> structure = new ResponseStructure<Branch>();
			structure.setMessage("Branch Found Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(branch);
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<Branch>> updateBranch(Branch branch, int id) {
		ResponseStructure<Branch> structure = new ResponseStructure<Branch>();
		Branch branch2 = dao.updateBranch(branch, id);
		if (branch2 != null) {
			structure.setMessage("Branch Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.saveBranch(branch2));
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("ID is not valid");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Branch>> deleteBranch(int id) {
		ResponseStructure<Branch> structure = new ResponseStructure<Branch>();
		Branch branch = dao.getBranchById(id).get();
		if (branch != null) {
			structure.setMessage("Branch Deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.deleteBranch(id));
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<Branch>>> findAllBranch() {
		ResponseStructure<List<Branch>> structure = new ResponseStructure<List<Branch>>();
		structure.setMessage("Branch Found Successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllBranch());
		return new ResponseEntity<ResponseStructure<List<Branch>>>(structure, HttpStatus.OK);
	}
}
