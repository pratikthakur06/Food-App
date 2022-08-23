package com.cl.food_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cl.food_app.dao.MenuDao;
import com.cl.food_app.dto.BranchManager;
import com.cl.food_app.dto.Menu;
import com.cl.food_app.dto.Staff;

@Service
public class MenuService {

	@Autowired
	private MenuDao dao;
	
	@Autowired
	private BranchManagerService branchManagerService;
	
	public Menu saveMenu(Menu menu, int branchManagerId) {
		BranchManager branchManager = branchManagerService.getBranchManagerById(branchManagerId);
		menu.setBranchManager(branchManager);
		return dao.saveMenu(menu);
	}
	
	public Menu getMenuById(int id) {
		return dao.getMenuById(id).get();
	}
}
