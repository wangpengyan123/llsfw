/**
 * WgetThreadUtil.java
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
 * ClassName: WgetThreadUtil
 * </p>
 * <p>
 * Description: wget线程
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2015年3月23日
 * </p>
 */
class WgetThreadUtil implements Runnable {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass()); 

    /**
     * <p>
     * Field character: 设置读取的字符编码
     * </p>
     */
    private String character = "UTF-8";

    /**
     * <p>
     * Field inputStream: 输入流
     * </p>
     */
    private InputStream inputStream;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param inputStream 输入流
     */
    public WgetThreadUtil(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * <p>
     * Description: 开始
     * </p>
     */
    public void start() {
        Thread thread = new Thread(this);
        thread.setDaemon(true); // 将其设置为守护线程
        thread.start();
    }

    @Override
    public void run() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(this.inputStream, this.character));
            String line = null;
            while ((line = br.readLine()) != null) {
                this.log.info(line);
            }
        } catch (IOException e) {
            this.log.error("WgetThreadUtil:", e);
        } finally {
            try {
                // 释放资源
                if (br != null) {
                    this.inputStream.close();
                    br.close();
                }
            } catch (IOException e) {
                this.log.error("WgetThreadUtil:", e);
            }
        }
    }

}
