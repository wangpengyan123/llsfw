/**
 * Group.java
 * Created at 2016-04-23
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.user.group;

import java.io.Serializable;

/**
 * <p>
 * ClassName: Group
 * </p>
 * <p>
 * Description: 微信平台分组对象
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月23日
 * </p>
 */
public class Group implements Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -5993297528826383815L;

    /**
     * <p>
     * Field id: 分组id，由微信分配
     * </p>
     */
    private Integer id;

    /**
     * <p>
     * Field name: 分组名字，UTF8编码
     * </p>
     */
    private String name;

    /**
     * <p>
     * Field count: 分组内用户数量
     * </p>
     */
    private Integer count;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
