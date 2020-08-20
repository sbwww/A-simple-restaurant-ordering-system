package com.OrderRecipe.entity;

public class TableDish {
    private int TNo;
    private int DId;
    private int TDNum;

    public TableDish() {
    }

    public TableDish(int tNo, int dId, int tDNum) {
        TNo = tNo;
        DId = dId;
        TDNum = tDNum;
    }

    public int getTNo() {
        return TNo;
    }

    public void setTNo(int tNo) {
        TNo = tNo;
    }

    public int getDId() {
        return DId;
    }

    public void setDId(int dId) {
        DId = dId;
    }

    public int getTDNum() {
        return TDNum;
    }

    public void setTDNum(int tDNum) {
        TDNum = tDNum;
    }

    @Override
    public String toString() {
        return "TableDish [DId=" + DId + ", TDNum=" + TDNum + ", TNo=" + TNo + "]";
    }

}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。