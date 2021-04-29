package com.idofast.admin.infrastructure;

import com.google.common.collect.Maps;
import com.idofast.admin.util.VcodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringEmailAdapterTest
{

    @Autowired
    private SpringEmailAdapter emailAdapter;

    @Test
    public void sendThymeleafMail() throws UnsupportedEncodingException, MessagingException
    {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("vcode", VcodeUtil.generateVCode());

        emailAdapter.sendThymeleafMail("测试主题", "liujingjie@siyizn.com", hashMap, "vcode.html");
    }
}