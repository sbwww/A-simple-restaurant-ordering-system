package com.OrderRecipe.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.OrderRecipe.dao.TableDishDao;
import com.OrderRecipe.entity.Bill;
import com.OrderRecipe.entity.TableDish;
import com.OrderRecipe.util.ConnectionFactory;

public class TableDishDaoImpl implements TableDishDao {

    public void insert(TableDish td) {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionFactory.getConnection();
            String sql = "insert into table_dish (TNo, DId) values (?, ?) on duplicate key update TDNum=TDNum+1";
            pst = con.prepareStatement(sql);
            pst.setInt(1, td.getTNo());
            pst.setInt(2, td.getDId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(null, pst, con);
        }
    }

    public void delete(TableDish td) {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionFactory.getConnection();
            String sql = "update table_dish set TDNum=TDNum-1 where TNo=? and DId=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, td.getTNo());
            pst.setInt(2, td.getDId());
            pst.executeUpdate();
            
            sql = "delete from table_dish where TDNum<=0";
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(null, pst, con);
        }
    }

    public void update(TableDish td) { // TDNum--
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionFactory.getConnection();
            String sql = "update table_dish set TDNum=TDNum-1 where TNo=? and DId=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, td.getTNo());
            pst.setInt(2, td.getDId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(null, pst, con);
        }
    }

    public boolean select(TableDish td) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            String sql = "select * from table_dish where TNo=? and DId=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, td.getTNo());
            pst.setInt(2, td.getDId());
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

    public List<TableDish> selectByTNo(int TNo) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TableDish> list = new ArrayList<TableDish>();
        try {
            con = ConnectionFactory.getConnection();
            String sql = "select * from table_dish where TNo=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, TNo);
            rs = pst.executeQuery();
            while (rs.next()) {
                TableDish td = new TableDish();
                td.setTNo(rs.getInt("TNo"));
                td.setDId(rs.getInt("DId"));
                td.setTDNum(rs.getInt("DId"));
                list.add(td);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(rs, pst, con);
        }
        return list;
    }

    public List<TableDish> selectByDId(int DId) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TableDish> list = new ArrayList<TableDish>();
        try {
            con = ConnectionFactory.getConnection();
            String sql = "select * from table_dish where DId=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, DId);
            rs = pst.executeQuery();
            while (rs.next()) {
                TableDish td = new TableDish();
                td.setTNo(rs.getInt("TNo"));
                td.setDId(rs.getInt("DId"));
                td.setTDNum(rs.getInt("DId"));
                list.add(td);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(rs, pst, con);
        }
        return list;
    }

    public List<Bill> getBill(int TNo) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Bill> list = new ArrayList<Bill>();
        try {
            con = ConnectionFactory.getConnection();
            String sql = "select * from table_dish join dish on table_dish.DId=dish.DId where TNo=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, TNo);
            rs = pst.executeQuery();
            while (rs.next()) {
                Bill b = new Bill();
                b.setDName(rs.getString("DName"));
                b.setDPrice(rs.getInt("DPrice"));
                b.setTDNum(rs.getInt("TDNum"));
                list.add(b);
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