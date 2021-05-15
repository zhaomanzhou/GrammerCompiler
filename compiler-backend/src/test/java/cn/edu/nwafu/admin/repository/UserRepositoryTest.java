package cn.edu.nwafu.admin.repository;

import cn.edu.nwafu.admin.domain.User;
import com.idofast.common.enums.RoleEnum;
import com.idofast.common.enums.UserStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRepositoryTest
{
    @Autowired
    private UserRepository userRepository;


    @Test
    public void testJpa()
    {
        User user = User.builder().nickname("ff")
                .email("aa")
                .password("111")
                .remark("dd")
                .status(UserStatusEnum.NORMAL)
                .role(RoleEnum.ADMIN)
                .build();
        userRepository.save(user);
    }
}