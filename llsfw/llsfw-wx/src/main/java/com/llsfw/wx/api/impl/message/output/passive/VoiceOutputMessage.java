/**
 * VoiceOutputMessage.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.passive;

import com.llsfw.wx.api.impl.message.output.AbstractOutputMessage;

/**
 * <p>
 * ClassName: VoiceOutputMessage
 * </p>
 * <p>
 * Description: 回复语音消息
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
public class VoiceOutputMessage extends AbstractOutputMessage {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -5096255190333101333L;

    /**
     * <p>
     * Field msgType: 语音消息
     * </p>
     */
    private String msgType = "voice";

    /**
     * <p>
     * Field voice: 通过上传多媒体文件，得到的id封装的Voice对象
     * </p>
     */
    private Voice voice;

    public Voice getVoice() {
        return this.voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
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
        sb.append("<Voice>");
        sb.append("<MediaId><![CDATA[").append(this.getVoice().getMediaId()).append("]]></MediaId>");
        sb.append("</Voice>");
        sb.append("</xml>");
        return sb.toString();
    }
}
