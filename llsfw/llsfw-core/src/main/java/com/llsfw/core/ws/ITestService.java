/**
 * ItestService.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * <p>
 * ClassName: ITestService
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
public interface ITestService {

    /**
     * <p>
     * Description: 测试服务器1
     * </p>
     * 
     * @return 结果
     */
    public String getSayHello();

    /**
     * <p>
     * Description: 测试服务2
     * </p>
     * 
     * @param name 名称
     * @return 结果
     */
    public String getName(@WebParam(name = "name") String name);

}
