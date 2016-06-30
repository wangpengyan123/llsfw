/**
 * ScanInputMessage.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.input.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.llsfw.wx.api.impl.message.input.BaseInputMessage;

/**
 * <p>
 * ClassName: ScanInputMessage
 * </p>
 * <p>
 * Description: 用户已关注时的事件推送
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
@XmlRootElement(name = "xml")
public class ScanInputMessage extends BaseInputMessage {

    /**
     * <p>
     * Field eventKey: 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
     * </p>
     */
    private String eventKey;

    /**
     * <p>
     * Field ticket: 二维码的ticket，可用来换取二维码图片
     * </p>
     */
    private String ticket;

    public String getEventKey() {
        return this.eventKey;
    }

    @XmlElement(name = "EventKey")
    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return this.ticket;
    }

    @XmlElement(name = "Ticket")
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

}
