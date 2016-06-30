/**
 * TextOutputMessage.java
 * Created at 2016-04-24
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.passive;

import com.llsfw.wx.api.impl.message.output.AbstractOutputMessage;

/**
 * <p>
 * ClassName: TextOutputMessage
 * </p>
 * <p>
 * Description: 回复文本消息
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月24日
 * </p>
 */
public class TextOutputMessage extends AbstractOutputMessage {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -1520567061410542950L;

    /**
     * <p>
     * Field msgType: text
     * </p>
     */
    private String msgType = "text";

    /**
     * <p>
     * Field content: 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
     * </p>
     */
    private String content;

    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[").append(this.getToUserName()).append("]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[").append(this.getFromUserName()).append("]]></FromUserName>");
        sb.append("<CreateTime>").append(this.getCreateTime()).append("</CreateTime>");
        sb.append("<MsgType><![CDATA[" + this.getMsgType() + "]]></MsgType>");
        sb.append("<Content><![CDATA[").append(this.getContent()).append("]]></Content>");
        sb.append("</xml>");
        return sb.toString();
    }

}
