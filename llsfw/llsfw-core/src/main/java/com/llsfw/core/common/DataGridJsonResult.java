/**
 * DataGridJsonResult.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.common;

import java.util.List;

/**
 * <p>
 * ClassName: DataGridJsonResult
 * </p>
 * <p>
 * Description: 表格数据结构
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class DataGridJsonResult<T> {

    /**
     * <p>
     * Field total: 总行数
     * </p>
     */
    private int total;

    /**
     * <p>
     * Field rows: 数据
     * </p>
     */
    private List<T> rows;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param total 总函数
     * @param rows 数据
     */
    public DataGridJsonResult(int total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return this.rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
