/**
 * MediaResut.java
 * Created at 2016-04-26
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.media;

import com.llsfw.wx.api.impl.base.BaseResult;

/**
 * <p>
 * ClassName: MediaResut
 * </p>
 * <p>
 * Description: 素材返回
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月26日
 * </p>
 */
public class MediaResut extends BaseResult {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -1092929744759216244L;

    /**
     * <p>
     * Field media_id: 媒体文件/图文消息上传后获取的唯一标识
     * </p>
     */
    private String media_id;

    /**
     * <p>
     * Field type: 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），此处为news，即图文消息
     * </p>
     */
    private String type;

    /**
     * <p>
     * Field created_at: 媒体文件上传时间
     * </p>
     */
    private Long created_at;

    public String getMedia_id() {
        return this.media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

}
