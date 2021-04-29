package com.idofast.admin.controller.vo.response;

import com.idofast.admin.constant.KVConstant;
import com.idofast.admin.domain.Notice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.format.DateTimeFormatter;


/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2020/12/19 1:03 下午
 */


@Data
@ApiModel("文章公告返回的vo对象")
public class NoticeUserResponseVo
{

    @ApiModelProperty("公告id")
    private Long id;

    @ApiModelProperty("公告标题")
    private String title;

    @ApiModelProperty("公告内容markdown格式")
    private String contentMarkdown;

    @ApiModelProperty("公告内容html格式")
    private String contentHtml;

    @ApiModelProperty("文章类型；0 - 教程；1 - 公告；")
    private Integer noticeType;

    @ApiModelProperty("公告是否置顶")
    private Boolean stick;

    @ApiModelProperty("公告序列值，默认为发布时间，排序用")
    private Long orderValue;

    @ApiModelProperty("公告修改时间")
    private String updateTime;

    @ApiModelProperty("公告发布时间")
    public String createTime;

    public static NoticeUserResponseVo convertFrom(Notice notice)
    {
        NoticeUserResponseVo noticeVo = new NoticeUserResponseVo();
        BeanUtils.copyProperties(notice, noticeVo);
        noticeVo.setNoticeType(notice.getNoticeType().getCode());
        noticeVo.setCreateTime(DateTimeFormatter.ofPattern(KVConstant.YYYYMMdd).format(notice.getCreateTime()));
        noticeVo.setUpdateTime(DateTimeFormatter.ofPattern(KVConstant.YYYYMMdd).format(notice.getUpdateTime()));
        return noticeVo;
    }
}
