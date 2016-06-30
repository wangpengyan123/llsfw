/**
 * GenericResponseWrapper.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package org.activiti.explorer;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * <p>
 * ClassName: GenericResponseWrapper
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
public class GenericResponseWrapper extends HttpServletResponseWrapper {

    /**
     * <p>
     * Field output: 输出流
     * </p>
     */
    private ByteArrayOutputStream output;

    /**
     * <p>
     * Field contentLength: 长度
     * </p>
     */
    private int contentLength;

    /**
     * <p>
     * Field contentType: 类型
     * </p>
     */
    private String contentType;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param response 响应对象
     */
    public GenericResponseWrapper(HttpServletResponse response) {
        super(response);
        this.output = new ByteArrayOutputStream();
    }

    public byte[] getData() {
        return this.output.toByteArray();
    }

    public ServletOutputStream getOutputStream() {
        return new FilterServletOutputStream(this.output);
    }

    public PrintWriter getWriter() {
        return new PrintWriter(getOutputStream(), true);
    }

    @Override
    public void setContentLength(int length) {
        this.contentLength = length;
        super.setContentLength(length);
    }

    public int getContentLength() {
        return this.contentLength;
    }

    @Override
    public void setContentType(String type) {
        this.contentType = type;
        super.setContentType(type);
    }

    public String getContentType() {
        return this.contentType;
    }
}
