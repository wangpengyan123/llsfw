/**
 * AppLogService.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.service.applog;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.llsfw.core.service.base.BaseService;
import com.llsfw.generator.mapper.standard.system.TtOnlineSessionHisMapper;
import com.llsfw.generator.mapper.standard.system.TtOnlineSessionMapper;
import com.llsfw.generator.mapper.standard.system.TtRequestResponseLogMapper;
import com.llsfw.generator.mapper.standard.system.TtScheduledLogMapper;
import com.llsfw.generator.model.standard.system.TtOnlineSession;
import com.llsfw.generator.model.standard.system.TtOnlineSessionCriteria;
import com.llsfw.generator.model.standard.system.TtOnlineSessionHis;
import com.llsfw.generator.model.standard.system.TtRequestResponseLog;
import com.llsfw.generator.model.standard.system.TtScheduledLog;

/**
 * <p>
 * ClassName: AppLogService
 * </p>
 * <p>
 * Description: 日志服务
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月17日
 * </p>
 */
@Service
public class AppLogService extends BaseService {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field tslm: 计划任务日志服务
     * </p>
     */
    @Autowired
    private TtScheduledLogMapper tslm;

    /**
     * <p>
     * Field tosm: 在线session服务
     * </p>
     */
    @Autowired
    private TtOnlineSessionMapper tosm;

    /**
     * <p>
     * Field toshm: 历史session服务
     * </p>
     */
    @Autowired
    private TtOnlineSessionHisMapper toshm;

    /**
     * <p>
     * Field trrlm: 请求日志服务
     * </p>
     */
    @Autowired
    private TtRequestResponseLogMapper trrlm;

    /**
     * 存储请求响应日志-该方法吃掉所有异常,不影响其他的应用-无事务-超时时间2秒
     * 
     * @param log 日志
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, timeout = 2)
    public void saveRequestResponseLog(TtRequestResponseLog log) {
        try {
            this.trrlm.insertSelective(log);
        } catch (DataAccessException e) {
            this.logger.error("saveRequestResponseLog:", e);
        }
    }

    /**
     * 清空OnlineSession表中的数据-该方法吃掉所有异常,不影响其他的应用-无事务-超时时间2秒
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, timeout = 2)
    public void clearOnlineSession() {
        try {
            this.tosm.deleteByExample(new TtOnlineSessionCriteria());
        } catch (DataAccessException e) {
            this.logger.error("clearOnlineSession:", e);
        }
    }

    /**
     * session创建记录-该方法吃掉所有异常,不影响其他的应用-无事务-超时时间2秒
     * 
     * @param tos 在线session
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, timeout = 2)
    public void sessionCreated(TtOnlineSession tos) {
        try {
            this.tosm.insertSelective(tos);
        } catch (DataAccessException e) {
            this.logger.error("sessionCreated:", e);
        }
    }

    /**
     * session销毁记录-该方法吃掉所有异常,不影响其他的应用-无事务-超时时间2秒
     * 
     * @param tosh session历史
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, timeout = 2)
    public void sessionDestroyed(TtOnlineSessionHis tosh) {
        try {
            this.tosm.deleteByPrimaryKey(tosh.getSessionId());
            this.toshm.insertSelective(tosh);
        } catch (DataAccessException e) {
            this.logger.error("sessionDestroyed:", e);
        }
    }

    /**
     * <p>
     * Description: 保存记录(此方法吃掉所有异常,避免异常日志切换形成递归.)
     * </p>
     * 
     * @param msg 信息
     */
    public void saveScheduledLog(String msg) {
        try {

            // 获得UUID
            String uuid = UUID.randomUUID().toString();

            // 设置
            TtScheduledLog tsl = new TtScheduledLog();
            tsl.setLogid(uuid);
            tsl.setCreateDate(new Date());
            tsl.setMsg(msg);

            // 保存
            this.tslm.insert(tsl);

        } catch (DataAccessException e) {
            this.logger.error("AppLogService.saveScheduledLog保存日志异常:", e);
        }
    }
}
