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

public class FindFoodFrame extends JFrame {

    private static final long serialVersionUID = 4134127437480439005L;
    
    private JLabel labelName = new JLabel("     食材名     ");
    private JTextField inputFoodName = new JTextField(15);
    private JButton buttonSearch = new JButton("  查询    ");

    private int windowHeight = 150; // 窗口高度
    private int windowWidth = 300; // 窗口宽度

    public FindFoodFrame(JFrame parent, String msg) {
        super(msg);
        System.out.println("find by food");
    }

    public void showMe(JFrame parent) {
        Panel pSouth = new Panel();
        pSouth.add(buttonSearch);

        Panel pCenter = new Panel();
        pCenter.add(labelName);
        pCenter.add(inputFoodName);
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
        buttonSearch.addActionListener(new ButtonSearchEmailHandler(parent));
    }

    private class ButtonSearchEmailHandler implements ActionListener { // 按菜名查询
        private JFrame parents;

        public ButtonSearchEmailHandler(JFrame parent) {
            parents = parent;
        }

        public void actionPerformed(ActionEvent e) {

            DishService ds = new DishService();
            FoodService fs = new FoodService();
            int FId;

            System.out.print("要查询包含哪个食材的菜品信息？输入食材名：");
            String foodName = inputFoodName.getText();
            if (foodName == null || foodName.length() == 0) {
                JOptionPane.showMessageDialog(null, "请输入要查询的食材名", "提示", JOptionPane.PLAIN_MESSAGE);
            } else {
                List<Food> selectedFoods = fs.findByName(foodName);
                Iterator<Food> itSelectedFoods = selectedFoods.iterator();
                List<Dish> resList = new ArrayList<Dish>();
                while (itSelectedFoods.hasNext()) {
                    Food selectedFood = itSelectedFoods.next();
                    
                    FId = selectedFood.getFId(); // 查找食材Id
                    System.out.println(selectedFood);
                    
                    DishFoodService dfs = new DishFoodService();
                    List<DishFood> dishFoods = dfs.findByFId(FId);
                    Iterator<DishFood> itDishFood = dishFoods.iterator();
                    while (itDishFood.hasNext()) {
                        DishFood df = itDishFood.next();
                        Dish dish=ds.findById(df.getDId());
                        System.out.println("\t" + dish);
                        resList.add(dish);
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