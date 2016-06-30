/**
 * Video.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.passive;

/**
 * <p>
 * ClassName: Video
 * </p>
 * <p>
 * Description: 回复视屏消息中的视屏对象
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
public class Video implements java.io.Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 1941656867991677618L;

    /**
     * <p>
     * Field mediaId: 通过上传多媒体文件，得到的id
     * </p>
     */
    private String mediaId;

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

    public String getMediaId() {
        return this.mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
