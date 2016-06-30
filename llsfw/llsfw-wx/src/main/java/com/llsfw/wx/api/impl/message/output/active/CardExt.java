/**
 * CardExt.java
 * Created at 2016-04-26
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.active;

import java.io.Serializable;

/**
 * <p>
 * ClassName: CardExt
 * </p>
 * <p>
 * Description: CardExt
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月26日
 * </p>
 */
public class CardExt implements Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -1120973286202484365L;

    /**
     * <p>
     * Field code: 指定的卡券code码，只能被领一次。use_custom_code字段为true的卡券必须填写，非自定义code不必填写。
     * </p>
     */
    private String code;

    /**
     * <p>
     * Field openid: 指定领取者的openid，只有该用户能领取。bind_openid字段为true的卡券必须填写，bind_openid字段为false不必填写。
     * </p>
     */
    private String openid;

    /**
     * <p>
     * Field timestamp: 时间戳，商户生成从1970年1月1日00:00:00至今的秒数,即当前的时间,且最终需要转换为字符串形式;由商户生成后传入,不同添加请求的时间戳须动态生成，若重复将会导致领取失败！。
     * </p>
     */
    private Long timestamp;

    /**
     * <p>
     * Field nonce_str: 随机字符串，由开发者设置传入，加强签名的安全性。随机字符串，不长于32位。推荐使用大小写字母和数字，不同添加请求的nonce须动态生成，若重复将会导致领取失败！。
     * </p>
     */
    private String nonce_str;

    /**
     * <p>
     * Field signature: 签名，商户将接口列表中的参数按照指定方式进行签名,签名方式使用SHA1,具体签名方案参见下文;由商户按照规范签名后传入。
     * </p>
     */
    private String signature;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce_str() {
        return this.nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}
