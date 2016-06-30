/**
 * ProcessPendingReceipt.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.activiti.controller.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llsfw.core.common.Constants;
import com.llsfw.core.common.DataGridJsonResult;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.controller.base.BaseController;
import com.llsfw.core.pagequery.PageResult;
import com.llsfw.webgen.mapper.standard.permissions.TsApplicationUserMapper;

/**
 * <p>
 * ClassName: ProcessPendingReceipt
 * </p>
 * <p>
 * Description: 签收
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Controller
@RequestMapping(value = "activiti/process/processPendingReceipt")
public class ProcessPendingReceipt extends BaseController {

    /**
     * <p>
     * Field taskService: 任务服务
     * </p>
     */
    @Autowired
    private TaskService taskService;

    /**
     * <p>
     * Field repositoryService: 仓库服务
     * </p>
     */
    @Autowired
    private RepositoryService repositoryService;

    /**
     * <p>
     * Field historyService: 历史服务
     * </p>
     */
    @Autowired
    private HistoryService historyService;

    /**
     * <p>
     * Field taum: 用户mapper
     * </p>
     */
    @Autowired
    private TsApplicationUserMapper taum;

    /**
     * <p>
     * Description: 签收
     * </p>
     * 
     * @param taskId 任务ID
     * @return 结果
     */
    @RequiresPermissions("processPendingReceipt:edit")
    @RequestMapping("claim")
    @ResponseBody
    public JsonResult<String> claim(String taskId) {
        this.taskService.claim(taskId, this.getLoginName());
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 加载待签收任务
     * </p>
     * 
     * @param page 页数
     * @param rows 行数
     * @return 页面
     */
    @RequiresPermissions("processPendingReceipt:view")
    @RequestMapping("loadProcessPendingReceipt")
    @ResponseBody
    public DataGridJsonResult<Map<String, Object>> loadProcessPendingReceipt(int page, int rows) {
        TaskQuery toClaimQuery = this.taskService.createTaskQuery().taskCandidateUser(this.getLoginName())
                .includeProcessVariables().active().orderByTaskCreateTime().desc();
        long toClaimCount = toClaimQuery.count();
        List<Task> toClaimList = toClaimQuery.listPage(PageResult.getFistResult(page, rows), rows);
        List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
        if (!CollectionUtils.isEmpty(toClaimList)) {
            for (Task task : toClaimList) {
                ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery()
                        .processDefinitionId(task.getProcessDefinitionId()).singleResult();
                HistoricProcessInstance historicProcessInstance = this.historyService
                        .createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId())
                        .singleResult();
                Map<String, Object> item = new HashMap<String, Object>();
                item.put("assignee", task.getAssignee());
                item.put("category", task.getCategory());
                item.put("createTime", task.getCreateTime());
                item.put("description", task.getDescription());
                item.put("dueDate", task.getDueDate());
                item.put("executionId", task.getExecutionId());
                item.put("formKey", task.getFormKey());
                item.put("id", task.getId());
                item.put("name", task.getName());
                item.put("owner", task.getOwner());
                item.put("parentTaskId", task.getParentTaskId());
                item.put("priority", task.getPriority());
                item.put("processInstanceId", task.getProcessInstanceId());
                item.put("processDefinitionId", task.getProcessDefinitionId());
                item.put("processDefinitionName", processDefinition.getName());
                item.put("processInstanceStartUserId", historicProcessInstance.getStartUserId());
                item.put("processInstanceStartUserName",
                        this.taum.selectByPrimaryKey(historicProcessInstance.getStartUserId()).getUserName());
                item.put("processInstanceStartTime", historicProcessInstance.getStartTime());
                item.put("taskDefinitionKey", task.getTaskDefinitionKey());
                item.put("tenantId", task.getTenantId());
                rs.add(item);
            }
        }
        return new DataGridJsonResult<Map<String, Object>>((int) toClaimCount, rs);
    }

    /**
     * <p>
     * Description: 初始化
     * </p>
     * 
     * @param req 请求对象
     * @return 页面
     */
    @RequiresPermissions("processPendingReceipt:view")
    @RequestMapping("init")
    public String init(HttpServletRequest req) {
        // 将国际化信息转换成脚本放入作用域
        this.getLocalStript(req, "activiti.process.processPendingReceipt.");
        return "llsfw/activiti/process/processPendingReceipt/processPendingReceiptMain";
    }

}
