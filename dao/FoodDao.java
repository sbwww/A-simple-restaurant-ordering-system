package com.OrderRecipe.dao;

import java.util.List;

import com.OrderRecipe.entity.Food;

public interface FoodDao {

	public void insert(Food food);

	public void delete(int FId);

	public void update(int FId, Food food);

	public List<Food> select();

	public List<Food> selectByName(String FName);

	public Food selectById(int FId);
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。