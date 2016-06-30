/**
 * Articles.java
 * Created at 2016-04-26
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.active.mass;

import java.io.Serializable;

/**
 * <p>
 * ClassName: Articles
 * </p>
 * <p>
 * Description: 图文消息素材
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
    private static final long serialVersionUID = -1092929744759216244L;

    /**
     * <p>
     * Field thumb_media_id: 图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
     * </p>
     */
    private String thumb_media_id;

    /**
     * <p>
     * Field author: 图文消息的作者
     * </p>
     */
    private String author;

    /**
     * <p>
     * Field title: 图文消息的标题
     * </p>
     */
    private String title;

    /**
     * <p>
     * Field content_source_url: 在图文消息页面点击“阅读原文”后的页面
     * </p>
     */
    private String content_source_url;

    /**
     * <p>
     * Field content: 图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用
     * </p>
     */
    private String content;

    /**
     * <p>
     * Field digest: 图文消息的描述
     * </p>
     */
    private String digest;

    /**
     * <p>
     * Field show_cover_pic: 是否显示封面，1为显示，0为不显示
     * </p>
     */
    private Integer show_cover_pic;

    public String getThumb_media_id() {
        return this.thumb_media_id;
    }

    public void setThumb_media_id(String thumb_media_id) {
        this.thumb_media_id = thumb_media_id;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent_source_url() {
        return this.content_source_url;
    }

    public void setContent_source_url(String content_source_url) {
        this.content_source_url = content_source_url;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDigest() {
        return this.digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Integer getShow_cover_pic() {
        return this.show_cover_pic;
    }

    public void setShow_cover_pic(Integer show_cover_pic) {
        this.show_cover_pic = show_cover_pic;
    }

}
