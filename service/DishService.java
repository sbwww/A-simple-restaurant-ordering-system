package com.OrderRecipe.service;

import java.util.List;

import com.OrderRecipe.dao.DishDao;
import com.OrderRecipe.dao.impl.DishDaoImpl;
import com.OrderRecipe.entity.Dish;

public class DishService {
	private DishDao dishDao;

	public DishService() {
		dishDao = new DishDaoImpl();
	}

	public void addDish(Dish dish) {
		dishDao.insert(dish);
	}

	public void removeDish(String DName) {
	}

	public void modifyDish(int DId, Dish dish) {
	}

	public List<Dish> findByName(String DName) {
		return dishDao.selectByName(DName);
	}

	public List<Dish> findAll() {
		return dishDao.select();
	}

	public Dish findById(int DId) {
		return dishDao.selectById(DId);
	}
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。