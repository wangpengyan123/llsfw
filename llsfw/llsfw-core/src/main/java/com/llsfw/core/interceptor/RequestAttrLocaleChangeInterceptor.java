/**
 * RequestAttrLocaleChangeInterceptor.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.interceptor;

import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * <p>
 * ClassName: RequestAttrLocaleChangeInterceptor
 * </p>
 * <p>
 * Description: 国际化拦截器
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class RequestAttrLocaleChangeInterceptor extends LocaleChangeInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException {

        // 将父类的方法执行完毕
        super.preHandle(request, response, handler);

        // 获取当前国际化的设定,并且添加到request作用域中去
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver == null) {
            throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
        }
        Locale locale = localeResolver.resolveLocale(request);
        request.setAttribute(this.getParamName(), locale.toString());
        return true;
    }

}
