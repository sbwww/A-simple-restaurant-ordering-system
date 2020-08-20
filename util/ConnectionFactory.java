package com.OrderRecipe.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static Properties props = new Properties();
	static {
		try {
			props.load(new FileInputStream("database.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			String driver = props.getProperty("driver");
			String url = props.getProperty("url");
			String username = props.getProperty("user");
			String password = props.getProperty("password");
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println("failed to register driver.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("failed to execute sql.");
			e.printStackTrace();
		}
		return con;
	}

	public static void close(ResultSet rs, PreparedStatement pst, Connection con) { // 关闭数据库连接
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			System.out.println("关闭rs对象失败");
			e.printStackTrace();
		}
		try {
			if (pst != null)
				pst.close();
		} catch (SQLException e) {
			System.out.println("关闭pst对象失败");
			e.printStackTrace();
		}
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("关闭con对象失败");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection con = getConnection();
		System.out.println(con);
	}
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。