/**
 * TestUserService.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.service.ws;

import org.springframework.stereotype.Service;

/**
 * <p>
 * ClassName: TestUserService
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
@Service
public class TestUserService {

    /**
     * <p>
     * Description: 测试方法
     * </p>
     * 
     * @return 列表
     */
    public String getSayHello() {
        return "HelloWord";
    }
}
