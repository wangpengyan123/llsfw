/**
 * ExceptionUtil.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.exception;

/**
 * <p>
 * ClassName: ExceptionUtil
 * </p>
 * <p>
 * Description: 异常工具类
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class ExceptionUtil {

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    private ExceptionUtil() {

    }

    /**
     * <p>
     * 将异常堆栈信息以字符串的格式返回
     * </p>
     * 
     * @param e 异常对象
     * @return 格式化的异常信息
     */
    public static String createStackTrackMessage(Exception e) {
        StringBuilder messsage = new StringBuilder();
        if (e != null) {
            messsage.append(e.getClass()).append(" : ").append(e.getMessage()).append("<br />");
            StackTraceElement[] elements = e.getStackTrace();
            for (StackTraceElement stackTraceElement : elements) {
                messsage.append("&nbsp;&nbsp;&nbsp;&nbsp;").append(stackTraceElement.toString()).append("<br />");
            }
        }
        return messsage.toString();
    }

}
