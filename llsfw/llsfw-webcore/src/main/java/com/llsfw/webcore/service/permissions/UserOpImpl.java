/**
 * UserOpImpl.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.service.permissions;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * ClassName: UserOpImpl
 * </p>
 * <p>
 * Description: 用户操作实现
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
public class UserOpImpl implements IUserOp {

    /**
     * <p>
     * Field uos: 用户操作
     * </p>
     */
    @Autowired
    private UserOpService uos;

    @Override
    public void changePswd(String hashAlgorithmName, int hashIterations, String loginName, String newPswd) {
        this.uos.changePswd(hashAlgorithmName, hashIterations, loginName, newPswd);
    }

    @Override
    public boolean pswdCheck(String hashAlgorithmName, int hashIterations, String loginName, String oldPswd) {
        return this.uos.pswdCheck(hashAlgorithmName, hashIterations, loginName, oldPswd);
    }

    @Override
    public String getUserName(String loginName) {
        return this.uos.getUserName(loginName);
    }

}
