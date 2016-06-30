/**
 * CheckMobile.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * ClassName: CheckMobile
 * </p>
 * <p>
 * Description: 检测是否移动设备
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class CheckMobile {

    /**
     * <p>
     * Field phoneReg:手机正则--- \b 是单词边界(连着的两个(字母字符 与 非字母字符)
     * 之间的逻辑上的间隔),字符串在编译时会被转码一次,所以是 "\\b"\B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔)
     * </p>
     */
    static String phoneReg = "\\bNokia|SAMSUNG|MIDP-2|CLDC1.1|SymbianOS|MAUI|UNTRUSTED/1.0"
            + "|Windows CE|iPhone|iPad|Android|BlackBerry|UCWEB|ucweb|BREW|J2ME|YULONG|YuLong|COOLPAD|TIANYU|TY-"
            + "|K-Touch|Haier|DOPOD|Lenovo|LENOVO|HUAQIN|AIGO-|CTC/1.0"
            + "|CTC/2.0|CMCC|DAXIAN|MOT-|SonyEricsson|GIONEE|HTC|ZTE|HUAWEI|webOS|GoBrowser|IEMobile|WAP2.0\\b";
    /**
     * <p>
     * Field tableReg: 平台正则
     * </p>
     */
    static String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser" + "|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

    /**
     * <p>
     * Field phonePat: 移动设备正则匹配：手机端
     * </p>
     */
    static Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);
    /**
     * <p>
     * Field tablePat: 移动设备正则匹配：平板
     * </p>
     */
    static Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);

    /**
     * 私有化构造函数
     */
    private CheckMobile() {
    }

    /**
     * 检测是否是移动设备访问
     * 
     * @param userAgent 浏览器标识
     * @return true:移动设备接入，false:pc端接入
     */
    public static boolean check(String userAgent) {
        // 如果传入为空,直接返回false
        if (null == userAgent) {
            return false;
        }
        // 匹配
        Matcher matcherPhone = phonePat.matcher(userAgent);
        Matcher matcherTable = tablePat.matcher(userAgent);
        if (matcherPhone.find() || matcherTable.find()) {
            return true;
        }
        return false;
    }
}
