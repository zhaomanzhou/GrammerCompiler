package com.idofast.admin.service.manager;

import com.idofast.admin.domain.Notice;
import com.idofast.admin.repository.NoticeRepository;
import com.idofast.common.enums.NoticeStatusEnum;
import com.idofast.common.enums.NoticeTypeEnum;
import com.idofast.common.enums.NoticeVisibilityEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
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
public class NoticeManager
{
    @Autowired
    private NoticeRepository noticeRepository;


    /**
     * 获取notice列表，通用方法
     * 返回的是可见的notice列表,即visibility大于等于传入参数的值
     * @return
     */
    public List<Notice> getNoticeList(NoticeTypeEnum noticeTypeEnum, NoticeVisibilityEnum noticeVisibilityEnum, NoticeStatusEnum noticeStatusEnum)
    {
        List<Sort.Order> sortList = new ArrayList<>();
        sortList.add(new Sort.Order(Sort.Direction.DESC, "stick"));
        sortList.add(new Sort.Order(Sort.Direction.DESC, "orderValue"));
        Sort sort = Sort.by(sortList);

        Notice notice = new Notice();
        Optional.ofNullable(noticeTypeEnum).ifPresent(notice::setNoticeType);
        Optional.ofNullable(noticeStatusEnum).ifPresent(notice::setStatus);
        Example<Notice> example = Example.of(notice);

        List<Notice> all = noticeRepository.findAll(noticeRepository.visibilityLessSpecification(noticeVisibilityEnum)
                .and(noticeRepository.exampleSpecification(example))
                , sort);


        return all;
    }


}
