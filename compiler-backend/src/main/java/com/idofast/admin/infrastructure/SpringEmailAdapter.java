package com.idofast.admin.infrastructure;

import com.idofast.admin.constant.EmailConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

@Component
public class SpringEmailAdapter implements EmailAdapter
{

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private EmailConstant emailConstant;


    public void sendThymeleafMail(String subject, String to, Map<String, Object> data, String templatePath) throws MessagingException, UnsupportedEncodingException
    {
        this.sendThymeleafMail(subject, emailConstant.getUsername(), to, null, null, data, templatePath);
    }


    /**
     * 发送邮件使用Thymeleaf模板
     *
     * @param subject      主题
     * @param from         发件人
     * @param to           收件人
     * @param data         邮件模板需要替换的数据
     * @param templatePath 模板路径 路径在src/main/resources/templates/下
     * @throws MessagingException
     */
    public void sendThymeleafMail(String subject, String from, String to, Map<String, Object> data, String templatePath) throws MessagingException, UnsupportedEncodingException
    {
        this.sendThymeleafMail(subject, from, to, null, null, data, templatePath);
    }

    /**
     * 发送邮件使用Thymeleaf模板
     *
     * @param subject      主题
     * @param from         发件人
     * @param to           收件人
     * @param cc           抄送人，可以有多个抄送人
     * @param bcc          隐秘抄送人，可以有多个
     * @param data         邮件模板需要替换的数据
     * @param templatePath 模板路径 路径在src/main/resources/templates/下
     * @throws MessagingException
     */
    public void sendThymeleafMail(String subject, String from, String to, String[] cc, String[] bcc, Map<String, Object> data, String templatePath) throws MessagingException, UnsupportedEncodingException
    {
        //1. 构建邮件对象，注意，这里要通过 javaMailSender 来获取一个复杂邮件对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //2. MimeMessageHelper 是一个邮件配置的辅助工具类，true 表示构建一个 multipart message 类型的邮件
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //3. 针对工具类，配置邮件发送的基本信息
        helper.setSubject(subject);
        helper.setFrom(from, emailConstant.getSenderName());
        helper.setTo(to);
        if (cc != null) {
            helper.setCc(cc);
        }
        if (bcc != null) {
            helper.setBcc(bcc);
        }
        helper.setSentDate(new Date());

        Context context = new Context();
        if (data != null) {
            data.forEach(context::setVariable);
        }
        String process = templateEngine.process(templatePath, context);
        helper.setText(process, true);
        javaMailSender.send(mimeMessage);
    }
}
