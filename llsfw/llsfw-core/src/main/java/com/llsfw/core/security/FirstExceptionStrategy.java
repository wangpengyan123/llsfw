/**
 * FirstExceptionStrategy.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.realm.Realm;

/**
 * <p>
 * ClassName: FirstExceptionStrategy
 * </p>
 * <p>
 * Description: 区别reaml的处理
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class FirstExceptionStrategy extends FirstSuccessfulStrategy {

    @Override
    public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo,
            AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {
        if ((t != null) && (t instanceof AuthenticationException)) {
            throw (AuthenticationException) t;
        }
        return super.afterAttempt(realm, token, singleRealmInfo, aggregateInfo, t);
    }
}
