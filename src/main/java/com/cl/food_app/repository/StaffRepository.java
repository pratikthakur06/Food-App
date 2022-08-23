package com.cl.food_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.food_app.dto.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

}
