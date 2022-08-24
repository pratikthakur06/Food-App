package com.cl.food_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.food_app.dao.FoodOrderDao;
import com.cl.food_app.dao.ItemDao;
import com.cl.food_app.dao.StaffDao;
import com.cl.food_app.dto.FoodOrder;
import com.cl.food_app.dto.Item;
import com.cl.food_app.dto.Staff;
import com.cl.food_app.exception.IdNotFoundException;
import com.cl.food_app.util.ResponseStructure;

@Service
public class FoodOrderService {

	@Autowired
	private FoodOrderDao dao;

	@Autowired
	private StaffDao staffDao;

	@Autowired
	private ItemDao itemDao;

	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(FoodOrder foodOrder, List<Integer> itemIds,
			int staffId) {
		int totalPrice = 0;
		List<Item> items = new ArrayList<>();
		for (int itemId : itemIds) {
			Item item = itemDao.getItemById(itemId).get();
			totalPrice += item.getPrice();
			items.add(item);
		}
		foodOrder.setItems(items);
		foodOrder.setTotalPrice(totalPrice);

		Staff staff = staffDao.getStaffById(staffId).get();
		foodOrder.setStaff(staff);
		ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
		structure.setMessage("Food Order Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveFoodOrder(foodOrder));
		return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<FoodOrder>> getFoodOrderById(int id) {
		FoodOrder foodOrder = dao.getFoodOrderById(id).get();
		if (foodOrder == null) {
			throw new IdNotFoundException();
		} else {
			ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
			structure.setMessage("Food Order Found Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(foodOrder);
			return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<FoodOrder>> updateFoodOrder(FoodOrder foodOrder, List<Integer> itemIds,
			int id) {
		ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
		FoodOrder foodOrder2 = dao.updateFoodOrder(foodOrder, id);
		if (foodOrder2 != null) {
			int totalPrice = 0;
			List<Item> items = new ArrayList<>();
			for (int itemId : itemIds) {
				Item item = itemDao.getItemById(itemId).get();
				totalPrice += item.getPrice();
				items.add(item);
			}
			foodOrder.setItems(items);
			foodOrder.setTotalPrice(totalPrice);
			return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("ID is not valid");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<FoodOrder>> deleteFoodOrder(int id) {
		ResponseStructure<FoodOrder> structure = new ResponseStructure<FoodOrder>();
		FoodOrder foodOrder = dao.getFoodOrderById(id).get();
		if (foodOrder != null) {
			structure.setMessage("Food Order Deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.deleteFoodOrder(id));
			return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<FoodOrder>>> findAllFoodOrder() {
		ResponseStructure<List<FoodOrder>> structure = new ResponseStructure<List<FoodOrder>>();
		structure.setMessage("Food Order Found Successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllFoodOrder());
		return new ResponseEntity<ResponseStructure<List<FoodOrder>>>(structure, HttpStatus.OK);
	}
}
