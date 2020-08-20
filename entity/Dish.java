package com.OrderRecipe.entity;

public class Dish {
	private int DId;
	private String DName;
	private int DPrice;
	private String DDetail;

	public Dish() {

	}

	public Dish(int dId, String dName, int dPrice, String dDetail) {
		this.DId = dId;
		this.DName = dName;
		this.DPrice = dPrice;
		this.DDetail = dDetail;
	}

	@Override
	public String toString() {
		return "Dish [DId=" + this.DId + ", DName=" + this.DName + ", DPrice=" + this.DPrice + ", DDetail=" + this.DDetail + "]";
	}

	public int getDId() {
		return DId;
	}

	public void setDId(int dId) {
		DId = dId;
	}

	public String getDName() {
		return DName;
	}

	public void setDName(String dName) {
		DName = dName;
	}

	public int getDPrice() {
		return DPrice;
	}

	public void setDPrice(int dPrice) {
		DPrice = dPrice;
	}
	
	public String getDDetail() {
		return DDetail;
	}

	public void setDDetail(String dDetail) {
		DDetail = dDetail;
	}


}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。