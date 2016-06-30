/**
 * JsonResult.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.common;

/**
 * <p>
 * ClassName: JsonResult
 * </p>
 * <p>
 * Description: json的返回结果
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年3月10日
 * </p>
 */
public class JsonResult<T> {
    /**
     * <p>
     * Field returnCode: 返回代码
     * </p>
     */
    private String returnCode;
    /**
     * <p>
     * Field result: 返回结果
     * </p>
     */
    private T result;

    /**
     * <p>
     * Description: 构造方法
     * </p>
     * 
     * @param returnCode 返回代码
     * @param result 返回结果
     */
    public JsonResult(String returnCode, T result) {
        this.returnCode = returnCode;
        this.result = result;
    }

    public String getReturnCode() {
        return this.returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}
