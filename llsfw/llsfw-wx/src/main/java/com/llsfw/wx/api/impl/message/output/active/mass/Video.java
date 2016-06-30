/**
 * Video.java
 * Created at 2016-04-26
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.active.mass;

import com.llsfw.wx.api.impl.message.media.BaseMedia;

/**
 * <p>
 * ClassName: Video
 * </p>
 * <p>
 * Description: 视频
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
    private static final long serialVersionUID = -671544506810077165L;

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
