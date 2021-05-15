package cn.edu.nwafu.admin.service.manager;

import cn.edu.nwafu.admin.repository.ProblemRepository;
import cn.edu.nwafu.admin.domain.Problem;
import com.idofast.common.enums.NoticeStatusEnum;
import com.idofast.common.enums.NoticeVisibilityEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2020/12/29 5:20 下午
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProblemManagerTest
{
    @Autowired
    private ProblemManager problemManager;

    @Autowired
    private ProblemRepository problemRepository;

    @Test
    public void getNoticeList()
    {
        List<Problem> problemList = problemManager.getNoticeList(null, NoticeVisibilityEnum.ALL, null);
        problemList.stream()
                .map(Problem::getId)
                .forEach(System.out::println);
    }

    @Test
    public void modifyStatus(){
        problemRepository.updateStatusById(1L, NoticeStatusEnum.DOWN);
    }
}