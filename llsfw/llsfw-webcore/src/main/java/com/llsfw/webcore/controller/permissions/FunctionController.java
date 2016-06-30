/**
 * FunctionController.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.controller.permissions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.llsfw.core.common.JsonResult;
import com.llsfw.core.controller.base.BaseController;
import com.llsfw.core.exception.SystemException;
import com.llsfw.webcore.service.permissions.FunctionService;
import com.llsfw.webgen.model.standard.permissions.TsFunction;
import com.llsfw.webgen.model.standard.permissions.TsPortal;
import com.llsfw.webgen.model.standard.permissions.TsPurview;

/**
 * <p>
 * ClassName: FunctionController
 * </p>
 * <p>
 * Description: 功能控制器
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Controller
@RequestMapping("functionController")
public class FunctionController extends BaseController {

    /**
     * <p>
     * Field FUNCTIONCODE: functionCode常量
     * </p>
     */
    private static final String FUNCTIONCODE = "functionCode";

    /**
     * <p>
     * Field fs: 功能服务
     * </p>
     */
    @Autowired
    private FunctionService fs;

    /**
     * 删除面板
     * 
     * @param functionCode 功能代码
     * @param portalCode 面板代码
     * @return 结果
     */
    @RequiresPermissions("functionController:edit")
    @RequestMapping("portalDelete")
    @ResponseBody
    public JsonResult<String> portalDelete(String functionCode, String portalCode) {
        return this.fs.portalDelete(functionCode, portalCode);
    }

    /**
     * 编辑面板
     * 
     * @param portal 面板数据
     * @return 结果
     */
    @RequiresPermissions("functionController:edit")
    @RequestMapping("portalAdd")
    @ResponseBody
    public JsonResult<String> portalAdd(TsPortal portal) {
        return this.fs.portalAdd(this.getLoginName(), portal);
    }

    /**
     * 跳转到面板编辑
     * 
     * @param op 操作
     * @param functionCode 功能代码
     * @param portalCode 面板代码
     * @param request 请求对象
     * @return 页面
     */
    @RequiresPermissions("functionController:view")
    @RequestMapping("toPortalAdd")
    public String toPortalAdd(String op, String functionCode, String portalCode, HttpServletRequest request) {
        request.setAttribute(FUNCTIONCODE, functionCode);
        request.setAttribute("op", op);
        request.setAttribute("portal", this.fs.loadPortal(functionCode, portalCode));
        return "/llsfw/function/portalAdd";
    }

    /**
     * 返回面板列表
     * 
     * @param functionCode 功能代码
     * @return 结果
     */
    @RequiresPermissions("functionController:view")
    @RequestMapping("getPortalList")
    @ResponseBody
    public List<TsPortal> getPortalList(String functionCode) {
        return this.fs.getPortalList(functionCode);
    }

    /**
     * <p>
     * Description: 修改操作权限
     * </p>
     * 
     * @param r 修改值
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @return 操作结果
     * @throws NoSuchMethodException 异常
     * @throws SecurityException 异常
     */
    @RequiresPermissions("functionController:edit")
    @RequestMapping("purviewEdit")
    @ResponseBody
    public JsonResult<String> purviewEdit(HttpServletRequest r, String functionCode, String purviewCode)
            throws SecurityException, NoSuchMethodException {
        return this.fs.purviewEdit(r.getParameterMap(), functionCode, purviewCode, this.getLoginName());
    }

    /**
     * <p>
     * Description: 加载操作权限
     * </p>
     * 
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @return 操作权限对象
     */
    @RequiresPermissions("functionController:view")
    @RequestMapping("loadPurview")
    @ResponseBody
    public TsPurview loadPurview(String functionCode, String purviewCode) {
        return this.fs.getPurview(functionCode, purviewCode);
    }

    /**
     * <p>
     * Description: 跳转到修改操作权限界面
     * </p>
     * 
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @param request 请求对象
     * @return 页面
     */
    @RequiresPermissions("functionController:view")
    @RequestMapping("toPurviewEdit")
    public String toPurviewEdit(String functionCode, String purviewCode, HttpServletRequest request) {
        request.setAttribute(FUNCTIONCODE, functionCode);
        request.setAttribute("purviewCode", purviewCode);
        return "/llsfw/function/purviewEdit";
    }

    /**
     * <p>
     * Description: 添加操作权限
     * </p>
     * 
     * @param req 请求对象
     * @param purview 操作权限
     * @return 结果
     */
    @RequiresPermissions("functionController:edit")
    @RequestMapping("purviewAdd")
    @ResponseBody
    public JsonResult<String> purviewAdd(HttpServletRequest req, TsPurview purview) {
        RequestContext requestContext = new RequestContext(req);
        return this.fs.purviewAdd(this.getLoginName(), purview, requestContext);
    }

    /**
     * <p>
     * Description: 跳转到操作权限添加界面
     * </p>
     * 
     * @param functionCode 功能代码
     * @param request 请求对象
     * @return 添加界面
     */
    @RequiresPermissions("functionController:view")
    @RequestMapping("toPurviewAdd")
    public String toPurviewAdd(String functionCode, HttpServletRequest request) {
        request.setAttribute(FUNCTIONCODE, functionCode);
        return "/llsfw/function/purviewAdd";
    }

    /**
     * <p>
     * Description: 删除操作权限
     * </p>
     * 
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @return 操作结果
     */
    @RequiresPermissions("functionController:edit")
    @RequestMapping("purviewDelete")
    @ResponseBody
    public JsonResult<String> purviewDelete(String functionCode, String purviewCode) {
        return this.fs.purviewDelete(functionCode, purviewCode);
    }

    /**
     * <p>
     * Description: 返回当前功能的上级列表
     * </p>
     * 
     * @param levelNo 级别编号
     * @return 当前功能的上级列表
     */
    @RequiresPermissions("functionController:view")
    @RequestMapping("loadParentFunction")
    @ResponseBody
    public List<TsFunction> loadParentFunction(Integer levelNo) {
        return this.fs.loadParentFunction(levelNo);
    }

    /**
     * <p>
     * Description: 更新功能
     * </p>
     * 
     * @param r 请求
     * @param functionCode 功能代码
     * @throws SystemException 异常
     * @return 操作结果
     * @throws NoSuchMethodException 异常
     * @throws SecurityException 异常
     */
    @RequiresPermissions("functionController:edit")
    @RequestMapping("editFunction")
    @ResponseBody
    public JsonResult<String> editFunction(HttpServletRequest r, String functionCode)
            throws SecurityException, NoSuchMethodException {
        return this.fs.editFunction(r.getParameterMap(), functionCode, this.getLoginName());
    }

    /**
     * <p>
     * Description: 获得功能对象
     * </p>
     * 
     * @param functionCode 功能代码
     * @return 功能对象
     */
    @RequiresPermissions("functionController:view")
    @RequestMapping("loadFunction")
    @ResponseBody
    public TsFunction loadFunction(String functionCode) {
        return this.fs.loadFunction(functionCode);
    }

    /**
     * <p>
     * Description: 跳转到功能编辑界面
     * </p>
     * 
     * @param functionCode 功能代码
     * @param request 请求对象
     * @return 功能编辑界面
     */
    @RequiresPermissions("functionController:view")
    @RequestMapping("toFunctionEdit")
    public String toFunctionEdit(String functionCode, HttpServletRequest request) {
        request.setAttribute(functionCode, functionCode);
        return "llsfw/function/functionEdit";
    }

    /**
     * <p>
     * Description: 保存功能
     * </p>
     * 
     * @param tf 功能对象
     * @return 操作结果
     */
    @RequiresPermissions("functionController:edit")
    @RequestMapping("addFunction")
    @ResponseBody
    public JsonResult<String> addFunction(TsFunction tf) {
        return this.fs.addFunction(tf, this.getLoginName());
    }

    /**
     * <p>
     * Description: 校验功能代码是否存在
     * </p>
     * 
     * @param functionCode 功能代码
     * @return false:不通过,true:通过
     */
    @RequiresPermissions("functionController:view")
    @RequestMapping("functionCodeUniqueCheck")
    @ResponseBody
    public boolean functionCodeUniqueCheck(String functionCode) {
        return this.fs.functionCodeUniqueCheck(functionCode);
    }

    /**
     * <p>
     * Description: 跳转到功能添加
     * </p>
     * 
     * @param levelNo 等级
     * @param parentFunctionCode 上级功能ID
     * @param request 请求
     * @return 功能添加界面
     */
    @RequiresPermissions("functionController:view")
    @RequestMapping("toFunctionAdd")
    public String toFunctionAdd(String levelNo, String parentFunctionCode, HttpServletRequest request) {
        request.setAttribute(levelNo, levelNo);
        request.setAttribute(parentFunctionCode, parentFunctionCode);
        return "llsfw/function/functionAdd";
    }

    /**
     * <p>
     * Description: 删除功能
     * </p>
     * 
     * @param functionCode 功能代码
     * @return 操作结果
     */
    @RequiresPermissions("functionController:edit")
    @RequestMapping("deleteFunction")
    @ResponseBody
    public JsonResult<String> deleteFunction(String functionCode) {
        return this.fs.deleteFunction(functionCode);
    }

    /**
     * <p>
     * Description: 获得权限列表
     * </p>
     * 
     * @param functionCode 功能代码
     * @return 权限列表
     */
    @RequiresPermissions("functionController:view")
    @RequestMapping("getPurviewList")
    @ResponseBody
    public List<TsPurview> getPurviewList(String functionCode) {
        return this.fs.getPurviewList(functionCode);
    }

    /**
     * <p>
     * Description: 返回功能菜单数据
     * </p>
     * 
     * @return 功能菜单数据
     */
    @RequiresPermissions("functionController:view")
    @RequestMapping("getAppNode")
    @ResponseBody
    public List<Map<String, Object>> getAppNode() {
        return this.fs.getAppNode(true);
    }

    /**
     * <p>
     * Description: 初始化方法
     * </p>
     * 
     * @param req 请求对象
     * @return 主页面
     */
    @RequiresPermissions("functionController:view")
    @RequestMapping("init")
    public String init(HttpServletRequest req) {
        this.getLocalStript(req, "function.");
        return "llsfw/function/functionMain";
    }

}
