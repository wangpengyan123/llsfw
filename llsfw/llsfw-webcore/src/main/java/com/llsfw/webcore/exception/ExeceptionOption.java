/**
 * ExeceptionOption.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.llsfw.core.common.Constants;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.exception.ExceptionResolver;
import com.llsfw.core.exception.ExceptionUtil;
import com.llsfw.core.exception.SystemException;
import com.llsfw.core.exception.SystemRuntimeException;
import com.llsfw.core.interceptor.LogInterceptor;
import com.llsfw.generator.model.standard.system.TtRequestResponseLog;

/**
 * <p>
 * ClassName: ExeceptionOption
 * </p>
 * <p>
 * Description: 异常操作
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
public class ExeceptionOption extends ExceptionResolver {

    /**
     * <p>
     * Field ERRORMESSAGE: errorMessage常量
     * </p>
     */
    private static final String ERRORMESSAGE = "errorMessage";

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
            Exception exception) {
        this.log.error("exception:", exception);
        ModelAndView modelAndView = new ModelAndView(super.getErrorPage());
        modelAndView.addObject("exctption", ExceptionUtil.createStackTrackMessage(exception));
        try {
            if (exception instanceof UnauthorizedException) {
                // 格式化错误
                StringBuilder sb = new StringBuilder();
                sb.append("您没有权限做此操作");
                // 设置错误
                modelAndView.addObject(ERRORMESSAGE,
                        new ObjectMapper().writeValueAsString(new JsonResult<String>(Constants.FAIL, sb.toString())));
            } else if (exception instanceof SystemException) {
                // 格式化错误
                StringBuilder sb = new StringBuilder();
                sb.append("系统错误,详情如下:<br />" + exception.getMessage());
                // 设置错误
                modelAndView.addObject(ERRORMESSAGE,
                        new ObjectMapper().writeValueAsString(new JsonResult<String>(Constants.FAIL, sb.toString())));
            } else if (exception instanceof SystemRuntimeException) {
                // 格式化错误
                StringBuilder sb = new StringBuilder();
                sb.append("系统运行时错误,详情如下:<br />" + exception.getMessage());
                // 设置错误
                modelAndView.addObject(ERRORMESSAGE,
                        new ObjectMapper().writeValueAsString(new JsonResult<String>(Constants.FAIL, sb.toString())));
            } else {
                // 格式化错误
                StringBuilder sb = new StringBuilder();
                // 设置错误
                sb.append("未知系统错误,详情如下:<br />" + ExceptionUtil.createStackTrackMessage(exception));
                modelAndView.addObject(ERRORMESSAGE,
                        new ObjectMapper().writeValueAsString(new JsonResult<String>(Constants.FAIL, sb.toString())));
            }
            // 记录
            TtRequestResponseLog log = LogInterceptor.REQRESLOG.get();
            if (log != null) {
                log.setResponseView(super.getErrorPage());
                log.setExceptionClass(exception.getClass().getName());
                log.setExceptionMessage(exception.getMessage());
                log.setExceptionStackTrack(ExceptionUtil.createStackTrackMessage(exception));
                LogInterceptor.REQRESLOG.set(log);
            }
        } catch (JsonProcessingException e) {
            this.log.error("exception:", e);
        }
        return modelAndView;
    }

}
