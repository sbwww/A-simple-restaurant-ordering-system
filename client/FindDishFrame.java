package com.OrderRecipe.client;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.OrderRecipe.entity.Dish;
import com.OrderRecipe.entity.DishFood;
import com.OrderRecipe.entity.Food;
import com.OrderRecipe.service.DishFoodService;
import com.OrderRecipe.service.DishService;
import com.OrderRecipe.service.FoodService;
import com.OrderRecipe.util.Parameters;

public class FindDishFrame extends JFrame {

    private static final long serialVersionUID = 4475925970848354206L;

    private JLabel labelName = new JLabel("      菜名      ");
    private JTextField inputDishName = new JTextField(15);
    private JButton buttonSearch = new JButton("  查询    ");

    private int windowHeight = 150; // 窗口高度
    private int windowWidth = 300; // 窗口宽度

    public FindDishFrame(JFrame parent, String msg) {
        super(msg);
        System.out.println("find dish");
    }

    public void showMe(JFrame parent) {
        Panel pSouth = new Panel();
        pSouth.add(buttonSearch);

        Panel pCenter = new Panel();
        pCenter.add(labelName);
        pCenter.add(inputDishName);
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
    }

    private class ButtonSearchDishHandler implements ActionListener { // 按菜名查询
        private JFrame parents;

        public ButtonSearchDishHandler(JFrame parent) {
            parents = parent;
        }

        public void actionPerformed(ActionEvent e) {

            DishService ds = new DishService();
            FoodService fs = new FoodService();
            int DId;

            System.out.println("要查询哪个菜品具有的食材信息？输入菜品名：");
            String dishName = inputDishName.getText();
            if (dishName == null || dishName.length() == 0) {
                JOptionPane.showMessageDialog(null, "请输入要查询的菜名", "提示", JOptionPane.PLAIN_MESSAGE);
            } else {
                List<Dish> selectedDishes = ds.findByName(dishName);
                Iterator<Dish> itSelectedDishes = selectedDishes.iterator();
                List<Dish> resList = new ArrayList<Dish>();
                while (itSelectedDishes.hasNext()) {
                    Dish selectedDish = itSelectedDishes.next();
                    resList.add(selectedDish);

                    DId = selectedDish.getDId(); // 查找菜品Id
                    System.out.println(selectedDish);

                    DishFoodService dfs = new DishFoodService();
                    List<DishFood> dishFoods = dfs.findByDId(DId);
                    Iterator<DishFood> itDishFood = dishFoods.iterator();
                    while (itDishFood.hasNext()) {
                        DishFood df = itDishFood.next();
                        Food food = fs.findById(df.getFId());
                        System.out.println("\t" + food);
                    }
                }
                new ShowDataTableFrame(parents, "查询结果", resList, Parameters.DISH).showMe(parents);
            }
        }
    }
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。