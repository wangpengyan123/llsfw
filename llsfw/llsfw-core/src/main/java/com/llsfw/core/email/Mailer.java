/**
 * Mailer.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * <p>
 * ClassName: Mailer
 * </p>
 * <p>
 * Description: 邮件
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class Mailer {

    /**
     * <p>
     * Field host: 地址
     * </p>
     */
    private String host;
    /**
     * <p>
     * Field auth: 认证
     * </p>
     */
    private String auth;
    /**
     * <p>
     * Field username: 用户名
     * </p>
     */
    private String username;
    /**
     * <p>
     * Field domainUser: 域
     * </p>
     */
    private String domainUser;
    /**
     * <p>
     * Field password: 密码
     * </p>
     */
    private String password;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param host 地址
     * @param auth 认证
     * @param domainUser 域
     * @param username 用户名
     * @param password 密码
     */
    public Mailer(String host, String auth, String domainUser, String username, String password) {
        super();
        this.host = host;
        this.auth = auth;
        this.domainUser = domainUser;
        this.username = username;
        this.password = password;
    }

    /**
     * <p>
     * Description: 发送
     * </p>
     * 
     * @param to 发送列表
     * @param cc 抄送列表
     * @param bcc 隐藏抄送列表
     * @param subject 主题
     * @param content 内容
     * @return 结果
     * @throws MessagingException 异常
     */
    public boolean send(String[] to, String[] cc, String[] bcc, String subject, String content)
            throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", this.host);
        props.put("mail.smtp.auth", this.auth);
        Session s = Session.getInstance(props);

        MimeMessage message = new MimeMessage(s);

        InternetAddress from = new InternetAddress(this.username);
        message.setFrom(from);
        InternetAddress[] toaddress = new InternetAddress[to.length];
        for (int i = 0; i < to.length; i++) {
            toaddress[i] = new InternetAddress(to[i]);
        }
        message.setRecipients(Message.RecipientType.TO, toaddress);

        if (cc != null) {
            InternetAddress[] ccaddress = new InternetAddress[cc.length];
            for (int i = 0; i < cc.length; i++) {
                ccaddress[i] = new InternetAddress(cc[i]);
            }
            message.setRecipients(Message.RecipientType.CC, ccaddress);
        }

        if (bcc != null) {
            InternetAddress[] bccaddress = new InternetAddress[bcc.length];
            for (int i = 0; i < bcc.length; i++) {
                bccaddress[i] = new InternetAddress(bcc[i]);
            }
            message.setRecipients(Message.RecipientType.BCC, bccaddress);
        }
        message.setSubject(subject);
        message.setSentDate(new Date());

        BodyPart mdp = new MimeBodyPart();
        mdp.setContent(content, "text/html;charset=utf-8");
        Multipart mm = new MimeMultipart();
        mm.addBodyPart(mdp);
        message.setContent(mm);

        message.saveChanges();
        Transport transport = s.getTransport("smtp");
        transport.connect(this.host, (null == this.domainUser) ? this.username : this.domainUser, this.password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        return true;
    }

    //    public static void main(String[] args) {
    //        try {
    //            // SMTP地址
    //            // 邮箱地址和密码
    //            // 域
    //            new Mailer("smtp.163.com", "true", null, "fringeframework@163.com", "wangkang123456")
    //                    .send(new String[] { "Omdsupport@CSVW.COM" }, null, null, "demo_title", 
    //              "<h3>你好,陆骞,测试邮件,无需理会</h3>");
    //            LOG.debug("send sucess!!!");
    //        } catch (Exception e) {
    //            LOG.error("send error", e);
    //        }
    //    }
}
