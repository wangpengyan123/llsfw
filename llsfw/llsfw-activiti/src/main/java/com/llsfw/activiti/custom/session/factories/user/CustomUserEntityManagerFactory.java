/**
 * CustomUserEntityManagerFactory.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.activiti.custom.session.factories.user;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;

/**
 * <p>
 * ClassName: CustomUserEntityManagerFactory
 * </p>
 * <p>
 * Description: 用户工厂
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
public class CustomUserEntityManagerFactory implements SessionFactory {

    /**
     * <p>
     * Field customUserEntityManager: 用户管理
     * </p>
     */
    private CustomUserEntityManager customUserEntityManager;

    public void setCustomUserEntityManager(CustomUserEntityManager customUserEntityManager) {
        this.customUserEntityManager = customUserEntityManager;
    }

    @Override
    public Class<?> getSessionType() {
        return UserIdentityManager.class;
    }

    @Override
    public Session openSession() {
        return this.customUserEntityManager;
    }

}
