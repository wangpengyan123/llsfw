/**
 * IuserOp.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.service.permissions;

/**
 * <p>
 * ClassName: IUserOp
 * </p>
 * <p>
 * Description: 用户操作接口
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
public interface IUserOp {

    /**
     * 返回用户姓名
     * 
     * @param loginName 登录名
     * @return 用户名
     */
    public String getUserName(String loginName);

    /**
     * 修改用户密码
     * 
     * @param hashAlgorithmName 加密方式
     * @param hashIterations 加密次数
     * @param loginName 用户名
     * @param newPswd 新密码
     */
    public void changePswd(String hashAlgorithmName, int hashIterations, String loginName, String newPswd);

    /**
     * 校验密码
     * 
     * @param hashAlgorithmName 加密方式
     * @param hashIterations 加密次数
     * @param loginName 用户名
     * @param oldPswd 老密码
     * @return 结果
     */
    public boolean pswdCheck(String hashAlgorithmName, int hashIterations, String loginName, String oldPswd);

}
