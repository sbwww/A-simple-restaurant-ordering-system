package com.OrderRecipe.client;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.OrderRecipe.entity.TableDish;
import com.OrderRecipe.service.DishService;
import com.OrderRecipe.service.TableDishService;
import com.OrderRecipe.service.TableService;

public class AddOrderFrame extends JFrame {

    private static final long serialVersionUID = 2120726638526296288L;

    private JLabel labelTNo = new JLabel("      桌号      ");
    private JTextField inputTNo = new JTextField(15);
    private JLabel labelDId = new JLabel("  菜品编号  ");
    private JTextField inputDId = new JTextField(15);
    private JButton buttonSearch = new JButton("    查询    ");
    private JButton buttonOrder = new JButton("    添加    ");

    private int windowHeight = 150; // 窗口高度
    private int windowWidth = 300; // 窗口宽度

    private int TNo;

    public AddOrderFrame(JFrame parent, int TNo, String msg) {
        super(msg);
        this.TNo = TNo;
        System.out.println("add order");
    }

    public void showMe(JFrame parent) {

        Panel pCenter = new Panel();
        pCenter.add(labelDId);
        pCenter.add(inputDId);
        pCenter.add(labelTNo);
        pCenter.add(inputTNo);
        inputTNo.setText(TNo+"");
        inputTNo.setEditable(false);

        Panel pSouth = new Panel();
        pSouth.add(buttonSearch);
        pSouth.add(buttonOrder);
        pCenter.add(pSouth);

        addEventHandler(parent);

        add(pCenter);

        setPosition(parent);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

    public void addEventHandler(JFrame parent) {
        buttonSearch.addActionListener(new ButtonSearchDishHandler(parent));
        buttonOrder.addActionListener(new ButtonOrderDishHandler(parent));
    }

    private class ButtonSearchDishHandler implements ActionListener { // 按菜名查询

        public ButtonSearchDishHandler(JFrame parent) {
        }

        public void actionPerformed(ActionEvent e) {
            new FindDishFrame(AddOrderFrame.this, "查询菜品").showMe(AddOrderFrame.this);
        }
    }

    private class ButtonOrderDishHandler implements ActionListener { // 按菜名查询
        public ButtonOrderDishHandler(JFrame parent) {
        }

        public void actionPerformed(ActionEvent e) {
            TableService ts = new TableService();
            DishService ds = new DishService();

            int TNo = Integer.parseInt(inputTNo.getText());

            if (ts.findByNo(TNo) == null) {
                System.out.println("没有该桌");
            } else {
                System.out.print("输入菜品编号");
                int DId = Integer.parseInt(inputDId.getText());
                if (ds.findById(DId) == null) {
                    System.out.println("没有该菜品");
                } else {
                    TableDish td = new TableDish(TNo, DId, 0);
                    TableDishService tds = new TableDishService();
                    tds.addTableDish(td);
                    System.out.println("添加订单成功");
                }
            }
            JOptionPane.showMessageDialog(null, "添加成功");
        }
    }
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。