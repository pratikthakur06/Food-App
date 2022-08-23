package com.cl.food_app.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.food_app.dto.Item;
import com.cl.food_app.repository.ItemRepository;

@Repository
public class ItemDao {

	@Autowired
	private ItemRepository repository;
	
	public Item saveItem(Item item) {
		return repository.save(item);
	}
	
	public Optional<Item> getItemById(int id) {
		return repository.findById(id);
	}
}
