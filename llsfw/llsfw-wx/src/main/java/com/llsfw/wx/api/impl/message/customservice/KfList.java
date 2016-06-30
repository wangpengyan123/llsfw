/**
 * KfList.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.customservice;

import java.io.Serializable;

/**
 * <p>
 * ClassName: KfList
 * </p>
 * <p>
 * Description: 客服账号
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
public class KfList implements Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -2297610223908843324L;

    /**
     * <p>
     * Field kf_account: 完整客服账号，格式为：账号前缀@公众号微信号
     * </p>
     */
    private String kf_account;

    /**
     * <p>
     * Field kf_nick: 客服昵称
     * </p>
     */
    private String kf_nick;

    /**
     * <p>
     * Field kf_id: 客服工号
     * </p>
     */
    private String kf_id;

    /**
     * <p>
     * Field kf_headimgurl: 头像地址
     * </p>
     */
    private String kf_headimgurl;

    public String getKf_account() {
        return this.kf_account;
    }

    public void setKf_account(String kf_account) {
        this.kf_account = kf_account;
    }

    public String getKf_nick() {
        return this.kf_nick;
    }

    public void setKf_nick(String kf_nick) {
        this.kf_nick = kf_nick;
    }

    public String getKf_id() {
        return this.kf_id;
    }

    public void setKf_id(String kf_id) {
        this.kf_id = kf_id;
    }

    public String getKf_headimgurl() {
        return this.kf_headimgurl;
    }

    public void setKf_headimgurl(String kf_headimgurl) {
        this.kf_headimgurl = kf_headimgurl;
    }

}
