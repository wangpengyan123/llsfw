/**
 * QuartzController.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.controller.quartz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.jdbcjobstore.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.llsfw.core.common.ClassUtil;
import com.llsfw.core.common.DataGridJsonResult;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.controller.base.BaseController;
import com.llsfw.core.scheduler.AbstractBaseJob;
import com.llsfw.webcore.service.quartz.QuartzService;

/**
 * <p>
 * ClassName: QuartzController
 * </p>
 * <p>
 * Description: 计划任务控制器
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Controller
@RequestMapping("quartzController")
public class QuartzController extends BaseController {

    /**
     * <p>
     * Field qs: 计划任务服务
     * </p>
     */
    @Autowired
    private QuartzService qs;

    /**
     * <p>
     * Description: 计划任务执行状态报表
     * </p>
     * 
     * @param req 请求对象
     * @return 页面
     * @throws JsonProcessingException 异常
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("reportSchedulerRunStatus")
    public String reportSchedulerRunStatus(HttpServletRequest req) throws JsonProcessingException {
        req.setAttribute("data", new ObjectMapper().writeValueAsString(this.qs.reportSchedulerRunStatus()));
        return "llsfw/quartz/reportSchedulerRunStatus";
    }

    /**
     * <p>
     * Description: 到Sechulde报表界面
     * </p>
     * 
     * @return 页面
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("toSchedulerChart")
    public String toSchedulerChart() {
        return "llsfw/quartz/schedulerChartMain";
    }

    /**
     * 触发器执行状态统计
     * 
     * @param req 请求对象
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @return 页面
     * @throws JsonProcessingException 异常
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("reportTriggerRunStatus")
    public String reportTriggerRunStatus(HttpServletRequest req, String triggerName, String triggerGroup)
            throws JsonProcessingException {
        req.setAttribute("data",
                new ObjectMapper().writeValueAsString(this.qs.reportTriggerRunStatus(triggerName, triggerGroup)));
        return "llsfw/quartz/reportTriggerRunStatus";
    }

    /**
     * <p>
     * Description: 到TRIGGER报表界面
     * </p>
     * 
     * @return 页面
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("toTriggerChart")
    public String toTriggerChart() {
        return "llsfw/quartz/triggerChartMain";
    }

    /**
     * 作业执行状态统计
     * 
     * @param req 请求对象
     * @param jobName 作业名称
     * @param jobGroup 作业组别
     * @return 页面
     * @throws JsonProcessingException 异常
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("reportJobRunStatus")
    public String reportJobRunStatus(HttpServletRequest req, String jobName, String jobGroup)
            throws JsonProcessingException {
        req.setAttribute("data", new ObjectMapper().writeValueAsString(this.qs.reportJobRunStatus(jobName, jobGroup)));
        return "llsfw/quartz/reportJobRunStatus";
    }

    /**
     * <p>
     * Description: 到JOB报表界面
     * </p>
     * 
     * @return 页面
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("toJobChart")
    public String toJobChart() {
        return "llsfw/quartz/jobChartMain";
    }

    /**
     * <p>
     * Description: 清除计划任务数据
     * </p>
     * 
     * @param req 请求对象
     * @param pswd 操作密码
     * @return 操作结果
     * @throws SchedulerException 计划任务异常
     */
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("schedulerClear")
    @ResponseBody
    public JsonResult<String> schedulerClear(HttpServletRequest req, String pswd) throws SchedulerException {
        RequestContext requestContext = new RequestContext(req);
        return this.qs.schedulerClear(requestContext, pswd);
    }

    /**
     * 加载计划任务操作历史
     * 
     * @param page 页数
     * @param rows 行数
     * @return 结果
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("loadSchedulerLogList")
    @ResponseBody
    public DataGridJsonResult<Map<String, Object>> loadSchedulerLogList(int page, int rows) {
        return this.qs.loadSchedulerLogList(page, rows);
    }

    /**
     * 返回计划任务详情
     * 
     * @return 结果
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("loadSchedulerState")
    @ResponseBody
    List<Map<String, Object>> loadSchedulerState() {
        return this.qs.loadSchedulerState();
    }

    /**
     * 跳转到计划任务详情页面
     * 
     * @return 页面
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("toSchDetailInit")
    public String toSchDetailInit() {
        return "llsfw/quartz/schDetailMain";
    }

    /**
     * 跳转到正在执行任务页面
     * 
     * @return 页面
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("toTriggerInit")
    public String toTriggerInit() {
        return "llsfw/quartz/triggersMain";
    }

    /**
     * 返回当前正在执行的任务
     * 
     * @return 结果
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("loadCurrentlyEexutingJobs")
    @ResponseBody
    List<Map<String, Object>> loadCurrentlyEexutingJobs() {
        return this.qs.loadCurrentlyEexutingJobs();
    }

    /**
     * 跳转到正在执行任务页面
     * 
     * @return 页面
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("toCurrentlyEexutingJobs")
    public String toCurrentlyEexutingJobs() {
        return "llsfw/quartz/currentlyEexutingJobsMain";
    }

    /**
     * 恢复触发器
     * 
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @return 结果
     * @throws SchedulerException 异常
     */
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("resumeTriggerOp")
    @ResponseBody
    public JsonResult<String> resumeTriggerOp(String triggerName, String triggerGroup) throws SchedulerException {
        return this.qs.resumeTriggerOp(triggerName, triggerGroup);
    }

    /**
     * 暂停触发器
     * 
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @return 结果
     * @throws SchedulerException 异常
     */
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("puseTriggerOp")
    @ResponseBody
    public JsonResult<String> puseTriggerOp(String triggerName, String triggerGroup) throws SchedulerException {
        return this.qs.puseTriggerOp(triggerName, triggerGroup);
    }

    /**
     * 删除trigger
     * 
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @return 结果
     * @throws SchedulerException 异常
     */
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("deleteTriggerOp")
    @ResponseBody
    public JsonResult<String> deleteTriggerOp(String triggerName, String triggerGroup) throws SchedulerException {
        return this.qs.deleteTriggerOp(triggerName, triggerGroup);
    }

    /**
     * <p>
     * Description: 添加CronTrigger
     * </p>
     * 
     * @param req 请求对象
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
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("addCronTirgger")
    @ResponseBody
    public JsonResult<String> addCronTirgger(HttpServletRequest req, String jName, String jGroup, String tName,
            String tGroup, String tDescription, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date triggerStartTime,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date triggerEndTime, Integer priority,
            Integer misfireInstruction, String triggerCalendar, String triggerCronExcp, String timeZoneId,
            String cronTriggerDetailDataMapHid) throws SchedulerException, IOException {
        RequestContext requestContext = new RequestContext(req);
        return this.qs.addCronTirgger(requestContext, jName, jGroup, tName, tGroup, tDescription, triggerStartTime,
                triggerEndTime, priority, misfireInstruction, triggerCalendar, triggerCronExcp, timeZoneId,
                cronTriggerDetailDataMapHid);
    }

    /**
     * <p>
     * Description: 跳转到cron生成器界面
     * </p>
     * 
     * @return crom生成器界面
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("toCronTriggersGeneratePage")
    public String toCronTriggersGeneratePage() {
        return "llsfw/quartz/cronTriggersGenerate";
    }

    /**
     * 返回时区列表
     * 
     * @return 返回时区列表
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("getTimeZoneIdList")
    @ResponseBody
    public List<Map<String, Object>> getTimeZoneIdList() {
        return this.qs.getTimeZoneIdList();
    }

    /**
     * 返回SimpleMisfireInstruction列表
     * 
     * @return 结果
     * @throws SchedulerException 异常
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("getCronMisfireInstructionList")
    @ResponseBody
    public List<Map<String, Object>> getCronMisfireInstructionList() throws SchedulerException {
        return this.qs.getCronMisfireInstructionList();
    }

    /**
     * <p>
     * Description: 跳转到cron触发器作业添加页面
     * </p>
     * 
     * @param request 请求对象
     * @param addTriggerOp 操作
     * @param jobName 作业名称
     * @param jobGroup 作业组别
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @return 页面
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("toAddCronTrigger")
    public String toAddCronTrigger(HttpServletRequest request, String addTriggerOp, String jobName, String jobGroup,
            String triggerName, String triggerGroup) {
        request.setAttribute("addTriggerOp", addTriggerOp);
        request.setAttribute("defaultGroup", Scheduler.DEFAULT_GROUP);
        request.setAttribute("defaultPriority", Trigger.DEFAULT_PRIORITY);
        request.setAttribute("defaultMisFireInstruction", Trigger.MISFIRE_INSTRUCTION_SMART_POLICY);
        request.setAttribute("defaultTimeZone", TimeZone.getDefault().getID());
        request.setAttribute("job", this.qs.loadJob(jobGroup, jobName));
        request.setAttribute("trigger", this.qs.loadTriggerDetail(triggerName, triggerGroup, Constants.TTYPE_CRON));
        return "llsfw/quartz/addCronTrigger";
    }

    /**
     * <p>
     * Description: 添加CronTrigger
     * </p>
     * 
     * @param req 请求对象
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
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("addSimpleTirgger")
    @ResponseBody
    public JsonResult<String> addSimpleTirgger(HttpServletRequest req, String jName, String jGroup, String tName,
            String tGroup, String tDescription, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date triggerStartTime,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date triggerEndTime, Integer priority,
            Integer misfireInstruction, String triggerCalendar, Integer triggerRepeatCount, Long intervalInMilliseconds,
            String triggerDetailDataMapHid) throws SchedulerException, IOException {
        RequestContext requestContext = new RequestContext(req);
        return this.qs.addSimpleTirgger(requestContext, jName, jGroup, tName, tGroup, tDescription, triggerStartTime,
                triggerEndTime, priority, misfireInstruction, triggerCalendar, triggerRepeatCount,
                intervalInMilliseconds, triggerDetailDataMapHid);
    }

    /**
     * <p>
     * Description: 返回日历列表
     * </p>
     * 
     * @return 返回日历列表
     * @throws SchedulerException 异常
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("getCalendarList")
    @ResponseBody
    public List<Map<String, Object>> getCalendarList() throws SchedulerException {
        return this.qs.getCalendarList();
    }

    /**
     * 返回SimpleMisfireInstruction列表
     * 
     * @return 结果
     * @throws SchedulerException 异常
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("getSimpleMisfireInstructionList")
    @ResponseBody
    public List<Map<String, Object>> getSimpleMisfireInstructionList() throws SchedulerException {
        return this.qs.getSimpleMisfireInstructionList();
    }

    /**
     * <p>
     * Description: 跳转到simple触发器作业添加页面
     * </p>
     * 
     * @param request 请求对象
     * @param addTriggerOp 操作
     * @param jobName 作业名称
     * @param jobGroup 作业组别
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @return 页面
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("toAddSimpleTrigger")
    public String toAddSimpleTrigger(HttpServletRequest request, String addTriggerOp, String jobName, String jobGroup,
            String triggerName, String triggerGroup) {
        request.setAttribute("addTriggerOp", addTriggerOp);
        request.setAttribute("defaultGroup", Scheduler.DEFAULT_GROUP);
        request.setAttribute("defaultPriority", Trigger.DEFAULT_PRIORITY);
        request.setAttribute("defaultMisFireInstruction", Trigger.MISFIRE_INSTRUCTION_SMART_POLICY);
        request.setAttribute("job", this.qs.loadJob(jobGroup, jobName));
        request.setAttribute("trigger", this.qs.loadTriggerDetail(triggerName, triggerGroup, Constants.TTYPE_SIMPLE));
        return "llsfw/quartz/addSimpleTrigger";
    }

    /**
     * 返回触发器组
     * 
     * @return 结果
     * @throws SchedulerException 异常
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("getTriggerGroupList")
    @ResponseBody
    public List<Map<String, Object>> getTriggerGroupList() throws SchedulerException {
        return this.qs.getTriggerGroupList();
    }

    /**
     * 返回作业组
     * 
     * @return 结果
     * @throws SchedulerException 异常
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("getJobGroupList")
    @ResponseBody
    public List<Map<String, Object>> getJobGroupList() throws SchedulerException {
        return this.qs.getJobGroupList();
    }

    /**
     * <p>
     * Description: 获得触发器的dataMap
     * </p>
     * 
     * @param triggerName 作业名称
     * @param triggerGroup 作业组别
     * @return dataMap
     * @throws SchedulerException 异常
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("getTriggerDataMap")
    @ResponseBody
    public DataGridJsonResult<Map<String, Object>> getTriggerDataMap(String triggerName, String triggerGroup)
            throws SchedulerException {
        return this.qs.getTriggerDataMap(triggerName, triggerGroup);
    }

    /**
     * 返回触发器明细
     * 
     * @param req 请求对象
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @param triggerType 触发器类型
     * @return 页面
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("getTriggerDetail")
    public String getTriggerDetail(HttpServletRequest req, String triggerName, String triggerGroup,
            String triggerType) {
        req.setAttribute("data", this.qs.getTriggerDetail(triggerName, triggerGroup, triggerType));
        return "llsfw/quartz/triggerDetailView";
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
    @RequiresPermissions("quartzController:view")
    @RequestMapping("getTriggers")
    @ResponseBody
    public List<Map<String, Object>> getTriggers(String op, String jobGroup, String jobName, String triggerName,
            String triggerGroup) {
        return this.qs.getTriggers(op, jobGroup, jobName, triggerName, triggerGroup);
    }

    /**
     * <p>
     * Description: 跳转到执行历史界面
     * </p>
     * 
     * @param op 操作
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @param jobName 作业名称
     * @param jobGroup 作业组别
     * @param request 请求对象
     * @return 页面
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("toJobTriggersPage")
    public String toJobTriggersPage(String op, String triggerName, String triggerGroup, String jobName, String jobGroup,
            HttpServletRequest request) {
        request.setAttribute("triggerName", triggerName);
        request.setAttribute("triggerGroup", triggerGroup);
        request.setAttribute("jobName", jobName);
        request.setAttribute("jobGroup", jobGroup);
        request.setAttribute("op", op);
        return "llsfw/quartz/jobTriggers";
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
     * @return 结果
     * @throws Exception 异常
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("loadExecutionHistoryList")
    @ResponseBody
    public DataGridJsonResult<Map<String, Object>> loadExecutionHistoryList(String op, int page, int rows,
            String executionHistoryTriggerGroup, String executionHistoryTriggerName, String executionHistoryJobGroup,
            String executionHistoryJobName, String executionStatus) throws Exception {
        return this.qs.loadExecutionHistoryList(op, page, rows, executionHistoryTriggerGroup,
                executionHistoryTriggerName, executionHistoryJobGroup, executionHistoryJobName, executionStatus);
    }

    /**
     * <p>
     * Description: 跳转到执行历史界面
     * </p>
     * 
     * @param op 操作
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @param jobName 作业名称
     * @param jobGroup 作业组别
     * @param request 请求对象
     * @return 页面
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("toExecutionHistoryPage")
    public String toExecutionHistoryPage(String op, String triggerName, String triggerGroup, String jobName,
            String jobGroup, HttpServletRequest request) {
        request.setAttribute("triggerName", triggerName);
        request.setAttribute("triggerGroup", triggerGroup);
        request.setAttribute("jobName", jobName);
        request.setAttribute("jobGroup", jobGroup);
        request.setAttribute("op", op);
        return "llsfw/quartz/executionHistory";
    }

    /**
     * <p>
     * Description: 反馈jobClassName列表
     * </p>
     * 
     * @return 结果
     * @throws ClassNotFoundException 异常
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("getJobClassNameList")
    @ResponseBody
    public List<Map<String, Object>> getJobClassNameList() throws ClassNotFoundException {
        List<Class<AbstractBaseJob>> jobList = ClassUtil.getSubClasses(AbstractBaseJob.class, "com");
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        if (!CollectionUtils.isEmpty(jobList)) {
            for (Class<AbstractBaseJob> claz : jobList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("value", claz.getName());
                map.put("text", claz.getName());
                rv.add(map);
            }
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

    @RequiresPermissions("quartzController:edit")
    @RequestMapping("deleteJobOp")
    @ResponseBody
    public JsonResult<String> deleteJobOp(String jobName, String jobGroup) throws SchedulerException {
        return this.qs.deleteJobOp(jobName, jobGroup);
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
     * @param req 请求对象
     * @return 操作结果
     * @throws Exception 计划任务异常
     */
    @RequiresPermissions("quartzController:edit")
    @RequestMapping("addJobDetail")
    @ResponseBody
    public JsonResult<String> addJobDetail(String jName, String jGroup, String jClass, String jDesc,
            boolean jobShouldRecover, boolean jobDurability, String jobDetailDataMapHid, HttpServletRequest req)
            throws Exception {
        RequestContext requestContext = new RequestContext(req);
        return this.qs.addJobDetail(jName, jGroup, jClass, jDesc, jobShouldRecover, jobDurability, jobDetailDataMapHid,
                requestContext);
    }

    /**
     * <p>
     * Description: 校验类是否存在,并且是否实现org.quartz.Job接口
     * </p>
     * 
     * @param jClass 类型
     * @return 是否通过 true:通过,false:不通过
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("jClassCheck")
    @ResponseBody
    public boolean jClassCheck(String jClass) {
        try {
            Class.forName(jClass).newInstance();
            return true;
        } catch (InstantiationException e) {
            this.log.info("类不存在:" + e.getMessage());
            return false;
        } catch (IllegalAccessException e) {
            this.log.info("类不存在:" + e.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
            this.log.info("类不存在:" + e.getMessage());
            return false;
        }
    }

    /**
     * <p>
     * Description: 跳转到作业添加页面
     * </p>
     * 
     * @param request 请求对象
     * @param op 操作
     * @param jobName 作业名称
     * @param jobGroup 作业组别
     * @return 界面
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("toAddJobDetail")
    public String toAddJobDetail(HttpServletRequest request, String op, String jobName, String jobGroup) {
        Map<String, Object> jobDetail = this.qs.loadJob(jobGroup, jobName);
        if (!CollectionUtils.isEmpty(jobDetail)) {
            request.setAttribute("jobDetail", jobDetail);
        }
        request.setAttribute("op", op);
        return "llsfw/quartz/addJobDetail";
    }

    /**
     * 恢复所有job
     * 
     * @return 结果
     * @throws SchedulerException 异常
     */

    @RequiresPermissions("quartzController:edit")
    @RequestMapping("resumeAllJobOp")
    @ResponseBody
    public JsonResult<String> resumeAllJobOp() throws SchedulerException {
        return this.qs.resumeAllJobOp();
    }

    /**
     * 暂停所有job
     * 
     * @return 结果
     * @throws SchedulerException 异常
     */

    @RequiresPermissions("quartzController:edit")
    @RequestMapping("puseAllJobOp")
    @ResponseBody
    public JsonResult<String> puseAllJobOp() throws SchedulerException {
        return this.qs.puseAllJobOp();
    }

    /**
     * 恢复job
     * 
     * @param jobName 作业名称
     * @param jobGroup 作业组别
     * @return 结果
     * @throws SchedulerException 异常
     */

    @RequiresPermissions("quartzController:edit")
    @RequestMapping("resumeJobOp")
    @ResponseBody
    public JsonResult<String> resumeJobOp(String jobName, String jobGroup) throws SchedulerException {
        return this.qs.resumeJobOp(jobName, jobGroup);
    }

    /**
     * 暂停job
     * 
     * @param jobName 作业名称
     * @param jobGroup 作业组别
     * @return 结果
     * @throws SchedulerException 异常
     */

    @RequiresPermissions("quartzController:edit")
    @RequestMapping("puseJobOp")
    @ResponseBody
    public JsonResult<String> puseJobOp(String jobName, String jobGroup) throws SchedulerException {
        return this.qs.puseJobOp(jobName, jobGroup);
    }

    /**
     * 触发job
     * 
     * @param jobName 作业名称
     * @param jobGroup 作业组别
     * @return 结果
     * @throws SchedulerException 异常
     */

    @RequiresPermissions("quartzController:edit")
    @RequestMapping("triggerJobOp")
    @ResponseBody
    public JsonResult<String> triggerJobOp(String jobName, String jobGroup) throws SchedulerException {
        return this.qs.triggerJobOp(jobName, jobGroup);
    }

    /**
     * <p>
     * Description: 获得作业的dataMap
     * </p>
     * 
     * @param jobName 作业名称
     * @param jobGroup 作业组别
     * @return dataMap
     * @throws SchedulerException 异常
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("getJobDetailDataMap")
    @ResponseBody
    public Map<String, Object> getJobDetailDataMap(String jobName, String jobGroup) throws SchedulerException {
        return this.qs.getJobDetailDataMap(jobName, jobGroup);
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
    @RequiresPermissions("quartzController:view")
    @RequestMapping("getJobs")
    @ResponseBody
    List<Map<String, Object>> getJobs(String jobGroup, String jobName, String description, String jobClassName) {
        return this.qs.getJobs(jobGroup, jobName, description, jobClassName);
    }

    /**
     * <p>
     * Description: 作业初始化方法
     * </p>
     * 
     * @return 页面
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("jobsInit")
    public String jobsInit() {
        return "llsfw/quartz/jobsMain";
    }

    /**
     * <p>
     * Description: 初始化
     * </p>
     * 
     * @param req 请求对象
     * 
     * @return 主页面
     */
    @RequiresPermissions("quartzController:view")
    @RequestMapping("init")
    public String init(HttpServletRequest req) {
        this.getLocalStript(req, "quartz.");
        return "llsfw/quartz/quartzMain";
    }
}
