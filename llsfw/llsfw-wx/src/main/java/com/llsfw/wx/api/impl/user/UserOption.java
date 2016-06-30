/**
 * UserOption.java
 * Created at 2016-04-24
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.user;

import java.util.List;

import com.llsfw.wx.api.impl.base.BaseResult;

/**
 * <p>
 * ClassName: UserOption
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
public class UserOption extends BaseResult {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 2089011063226044159L;

    /**
     * <p>
     * Field user_info_list: 用户列表
     * </p>
     */
    private List<User> user_info_list;

    public List<User> getUser_info_list() {
        return this.user_info_list;
    }

    public void setUser_info_list(List<User> user_info_list) {
        this.user_info_list = user_info_list;
    }

}
