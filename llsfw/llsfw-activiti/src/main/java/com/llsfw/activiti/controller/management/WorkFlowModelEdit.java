/**
 * WorkFlowModelEdit.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.activiti.controller.management;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.llsfw.core.common.Constants;
import com.llsfw.core.common.DataGridJsonResult;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.controller.base.BaseController;
import com.llsfw.core.pagequery.PageResult;

/**
 * <p>
 * ClassName: WorkFlowModelEdit
 * </p>
 * <p>
 * Description: 模型编辑
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Controller
@RequestMapping(value = "activiti/management/workFlowModelEdit")
public class WorkFlowModelEdit extends BaseController {

    /**
     * <p>
     * Field repositoryService: 仓库服务
     * </p>
     */
    @Autowired
    RepositoryService repositoryService;

    /**
     * <p>
     * Description: 下载模型
     * </p>
     * 
     * @param modelId 模型ID
     * @param response 响应对象
     * @throws IOException 异常
     */
    @RequiresPermissions("workFlowModelEdit:view")
    @RequestMapping("downloadWorkFlowModel")
    public void downloadWorkFlowModel(String modelId, HttpServletResponse response) throws IOException {
        Model modelData = this.repositoryService.getModel(modelId);
        BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
        byte[] modelEditorSource = this.repositoryService.getModelEditorSource(modelData.getId());
        JsonNode editorNode = new ObjectMapper().readTree(modelEditorSource);
        BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
        // 处理异常
        if (bpmnModel.getMainProcess() == null) {
            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            response.getOutputStream().println("no main process, can't export");
            response.flushBuffer();
            return;
        }
        String mainProcessId = bpmnModel.getMainProcess().getId();
        BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
        byte[] exportBytes = xmlConverter.convertToXML(bpmnModel);
        String filename = mainProcessId + ".bpmn";
        ByteArrayInputStream in = new ByteArrayInputStream(exportBytes);
        IOUtils.copy(in, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        response.flushBuffer();
    }

    /**
     * <p>
     * Description: 删除模型
     * </p>
     * 
     * @param modelId 模型ID
     * @return 结果
     */
    @RequiresPermissions("workFlowModelEdit:edit")
    @RequestMapping("deleteWorkFlowModel")
    @ResponseBody
    public JsonResult<String> deleteWorkFlowModel(String modelId) {
        this.repositoryService.deleteModel(modelId);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 添加模型
     * </p>
     * 
     * @param name 名称
     * @param key 键
     * @param description 描述
     * @return 结果
     * @throws JsonProcessingException 异常
     * @throws UnsupportedEncodingException 异常
     */
    @RequiresPermissions("workFlowModelEdit:edit")
    @RequestMapping("addWorkFlowModel")
    @ResponseBody
    public JsonResult<String> addworkFlowModel(String name, String key, String description)
            throws JsonProcessingException, UnsupportedEncodingException {
        Map<String, Object> editorNode = new HashMap<String, Object>();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        Map<String, Object> stencilSetNode = new HashMap<String, Object>();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);

        Map<String, Object> modelObjectNode = new HashMap<String, Object>();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, StringUtils.defaultString(description));

        Model modelData = this.repositoryService.newModel();
        modelData.setMetaInfo(new ObjectMapper().writeValueAsString(modelObjectNode));
        modelData.setName(name);
        modelData.setKey(StringUtils.defaultString(key));
        modelData.setTenantId(this.getLoginName());

        this.repositoryService.saveModel(modelData);
        this.repositoryService.addModelEditorSource(modelData.getId(),
                new ObjectMapper().writeValueAsString(editorNode).getBytes("utf-8"));

        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 跳转到添加模型页面
     * </p>
     * 
     * @return 页面
     */
    @RequiresPermissions("workFlowModelEdit:view")
    @RequestMapping("toAddWorkFlowModel")
    public String toAddworkFlowModel() {
        return "llsfw/activiti/management/workFlowModelEdit/addworkFlowModel";
    }

    /**
     * <p>
     * Description: 加载模型列表
     * </p>
     * 
     * @param page 页数
     * @param rows 行数
     * @return 结果
     */
    @RequiresPermissions("workFlowModelEdit:view")
    @RequestMapping("loadWorkFlowModelList")
    @ResponseBody
    public DataGridJsonResult<Model> loadWorkFlowModelList(int page, int rows) {
        long count = this.repositoryService.createModelQuery().count();
        List<Model> list = this.repositoryService.createModelQuery().listPage(PageResult.getFistResult(page, rows),
                rows);
        return new DataGridJsonResult<Model>((int) count, list);
    }

    /**
     * <p>
     * Description: 初始化
     * </p>
     * 
     * @param req 请求对象
     * @return 页面
     */
    @RequiresPermissions("workFlowModelEdit:view")
    @RequestMapping("init")
    public String init(HttpServletRequest req) {
        // 将国际化信息转换成脚本放入作用域
        this.getLocalStript(req, "activiti.management.workFlowModelEdit.");
        return "llsfw/activiti/management/workFlowModelEdit/workFlowModelEditMain";
    }

}
