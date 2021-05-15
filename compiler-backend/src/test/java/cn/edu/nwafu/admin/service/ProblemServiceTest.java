package cn.edu.nwafu.admin.service;

import cn.edu.nwafu.admin.domain.Problem;
import cn.edu.nwafu.admin.util.OrderByTimeUtil;
import com.idofast.common.enums.NoticeStatusEnum;
import com.idofast.common.enums.NoticeTypeEnum;
import com.idofast.common.enums.NoticeVisibilityEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProblemServiceTest
{

    @Autowired
    private ProblemService problemService;

    @Test
    public void addNotice()
    {
        Problem problem = Problem.builder()
                .contentHtml("<h1>第一步，下载v2ray</h1>\n" +
                        "<p>点击下方链接<a href=\"https://idofast.com/docs/assert/file/qv2ray-win64.exe\">v2ray下载链接</a></p>\n" +
                        "<ul>\n" +
                        "<li>彻底反腐地方</li>\n" +
                        "<li>方式方法</li>\n" +
                        "</ul>")
                .contentMarkdown("# 第一步，下载v2ray\n" +
                        "点击下方链接[v2ray下载链接](https://idofast.com/docs/assert/file/qv2ray-win64.exe)\n" +
                        "- 彻底反腐地方\n" +
                        "- 方式方法")
                .stick(false)
                .title("win7教程1")
                .status(NoticeStatusEnum.DRAFT)
                .orderValue(OrderByTimeUtil.getOrderByCurTime())
                .visibility(NoticeVisibilityEnum.ALL)
                .build();
        problem.setId(10L);
        problemService.addNotice(problem);
    }

    public void testModify(){

    }
}