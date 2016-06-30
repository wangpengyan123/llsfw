/**
 * OAuth2.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

import com.llsfw.wx.api.impl.base.BaseResult;
import com.llsfw.wx.api.impl.base.WxBase;
import com.llsfw.wx.common.OAuth;
import com.llsfw.wx.common.OAuth2Token;
import com.llsfw.wx.common.OAuth2User;
import com.llsfw.wx.exception.WxException;

/**
 * <p>
 * ClassName: OAuth2
 * </p>
 * <p>
 * Description: 网页授权获取用户基本信息
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
public class OAuth2 extends WxBase {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 6385924009254750138L;

    /**
     * <p>
     * Field oauth: 公众号对象
     * </p>
     */
    protected OAuth oauth = null;

    /**
     * <p>
     * Field oauth2Token: 网页授权对象
     * </p>
     */
    protected OAuth2Token oauth2Token;

    /**
     * <p>
     * Field _code: 网页授权code code说明 ： code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
     * </p>
     */
    private String _code;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    public OAuth2() {
    }

    /**
     * <p>
     * Description: 初始化方法
     * </p>
     * 
     * @param appId 公众号的唯一标识
     * @param secret 公众号的appsecret
     * @param accessToken 凭证
     * @param expiresIn 凭证过期时间
     * @param scope 用户授权的作用域，使用逗号（,）分隔
     * @param refresh_token 刷新凭证
     * @param openid 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     * @param code 填写第一步获取的code参数
     * @throws WxException 异常
     */
    public void init(String appId, String secret, String accessToken, int expiresIn, String scope, String refresh_token,
            String openid, String code) throws WxException {
        //校验code
        if (StringUtils.isEmpty(code)) {
            throw new WxException("invalid null, code is null.");
        }
        //初始化code
        this._code = code;
        //校验开发者信息
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(secret)) {
            throw new WxException("invalid null, appid or appSecret or grantType is null.");
        }
        //初始化开发者信息
        this.oauth = new OAuth(appId, secret, "client_credential");
        //初始化凭证信息
        if (!StringUtils.isEmpty(accessToken)) {
            this.oauth2Token = new OAuth2Token(accessToken, expiresIn);
            this.oauth2Token.setScope(scope);
            this.oauth2Token.setOpenid(openid);
            this.oauth2Token.setRefresh_token(refresh_token);
        }

        //验证用户登录
        this.checkLogin();
    }

    /**
     * <p>
     * Description: 检验授权凭证（access_token）是否有效
     * </p>
     * 
     * @param openid 用户的唯一标识
     * @return 是否有效
     * @throws WxException 异常
     */
    public boolean checkToken(String openid) throws WxException {
        //检查
        this.checkLogin();
        //准备参数
        String param = "?access_token=" + this.oauth2Token.getAccess_token() + "&openid=" + openid;
        //请求
        String result = this.get(Wx.getWxApiUrl("16") + param);
        //处理
        BaseResult r = Wx.jsonToObject(result, BaseResult.class);
        try {
            this.checkResult(r);
            return true;
        } catch (WxException e) {
            return false;
        }
    }

    /**
     * <p>
     * Description: 拉取用户信息(需scope为 snsapi_userinfo)
     * </p>
     * 
     * @param openid 用户的唯一标识
     * @param lang 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
     * @return 用户信息
     * @throws WxException 异常
     */
    public OAuth2User getUserInfo(String openid, String lang) throws WxException {
        //检查
        this.checkLogin();
        //准备参数
        String param = "?access_token=" + this.oauth2Token.getAccess_token() + "&openid=" + this.oauth2Token.getOpenid()
                + "&lang=" + lang;
        //请求
        String result = this.get(Wx.getWxApiUrl("15") + param);
        //处理
        OAuth2User r = Wx.jsonToObject(result, OAuth2User.class);
        this.checkResult(r);
        return r;
    }

    /**
     * <p>
     * Description: 刷新access_token
     * </p>
     * 
     * @throws WxException 异常
     */
    public void refreshToken() throws WxException {
        //检查
        this.checkLogin();
        //准备参数
        String param = "?appid=" + this.oauth.getAppId() + "&refresh_token=" + this.oauth2Token.getRefresh_token()
                + "&grant_type=refresh_token";
        //请求
        String result = this.get(Wx.getWxApiUrl("14") + param);
        //处理
        OAuth2Token r = Wx.jsonToObject(result, OAuth2Token.class);
        this.checkResult(r);
        this.oauth2Token = r;
    }

    /**
     * <p>
     * Description: 验证token是否过期
     * </p>
     * 
     * @throws WxException 异常
     */
    private void checkLogin() throws WxException {
        //判断是否过期
        if (this.oauth2Token == null || this.oauth2Token.isExprexpired()) {
            //如果用户名和密码正确，则自动登录，否则返回异常
            if (this.oauth == null) {
                throw new WxException("oauth is null and oauth2Token is exprexpired, please log in again!");
            }
            //自动重新获取授权
            this.login("authorization_code");
        }
    }

    /**
     * <p>
     * Description: 向微信平台发送获取access_token请求
     * </p>
     * 
     * @param grantType 获取access_token填写authorization_code
     * @throws WxException 异常
     */
    private void login(String grantType) throws WxException {
        //准备参数
        String param = "?grant_type=" + grantType + "&appid=" + this.oauth.getAppId() + "&secret="
                + this.oauth.getSecret() + "&code=" + this._code;
        //请求
        String result = this.get(WxBase.getWxApiUrl("13") + param);
        //处理
        OAuth2Token r = Wx.jsonToObject(result, OAuth2Token.class);
        this.checkResult(r);
        this.oauth2Token = r;
    }

    /**
     * <p>
     * Description: 获取 "用户同意授权，获取code"的url
     * </p>
     * 
     * @param appId 公众号的唯一标识
     * @param returnUrl 授权后重定向的回调链接地址，请使用urlencode对链接进行处理
     * @return "用户同意授权，获取code" url
     * @throws WxException 异常
     */
    public String getOauth2CodeBaseUrl(String appId, String returnUrl) throws WxException {
        return getOauth2CodeUrl(appId, returnUrl, "snsapi_base");
    }

    /**
     * <p>
     * Description: 获取 "拉取用户信息(需scope为 snsapi_userinfo)"的url
     * </p>
     * 
     * @param appId 公众号的唯一标识
     * @param returnUrl 授权后重定向的回调链接地址，请使用urlencode对链接进行处理
     * @return "拉取用户信息(需scope为 snsapi_userinfo)"的url
     * @throws WxException 异常
     */
    public String getOauth2CodeUserInfoUrl(String appId, String returnUrl) throws WxException {
        return getOauth2CodeUrl(appId, returnUrl, "snsapi_userinfo");
    }

    /**
     * <p>
     * Description: 默认请求的url
     * </p>
     * 
     * @param appId 公众号的唯一标识
     * @param returnUrl 授权后重定向的回调链接地址，请使用urlencode对链接进行处理
     * @param scope 应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo
     *            （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     * @return 默认请求的url
     * @throws WxException 异常
     */
    private String getOauth2CodeUrl(String appId, String returnUrl, String scope) throws WxException {
        return getOauth2CodeUrl(appId, returnUrl, scope, "DEFAULT");
    }

    /**
     * <p>
     * Description: 拼装统一请求的url
     * </p>
     * 
     * @param appId 公众号的唯一标识
     * @param returnUrl 授权后重定向的回调链接地址，请使用urlencode对链接进行处理
     * @param scope 应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo
     *            （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @return "用户同意授权，获取code"的请求url
     * @throws WxException 异常
     */
    public String getOauth2CodeUrl(String appId, String returnUrl, String scope, String state) throws WxException {
        try {
            //注意 : "response_type 返回类型，请填写code" 这里是写死的
            return getWxApiUrl("12") + "?appid=" + appId + "&redirect_uri=" + URLEncoder.encode(returnUrl, "UTF-8")
                    + "&response_type=code&scope=" + scope + "&state=" + state + "#wechat_redirect";
        } catch (UnsupportedEncodingException e) {
            throw new WxException(e.getMessage(), e);
        }

    }

}
