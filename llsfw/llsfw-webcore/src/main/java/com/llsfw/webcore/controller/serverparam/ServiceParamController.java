/**
 * ServiceParamController.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.controller.serverparam;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llsfw.core.common.JsonResult;
import com.llsfw.core.controller.base.BaseController;
import com.llsfw.generator.model.standard.system.TtServerGlobalParameters;
import com.llsfw.webcore.service.serverparam.ServiceParamService;

/**
 * <p>
 * ClassName: ServiceParamController
 * </p>
 * <p>
 * Description: 参数服务控制器
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Controller
@RequestMapping("serviceParamController")
public class ServiceParamController extends BaseController {

    /**
     * <p>
     * Field fsps: 服务
     * </p>
     */
    @Autowired
    private ServiceParamService fsps;

    /**
     * <p>
     * Description: 修改参数
     * </p>
     * 
     * @param request 请求对象
     * @param parametersCode 主键
     * @return 操作结果
     * @throws NoSuchMethodException 异常
     * @throws SecurityException 异常
     */
    @RequiresPermissions("serviceParamController:edit")
    @RequestMapping("editParameters")
    @ResponseBody
    public JsonResult<String> editParameters(HttpServletRequest request, String parametersCode)
            throws SecurityException, NoSuchMethodException {
        return this.fsps.editParameters(request.getParameterMap(), parametersCode);
    }

    /**
     * <p>
     * Description:加载参数对象
     * </p>
     * 
     * @param parametersCode 参数代码
     * @return 参数对象
     */
    @RequiresPermissions("serviceParamController:view")
    @RequestMapping("loadParam")
    @ResponseBody
    public TtServerGlobalParameters loadParam(String parametersCode) {
        return this.fsps.loadParam(parametersCode);
    }

    /**
     * <p>
     * Description: 跳转到参数修改界面
     * </p>
     * 
     * @param parametersCode 参数代码
     * @param request 请求对象
     * @return 页面
     */
    @RequiresPermissions("serviceParamController:view")
    @RequestMapping("toServerParamEdit")
    public String toServerParamEdit(String parametersCode, HttpServletRequest request) {
        request.setAttribute("PARAMETERS_CODE", parametersCode);
        return "llsfw/serverParam/serverParamEdit";
    }

    /**
     * <p>
     * Description: 删除参数
     * </p>
     * 
     * @param parametersCode 参数代码
     * @return 操作结果
     */
    @RequiresPermissions("serviceParamController:edit")
    @RequestMapping("deleteParam")
    @ResponseBody
    public JsonResult<String> deleteParam(String parametersCode) {
        return this.fsps.deleteParam(parametersCode);
    }

    /**
     * <p>
     * Description: 保存参数
     * </p>
     * 
     * @param tsgp 需保存参数的对象
     * @return 返回保存结果
     */
    @RequiresPermissions("serviceParamController:edit")
    @RequestMapping("addParameters")
    @ResponseBody
    public JsonResult<String> addParameters(TtServerGlobalParameters tsgp) {
        return this.fsps.addParameters(tsgp);
    }

    /**
     * <p>
     * Description: 校验参数代码是否重复
     * </p>
     * 
     * @param parametersCode 参数代码
     * @return true:通过,false:不通过
     */
    @RequiresPermissions("serviceParamController:view")
    @RequestMapping("parametersCodeUniqueCheck")
    @ResponseBody
    public boolean parametersCodeUniqueCheck(String parametersCode) {
        return this.fsps.parametersCodeUniqueCheck(parametersCode);
    }

    /**
     * <p>
     * Description: 跳转到参数新增界面
     * </p>
     * 
     * @return 参数新增界面
     */
    @RequiresPermissions("serviceParamController:view")
    @RequestMapping("toServerParamAdd")
    public String toServerParamAdd() {
        return "llsfw/serverParam/serverParamAdd";
    }

    /**
     * <p>
     * Description: 返回参数列表(无分页)
     * </p>
     * 
     * @param parametersCode 参数代码
     * @param parametersDesc 参数描述
     * @return 参数列表
     */
    @RequiresPermissions("serviceParamController:view")
    @RequestMapping("getParamsList")
    @ResponseBody
    public List<Map<String, Object>> getParamsList(String parametersCode, String parametersDesc) {
        return this.fsps.getParamsList(parametersCode, parametersDesc);
    }

    /**
     * <p>
     * Description: 初始化方法
     * </p>
     * 
     * @param req 请求参数
     * 
     * @return 主页面
     */
    @RequiresPermissions("serviceParamController:view")
    @RequestMapping("init")
    public String init(HttpServletRequest req) {
        this.getLocalStript(req, "serverParam.");
        return "llsfw/serverParam/serverParam";
    }
}
