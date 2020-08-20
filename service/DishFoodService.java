package com.OrderRecipe.service;

import java.util.List;

import com.OrderRecipe.dao.DishFoodDao;
import com.OrderRecipe.dao.impl.DishFoodDaoImpl;
import com.OrderRecipe.entity.DishFood;

public class DishFoodService {
	private DishFoodDao dishFoodDao;

	public DishFoodService() {
		dishFoodDao = new DishFoodDaoImpl();
	}

	public void addDishFood(DishFood df) {
		dishFoodDao.insert(df);
	}

	public boolean find(DishFood sc) {
		return dishFoodDao.select(sc);
	}

	public List<DishFood> findByDId(int DId) {
		return dishFoodDao.selectByDId(DId);
	}

	public List<DishFood> findByFId(int FId) {
		return dishFoodDao.selectByFId(FId);
	}
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。