package com.cl.food_app.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.food_app.dto.FoodOrder;
import com.cl.food_app.repository.FoodOrderRepository;

@Repository
public class FoodOrderDao {

	@Autowired
	private FoodOrderRepository repository;
	
	public FoodOrder saveFoodOrder(FoodOrder foodOrder) {
		return repository.save(foodOrder);
	}
	
	public Optional<FoodOrder> getFoodOrderById(int id) {
		return repository.findById(id);
	}
}
