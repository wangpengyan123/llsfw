/**
 * ImageOutputMessage.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.passive;

import com.llsfw.wx.api.impl.message.output.AbstractOutputMessage;

/**
 * <p>
 * ClassName: ImageOutputMessage
 * </p>
 * <p>
 * Description: 回复图片消息
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
public class ImageOutputMessage extends AbstractOutputMessage {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -1520567061410542950L;

    /**
     * <p>
     * Field msgType: image
     * </p>
     */
    private String msgType = "image";

    /**
     * <p>
     * Field image: 通过上传多媒体文件，得到的id
     * </p>
     */
    private Image image;

    public String getMsgType() {
        return this.msgType;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[").append(this.getToUserName()).append("]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[").append(this.getFromUserName()).append("]]></FromUserName>");
        sb.append("<CreateTime>").append(this.getCreateTime()).append("</CreateTime>");
        sb.append("<MsgType><![CDATA[" + this.msgType + "]]></MsgType>");
        sb.append("<Image>");
        sb.append("<MediaId><![CDATA[").append(this.getImage().getMediaId()).append("]]></MediaId>");
        sb.append("</Image>");
        sb.append("</xml>");
        return sb.toString();
    }

}
