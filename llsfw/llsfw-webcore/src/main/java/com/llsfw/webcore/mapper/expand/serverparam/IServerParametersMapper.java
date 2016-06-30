/**
 * IserverParametersMapper.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.mapper.expand.serverparam;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * ClassName: IServerParametersMapper
 * </p>
 * <p>
 * Description: 参数mapper
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
public interface IServerParametersMapper {

    /**
     * <p>
     * Description: 返回参数列表
     * </p>
     * 
     * @param parametersCodeParam 参数代码
     * @param parametersDescParam 参数描述
     * @return 参数列表
     */
    List<Map<String, Object>> getParamsList(@Param("parametersCodeParam") String parametersCodeParam,
            @Param("parametersDescParam") String parametersDescParam);

}
