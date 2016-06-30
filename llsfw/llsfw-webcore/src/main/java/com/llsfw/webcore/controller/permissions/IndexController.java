/**
 * IndexController.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.controller.permissions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.llsfw.core.controller.base.BaseController;

/**
 * <p>
 * ClassName: IndexController
 * </p>
 * <p>
 * Description: 首页控制器
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

    /**
     * <p>
     * Field uiType: UI类别
     * </p>
     */
    @Value("#{configProperties['mvc.uiType']}")
    private String uiType;

    /**
     * <p>
     * Description: 国际化变更
     * </p>
     * 
     * @return 页面
     */
    @RequestMapping("/localeChange")
    public String localeChange() {
        return "redirect:/";
    }

    /**
     * <p>
     * Description: 初始化方法
     * </p>
     * 
     * @param req 请求对象
     * @return 页面
     */
    @RequestMapping("/")
    public String index(HttpServletRequest req) {
        //判断UI类别,分别跳转
        if ("responsive".equals(this.uiType)) {
            return "llsfw/responsive/index";
        } else {
            return "llsfw/index";
        }
    }
}
