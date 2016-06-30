/**
 * QuartzService.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.service.quartz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.support.RequestContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.llsfw.core.common.Constants;
import com.llsfw.core.common.DataGridJsonResult;
import com.llsfw.core.common.DateUtil;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.exception.SystemRuntimeException;
import com.llsfw.core.pagequery.PageInterceptor;
import com.llsfw.core.pagequery.PageResult;
import com.llsfw.core.service.base.BaseService;
import com.llsfw.webcore.mapper.expand.quartz.IQuartzMapper;

/**
 * <p>
 * ClassName: QuartzService
 * </p>
 * <p>
 * Description: 计划任务服务
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Service
public class QuartzService extends BaseService {

    /**
     * <p>
     * Field TOTAL: 常量
     * </p>
     */
    private static final String TOTAL = "total";

    /**
     * <p>
     * Field ROWS: 常量
     * </p>
     */
    private static final String ROWS = "rows";

    /**
     * <p>
     * Field TEXT: 常量
     * </p>
     */
    private static final String TEXT = "text";

    /**
     * <p>
     * Field VALUE: 常量
     * </p>
     */
    private static final String VALUE = "value";

    /**
     * <p>
     * Field STATUS: 常量
     * </p>
     */
    private static final String STATUS = "STATUS";

    /**
     * <p>
     * Field STATUS_COUNT: 常量
     * </p>
     */
    private static final String STATUS_COUNT = "STATUS_COUNT";

    /**
     * <p>
     * Field CHAR_NAME: 常量
     * </p>
     */
    private static final String CHAR_NAME = "name";

    /**
     * <p>
     * Field PERCENT_SIGN: 常量
     * </p>
     */
    private static final String PERCENT_SIGN = "%";

    /**
     * <p>
     * Field s: Scheduler服务
     * </p>
     */
    @Autowired
    @Qualifier("clusterQuartzScheduler")
    private SchedulerFactoryBean s;

    /**
     * <p>
     * Field qm: 计划任务mapper
     * </p>
     */
    @Autowired
    private IQuartzMapper qm;

    /**
     * <p>
     * Description: 计划任务执行状态报表
     * </p>
     * 
     * @return 结果 页面
     * @throws JsonProcessingException 异常
     */
    public List<Map<String, Object>> reportSchedulerRunStatus() {
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> data = this.qm.reportSchedulerRunStatus();
        if (!CollectionUtils.isEmpty(data)) {
            for (Map<String, Object> source : data) {
                Map<String, Object> item = new HashMap<String, Object>();
                item.put(CHAR_NAME, source.get(STATUS));
                item.put("y", source.get(STATUS_COUNT));
                rv.add(item);
            }
        }
        return rv;
    }

    /**
     * 触发器执行状态统计
     * 
     * @param triggerName 触发器名称 触发器名称
     * @param triggerGroup 触发器组别 触发器组别
     * @return 结果 页面
     * @throws JsonProcessingException 异常
     */
    public List<Map<String, Object>> reportTriggerRunStatus(String triggerName, String triggerGroup) {
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> data = this.qm.reportTriggerRunStatus(triggerName, triggerGroup);
        if (!CollectionUtils.isEmpty(data)) {
            for (Map<String, Object> source : data) {
                Map<String, Object> item = new HashMap<String, Object>();
                item.put(CHAR_NAME, source.get(STATUS));
                item.put("y", source.get(STATUS_COUNT));
                rv.add(item);
            }
        }
        return rv;
    }

    /**
     * 作业执行状态统计
     * 
     * @param jobName 作业名称 作业名称
     * @param jobGroup 作业组别 作业组别
     * @return 结果 页面
     * @throws JsonProcessingException 异常
     */
    public List<Map<String, Object>> reportJobRunStatus(String jobName, String jobGroup) {
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> data = this.qm.reportJobRunStatus(jobName, jobGroup);
        if (!CollectionUtils.isEmpty(data)) {
            for (Map<String, Object> source : data) {
                Map<String, Object> item = new HashMap<String, Object>();
                item.put(CHAR_NAME, source.get(STATUS));
                item.put("y", source.get(STATUS_COUNT));
                rv.add(item);
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 清除计划任务数据
     * </p>
     * 
     * @param requestContext 请求对象
     * @param pswd 操作密码
     * @return 结果 操作结果
     * @throws SchedulerException 异常 计划任务异常
     */
    public JsonResult<String> schedulerClear(RequestContext requestContext, String pswd) throws SchedulerException {
        if (Constants.SCHEDULER_CLEAR_OP_PSWD.equals(pswd)) {
            this.s.getScheduler().clear();
        } else {
            return new JsonResult<String>(Constants.FAIL, requestContext.getMessage("quartz.page.server.msg.1"));
        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * 加载计划任务操作历史
     * 
     * @param page 页数
     * @param rows 行数
     * @return 结果 结果
     */
    public DataGridJsonResult<Map<String, Object>> loadSchedulerLogList(int page, int rows) {
        PageInterceptor.startPage(rows, page);
        PageResult<Map<String, Object>> pr = PageInterceptor.getPageResult(this.qm.loadSchedulerOptionHisPageQuery());
        return new DataGridJsonResult<Map<String, Object>>(pr.getTotalRecords(), pr.toList());
    }

    /**
     * 返回计划任务详情
     * 
     * @return 结果
     */
    public List<Map<String, Object>> loadSchedulerState() {
        return this.qm.loadSchedulerState();
    }

    /**
     * 返回当前正在执行的任务
     * 
     * @return 结果
     */
    public List<Map<String, Object>> loadCurrentlyEexutingJobs() {
        return this.qm.loadCurrentlyEexutingJobs();
    }

    /**
     * 恢复触发器
     * 
     * @param triggerName 触发器名称 触发器名称
     * @param triggerGroup 触发器组别 触发器组别
     * @return 结果
     * @throws SchedulerException 异常 异常
     */
    public JsonResult<String> resumeTriggerOp(String triggerName, String triggerGroup) throws SchedulerException {
        // 获得计划任务管理器
        Scheduler sch = this.s.getScheduler();
        // 获得TriggerKey
        TriggerKey tk = getTriggerKey(sch, triggerName, triggerGroup);
        // 判断是否为空
        if (tk != null) {
            // 暂停trigger
            sch.resumeTrigger(tk);
        } else {
            return new JsonResult<String>(Constants.FAIL, null);
        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * 暂停触发器
     * 
     * @param triggerName 触发器名称 触发器名称
     * @param triggerGroup 触发器组别 触发器组别
     * @return 结果
     * @throws SchedulerException 异常 异常
     */
    public JsonResult<String> puseTriggerOp(String triggerName, String triggerGroup) throws SchedulerException {
        // 获得计划任务管理器
        Scheduler sch = this.s.getScheduler();
        // 获得TriggerKey
        TriggerKey tk = getTriggerKey(sch, triggerName, triggerGroup);
        // 判断是否为空
        if (tk != null) {
            // 暂停trigger
            sch.pauseTrigger(tk);
        } else {
            return new JsonResult<String>(Constants.FAIL, null);
        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * 删除trigger
     * 
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @return 结果
     * @throws SchedulerException 异常
     */
    public JsonResult<String> deleteTriggerOp(String triggerName, String triggerGroup) throws SchedulerException {
        // 获得计划任务管理器
        Scheduler sch = this.s.getScheduler();
        // 获得TriggerKey
        TriggerKey tk = getTriggerKey(sch, triggerName, triggerGroup);
        // 判断是否为空
        if (tk != null) {
            // 移除trigger
            sch.unscheduleJob(tk);
        } else {
            return new JsonResult<String>(Constants.FAIL, null);
        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 添加CronTrigger
     * </p>
     * 
     * @param requestContext 请求对象
     * @param jName 作业名称
     * @param jGroup 作业组别
     * @param tName 触发器名称
     * @param tGroup 触发器组别
     * @param tDescription 触发器描述
     * @param triggerStartTime 触发器开始时间
     * @param triggerEndTime 触发器结束时间
     * @param priority 优先级
     * @param misfireInstruction 失效机制
     * @param triggerCalendar 触发器日历
     * @param triggerCronExcp cron表达式
     * @param timeZoneId 时区ID
     * @param cronTriggerDetailDataMapHid 触发器参数
     * @return 结果
     * @throws SchedulerException 异常
     * @throws IOException 异常
     */
    public JsonResult<String> addCronTirgger(RequestContext requestContext, String jName, String jGroup, String tName,
            String tGroup, String tDescription, Date triggerStartTime, Date triggerEndTime, Integer priority,
            Integer misfireInstruction, String triggerCalendar, String triggerCronExcp, String timeZoneId,
            String cronTriggerDetailDataMapHid) throws SchedulerException, IOException {

        // 获得计划任务管理器
        Scheduler sch = this.s.getScheduler();

        // 设置jobkey
        JobKey jk = new JobKey(jName, jGroup);

        // 获得jobDataMap
        JobDataMap jobDataMap = this.getJobDataMap(cronTriggerDetailDataMapHid, requestContext);

        // 判断jobDetail
        if (!sch.checkExists(jk)) {
            return new JsonResult<String>(Constants.FAIL, requestContext.getMessage("quartz.page.server.msg.2"));
        }

        // 设置triggerkey
        TriggerKey tk = new TriggerKey(tName, tGroup);

        // 获得triggerBuilder
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();

        // 设置triggerBuilder
        triggerBuilder.withIdentity(tk); // 设置触发器对象
        triggerBuilder.usingJobData(jobDataMap); // 设置jobDataMap
        triggerBuilder.withPriority(priority); // 设置优先级
        if (triggerStartTime == null) { // 设置开始时间
            triggerBuilder.startNow();
        } else {
            triggerBuilder.startAt(triggerStartTime);
        }
        triggerBuilder.endAt(triggerEndTime); // 结束时间,可以为空
        triggerBuilder.forJob(jk); // 针对的作业
        triggerBuilder.withDescription(tDescription); // 设置描述
        if (!StringUtils.isEmpty(triggerCalendar)) { // 设置日历
            triggerBuilder.modifiedByCalendar(triggerCalendar);
        }

        // 获得CronTrigger
        CronScheduleBuilder cronTriggerBuilder = CronScheduleBuilder.cronSchedule(triggerCronExcp); // cron表达式
        cronTriggerBuilder.inTimeZone(TimeZone.getTimeZone(timeZoneId)); // 时区
        // 设置触发机制 默认 SimpleTrigger.MISFIRE_INSTRUCTION_SMART_POLICY
        if (CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING == misfireInstruction.intValue()) {
            cronTriggerBuilder.withMisfireHandlingInstructionDoNothing();
        } else if (CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW == misfireInstruction.intValue()) {
            cronTriggerBuilder.withMisfireHandlingInstructionFireAndProceed();
        } else if (CronTrigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY == misfireInstruction.intValue()) {
            cronTriggerBuilder.withMisfireHandlingInstructionIgnoreMisfires();
        }

        // 将cronTrigger设置到triggerBuilder中
        triggerBuilder.withSchedule(cronTriggerBuilder);

        // 构造Trigger
        Trigger cronTrigger = triggerBuilder.build();

        // 锁定触发器(避免偶尔修改失败的情况,不会对后续操作造成影响)
        if (lockTrigger(tk.getName(), tk.getGroup())) {
            // 判断trigger是否存在
            if (sch.checkExists(tk)) {
                // 获得当前触发器
                Trigger t = sch.getTrigger(tk);
                // 判断触发器关联
                if (jk.getName().equals(t.getJobKey().getName()) && jk.getGroup().equals(t.getJobKey().getGroup())) {
                    // 更新触发器
                    sch.rescheduleJob(tk, cronTrigger);

                } else {
                    return new JsonResult<String>(Constants.FAIL,
                            requestContext.getMessage("quartz.page.server.msg.3"));
                }
            } else {
                sch.scheduleJob(cronTrigger); // 创建触发器
            }
        } else {
            return new JsonResult<String>(Constants.FAIL, requestContext.getMessage("quartz.page.server.msg.4"));
        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * 返回日历列表
     * 
     * @return 结果
     * @throws SchedulerException 异常
     */
    public List<Map<String, Object>> getTimeZoneIdList() {
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        List<String> jgl = DateUtil.fecthAllTimeZoneIds();
        if (!CollectionUtils.isEmpty(jgl)) {
            for (String item : jgl) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(TEXT, "(UTC+" + DateUtil.getUtcTimeZoneRawOffset(item) + ")" + item);
                map.put(VALUE, item);
                rv.add(map);
            }
        }
        return rv;
    }

    /**
     * 返回MisfireInstruction列表
     * 
     * @return 结果
     * @throws SchedulerException 异常
     */
    public List<Map<String, Object>> getCronMisfireInstructionList() throws SchedulerException {
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(TEXT, "Do Nothing");
        map.put(VALUE, CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
        rv.add(map);
        map = new HashMap<String, Object>();
        map.put(TEXT, "Ignore Misfire Policy");
        map.put(VALUE, CronTrigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY);
        rv.add(map);
        map = new HashMap<String, Object>();
        map.put(TEXT, "Smart Policy");
        map.put(VALUE, CronTrigger.MISFIRE_INSTRUCTION_SMART_POLICY);
        rv.add(map);
        map = new HashMap<String, Object>();
        map.put(TEXT, "Fire Once Now");
        map.put(VALUE, CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW);
        rv.add(map);
        return rv;
    }

    /**
     * <p>
     * Description: 添加CronTrigger
     * </p>
     * 
     * @param requestContext 请求对象
     * @param jName 作业名称
     * @param jGroup 作业组别
     * @param tName 触发器名称
     * @param tGroup 触发器组别
     * @param tDescription 触发器描述
     * @param triggerStartTime 触发器开始时间
     * @param triggerEndTime 触发器结束时间
     * @param priority 优先级
     * @param misfireInstruction 失效机制
     * @param triggerCalendar 触发器日历
     * @param triggerRepeatCount 重复次数
     * @param intervalInMilliseconds 重复间隔
     * @param triggerDetailDataMapHid 触发器参数
     * @return 结果
     * @throws SchedulerException 异常
     * @throws IOException 异常
     */
    public JsonResult<String> addSimpleTirgger(RequestContext requestContext, String jName, String jGroup, String tName,
            String tGroup, String tDescription, Date triggerStartTime, Date triggerEndTime, Integer priority,
            Integer misfireInstruction, String triggerCalendar, Integer triggerRepeatCount, Long intervalInMilliseconds,
            String triggerDetailDataMapHid) throws SchedulerException, IOException {

        // 获得计划任务管理器
        Scheduler sch = this.s.getScheduler();

        // 设置jobkey
        JobKey jk = new JobKey(jName, jGroup);

        // 获得jobDataMap
        JobDataMap jobDataMap = this.getJobDataMap(triggerDetailDataMapHid, requestContext);

        // 判断jobDetail
        if (!sch.checkExists(jk)) {
            return new JsonResult<String>(Constants.FAIL, requestContext.getMessage("quartz.page.server.msg.2"));
        }

        // 设置triggerkey
        TriggerKey tk = new TriggerKey(tName, tGroup);

        // 获得triggerBuilder
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();

        // 设置triggerBuilder
        triggerBuilder.withIdentity(tk); // 设置触发器对象
        triggerBuilder.usingJobData(jobDataMap); // 设置jobDataMap
        triggerBuilder.withPriority(priority); // 设置优先级
        if (triggerStartTime == null) { // 设置开始时间
            triggerBuilder.startNow();
        } else {
            triggerBuilder.startAt(triggerStartTime);
        }
        triggerBuilder.endAt(triggerEndTime); // 结束时间,可以为空
        triggerBuilder.forJob(jk); // 针对的作业
        triggerBuilder.withDescription(tDescription); // 设置描述
        if (!StringUtils.isEmpty(triggerCalendar)) { // 设置日历
            triggerBuilder.modifiedByCalendar(triggerCalendar);
        }

        // 获得SimpleTrigger
        SimpleScheduleBuilder simpleTriggerBuilder = SimpleScheduleBuilder.simpleSchedule();
        if (triggerRepeatCount != null) { // 重复次数
            simpleTriggerBuilder.withRepeatCount(triggerRepeatCount);
        } else {
            simpleTriggerBuilder.withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        }
        simpleTriggerBuilder.withIntervalInMilliseconds(intervalInMilliseconds); // 重复时间
        // 设置触发机制 默认 SimpleTrigger.MISFIRE_INSTRUCTION_SMART_POLICY
        if (SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW == misfireInstruction.intValue()) {
            simpleTriggerBuilder.withMisfireHandlingInstructionFireNow();
        } else if (SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT == misfireInstruction
                .intValue()) {
            simpleTriggerBuilder.withMisfireHandlingInstructionNextWithExistingCount();
        } else if (SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT == misfireInstruction
                .intValue()) {
            simpleTriggerBuilder.withMisfireHandlingInstructionNextWithRemainingCount();
        } else if (SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT == misfireInstruction
                .intValue()) {
            simpleTriggerBuilder.withMisfireHandlingInstructionNowWithExistingCount();
        } else if (SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT == misfireInstruction
                .intValue()) {
            simpleTriggerBuilder.withMisfireHandlingInstructionNowWithRemainingCount();
        } else if (SimpleTrigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY == misfireInstruction.intValue()) {
            simpleTriggerBuilder.withMisfireHandlingInstructionIgnoreMisfires();
        }

        // 将SimpleTrigger设置到triggerBuilder中
        triggerBuilder.withSchedule(simpleTriggerBuilder);

        // 构造Trigger
        Trigger simpleTrigger = triggerBuilder.build();

        // 锁定触发器(避免偶尔修改失败的情况,不会对后续操作造成影响)
        if (lockTrigger(tk.getName(), tk.getGroup())) {
            // 判断trigger是否存在
            if (sch.checkExists(tk)) {
                // 获得当前触发器
                Trigger t = sch.getTrigger(tk);
                // 判断触发器关联
                if (jk.getName().equals(t.getJobKey().getName()) && jk.getGroup().equals(t.getJobKey().getGroup())) {

                    // 更新触发器
                    sch.rescheduleJob(tk, simpleTrigger);
                } else {
                    return new JsonResult<String>(Constants.FAIL,
                            requestContext.getMessage("quartz.page.server.msg.3"));
                }
            } else {
                sch.scheduleJob(simpleTrigger); // 创建触发器
            }
        } else {
            return new JsonResult<String>(Constants.FAIL, requestContext.getMessage("quartz.page.server.msg.4"));

        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * 锁定触发器
     * 
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @return 结果
     */
    private boolean lockTrigger(String triggerName, String triggerGroup) {
        try {
            this.qm.lockTrigger(triggerName, triggerGroup);
            this.getLog().info(
                    "lock trigger triggerName:" + triggerName + "," + "triggerGroup:" + triggerGroup + " success");
            return true;
        } catch (DataAccessException e) {
            this.getLog().info(
                    "lock trigger triggerName:" + triggerName + "," + "triggerGroup:" + triggerGroup + " fail", e);
            return false;
        }
    }

    /**
     * 返回日历列表
     * 
     * @return 结果
     * @throws SchedulerException 异常
     */
    public List<Map<String, Object>> getCalendarList() throws SchedulerException {
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        List<String> jgl = this.s.getScheduler().getCalendarNames();
        if (!CollectionUtils.isEmpty(jgl)) {
            for (String item : jgl) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(TEXT, item);
                map.put(VALUE, item);
                rv.add(map);
            }
        }
        return rv;
    }

    /**
     * 返回MisfireInstruction列表
     * 
     * @return 结果
     * @throws SchedulerException 异常
     */
    public List<Map<String, Object>> getSimpleMisfireInstructionList() throws SchedulerException {
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(TEXT, "Fire Now");
        map.put(VALUE, SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
        rv.add(map);
        map = new HashMap<String, Object>();
        map.put(TEXT, "Reschedule Next With Existing Count");
        map.put(VALUE, SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT);
        rv.add(map);
        map = new HashMap<String, Object>();
        map.put(TEXT, "Reschedule Next With Remaining Count");
        map.put(VALUE, SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
        rv.add(map);
        map = new HashMap<String, Object>();
        map.put(TEXT, "Now With Existing Repeat Count");
        map.put(VALUE, SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT);
        rv.add(map);
        map = new HashMap<String, Object>();
        map.put(TEXT, "Reschedule Now With Remaining Repeat Count");
        map.put(VALUE, SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT);
        rv.add(map);
        map = new HashMap<String, Object>();
        map.put(TEXT, "Ignore Misfire Policy");
        map.put(VALUE, SimpleTrigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY);
        rv.add(map);
        map = new HashMap<String, Object>();
        map.put(TEXT, "Smart Policy");
        map.put(VALUE, SimpleTrigger.MISFIRE_INSTRUCTION_SMART_POLICY);
        rv.add(map);
        return rv;
    }

    /**
     * 加载触发器
     * 
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @param triggerType 触发器类别
     * @return 结果
     */
    public Map<String, Object> loadTriggerDetail(String triggerName, String triggerGroup, String triggerType) {
        if (!StringUtils.isEmpty(triggerName) && !StringUtils.isEmpty(triggerGroup)) { // 关键字段不为空
            if (org.quartz.impl.jdbcjobstore.Constants.TTYPE_SIMPLE.equals(triggerType)) { // simple
                return this.qm.getSimpleTriggerDetail(triggerName, triggerGroup);
            } else if (org.quartz.impl.jdbcjobstore.Constants.TTYPE_CRON.equals(triggerType)) { // cron
                return this.qm.getCronTriggerDetail(triggerName, triggerGroup);
            }
        }
        return new HashMap<String, Object>();
    }

    /**
     * 返回触发器组
     * 
     * @return 结果
     * @throws SchedulerException 异常
     */
    public List<Map<String, Object>> getTriggerGroupList() throws SchedulerException {
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        List<String> jgl = this.s.getScheduler().getTriggerGroupNames();
        if (!CollectionUtils.isEmpty(jgl)) {
            for (String item : jgl) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(TEXT, item);
                map.put(VALUE, item);
                rv.add(map);
            }
        }
        return rv;
    }

    /**
     * 返回作业组
     * 
     * @return 结果
     * @throws SchedulerException 异常
     */
    public List<Map<String, Object>> getJobGroupList() throws SchedulerException {
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        List<String> jgl = this.s.getScheduler().getJobGroupNames();
        if (!CollectionUtils.isEmpty(jgl)) {
            for (String item : jgl) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(TEXT, item);
                map.put(VALUE, item);
                rv.add(map);
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 获得trigger的dataMap
     * </p>
     * 
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @return 结果 dataMap
     * @throws SchedulerException 异常
     */
    public DataGridJsonResult<Map<String, Object>> getTriggerDataMap(String triggerName, String triggerGroup)
            throws SchedulerException {
        // 参数列表
        List<Map<String, Object>> rvList = new ArrayList<Map<String, Object>>();
        // 判断 传入参数必须存在
        if (!StringUtils.isEmpty(triggerName) && !StringUtils.isEmpty(triggerGroup)) {
            // 设置triggerkey
            TriggerKey tk = new TriggerKey(triggerName, triggerGroup);
            // 判断作业是否存在,并且获取作业,和 dataMap
            if (this.s.getScheduler().checkExists(tk)) {
                Trigger trigger = this.s.getScheduler().getTrigger(tk);
                JobDataMap jdm = trigger.getJobDataMap();
                // 判断是否存在dataMap
                if (!CollectionUtils.isEmpty(jdm)) {
                    Iterator<String> keyIterator = jdm.keySet().iterator();
                    while (keyIterator.hasNext()) {
                        try {
                            String key = keyIterator.next();
                            Object value = jdm.get(key);
                            Map<String, Object> item = new HashMap<String, Object>();
                            item.put(CHAR_NAME, key);
                            item.put(VALUE, value);
                            item.put("editor", TEXT);
                            item.put("IS_DATABASE", true);
                            rvList.add(item);
                        } catch (SystemRuntimeException e) {
                            throw e;
                        }
                    }
                }
            }
        }
        // 拼装返回结果
        return new DataGridJsonResult<Map<String, Object>>(rvList.size(), rvList);
    }

    /**
     * 获得触发器明细
     * 
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @param triggerType 触发器类型
     * @return 结果
     */
    public List<Map<String, Object>> getTriggerDetail(String triggerName, String triggerGroup, String triggerType) {
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        if (org.quartz.impl.jdbcjobstore.Constants.TTYPE_SIMPLE.equals(triggerType)) { // simple
            Map<String, Object> trigger = this.qm.getSimpleTriggerDetail(triggerName, triggerGroup);
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("p", org.quartz.impl.jdbcjobstore.Constants.COL_REPEAT_COUNT);
            item.put("v", trigger.get(org.quartz.impl.jdbcjobstore.Constants.COL_REPEAT_COUNT));
            rv.add(item);
            item = new HashMap<String, Object>();
            item.put("p", org.quartz.impl.jdbcjobstore.Constants.COL_REPEAT_INTERVAL);
            item.put("v", trigger.get(org.quartz.impl.jdbcjobstore.Constants.COL_REPEAT_INTERVAL));
            rv.add(item);
            item = new HashMap<String, Object>();
            item.put("p", org.quartz.impl.jdbcjobstore.Constants.COL_TIMES_TRIGGERED);
            item.put("v", trigger.get(org.quartz.impl.jdbcjobstore.Constants.COL_TIMES_TRIGGERED));
            rv.add(item);
        } else if (org.quartz.impl.jdbcjobstore.Constants.TTYPE_CRON.equals(triggerType)) { // cron
            Map<String, Object> trigger = this.qm.getCronTriggerDetail(triggerName, triggerGroup);
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("p", org.quartz.impl.jdbcjobstore.Constants.COL_CRON_EXPRESSION);
            item.put("v", trigger.get(org.quartz.impl.jdbcjobstore.Constants.COL_CRON_EXPRESSION));
            rv.add(item);
            item = new HashMap<String, Object>();
            item.put("p", org.quartz.impl.jdbcjobstore.Constants.COL_TIME_ZONE_ID);
            item.put("v", trigger.get(org.quartz.impl.jdbcjobstore.Constants.COL_TIME_ZONE_ID));
            rv.add(item);
        }
        return rv;
    }

    /**
     * 返回trigger清单
     * 
     * @param op 操作
     * @param jobGroup 作业组别
     * @param jobName 作业名称
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @return 结果
     */
    public List<Map<String, Object>> getTriggers(String op, String jobGroup, String jobName, String triggerName,
            String triggerGroup) {
        // 判断是否默认执行查询
        boolean search = false;
        if ("jobsMain".equals(op) && !StringUtils.isEmpty(jobGroup) && !StringUtils.isEmpty(jobName)) {
            search = true;
        } else if ("triggersMain".equals(op)) {
            search = true;
        }
        // 进行查询
        if (search) {
            // 进行参数格式化(LIKE查询)
            String jobNameParam = jobName;
            if (!StringUtils.isEmpty(jobName)) {
                jobNameParam = "" + jobName + "";
            }
            String triggerNameParam = triggerName;
            if (!StringUtils.isEmpty(triggerName)) {
                triggerNameParam = "" + triggerName + "";
            }
            return this.qm.getTriggers(jobGroup, jobNameParam, triggerNameParam, triggerGroup);
        } else {
            return new ArrayList<Map<String, Object>>();
        }
    }

    /**
     * <p>
     * Description: 返回执行历史清单
     * </p>
     * 
     * @param op 操作
     * @param page 页数
     * @param rows 行数
     * @param executionHistoryTriggerGroup 触发器组别
     * @param executionHistoryTriggerName 触发器名称
     * @param executionHistoryJobGroup 作业组别
     * @param executionHistoryJobName 作业名称
     * @param executionStatus 状态
     * @return 结果 结果
     * @throws Exception 异常
     */
    public DataGridJsonResult<Map<String, Object>> loadExecutionHistoryList(String op, int page, int rows,
            String executionHistoryTriggerGroup, String executionHistoryTriggerName, String executionHistoryJobGroup,
            String executionHistoryJobName, String executionStatus) throws Exception {
        // 判断是否默认执行查询
        boolean search = false;
        if ("jobsMain".equals(op) && !StringUtils.isEmpty(executionHistoryJobName)
                && !StringUtils.isEmpty(executionHistoryJobGroup)) {
            search = true;
        } else if ("triggersMain".equals(op) && !StringUtils.isEmpty(executionHistoryTriggerGroup)
                && !StringUtils.isEmpty(executionHistoryTriggerName)) {
            search = true;
        } else if ("executionHistory".equals(op)) {
            search = true;
        }
        // 执行查询
        DataGridJsonResult<Map<String, Object>> rv = new DataGridJsonResult<Map<String, Object>>(0,
                new ArrayList<Map<String, Object>>());
        if (search) {
            PageInterceptor.startPage(rows, page);
            PageResult<Map<String, Object>> pr = PageInterceptor.getPageResult(
                    this.qm.loadExecutionHistoryListPageQuery(executionHistoryTriggerGroup, executionHistoryTriggerName,
                            executionHistoryJobGroup, executionHistoryJobName, executionStatus));
            rv = new DataGridJsonResult<Map<String, Object>>(pr.getTotalRecords(), pr.toList());
        }
        return rv;

    }

    /**
     * 删除job
     * 
     * @param jobName 作业名称
     * @param jobGroup 作业组别
     * @return 结果
     * @throws SchedulerException 异常
     */
    public JsonResult<String> deleteJobOp(String jobName, String jobGroup) throws SchedulerException {
        // 获得计划任务管理器
        Scheduler sch = this.s.getScheduler();
        // 获得JobKey
        JobKey jk = getJobKey(sch, jobName, jobGroup);
        // 判断是否为空
        if (jk != null) {
            // 恢复job
            sch.deleteJob(jk);
        } else {
            return new JsonResult<String>(Constants.FAIL, null);
        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 添加作业
     * </p>
     * 
     * @param jName 作业名称
     * @param jGroup 作业组别
     * @param jClass 作业类
     * @param jDesc 作业描述
     * @param jobShouldRecover 遗漏恢复
     * @param jobDurability 是否持久
     * @param jobDetailDataMapHid dataMap
     * @param requestContext 请求对象
     * @return 结果 操作结果
     * @throws Exception 计划任务异常
     */
    @SuppressWarnings("unchecked")
    public JsonResult<String> addJobDetail(String jName, String jGroup, String jClass, String jDesc,
            boolean jobShouldRecover, boolean jobDurability, String jobDetailDataMapHid, RequestContext requestContext)
            throws Exception {

        // 获得jobDataMap
        JobDataMap jobDataMap = this.getJobDataMap(jobDetailDataMapHid, requestContext);

        // 获得计划任务管理器
        Scheduler sch = this.s.getScheduler();

        // 设置jobkey
        JobKey jk = new JobKey(jName, jGroup);

        // 初始化jobdetail
        JobDetail jobDetail = JobBuilder.newJob((Class<Job>) Class.forName(jClass)).withIdentity(jk)
                .withDescription(jDesc).requestRecovery(jobShouldRecover).storeDurably(jobDurability)
                .setJobData(jobDataMap).build();

        // 添加job
        sch.addJob(jobDetail, true, !jobDurability);

        // 返回
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 获得jobDataMap
     * </p>
     * 
     * @param requestContext 请求上下文
     * @param jobDetailDataMapHid json数据
     * @return 结果 jobDataMap
     * @throws IOException 异常
     */
    @SuppressWarnings("unchecked")
    private JobDataMap getJobDataMap(String jobDetailDataMapHid, RequestContext requestContext) throws IOException {

        // 返回值
        JobDataMap rv = new JobDataMap();

        // 判断有数据,才进行后续的操作
        if (!org.apache.commons.lang3.StringUtils.isEmpty(jobDetailDataMapHid)
                && !com.llsfw.core.common.Constants.EMPTY_JOB_MAP_DATA.equals(jobDetailDataMapHid)) {

            // 转换JSON数据
            List<Map<String, String>> dataList = new ObjectMapper().readValue(jobDetailDataMapHid, List.class);

            // 判断是否有数据,装载JobDataMap
            if (!CollectionUtils.isEmpty(dataList)) {
                for (Map<String, String> item : dataList) {
                    String name = item.get(CHAR_NAME);
                    String value = item.get(VALUE);
                    String n = requestContext.getMessage("quartz.page.jobsmain.edit.window.msg.1");
                    String v = requestContext.getMessage("quartz.page.jobsmain.edit.window.msg.2");
                    if (!n.equals(name) && !v.equals(value)) {
                        rv.put(name, value);
                    }
                }
            }
        }

        // 返回
        return rv;
    }

    /**
     * 加载单个job
     * 
     * @param jobGroup 作业组别
     * @param jobName 作业名称
     * @return 结果
     */
    public Map<String, Object> loadJob(String jobGroup, String jobName) {
        if (!StringUtils.isEmpty(jobName) && !StringUtils.isEmpty(jobGroup)) {
            return this.qm.loadJob(jobGroup, jobName);
        }
        return null;
    }

    /**
     * 暂停所有job
     * 
     * @return 结果
     * @throws SchedulerException 异常
     */
    public JsonResult<String> resumeAllJobOp() throws SchedulerException {
        // 获得计划任务管理器
        Scheduler sch = this.s.getScheduler();
        // 恢复所有job
        sch.resumeAll();
        // 返回
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * 暂停所有job
     * 
     * @return 结果
     * @throws SchedulerException 异常
     */
    public JsonResult<String> puseAllJobOp() throws SchedulerException {
        // 获得计划任务管理器
        Scheduler sch = this.s.getScheduler();
        // 暂停所有job
        sch.pauseAll();
        // 返回
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * 恢复job
     * 
     * @param jobName 作业名称
     * @param jobGroup 作业组别
     * @return 结果
     * @throws SchedulerException 异常
     */
    public JsonResult<String> resumeJobOp(String jobName, String jobGroup) throws SchedulerException {
        // 获得计划任务管理器
        Scheduler sch = this.s.getScheduler();
        // 获得JobKey
        JobKey jk = getJobKey(sch, jobName, jobGroup);
        // 判断是否为空
        if (jk != null) {
            // 恢复job
            sch.resumeJob(jk);
        } else {
            return new JsonResult<String>(Constants.FAIL, null);
        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * 暂停job
     * 
     * @param jobName 作业名称
     * @param jobGroup 作业组别
     * @return 结果
     * @throws SchedulerException 异常
     */
    public JsonResult<String> puseJobOp(String jobName, String jobGroup) throws SchedulerException {
        // 获得计划任务管理器
        Scheduler sch = this.s.getScheduler();
        // 获得JobKey
        JobKey jk = getJobKey(sch, jobName, jobGroup);
        // 判断是否为空
        if (jk != null) {
            // 暂停job
            sch.pauseJob(jk);
        } else {
            return new JsonResult<String>(Constants.FAIL, null);
        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * 触发job
     * 
     * @param jobName 作业名称
     * @param jobGroup 作业组别
     * @return 结果
     * @throws SchedulerException 异常
     */
    public JsonResult<String> triggerJobOp(String jobName, String jobGroup) throws SchedulerException {
        // 获得计划任务管理器
        Scheduler sch = this.s.getScheduler();
        // 获得JobKey
        JobKey jk = getJobKey(sch, jobName, jobGroup);
        // 判断是否为空
        if (jk != null) {
            // 触发job
            sch.triggerJob(jk);
        } else {
            return new JsonResult<String>(Constants.FAIL, null);
        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * 返回TriggerKey
     * 
     * @param sch 计划任务对象
     * @param triggerName 作业名称
     * @param triggerGroup 作业组别
     * @return 结果
     * @throws SchedulerException 异常
     */
    private static TriggerKey getTriggerKey(Scheduler sch, String triggerName, String triggerGroup)
            throws SchedulerException {
        // 判断 传入参数必须存在
        if (!StringUtils.isEmpty(triggerName) && !StringUtils.isEmpty(triggerGroup)) {
            // 设置trigger key
            TriggerKey tk = new TriggerKey(triggerName, triggerGroup);
            // 判断作业是否存在,并且获取作业,和 dataMap
            if (sch.checkExists(tk)) {
                return tk;
            }
        }
        return null;
    }

    /**
     * 返回JobKey
     * 
     * @param sch 计划任务对象
     * @param jobName 作业名称
     * @param jobGroup 作业组别
     * @return 结果
     * @throws SchedulerException 异常
     */
    private static JobKey getJobKey(Scheduler sch, String jobName, String jobGroup) throws SchedulerException {
        // 判断 传入参数必须存在
        if (!StringUtils.isEmpty(jobName) && !StringUtils.isEmpty(jobGroup)) {
            // 设置jobkey
            JobKey jk = new JobKey(jobName, jobGroup);
            // 判断作业是否存在,并且获取作业,和 dataMap
            if (sch.checkExists(jk)) {
                return jk;
            }
        }
        return null;
    }

    /**
     * <p>
     * Description: 获得作业的dataMap
     * </p>
     * 
     * @param jName 作业名称
     * @param jGroup 作业组别
     * @return 结果 dataMap
     * @throws SchedulerException 异常
     */
    public Map<String, Object> getJobDetailDataMap(String jName, String jGroup) throws SchedulerException {
        // 参数列表
        List<Map<String, Object>> rvList = new ArrayList<Map<String, Object>>();
        // 判断 传入参数必须存在
        if (!StringUtils.isEmpty(jName) && !StringUtils.isEmpty(jGroup)) {
            // 设置jobkey
            JobKey jk = new JobKey(jName, jGroup);
            // 判断作业是否存在,并且获取作业,和 dataMap
            if (this.s.getScheduler().checkExists(jk)) {
                JobDetail jd = this.s.getScheduler().getJobDetail(jk);
                JobDataMap jdm = jd.getJobDataMap();
                // 判断是否存在dataMap
                if (!CollectionUtils.isEmpty(jdm)) {
                    Iterator<String> keyIterator = jdm.keySet().iterator();
                    while (keyIterator.hasNext()) {
                        try {
                            String key = keyIterator.next();
                            Object value = jdm.get(key);
                            Map<String, Object> item = new HashMap<String, Object>();
                            item.put(CHAR_NAME, key);
                            item.put(VALUE, value);
                            item.put("editor", TEXT);
                            item.put("IS_DATABASE", true);
                            rvList.add(item);
                        } catch (SystemRuntimeException e) {
                            throw e;
                        }
                    }
                }
            }
        }

        // 拼装返回结果
        Map<String, Object> rv = new HashMap<String, Object>();
        rv.put(TOTAL, rvList.size());
        rv.put(ROWS, rvList);
        this.getLog().info("jobDataMap:" + rv);

        return rv;
    }

    /**
     * 返回job清单
     * 
     * @param jobGroup 作业组别
     * @param jobName 作业名称
     * @param description 作业描述
     * @param jobClassName 作业类
     * @return 结果
     */
    public List<Map<String, Object>> getJobs(String jobGroup, String jobName, String description, String jobClassName) {
        // 格式化参数(LIKE查询)
        String jobGroupParam = jobGroup;
        if (!StringUtils.isEmpty(jobGroup)) {
            jobGroupParam = PERCENT_SIGN + jobGroup + PERCENT_SIGN;
        }
        String jobNameParam = jobName;
        if (!StringUtils.isEmpty(jobName)) {
            jobNameParam = PERCENT_SIGN + jobName + PERCENT_SIGN;
        }
        String descriptionParam = description;
        if (!StringUtils.isEmpty(description)) {
            descriptionParam = PERCENT_SIGN + description + PERCENT_SIGN;
        }
        String jobClassNameParam = jobClassName;
        if (!StringUtils.isEmpty(jobClassName)) {
            jobClassNameParam = PERCENT_SIGN + jobClassName + PERCENT_SIGN;
        }
        return this.qm.getJobs(jobGroupParam, jobNameParam, descriptionParam, jobClassNameParam);
    }

}
