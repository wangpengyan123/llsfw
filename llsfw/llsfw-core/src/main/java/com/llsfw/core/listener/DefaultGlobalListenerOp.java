/**
 * DefaultGlobalListenerOp.java
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * ClassName: DefaultGlobalListenerOp
 * </p>
 * <p>
 * Description: 全局监听操作类
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class DefaultGlobalListenerOp implements IGlobalListenerOp {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        this.log.trace(srae.toString());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        this.log.trace(srae.toString());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        this.log.trace(srae.toString());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        this.log.trace(sre.toString());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        this.log.trace(sre.toString());
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        this.log.trace(se.toString());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        this.log.trace(se.toString());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        this.log.trace(se.toString());
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        this.log.trace(se.toString());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        this.log.trace(se.toString());
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        this.log.trace(scae.toString());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        this.log.trace(scae.toString());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        this.log.trace(scae.toString());
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.log.trace(sce.toString());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        this.log.trace(sce.toString());
    }

}
