package com.OrderRecipe;

import javax.swing.JOptionPane;

import com.OrderRecipe.client.LoginFrame;;

public class Test {
    public static void main(String[] args) {
    	JOptionPane.showMessageDialog(null, "Copyright 2020 Bowen Shen, NUIST\nDo not change this dialog!", "版权声明", JOptionPane.INFORMATION_MESSAGE);
        new LoginFrame("顾客选桌").showMe();
    }
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。