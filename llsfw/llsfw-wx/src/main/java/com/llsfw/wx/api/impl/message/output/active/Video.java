/**
 * Video.java
 * Created at 2016-04-26
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.active;

import com.llsfw.wx.api.impl.message.media.BaseMedia;

/**
 * <p>
 * ClassName: Video
 * </p>
 * <p>
 * Description: Video
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月26日
 * </p>
 */
public class Video extends BaseMedia {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 5958248933397351973L;

    /**
     * <p>
     * Field thumb_media_id: 缩略图的媒体ID
     * </p>
     */
    private String thumb_media_id;

    /**
     * <p>
     * Field title: 图文消息/视频消息/音乐消息的标题
     * </p>
     */
    private String title;

    /**
     * <p>
     * Field description: 图文消息/视频消息/音乐消息的描述
     * </p>
     */
    private String description;

    public String getThumb_media_id() {
        return this.thumb_media_id;
    }

    public void setThumb_media_id(String thumb_media_id) {
        this.thumb_media_id = thumb_media_id;
    }

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

}
