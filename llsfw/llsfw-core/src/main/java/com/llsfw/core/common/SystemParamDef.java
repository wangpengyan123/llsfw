/**
 * SystemParamDef.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.common;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * ClassName: SystemParamDef
 * </p>
 * <p>
 * Description: 默认系统参数
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年3月7日
 * </p>
 */
public class SystemParamDef {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass()); 

    /**
     * <p>
     * Field data: 存储的系统参数map
     * </p>
     */
    private Map<String, String> data;

    public Map<String, String> getData() {
        return this.data;
    }

    /**
     * <p>
     * Description: 设置值
     * </p>
     * 
     * @param data 值
     */
    public void setData(Map<String, String> data) {
        this.data = data;
        this.log.debug("data:" + this.data);
    }
}
