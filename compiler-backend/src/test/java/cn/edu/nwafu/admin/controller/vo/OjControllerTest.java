package cn.edu.nwafu.admin.controller.vo;

import cn.edu.nwafu.admin.config.context.ThreadLocalCache;
import cn.edu.nwafu.admin.constant.ContextConstant;
import cn.edu.nwafu.admin.controller.OjController;
import cn.edu.nwafu.admin.domain.User;
import com.idofast.common.response.error.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/5/11 12:35 上午
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class OjControllerTest
{

    @Autowired
    private OjController ojController;
    @Test
    void testCode() throws IOException, BusinessException
    {
        User user = new User();
        user.setId(10L);
        ThreadLocalCache.set(ContextConstant.USER, user);
        ojController.judgeCode(2L, "#include <stdio.h>\n" +
                "\n" +
                "\n" +
                "void PrintN ( int N )\n" +
                "{\n" +
                "    int i;\n" +
                "    for( i = 1; i <= N; i++)\n" +
                "    {\n" +
                "        printf(\"%d\\n\", i);\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "\n" +
                "int main ()\n" +
                "{\n" +
                "    int N;\n" +
                "\n" +
                "    scanf(\"%d\", &N);\n" +
                "    PrintN( N );\n" +
                "\n" +
                "\n" +
                "    return 0;\n" +
                "}");
    }
}