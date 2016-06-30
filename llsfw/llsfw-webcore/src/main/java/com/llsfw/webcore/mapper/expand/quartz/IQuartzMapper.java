/**
 * IquartzMapper.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.mapper.expand.quartz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * ClassName: IQuartzMapper
 * </p>
 * <p>
 * Description: 计划任务mapper
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
public interface IQuartzMapper {

    /**
     * 计划任务执行状态统计
     * 
     * @return 结果
     */
    List<Map<String, Object>> reportSchedulerRunStatus();

    /**
     * 触发器执行状态统计
     * 
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @return 结果
     */
    List<Map<String, Object>> reportTriggerRunStatus(@Param("triggerName") String triggerName,
            @Param("triggerGroup") String triggerGroup);

    /**
     * 作业执行状态统计
     * 
     * @param jobName 作业名称
     * @param jobGroup 作业组别
     * @return 结果
     */
    List<Map<String, Object>> reportJobRunStatus(@Param("jobName") String jobName, @Param("jobGroup") String jobGroup);

    /**
     * 返回计划任务操作历史
     * 
     * @return 结果
     */
    List<Map<String, Object>> loadSchedulerOptionHisPageQuery();

    /**
     * 返回计划任务详情
     * 
     * @return 结果
     */
    List<Map<String, Object>> loadSchedulerState();

    /**
     * 返回当前正在执行的任务
     * 
     * @return 结果
     */
    List<Map<String, Object>> loadCurrentlyEexutingJobs();

    /**
     * 锁定触发器
     * 
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @return 结果
     */
    Map<String, Object> lockTrigger(@Param("triggerName") String triggerName,
            @Param("triggerGroup") String triggerGroup);

    /**
     * simple触发器
     * 
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @return 结果
     */
    Map<String, Object> getSimpleTriggerDetail(@Param("triggerName") String triggerName,
            @Param("triggerGroup") String triggerGroup);

    /**
     * cron触发器
     * 
     * @param triggerName 触发器名称
     * @param triggerGroup 触发器组别
     * @return 结果
     */
    Map<String, Object> getCronTriggerDetail(@Param("triggerName") String triggerName,
            @Param("triggerGroup") String triggerGroup);

    /**
     * 返回trigger清单
     * 
     * @param jobGroup 作业组别
     * @param jobNameParam 作业名称
     * @param triggerNameParam 触发器名称
     * @param triggerGroup 触发器组别
     * @param sort
     * @param order
     * @return 结果
     */
    List<Map<String, Object>> getTriggers(@Param("jobGroup") String jobGroup,
            @Param("jobNameParam") String jobNameParam, @Param("triggerNameParam") String triggerNameParam,
            @Param("triggerGroup") String triggerGroup);

    /**
     * 查询计划任务执行历史
     * 
     * @param executionHistoryTriggerGroup 触发器组别
     * @param executionHistoryTriggerName 触发器名称
     * @param executionHistoryJobGroup 作业组别
     * @param executionHistoryJobName 作业名称
     * @param executionStatus 状态
     * @return 结果
     */
    List<Map<String, Object>> loadExecutionHistoryListPageQuery(
            @Param("execution_history_trigger_group") String executionHistoryTriggerGroup,
            @Param("execution_history_trigger_name") String executionHistoryTriggerName,
            @Param("execution_history_job_group") String executionHistoryJobGroup,
            @Param("execution_history_job_name") String executionHistoryJobName,
            @Param("execution_status") String executionStatus);

    /**
     * 加载单个job
     * 
     * @param jobGroup 作业组别
     * @param jobName 作业名称
     * @return 结果
     */
    Map<String, Object> loadJob(@Param("jobGroup") String jobGroup, @Param("jobName") String jobName);

    /**
     * 返回job清单
     * 
     * @param jobGroupParam 作业组别
     * @param jobNameParam 作业名称
     * @param descriptionParam 作业描述
     * @param jobClassNameParam 作业类
     * @return 结果
     */
    List<Map<String, Object>> getJobs(@Param("jobGroupParam") String jobGroupParam,
            @Param("jobNameParam") String jobNameParam, @Param("descriptionParam") String descriptionParam,
            @Param("jobClassNameParam") String jobClassNameParam);
}
