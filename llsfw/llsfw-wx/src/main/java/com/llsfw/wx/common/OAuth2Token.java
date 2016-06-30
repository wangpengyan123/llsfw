/**
 * OAuth2Token.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.common;

import java.util.Date;

import com.llsfw.core.common.Constants;
import com.llsfw.wx.api.impl.base.BaseResult;

/**
 * <p>
 * ClassName: OAuth2Token
 * </p>
 * <p>
 * Description: 网页授权对象
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
public class OAuth2Token extends BaseResult {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 1L;

    /**
     * <p>
     * Field access_token: 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     * </p>
     */
    private String access_token;

    /**
     * <p>
     * Field expires_in: access_token接口调用凭证超时时间，单位（秒）
     * </p>
     */
    private int expires_in;

    /**
     * <p>
     * Field refresh_token: 用户刷新access_token
     * </p>
     */
    private String refresh_token;

    /**
     * <p>
     * Field openid: 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     * </p>
     */
    private String openid;

    /**
     * <p>
     * Field scope: 用户授权的作用域，使用逗号（,）分隔
     * </p>
     */
    private String scope;

    /**
     * <p>
     * Field unionid: 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
     * </p>
     */
    private String unionid;

    /**
     * <p>
     * Field exprexpiredTime: 过期时间
     * </p>
     */
    private long exprexpiredTime;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    public OAuth2Token() {
    }

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param accessToken 凭证
     * @param exprexpiredTime 凭证过期事件
     */
    public OAuth2Token(String accessToken, int exprexpiredTime) {
        this.access_token = accessToken;
        setExpires_in(exprexpiredTime);
    }

    /**
     * <p>
     * Description: 获取 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     * </p>
     * 
     * @return 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     */
    public String getAccess_token() {
        return this.access_token;
    }

    /**
     * <p>
     * Description: 设置 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     * </p>
     * 
     * @param access_token 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     */
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    /**
     * <p>
     * Description: 获取 access_token接口调用凭证超时时间，单位（秒）
     * </p>
     * 
     * @return access_token接口调用凭证超时时间，单位（秒）
     */
    public int getExpires_in() {
        return this.expires_in;
    }

    /**
     * <p>
     * Description: 设置access_token接口调用凭证超时时间，单位（秒）
     * </p>
     * 
     * @param expires_in access_token接口调用凭证超时时间，单位（秒）
     */
    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
        //获取当前时间
        Date now = new Date();
        //获取当前时间毫秒数
        long nowLong = now.getTime();
        //设置下次过期时间 = 当前时间 + (凭证有效时间(秒) * 1000)
        this.exprexpiredTime = nowLong + (expires_in * Constants.NUMBER_1000);
    }

    /**
     * <p>
     * Description: 获取 用户刷新access_token
     * </p>
     * 
     * @return 用户刷新access_token
     */
    public String getRefresh_token() {
        return this.refresh_token;
    }

    /**
     * <p>
     * Description: 设置 用户刷新access_token
     * </p>
     * 
     * @param refresh_token 用户刷新access_token
     */
    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    /**
     * <p>
     * Description: 获取用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     * </p>
     * 
     * @return 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     */
    public String getOpenid() {
        return this.openid;
    }

    /**
     * <p>
     * Description: 设置用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     * </p>
     * 
     * @param openid 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * <p>
     * Description: 获取用户授权的作用域，使用逗号（,）分隔
     * </p>
     * 
     * @return 用户授权的作用域，使用逗号（,）分隔
     */
    public String getScope() {
        return this.scope;
    }

    /**
     * <p>
     * Description: 设置用户授权的作用域，使用逗号（,）分隔
     * </p>
     * 
     * @param scope 用户授权的作用域，使用逗号（,）分隔
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * <p>
     * Description: 设置 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
     * </p>
     * 
     * @return 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
     */
    public String getUnionid() {
        return this.unionid;
    }

    /**
     * <p>
     * Description: 获取 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
     * </p>
     * 
     * @param unionid 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public long getExprexpiredTime() {
        return this.exprexpiredTime;
    }

    public void setExprexpiredTime(long exprexpiredTime) {
        this.exprexpiredTime = exprexpiredTime;
    }

    /**
     * 判断用户凭证是否过期
     *
     * @return 过期返回 true,否则返回false
     */
    public boolean isExprexpired() {
        Date now = new Date();
        long nowLong = now.getTime();
        return nowLong > this.exprexpiredTime;
    }

    /**
     * 将数据转换为JSON数据包
     *
     * @return JSON数据包
     */
    @Override
    public String toString() {
        return "{\"access_token\"=\"" + this.access_token + "\",\"expires_in\"=" + this.getExpires_in() + "}";
    }

}
