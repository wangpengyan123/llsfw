/**
 * PageInit.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.controller.serverparam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llsfw.core.controller.base.BaseController;
import com.llsfw.core.service.serverparam.ParamService;

/**
 * <p>
 * ClassName: PageInit
 * </p>
 * <p>
 * Description: 页面初始化
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月4日
 * </p>
 */
@Controller
@RequestMapping("pageInit")
public class PageInit extends BaseController {

    /**
     * <p>
     * Field pis: 页面初始化服务
     * </p>
     */
    @Autowired
    private ParamService pis;

    /**
     * <p>
     * Description: 获得指定参数
     * </p>
     * 
     * @param parametersCode 参数代码
     * @return 值
     */
    @RequestMapping("getServerParam")
    @ResponseBody
    public String getServerParam(String parametersCode) {
        return this.pis.getServerParamter(parametersCode);
    }
}
