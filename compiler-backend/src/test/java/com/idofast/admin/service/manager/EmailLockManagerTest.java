package com.idofast.admin.service.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EmailLockManagerTest
{

    @Autowired
    private EmailLockManager emailLockManager;

    @Test
    public void canSendVerificationCode() throws InterruptedException
    {
        System.out.println(emailLockManager.canSendVerificationCode("123"));
        emailLockManager.lockUserForVerificationCode("123", "456");
        TimeUnit.SECONDS.sleep(8);
        System.out.println(emailLockManager.canSendVerificationCode("123"));
//        emailLockManager.lockUserForVerificationCode("123", "456");
        TimeUnit.SECONDS.sleep(4);

        System.out.println(emailLockManager.canSendVerificationCode("123"));

    }
}