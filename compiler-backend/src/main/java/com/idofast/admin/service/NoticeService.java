package com.idofast.admin.service;
import com.idofast.common.enums.NoticeStatusEnum;
import java.time.LocalDateTime;

import com.idofast.admin.domain.Notice;
import com.idofast.admin.repository.NoticeRepository;
import com.idofast.admin.service.manager.NoticeManager;
import com.idofast.common.enums.NoticeTypeEnum;
import com.idofast.common.enums.NoticeVisibilityEnum;
import com.idofast.common.response.error.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeService
{
    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private NoticeManager noticeManager;


    public void addNotice(Notice notice)
    {
        noticeRepository.save(notice);
    }

    /**
     * 文章内容修改
     */
    public void modifyNoticeContent(Notice notice) throws BusinessException
    {
        Optional<Notice> originNoticeOptional = noticeRepository.findById(notice.getId());
        if(!originNoticeOptional.isPresent())
        {
            throw new BusinessException("原文章不存在");
        }
        Notice originNotice = originNoticeOptional.get();
        originNotice.setTitle(notice.getTitle());
        originNotice.setContentMarkdown(notice.getContentMarkdown());
        originNotice.setContentHtml(notice.getContentHtml());
        originNotice.setVisibility(notice.getVisibility());
        originNotice.setNoticeType(notice.getNoticeType());
        noticeRepository.save(originNotice);
    }

    /**
     * 文章置顶和orderValue修改
     */
    public void modifyNoticeStickAndOrderValue(Long id, Boolean stick, Long orderValue) throws BusinessException
    {
        Optional<Notice> originNoticeOptional = noticeRepository.findById(id);
        if(!originNoticeOptional.isPresent())
        {
            throw new BusinessException("原文章不存在");
        }
        Notice originNotice = originNoticeOptional.get();
        Optional.ofNullable(stick).ifPresent(originNotice::setStick);
        Optional.ofNullable(orderValue).ifPresent(originNotice::setOrderValue);
        noticeRepository.save(originNotice);
    }

    /**
     *管理员获取公告
     */
    public List<Notice> getAllNoticeForAdmin(NoticeTypeEnum noticeType)
    {
        return noticeManager.getNoticeList(noticeType, null, null);
    }

    /**
     * 普通用户获取公告列表
     */
    public List<Notice> getAllNoticeForSimpleUser()
    {
        return noticeManager.getNoticeList(NoticeTypeEnum.NOTIFICATION, NoticeVisibilityEnum.USER, NoticeStatusEnum.PUBLISHED);
    }



    /**
     * 普通用户获取教程列表
     */
    public List<Notice> getAllInstructionForSimpleUser()
    {
        return noticeManager.getNoticeList(NoticeTypeEnum.INSTRUCTION, NoticeVisibilityEnum.USER, NoticeStatusEnum.PUBLISHED);
    }


    /**
     * 普通用户获取科普列表
     */
    public List<Notice> getAllKnowledgeForSimpleUser()
    {
        return noticeManager.getNoticeList(NoticeTypeEnum.KNOWLEDGE, NoticeVisibilityEnum.USER, NoticeStatusEnum.PUBLISHED);
    }



    public Notice getNoticeById(Long id) throws BusinessException
    {

        Optional<Notice> byId = noticeRepository.findById(id);
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
        noticeRepository.updateStatusById(id, noticeStatusEnum);
    }


    public void deleteNotice(Long id)
    {
        noticeRepository.deleteById(id);
    }



}
