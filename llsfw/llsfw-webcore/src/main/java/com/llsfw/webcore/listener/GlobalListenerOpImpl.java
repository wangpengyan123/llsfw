/**
 * GlobalListenerOpImpl.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.listener;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSessionEvent;

import org.springframework.beans.factory.annotation.Autowired;

import com.llsfw.core.listener.DefaultGlobalListenerOp;
import com.llsfw.core.service.applog.AppLogService;
import com.llsfw.generator.model.standard.system.TtOnlineSession;
import com.llsfw.generator.model.standard.system.TtOnlineSessionHis;

/**
 * <p>
 * ClassName: GlobalListenerOpImpl
 * </p>
 * <p>
 * Description: 全局监听操作实现
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
public class GlobalListenerOpImpl extends DefaultGlobalListenerOp {

    /**
     * <p>
     * Field als: 日志服务
     * </p>
     */
    @Autowired
    private AppLogService als;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.als.clearOnlineSession();
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        TtOnlineSession tos = new TtOnlineSession();
        tos.setCreateDate(new Date());
        tos.setSessionCreateDate(se.getSession().getCreationTime());
        tos.setSessionId(se.getSession().getId());
        this.als.sessionCreated(tos);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        TtOnlineSessionHis tosh = new TtOnlineSessionHis();
        tosh.setCreateDate(new Date());
        tosh.setSessionCreateDate(se.getSession().getCreationTime());
        tosh.setSessionDestoryedTime(new Date());
        tosh.setSessionId(se.getSession().getId());
        tosh.setSessionLastAccessedTime(se.getSession().getLastAccessedTime());
        tosh.setSessionMaxInactiveInterval(Long.valueOf(se.getSession().getMaxInactiveInterval()));
        this.als.sessionDestroyed(tosh);
    }

}
