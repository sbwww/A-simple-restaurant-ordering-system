package com.OrderRecipe.util;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.OrderRecipe.entity.Dish;

public class DishTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 6458480131162770750L;
	
	private List<Dish> dishes;

	public DishTableModel(List<Dish> dishes) {
		this.dishes = dishes;
	}

	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return dishes.size();
	}

	public Object getValueAt(int row, int col) { // 按指定的行、列取出数据
		Dish dish = (Dish) dishes.get(row);
		switch (col) {
			case 0:
				return dish.getDId() + ""; // 菜号
			case 1:
				return dish.getDName() + ""; // 菜名
			case 2:
				return dish.getDPrice() + ""; // 价格
			case 3:
				return dish.getDDetail() + ""; // 详情
			default:
				break;
		}
		return null;
	}

	public String getColumnName(int col) {
		switch (col) {
			case 0:
				return "ID";
			case 1:
				return "菜名";
			case 2:
				return "价格";
			case 3:
				return "详情";
			default:
				break;
		}
		return null;
	}
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。