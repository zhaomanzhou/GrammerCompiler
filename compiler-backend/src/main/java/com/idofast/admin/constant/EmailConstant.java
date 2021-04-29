package com.idofast.admin.constant;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@Slf4j
@ConfigurationProperties(prefix = "email")
public class EmailConstant
{
    private String host;
    private String username;
    private String password;
    private String senderName;
    private Integer smtpPort;
    private Boolean useSsl;
    //验证码消息模板
    private String vCodeTemplate;
    private  String overdueDate;
    private  String exceedConnections;

}
