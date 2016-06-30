/**
 * CsvFileUtil.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * ClassName: CommandExecutor
 * </p>
 * <p>
 * Description: 执行操作系统命令
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2015年3月13日
 * </p>
 */
public class CommandExecutor {

    /**
     * <p>
     * Field LOG: 日志
     * </p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Description: 执行操作系统命令
     * </p>
     * 
     * @param command 命令
     * @throws IOException 异常
     * @throws InterruptedException 异常
     */
    public void runCommand(String command) throws IOException, InterruptedException {
        Process child = null;
        BufferedReader reader = null;
        try {
            Runtime rt = Runtime.getRuntime();
            child = rt.exec(command);

            // 以下代码为控制台输出相关的批出理
            String line = null;
            reader = new BufferedReader(new InputStreamReader(child.getInputStream(), "UTF-8"));
            while ((line = reader.readLine()) != null) {
                this.log.info(line);
            }

            // 等待刚刚执行的命令的结束
            while (true) {
                if (child.waitFor() == 0) {
                    break;
                }
            }
        } finally {
            try {
                if (child != null) {
                    child.destroy();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                this.log.error("runCommand:", e);
            }
        }
    }
}
