package com.cl.food_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cl.food_app.dto.Item;
import com.cl.food_app.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	private ItemService service;
	
	@PostMapping("/saveItem/{menuId}")
	public Item saveItem(@RequestBody Item item, @PathVariable int menuId) {
		return service.saveItem(item,menuId);
	}
}
