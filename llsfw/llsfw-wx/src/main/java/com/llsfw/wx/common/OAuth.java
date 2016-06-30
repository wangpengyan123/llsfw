/**
 * Oauth.java
 * Created at 2016-04-23
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.common;

import java.io.Serializable;

/**
 * <p>
 * ClassName: OAuth
 * </p>
 * <p>
 * Description: 微信公众号对象
 * <p>
 * 每一次请求即对应一个<tt>HttpClient</tt>， 每次登陆产生一个<tt>OAuth</tt>用户连接,使用 <tt>OAuthToken</tt> 可以不用重复向微信平台发送登陆请求，在没有过期时间内，可继续请求。
 * </p>
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月23日
 * </p>
 */
public class OAuth implements Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 4068745115847196695L;

    /**
     * <p>
     * Field appId: 第三方用户唯一凭证
     * </p>
     */
    private String appId;

    /**
     * <p>
     * Field secret: 第三方用户唯一凭证密钥
     * </p>
     */
    private String secret;

    /**
     * <p>
     * Field grant_type: 生成类型 ,获取access_token填写client_credential
     * </p>
     */
    private String grant_type;

    /**
     * <p>
     * Description: 创建微信公众号实例
     * </p>
     * 
     * @param appId 第三方用户唯一凭证
     * @param secret 第三方用户唯一凭证密钥
     * @param grant_type 生成类型 ,获取access_token填写client_credential
     */
    public OAuth(String appId, String secret, String grant_type) {
        this.setAppId(appId);
        this.setSecret(secret);
        this.setGrant_type(grant_type);
    }

    /**
     * <p>
     * Description: 获取第三方用户唯一凭证
     * </p>
     * 
     * @return 第三方用户唯一凭证
     */
    public String getAppId() {
        return this.appId;
    }

    /**
     * <p>
     * Description: 设置第三方用户唯一凭证
     * </p>
     * 
     * @param appId 第三方用户唯一凭证
     */
    private void setAppId(String appId) {
        this.appId = null != appId ? appId : "";
    }

    /**
     * <p>
     * Description: 获取第三方用户唯一凭证密钥
     * </p>
     * 
     * @return 第三方用户唯一凭证密钥
     */
    public String getSecret() {
        return this.secret;
    }

    /**
     * <p>
     * Description: 设置第三方用户唯一凭证密钥
     * </p>
     * 
     * @param secret 第三方用户唯一凭证密钥
     */
    private void setSecret(String secret) {
        this.secret = null != secret ? secret : "";
    }

    /**
     * <p>
     * Description: 获得生成类型
     * </p>
     * 
     * @return 生成类型
     */
    public String getGrant_type() {
        return this.grant_type;
    }

    /**
     * <p>
     * Description: 设置生成类型
     * </p>
     * 
     * @param grant_type 生成类型
     */
    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof OAuth)) {
            return false;
        }
        OAuth oAuth = (OAuth) o;
        if (oAuth.getAppId() == null || !this.appId.equals(oAuth.getAppId())) {
            return false;
        }
        if (oAuth.getSecret() == null || !this.getSecret().equals(oAuth.getSecret())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = this.appId != null ? this.appId.hashCode() : 0;
        result = 31 * result + (this.getSecret() != null ? this.getSecret().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{appid='" + this.getAppId() + "',appSecret='" + this.getSecret() + "'}";
    }

}
