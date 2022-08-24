package com.cl.food_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.food_app.dao.BranchManagerDao;
import com.cl.food_app.dao.MenuDao;
import com.cl.food_app.dto.BranchManager;
import com.cl.food_app.dto.Menu;
import com.cl.food_app.exception.IdNotFoundException;
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
		Menu menu = dao.getMenuById(id).get();
		if (menu == null) {
			throw new IdNotFoundException();
		} else {
			ResponseStructure<Menu> structure = new ResponseStructure<Menu>();
			structure.setMessage("Menu Found Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(menu);
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
			structure.setMessage("ID is not valid");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Menu>> deleteMenu(int id) {
		ResponseStructure<Menu> structure = new ResponseStructure<Menu>();
		Menu menu = dao.getMenuById(id).get();
		if (menu != null) {
			structure.setMessage("Menu Deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(dao.deleteMenu(id));
			return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException();
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
