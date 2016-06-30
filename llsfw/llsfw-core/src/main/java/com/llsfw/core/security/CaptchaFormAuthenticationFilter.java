/**
 * CaptchaFormAuthenticationFilter.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.google.code.kaptcha.Constants;
import com.llsfw.core.exception.IncorrectCaptchaException;

/**
 * <p>
 * ClassName: CaptchaFormAuthenticationFilter
 * </p>
 * <p>
 * Description: 验证码登陆过滤器
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {

    /**
     * 默认验证码参数
     */
    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

    /**
     * 验证码参数
     */
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    public String getCaptchaParam() {
        return this.captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        CaptchaUsernamePasswordToken token = createToken(request, response);
        try {
            /* 图形验证码验证 */
            doCaptchaValidate((HttpServletRequest) request, token);
            Subject subject = getSubject(request, response);
            subject.login(token); // 正常验证
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }

    @Override
    protected CaptchaUsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new CaptchaUsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha);
    }

    /**
     * 验证码校验
     * 
     * @param request 请求
     * @param token 令牌
     */
    private void doCaptchaValidate(HttpServletRequest request, CaptchaUsernamePasswordToken token) {
        // session中的图形码字符串
        String captcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        // 比对
        if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
            throw new IncorrectCaptchaException("验证码错误！");
        }
    }

    /**
     * 获取,并且清除验证码参数
     * 
     * @param request 请求
     * @return 结果
     */
    private String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

}
