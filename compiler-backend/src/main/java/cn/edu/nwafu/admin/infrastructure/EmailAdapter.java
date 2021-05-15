package cn.edu.nwafu.admin.infrastructure;


import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface EmailAdapter
{

    void sendThymeleafMail(String subject, String to, Map<String, Object> data, String templatePath) throws MessagingException, UnsupportedEncodingException;

    void sendThymeleafMail(String subject, String from, String to, Map<String, Object> data, String templatePath) throws MessagingException, UnsupportedEncodingException;

    void sendThymeleafMail(String subject, String from, String to, String[] cc, String[] bcc, Map<String, Object> data, String templatePath) throws MessagingException, UnsupportedEncodingException;


}
