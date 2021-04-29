package com.idofast.admin.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EmailServiceTest
{
    @Autowired
    private EmailService emailService;

    @Test
    public void testSend()
    {
        //emailService.sendEmail("271832284@qq.com", "ljj中午", "ljj是傻吊,略略略" ,null);
    }
}