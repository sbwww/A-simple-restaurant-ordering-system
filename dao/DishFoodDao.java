package com.OrderRecipe.dao;

import java.util.List;

import com.OrderRecipe.entity.DishFood;

public interface DishFoodDao {

	public void insert(DishFood df);

	public void delete(int DId,int FId);
	
	public void update(int DId, int FId, DishFood df);

	public boolean select(DishFood df);

	public List<DishFood> selectByDId(int DId);

	public List<DishFood> selectByFId(int FId);
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。