/**
 * BaseMedia.java
 * Created at 2016-04-26
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.media;

import java.io.Serializable;

/**
 * <p>
 * ClassName: BaseMedia
 * </p>
 * <p>
 * Description: 素材父类
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月26日
 * </p>
 */
public class BaseMedia implements Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -2418842333469156509L;
    /**
     * <p>
     * Field media_id: 素材ID
     * </p>
     */
    private String media_id;

    public String getMedia_id() {
        return this.media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

}
