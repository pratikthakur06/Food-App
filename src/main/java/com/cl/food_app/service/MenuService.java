package com.cl.food_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.food_app.dao.BranchManagerDao;
import com.cl.food_app.dao.MenuDao;
import com.cl.food_app.dto.BranchManager;
import com.cl.food_app.dto.Menu;
import com.cl.food_app.exception.IdNotFoundException;
import com.cl.food_app.exception.MenuNotFoundException;
import com.cl.food_app.util.ResponseStructure;

@Service
public class MenuService {

	@Autowired
	private MenuDao dao;

	@Autowired
	private BranchManagerDao branchManagerDao;

	public ResponseEntity<ResponseStructure<Menu>> saveMenu(Menu menu, int branchManagerId) {
		BranchManager branchManager = branchManagerDao.getBranchManagerById(branchManagerId).get();
		menu.setBranchManager(branchManager);
		ResponseStructure<Menu> structure = new ResponseStructure<Menu>();
		structure.setMessage("Menu Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveMenu(menu));
		return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Menu>> getMenuById(int id) {
		Optional<Menu> menu = dao.getMenuById(id);
		if (menu.isEmpty()) {
			throw new MenuNotFoundException(id);
		} else {
			ResponseStructure<Menu> structure = new ResponseStructure<Menu>();
			structure.setMessage("Menu Found Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(menu.get());
			return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<Menu>> updateMenu(Menu menu, int id) {
		ResponseStructure<Menu> structure = new ResponseStructure<Menu>();
		Menu menu2 = dao.updateMenu(menu, id);
		if (menu2 != null) {
			structure.setMessage("Menu Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.saveMenu(menu2));
			return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);
		} else {
			throw new MenuNotFoundException(id);
		}
	}

	public ResponseEntity<ResponseStructure<Menu>> deleteMenu(int id) {
		ResponseStructure<Menu> structure = new ResponseStructure<Menu>();
		Optional<Menu> menu = dao.getMenuById(id);
		if (menu.isPresent()) {
			structure.setMessage("Menu Deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.deleteMenu(id));
			return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);
		} else {
			throw new MenuNotFoundException(id);
		}
	}

	public ResponseEntity<ResponseStructure<List<Menu>>> findAllMenu() {
		ResponseStructure<List<Menu>> structure = new ResponseStructure<List<Menu>>();
		structure.setMessage("Menu Found Successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllMenu());
		return new ResponseEntity<ResponseStructure<List<Menu>>>(structure, HttpStatus.OK);
	}
}
