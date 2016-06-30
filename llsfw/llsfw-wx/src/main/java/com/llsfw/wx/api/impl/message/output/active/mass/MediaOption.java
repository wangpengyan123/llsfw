/**
 * MediaOption.java
 * Created at 2016-04-26
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.active.mass;

import java.util.List;

import com.llsfw.wx.api.impl.base.BaseResult;

/**
 * <p>
 * ClassName: MediaOption
 * </p>
 * <p>
 * Description: 多媒体素材操作类
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月26日
 * </p>
 */
public class MediaOption extends BaseResult {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -3089747263910586268L;

    /**
     * <p>
     * Field url: 素材地址
     * </p>
     */
    private String url;

    /**
     * <p>
     * Field articles: 图文消息，一个图文消息支持1到10条图文
     * </p>
     */
    private List<Articles> articles;

    public List<Articles> getArticles() {
        return this.articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
