/**
 * ImageInputMessage.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.input.normal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.llsfw.wx.api.impl.message.input.BaseInputMessage;

/**
 * <p>
 * ClassName: ImageInputMessage
 * </p>
 * <p>
 * Description: 图片消息
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
@XmlRootElement(name = "xml")
public class ImageInputMessage extends BaseInputMessage {

    /**
     * <p>
     * Field picUrl: 图片链接
     * </p>
     */
    private String picUrl;

    /**
     * <p>
     * Field mediaId: 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
     * </p>
     */
    private String mediaId;

    public String getPicUrl() {
        return this.picUrl;
    }

    @XmlElement(name = "PicUrl")
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    @XmlElement(name = "MediaId")
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
