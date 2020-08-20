package com.OrderRecipe.service;

import java.util.List;

import com.OrderRecipe.dao.TableDao;
import com.OrderRecipe.dao.impl.TableDaoImpl;
import com.OrderRecipe.entity.Table;

public class TableService {
	private TableDao tableDao;

	public TableService() {
		tableDao = new TableDaoImpl();
	}

	public void addTable(Table table) {
		tableDao.insert(table);
	}

	public void removeTable(String TName) {
	}

	public void modifyTable(int TNo, Table table) {
	}

    public List<Table> findByAvl() {
		return tableDao.selectByAvl();
	}

	public List<Table> findAll() {
		return tableDao.select();
	}

	public Table findByNo(int TNo) {
		return tableDao.selectByNo(TNo);
	}

	public int tableCheck(int TNo, String TPass){
		return tableDao.tableCheck(TNo, TPass);
	}

}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。