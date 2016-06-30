/**
 * ClearScheduledLog.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.llsfw.core.service.job.JobService;

/**
 * <p>
 * ClassName: ClearScheduledLog
 * </p>
 * <p>
 * Description: 清理计划任务日志
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年3月7日
 * </p>
 */
@DisallowConcurrentExecution
public class ClearScheduledLog extends AbstractBaseJob {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobService js = this.getApplicationContext().getBean(JobService.class);
        js.clearScheduledLog();
        this.log.info("clearScheduledLog executeInternal end");
    }

}
