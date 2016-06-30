/**
 * ApiUserAuthenRealm.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.security.realm;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.PrincipalCollection;

import com.llsfw.core.security.ApiToken;
import com.llsfw.webgen.model.standard.permissions.TsApplicationUser;
import com.llsfw.webgen.model.standard.permissions.TsApplicationUserCriteria;

/**
 * <p>
 * ClassName: ApiUserAuthenRealm
 * </p>
 * <p>
 * Description: Api的验证
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
public class ApiUserAuthenRealm extends LoginUserAuthenRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        // 仅支持Api的Token类型的Token
        return token instanceof ApiToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        // 获得必要信息
        ApiToken apiToken = (ApiToken) token;
        String clientIdentity = apiToken.getClientIdentity();
        // 查询账户
        TsApplicationUserCriteria tauc = new TsApplicationUserCriteria();
        tauc.createCriteria().andUserIdEqualTo(clientIdentity);
        List<TsApplicationUser> users = this.getTaum().selectByExample(tauc);
        // 判断账号是否存在
        if (CollectionUtils.isEmpty(users)) {
            throw new UnknownAccountException(); // 没找到帐号
        }
        // 获得账户信息
        TsApplicationUser user = users.get(0);
        // 判断账号状态
        if ("0".equals(user.getUserStatus())) {
            throw new LockedAccountException(); // 帐号锁定
        }
        // 开始认证,并返回
        return new SimpleAuthenticationInfo(user.getLoginName(), user.getLoginPassword(), getName());
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
            return false;
        } else {
            return super.hasRole(principals, roleIdentifier);
        }
    }

}
