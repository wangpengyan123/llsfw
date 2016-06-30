/**
 * JobService.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.service.job;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.service.base.BaseService;
import com.llsfw.generator.mapper.standard.system.TtScheduledLogMapper;
import com.llsfw.generator.mapper.standard.system.TtScheduledTriggerLogMapper;
import com.llsfw.generator.model.standard.system.TtScheduledLogCriteria;
import com.llsfw.generator.model.standard.system.TtScheduledTriggerLogCriteria;

/**
 * <p>
 * ClassName: JobService
 * </p>
 * <p>
 * Description: 计划任务相关操作
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年3月7日
 * </p>
 */
@Service
public class JobService extends BaseService {

    /**
     * <p>
     * Field tslm: 计划任务日志服务
     * </p>
     */
    @Autowired
    private TtScheduledLogMapper tslm;

    /**
     * <p>
     * Field tstlm: 计划任务执行日志服务
     * </p>
     */
    @Autowired
    private TtScheduledTriggerLogMapper tstlm;

    /**
     * <p>
     * Description: 清理计划任务日志
     * </p>
     */
    public void clearScheduledLog() {

        // 获取参数
        String scheduledLogKeepTime = this.getPs().getServerParamter("SCHEDULED_LOG_KEEP_TIME");

        // 计算归档日期
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date());
        cd.add(Calendar.DATE, 0 - Integer.parseInt(scheduledLogKeepTime));

        // 条件
        TtScheduledLogCriteria tac = new TtScheduledLogCriteria();
        tac.createCriteria().andCreateDateLessThanOrEqualTo(cd.getTime());

        // 删除
        this.tslm.deleteByExample(tac);
    }

    /**
     * <p>
     * Description: 清理计划任务执行日志
     * </p>
     */
    public void clearScheduledTriggerLog() {

        // 获取参数
        String scheduledTriggerLogKeepTime = this.getPs().getServerParamter("SCHEDULED_TRIGGER_LOG_KEEP_TIME");

        // 计算归档日期
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date());
        cd.add(Calendar.DATE, 0 - Integer.parseInt(scheduledTriggerLogKeepTime));

        // 条件
        TtScheduledTriggerLogCriteria tac = new TtScheduledTriggerLogCriteria();
        tac.createCriteria().andCreateDateLessThanOrEqualTo(cd.getTime());

        // 删除
        this.tstlm.deleteByExample(tac);
    }
}
