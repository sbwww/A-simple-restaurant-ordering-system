package com.OrderRecipe.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.OrderRecipe.dao.FoodDao;
import com.OrderRecipe.entity.Food;
import com.OrderRecipe.util.ConnectionFactory;

public class FoodDaoImpl implements FoodDao {

	public void insert(Food food) {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = ConnectionFactory.getConnection();
			String sql = "insert into food values(NULL,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, food.getFName());
			pst.setInt(2, food.getFRes());
			pst.setString(3, food.getFType());
			pst.setString(4, food.getFDetail());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(null, pst, con);
		}
	}

	public void delete(int FId) {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = ConnectionFactory.getConnection();
			String sql = "delete from food where FId=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, FId);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(null, pst, con);
		}
	}

	public void update(int FId, Food food) {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = ConnectionFactory.getConnection();
			String sql = "update food set FName=?, FRes=?, FType=?, FDetail=? where FId=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, food.getFName());
			pst.setInt(2, food.getFRes());
			pst.setString(3, food.getFType());
			pst.setString(4, food.getFDetail());
			pst.setInt(5, FId);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(null, pst, con);
		}
	}

	public List<Food> select() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Food> list = new ArrayList<Food>();
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select * from food";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery(sql);
			while (rs.next()) {
				Food food = new Food();
				food.setFId(rs.getInt("FId"));
				food.setFName(rs.getString("FName"));
				food.setFRes(rs.getInt("FRes"));
				food.setFType(rs.getString("FType"));
				food.setFDetail(rs.getString("FDetail"));
				list.add(food);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs, pst, con);
		}
		return list;
	}

	public List<Food> selectByName(String FName) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Food> list = new ArrayList<Food>();
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select * from food where FName like '%" + FName + "%'";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Food food = new Food();
				food.setFId(rs.getInt("FId"));
				food.setFName(rs.getString("FName"));
				food.setFRes(rs.getInt("FRes"));
				food.setFType(rs.getString("FType"));
				food.setFDetail(rs.getString("FDetail"));
				list.add(food);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs, pst, con);
		}
		return list;
	}

	public Food selectById(int FId) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Food food = null;
		try {
			con = ConnectionFactory.getConnection();

			String sql = "select * from food where FId=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, FId);
			rs = pst.executeQuery();
			if (rs.next()) {
				food = new Food();
				food.setFId(rs.getInt("FId"));
				food.setFName(rs.getString("FName"));
				food.setFRes(rs.getInt("FRes"));
				food.setFType(rs.getString("FType"));
				food.setFDetail(rs.getString("FDetail"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs, pst, con);
		}
		return food;
	}
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。