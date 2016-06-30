/**
 * FilterServletOutputStream.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package org.activiti.explorer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * <p>
 * ClassName: FilterServletOutputStream
 * </p>
 * <p>
 * Description: 配置
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
public class FilterServletOutputStream extends ServletOutputStream {

    /**
     * <p>
     * Field stream: 流
     * </p>
     */
    private DataOutputStream stream;

    /**
     * <p>
     * Field writeListener: 监听器
     * </p>
     */
    @SuppressWarnings("unused")
    private WriteListener writeListener;

    /**
     * <p>
     * Description: 配置
     * </p>
     * 
     * @param output 流
     */
    public FilterServletOutputStream(OutputStream output) {
        this.stream = new DataOutputStream(output);
    }

    /**
     * <p>
     * Description: 配置
     * </p>
     * 
     * @param b 数据
     * @exception IOException 异常
     */
    public void write(int b) throws IOException {
        this.stream.write(b);
    }

    /**
     * <p>
     * Description: 配置
     * </p>
     * 
     * @param b 数据
     * @exception IOException 异常
     */
    public void write(byte[] b) throws IOException {
        this.stream.write(b);
    }

    /**
     * <p>
     * Description: 配置
     * </p>
     * 
     * @param b b
     * @param off off
     * @param len len
     * @exception IOException 异常
     */
    public void write(byte[] b, int off, int len) throws IOException {
        this.stream.write(b, off, len);
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        this.writeListener = writeListener;
    }

    @Override
    public boolean isReady() {
        return true;
    }
}
