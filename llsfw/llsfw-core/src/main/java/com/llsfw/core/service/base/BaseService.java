/**
 * BaseService.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.service.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.llsfw.core.service.serverparam.ParamService;

/**
 * <p>
 * ClassName: BaseService
 * </p>
 * <p>
 * Description: 根服务
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月2日
 * </p>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BaseService {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field ps: 参数获取服务
     * </p>
     */
    @Autowired
    private ParamService ps;

    public Logger getLog() {
        return this.log;
    }

    public ParamService getPs() {
        return this.ps;
    }
}
