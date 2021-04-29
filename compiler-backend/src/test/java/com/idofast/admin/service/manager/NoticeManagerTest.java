package com.idofast.admin.service.manager;

import com.idofast.admin.domain.Notice;
import com.idofast.admin.repository.NoticeRepository;
import com.idofast.common.enums.NoticeStatusEnum;
import com.idofast.common.enums.NoticeVisibilityEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2020/12/29 5:20 下午
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class NoticeManagerTest
{
    @Autowired
    private NoticeManager noticeManager;

    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    public void getNoticeList()
    {
        List<Notice> noticeList = noticeManager.getNoticeList(null, NoticeVisibilityEnum.ALL, null);
        noticeList.stream()
                .map(Notice::getId)
                .forEach(System.out::println);
    }

    @Test
    public void modifyStatus(){
        noticeRepository.updateStatusById(1L, NoticeStatusEnum.DOWN);
    }
}