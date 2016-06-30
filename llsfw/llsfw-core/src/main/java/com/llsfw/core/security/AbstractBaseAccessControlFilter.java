/**
 * AbstractBaseAccessControlFilter.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.llsfw.core.common.Constants;
import com.llsfw.core.common.JsonResult;

/**
 * <p>
 * ClassName: AbstractBaseAccessControlFilter
 * </p>
 * <p>
 * Description: 基础过滤器
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public abstract class AbstractBaseAccessControlFilter extends AccessControlFilter {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private static final Logger LOG = LoggerFactory.getLogger(AbstractBaseAccessControlFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        return false;
    }

    /**
     * <p>
     * Description: 登录失败
     * </p>
     * 
     * @param response 响应
     * @param errorString 错误信息
     * @param e 异常
     * @throws IOException 异常
     */
    public void onLoginFail(ServletResponse response, String errorString, Exception e) throws IOException {
        LOG.error(errorString, e);
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Map<String, Object> error = new HashMap<String, Object>();
        error.put("errno", HttpServletResponse.SC_UNAUTHORIZED);
        error.put("error", errorString);
        JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>(Constants.FAIL, error);
        httpResponse.getWriter().write(new ObjectMapper().writeValueAsString(result));
    }

}
