package com.OrderRecipe.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.OrderRecipe.dao.TableDao;
import com.OrderRecipe.entity.Table;
import com.OrderRecipe.util.ConnectionFactory;
import com.OrderRecipe.util.Parameters;

public class TableDaoImpl implements TableDao {

    public void insert(Table table) {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionFactory.getConnection();
            String sql = "insert into table values(?,?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, table.getTNo());
            pst.setString(2, table.getTPass());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(null, pst, con);
        }
    }

    public void delete(int TNo) {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionFactory.getConnection();
            String sql = "delete from `table` where TNo=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, TNo);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(null, pst, con);
        }
    }

    public void update(int TNo, Table table) { // 结账后，更改TAvl，异或1
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionFactory.getConnection();
            String sql = "update `table` set TAvl=1^TAvl where TNo=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, TNo);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(null, pst, con);
        }
    }

    public List<Table> select() { // 选择全部
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Table> list = new ArrayList<Table>();
        try {
            con = ConnectionFactory.getConnection();
            String sql = "select * from table";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while (rs.next()) {
                Table table = new Table();
                table.setTNo(rs.getInt("TNo"));
                table.setTPass(rs.getString("TPass"));
                table.setTAvl(rs.getInt("TAvl"));
                list.add(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(rs, pst, con);
        }
        return list;
    }

    public List<Table> selectByAvl() { // 选择空闲
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Table> list = new ArrayList<Table>();
        try {
            con = ConnectionFactory.getConnection();

            String sql = "select * from `table` where TAvl=1";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Table table = new Table();
                table.setTNo(rs.getInt("TNo"));
                table.setTPass(rs.getString("TPass"));
                table.setTAvl(rs.getInt("TAvl"));
                list.add(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(rs, pst, con);
        }
        return list;
    }

    public Table selectByNo(int TNo) { // 按号选择
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Table table = null;
        try {
            con = ConnectionFactory.getConnection();

            String sql = "select * from `table` where TNo=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, TNo);
            rs = pst.executeQuery();
            if (rs.next()) {
                table = new Table();
                table.setTNo(rs.getInt("TNo"));
                table.setTPass(rs.getString("TPass"));
                table.setTAvl(rs.getInt("TAvl"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(rs, pst, con);
        }
        return table;
    }

    public int tableCheck(int TNo, String TPass) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int flag = Parameters.WRONG_PASS;

        try {
            con = ConnectionFactory.getConnection();

            String sql = "select * from `table` where TNo=? and TPass=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, TNo);
            pst.setString(2, TPass);
            rs = pst.executeQuery();

            if (rs.next()) {
                Table table = new Table();
                table.setTNo(rs.getInt("TNo"));
                table.setTPass(rs.getString("TPass"));
                table.setTAvl(rs.getInt("TAvl"));
                if (table.getTAvl() == 1) {
                    flag = Parameters.TABLE_SUCCESS;
                } else {
                    flag = Parameters.TABLE_BUSY;
                    System.out.println("TableDaoImpl: table pre-ordered.");
                }
            } else {
                flag = Parameters.WRONG_PASS;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(rs, pst, con);
        }
        return flag;
    }
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。