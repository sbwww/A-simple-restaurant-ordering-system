package com.OrderRecipe.dao;

import java.util.List;

import com.OrderRecipe.entity.Bill;
import com.OrderRecipe.entity.TableDish;

public interface TableDishDao {

    public void insert(TableDish td);

    public void delete(TableDish td);

    public void update(TableDish td);

    public boolean select(TableDish td);

    public List<TableDish> selectByTNo(int TNo);

    public List<TableDish> selectByDId(int DId);

	public List<Bill> getBill(int TNo);

}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。