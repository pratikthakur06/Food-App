package com.cl.food_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cl.food_app.dto.FoodOrder;
import com.cl.food_app.service.FoodOrderService;

@RestController
public class FoodOrderController {

	@Autowired
	private FoodOrderService service;
	
	@PostMapping("/saveFoodOrder")
	public FoodOrder saveFoodOrder(@RequestBody FoodOrder foodOrder, @RequestParam("items") List<Integer> itemIds, @RequestParam("staffId") int staffId) {
		return service.saveFoodOrder(foodOrder,itemIds,staffId);
	}
}
