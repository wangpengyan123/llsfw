/**
 * Articles.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.passive;

import java.io.Serializable;

/**
 * <p>
 * ClassName: Articles
 * </p>
 * <p>
 * Description: 图文消息条目
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
public class Articles implements Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -8850105449158868545L;

    /**
     * <p>
     * Field title: 图文消息标题
     * </p>
     */
    private String title;

    /**
     * <p>
     * Field description: 图文消息描述
     * </p>
     */
    private String description;

    /**
     * <p>
     * Field picUrl: 发送被动响应时设置的图片url 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     * </p>
     */
    private String picUrl;

    /**
     * <p>
     * Field url: 点击图文消息跳转链接
     * </p>
     */
    private String url;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
