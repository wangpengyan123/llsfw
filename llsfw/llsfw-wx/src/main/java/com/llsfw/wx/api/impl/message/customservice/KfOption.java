/**
 * KfOption.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.customservice;

import java.util.List;

import com.llsfw.wx.api.impl.base.BaseResult;

/**
 * <p>
 * ClassName: KfOption
 * </p>
 * <p>
 * Description: 客服操作
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
public class KfOption extends BaseResult {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 1431979323553267628L;

    /**
     * <p>
     * Field kf_account: 客服账号
     * </p>
     */
    private String kf_account;

    /**
     * <p>
     * Field nickname: 客服昵称
     * </p>
     */
    private String nickname;

    /**
     * <p>
     * Field password: 密码
     * </p>
     */
    private String password;

    /**
     * <p>
     * Field kf_list: 客服账号列表
     * </p>
     */
    private List<KfList> kf_list;

    public List<KfList> getKf_list() {
        return this.kf_list;
    }

    public void setKf_list(List<KfList> kf_list) {
        this.kf_list = kf_list;
    }

    public String getKf_account() {
        return this.kf_account;
    }

    public void setKf_account(String kf_account) {
        this.kf_account = kf_account;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
