package com.cl.food_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cl.food_app.dao.FoodOrderDao;

@Service
public class FoodOrderService {

	@Autowired
	private FoodOrderDao dao;

	@Autowired
	private StaffService staffService;

	@Autowired
	private ItemService itemService;

}
