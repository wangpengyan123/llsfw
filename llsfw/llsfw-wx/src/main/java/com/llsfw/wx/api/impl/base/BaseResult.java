/**
 * BaseResult.java
 * Created at 2016-04-23
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.base;

/**
 * <p>
 * ClassName: BaseResult
 * </p>
 * <p>
 * Description: 结果集基类
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月23日
 * </p>
 */
public class BaseResult implements java.io.Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -8409748278223908895L;

    /**
     * <p>
     * Field errcode: 错误代码
     * </p>
     */
    private String errcode;

    /**
     * <p>
     * Field errmsg: 错误描述
     * </p>
     */
    private String errmsg;

    public String getErrcode() {
        return this.errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return this.errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

}
