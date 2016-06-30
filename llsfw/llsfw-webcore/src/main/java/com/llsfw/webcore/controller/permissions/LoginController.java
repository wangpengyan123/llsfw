/**
 * LoginController.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.controller.permissions;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContext;

import com.llsfw.core.controller.base.BaseController;

/**
 * <p>
 * ClassName: LoginController
 * </p>
 * <p>
 * Description: 登陆控制器
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Controller
public class LoginController extends BaseController {

    /**
     * <p>
     * Field uiType: UI类别
     * </p>
     */
    @Value("#{configProperties['mvc.uiType']}")
    private String uiType;

    /**
     * <p>
     * Description: 登陆
     * </p>
     * 
     * @param req 请求对象
     * @return 结果
     */
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest req) {

        // 如果登陆,则直接跳转主页面
        if (SecurityUtils.getSubject().getPrincipal() != null) {
            return "redirect:/";
        }

        // 获得错误信息
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        RequestContext requestContext = new RequestContext(req);
        if ("org.apache.shiro.authc.UnknownAccountException".equals(exceptionClassName)
                || "org.apache.shiro.authc.LockedAccountException".equals(exceptionClassName)
                || "org.apache.shiro.authc.IncorrectCredentialsException".equals(exceptionClassName)
                || "com.llsfw.core.exception.IncorrectCaptchaException".equals(exceptionClassName)) {
            req.setAttribute("rv", requestContext.getMessage(exceptionClassName));
        } else {
            if (StringUtils.isNotEmpty(exceptionClassName)) {
                req.setAttribute("rv", requestContext.getMessage("login.error.other"));
            }
        }

        // 将国际化信息转换成脚本放入作用域
        this.getLocalStript(req, "login.");

        //判断UI类别,分别跳转
        if ("responsive".equals(this.uiType)) {
            return "llsfw/responsive/login";
        } else {
            return "llsfw/login";
        }
    }

}
