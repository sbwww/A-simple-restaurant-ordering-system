package com.OrderRecipe.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

import com.OrderRecipe.entity.Dish;
import com.OrderRecipe.entity.DishFood;
import com.OrderRecipe.entity.Food;
import com.OrderRecipe.service.DishFoodService;
import com.OrderRecipe.service.DishService;
import com.OrderRecipe.service.FoodService;
import com.OrderRecipe.util.Parameters;

public class AllDishFrame extends JFrame {

    private static final long serialVersionUID = -2750810188351109554L;

    private int windowHeight = 150; // 窗口高度
    private int windowWidth = 300; // 窗口宽度

    public AllDishFrame(JFrame parent, String msg) {
        super(msg);
        System.out.println("find dish");

        DishService ds = new DishService();
        FoodService fs = new FoodService();
        int DId;

        List<Dish> selectedDishes = ds.findAll();
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
        new ShowDataTableFrame(parent, "查询结果", resList, Parameters.DISH).showMe(parent);
    }

    public void showMe(JFrame parent) {
        
        setPosition(parent);
        setVisible(false);
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

}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。