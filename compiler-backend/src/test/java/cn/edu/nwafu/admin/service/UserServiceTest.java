package cn.edu.nwafu.admin.service;

import cn.edu.nwafu.admin.controller.vo.request.RegisterUserVo;
import cn.edu.nwafu.admin.service.manager.EmailLockManager;
import com.idofast.common.response.error.BusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/2/20 5:03 下午
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest
{

    @Autowired
    private UserService userService;


    @Autowired
    private EmailLockManager lockManager;

    @Test
    public void registerUser() throws BusinessException
    {
        RegisterUserVo vo = new RegisterUserVo();
        vo.setEmail("12211@11");
        vo.setPassword("1111");
        vo.setVcode("1");
        lockManager.lockUserForVerificationCode(vo.getEmail(), vo.getVcode());
        userService.registerUser(vo);
    }
}