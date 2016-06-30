/**
 * MusicOutputMessage.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.passive;

import com.llsfw.wx.api.impl.message.output.AbstractOutputMessage;

/**
 * <p>
 * ClassName: MusicOutputMessage
 * </p>
 * <p>
 * Description: 回复音乐消息
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
public class MusicOutputMessage extends AbstractOutputMessage {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -5096255190333101333L;

    /**
     * <p>
     * Field msgType: 音乐消息
     * </p>
     */
    private String msgType = "music";

    /**
     * <p>
     * Field voice: 音乐消息对象
     * </p>
     */
    private Music music;

    public Music getMusic() {
        return this.music;
    }

    public void setMusic(Music music) {
        this.music = music;
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
        sb.append("<Music>");
        sb.append("<Title><![CDATA[").append(this.getMusic().getTitle()).append("]]></Title>");
        sb.append("<Description><![CDATA[").append(this.getMusic().getDescription()).append("]]></Description>");
        sb.append("<MusicUrl><![CDATA[").append(this.getMusic().getMusicUrl()).append("]]></MusicUrl>");
        sb.append("<HQMusicUrl><![CDATA[").append(this.getMusic().getHqMusicUrl()).append("]]></HQMusicUrl>");
        sb.append("<ThumbMediaId><![CDATA[").append(this.getMusic().getThumbMediaId()).append("]]></ThumbMediaId>");
        sb.append("</Music>");
        sb.append("</xml>");
        return sb.toString();
    }
}
