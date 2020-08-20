package com.OrderRecipe.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DaoFactory {
	private static Properties props = new Properties();
	static {
		try {
			props.load(new FileInputStream("dao.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object getDao(String name) {
		Object dao = null;
		String userDao = props.getProperty(name);
		try {
			dao = Class.forName(userDao).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		}
		return dao;
	}
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。