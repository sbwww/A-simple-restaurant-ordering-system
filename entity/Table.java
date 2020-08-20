package com.OrderRecipe.entity;

public class Table {
	private int TNo;
	private String TPass;
	private int TAvl;

	public Table() {

	}

	public Table(int tNo, String tPass, int tAvl) {
		this.TNo = tNo;
		this.TPass = tPass;
		this.TAvl = tAvl;
	}

	public int getTNo() {
		return this.TNo;
	}

	public void setTNo(int tNo) {
		this.TNo = tNo;
	}

	public String getTPass() {
		return this.TPass;
	}

	public void setTPass(String tPass) {
		this.TPass = tPass;
	}

	public int getTAvl() {
		return this.TAvl;
	}

	public void setTAvl(int tAvl) {
		this.TAvl = tAvl;
	}

}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。