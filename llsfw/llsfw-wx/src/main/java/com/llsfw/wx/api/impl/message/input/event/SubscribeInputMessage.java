/**
 * SubscribeInputMessage.java
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
 * ClassName: SubscribeInputMessage
 * </p>
 * <p>
 * Description: 关注事件(和"扫描带参数二维码事件"事件使用相同事件类型,判断eventKey和ticket来区分两种事件)
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
@XmlRootElement(name = "xml")
public class SubscribeInputMessage extends BaseInputMessage {

    /**
     * <p>
     * Field eventKey: 事件KEY值，qrscene_为前缀，后面为二维码的参数值
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
