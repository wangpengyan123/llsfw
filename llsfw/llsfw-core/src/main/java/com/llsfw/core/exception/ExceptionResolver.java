/**
 * ExceptionResolver.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * ClassName: ExceptionResolver
 * </p>
 * <p>
 * Description: 异常处理
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
@Component
public class ExceptionResolver implements HandlerExceptionResolver {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    public Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 错误页面
     */
    private String errorPage;

    public String getErrorPage() {
        return this.errorPage;
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
            Exception exception) {
        this.log.error("exception:", exception);
        String excpResult = ExceptionUtil.createStackTrackMessage(exception);
        ModelAndView modelAndView = new ModelAndView(this.errorPage);
        modelAndView.addObject("errorMsg", excpResult);
        return modelAndView;
    }

}
