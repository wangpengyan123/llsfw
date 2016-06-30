/**
 * IncorrectCaptchaException.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * <p>
 * ClassName: IncorrectCaptchaException
 * </p>
 * <p>
 * Description: 验证码校验异常
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class IncorrectCaptchaException extends AuthenticationException { 

    /**
     * 序列号
     */
    private static final long serialVersionUID = 466678921119504585L;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    public IncorrectCaptchaException() {
        super();
    }

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param message 异常信息
     * @param cause 堆栈
     */
    public IncorrectCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param message 异常信息
     */
    public IncorrectCaptchaException(String message) {
        super(message);
    }

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param cause 堆栈
     */
    public IncorrectCaptchaException(Throwable cause) {
        super(cause);
    }

}
