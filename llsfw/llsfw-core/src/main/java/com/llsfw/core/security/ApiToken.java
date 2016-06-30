/**
 * ApiToken.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.security;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * <p>
 * ClassName: ApiToken
 * </p>
 * <p>
 * Description: Api的token令牌类
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class ApiToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    /**
     * <p>
     * Field clientIdentity: 用户名
     * </p>
     */
    private String clientIdentity;

    /**
     * <p>
     * Field clientDigest: 摘要
     * </p>
     */
    private String clientDigest;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param clientIdentity 用户名
     * @param clientDigest 摘要
     */
    public ApiToken(String clientIdentity, String clientDigest) {
        super();
        this.clientIdentity = clientIdentity;
        this.clientDigest = clientDigest;
    }

    @Override
    public Object getPrincipal() {
        return this.clientIdentity;
    }

    @Override
    public Object getCredentials() {
        return this.clientDigest;
    }

    public String getClientIdentity() {
        return this.clientIdentity;
    }

    public String getClientDigest() {
        return this.clientDigest;
    }

}
