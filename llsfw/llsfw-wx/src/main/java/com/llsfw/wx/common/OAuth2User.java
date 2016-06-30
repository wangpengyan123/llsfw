/**
 * OAuth2User.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.common;

import java.util.List;

import com.llsfw.wx.api.impl.base.BaseResult;

/**
 * <p>
 * ClassName: OAuth2User
 * </p>
 * <p>
 * Description: 用户信息
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月25日
 * </p>
 */
public class OAuth2User extends BaseResult {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 3262655393469847731L;

    /**
     * <p>
     * Field openid: 用户的唯一标识
     * </p>
     */
    private String openid;

    /**
     * <p>
     * Field nickname: 用户昵称
     * </p>
     */
    private String nickname;

    /**
     * <p>
     * Field sex: 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     * </p>
     */
    private int sex;

    /**
     * <p>
     * Field province: 用户个人资料填写的省份
     * </p>
     */
    private String province;

    /**
     * <p>
     * Field city: 普通用户个人资料填写的城市
     * </p>
     */
    private String city;

    /**
     * <p>
     * Field country: 国家，如中国为CN
     * </p>
     */
    private String country;

    /**
     * <p>
     * Field headimgurl: 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     * </p>
     */
    private String headimgurl;

    /**
     * <p>
     * Field privilege: 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     * </p>
     */
    private List<String> privilege;

    /**
     * <p>
     * Field unionid: 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
     * </p>
     */
    private String unionid;

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

    public int getSex() {
        return this.sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public String getHeadimgurl() {
        return this.headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public List<String> getPrivilege() {
        return this.privilege;
    }

    public void setPrivilege(List<String> privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return this.unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

}
