package com.cl.food_app.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.food_app.dto.Menu;
import com.cl.food_app.repository.MenuRepository;

@Repository
public class MenuDao {

	@Autowired
	private MenuRepository repository;
	
	public Menu saveMenu(Menu menu) {
		return repository.save(menu);
	}
	
	public Optional<Menu> getMenuById(int id) {
		return repository.findById(id);
	}
}
