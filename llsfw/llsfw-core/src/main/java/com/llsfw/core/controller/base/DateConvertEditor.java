/**
 * DateConvertEditor.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.controller.base;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * <p>
 * ClassName: DateConvertEditor
 * </p>
 * <p>
 * Description: 转换Date类型的请求数据
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年11月29日
 * </p>
 */
public class DateConvertEditor extends PropertyEditorSupport {

    /**
     * <p>
     * Field DATA_FORMAT_10: 10位长度的日期
     * </p>
     */
    private static final int DATA_FORMAT_10 = 10;

    /**
     * <p>
     * Field DATA_FORMAT_19: 19位长度的日期
     * </p>
     */
    private static final int DATA_FORMAT_19 = 19;

    /**
     * <p>
     * Field DATA_FORMAT_21: 21位长度的日期
     * </p>
     */
    private static final int DATA_FORMAT_21 = 21;

    /**
     * <p>
     * Field datetimeFormat: yyyy-MM-dd HH:mm:ss 格式化日期
     * </p>
     */
    private SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * <p>
     * Field dateFormat: yyyy-MM-dd 格式化日期
     * </p>
     */
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass()); 

    /**
     * <p>
     * Description: 日期格式转换
     * </p>
     * 
     * @param text 日期型数据
     * @throws IllegalArgumentException 格式化异常
     */
    @Override
    public void setAsText(String text) {
        if (StringUtils.hasText(text)) {
            try {
                formatDate(text);
            } catch (ParseException ex) {
                this.log.error("ParseException", ex);
            }
        } else {
            setValue(null);
        }
    }

    /**
     * <p>
     * Description: 格式化日期
     * </p>
     * 
     * @param text 需要格式化的日期数据
     * @throws ParseException 转换异常
     */
    private void formatDate(String text) throws ParseException {
        String t = text;
        if (t.indexOf(":") == -1 && t.length() == DATA_FORMAT_10) {
            setValue(this.dateFormat.parse(t));
        } else if (t.indexOf(":") != -1 && t.length() == DATA_FORMAT_19) {
            setValue(this.datetimeFormat.parse(t));
        } else if (t.indexOf(":") != -1 && t.length() == DATA_FORMAT_21) {
            t = t.replace(".0", "");
            setValue(this.datetimeFormat.parse(t));
        } else {
            throw new IllegalArgumentException("Could not parse date, date format is error ");
        }
    }
}
