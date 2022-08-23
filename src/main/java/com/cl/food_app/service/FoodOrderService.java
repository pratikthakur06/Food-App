package com.cl.food_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cl.food_app.dao.FoodOrderDao;
import com.cl.food_app.dto.FoodOrder;
import com.cl.food_app.dto.Item;
import com.cl.food_app.dto.Staff;

@Service
public class FoodOrderService {

	@Autowired
	private FoodOrderDao dao;

	@Autowired
	private StaffService staffService;

	@Autowired
	private ItemService itemService;
	
	public FoodOrder saveFoodOrder(FoodOrder foodOrder, List<Integer> itemIds, int staffId) {
		int totalPrice = 0;
		List<Item> items = new ArrayList<>();
		for(int itemId:itemIds) {
			Item item = itemService.getItemById(itemId);
			totalPrice+=item.getPrice();
			items.add(item);
		}
		foodOrder.setItems(items);
		foodOrder.setTotalPrice(totalPrice);
		
		Staff staff = staffService.getStaffById(staffId);
		foodOrder.setStaff(staff);
		return dao.saveFoodOrder(foodOrder);
	}
	
	public Optional<FoodOrder> getFoodOrderById(int id) {
		return dao.getFoodOrderById(id);
	}
}
