package com.idofast.admin.repository;

import com.idofast.admin.domain.UserProxyInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2020/12/31 12:28 上午
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserProxyInfoRepositoryTest
{
    @Autowired
    private UserProxyInfoRepository dataInfoRepository;

    @Test
    public void testSelect()
    {
        Optional<UserProxyInfo> byId = dataInfoRepository.findById(1L);
        System.out.println(byId.get().getExpireDate());
    }
}