/**
 * VoiceInputMessage.java
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
 * ClassName: VoiceInputMessage
 * </p>
 * <p>
 * Description: 语音消息
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
@XmlRootElement(name = "xml")
public class VoiceInputMessage extends BaseInputMessage {

    /**
     * <p>
     * Field mediaId: 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
     * </p>
     */
    private String mediaId;

    /**
     * <p>
     * Field format: 语音格式，如amr，speex等
     * </p>
     */
    private String format;

    /**
     * <p>
     * Field recognition: 语音识别结果，使用UTF8编码
     * </p>
     */
    private String recognition;

    public String getRecognition() {
        return this.recognition;
    }

    @XmlElement(name = "Recognition")
    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    @XmlElement(name = "MediaId")
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return this.format;
    }

    @XmlElement(name = "Format")
    public void setFormat(String format) {
        this.format = format;
    }

}
