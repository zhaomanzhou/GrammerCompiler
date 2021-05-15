package cn.edu.nwafu.admin.service.manager;

import cn.edu.nwafu.admin.domain.Problem;
import cn.edu.nwafu.admin.repository.ProblemRepository;
import com.idofast.common.enums.NoticeStatusEnum;
import com.idofast.common.enums.NoticeTypeEnum;
import com.idofast.common.enums.NoticeVisibilityEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2020/12/24 3:36 下午
 */

@Service
public class ProblemManager
{
    @Autowired
    private ProblemRepository problemRepository;


    /**
     * 获取notice列表，通用方法
     * 返回的是可见的notice列表,即visibility大于等于传入参数的值
     * @return
     */
    public List<Problem> getNoticeList(NoticeTypeEnum noticeTypeEnum, NoticeVisibilityEnum noticeVisibilityEnum, NoticeStatusEnum noticeStatusEnum)
    {
        List<Sort.Order> sortList = new ArrayList<>();
        sortList.add(new Sort.Order(Sort.Direction.DESC, "stick"));
        sortList.add(new Sort.Order(Sort.Direction.DESC, "orderValue"));
        Sort sort = Sort.by(sortList);

        Problem problem = new Problem();
        Optional.ofNullable(noticeStatusEnum).ifPresent(problem::setStatus);
        Example<Problem> example = Example.of(problem);

        List<Problem> all = problemRepository.findAll(problemRepository.visibilityLessSpecification(noticeVisibilityEnum)
                .and(problemRepository.exampleSpecification(example))
                , sort);


        return all;
    }


}
