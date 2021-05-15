package cn.edu.nwafu.admin.service;
import cn.edu.nwafu.admin.service.manager.ProblemManager;
import com.idofast.common.enums.NoticeStatusEnum;

import cn.edu.nwafu.admin.domain.Problem;
import cn.edu.nwafu.admin.repository.ProblemRepository;
import com.idofast.common.enums.NoticeTypeEnum;
import com.idofast.common.enums.NoticeVisibilityEnum;
import com.idofast.common.response.error.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService
{
    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private ProblemManager problemManager;


    public void addNotice(Problem problem)
    {
        problem.setCaseNum(0);
        problemRepository.save(problem);
    }

    /**
     * 文章内容修改
     */
    public void modifyNoticeContent(Problem problem) throws BusinessException
    {
        Optional<Problem> originNoticeOptional = problemRepository.findById(problem.getId());
        if(!originNoticeOptional.isPresent())
        {
            throw new BusinessException("原文章不存在");
        }
        Problem originProblem = originNoticeOptional.get();
        originProblem.setTitle(problem.getTitle());
        originProblem.setContentMarkdown(problem.getContentMarkdown());
        originProblem.setContentHtml(problem.getContentHtml());
        originProblem.setVisibility(problem.getVisibility());
        problemRepository.save(originProblem);
    }

    /**
     * 文章置顶和orderValue修改
     */
    public void modifyNoticeStickAndOrderValue(Long id, Boolean stick, Long orderValue) throws BusinessException
    {
        Optional<Problem> originNoticeOptional = problemRepository.findById(id);
        if(!originNoticeOptional.isPresent())
        {
            throw new BusinessException("原文章不存在");
        }
        Problem originProblem = originNoticeOptional.get();
        Optional.ofNullable(stick).ifPresent(originProblem::setStick);
        Optional.ofNullable(orderValue).ifPresent(originProblem::setOrderValue);
        problemRepository.save(originProblem);
    }

    /**
     *管理员获取公告
     */
    public List<Problem> getAllNoticeForAdmin(NoticeTypeEnum noticeType)
    {
        return problemManager.getNoticeList(noticeType, null, null);
    }

    /**
     * 普通用户获取公告列表
     */
    public List<Problem> getAllNoticeForSimpleUser()
    {
        return problemManager.getNoticeList(NoticeTypeEnum.NOTIFICATION, NoticeVisibilityEnum.USER, NoticeStatusEnum.PUBLISHED);
    }



    /**
     * 普通用户获取教程列表
     */
    public List<Problem> getAllInstructionForSimpleUser()
    {
        return problemManager.getNoticeList(NoticeTypeEnum.INSTRUCTION, NoticeVisibilityEnum.USER, NoticeStatusEnum.PUBLISHED);
    }


    /**
     * 普通用户获取科普列表
     */
    public List<Problem> getAllKnowledgeForSimpleUser()
    {
        return problemManager.getNoticeList(NoticeTypeEnum.KNOWLEDGE, NoticeVisibilityEnum.USER, NoticeStatusEnum.PUBLISHED);
    }



    public Problem getNoticeById(Long id) throws BusinessException
    {

        Optional<Problem> byId = problemRepository.findById(id);
        if(byId.isPresent())
        {
            return byId.get();
        }
        throw new BusinessException("该文章不存在");
    }


    /**
     * 改变notice的状态，下架，或上架
     */
    public void setNoticeStatus(Long id, NoticeStatusEnum noticeStatusEnum)
    {
        problemRepository.updateStatusById(id, noticeStatusEnum);
    }


    public void deleteNotice(Long id)
    {
        problemRepository.deleteById(id);
    }



}
