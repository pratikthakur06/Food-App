package com.cl.food_app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.food_app.dto.Item;
import com.cl.food_app.dto.Menu;
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

	public Item updateItem(Item item, int id) {
		if (repository.findById(id).isEmpty()) {
			return null;
		} else {
			item.setId(id);
			Menu menu = getItemById(id).get().getMenu();
			item.setMenu(menu);
			return repository.save(item);
		}
	}

	public Item deleteItem(int id) {
		Item item = getItemById(id).get();
		repository.delete(item);
		return item;
	}

	public List<Item> findAllItem() {
		return repository.findAll();
	}
}
