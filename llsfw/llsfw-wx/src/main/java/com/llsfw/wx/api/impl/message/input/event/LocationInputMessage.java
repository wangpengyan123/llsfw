/**
 * LocationInputMessage.java
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
 * ClassName: LocationEventMessage
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
     * Field patitude: 地理位置纬度
     * </p>
     */
    private String latitude;

    /**
     * <p>
     * Field longitude: 地理位置经度
     * </p>
     */
    private String longitude;

    /**
     * <p>
     * Field precision: 地理位置精度
     * </p>
     */
    private String precision;

    public String getLatitude() {
        return this.latitude;
    }

    @XmlElement(name = "Latitude")
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    @XmlElement(name = "Longitude")
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrecision() {
        return this.precision;
    }

    @XmlElement(name = "Precision")
    public void setPrecision(String precision) {
        this.precision = precision;
    }

}
