/**
 * User.java
 * Created at 2016-04-24
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.user;

import com.llsfw.wx.api.impl.base.BaseResult;

/**
 * <p>
 * ClassName: User
 * </p>
 * <p>
 * Description: 微信平台用户管理
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月24日
 * </p>
 */
public class User extends BaseResult {
    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -3921519136432698626L;
    /**
     * <p>
     * Field subscribe: 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     * </p>
     */
    private String subscribe;
    /**
     * <p>
     * Field openid: 用户的标识，对当前公众号唯一
     * </p>
     */
    private String openid;
    /**
     * <p>
     * Field nickname: 用户的昵称
     * </p>
     */
    private String nickname;
    /**
     * <p>
     * Field sex: 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     * </p>
     */
    private Integer sex;
    /**
     * <p>
     * Field city: 用户所在城市
     * </p>
     */
    private String city;
    /**
     * <p>
     * Field country: 用户所在国家
     * </p>
     */
    private String country;
    /**
     * <p>
     * Field province: 用户所在省份
     * </p>
     */
    private String province;
    /**
     * <p>
     * Field language: 用户的语言，简体中文为zh_CN
     * </p>
     */
    private String language;

    /**
     * <p>
     * Field headimgurl:
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。
     * 若用户更换头像，原有头像URL将失效。
     * </p>
     */
    private String headimgurl;
    /**
     * <p>
     * Field subscribe_time: 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     * </p>
     */
    private Long subscribe_time;
    /**
     * <p>
     * Field unionid: 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     * </p>
     */
    private String unionid;
    /**
     * <p>
     * Field remark: 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     * </p>
     */
    private String remark;
    /**
     * <p>
     * Field groupid: 用户所在的分组ID
     * </p>
     */
    private Integer groupid;

    public String getSubscribe() {
        return this.subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadimgurl() {
        return this.headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Long getSubscribe_time() {
        return this.subscribe_time;
    }

    public void setSubscribe_time(Long subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public String getUnionid() {
        return this.unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGroupid() {
        return this.groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

}
