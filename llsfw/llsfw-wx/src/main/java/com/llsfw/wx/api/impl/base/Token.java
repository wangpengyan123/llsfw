/**
 * Token.java
 * Created at 2016-04-23
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.base;

/**
 * <p>
 * ClassName: Token
 * </p>
 * <p>
 * Description: 凭证
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月23日
 * </p>
 */
public class Token extends BaseResult {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -5037899897898354450L;

    /**
     * <p>
     * Field access_token: 获取到的凭证
     * </p>
     */
    private String access_token;

    /**
     * <p>
     * Field expires_in: 凭证有效时间，单位：秒
     * </p>
     */
    private int expires_in;

    public String getAccess_token() {
        return this.access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return this.expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

}
