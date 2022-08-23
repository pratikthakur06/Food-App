package com.cl.food_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cl.food_app.dao.ItemDao;
import com.cl.food_app.dto.Item;
import com.cl.food_app.dto.Menu;

@Service
public class ItemService {

	@Autowired
	private ItemDao dao;
	
	@Autowired
	private MenuService menuService;
	
	public Item saveItem(Item item, int menuId) {
		Menu menu = menuService.getMenuById(menuId);
		item.setMenu(menu);
		return dao.saveItem(item);
	}
	
	public Item getItemById(int id) {
		return dao.getItemById(id).get();
	}
}
