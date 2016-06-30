/**
 * BaseInputMessage.java
 * Created at 2016-04-24
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.input;

import javax.xml.bind.annotation.XmlElement;

/**
 * <p>
 * ClassName: BaseInputMessage
 * </p>
 * <p>
 * Description: 输入消息基类
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月23日
 * </p>
 */
public class BaseInputMessage {

    /**
     * <p>
     * Field toUserName: 开发者微信号
     * </p>
     */
    private String toUserName;
    /**
     * <p>
     * Field fromUserName: 发送方帐号（一个OpenID）
     * </p>
     */
    private String fromUserName;
    /**
     * <p>
     * Field createTime: 消息创建时间 （整型）
     * </p>
     */
    private Long createTime;

    /**
     * <p>
     * Field msgType: 消息类型
     * </p>
     */
    private String msgType;

    /**
     * <p>
     * Field Event: 事件类型
     * </p>
     */
    private String event;

    /**
     * <p>
     * Field msgId: 消息id，64位整型
     * </p>
     */
    private Long msgId;

    public String getToUserName() {
        return this.toUserName;
    }

    @XmlElement(name = "ToUserName")
    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return this.fromUserName;
    }

    @XmlElement(name = "FromUserName")
    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    @XmlElement(name = "CreateTime")
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return this.msgType;
    }

    @XmlElement(name = "MsgType")
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getEvent() {
        return this.event;
    }

    @XmlElement(name = "Event")
    public void setEvent(String event) {
        this.event = event;
    }

    public Long getMsgId() {
        return this.msgId;
    }

    @XmlElement(name = "MsgId")
    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

}
