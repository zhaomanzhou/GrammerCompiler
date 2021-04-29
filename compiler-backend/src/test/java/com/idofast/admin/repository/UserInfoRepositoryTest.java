package com.idofast.admin.repository;

import com.idofast.admin.domain.dto.UserInfoLite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/2/20 11:01 下午
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserInfoRepositoryTest
{

    @Autowired
    private UserProxyInfoRepository userProxyInfoRepository;

    @Test
    public void queryUserInfo()
    {
        Page<UserInfoLite> userInfoLites = userProxyInfoRepository.queryUserInfo(null);
        userInfoLites.forEach(System.out::println);
    }
}