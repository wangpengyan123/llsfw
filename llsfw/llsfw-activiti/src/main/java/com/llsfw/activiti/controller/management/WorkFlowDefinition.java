/**
 * WorkFlowDefinition.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.activiti.controller.management;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
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

/**
 * <p>
 * ClassName: WorkFlowDefinition
 * </p>
 * <p>
 * Description: 工作流定义
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Controller
@RequestMapping(value = "activiti/management/workFlowDefinition")
public class WorkFlowDefinition extends BaseController {

    /**
     * <p>
     * Field repositoryService: 仓库服务
     * </p>
     */
    @Autowired
    RepositoryService repositoryService;

    /**
     * <p>
     * Description: 删除部署
     * </p>
     * 
     * @param deploymentId 部署ID
     * @return 结果
     */
    @RequiresPermissions("workFlowDefinition:edit")
    @RequestMapping(value = "deleteDeployment")
    @ResponseBody
    public JsonResult<String> deleteDeployment(String deploymentId) {
        this.repositoryService.deleteDeployment(deploymentId, true);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 更新状态
     * </p>
     * 
     * @param id 部署ID
     * @param type 类别
     * @return 结果
     */
    @RequiresPermissions("workFlowDefinition:edit")
    @RequestMapping(value = "updateState")
    @ResponseBody
    public JsonResult<String> updateState(String id, String type) {
        if ("1".equals(type)) {
            this.repositoryService.activateProcessDefinitionById(id, false, null);
        } else if ("0".equals(type)) {
            this.repositoryService.suspendProcessDefinitionById(id, true, null);
        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 读取资源
     * </p>
     * 
     * @param id id
     * @param type type
     * @param response 响应对象
     * @throws IOException 异常
     */
    @RequiresPermissions("workFlowDefinition:view")
    @RequestMapping("readResource")
    public void readResource(String id, String type, HttpServletResponse response) throws IOException {
        ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(id).singleResult();
        String resourceName = "";
        if ("image".equals(type)) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if ("xml".equals(type)) {
            resourceName = processDefinition.getResourceName();
        }
        InputStream resourceAsStream = this.repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
                resourceName);
        byte[] b = new byte[Constants.NUMBER_1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, Constants.NUMBER_1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }

    /**
     * <p>
     * Description: 加载流程定义列表
     * </p>
     * 
     * @param page 页数
     * @param rows 行数
     * @return 结果
     */
    @RequiresPermissions("workFlowDefinition:view")
    @RequestMapping("loadWorkFlowDefinitionList")
    @ResponseBody
    public DataGridJsonResult<Map<String, Object>> loadWorkFlowDefinitionList(int page, int rows) {
        long count = this.repositoryService.createProcessDefinitionQuery().count();
        List<ProcessDefinition> list = this.repositoryService.createProcessDefinitionQuery()
                .listPage(PageResult.getFistResult(page, rows), rows);
        List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
        for (ProcessDefinition processDefinition : list) {
            String deploymentId = processDefinition.getDeploymentId();
            Deployment deployment = this.repositoryService.createDeploymentQuery().deploymentId(deploymentId)
                    .singleResult();
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("id", processDefinition.getId());
            item.put("name", processDefinition.getName());
            item.put("key", processDefinition.getKey());
            item.put("description", processDefinition.getDescription());
            item.put("version", processDefinition.getVersion());
            item.put("deploymentId", processDefinition.getDeploymentId());
            item.put("resourceName", processDefinition.getResourceName());
            item.put("diagramResourceName", processDefinition.getDiagramResourceName());
            item.put("hasStartFormKey", processDefinition.hasStartFormKey());
            item.put("hasGraphicalNotation", processDefinition.hasGraphicalNotation());
            item.put("isSuspended", processDefinition.isSuspended());
            item.put("tenantId", processDefinition.getTenantId());
            item.put("deploymentTime", deployment.getDeploymentTime());
            rs.add(item);
        }
        return new DataGridJsonResult<Map<String, Object>>((int) count, rs);
    }

    /**
     * <p>
     * Description: 初始化
     * </p>
     * 
     * @param req 请求对象
     * @return 页面
     */
    @RequiresPermissions("workFlowDefinition:view")
    @RequestMapping("init")
    public String init(HttpServletRequest req) {
        // 将国际化信息转换成脚本放入作用域
        this.getLocalStript(req, "activiti.management.workFlowDefinition.");
        return "llsfw/activiti/management/workFlowDefinition/workFlowDefinitionMain";
    }

}
