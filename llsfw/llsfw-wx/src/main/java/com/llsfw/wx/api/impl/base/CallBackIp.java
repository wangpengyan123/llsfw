/**
 * CallBackIp.java
 * Created at 2016-04-23
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.base;

import java.util.List;

/**
 * <p>
 * ClassName: CallBackIp
 * </p>
 * <p>
 * Description: 微信服务器IP地址
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月23日
 * </p>
 */
public class CallBackIp extends BaseResult {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 3494055148634029016L;

    /**
     * <p>
     * Field ip_list: 微信服务器IP地址列表
     * </p>
     */
    private List<String> ip_list;

    public List<String> getIp_list() {
        return this.ip_list;
    }

    public void setIp_list(List<String> ip_list) {
        this.ip_list = ip_list;
    }

}
