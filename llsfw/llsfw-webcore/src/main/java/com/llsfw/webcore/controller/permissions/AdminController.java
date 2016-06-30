/**
 * AdminController.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.controller.permissions;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.llsfw.core.common.CookieUtil;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.controller.base.BaseController;
import com.llsfw.webcore.service.permissions.AdminService;
import com.llsfw.webcore.service.permissions.PortalService;

/**
 * <p>
 * ClassName: AdminController
 * </p>
 * <p>
 * Description: 控制台控制器
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    /**
     * <p>
     * Field uiType: UI类别
     * </p>
     */
    @Value("#{configProperties['mvc.uiType']}")
    private String uiType;

    /**
     * <p>
     * Field as: 控制台服务
     * </p>
     */
    @Autowired
    private AdminService as;

    /**
     * <p>
     * Field ps: 面板服务
     * </p>
     */
    @Autowired
    private PortalService ps;

    /**
     * <p>
     * Description: 跳转到主页
     * </p>
     * 
     * @param req 请求对象
     * @return 页面
     * @throws JsonProcessingException 异常
     */
    @RequestMapping("toMainPage")
    public String toMainPage(HttpServletRequest req) throws JsonProcessingException {
        // 将国际化信息转换成脚本放入作用域
        this.getLocalStript(req, "admin.");
        // 将portal信息放入作用域
        req.setAttribute("portal", new ObjectMapper().writeValueAsString(this.ps.getUserPortal(this.getLoginName())));
        return "llsfw/main";
    }

    /**
     * <p>
     * Description: 跳转到密码修改页面
     * </p>
     * 
     * @return 页面
     */
    @RequestMapping("toChangePswd")
    public String toChangePswd() {
        return "llsfw/changePswd";
    }

    /**
     * <p>
     * Description: 跳转到语言变更
     * </p>
     * 
     * @return 页面
     */
    @RequestMapping("/toChangeLanguage")
    public String toChangeLanguage() {
        return "llsfw/changeLanguage";
    }

    /**
     * <p>
     * Description: 跳转到主题变更
     * </p>
     * 
     * @return 页面
     */
    @RequestMapping("/toChangeTheme")
    public String toChangeTheme() {
        return "llsfw/changeTheme";
    }

    /**
     * <p>
     * Description: 修改密码
     * </p>
     * 
     * @param oldPswd 旧密码
     * @param newPswd 新密码
     * @return 操作结果
     */
    @RequestMapping("changePswd")
    @ResponseBody
    public JsonResult<String> changePswd(String oldPswd, String newPswd) {
        return this.as.changePswd(this.getLoginName(), oldPswd, newPswd);
    }

    /**
     * <p>
     * Description: 验证密码是否正确
     * </p>
     * 
     * @param oldPswd 旧密码
     * @return ture:正确,false:错误
     */
    @RequestMapping("pswdCheck")
    @ResponseBody
    public boolean pswdCheck(String oldPswd) {
        return this.as.pswdCheck(this.getLoginName(), oldPswd);
    }

    /**
     * <p>
     * Description: 变更主题
     * </p>
     * 
     * @param themeName 主题名称
     * @param response 响应对象
     * @return 结果
     * @throws UnsupportedEncodingException 异常
     */
    @RequestMapping("/changeTheme")
    public String changeTheme(String themeName, HttpServletResponse response) throws UnsupportedEncodingException {
        CookieUtil.setCookie(response, "themeName", themeName, "/", false);
        return "redirect:/";
    }

    /**
     * <p>
     * Description: 跳转到控制台
     * </p>
     * 
     * @param req 请求对象
     * @return 页面
     */
    @RequestMapping("/")
    public String admin(HttpServletRequest req) {

        req.setAttribute("loginName", this.getLoginName());
        req.setAttribute("userName", this.as.getUserName(this.getLoginName()));

        // 将国际化信息转换成脚本放入作用域
        this.getLocalStript(req, "admin.");

        //判断UI类别,分别跳转
        if ("responsive".equals(this.uiType)) {
            return "llsfw/responsive/admin";
        } else {
            // 获得用户当前菜单(这里写死一个参数,只查PC端的功能)
            String menu = this.as.buildApp(this.getLoginName(), "0");
            req.setAttribute("menu", menu);
            return "llsfw/admin";
        }
    }

}
