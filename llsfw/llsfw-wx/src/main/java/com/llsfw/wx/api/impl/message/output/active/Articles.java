/**
 * Articles.java
 * Created at 2016-04-26
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.active;

import java.io.Serializable;

/**
 * <p>
 * ClassName: Articles
 * </p>
 * <p>
 * Description: Articles
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月26日
 * </p>
 */
public class Articles implements Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 4778158556969992887L;

    /**
     * <p>
     * Field title: 消息的标题
     * </p>
     */
    private String title;

    /**
     * <p>
     * Field description: 消息的描述
     * </p>
     */
    private String description;

    /**
     * <p>
     * Field url: 图文消息被点击后跳转的链接
     * </p>
     */
    private String url;

    /**
     * <p>
     * Field picurl: 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80
     * </p>
     */
    private String picurl;

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

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicurl() {
        return this.picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

}
