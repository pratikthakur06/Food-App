package com.cl.food_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.food_app.dto.BranchManager;

public interface BranchManagerRepository extends JpaRepository<BranchManager, Integer> {

	public BranchManager findBranchManagerByEmailAndPassword(String email, String password);
}
