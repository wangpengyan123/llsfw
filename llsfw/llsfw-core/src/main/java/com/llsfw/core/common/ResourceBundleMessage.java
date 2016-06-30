/**
 * ResourceBundleMessage.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.common;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * <p>
 * ClassName: ResourceBundleMessage
 * </p>
 * <p>
 * Description: 国际化扩展类
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class ResourceBundleMessage extends ResourceBundleMessageSource {
    /**
     * 获得属性对象
     * 
     * @param basename 基本名称
     * @param locale 本地对象
     * @return 结果
     */
    public ResourceBundle getBundle(String basename, Locale locale) {
        return this.getResourceBundle(basename, locale);
    }
}
