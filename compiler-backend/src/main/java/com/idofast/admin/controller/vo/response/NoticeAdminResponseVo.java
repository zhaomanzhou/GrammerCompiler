package com.idofast.admin.controller.vo.response;

import com.idofast.admin.constant.KVConstant;
import com.idofast.admin.domain.Notice;
import com.idofast.common.enums.NoticeStatusEnum;
import com.idofast.common.enums.NoticeVisibilityEnum;
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
public class NoticeAdminResponseVo
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

    @ApiModelProperty("公告发布人id")
    private Long publisherId;

    @ApiModelProperty("公告状态；0-未发布；1-已发布；2-已下架")
    private Integer status;

    @ApiModelProperty("公告可见性；0-所有人可见；1-登陆用户可见；2-管理员可见")
    private Integer visibility;

    @ApiModelProperty("公告修改时间")
    private String updateTime;

    @ApiModelProperty("公告发布时间")
    public String createTime;

    public static NoticeAdminResponseVo convertFrom(Notice notice)
    {
        NoticeAdminResponseVo noticeVo = new NoticeAdminResponseVo();
        BeanUtils.copyProperties(notice, noticeVo);
        noticeVo.setNoticeType(notice.getNoticeType().getCode());
        noticeVo.setCreateTime(DateTimeFormatter.ofPattern(KVConstant.YYYYMMdd).format(notice.getCreateTime()));
        noticeVo.setUpdateTime(DateTimeFormatter.ofPattern(KVConstant.YYYYMMdd).format(notice.getUpdateTime()));
        noticeVo.setStatus(notice.getStatus().getCode());
        noticeVo.setVisibility(notice.getVisibility().getCode());
        return noticeVo;
    }
}
