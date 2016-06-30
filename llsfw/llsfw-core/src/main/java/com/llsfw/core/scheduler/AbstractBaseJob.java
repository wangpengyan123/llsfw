/**
 * AbstractBaseJob.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.scheduler;

import java.util.Random;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * <p>
 * ClassName: BaseJob
 * </p>
 * <p>
 * Description: 计划任务父类
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月27日
 * </p>
 */
public abstract class AbstractBaseJob extends QuartzJobBean {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field applicationContext: spring上下文
     * </p>
     */
    private ApplicationContext applicationContext;

    public ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * <p>
     * Description: 任务执行方法
     * </p>
     * 
     * @param jobExecutionContext 任务上下文
     * @throws JobExecutionException 任务执行异常
     */
    @Override
    protected abstract void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException;

    /**
     * <p>
     * Description: 休眠方法
     * </p>
     * 
     * @param name 名字
     * @param time 时间
     */
    public void sleep(String name, int time) {
        // 休眠
        try {
            Random r = new Random();
            int rInt = r.nextInt(time);
            this.log.info(name + "本次休眠时间为" + rInt + "毫秒");
            Thread.sleep(rInt);
        } catch (InterruptedException e) {
            this.log.error("sleep:", e);
        }
    }
}
