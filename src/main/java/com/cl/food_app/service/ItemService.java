package com.cl.food_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.food_app.dao.ItemDao;
import com.cl.food_app.dao.MenuDao;
import com.cl.food_app.dto.Item;
import com.cl.food_app.dto.Menu;
import com.cl.food_app.exception.IdNotFoundException;
import com.cl.food_app.util.ResponseStructure;

@Service
public class ItemService {

	@Autowired
	private ItemDao dao;

	@Autowired
	private MenuDao menuDao;

	public ResponseEntity<ResponseStructure<Item>> saveItem(Item item, int menuId) {
		Menu menu = menuDao.getMenuById(menuId).get();
		item.setMenu(menu);
		ResponseStructure<Item> structure = new ResponseStructure<Item>();
		structure.setMessage("Item Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveItem(item));
		return new ResponseEntity<ResponseStructure<Item>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Item>> getItemById(int id) {
		Item item = dao.getItemById(id).get();
		if (item == null) {
			throw new IdNotFoundException();
		} else {
			ResponseStructure<Item> structure = new ResponseStructure<Item>();
			structure.setMessage("Item Found Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(item);
			return new ResponseEntity<ResponseStructure<Item>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<Item>> updateItem(Item item, int id) {
		ResponseStructure<Item> structure = new ResponseStructure<Item>();
		Item item2 = dao.updateItem(item, id);
		if (item2 != null) {
			structure.setMessage("Item Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.saveItem(item2));
			return new ResponseEntity<ResponseStructure<Item>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("ID is not valid");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Item>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Item>> deleteItem(int id) {
		ResponseStructure<Item> structure = new ResponseStructure<Item>();
		Item item = dao.getItemById(id).get();
		if (item != null) {
			structure.setMessage("Item Deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.deleteItem(id));
			return new ResponseEntity<ResponseStructure<Item>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<Item>>> findAllItem() {
		ResponseStructure<List<Item>> structure = new ResponseStructure<List<Item>>();
		structure.setMessage("Menu Found Successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllItem());
		return new ResponseEntity<ResponseStructure<List<Item>>>(structure, HttpStatus.OK);
	}
}
