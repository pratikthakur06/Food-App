package com.cl.food_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cl.food_app.dao.AdminDao;
import com.cl.food_app.dto.Admin;
import com.cl.food_app.util.AES;

@Service
public class AdminService {

	@Autowired
	private AdminDao dao;
	
	@Autowired
	private AES aes;

	public Admin saveAdmin(Admin admin) {
		admin.setPassword(aes.encrypt(admin.getPassword(), "secretpratik"));
		return dao.saveAdmin(admin);
	}

	public Admin updateAdmin(Admin admin, int id) {
		return dao.updateAdmin(admin, id);
	}

	public Admin getAdminById(int id) {
		return dao.getAdminById(id).get();
	}
	
	public Admin deleteAdmin(int id) {
		return dao.deleteAdmin(id);
	}
	
	public List<Admin> findAllAdmin() {
		return dao.findAllAdmin();
	}
}
