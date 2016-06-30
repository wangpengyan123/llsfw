/**
 * ShortvideoInputMessage.java
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
 * ClassName: ShortvideoInputMessage
 * </p>
 * <p>
 * Description: 小视频消息
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
@XmlRootElement(name = "xml")
public class ShortvideoInputMessage extends BaseInputMessage {

    /**
     * <p>
     * Field mediaId: 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
     * </p>
     */
    private String mediaId;

    /**
     * <p>
     * Field thumbMediaId: 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     * </p>
     */
    private String thumbMediaId;

    public String getMediaId() {
        return this.mediaId;
    }

    @XmlElement(name = "MediaId")
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getThumbMediaId() {
        return this.thumbMediaId;
    }

    @XmlElement(name = "ThumbMediaId")
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

}
