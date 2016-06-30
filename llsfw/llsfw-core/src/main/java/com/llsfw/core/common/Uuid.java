/**
 * Uuid.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.common;

/**
 * <p>
 * ClassName: Uuid
 * </p>
 * <p>
 * Description: uuid相关操作
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年3月18日
 * </p>
 */
public class Uuid {

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    private Uuid() {

    }

    /**
     * <p>
     * Description: 返回uuid
     * </p>
     * 
     * @param separated 是否有分隔符 true:有(默认),false:无
     * @return uuid(小写)
     */
    public static String getUuid(boolean separated) {
        String id = java.util.UUID.randomUUID().toString();
        if (!separated) {
            id = id.replaceAll("-", "");
        }
        return id;
    }
}
