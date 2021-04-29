package com.idofast.admin.util;

import com.idofast.admin.constant.EmailConstant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeUtility;

@Component
public class EmailUtils
{
    @Autowired
    EmailConstant emailConstant;
    @Async
    public void sendEmail(MailContent mailContent) {
        try {

            HtmlEmail email = new HtmlEmail();

            email.setSSLOnConnect(emailConstant.getUseSsl());
            email.setStartTLSEnabled(emailConstant.getUseSsl());
            email.setSmtpPort(emailConstant.getSmtpPort());
            email.setDebug(true);

            email.setCharset("UTF-8");
            //设置主机名,QQ邮箱是"smtp.qq.com",网易邮箱是"smtp.163.com"
            email.setHostName(emailConstant.getHost());
            //设置来源,就是发送方的邮箱地址
            email.setFrom(emailConstant.getUsername(), emailConstant.getSenderName(), "UTF-8");
            // 用户名和密码为邮箱的账号和授权码（不需要进行base64编码）
            email.setAuthentication(emailConstant.getUsername(), emailConstant.getPassword());
            //接收人邮箱地址
            email.addTo(mailContent.getToEmail());
            //设置主题,可以不设置
            email.setSubject(mailContent.getSubject());
            email.setTextMsg(mailContent.getMsg());
            email.send();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }

    @Getter
    @Setter
    @Builder
    public static class MailContent {
        private String[] toEmail;
        private String msg;
        private String subject;

    }

}
