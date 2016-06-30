/**
 * JobDetailListener.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.scheduler.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;

import com.llsfw.core.exception.SystemException;
import com.llsfw.core.service.quartz.TriggerListenerService;

/**
 * <p>
 * ClassName: JobListener
 * </p>
 * <p>
 * Description: 作业监听器
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年2月8日
 * </p>
 */
public class JobDetailListener implements org.quartz.JobListener {

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
        return "jobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) { // 4
        try {
            this.tls.saveJobToBeExecuted(context);
        } catch (SystemException e) {
            this.log.error("jobToBeExecuted:", e);
        }
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) { // 5
        try {
            this.tls.saveJobExecutionVetoed(context);
        } catch (SystemException e) {
            this.log.error("jobExecutionVetoed:", e);
        }
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) { // 6
        try {
            this.tls.saveJobWasExecuted(context, jobException);
        } catch (SchedulerException e) {
            this.log.error("jobWasExecuted:", e);
        }
    }

}
