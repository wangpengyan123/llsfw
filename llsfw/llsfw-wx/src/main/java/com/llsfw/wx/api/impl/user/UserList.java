/**
 * UserList.java
 * Created at 2016-04-24
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.user;

import com.llsfw.wx.api.impl.base.BaseResult;

/**
 * <p>
 * ClassName: UserList
 * </p>
 * <p>
 * Description: 用户列表
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月24日
 * </p>
 */
public class UserList extends BaseResult {
    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 8986992093485834682L;
    /**
     * <p>
     * Field total: 关注该公众账号的总用户数
     * </p>
     */
    private Integer total;
    /**
     * <p>
     * Field count: 拉取的OPENID个数，最大值为10000
     * </p>
     */
    private Integer count;
    /**
     * <p>
     * Field data: 列表数据，OPENID的列表
     * </p>
     */
    private OpenId data;
    /**
     * <p>
     * Field next_openid: 拉取列表的最后一个用户的OPENID
     * </p>
     */
    private String next_openid;

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public OpenId getData() {
        return this.data;
    }

    public void setData(OpenId data) {
        this.data = data;
    }

    public String getNext_openid() {
        return this.next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }

}
