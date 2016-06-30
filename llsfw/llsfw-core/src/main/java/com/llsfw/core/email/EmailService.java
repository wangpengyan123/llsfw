/**
 * EmailService.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.email;

import java.io.IOException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * <p>
 * ClassName: EmailService
 * </p>
 * <p>
 * Description: 邮件服务
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class EmailService {
    /**
     * <p>
     * Field sender: 发送对象
     * </p>
     */
    private JavaMailSenderImpl sender;
    /**
     * <p>
     * Field defaultToAddress: 默认地址
     * </p>
     */
    private String defaultToAddress = "";

    /**
     * <p>
     * Description: 发送文本文件
     * </p>
     * 
     * @param info 发送信息
     */
    public void sendTextMail(MailSenderInfo info) {
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setFrom(this.sender.getUsername());
        msg.setTo(StringUtils.isEmpty(info.getToAddress()) ? this.defaultToAddress : info.getToAddress());
        msg.setSubject(info.getSubject());
        msg.setText(info.getContent());
        msg.setSentDate(new Date());

        this.sender.send(msg);
    }

    /**
     * <p>
     * Description: 发送HTML文件
     * </p>
     * 
     * @param info 发送信息
     * @throws MessagingException 异常
     */
    public void sendHtmlMail(MailSenderInfo info) throws MessagingException {
        MimeMessage msg = this.sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8"); // false表示非marltipart,UTF-8为字符编码
        helper.setFrom(this.sender.getUsername());
        helper.setTo(StringUtils.isEmpty(info.getToAddress()) ? this.defaultToAddress : info.getToAddress());
        helper.setSubject(info.getSubject());
        helper.setText(info.getContent(), true); // 设置为true表示发送的是HTML
        helper.setSentDate(new Date());

        this.sender.send(msg);
    }

    // 
    /**
     * <p>
     * Description: 发送Marltipart文件,包含附件(未测试)
     * </p>
     * 
     * @param info 发送信息
     * @throws MessagingException 异常
     * @throws IOException 异常
     */
    public void sendMarltipartMail(MailSenderInfo info) throws MessagingException, IOException {
        MimeMessage msg = this.sender.createMimeMessage();
        // true表示非arltipart,UTF-8为字符编码
        MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
        helper.setSubject(info.getSubject());
        helper.setFrom(this.sender.getUsername());
        helper.setTo(info.getToAddress());
        String[] files = info.getAttachFileNames(); // 附件
        if (files != null && files.length > 0) {
            for (String fileName : files) {
                // 获取资源文件
                ClassPathResource file = new ClassPathResource(fileName);
                helper.addAttachment(file.getFilename(), file.getFile()); // 设置附件的名称及其文件流
            }
        }
        this.sender.send(msg);
    }

    public void setDefaultToAddress(String defaultToAddress) {
        this.defaultToAddress = defaultToAddress;
    }

    public void setSender(JavaMailSenderImpl sender) {
        this.sender = sender;
    }
}
