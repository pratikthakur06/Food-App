package com.cl.food_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.food_app.dao.AdminDao;
import com.cl.food_app.dto.Admin;
import com.cl.food_app.exception.AdminNotFoundException;
import com.cl.food_app.exception.IdNotFoundException;
import com.cl.food_app.util.AES;
import com.cl.food_app.util.ResponseStructure;

@Service
public class AdminService {

	@Autowired
	private AdminDao dao;

	@Autowired
	private AES aes;

	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
		admin.setPassword(aes.encrypt(admin.getPassword(), "secretpratik"));
		ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
		structure.setMessage("Admin Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveAdmin(admin));
		return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Admin>> loginAdmin(Admin admin) {
		admin.setPassword(aes.encrypt(admin.getPassword(), "secretpratik"));
		Admin admin2 = dao.loginAdmin(admin);
		ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
		if (admin2 == null) {
			structure.setMessage("Admin Not Found!!");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.NOT_FOUND);
		} else {
			admin2.setPassword(aes.decrypt(admin2.getPassword(), "secretpratik"));
			structure.setMessage("Admin Logged In Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(admin2);
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin, int id) {
		ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
		Admin admin2 = dao.updateAdmin(admin, id);
		if (admin2 != null) {
			admin2.setPassword(aes.encrypt(admin.getPassword(), "secretpratik"));
			structure.setMessage("Admin Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.saveAdmin(admin2));
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		} else {
			throw new AdminNotFoundException(id);
		}
	}

	public ResponseEntity<ResponseStructure<Admin>> getAdminById(int id) {
		Optional<Admin> optional = dao.getAdminById(id);
		if (optional.isEmpty()) {
			throw new AdminNotFoundException(id);
		} else {
			ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
			structure.setMessage("Admin Found Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<Admin>> deleteAdmin(int id) {
		ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
		Optional<Admin> admin = dao.getAdminById(id);
		if (admin.isPresent()) {
			structure.setMessage("Admin Deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.deleteAdmin(id));
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		} else {
			throw new AdminNotFoundException(id);
		}
	}

	public ResponseEntity<ResponseStructure<List<Admin>>> findAllAdmin() {
		ResponseStructure<List<Admin>> structure = new ResponseStructure<List<Admin>>();
		structure.setMessage("Admin Found Successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllAdmin());
		return new ResponseEntity<ResponseStructure<List<Admin>>>(structure, HttpStatus.OK);
	}
}
