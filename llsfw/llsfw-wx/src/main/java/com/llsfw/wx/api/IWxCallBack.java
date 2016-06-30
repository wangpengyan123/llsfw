/**
 * IWxCallBack.java
 * Created at 2016-04-24
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api;

import javax.servlet.ServletInputStream;

import com.llsfw.wx.exception.WxException;

/**
 * <p>
 * ClassName: IWxCallBack
 * </p>
 * <p>
 * Description: 微信回调接口
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月24日
 * </p>
 */
public interface IWxCallBack {

    /**
     * <p>
     * Description: 加密/校验流程如下：
     * </p>
     * 
     * <p>
     * 1. 将token、timestamp、nonce三个参数进行字典序排序<br>
     * 2.将三个参数字符串拼接成一个字符串进行sha1加密<br>
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信<br>
     * </p>
     * 
     * @param systemToken 此加密密钥用于加密公众号Token
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数，nonce参数
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @throws WxException 异常
     */
    public void checkSignature(String systemToken, String signature, String timestamp, String nonce) throws WxException;

    /**
     * <p>
     * Description: 处理微信传递过来的消息
     * </p>
     * 
     * @param businessCode 业务代码
     * @param systemToken 此加密密钥用于加密公众号Token
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数，nonce参数
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param inputStream 消息流
     * @return 返回给微信的消息(需在5秒内处理完毕)
     * @throws WxException 异常
     */
    public String msessageHandler(String businessCode, String systemToken, String signature, String timestamp,
            String nonce, ServletInputStream inputStream) throws WxException;
}
