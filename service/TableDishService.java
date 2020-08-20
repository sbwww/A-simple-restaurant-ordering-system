package com.OrderRecipe.service;

import java.util.List;

import com.OrderRecipe.dao.TableDishDao;
import com.OrderRecipe.dao.impl.TableDishDaoImpl;
import com.OrderRecipe.entity.Bill;
import com.OrderRecipe.entity.TableDish;

public class TableDishService {
	private TableDishDao tableDishDao;

	public TableDishService() {
		tableDishDao = new TableDishDaoImpl();
	}

	public void addTableDish(TableDish td) {
		tableDishDao.insert(td);
	}

	public void removeTableDish(TableDish td) {
		tableDishDao.delete(td);
	}

	public void modifyTableDish(TableDish td) {
	}

	public boolean find(TableDish td) {
		return tableDishDao.select(td);
	}

	public List<TableDish> findByTNo(int TNo) {
		return tableDishDao.selectByTNo(TNo);
	}

	public List<TableDish> findByDId(int DId) {
		return tableDishDao.selectByDId(DId);
	}

	public List<Bill> getBill(int TNo) {
		return tableDishDao.getBill(TNo);
	}
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。