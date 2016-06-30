/**
 * Voice.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.passive;

/**
 * <p>
 * ClassName: Voice
 * </p>
 * <p>
 * Description: 回复语音消息中的语音对象
 * <p>
 * 提供了获取语音Id<code>getMediaId()</code>等主要方法.
 * </p>
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
public class Voice implements java.io.Serializable {

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

    public String getMediaId() {
        return this.mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
