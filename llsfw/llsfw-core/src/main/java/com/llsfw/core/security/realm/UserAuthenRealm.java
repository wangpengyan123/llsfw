/**
 * UserAuthenRealm.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.security.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * <p>
 * ClassName: UserAuthenRealm
 * </p>
 * <p>
 * Description: 用户权限验证
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年4月19日
 * </p>
 */
public class UserAuthenRealm extends AuthorizingRealm {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserAuthenRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // 获得所关联的权限
        List<String> roleList = new ArrayList<String>();
        Set<String> permissions = new HashSet<String>();

        // 设置所关联的权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(new HashSet<String>(roleList));
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {

        // 获取用户信息
        String username = (String) token.getPrincipal();

        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        return new SimpleAuthenticationInfo(username, null, null, getName());
    }

    /**
     * {@inheritDoc} 重写函数，如果不是web app登录，则用Api的授权
     */
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        if (principals.fromRealm(getName()).isEmpty()) {
            LOG.debug("not web app auth");
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
        LOG.debug("here");
        if (principals.fromRealm(getName()).isEmpty()) {
            LOG.debug("not web app auth");
            return false;
        } else {
            return super.hasRole(principals, roleIdentifier);
        }
    }

    //    public static void main(String[] arge) {
    //        String hashAlgorithmName = "md5";
    //        String pswd = "123456";
    //        int hashIterations = 2;
    //        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
    //        SimpleHash hash = new SimpleHash(hashAlgorithmName, pswd, ByteSource.Util.bytes(salt), hashIterations);
    //        String encodedPassword = hash.toHex();
    //        LOG.debug(encodedPassword);
    //        LOG.debug(salt);
    //    }
}
