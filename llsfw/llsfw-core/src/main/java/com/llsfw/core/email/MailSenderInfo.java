/**
 * MailSenderInfo.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.email;

import java.util.Arrays;

/**
 * <p>
 * ClassName: MailSenderInfo
 * </p>
 * <p>
 * Description: 邮件发送信息
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class MailSenderInfo {
    /**
     * <p>
     * Field toAddress: 邮件接收者的地址
     * </p>
     */
    private String toAddress;
    /**
     * <p>
     * Field subject: 邮件主题
     * </p>
     */
    private String subject;
    /**
     * <p>
     * Field content: 邮件的文本内容
     * </p>
     */
    private String content;
    /**
     * <p>
     * Field attachFileNames: 邮件附件的文件名
     * </p>
     */
    private String[] attachFileNames;

    public String getToAddress() {
        return this.toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getAttachFileNames() {
        return Arrays.copyOf(this.attachFileNames, this.attachFileNames.length);
    }

    public void setAttachFileNames(String[] attachFileNames) {
        this.attachFileNames = Arrays.copyOf(attachFileNames, attachFileNames.length);
    }

}
