package com.OrderRecipe.service;

import java.util.List;

import com.OrderRecipe.dao.FoodDao;
import com.OrderRecipe.dao.impl.FoodDaoImpl;
import com.OrderRecipe.entity.Food;

public class FoodService {
	private FoodDao foodDao;

	public FoodService() {
		foodDao = new FoodDaoImpl();
	}

	public void addFood(Food food) {
		foodDao.insert(food);
	}

	public void removeFood(String FName) {
	}

	public void modifyFood(int FId, Food food) {
	}

	public List<Food> findByName(String FName) {
		return foodDao.selectByName(FName);
	}

	public List<Food> findAll() {
		return foodDao.select();
	}

	public Food findById(int Fid) {
		return foodDao.selectById(Fid);
	}
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。