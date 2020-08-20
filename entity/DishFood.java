package com.OrderRecipe.entity;

public class DishFood {
	private int DId;
	private int FId;
	private int FNum;

	public DishFood() {
	}

	public DishFood(int dId, int fId, int fNum) {
		DId = dId;
		FId = fId;
		FNum = fNum;
	}

	public int getDId() {
		return DId;
	}

	public void setDId(int dId) {
		DId = dId;
	}

	public int getFId() {
		return FId;
	}

	public void setFId(int fId) {
		FId = fId;
	}

	public int getFNum() {
		return FNum;
	}

	public void setFNum(int fNum) {
		FNum = fNum;
	}

	@Override
	public String toString() {
		return "DishFood [DId=" + DId + ", FId=" + FId + ", FNum=" + FNum + "]";
	}

}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。