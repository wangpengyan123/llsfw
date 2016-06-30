/**
 * LoginUserAuthenRealm.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.security.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.llsfw.core.security.realm.UserAuthenRealm;
import com.llsfw.webcore.service.permissions.AdminService;
import com.llsfw.webgen.mapper.standard.permissions.TsApplicationUserMapper;
import com.llsfw.webgen.model.standard.permissions.TsApplicationUser;

/**
 * <p>
 * ClassName: LoginUserAuthenRealm
 * </p>
 * <p>
 * Description: 登陆验证
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
public class LoginUserAuthenRealm extends UserAuthenRealm {

    /**
     * <p>
     * Field taum: 用户mapper
     * </p>
     */
    @Autowired
    private TsApplicationUserMapper taum;

    /**
     * <p>
     * Field as: 管理员服务
     * </p>
     */
    @Autowired
    private AdminService as;

    public TsApplicationUserMapper getTaum() {
        return this.taum;
    }

    public void setTaum(TsApplicationUserMapper taum) {
        this.taum = taum;
    }

    public AdminService getAs() {
        return this.as;
    }

    public void setAs(AdminService as) {
        this.as = as;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // 获得所关联的权限
        List<String> roleList = (List<String>) SecurityUtils.getSubject().getSession().getAttribute("roleList");
        Set<String> permissionsSet = (Set<String>) SecurityUtils.getSubject().getSession()
                .getAttribute("permissionsSet");

        // 判断权限是否存在,如果不存在,则查询,然后放入session作用域中
        if (CollectionUtils.isEmpty(roleList)) {
            roleList = this.as.findUserRoles(SecurityUtils.getSubject().getPrincipal().toString());
            SecurityUtils.getSubject().getSession().setAttribute("roleList", roleList);
        }
        if (CollectionUtils.isEmpty(permissionsSet)) {
            permissionsSet = this.as.findUserPermissions(SecurityUtils.getSubject().getPrincipal().toString(),
                    roleList);
            SecurityUtils.getSubject().getSession().setAttribute("permissionsSet", permissionsSet);
        }

        // 设置所关联的权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(new HashSet<String>(roleList));
        authorizationInfo.setStringPermissions(permissionsSet);
        return authorizationInfo;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {

        // 获取用户信息
        String username = (String) token.getPrincipal();
        TsApplicationUser user = this.taum.selectByPrimaryKey(username);

        // 判断账号是否正常
        if (user == null) {
            throw new UnknownAccountException(); // 没找到帐号
        }
        if ("0".equals(user.getUserStatus())) {
            throw new LockedAccountException(); // 帐号锁定
        }

        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        return new SimpleAuthenticationInfo(user.getLoginName(), user.getLoginPassword(),
                ByteSource.Util.bytes(user.getSalt()), getName());
    }

    //    public static void main(String[] arge) {
    //        String hashAlgorithmName = "md5";
    //        String pswd = "123456";
    //        int hashIterations = 2;
    //        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
    //        SimpleHash hash = new SimpleHash(hashAlgorithmName, pswd, ByteSource.Util.bytes(salt), hashIterations);
    //        String encodedPassword = hash.toHex();
    //        LOG.info(encodedPassword);
    //        LOG.info(salt);
    //    }
}
