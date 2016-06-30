/**
 * SystemException.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.exception;

/**
 * <p>
 * ClassName: SystemException
 * </p>
 * <p>
 * Description: 系统异常
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class SystemException extends Exception {

    /**
     * <p>
     * Field serialVersionUID: 序列号
     * </p>
     */
    private static final long serialVersionUID = 1L;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param message 异常信息
     */
    public SystemException(String message) {
        super(message);
    }

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param cause 堆栈
     */
    public SystemException(Throwable cause) {
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
    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

}
