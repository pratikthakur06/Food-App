package com.cl.food_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.food_app.dao.BranchDao;
import com.cl.food_app.dao.BranchManagerDao;
import com.cl.food_app.dto.Branch;
import com.cl.food_app.dto.BranchManager;
import com.cl.food_app.exception.BranchManagerNotFoundException;
import com.cl.food_app.exception.IdNotFoundException;
import com.cl.food_app.util.AES;
import com.cl.food_app.util.ResponseStructure;

@Service
public class BranchManagerService {

	@Autowired
	private BranchManagerDao dao;

	@Autowired
	private AES aes;

	@Autowired
	private BranchDao branchDao;

	public ResponseEntity<ResponseStructure<BranchManager>> saveBranchManager(BranchManager branchManager,
			int branchId) {
		Branch branch = branchDao.getBranchById(branchId).get();
		branchManager.setBranch(branch);
		branchManager.setPassword(aes.encrypt(branchManager.getPassword(), "secretpratik"));
		ResponseStructure<BranchManager> structure = new ResponseStructure<BranchManager>();
		structure.setMessage("Branch Manager Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveBranchManager(branchManager));
		return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<BranchManager>> getBranchManagerById(int id) {
		Optional<BranchManager> branchManager = dao.getBranchManagerById(id);
		if (branchManager.isEmpty()) {
			throw new BranchManagerNotFoundException(id);
		} else {
			ResponseStructure<BranchManager> structure = new ResponseStructure<BranchManager>();
			structure.setMessage("Branch Manager Found Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(branchManager.get());
			return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<BranchManager>> updateBranchManager(BranchManager branchManager, int id) {
		ResponseStructure<BranchManager> structure = new ResponseStructure<BranchManager>();
		BranchManager branchManager2 = dao.updateBranchManager(branchManager, id);
		if (branchManager2 != null) {
			branchManager2.setPassword(aes.encrypt(branchManager.getPassword(), "secretpratik"));
			structure.setMessage("Admin Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.saveBranchManager(branchManager2));
			return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.OK);
		} else {
			throw new BranchManagerNotFoundException(id);
		}
	}

	public ResponseEntity<ResponseStructure<BranchManager>> deleteBranchManager(int id) {
		ResponseStructure<BranchManager> structure = new ResponseStructure<BranchManager>();
		Optional<BranchManager> branchManager = dao.getBranchManagerById(id);
		if (branchManager.isPresent()) {
			structure.setMessage("Branch Deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.deleteBranchManager(id));
			return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.OK);
		} else {
			throw new BranchManagerNotFoundException(id);
		}
	}

	public ResponseEntity<ResponseStructure<List<BranchManager>>> findAllBranchManager() {
		ResponseStructure<List<BranchManager>> structure = new ResponseStructure<List<BranchManager>>();
		structure.setMessage("Branch Found Successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllBranchManager());
		return new ResponseEntity<ResponseStructure<List<BranchManager>>>(structure, HttpStatus.OK);
	}
}
