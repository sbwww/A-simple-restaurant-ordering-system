package com.OrderRecipe.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.OrderRecipe.dao.DishFoodDao;
import com.OrderRecipe.entity.DishFood;
import com.OrderRecipe.util.ConnectionFactory;

public class DishFoodDaoImpl implements DishFoodDao {

	public void insert(DishFood df) {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = ConnectionFactory.getConnection();
			String sql = "insert into dish_food values(?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, df.getDId());
			pst.setInt(2, df.getFId());
			pst.setInt(3, df.getFNum());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(null, pst, con);
		}
	}

	public void delete(int DId, int FId) {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = ConnectionFactory.getConnection();
			String sql = "delete from dish_food where DId=? and FId=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, DId);
			pst.setInt(2, FId);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(null, pst, con);
		}
	}

	public void update(int DId, int FId, DishFood df) {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = ConnectionFactory.getConnection();
			String sql = "update dish_food set DId=?, FId=?, FNum=? where DId=? and FId=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, df.getDId());
			pst.setInt(2, df.getFId());
			pst.setInt(3, df.getFNum());
			pst.setInt(4, DId);
			pst.setInt(5, FId);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(null, pst, con);
		}
	}

	public boolean select(DishFood df) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select * from dish_food where DId=? and FId=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, df.getDId());
			pst.setInt(2, df.getFId());
			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs, pst, con);
		}
		return false;
	}

	public List<DishFood> selectByDId(int DId) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<DishFood> list = new ArrayList<DishFood>();
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select * from dish_food where DId=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, DId);
			rs = pst.executeQuery();
			while (rs.next()) {
				DishFood df = new DishFood();
				df.setDId(rs.getInt("DId"));
				df.setFId(rs.getInt("FId"));
				df.setFNum(rs.getInt("FNum"));
				list.add(df);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs, pst, con);
		}
		return list;
	}

	public List<DishFood> selectByFId(int FId) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<DishFood> list = new ArrayList<DishFood>();
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select * from dish_food where FId=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, FId);
			rs = pst.executeQuery();
			while (rs.next()) {
				DishFood df = new DishFood();
				df.setDId(rs.getInt("DId"));
				df.setFId(rs.getInt("FId"));
				df.setFNum(rs.getInt("FNum"));
				list.add(df);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs, pst, con);
		}
		return list;
	}

}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。