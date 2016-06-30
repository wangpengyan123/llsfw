/**
 * ApiLoginToken.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.security;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * <p>
 * ClassName: ApiLoginToken
 * </p>
 * <p>
 * Description: api令牌
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class ApiLoginToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    /**
     * <p>
     * Field clientUserName: 用户名
     * </p>
     */
    private String clientUserName;

    /**
     * <p>
     * Field clientPassword: 密码
     * </p>
     */
    private String clientPassword;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param clientUserName 用户名
     * @param clientPassword 密码
     */
    public ApiLoginToken(String clientUserName, String clientPassword) {
        super();
        this.clientUserName = clientUserName;
        this.clientPassword = clientPassword;
    }

    @Override
    public Object getPrincipal() {
        return this.clientUserName;
    }

    @Override
    public Object getCredentials() {
        return this.clientPassword;
    }

    public String getClientUserName() {
        return this.clientUserName;
    }

    public String getClientPassword() {
        return this.clientPassword;
    }

}
