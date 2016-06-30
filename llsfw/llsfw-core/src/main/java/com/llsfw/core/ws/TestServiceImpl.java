/**
 * TestServiceImpl.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.ws;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.llsfw.core.service.ws.TestUserService;

/**
 * <p>
 * ClassName: TestServiceImpl
 * </p>
 * <p>
 * Description: 测试服务
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
@WebService
public class TestServiceImpl implements ITestService {

    /**
     * <p>
     * Field tus: service
     * </p>
     */
    @Autowired
    private TestUserService tus;

    @Override
    public String getSayHello() {
        return this.tus.getSayHello();
    }

    @Override
    public String getName(String name) {
        return "hello " + name;
    }

}
