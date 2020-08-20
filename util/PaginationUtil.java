package com.OrderRecipe.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PaginationUtil {
    private List list;
    private int pageSize; // 每页记录数
    private int totalPage; // 总页数
    private int currentPage; // 当前页号

    public PaginationUtil(List list, int pageSize) {
        this.list = list;
        this.pageSize = pageSize;

        // 求总页数
        int totalRecord = list.size(); // 记录总数
        if (totalRecord % pageSize == 0) {
            totalPage = totalRecord / pageSize;
        } else {
            totalPage = totalRecord / pageSize + 1;
        }
    }

    public List getCurrentPageRecords(int currentPage) {
        this.currentPage = currentPage; // 当前页号

        int firstRecord = (currentPage - 1) * pageSize + 1; // 当前页的第一个记录

        // 将当前页的记录放入currentRecords，并返回
        List currentRecords = new ArrayList();
        // (1)找到起始firstRecord
        Iterator it = list.iterator();
        int i = 1;
        while (it.hasNext() && i < firstRecord) {
            it.next();
            ++i;
        }
        // (2)将当前页的pageSize个记录或剩余不足pageSize的记录放入currentRecords
        int j = 1;
        while (it.hasNext() && j <= pageSize && i <= list.size()) {
            currentRecords.add(list.get(i - 1)); // arraylist的下标从0开始
            it.next();
            ++i;
            ++j;
        }
        return currentRecords;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getPrePage() { // 获取前一页
        if (currentPage > 1) {
            return currentPage - 1;
        } else {
            return 1;
        }
    }

    public int getNextPage() { // 获取后一页
        if (currentPage < totalPage) {
            return currentPage + 1;
        } else {
            return totalPage;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
--------------------
作者：申博文，南京信息工程大学（Bowen Shen, NUIST）
CSDN: https://blog.csdn.net/qq_44926567
GitHub: https://github.com/sbwww
著作权归作者所有。内容仅供学习交流，不得抄袭。