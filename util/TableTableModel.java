package com.OrderRecipe.util;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.OrderRecipe.entity.Table;

public class TableTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -2712803218994823697L;

	private List<Table> tables;

	public TableTableModel(List<Table> tables) {
		this.tables = tables;
	}

	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return tables.size();
	}

	public Object getValueAt(int row, int col) { // 按指定的行、列取出数据
		Table table = (Table) tables.get(row);
		switch (col) {
			case 0:
				return table.getTNo() + ""; // 桌号
			case 1:
				if (table.getTAvl()==1)
					return "YES";
				return "NO";
				// return table.getTAvl() + ""; // 空闲情况
			default:
				break;
		}
		return null;
	}

	public String getColumnName(int col) {
		switch (col) {
			case 0:
				return "No";
			case 1:
				return "空闲情况";
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