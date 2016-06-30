/**
 * TriggerDetailListener.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.scheduler.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.springframework.beans.factory.annotation.Autowired;

import com.llsfw.core.exception.SystemException;
import com.llsfw.core.service.quartz.TriggerListenerService;

/**
 * <p>
 * ClassName: TriggerListener
 * </p>
 * <p>
 * Description: 全局触发器监听
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年2月8日
 * </p>
 */
public class TriggerDetailListener implements org.quartz.TriggerListener {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field tls: 触发器监听服务
     * </p>
     */
    @Autowired
    private TriggerListenerService tls;

    @Override
    public String getName() {
        return "triggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) { // 2
        try {
            this.tls.saveTriggerFired(context);
        } catch (SystemException e) {
            this.log.error("triggerFired error:", e);
        }
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) { // 3
        boolean vetoed = false;
        try {
            vetoed = this.tls.saveVetoJobExecution(context);
        } catch (SystemException e) {
            this.log.error("vetoJobExecution error:", e);
        }
        return vetoed;
    }

    @Override
    public void triggerMisfired(Trigger trigger) { // 1
        this.tls.saveTriggerMisfired(trigger);
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context,
            CompletedExecutionInstruction triggerInstructionCode) { // 7
        try {
            this.tls.saveTriggerComplete(context, triggerInstructionCode);
        } catch (SystemException e) {
            this.log.error("triggerComplete error:", e);
        }
    }
}
