/**
 * Music.java
 * Created at 2016-04-26
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.active;

import java.io.Serializable;

/**
 * <p>
 * ClassName: Music
 * </p>
 * <p>
 * Description: Music
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月26日
 * </p>
 */
public class Music implements Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 4778158556969992887L;

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

    /**
     * <p>
     * Field musicurl: 音乐链接
     * </p>
     */
    private String musicurl;

    /**
     * <p>
     * Field hqmusicurl: 高品质音乐链接，wifi环境优先使用该链接播放音乐
     * </p>
     */
    private String hqmusicurl;

    public String getMusicurl() {
        return this.musicurl;
    }

    public void setMusicurl(String musicurl) {
        this.musicurl = musicurl;
    }

    public String getHqmusicurl() {
        return this.hqmusicurl;
    }

    public void setHqmusicurl(String hqmusicurl) {
        this.hqmusicurl = hqmusicurl;
    }

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
