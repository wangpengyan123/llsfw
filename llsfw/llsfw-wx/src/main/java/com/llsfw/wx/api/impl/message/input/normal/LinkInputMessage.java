/**
 * LinkInputMessage.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.input.normal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.llsfw.wx.api.impl.message.input.BaseInputMessage;

/**
 * <p>
 * ClassName: LinkInputMessage
 * </p>
 * <p>
 * Description: 链接消息
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
@XmlRootElement(name = "xml")
public class LinkInputMessage extends BaseInputMessage {

    /**
     * <p>
     * Field title: 消息标题
     * </p>
     */
    private String title;

    /**
     * <p>
     * Field description: 消息描述
     * </p>
     */
    private String description;

    /**
     * <p>
     * Field url: 消息链接
     * </p>
     */
    private String url;

    public String getTitle() {
        return this.title;
    }

    @XmlElement(name = "Title")
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    @XmlElement(name = "Description")
    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return this.url;
    }

    @XmlElement(name = "Url")
    public void setUrl(String url) {
        this.url = url;
    }

}
