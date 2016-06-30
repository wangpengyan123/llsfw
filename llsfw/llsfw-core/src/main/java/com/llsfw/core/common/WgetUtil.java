/**
 * WgetUtil.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.common;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * ClassName: WgetUtil
 * </p>
 * <p>
 * Description: wget调用工具
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2015年3月23日
 * </p>
 */
public class WgetUtil {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private static final Logger LOG = LoggerFactory.getLogger(WgetUtil.class); 

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    private WgetUtil() {

    }

    /**
     * <p>
     * Description: 使用wget下载文件
     * </p>
     * 
     * @param downloadUrl 下载地址
     * @param saveFilePath 保存路径
     */
    public static void downloadFileByWget(String downloadUrl, String saveFilePath) {
        int retry = Constants.NUMBER_2;
        int time = 1;
        while (retry-- > 0) {
            ProcessBuilder pb = new ProcessBuilder("wget", downloadUrl, "-c", "-t", "10", "-T", "120", "-O",
                    saveFilePath);
            LOG.info("wget shell: {}", pb.command());
            Process ps = null;
            try {
                ps = pb.start();
            } catch (IOException e) {
                LOG.error("IOException", e);
            }
            int res = doWaitFor(ps, Constants.NUMBER_120 * time++);
            if (res != 0) {
                LOG.warn("Wget download failed...");
            } else {
                break;
            }
        }
    }

    /**
     * 等待
     * 
     * @param ps sub process
     * @param timeout 超时时间，SECONDS
     * @return 正常结束返回0
     */
    private static int doWaitFor(Process ps, int timeout) {
        int res = -1;
        if (ps == null) {
            return res;
        }
        boolean finished = false;
        int time = 0;
        WgetThreadUtil stdoutUtil = new WgetThreadUtil(ps.getInputStream());
        WgetThreadUtil erroroutUtil = new WgetThreadUtil(ps.getErrorStream());
        // 启动线程读取缓冲区数据
        stdoutUtil.start();
        erroroutUtil.start();
        while (!finished) {
            time++;
            if (time >= timeout) {
                LOG.info("Process wget timeout 30s, destroyed!");
                ps.destroy();
                break;
            }
            try {
                res = ps.exitValue();
                finished = true;
            } catch (IllegalThreadStateException e) {
                LOG.error("IllegalThreadStateException", e);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e1) {
                    LOG.error("doWaitFor", e1);
                }
            }
        }
        return res;
    }
}
