/**
 * LocationInputMessage.java
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
 * ClassName: LocationInputMessage
 * </p>
 * <p>
 * Description: 地理位置消息
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
@XmlRootElement(name = "xml")
public class LocationInputMessage extends BaseInputMessage {

    /**
     * <p>
     * Field location_x: 地理位置维度
     * </p>
     */
    private String location_x;

    /**
     * <p>
     * Field location_y: 地理位置经度
     * </p>
     */
    private String location_y;

    /**
     * <p>
     * Field scale: 地图缩放大小
     * </p>
     */
    private String scale;

    /**
     * <p>
     * Field label: 地理位置信息
     * </p>
     */
    private String label;

    public String getLocation_x() {
        return this.location_x;
    }

    @XmlElement(name = "Location_X")
    public void setLocation_x(String location_x) {
        this.location_x = location_x;
    }

    public String getLocation_y() {
        return this.location_y;
    }

    @XmlElement(name = "Location_Y")
    public void setLocation_y(String location_y) {
        this.location_y = location_y;
    }

    public String getScale() {
        return this.scale;
    }

    @XmlElement(name = "Scale")
    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return this.label;
    }

    @XmlElement(name = "Label")
    public void setLabel(String label) {
        this.label = label;
    }

}
