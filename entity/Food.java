package com.OrderRecipe.entity;

public class Food {
	private int FId;
	private String FName;
	private int FRes;
	private String FType;
	private String FDetail;

	public Food() {

	}

	public Food(int fId, String fName, int fRes, String fType, String fDetail) {
		this.FId = fId;
		this.FName = fName;
		this.FRes = fRes;
		this.FType = fType;
		this.FDetail = fDetail;
	}

	@Override
	public String toString() {
		return "Food [FId=" + this.FId + ", FName=" + this.FName + ", FRes=" + this.FRes + ", FType=" + this.FType
				+ ", FDetail=" + this.FDetail + "]";
	}

	public int getFId() {
		return FId;
	}

	public void setFId(int fId) {
		FId = fId;
	}

	public String getFName() {
		return FName;
	}

	public void setFName(String fName) {
		FName = fName;
	}

	public int getFRes() {
		return FRes;
	}

	public void setFRes(int fRes) {
		FRes = fRes;
	}

	public String getFType() {
		return FType;
	}

	public void setFType(String fType) {
		FType = fType;
	}

	public String getFDetail() {
		return FDetail;
	}

	public void setFDetail(String fDetail) {
		FDetail = fDetail;
	}

}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。