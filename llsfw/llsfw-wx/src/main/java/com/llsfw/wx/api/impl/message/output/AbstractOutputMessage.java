/**
 * AbstractOutputMessage.java
 * Created at 2016-04-24
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output;

import java.util.Date;

import com.llsfw.wx.api.impl.message.input.BaseInputMessage;

/**
 * <p>
 * ClassName: AbstractOutputMessage
 * </p>
 * <p>
 * Description: 回复消息基类
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月24日
 * </p>
 */
public abstract class AbstractOutputMessage implements java.io.Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 4836512097014902549L;

    /**
     * <p>
     * Field toUserName: 接收方帐号（收到的OpenID）
     * </p>
     */
    private String toUserName;

    /**
     * <p>
     * Field fromUserName: 开发者微信号
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
     * Description: 将对象转为XML字符串
     * </p>
     * 
     * @return this.XML字符串
     */
    public abstract String toXml();

    /**
     * <p>
     * Description: 初始化基本参数(消息发送方,消息接收方,消息创建时间)
     * </p>
     * 
     * @param inputMessage input消息
     */
    public void initBaseParam(BaseInputMessage inputMessage) {
        this.setToUserName(inputMessage.getFromUserName());
        this.setFromUserName(inputMessage.getToUserName());
        this.setCreateTime(new Date().getTime());
    }

    public String getToUserName() {
        return this.toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return this.fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}
