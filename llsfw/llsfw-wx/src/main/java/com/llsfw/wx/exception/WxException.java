/**
 * WxException.java
 * Created at 2016-04-23
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.exception;

import com.llsfw.core.exception.SystemException;

/**
 * <p>
 * ClassName: WxException
 * </p>
 * <p>
 * Description: 微信操作全局异常
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月23日
 * </p>
 */
public class WxException extends SystemException {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -8717453556136071643L;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param message 异常信息
     */
    public WxException(String message) {
        super(message);
    }

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param cause 堆栈
     */
    public WxException(Throwable cause) {
        super(cause);
    }

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param message 异常信息
     * @param cause 堆栈
     */
    public WxException(String message, Throwable cause) {
        super(message, cause);
    }

}
