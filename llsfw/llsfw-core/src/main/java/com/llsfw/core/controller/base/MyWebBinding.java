/**
 * MyWebBinding.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.controller.base;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * <p>
 * ClassName: MyWebBinding
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年11月29日
 * </p>
 */
public class MyWebBinding implements WebBindingInitializer {

    @Override
    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(Date.class, new DateConvertEditor());
    }

}
