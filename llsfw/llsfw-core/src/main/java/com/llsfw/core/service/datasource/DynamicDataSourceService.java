/**
 * DynamicDataSourceService.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.service.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.service.base.BaseService;
import com.llsfw.generator.mapper.standard.system.TtDynamicDataSourceMapper;
import com.llsfw.generator.model.standard.system.TtDynamicDataSource;

/**
 * <p>
 * ClassName: DynamicDataSourceService
 * </p>
 * <p>
 * Description: 动态数据源服务
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
@Service
public class DynamicDataSourceService extends BaseService {

    /**
     * <p>
     * Field tddsm: 数据源服务器
     * </p>
     */
    @Autowired
    private TtDynamicDataSourceMapper tddsm;

    /**
     * 根据数据源名称返回数据源数据
     * 
     * @param dbsName 数据源名称
     * @return 数据源数据
     */
    public TtDynamicDataSource getDataSourceData(String dbsName) {
        return this.tddsm.selectByPrimaryKey(dbsName);
    }
}
