package com.cl.food_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.food_app.dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
