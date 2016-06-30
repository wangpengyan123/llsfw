/**
 * OpenId.java
 * Created at 2016-04-24
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.user;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * ClassName: OpenId
 * </p>
 * <p>
 * Description: openId
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月24日
 * </p>
 */
public class OpenId implements Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 3673614760654462445L;
    /**
     * <p>
     * Field openid: 普通用户的标识，对当前公众号唯一
     * </p>
     */
    private List<String> openid;

    public List<String> getOpenid() {
        return this.openid;
    }

    public void setOpenid(List<String> openid) {
        this.openid = openid;
    }

}
