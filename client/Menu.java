package com.OrderRecipe.client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class Menu extends JFrame {

    private static final long serialVersionUID = 5948817055766223953L;

    private JMenuItem jmiShowBill, jmiAllDish, jmiFindDish, jmiFindFood, jmiAddDish, jmiRemoveDish, jmiPayBill;

    private JButton btnShowBill, btnAllDish, btnAddDish, btnRemoveDish;

    private int TNo;

    public static void main(String[] args) throws IOException {
        new Menu(1);
    }

    public Menu(int TNo) {
        this.setTNo(TNo);
        // 设置窗口对象
        this.init();
        this.addEventHandler();
        this.setTitle("\u7533\u535a\u6587 面向顾客的餐厅点餐系统");
        this.setBounds(300, 200, 800, 400);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createMenuBar() {

        JMenuBar menuBar = new JMenuBar(); // 创建菜单栏

        JMenu billMenu = new JMenu("账单(N)"); // 创建菜单
        billMenu.setMnemonic(KeyEvent.VK_N); // 设置热键
        billMenu.add(jmiShowBill = new JMenuItem("显示账单")); // 创建、并向菜单添加菜单项
        billMenu.add(jmiPayBill = new JMenuItem("确认结账")); // 创建、并向菜单添加菜单项
        menuBar.add(billMenu); // 将菜单添加到菜单栏

        JMenu searchMenu = new JMenu("查询菜单(E)");
        searchMenu.setMnemonic(KeyEvent.VK_E);
        searchMenu.add(jmiAllDish = new JMenuItem("所有菜品"));
        searchMenu.add(jmiFindDish = new JMenuItem("查询菜品"));
        searchMenu.add(jmiFindFood = new JMenuItem("查询食材"));
        menuBar.add(searchMenu);

        JMenu addMenu = new JMenu("增加订单(S)");
        addMenu.setMnemonic(KeyEvent.VK_S);
        addMenu.add(jmiAddDish = new JMenuItem("增加订单"));
        menuBar.add(addMenu);

        JMenu removeMenu = new JMenu("删除订单(L)");
        removeMenu.setMnemonic(KeyEvent.VK_L);
        removeMenu.add(jmiRemoveDish = new JMenuItem("删除订单"));
        menuBar.add(removeMenu);

        this.setJMenuBar(menuBar);
    }

    private void createToolBar() {
        JToolBar toolBar = new JToolBar(); // 创建工具栏

        btnShowBill = new JButton("", new ImageIcon(this.getClass().getResource("../ico/bill.PNG")));
        btnShowBill.setToolTipText("显示账单");

        btnAllDish = new JButton("", new ImageIcon(this.getClass().getResource("../ico/find.PNG")));
        btnAllDish.setToolTipText("所有菜品");

        btnAddDish = new JButton("", new ImageIcon(this.getClass().getResource("../ico/add.PNG")));
        btnAddDish.setToolTipText("增加订单");

        btnRemoveDish = new JButton("", new ImageIcon(this.getClass().getResource("../ico/delete.PNG")));
        btnRemoveDish.setToolTipText("删除订单");

        toolBar.add(btnShowBill);
        toolBar.add(btnAllDish);
        toolBar.add(btnAddDish);
        toolBar.add(btnRemoveDish);

        // 将工具栏添加至JFrame，this为当前窗口对象
        this.add("North", toolBar);
    }

    private void init() {
        // 1.创建菜单栏
        createMenuBar();

        // 2.创建工具栏
        createToolBar();

        // 3.按钮面板
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 400));
        this.add("Center", panel);

        ImageIcon img = new ImageIcon(".\\src\\com\\OrderRecipe\\ico\\bg.png"); // 背景图设置
        JLabel imgLabel = new JLabel(img);
        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
        imgLabel.setBounds(200, 60, img.getIconWidth(), img.getIconHeight()); // 设置背景标签位置
        Container cp = this.getContentPane();
        cp.setLayout(new BorderLayout());
        ((JPanel) cp).setOpaque(false);

    }

    public void addEventHandler() {
        jmiShowBill.addActionListener(new ShowBillHandler());
        jmiPayBill.addActionListener(new PayBillHandler());
        jmiAllDish.addActionListener(new AllDishHandler());
        jmiFindDish.addActionListener(new FindDishHandler());
        jmiFindFood.addActionListener(new FindFoodHandler());
        jmiAddDish.addActionListener(new AddDishHandler());
        jmiRemoveDish.addActionListener(new RemoveDishHandler());

        btnShowBill.addActionListener(new ShowBillHandler());
        btnAllDish.addActionListener(new AllDishHandler());
        btnAddDish.addActionListener(new AddDishHandler());
        btnRemoveDish.addActionListener(new RemoveDishHandler());
    }

    private class ShowBillHandler implements ActionListener { // 显示账单
        public void actionPerformed(ActionEvent events) {
            new ShowBillFrame(Menu.this, TNo, "显示账单").showMe(Menu.this);
        }
    }

    private class PayBillHandler implements ActionListener { // TODO 结账
        public void actionPerformed(ActionEvent events) {
            new PayBillFrame(Menu.this, "结账").showMe(Menu.this);
        }
    }

    private class AllDishHandler implements ActionListener { // 查询菜品
        public void actionPerformed(ActionEvent events) {
            new AllDishFrame(Menu.this, "查询菜品").showMe(Menu.this);
        }
    }

    private class FindDishHandler implements ActionListener { // 查询菜品
        public void actionPerformed(ActionEvent events) {
            new FindDishFrame(Menu.this, "查询菜品").showMe(Menu.this);
        }
    }

    private class FindFoodHandler implements ActionListener { // 查询食材
        public void actionPerformed(ActionEvent events) {
            new FindFoodFrame(Menu.this, "按食材查询").showMe(Menu.this);
        }
    }

    private class AddDishHandler implements ActionListener { // 增加订单
        public void actionPerformed(ActionEvent events) {
            new AddOrderFrame(Menu.this, TNo, "增加订单").showMe(Menu.this);
        }
    }

    private class RemoveDishHandler implements ActionListener { // 删除订单
        public void actionPerformed(ActionEvent events) {
            new DeleteOrderFrame(Menu.this, TNo, "删除订单").showMe(Menu.this);
        }
    }

    public int getTNo() {
        return TNo;
    }

    public void setTNo(int tNo) {
        TNo = tNo;
    }

}--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。