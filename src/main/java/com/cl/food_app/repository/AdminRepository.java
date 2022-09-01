package com.cl.food_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.food_app.dto.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	public Admin findAdminByEmailAndPassword(String email,String password); 
}
