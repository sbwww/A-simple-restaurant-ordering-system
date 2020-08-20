package com.OrderRecipe.dao;

import java.util.List;

import com.OrderRecipe.entity.Table;

public interface TableDao {

	public void insert(Table table);

	public void delete(int TNo);

	public void update(int TNo, Table table);

	public List<Table> select();

	public List<Table> selectByAvl();

	public Table selectByNo(int TNo);

	public int tableCheck(int TNo, String TPass);
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。