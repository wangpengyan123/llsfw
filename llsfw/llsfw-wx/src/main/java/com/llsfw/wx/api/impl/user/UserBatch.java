/**
 * UserBatch.java
 * Created at 2016-04-24
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.user;

import java.io.Serializable;

/**
 * <p>
 * ClassName: UserBatch
 * </p>
 * <p>
 * Description: 微信平台用户管理
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月24日
 * </p>
 */
public class UserBatch implements Serializable {
    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -3921519136432698686L;

    /**
     * <p>
     * Field openid: 用户的标识，对当前公众号唯一
     * </p>
     */
    private String openid;

    /**
     * <p>
     * Field language: 用户的语言，简体中文为zh_CN
     * </p>
     */
    private String lang;

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLang() {
        return this.lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

}
