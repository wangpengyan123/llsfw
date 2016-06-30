/**
 * DataSourceThreadLocal.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.datasource;

import com.llsfw.generator.model.standard.system.TtDynamicDataSource;

/**
 * <p>
 * ClassName: DataSourceThreadLocal
 * </p>
 * <p>
 * Description: 数据源本地线程
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class DataSourceThreadLocal extends ThreadLocal<TtDynamicDataSource> {

    /*
     * 修改点参考:http://blog.csdn.net/cxh5060/article/details/49275633
     * @see java.lang.ThreadLocal#remove()
     */
    @Override
    public void remove() {
        super.remove();
        this.initialValue();
    }

}
