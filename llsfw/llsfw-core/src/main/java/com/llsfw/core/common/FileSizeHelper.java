/**
 * FileSizeHelper.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.common;

import java.text.DecimalFormat;

/**
 * <p>
 * ClassName: FileSizeHelper
 * </p>
 * <p>
 * Description: 文件大小工具类.
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class FileSizeHelper {

    /**
     * <p>
     * Field ONE_KB: KB
     * </p>
     */
    public static final long ONE_KB = 1024;
    /**
     * <p>
     * Field ONE_MB: MB
     * </p>
     */
    public static final long ONE_MB = ONE_KB * 1024;
    /**
     * <p>
     * Field ONE_GB: GB
     * </p>
     */
    public static final long ONE_GB = ONE_MB * 1024;
    /**
     * <p>
     * Field ONE_TB: TP
     * </p>
     */
    public static final long ONE_TB = ONE_GB * 1024;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    private FileSizeHelper() {

    }

    /**
     * <p>
     * Description: 转换大小
     * </p>
     * 
     * @param fileSize 文件尺寸
     * @return 转换后的大小
     */
    public static String getHumanReadableFileSize(long fileSize) {
        String result = getHumanReadableFileSize(fileSize, ONE_TB, "TB");
        if (result != null) {
            return result;
        }
        result = getHumanReadableFileSize(fileSize, ONE_GB, "GB");
        if (result != null) {
            return result;
        }
        result = getHumanReadableFileSize(fileSize, ONE_MB, "MB");
        if (result != null) {
            return result;
        }
        result = getHumanReadableFileSize(fileSize, ONE_KB, "KB");
        if (result != null) {
            return result;
        }
        return String.valueOf(fileSize) + "B";
    }

    /**
     * <p>
     * Description: 转换后的大小
     * </p>
     * 
     * @param fileSize 文件尺寸
     * @param unit 单位
     * @param unitName 单位名称
     * @return 转换后的大小
     */
    private static String getHumanReadableFileSize(long fileSize, long unit, String unitName) {
        if (fileSize == 0) {
            return "0";
        }

        if (fileSize / unit >= 1) {
            double value = fileSize / (double) unit;
            DecimalFormat df = new DecimalFormat("######.##" + unitName);
            return df.format(value);
        }
        return null;
    }
}
