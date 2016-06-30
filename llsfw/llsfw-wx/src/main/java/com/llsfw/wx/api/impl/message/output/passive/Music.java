/**
 * Music.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.passive;

/**
 * <p>
 * ClassName: Music
 * </p>
 * <p>
 * Description: 音乐消息
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
public class Music implements java.io.Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 1941656867991677618L;

    /**
     * <p>
     * Field title: 视频消息的标题
     * </p>
     */
    private String title;

    /**
     * <p>
     * Field description: 视频消息的描述
     * </p>
     */
    private String description;

    /**
     * <p>
     * Field musicUrl: 音乐链接
     * </p>
     */
    private String musicUrl;

    /**
     * <p>
     * Field hqMusicUrl: 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     * </p>
     */
    private String hqMusicUrl;

    /**
     * <p>
     * Field thumbMediaId: 缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id
     * </p>
     */
    private String thumbMediaId;

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

    public String getMusicUrl() {
        return this.musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getHqMusicUrl() {
        return this.hqMusicUrl;
    }

    public void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
    }

    public String getThumbMediaId() {
        return this.thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

}
