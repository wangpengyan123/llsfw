/**
 * MultiSessionStorageEvaluator.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.security;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

/**
 * Web Application 需要用 session 进行状态保持<br />
 * Web Service 是Api的应用，不能保存session<br />
 * 所以需要分别这两种访问形式进行登录操作<br />
 * 用MultiSessionStorageEvaluator implements SessionStorageEvaluator 进行 session
 * 是否存储的设置<br />
 * 采用了检查请求HTTP头中的Accept信息的方式，若请求application/json则认为是Api的请求<br />
 * 
 * @author Administrator
 *
 */
public class MultiSessionStorageEvaluator implements SessionStorageEvaluator {

    @Override
    public boolean isSessionStorageEnabled(Subject subject) {
        boolean enabled = true;
        if (WebUtils.isWeb(subject)) {
            HttpServletRequest request = WebUtils.getHttpRequest(subject);
            @SuppressWarnings("rawtypes")
            // 包含无状态认证的相关参数,则不创建session
            Enumeration parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String parameterName = (String) parameterNames.nextElement();
                if ("clientIdentity".equals(parameterName) || "clientDigest".equals(parameterName)
                        || "clientUserName".equals(parameterName) || "clientPassword".equals(parameterName)) {
                    enabled = false;
                    continue;
                }
            }
        } else {
            return enabled;
        }
        return enabled;
    }

}
