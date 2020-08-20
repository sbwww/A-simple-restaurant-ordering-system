package com.OrderRecipe.util;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.OrderRecipe.entity.Bill;

public class BillTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 3062159754795490066L;
	
	private List<Bill> bills;

	public BillTableModel(List<Bill> bills) {
		this.bills = bills;
	}

	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return bills.size();
	}

	public Object getValueAt(int row, int col) { // 按指定的行、列取出数据
		Bill bill = (Bill) bills.get(row);
		switch (col) {
			case 0:
				return bill.getDName() + ""; // 菜名
			case 1:
				return bill.getDPrice() + ""; // 价格
			case 2:
				return bill.getTDNum() + ""; // 数量
			case 3:
				return bill.getDPrice() * bill.getTDNum() + ""; // 小计
			default:
				break;
		}
		return null;
	}

	public String getColumnName(int col) {
		switch (col) {
			case 0:
				return "菜名";
			case 1:
				return "单价";
			case 2:
				return "数量";
			case 3:
				return "小计";
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