/**
 * ProcessPending.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.activiti.controller.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.form.TaskFormDataImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llsfw.activiti.common.ProcessUtils;
import com.llsfw.core.common.Constants;
import com.llsfw.core.common.DataGridJsonResult;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.controller.base.BaseController;
import com.llsfw.core.exception.SystemException;
import com.llsfw.core.pagequery.PageResult;
import com.llsfw.webgen.mapper.standard.permissions.TsApplicationUserMapper;

/**
 * <p>
 * ClassName: ProcessPending
 * </p>
 * <p>
 * Description: 流程处理
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Controller
@RequestMapping(value = "activiti/process/processPending")
public class ProcessPending extends BaseController {

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
     * Field formService: 表单服务
     * </p>
     */
    @Autowired
    private FormService formService;

    /**
     * <p>
     * Field identityService: 认证服务
     * </p>
     */
    @Autowired
    private IdentityService identityService;

    /**
     * <p>
     * Field taum: 用户mapper
     * </p>
     */
    @Autowired
    private TsApplicationUserMapper taum;

    /**
     * <p>
     * Description: 完成任务
     * </p>
     * 
     * @param taskId 任务ID
     * @param request 请求对象
     * @return 结果
     */
    @RequiresPermissions("processPending:edit")
    @RequestMapping("completeTask")
    @ResponseBody
    public JsonResult<String> completeTask(String taskId, HttpServletRequest request) {
        try {
            // 整理表单数据
            Map<String, String> formProperties = new HashMap<String, String>();
            Map<String, String[]> parameterMap = request.getParameterMap();
            Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
            for (Entry<String, String[]> entry : entrySet) {
                formProperties.put(entry.getKey(), entry.getValue()[0]);
            }
            this.log.info("form parameters: {}", formProperties);
            // 办理任务
            this.identityService.setAuthenticatedUserId(this.getLoginName());
            this.formService.submitTaskFormData(taskId, formProperties);
            // 返回
            return new JsonResult<String>(Constants.SUCCESS, "办理成功");
        } finally {
            this.identityService.setAuthenticatedUserId(null);
        }
    }

    /**
     * <p>
     * Description: 加载任务表单
     * </p>
     * 
     * @param req 请求对象
     * @param taskId 任务ID
     * @return 结果
     * @throws SystemException 异常
     */
    @RequiresPermissions("processPending:view")
    @RequestMapping("loadTaskForm")
    public String loadTaskForm(HttpServletRequest req, String taskId) throws SystemException {

        // 表单类型(0:外置表单,1:动态表单,2:普通表单)
        int formType;

        // 获得任务
        Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();

        // 判断表单类型
        if (!StringUtils.isEmpty(task.getFormKey())) { // 外置表单
            // 根据流程定义ID读取外置表单
            Object taskForm = this.formService.getRenderedTaskForm(taskId);
            req.setAttribute("formData", taskForm);
            formType = 0;
        } else {
            // 读取表单定义
            TaskFormDataImpl taskFormData = (TaskFormDataImpl) this.formService.getTaskFormData(taskId);
            if (taskFormData != null) { // 动态表单
                String formData = ProcessUtils.formatForm(taskFormData.getFormProperties());
                req.setAttribute("formData", formData);
                formType = 1;
            } else { // 普通表单
                formType = Constants.NUMBER_2;
                throw new SystemException("普通表单加载,暂未开发");
            }
        }

        // 存放作用域
        req.setAttribute("formType", formType);
        req.setAttribute("taskId", taskId);

        // 将国际化信息转换成脚本放入作用域
        this.getLocalStript(req, "activiti.process.processPending.");
        return "llsfw/activiti/process/processPending/processPendingTask";

    }

    /**
     * <p>
     * Description: 加载待处理任务列表
     * </p>
     * 
     * @param page 页数
     * @param rows 行数
     * @return 结果
     */
    @RequiresPermissions("processPending:view")
    @RequestMapping("loadProcessPending")
    @ResponseBody
    public DataGridJsonResult<Map<String, Object>> loadProcessPendingReceipt(int page, int rows) {
        TaskQuery toClaimQuery = this.taskService.createTaskQuery().taskAssignee(this.getLoginName()).active()
                .includeProcessVariables().orderByTaskCreateTime().desc();
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
    @RequiresPermissions("processPending:view")
    @RequestMapping("init")
    public String init(HttpServletRequest req) {
        // 将国际化信息转换成脚本放入作用域
        this.getLocalStript(req, "activiti.process.processPending.");
        return "llsfw/activiti/process/processPending/processPendingMain";
    }

}
