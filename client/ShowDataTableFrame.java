package com.OrderRecipe.client;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.OrderRecipe.util.BillTableModel;
import com.OrderRecipe.util.DishTableModel;
import com.OrderRecipe.util.FoodTableModel;
import com.OrderRecipe.util.PaginationUtil;
import com.OrderRecipe.util.Parameters;
import com.OrderRecipe.util.TableTableModel;

public class ShowDataTableFrame extends JFrame {

	private static final long serialVersionUID = 590240088840872285L;
	
	private int windowHeight = 300; // 窗口高度
	private int windowWidth = 450; // 窗口宽度
	private JTable table = new JTable();
	private PaginationUtil pagesUtil; // 计算分页的工具类对象
	private int type;
	private String colName[][] = { { "ID", "菜名", "价格", "详情" }, { "ID", "食材名", "种类", "详情" }, { "No", "空闲情况" },
			{ "菜名", "单价", "数量", "小计" } };
	private JLabel pageMessage = new JLabel();
	private JButton previous = new JButton("上一页");
	private JButton next = new JButton("下一页");

	public ShowDataTableFrame(JFrame parent, String msg, List list, int type) {
		super("列表");
		this.type = type;
		pagesUtil = new PaginationUtil(list, 10);
	}

	// 设置布局
	private void init() {
		// 总面板
		Panel panel = new Panel(new BorderLayout());

		// 1.添加表格部分
		// (1)显示第一页
		showTableData(1);
		// (2)将JTable加入滚动条面板
		JScrollPane pane = new JScrollPane(table);
		// (3)将滚动条面板加入总面板
		panel.add(pane, BorderLayout.CENTER);

		// 2.添加按钮部分
		Panel southPanel = new Panel();
		southPanel.add(pageMessage);
		southPanel.add(previous);
		southPanel.add(next);
		panel.add(southPanel, BorderLayout.SOUTH);

		this.add(panel);
	}

	// 显示指定页，控制按钮是否可用
	private void showTableData(int pageNo) {
		// 1.获取pageNo页对应的记录集合
		List currentPageRecords = pagesUtil.getCurrentPageRecords(pageNo);
		switch (type) {
			case Parameters.DISH:
				// 2.利用currentPageRecords创建TableModel
				DishTableModel modelDish = new DishTableModel(currentPageRecords);
				table.setModel(modelDish);
				break;
			case Parameters.FOOD:
				FoodTableModel modelFood = new FoodTableModel(currentPageRecords);
				table.setModel(modelFood);
				break;
			case Parameters.TABLE:
				TableTableModel modelTable = new TableTableModel(currentPageRecords);
				table.setModel(modelTable);
				break;
			case Parameters.BILL:
				BillTableModel modelBill = new BillTableModel(currentPageRecords);
				table.setModel(modelBill);
				break;
			default:
				break;
		}
		// 3.为JTable设置TableModel：指定行、列信息

		// 4.设置JTable的列宽
		for (int i = 1; i <= colName[this.type - 1].length; ++i) {
			table.getColumn(colName[this.type - 1][i - 1]).setPreferredWidth(100);
		}

		// 5.关闭JTable的自动调整功能
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// 6.根据当前页的情况设置"上一页"、"下一页"按钮是否可用
		int currentPage = pagesUtil.getCurrentPage();
		int totalPage = pagesUtil.getTotalPage();
		if (currentPage == 1) {
			previous.setEnabled(false);
		}
		if (currentPage == totalPage) {
			next.setEnabled(false);
		}
		pageMessage.setText("第" + currentPage + "页 共" + totalPage + "页  ");
	}

	public void showMe(JFrame parent) {
		this.init(); // 设置窗口布局
		addEventHandler();
		setPosition(parent);
		setVisible(true);
		validate();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void addEventHandler() {
		next.addActionListener(new ButtonNextHandler());
		previous.addActionListener(new ButtonPreviousHandler());
	}

	private class ButtonNextHandler implements ActionListener { // 下一页
		public void actionPerformed(ActionEvent e) {
			pagesUtil.setCurrentPage(pagesUtil.getNextPage());
			previous.setEnabled(true);
			showTableData(pagesUtil.getCurrentPage());
		}
	}

	private class ButtonPreviousHandler implements ActionListener { // 上一页
		public void actionPerformed(ActionEvent e) {
			pagesUtil.setCurrentPage(pagesUtil.getPrePage());
			next.setEnabled(true);
			showTableData(pagesUtil.getCurrentPage());
		}
	}

	private void setPosition(JFrame parent) {
		// 计算对话框的显示位置
		int parentX = parent.getX();
		int parentY = parent.getY();
		int parentWidth = parent.getWidth();
		int parentHeight = parent.getHeight();
		int dialogX = parentX + (parentWidth - windowWidth) / 2;
		int dialogY = parentY + (parentHeight - windowHeight) / 2 + 40;
		this.setBounds(dialogX, dialogY, windowWidth, windowHeight);
	}
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。