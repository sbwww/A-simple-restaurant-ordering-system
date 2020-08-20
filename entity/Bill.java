package com.OrderRecipe.entity;

public class Bill {
    private String DName;
    private int DPrice;
    private int TDNum;

    public Bill() {
    }

    public Bill(String dName, int tDNum, int dPrice) {
        DName = dName;
        TDNum = tDNum;
        DPrice = dPrice;
    }

    public String getDName() {
        return DName;
    }

    public void setDName(String dName) {
        DName = dName;
    }

    public int getTDNum() {
        return TDNum;
    }

    public void setTDNum(int tDNum) {
        TDNum = tDNum;
    }

    public int getDPrice() {
        return DPrice;
    }

    public void setDPrice(int dPrice) {
        DPrice = dPrice;
    }

    @Override
    public String toString() {
        return "Bill [DName=" + DName + ", DPrice=" + DPrice + ", TDNum=" + TDNum + "]";
    }

}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。