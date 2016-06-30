/**
 * BaseController.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.controller.base;

import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.RequestContext;

import com.llsfw.core.common.ResourceBundleMessage;
import com.llsfw.core.service.datasource.DynamicDataSourceService;
import com.llsfw.generator.model.standard.system.TtDynamicDataSource;

/**
 * <p>
 * ClassName: BaseController
 * </p>
 * <p>
 * Description: 父控制器
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月4日
 * </p>
 */
@Controller
public class BaseController {
    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    public Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field ddss: 动态数据源服务
     * </p>
     */
    @Autowired
    private DynamicDataSourceService ddss;

    /**
     * <p>
     * Field messageSource: 国际化资源服务
     * </p>
     */
    @Autowired
    @Qualifier("messageSource")
    private ResourceBundleMessage messageSource;

    /**
     * <p>
     * Description: 将国际化信息转换成脚本放入作用域
     * </p>
     * 
     * @param req 请求对象
     * @param name key
     */
    public void getLocalStript(HttpServletRequest req, String name) {
        RequestContext requestContext = new RequestContext(req);
        ResourceBundle rb = this.messageSource.getBundle("i18n.messages", requestContext.getLocale());
        Enumeration<String> keys = rb.getKeys();
        StringBuilder script = new StringBuilder();
        script.append("<script type='text/javascript'> \n");
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            boolean flg = key.startsWith("system.") || key.startsWith("base.") || key.startsWith("message.")
                    || key.startsWith("validatebox.");
            if (flg || key.startsWith(name)) {
                script.append(" var " + key.replace(".", "_") + " = '" + requestContext.getMessage(key) + "';  \n");
            }
        }
        script.append("</script>");
        req.setAttribute("localStript", script.toString());
    }

    /**
     * <p>
     * Description: 返回当前登陆的用户名
     * </p>
     * 
     * @return 用户名
     */
    public String getLoginName() {
        return SecurityUtils.getSubject().getPrincipal().toString();
    }

    /**
     * 返回数据源信息
     * 
     * @param dataSourceName 数据源名称
     * @return 数据源信息
     */
    public TtDynamicDataSource getDynamicDataSoucre(String dataSourceName) {
        this.log.debug("load DynamicDataSource name is : " + dataSourceName);
        return this.ddss.getDataSourceData(dataSourceName);
    }
}
