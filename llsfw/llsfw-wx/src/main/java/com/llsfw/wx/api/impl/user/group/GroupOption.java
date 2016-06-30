/**
 * User.java
 * Created at 2016-04-23
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.user.group;

import java.util.List;

import com.llsfw.wx.api.impl.base.BaseResult;

/**
 * <p>
 * ClassName: User
 * </p>
 * <p>
 * Description: 用户模块对象
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月23日
 * </p>
 */
public class GroupOption extends BaseResult {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -4853000252291623052L;

    /**
     * <p>
     * Field openid: 用户的OpenID
     * </p>
     */
    private String openid;

    /**
     * <p>
     * Field openid_list: 用户唯一标识符openid的列表（size不能超过50）
     * </p>
     */
    private List<String> openid_list;

    /**
     * <p>
     * Field to_groupid: 分组id
     * </p>
     */
    private Integer to_groupid;

    /**
     * <p>
     * Field groupid: 用户所属的groupid
     * </p>
     */
    private Integer groupid;

    /**
     * <p>
     * Field group: 分组对象
     * </p>
     */
    private Group group;

    /**
     * <p>
     * Field groups: 公众平台分组信息列表
     * </p>
     */
    private List<Group> groups;

    public List<Group> getGroups() {
        return this.groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Group getGroup() {
        return this.group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getGroupid() {
        return this.groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getTo_groupid() {
        return this.to_groupid;
    }

    public void setTo_groupid(Integer to_groupid) {
        this.to_groupid = to_groupid;
    }

    public List<String> getOpenid_list() {
        return this.openid_list;
    }

    public void setOpenid_list(List<String> openid_list) {
        this.openid_list = openid_list;
    }

}
