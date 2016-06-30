/**
 * ThemeChangeInterceptor.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.interceptor;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.llsfw.core.common.CookieUtil;

/**
 * <p>
 * ClassName: ThemeChangeInterceptor
 * </p>
 * <p>
 * Description: 主题切换拦截器
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class ThemeChangeInterceptor implements HandlerInterceptor {

    /**
     * <p>
     * Field THEME_NAME: 默认主题参数名
     * </p>
     */
    private static final String THEME_NAME = "themeName";

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 系统默认皮肤
     */
    private String defailtTheme;

    public void setDefailtTheme(String defailtTheme) {
        this.defailtTheme = defailtTheme;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException {
        try {
            if (StringUtils.isEmpty(CookieUtil.getCookie(request, THEME_NAME))) {
                CookieUtil.setCookie(response, THEME_NAME, this.defailtTheme, "/", false);
            }
        } catch (UnsupportedEncodingException e) {
            this.log.error("RequestAttrThemeChangeInterceptor error:", e);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // Do nothing
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // Do nothing
    }

}
