/**
 * PortalController.java
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.controller.base.BaseController;
import com.llsfw.webcore.service.permissions.PortalService;
import com.llsfw.webgen.model.standard.permissions.TsUserPortal;

/**
 * <p>
 * ClassName: PortalController
 * </p>
 * <p>
 * Description: 面板控制器
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Controller
@RequestMapping("portalController")
public class PortalController extends BaseController {

    /**
     * <p>
     * Field PORTAL_STRING: portal.常量
     * </p>
     */
    private static final String PORTAL_STRING = "portal.";

    /**
     * <p>
     * Field ps: 面板服务
     * </p>
     */
    @Autowired
    private PortalService ps;

    /**
     * 删除面板
     * 
     * @param portalCode 面板代码
     * @return 结果
     */
    @RequiresPermissions("portalController:edit")
    @RequestMapping("portalDelete")
    @ResponseBody
    public JsonResult<String> portalDelete(String portalCode) {
        return this.ps.portalDelete(this.getLoginName(), portalCode);
    }

    /**
     * 编辑面板
     * 
     * @param portal 面板对象
     * @return 结果
     */
    @RequiresPermissions("portalController:edit")
    @RequestMapping("portalAdd")
    @ResponseBody
    public JsonResult<String> portalAdd(TsUserPortal portal) {
        return this.ps.portalAdd(this.getLoginName(), portal);
    }

    /**
     * 获得面板列表
     * 
     * @return 面板清单
     */
    @RequiresPermissions("portalController:view")
    @RequestMapping("loadPoartList")
    @ResponseBody
    public List<Map<String, Object>> loadPoartList() {
        return this.ps.loadPoartList(this.getLoginName());
    }

    /**
     * 跳转到编辑页面
     * 
     * @param op 操作
     * @param portalCode 面板代码
     * @param request 请求对象
     * @return 页面
     */
    @RequiresPermissions("portalController:view")
    @RequestMapping("toPortalAdd")
    public String toPortalAdd(String op, String portalCode, HttpServletRequest request) {
        request.setAttribute("op", op);
        request.setAttribute("portal", this.ps.loadPortal(this.getLoginName(), portalCode));
        return "/llsfw/portal/portalAdd";
    }

    /**
     * 获得用户面板列表
     * 
     * @return 结果
     */
    @RequiresPermissions("portalController:view")
    @RequestMapping("getUserPortal")
    @ResponseBody
    public List<Map<String, Object>> getUserPortal() {
        return this.ps.getUserPortal(this.getLoginName());
    }

    /**
     * 初始化方法
     * 
     * @param req 请求对象
     * @return 页面
     */
    @RequiresPermissions("portalController:view")
    @RequestMapping("init")
    public String init(HttpServletRequest req) {
        this.getLocalStript(req, PORTAL_STRING);
        return "llsfw/portal/portalMain";
    }

    /**
     * 系统介绍
     * 
     * @param req 请求对象
     * @return 页面
     */
    @RequiresPermissions("portalController:view")
    @RequestMapping("remarkInit")
    public String remarkInit(HttpServletRequest req) {
        this.getLocalStript(req, PORTAL_STRING);
        return "llsfw/portal/remarkInit/remarkInitMain";
    }

    /**
     * 在线session初始化方法
     * 
     * @param req 请求对象
     * @return 页面
     */
    @RequiresPermissions("portalController:view")
    @RequestMapping("onlineSecctionInit")
    public String onlineSecctionInit(HttpServletRequest req) {
        this.getLocalStript(req, PORTAL_STRING);
        return "llsfw/portal/onlineSession/onlineSessionMain";
    }

    /**
     * 加载在线session数据
     * 
     * @return 结果
     */
    @RequiresPermissions("portalController:view")
    @RequestMapping("loadOnlineSecctionData")
    @ResponseBody
    public List<Map<String, Object>> loadOnlineSecctionData() {
        return this.ps.loadOnlineSecctionData();
    }

    /**
     * 统计请求次数
     * 
     * @param req 请求对象
     * @return 页面
     * @throws JsonProcessingException 异常
     */
    @RequiresPermissions("portalController:view")
    @RequestMapping("reportRequestCount")
    public String reportRequestCount(HttpServletRequest req) throws JsonProcessingException {
        req.setAttribute("data", new ObjectMapper().writeValueAsString(this.ps.reportRequestCount()));
        return "llsfw/portal/reportRequestCount/reportRequestCountMain";
    }

    /**
     * 统计session个数
     * 
     * @param req 请求对象
     * @return 页面
     * @throws JsonProcessingException 异常
     */
    @RequiresPermissions("portalController:view")
    @RequestMapping("reportSessionCount")
    public String reportSessionCount(HttpServletRequest req) throws JsonProcessingException {
        req.setAttribute("data", new ObjectMapper().writeValueAsString(this.ps.reportSessionCount()));
        return "llsfw/portal/reportSessionCount/reportSessionCountMain";
    }

    /**
     * 统计异常率
     * 
     * @param req 请求对象
     * @return 页面
     * @throws JsonProcessingException 异常
     */
    @RequiresPermissions("portalController:view")
    @RequestMapping("reportExceptionCount")
    public String reportExceptionCount(HttpServletRequest req) throws JsonProcessingException {
        req.setAttribute("data", new ObjectMapper().writeValueAsString(this.ps.reportExceptionCount()));
        return "llsfw/portal/reportExceptionCount/reportExceptionCountMain";
    }
}
