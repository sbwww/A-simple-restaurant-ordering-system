package com.OrderRecipe.util;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.OrderRecipe.entity.Food;

public class FoodTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8945079030097841657L;
	
	private List<Food> foods;

	public FoodTableModel(List<Food> foods) {
		this.foods = foods;
	}

	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return foods.size();
	}

	public Object getValueAt(int row, int col) { // 按指定的行、列取出数据
		Food food = (Food) foods.get(row);
		switch (col) {
			case 0:
				return food.getFId() + ""; // 食材号
			case 1:
				return food.getFName() + ""; // 食材名
			case 2:
				return food.getFType() + ""; // 种类
			case 3:
				return food.getFDetail() + ""; // 详情
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
				return "食材名";
			case 2:
				return "种类";
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