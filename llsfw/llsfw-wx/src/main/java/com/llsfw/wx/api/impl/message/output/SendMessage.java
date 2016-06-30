/**
 * SendMessage.java
 * Created at 2016-04-26
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output;

import java.util.List;

import com.llsfw.wx.api.impl.base.BaseResult;
import com.llsfw.wx.api.impl.message.output.active.Articles;
import com.llsfw.wx.api.impl.message.output.active.CustomserviceSend;
import com.llsfw.wx.api.impl.message.output.active.Image;
import com.llsfw.wx.api.impl.message.output.active.Music;
import com.llsfw.wx.api.impl.message.output.active.Text;
import com.llsfw.wx.api.impl.message.output.active.Video;
import com.llsfw.wx.api.impl.message.output.active.Voice;
import com.llsfw.wx.api.impl.message.output.active.Wxcard;

/**
 * <p>
 * ClassName: SendMessage
 * </p>
 * <p>
 * Description: 发送消息
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月26日
 * </p>
 */
public class SendMessage extends BaseResult {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 9181978258196786514L;

    /**
     * <p>
     * Field touser: 普通用户openid
     * </p>
     */
    private String touser;

    /**
     * <p>
     * Field msgtype: 消息类型，文本为text，图片为image，语音为voice，视频消息为video，音乐消息为music，图文消息为news，卡券为wxcard
     * </p>
     */
    private String msgtype;

    /**
     * <p>
     * Field customservice: 以客服帐号来发消息
     * </p>
     */
    private CustomserviceSend customservice;

    /**
     * <p>
     * Field text: 文本消息
     * </p>
     */
    private Text text;

    /**
     * <p>
     * Field image: 图片消息
     * </p>
     */
    private Image image;

    /**
     * <p>
     * Field voice: 语音消息
     * </p>
     */
    private Voice voice;

    /**
     * <p>
     * Field video: 视频消息
     * </p>
     */
    private Video video;

    /**
     * <p>
     * Field music: 音乐消息
     * </p>
     */
    private Music music;

    /**
     * <p>
     * Field news: 图文消息
     * </p>
     */
    private List<Articles> news;

    /**
     * <p>
     * Field wxcard: 卡券消息
     * </p>
     */
    private Wxcard wxcard;

    public Text getText() {
        return this.text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Voice getVoice() {
        return this.voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    public Video getVideo() {
        return this.video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Music getMusic() {
        return this.music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public List<Articles> getNews() {
        return this.news;
    }

    public void setNews(List<Articles> news) {
        this.news = news;
    }

    public Wxcard getWxcard() {
        return this.wxcard;
    }

    public void setWxcard(Wxcard wxcard) {
        this.wxcard = wxcard;
    }

    public CustomserviceSend getCustomservice() {
        return this.customservice;
    }

    public void setCustomservice(CustomserviceSend customservice) {
        this.customservice = customservice;
    }

    public String getTouser() {
        return this.touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return this.msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

}
