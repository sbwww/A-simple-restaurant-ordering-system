package com.OrderRecipe.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.OrderRecipe.entity.Bill;
import com.OrderRecipe.service.TableDishService;
import com.OrderRecipe.util.Parameters;

public class PayBillFrame extends JFrame {

    private static final long serialVersionUID = -1943949998199034110L;
    
    private JLabel labelTNo = new JLabel("      桌号      ");
    private JTextField tableTNo = new JTextField(15);

    private JButton buttonBill = new JButton("    结账    ");
    private JButton buttonExit = new JButton("    退出    ");

    private int windowHeight = 150; // 窗口高度
    private int windowWidth = 300; // 窗口宽度

    JPanel pTNo = new JPanel(); // 桌号
    private JPanel pButton = new JPanel(); // 按钮区面板，与修改功能共享
    private JPanel panel = new JPanel(); // 总面板，与修改功能共享
    
    public PayBillFrame(JFrame parent, String msg) {// 注册对话框
        super(msg);
        System.out.println("Show Bill");
    }

    // 设置布局
    private void init() {
        // TNo
        pTNo.add(labelTNo);
        pTNo.add(tableTNo);

        // 按钮
        pButton.add(buttonBill);
        pButton.add(buttonExit);

        // 添加至总面板
        panel.add(pTNo);
        panel.add(pButton);

        this.add(panel);
    }


    public void showMe(JFrame parent) {
        this.init(); // 设置窗口布局
        addEventHandler(parent);
        setPosition(parent);
        this.validate();
        setVisible(true);
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
        buttonBill.addActionListener(new ButtonBillHandler(parent));
        buttonExit.addActionListener(new ButtonExitHandler());
    }

    private class ButtonBillHandler implements ActionListener { // 结账按钮的事件监听器
        private JFrame parents;

        public ButtonBillHandler(JFrame parent) {
            parents = parent;
        }

        public void actionPerformed(ActionEvent e) {
            // 桌号
            String TNo = tableTNo.getText();

            if (TNo.length() == 0) {
                JOptionPane.showMessageDialog(null, "请输入桌号", "提示", JOptionPane.PLAIN_MESSAGE);
                tableTNo.grabFocus();
            } else if (!TNo.matches("[0-9_+\\.-]")) {
                JOptionPane.showMessageDialog(null, "桌号格式有误", "提示", JOptionPane.PLAIN_MESSAGE);
                tableTNo.setText("");
                tableTNo.grabFocus();
            } else {
                TableDishService tds = new TableDishService();
                List<Bill> tableBill = tds.getBill(Integer.parseInt(TNo));
                Iterator<Bill> itTableBill = tableBill.iterator();
                int totalPrice = 0;
                while (itTableBill.hasNext()) {
                    Bill b = itTableBill.next();
                    totalPrice = totalPrice + b.getDPrice() * b.getTDNum();
                    System.out.println(b.toString());
                }
                JOptionPane.showMessageDialog(null, "\u7533\u535a\u6587，请付款" + totalPrice + "元，谢谢惠顾");
                // new ShowDataTableFrame(parents, "查询结果", tableBill, Parameters.BILL).showMe(parents);
                System.out.println("Total: " + totalPrice);
            }
            clear();
        }
    }

    private class ButtonExitHandler implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            dispose();
        }
    }

    /**
     * 清空组件
     */
    private void clear() {
        tableTNo.setText("");
    }

}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。