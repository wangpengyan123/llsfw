/**
 * UserOpService.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.service.permissions;

import java.util.Date;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.service.base.BaseService;
import com.llsfw.webgen.mapper.standard.permissions.TsApplicationUserMapper;
import com.llsfw.webgen.model.standard.permissions.TsApplicationUser;

/**
 * <p>
 * ClassName: UserOpService
 * </p>
 * <p>
 * Description: 用户操作服务
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Service
public class UserOpService extends BaseService {

    /**
     * <p>
     * Field taum: 用户mapper
     * </p>
     */
    @Autowired
    private TsApplicationUserMapper taum;

    /**
     * 修改用户密码
     * 
     * @param hashAlgorithmName 加密方式
     * @param hashIterations 加密次数
     * @param loginName 用户名
     * @param newPswd 新密码
     */
    public void changePswd(String hashAlgorithmName, int hashIterations, String loginName, String newPswd) {
        TsApplicationUser tau = new TsApplicationUser();
        tau.setLoginName(loginName);
        tau.setUpdateBy(loginName);
        tau.setUpdateDate(new Date());

        // 设置密码
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        SimpleHash hash = new SimpleHash(hashAlgorithmName, newPswd, ByteSource.Util.bytes(salt), hashIterations);
        String encodedPassword = hash.toHex();
        tau.setLoginPassword(encodedPassword);
        tau.setSalt(salt);

        // 保存
        this.taum.updateByPrimaryKeySelective(tau);
    }

    /**
     * 校验密码
     * 
     * @param hashAlgorithmName 加密方式
     * @param hashIterations 加密次数
     * @param loginName 用户名
     * @param oldPswd 老密码
     * @return 结果
     */
    public boolean pswdCheck(String hashAlgorithmName, int hashIterations, String loginName, String oldPswd) {
        TsApplicationUser tau = this.taum.selectByPrimaryKey(loginName);
        ByteSource salt = ByteSource.Util.bytes(tau.getSalt());
        SimpleHash hash = new SimpleHash(hashAlgorithmName, oldPswd, salt, hashIterations);
        String oldPswdEncodedPassword = hash.toHex();
        return tau.getLoginPassword().equals(oldPswdEncodedPassword);
    }

    /**
     * 返回用户姓名
     * 
     * @param loginName 登录名
     * @return 用户名
     */
    public String getUserName(String loginName) {
        return this.taum.selectByPrimaryKey(loginName).getUserName();
    }

}
