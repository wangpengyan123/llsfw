/**
 * Image.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.passive;

/**
 * <p>
 * ClassName: Image
 * </p>
 * <p>
 * Description: 回复图片消息中的图片对象
 * </p>
 * <p>
 * 提供了获取图片Id<code>getMediaId()</code>等主要方法.
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
public class Image implements java.io.Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 2340129132488364838L;

    /**
     * <p>
     * Field MediaId: 通过上传多媒体文件，得到的id
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
