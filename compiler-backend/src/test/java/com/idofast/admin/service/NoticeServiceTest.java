package com.idofast.admin.service;

import com.idofast.admin.domain.Notice;
import com.idofast.admin.util.OrderByTimeUtil;
import com.idofast.common.enums.NoticeStatusEnum;
import com.idofast.common.enums.NoticeTypeEnum;
import com.idofast.common.enums.NoticeVisibilityEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoticeServiceTest
{

    @Autowired
    private NoticeService noticeService;

    @Test
    public void addNotice()
    {
        Notice notice = Notice.builder()
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
                .noticeType(NoticeTypeEnum.INSTRUCTION)
                .status(NoticeStatusEnum.DRAFT)
                .orderValue(OrderByTimeUtil.getOrderByCurTime())
                .visibility(NoticeVisibilityEnum.ALL)
                .build();
        notice.setId(10L);
        noticeService.addNotice(notice);
    }

    public void testModify(){

    }
}