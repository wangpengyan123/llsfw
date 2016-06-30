/**
 * MsgType.java
 * Created at 2016-04-24
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message;

/**
 * <p>
 * ClassName: MsgType
 * </p>
 * <p>
 * Description: 消息大类型(主要是区分消息是否是事件)
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月23日
 * </p>
 */
public enum MsgType {

    /**
     * <p>
     * Field Event: 事件消息
     * </p>
     */
    event("event");

    /**
     * <p>
     * Field value:值
     * </p>
     */
    private String value = "";

    /**
     * <p>
     * Description: 构造
     * </p>
     * 
     * @param value 值
     */
    MsgType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
