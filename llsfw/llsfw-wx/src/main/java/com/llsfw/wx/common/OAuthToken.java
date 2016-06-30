/**
 * OAuthToken.java
 * Created at 2016-04-23
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.common;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.llsfw.wx.exception.WxException;

/**
 * <p>
 * ClassName: OAuthToken
 * </p>
 * <p>
 * Description: 微信平台用户凭证对象
 * <p>
 * 通过<tt>Weixin</tt>产生一个请求对象，对应生成一个<tt>HttpClient</tt>， 每次登陆产生一个<tt>OAuth</tt> 用户连接,使用<tt>OAuthToken</tt>
 * 可以不用重复向微信平台发送登陆请求，在没有过期时间内，可继续请求。
 * </p>
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月23日
 * </p>
 */
public class OAuthToken implements Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 7564525878908755452L;

    /**
     * <p>
     * Field accessToken: 用户凭证
     * </p>
     */
    private String access_token;

    /**
     * <p>
     * Field expiresIn: 凭证有效时间，单位：秒
     * </p>
     */
    private int expires_in;

    /**
     * <p>
     * Field exprexpiredTime: 凭证下次过期时间
     * </p>
     */
    private long exprexpiredTime;

    /**
     * <p>
     * Field createTime: 凭证创建时间
     * </p>
     */
    private long createTime;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param access_token 用户凭证
     * @param expiresIn 凭证过期时间
     */
    public OAuthToken(String access_token, int expiresIn) {
        this(access_token, expiresIn, new Date().getTime());
    }

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param access_token 用户凭证
     * @param expiresIn 凭证过期时间
     * @param createTime 凭证创建时间
     */
    public OAuthToken(String access_token, int expiresIn, long createTime) {
        this.setAccess_token(access_token);
        this.setExpires_in(expiresIn, createTime);
    }

    /**
     * <p>
     * Description: 通过微信公众平台返回JSON对象创建凭证对象
     * </p>
     * <p>
     * 正常情况下，微信会返回下述JSON数据包给公众号： {"access_token":"ACCESS_TOKEN","expires_in":7200}
     * </p>
     * 
     * @param jsonObj json数据
     * @throws WxException 异常
     */
    public OAuthToken(Map<?, ?> jsonObj) throws WxException {
        this.setAccess_token(jsonObj.get("access_token").toString());
        int pExpiresIn = Integer.parseInt(jsonObj.get("expires_in").toString());
        if (jsonObj.containsKey("create_time")) {
            //获取创建时间
            long pCreateTime = Long.parseLong(jsonObj.get("create_time").toString());
            if (pCreateTime > 0) {
                this.setExpires_in(pExpiresIn, pCreateTime);
            } else {
                this.setExpires_in(pExpiresIn, new Date().getTime());
            }
        } else {
            this.setExpires_in(pExpiresIn, new Date().getTime());
        }

    }

    /**
     * <p>
     * Description: 判断用户凭证是否过期
     * </p>
     * 
     * @return 过期返回 true,否则返回false
     */
    public boolean isExprexpired() {
        Date now = new Date();
        long nowLong = now.getTime();
        return nowLong >= this.exprexpiredTime;
    }

    /**
     * <p>
     * Description: 获取用户凭证
     * </p>
     * 
     * @return 用户凭证
     */
    public String getAccess_token() {
        return this.access_token;
    }

    /**
     * <p>
     * Description: 设置用户凭证
     * </p>
     * 
     * @param access_token 用户凭证
     */
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    /**
     * <p>
     * Description: 获得凭证有效时间，单位：秒
     * </p>
     * 
     * @return 凭证有效时间，单位：秒
     */
    public int getExpires_in() {
        return this.expires_in;
    }

    /**
     * <p>
     * Description: 设置 凭证有效时间，单位：秒
     * <p>
     * 为了与微信服务器保存同步，误差设置为提前1分钟，即：将创建时间提早1分钟
     * </p>
     * </p>
     * 
     * @param paramExpires_in 凭证有效时间，单位：秒
     * @param paramCreateTime 凭证创建时间
     */
    public void setExpires_in(int paramExpires_in, long paramCreateTime) {
        //设置过期时间
        this.expires_in = paramExpires_in;
        this.setCreateTime(paramCreateTime);
        //获取当前时间毫秒数 - 1 分钟，即提前1分钟过期
        final long EXPR_TIME = 60000; // 一分钟
        //设置下次过期时间 = 当前时间 + (凭证有效时间(秒) * 1000)
        final long EXPERXPIRED_TIME = 1000;
        this.setExprexpiredTime(this.getCreateTime() - EXPR_TIME + (this.getExpires_in() * EXPERXPIRED_TIME));
    }

    /**
     * <p>
     * Description: 获得凭证下次过期时间
     * </p>
     * 
     * @return 凭证下次过期时间
     */
    public long getExprexpiredTime() {
        return this.exprexpiredTime;
    }

    /**
     * <p>
     * Description: 设置凭证下次过期时间
     * </p>
     * 
     * @param exprexpiredTime 凭证下次过期时间
     */
    private void setExprexpiredTime(long exprexpiredTime) {
        this.exprexpiredTime = exprexpiredTime;
    }

    /**
     * <p>
     * Description: 获取凭证创建时间
     * </p>
     * 
     * @return 凭证创建时间
     */
    public long getCreateTime() {
        return this.createTime;
    }

    /**
     * <p>
     * Description: 设置凭证创建时间
     * </p>
     * 
     * @param createTime 凭证创建时间
     */
    private void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        //对外的时间 需要加上扣掉的 60秒
        return "{\"access_token\":\"" + this.getAccess_token() + "\",\"expires_in\":" + this.getExpires_in()
                + ",\"create_time\" : " + this.getCreateTime() + "}";
    }

}
