/**
 * CaptchaUsernamePasswordToken.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.security;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * <p>
 * ClassName: CaptchaUsernamePasswordToken
 * </p>
 * <p>
 * Description: 验证码登陆令牌
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -2215765630192402048L;

    /**
     * <p>
     * Field captcha: 验证码字符串
     * </p>
     */
    private String captcha;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param username 用户名
     * @param password 密码
     * @param rememberMe 记住我
     * @param host 地址
     * @param captcha 验证码
     */
    public CaptchaUsernamePasswordToken(String username, char[] password, boolean rememberMe, String host,
            String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return this.captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}
