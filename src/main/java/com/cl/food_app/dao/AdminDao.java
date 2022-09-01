package com.cl.food_app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.food_app.dto.Admin;
import com.cl.food_app.repository.AdminRepository;

@Repository
public class AdminDao {

	@Autowired
	private AdminRepository repository;

	// Save
	public Admin saveAdmin(Admin admin) {
		return repository.save(admin);
	}

	// Login
	public Admin loginAdmin(Admin admin) {
		return repository.findAdminByEmailAndPassword(admin.getEmail(),admin.getPassword());
	}

	// Update
	public Admin updateAdmin(Admin admin, int id) {
		if (repository.findById(id).isEmpty()) {
			return null;
		} else {
			admin.setId(id);
			return repository.save(admin);
		}
	}

	// Get By Id
	public Optional<Admin> getAdminById(int id) {
		return repository.findById(id);
	}

	// Delete
	public Admin deleteAdmin(int id) {
		Admin admin = getAdminById(id).get();
		repository.delete(admin);
		return admin;
	}

	// Find All Admin
	public List<Admin> findAllAdmin() {
		return repository.findAll();
	}
}
