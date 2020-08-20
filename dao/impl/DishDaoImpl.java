package com.OrderRecipe.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.OrderRecipe.dao.DishDao;
import com.OrderRecipe.entity.Dish;
import com.OrderRecipe.util.ConnectionFactory;

public class DishDaoImpl implements DishDao {

	public void insert(Dish dish) { // 插入
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = ConnectionFactory.getConnection();
			String sql = "insert into dish values(NULL,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, dish.getDName());
			pst.setInt(2, dish.getDPrice());
			pst.setString(3, dish.getDDetail());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(null, pst, con);
		}
	}

	public void delete(int DId) {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = ConnectionFactory.getConnection();
			String sql = "delete from dish where DId=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, DId);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(null, pst, con);
		}
	}

	public void update(int DId, Dish dish) {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = ConnectionFactory.getConnection();
			String sql = "update dish set DName=?, DPrice=?,DDetail=? where DId=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, dish.getDName());
			pst.setInt(2, dish.getDPrice());
			pst.setString(3, dish.getDDetail());
			pst.setInt(4, DId);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(null, pst, con);
		}
	}

	public List<Dish> select() { // 选择
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Dish> list = new ArrayList<Dish>();

		try {
			con = ConnectionFactory.getConnection();
			String sql = "select * from dish";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery(sql);
			while (rs.next()) {
				Dish dish = new Dish();
				dish.setDId(rs.getInt("DId"));
				dish.setDName(rs.getString("DName"));
				dish.setDPrice(rs.getInt("DPrice"));
				dish.setDDetail(rs.getString("DDetail"));
				list.add(dish);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs, pst, con);
		}
		return list;
	}

	public List<Dish> selectByName(String DName) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Dish> list = new ArrayList<Dish>();
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select * from dish where DName like '%" + DName + "%'";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Dish dish = new Dish();
				dish.setDId(rs.getInt("DId"));
				dish.setDName(rs.getString("DName"));
				dish.setDPrice(rs.getInt("DPrice"));
				dish.setDDetail(rs.getString("DDetail"));
				list.add(dish);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs, pst, con);
		}
		return list;
	}

	public Dish selectById(int DId) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Dish dish = null;

		try {
			con = ConnectionFactory.getConnection();
			String sql = "select * from dish where DId=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, DId);
			rs = pst.executeQuery();
			if (rs.next()) {
				dish = new Dish();
				dish.setDId(rs.getInt("DId"));
				dish.setDName(rs.getString("DName"));
				dish.setDPrice(rs.getInt("DPrice"));
				dish.setDDetail(rs.getString("DDetail"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs, pst, con);
		}
		return dish;
	}
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。