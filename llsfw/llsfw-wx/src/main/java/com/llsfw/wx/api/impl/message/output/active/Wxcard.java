/**
 * Wxcard.java
 * Created at 2016-04-26
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.active;

import java.io.Serializable;

/**
 * <p>
 * ClassName: Music
 * </p>
 * <p>
 * Description: Wxcard
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月26日
 * </p>
 */
public class Wxcard implements Serializable {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 4778158556969992887L;

    /**
     * <p>
     * Field card_id: 卡券ID
     * </p>
     */
    private String card_id;

    /**
     * <p>
     * Field card_ext: 卡券信息
     * </p>
     */
    private CardExt card_ext;

    public String getCard_id() {
        return this.card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public CardExt getCard_ext() {
        return this.card_ext;
    }

    public void setCard_ext(CardExt card_ext) {
        this.card_ext = card_ext;
    }

}
