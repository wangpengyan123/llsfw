/**
 * CsvFileUtil.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;

import com.llsfw.core.exception.SystemException;

/**
 * <p>
 * ClassName: ApiAuthcFilter
 * </p>
 * <p>
 * Description: api过滤器
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class ApiAuthcFilter extends AbstractBaseAccessControlFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        try {
            // 客户端传入的用户身份信息
            String clientIdentity = request.getParameter("clientIdentity");
            // 客户端传入的用户认证信息
            String clientDigest = request.getParameter("clientDigest");
            // 判断身份和认证信息是否都传入了
            if (StringUtils.isEmpty(clientIdentity) || StringUtils.isEmpty(clientDigest)) {
                throw new SystemException("clientIdentity or clientDigest is null");
            }
            // 生成Api的Token
            ApiToken token = new ApiToken(clientIdentity, clientDigest);
            // 获得Subject
            Subject subject = getSubject(request, response);
            // 委托给Realm进行登录
            subject.login(token);
        } catch (UnknownAccountException e) {
            onLoginFail(response, "No Account", e);
            return false;
        } catch (LockedAccountException e) {
            onLoginFail(response, "Account Locked", e);
            return false;
        } catch (AuthenticationException e) {
            onLoginFail(response, "Login Error", e); // 6、登录失败
            return false;
        }
        return true;
    }

}
