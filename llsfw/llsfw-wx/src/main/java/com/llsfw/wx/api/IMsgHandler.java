/**
 * IMsgHandler.java
 * Created at 2016-04-24
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api;

import com.llsfw.wx.api.impl.message.output.AbstractOutputMessage;
import com.llsfw.wx.exception.WxException;

/**
 * <p>
 * ClassName: IMsgHandler
 * </p>
 * <p>
 * Description: 消息处理
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月24日
 * </p>
 */
public interface IMsgHandler {

    /**
     * <p>
     * Description: 消息处理
     * </p>
     * 
     * @param businessCode 业务代码
     * @param inputMessage input消息
     * @return output消息
     * @throws WxException 异常
     */
    public AbstractOutputMessage handler(String businessCode, String inputMessage) throws WxException;
}
