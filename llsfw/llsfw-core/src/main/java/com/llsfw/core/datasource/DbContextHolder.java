/**
 * DbContextHolder.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.datasource;

import com.llsfw.generator.model.standard.system.TtDynamicDataSource;

/**
 * <p>
 * ClassName: DbContextHolder
 * </p>
 * <p>
 * Description: 多个登录用户可能需要同时切换数据源，所以这里需要写一个线程安全的ThreadLocal 用户切换数据源只要在程序中使用
 * DBContextHolder.setDBType("1") 即可完成数据源切换
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class DbContextHolder {

    /**
     * <p>
     * Field contextHolder: 本地线程
     * </p>
     */
    private static final ThreadLocal<TtDynamicDataSource> CONTEXTHOLDER = new DataSourceThreadLocal();

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    private DbContextHolder() {

    }

    /**
     * <p>
     * Description: 设置数据库类型
     * </p>
     * 
     * @param dbType 数据库
     */
    public static void setDbType(TtDynamicDataSource dbType) {
        CONTEXTHOLDER.set(dbType);
    }

    public static TtDynamicDataSource getDbType() {
        return CONTEXTHOLDER.get();
    }

    /**
     * <p>
     * Description: 清理
     * </p>
     */
    public static void clearDbType() {
        CONTEXTHOLDER.remove();
    }

}
