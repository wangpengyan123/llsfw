/**
 * Filter.java
 * Created at 2016-04-26
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.active.mass;

import java.io.Serializable;

/**
 * <p>
 * ClassName: Filter
 * </p>
 * <p>
 * Description: 过滤器
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月26日
 * </p>
 */
public class Filter implements Serializable {

    /**
     * <p>
     * Field serialVersionUID: TODO
     * </p>
     */
    private static final long serialVersionUID = -652889073462921090L;

    /**
     * <p>
     * Field is_to_all: 用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据group_id发送给指定群组的用户
     * </p>
     */
    private boolean is_to_all;

    /**
     * <p>
     * Field group_id: 群发到的分组的group_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写group_id
     * </p>
     */
    private Integer group_id;

    public boolean isIs_to_all() {
        return this.is_to_all;
    }

    public void setIs_to_all(boolean is_to_all) {
        this.is_to_all = is_to_all;
    }

    public Integer getGroup_id() {
        return this.group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

}
