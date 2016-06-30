/**
 * ApiLoginUserAuthenRealm.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.security.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.llsfw.core.security.ApiLoginToken;
import com.llsfw.webgen.model.standard.permissions.TsApplicationUser;

/**
 * <p>
 * ClassName: ApiLoginUserAuthenRealm
 * </p>
 * <p>
 * Description: api登陆验证
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
public class ApiLoginUserAuthenRealm extends LoginUserAuthenRealm {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private static final Logger LOG = LoggerFactory.getLogger(ApiLoginUserAuthenRealm.class);

    @Override
    public boolean supports(AuthenticationToken token) {
        // 仅支持Api的Token类型的Token
        return token instanceof ApiLoginToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        ApiLoginToken apiToken = (ApiLoginToken) token;
        String clientUserName = apiToken.getClientUserName();
        LOG.debug(clientUserName);
        TsApplicationUser user = this.getTaum().selectByPrimaryKey(clientUserName);
        // 判断账号是否正常
        if (user == null) {
            throw new UnknownAccountException(); // 没找到帐号
        }
        if ("0".equals(user.getUserStatus())) {
            throw new LockedAccountException(); // 帐号锁定
        }
        return new SimpleAuthenticationInfo(user.getLoginName(), user.getLoginPassword(),
                ByteSource.Util.bytes(user.getSalt()), getName());
    }

    /**
     * {@inheritDoc} 重写函数，如果不是web app登录，则用Api的授权
     */
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        if (principals.fromRealm(getName()).isEmpty()) {
            return false;
        } else {
            return super.isPermitted(principals, permission);
        }
    }

    /**
     * {@inheritDoc} 重写函数，如果不是web app登录，则用Api的授权
     */
    @Override
    public boolean hasRole(PrincipalCollection principals, String roleIdentifier) {
        if (principals.fromRealm(getName()).isEmpty()) {
            LOG.debug("not api login auth");
            return false;
        } else {
            return super.hasRole(principals, roleIdentifier);
        }
    }
}
