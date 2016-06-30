/**
 * SendMassMessage.java
 * Created at 2016-04-26
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.active.mass;

import java.util.List;

import com.llsfw.wx.api.impl.base.BaseResult;
import com.llsfw.wx.api.impl.message.media.BaseMedia;
import com.llsfw.wx.api.impl.message.output.active.Text;
import com.llsfw.wx.api.impl.message.output.active.Wxcard;

/**
 * <p>
 * ClassName: SendMassMessage
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
public class SendMassMessage extends BaseResult {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 9181978258196786514L;

    /**
     * <p>
     * Field filter: 用于设定图文消息的接收者
     * </p>
     */
    private Filter filter;

    /**
     * <p>
     * Field towxname: 示例的微信号
     * </p>
     */
    private String towxname;

    /**
     * <p>
     * Field touser: 填写图文消息的接收者，一串OpenID列表，OpenID最少2个，最多10000个
     * </p>
     */
    private List<String> touser;

    /**
     * <p>
     * Field msgtype: 群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
     * </p>
     */
    private String msgtype;

    /**
     * <p>
     * Field mpnews: 图文消息
     * </p>
     */
    private BaseMedia mpnews;

    /**
     * <p>
     * Field text: 文本消息
     * </p>
     */
    private Text text;

    /**
     * <p>
     * Field voice: 语音消息
     * </p>
     */
    private BaseMedia voice;

    /**
     * <p>
     * Field image: 图片消息
     * </p>
     */
    private BaseMedia image;

    /**
     * <p>
     * Field mpvideo: 视频消息
     * </p>
     */
    private BaseMedia mpvideo;

    /**
     * <p>
     * Field video: 视频消息
     * </p>
     */
    private Video video;

    /**
     * <p>
     * Field wxcard: 卡券消息
     * </p>
     */
    private Wxcard wxcard;

    public Filter getFilter() {
        return this.filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public List<String> getTouser() {
        return this.touser;
    }

    public void setTouser(List<String> touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return this.msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public BaseMedia getMpnews() {
        return this.mpnews;
    }

    public void setMpnews(BaseMedia mpnews) {
        this.mpnews = mpnews;
    }

    public Text getText() {
        return this.text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public BaseMedia getVoice() {
        return this.voice;
    }

    public void setVoice(BaseMedia voice) {
        this.voice = voice;
    }

    public BaseMedia getImage() {
        return this.image;
    }

    public void setImage(BaseMedia image) {
        this.image = image;
    }

    public BaseMedia getMpvideo() {
        return this.mpvideo;
    }

    public void setMpvideo(BaseMedia mpvideo) {
        this.mpvideo = mpvideo;
    }

    public Wxcard getWxcard() {
        return this.wxcard;
    }

    public void setWxcard(Wxcard wxcard) {
        this.wxcard = wxcard;
    }

    public Video getVideo() {
        return this.video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getTowxname() {
        return this.towxname;
    }

    public void setTowxname(String towxname) {
        this.towxname = towxname;
    }

}
