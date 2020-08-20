package com.OrderRecipe.client;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.OrderRecipe.dao.TableDao;
import com.OrderRecipe.dao.impl.TableDaoImpl;
import com.OrderRecipe.entity.Table;
import com.OrderRecipe.service.TableService;
import com.OrderRecipe.util.Parameters;

public class LoginFrame extends JFrame implements WindowListener {

    private static final long serialVersionUID = -8506998315222305815L;

    private JLabel labelTNo = new JLabel("      桌号      ");
    private JTextField inputTNo = new JTextField(15);
    private JLabel labelTPass = new JLabel("  桌识别码  ");
    private JTextField inputTPass = new JPasswordField(15);
    private JButton buttonSearch = new JButton("    查询    ");
    private JButton buttonPick = new JButton("    选桌    ");
    private JButton buttonExit = new JButton("    退出    ");

    private int windowHeight = 150; // 窗口高度
    private int windowWidth = 300; // 窗口宽度

    public static void main(String[] args) {
        new LoginFrame("选桌").showMe();
    }

    public LoginFrame(String msg) {
        super(msg);
        System.out.println("login");
    }

    public void showMe() {
        this.setBounds(500, 300, windowWidth, windowHeight);

        Panel pCenter = new Panel();
        pCenter.add(labelTNo);
        pCenter.add(inputTNo);
        pCenter.add(labelTPass);
        pCenter.add(inputTPass);

        Panel pSouth = new Panel();
        pSouth.add(buttonSearch);
        pSouth.add(buttonPick);
        pSouth.add(buttonExit);
        pCenter.add(pSouth);

        addEventHandler();

        add(pCenter);

        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void addEventHandler() {
        buttonSearch.addActionListener(new ButtonSearchHandler());
        buttonPick.addActionListener(new ButtonPickTableHandler());
        buttonExit.addActionListener(new ButtonExitHandler());
    }

    private class ButtonPickTableHandler implements ActionListener { // 选桌

        public void actionPerformed(ActionEvent e) {
            int TNo = Integer.parseInt(inputTNo.getText());
            String TPass = inputTPass.getText();
            TableDao tablequery = new TableDaoImpl();
            int stat = tablequery.tableCheck(TNo, TPass);

            if (stat == Parameters.TABLE_SUCCESS) {
                new Menu(TNo);
                LoginFrame.this.setVisible(false);
            } else if (stat == Parameters.WRONG_PASS) {
                JOptionPane.showMessageDialog(null, "\u7533\u535a\u6587您好，验证错误！", "错误", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "\u7533\u535a\u6587您好，该桌正在使用中！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ButtonSearchHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TableService ts = new TableService();
            List<Table> availableTable = ts.findByAvl();
            System.out.println(availableTable.size());
            new ShowDataTableFrame(LoginFrame.this, "查询结果", availableTable, Parameters.TABLE).showMe(LoginFrame.this);
        }
    }

    public void windowClosing(WindowEvent e) { // 实现窗口关闭事件
        int result = JOptionPane.showConfirmDialog(null, "确定要退出吗？", "系统消息", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }

    private class ButtonExitHandler implements ActionListener { // 按菜名查询

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public void windowClosed(WindowEvent e) {
    } // 实现window事件

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }

    public void keyTyped(KeyEvent arg0) {
    }// 实现keyboard事件

    public void keyPressed(KeyEvent argo) {
    }

    public void keyReleased(KeyEvent arg0) {
    }
}--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。