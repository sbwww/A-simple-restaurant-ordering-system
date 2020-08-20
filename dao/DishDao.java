package com.OrderRecipe.dao;

import java.util.List;

import com.OrderRecipe.entity.Dish;

public interface DishDao {

	public void insert(Dish dish);

	public void delete(int DId);

	public void update(int DId, Dish dish);

	public List<Dish> select();

	public List<Dish> selectByName(String DName);

	public Dish selectById(int DId);
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。