/**
 * CustomserviceSend.java
 * Created at 2016-04-26
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.active;

import java.io.Serializable;

/**
 * <p>
 * ClassName: CustomserviceSend
 * </p>
 * <p>
 * Description: 客服
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月26日
 * </p>
 */
public class CustomserviceSend implements Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 3580916054687192839L;

    /**
     * <p>
     * Field kf_account: 客服账号
     * </p>
     */
    private String kf_account;

    public String getKf_account() {
        return this.kf_account;
    }

    public void setKf_account(String kf_account) {
        this.kf_account = kf_account;
    }

}
