/**
 * TextInputMessage.java
 * Created at 2016-04-24
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.input.normal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.llsfw.wx.api.impl.message.input.BaseInputMessage;

/**
 * <p>
 * ClassName: TextInputMessage
 * </p>
 * <p>
 * Description: 文本消息
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月24日
 * </p>
 */
@XmlRootElement(name = "xml")
public class TextInputMessage extends BaseInputMessage {
    /**
     * <p>
     * Field content: 文本消息内容
     * </p>
     */
    private String content;

    public String getContent() {
        return this.content;
    }

    @XmlElement(name = "Content")
    public void setContent(String content) {
        this.content = content;
    }

}
