/**
 * CustomGroupEntityManagerFactory.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.activiti.custom.session.factories.group;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;

/**
 * <p>
 * ClassName: CustomGroupEntityManagerFactory
 * </p>
 * <p>
 * Description: 岗位工厂
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
public class CustomGroupEntityManagerFactory implements SessionFactory {

    /**
     * <p>
     * Field customGroupEntityManager: 岗位管理器
     * </p>
     */
    private CustomGroupEntityManager customGroupEntityManager;

    public void setCustomGroupEntityManager(CustomGroupEntityManager customGroupEntityManager) {
        this.customGroupEntityManager = customGroupEntityManager;
    }

    @Override
    public Class<?> getSessionType() {
        return GroupIdentityManager.class;
    }

    @Override
    public Session openSession() {
        return this.customGroupEntityManager;
    }

}
