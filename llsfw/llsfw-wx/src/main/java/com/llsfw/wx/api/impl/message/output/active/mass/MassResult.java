/**
 * MassResult.java
 * Created at 2016-04-26
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.active.mass;

import com.llsfw.wx.api.impl.base.BaseResult;

/**
 * <p>
 * ClassName: MassResult
 * </p>
 * <p>
 * Description: 群发消息返回结果
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月26日
 * </p>
 */
public class MassResult extends BaseResult {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -3039657301824391731L;

    /**
     * <p>
     * Field msg_id: 消息发送任务的ID
     * </p>
     */
    private String msg_id;

    /**
     * <p>
     * Field msg_data_id:
     * 消息的数据ID，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中，获取到对应的图文消息的数据，是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
     * </p>
     */
    private String msg_data_id;

    /**
     * <p>
     * Field msg_status: 消息发送后的状态，SEND_SUCCESS表示发送成功
     * </p>
     */
    private String msg_status;

    public String getMsg_status() {
        return this.msg_status;
    }

    public void setMsg_status(String msg_status) {
        this.msg_status = msg_status;
    }

    public String getMsg_id() {
        return this.msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_data_id() {
        return this.msg_data_id;
    }

    public void setMsg_data_id(String msg_data_id) {
        this.msg_data_id = msg_data_id;
    }

}
