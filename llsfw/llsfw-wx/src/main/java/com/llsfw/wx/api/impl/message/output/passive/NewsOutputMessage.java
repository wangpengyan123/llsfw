/**
 * MusicOutputMessage.java
 * Created at 2016-04-25
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.output.passive;

import java.util.ArrayList;
import java.util.List;

import com.llsfw.core.common.Constants;
import com.llsfw.wx.api.impl.message.output.AbstractOutputMessage;

/**
 * 这个类实现了<tt>OutputMessage</tt>，用来回复图文消息
 *
 * <p>
 * 提供了获取多条图文消息信息<code>getArticles()</code>等主要方法.
 * </p>
 *
 * @author weixin4j<weixin4j@ansitech.com>
 */
public class NewsOutputMessage extends AbstractOutputMessage {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -517949334993512785L;

    /**
     * <p>
     * Field msgType: 图文消息
     * </p>
     */
    private String msgType = "news";

    /**
     * <p>
     * Field articleCount: 图文消息个数，限制为10条以内
     * </p>
     */
    private Integer articleCount;

    /**
     * <p>
     * Field articles: 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
     * </p>
     */
    private List<Articles> articles;

    public Integer getArticleCount() {
        return this.articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public List<Articles> getArticles() {
        return this.articles;
    }

    /**
     * <p>
     * Description: 设置 多条图文消息信息
     * </p>
     * 
     * @param articles 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则只读取前10个
     */
    public void setArticles(List<Articles> articles) {
        if (articles != null) {
            if (articles.size() > Constants.NUMBER_10) {
                articles = new ArrayList<Articles>(articles.subList(0, Constants.NUMBER_10));
            }
            this.articleCount = articles.size();
        }
        this.articles = articles;
    }

    public String getMsgType() {
        return this.msgType;
    }

    @Override
    public String toXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[").append(this.getToUserName()).append("]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[").append(this.getFromUserName()).append("]]></FromUserName>");
        sb.append("<CreateTime>").append(this.getCreateTime()).append("</CreateTime>");
        sb.append("<MsgType><![CDATA[" + this.msgType + "]]></MsgType>");
        sb.append("<ArticleCount>").append(this.articleCount).append("</ArticleCount>");
        sb.append("<Articles>");
        for (Articles article : this.articles) {
            sb.append("<item>");
            sb.append("<Title><![CDATA[").append(article.getTitle()).append("]]></Title>");
            sb.append("<Description><![CDATA[").append(article.getDescription()).append("]]></Description>");
            sb.append("<PicUrl><![CDATA[").append(article.getPicUrl()).append("]]></PicUrl>");
            sb.append("<Url><![CDATA[").append(article.getUrl()).append("]]></Url>");
            sb.append("</item>");
        }
        sb.append("</Articles>");
        sb.append("</xml>");
        return sb.toString();
    }

}
