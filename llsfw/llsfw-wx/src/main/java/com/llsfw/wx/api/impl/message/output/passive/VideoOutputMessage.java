/**
 * VideoOutputMessage.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.passive;

import com.llsfw.wx.api.impl.message.output.AbstractOutputMessage;

/**
 * <p>
 * ClassName: VideoOutputMessage
 * </p>
 * <p>
 * Description: 回复视屏消息
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
public class VideoOutputMessage extends AbstractOutputMessage {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -5096255190333101333L;

    /**
     * <p>
     * Field msgType: 视屏消息
     * </p>
     */
    private String msgType = "video";

    /**
     * <p>
     * Field voice: 通过上传多媒体文件，得到的id
     * </p>
     */
    private Video video;

    public Video getVideo() {
        return this.video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getMsgType() {
        return this.msgType;
    }

    @Override
    public String toXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[").append(this.getToUserName()).append("]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[").append(this.getFromUserName()).append("]]></FromUserName>");
        sb.append("<CreateTime>").append(this.getCreateTime()).append("</CreateTime>");
        sb.append("<MsgType><![CDATA[" + this.msgType + "]]></MsgType>");
        sb.append("<Video>");
        sb.append("<MediaId><![CDATA[").append(this.getVideo().getMediaId()).append("]]></MediaId>");
        sb.append("<Title><![CDATA[").append(this.getVideo().getTitle()).append("]]></Title>");
        sb.append("<Description><![CDATA[").append(this.getVideo().getDescription()).append("]]></Description>");
        sb.append("</Video>");
        sb.append("</xml>");
        return sb.toString();
    }
}
