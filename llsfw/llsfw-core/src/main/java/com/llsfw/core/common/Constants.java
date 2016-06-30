/**
 * Constants.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * ClassName: Constants
 * </p>
 * <p>
 * Description: 常量
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年11月29日
 * </p>
 */
public class Constants {

    /**
     * <p>
     * Field NUMBER_1: 数字1
     * </p>
     */
    public static final int NUMBER_1 = 1;

    /**
     * <p>
     * Field NUMBER_2: 数字2
     * </p>
     */
    public static final int NUMBER_2 = 2;

    /**
     * <p>
     * Field NUMBER_3: 数字3
     * </p>
     */
    public static final int NUMBER_3 = 3;

    /**
     * <p>
     * Field NUMBER_4: 数字4
     * </p>
     */
    public static final int NUMBER_4 = 4;

    /**
     * <p>
     * Field NUMBER_5: 数字5
     * </p>
     */
    public static final int NUMBER_5 = 5;

    /**
     * <p>
     * Field NUMBER_6: 数字6
     * </p>
     */
    public static final int NUMBER_6 = 6;

    /**
     * <p>
     * Field NUMBER_10: 数字10
     * </p>
     */
    public static final int NUMBER_10 = 10;

    /**
     * <p>
     * Field NUMBER_16: 数字16
     * </p>
     */
    public static final int NUMBER_16 = 16;

    /**
     * <p>
     * Field NUMBER_24: 数字24
     * </p>
     */
    public static final int NUMBER_24 = 24;

    /**
     * <p>
     * Field NUMBER_30: 数字30
     * </p>
     */
    public static final int NUMBER_30 = 30;

    /**
     * <p>
     * Field NUMBER_50: 数字50
     * </p>
     */
    public static final int NUMBER_50 = 50;

    /**
     * <p>
     * Field NUMBER_60: 数字60
     * </p>
     */
    public static final int NUMBER_60 = 60;

    /**
     * <p>
     * Field NUMBER_100: 数字100
     * </p>
     */
    public static final int NUMBER_100 = 100;

    /**
     * <p>
     * Field NUMBER_120: 数字120
     * </p>
     */
    public static final int NUMBER_120 = 120;

    /**
     * <p>
     * Field NUMBER_128: 数字128
     * </p>
     */
    public static final int NUMBER_128 = 128;

    /**
     * <p>
     * Field NUMBER_200: 数字200
     * </p>
     */
    public static final int NUMBER_200 = 200;

    /**
     * <p>
     * Field NUMBER_256: 数字256
     * </p>
     */
    public static final int NUMBER_256 = 256;

    /**
     * <p>
     * Field NUMBER_100: 数字1000
     * </p>
     */
    public static final int NUMBER_1000 = 1000;

    /**
     * <p>
     * Field NUMBER_1024: 数字1024
     * </p>
     */
    public static final int NUMBER_1024 = 1024;

    /**
     * <p>
     * Field NUMBER_3500: 数字3500
     * </p>
     */
    public static final int NUMBER_3500 = 3500;

    /**
     * <p>
     * Field NUMBER_10000: 数字10000
     * </p>
     */
    public static final int NUMBER_10000 = 10000;

    /**
     * <p>
     * Field NUMBER_0X0F: 数字0X0F
     * </p>
     */
    public static final int NUMBER_0X0F = 0x0f;

    /**
     * <p>
     * Field SCHEDULER_CLEAR_OP_PSWD: 清除计划任务数据操作密码
     * </p>
     */
    public static final String SCHEDULER_CLEAR_OP_PSWD = "48527136";

    /**
     * <p>
     * Field DEFAULT_JOB_MAP_DATA: 空jobMap数据
     * </p>
     */
    public static final String EMPTY_JOB_MAP_DATA = "[]";

    /**
     * <p>
     * Field CURRENT_LOGIN_NAME: 当前登陆的用户名
     * </p>
     */
    public static final String CURRENT_LOGIN_NAME = "CURRENT_LOGIN_NAME";

    /**
     * <p>
     * Field DEF_CHARACTER_SET_ENCODING: 默认字符集编码
     * </p>
     */
    public static final String DEF_CHARACTER_SET_ENCODING = "UTF-8";

    /**
     * <p>
     * Field SUCCESS:成功
     * </p>
     */
    public static final String SUCCESS = "1";
    /**
     * <p>
     * Field FAIL:失败
     * </p>
     */
    public static final String FAIL = "-1";

    /**
     * <p>
     * Field APP_LEVEL: 应用级别
     * </p>
     */
    public static final int APP_LEVEL = 1;

    /**
     * <p>
     * Field MENU_LEVEL: 目录级别
     * </p>
     */
    public static final int MENU_LEVEL = 2;

    /**
     * <p>
     * Field FUNCTION_LEVEL: 功能级别
     * </p>
     */
    public static final int FUNCTION_LEVEL = 3;

    /**
     * <p>
     * Field IO_BUFFERED: 缓冲区大小
     * </p>
     */
    public static final int IO_BUFFERED = 1024;

    /**
     * <p>
     * Field EXCEPTION_MSG_LENGTH:
     * 记录异常的长度(oracle字段长度4000,预计在异常信息中不可能出现333个汉字,则定义为3000)
     * </p>
     */
    public static final int EXCEPTION_MSG_LENGTH = 3000;

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private static final Logger LOG = LoggerFactory.getLogger(Constants.class);

    /**
     * 私有化构造函数
     */
    private Constants() {

    }

    /**
     * 
     * 读取返回的信息
     * 
     * @param in 输入流
     * 
     * @return 数据
     * @throws IOException 异常
     */
    public static String getData(InputStream in) throws IOException {
        String result = "";
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in, DEF_CHARACTER_SET_ENCODING));
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
        } finally {
            if (result != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOG.error("getData", e);
                }
            }
        }
        return sb.toString();
    }
}
