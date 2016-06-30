/**
 * ProcessApplication.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.activiti.controller.process;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.form.StartFormDataImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llsfw.activiti.common.ProcessUtils;
import com.llsfw.core.common.Constants;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.controller.base.BaseController;
import com.llsfw.core.exception.SystemException;

/**
 * <p>
 * ClassName: ProcessApplication
 * </p>
 * <p>
 * Description: 流程发起
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Controller
@RequestMapping(value = "activiti/process/processApplication")
public class ProcessApplication extends BaseController {

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
     * Field repositoryService: 仓库服务
     * </p>
     */
    @Autowired
    private RepositoryService repositoryService;

    /**
     * <p>
     * Description: 启动流程
     * </p>
     * 
     * @param processDefinitionId 流程ID
     * @param request 请求对象
     * @return 结果
     */
    @RequestMapping("startProcessInstance")
    @ResponseBody
    public JsonResult<String> startProcessInstance(String processDefinitionId, HttpServletRequest request) {
        try {
            // 整理表单数据
            Map<String, String> formProperties = new HashMap<String, String>();
            Map<String, String[]> parameterMap = request.getParameterMap();
            Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
            for (Entry<String, String[]> entry : entrySet) {
                formProperties.put(entry.getKey(), entry.getValue()[0]);
            }
            this.log.info("startProcessInstance form parameters: {}", formProperties);
            // 开始启动流程
            this.identityService.setAuthenticatedUserId(this.getLoginName());
            ProcessInstance processInstance = this.formService.submitStartFormData(processDefinitionId, formProperties);
            this.log.info("start a processinstance: {}", processInstance);
            // 返回
            return new JsonResult<String>(Constants.SUCCESS, "流程启动成功");
        } finally {
            this.identityService.setAuthenticatedUserId(null);
        }
    }

    /**
     * <p>
     * Description: 初始化
     * </p>
     * 
     * @param req 请求对象
     * @param processDefinitionId 流程ID
     * @return 结果
     * @throws SystemException 异常
     */
    @RequestMapping("init")
    public String init(HttpServletRequest req, String processDefinitionId) throws SystemException {
        this.log.info("processDefinitionId:" + processDefinitionId);

        // 表单类型(0:外置表单,1:动态表单,2:普通表单)
        int formType;

        // 获得流程定义
        ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();

        // 判断流程是否存在
        if (processDefinition == null) {
            throw new SystemException("业务流程" + processDefinitionId + "不存在");
        }

        // 判断表单类型
        if (processDefinition.hasStartFormKey()) { // 外置表单
            // 根据流程定义ID读取外置表单
            Object startForm = this.formService.getRenderedStartForm(processDefinitionId);
            req.setAttribute("formData", startForm);
            formType = 0;
        } else {
            // 读取表单定义
            StartFormDataImpl startFormData = (StartFormDataImpl) this.formService
                    .getStartFormData(processDefinitionId);
            if (startFormData != null) { // 动态表单
                String formData = ProcessUtils.formatForm(startFormData.getFormProperties());
                req.setAttribute("formData", formData);
                formType = 1;
            } else { // 普通表单
                formType = Constants.NUMBER_2;
                throw new SystemException("普通表单加载,暂未开发");
            }
        }

        // 存放作用域
        req.setAttribute("formType", formType);
        req.setAttribute("processDefinitionId", processDefinitionId);

        // 将国际化信息转换成脚本放入作用域
        this.getLocalStript(req, "activiti.process.processApplication.");
        return "llsfw/activiti/process/processApplication/processApplicationMain";
    }

}
