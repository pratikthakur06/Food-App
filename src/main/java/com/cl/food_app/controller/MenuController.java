package com.cl.food_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cl.food_app.dto.Menu;
import com.cl.food_app.service.MenuService;

@RestController
public class MenuController {

	@Autowired
	private MenuService service;
	
	@PostMapping("/saveMenu/{branchManagerId}")
	public Menu saveMenu(@RequestBody Menu menu, @PathVariable int branchManagerId) {
		return service.saveMenu(menu,branchManagerId);
	}
}
