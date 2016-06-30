/**
 * IglobalListenerOp.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;

/**
 * <p>
 * ClassName: IGlobalListenerOp
 * </p>
 * <p>
 * Description: 全局监听器接口
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public interface IGlobalListenerOp {

    /**
     * <p>
     * Description: 请求参数添加
     * </p>
     * 
     * @param srae 事件
     */
    void attributeAdded(ServletRequestAttributeEvent srae);

    /**
     * <p>
     * Description: 请求参数移除
     * </p>
     * 
     * @param srae 事件
     */
    void attributeRemoved(ServletRequestAttributeEvent srae);

    /**
     * <p>
     * Description: 请求参数替换
     * </p>
     * 
     * @param srae 事件
     */
    void attributeReplaced(ServletRequestAttributeEvent srae);

    /**
     * <p>
     * Description: 请求销毁
     * </p>
     * 
     * @param sre 事件
     */
    void requestDestroyed(ServletRequestEvent sre);

    /**
     * <p>
     * Description: 请求初始化
     * </p>
     * 
     * @param sre 事件
     */
    void requestInitialized(ServletRequestEvent sre);

    /**
     * <p>
     * Description: 会话参数添加
     * </p>
     * 
     * @param se 事件
     */
    void attributeAdded(HttpSessionBindingEvent se);

    /**
     * <p>
     * Description: 会话参数移除
     * </p>
     * 
     * @param se 事件
     */
    void attributeRemoved(HttpSessionBindingEvent se);

    /**
     * <p>
     * Description: 会话参数替换
     * </p>
     * 
     * @param se 事件
     */
    void attributeReplaced(HttpSessionBindingEvent se);

    /**
     * <p>
     * Description: 会话创建
     * </p>
     * 
     * @param se 事件
     */
    void sessionCreated(HttpSessionEvent se);

    /**
     * <p>
     * Description: 会话销毁
     * </p>
     * 
     * @param se 事件
     */
    void sessionDestroyed(HttpSessionEvent se);

    /**
     * <p>
     * Description: 上下文参数添加
     * </p>
     * 
     * @param scae 事件
     */
    void attributeAdded(ServletContextAttributeEvent scae);

    /**
     * <p>
     * Description: 上下文参数移除
     * </p>
     * 
     * @param scae 事件
     */
    void attributeRemoved(ServletContextAttributeEvent scae);

    /**
     * <p>
     * Description: 上下文参数替换
     * </p>
     * 
     * @param scae 事件
     */
    void attributeReplaced(ServletContextAttributeEvent scae);

    /**
     * <p>
     * Description: 上下文创建
     * </p>
     * 
     * @param sce 事件
     */
    void contextInitialized(ServletContextEvent sce);

    /**
     * <p>
     * Description: 上下文销毁
     * </p>
     * 
     * @param sce 事件
     */
    void contextDestroyed(ServletContextEvent sce);

}
