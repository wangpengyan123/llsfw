/**
 * ClickInputMessage.java
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
 * ClassName: ClickInputMessage
 * </p>
 * <p>
 * Description: 自定义菜单事件(点击菜单拉取消息时的事件推送)
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
@XmlRootElement(name = "xml")
public class ClickInputMessage extends BaseInputMessage {

    /**
     * <p>
     * Field eventKey: 事件KEY值，与自定义菜单接口中KEY值对应
     * </p>
     */
    private String eventKey;

    public String getEventKey() {
        return this.eventKey;
    }

    @XmlElement(name = "EventKey")
    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

}
