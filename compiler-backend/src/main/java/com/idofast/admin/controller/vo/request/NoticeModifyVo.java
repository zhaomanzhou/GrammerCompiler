package com.idofast.admin.controller.vo.request;


import com.idofast.admin.domain.Notice;
import com.idofast.admin.util.OrderByTimeUtil;
import com.idofast.common.enums.NoticeStatusEnum;
import com.idofast.common.enums.NoticeTypeEnum;
import com.idofast.common.enums.NoticeVisibilityEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("文章修改的vo对象")
public class NoticeModifyVo
{

    @ApiModelProperty("公告id")
    @NotNull(message = "公告id不能为空")
    private Long id;


    @NotNull(message = "标题不能为空")
    @ApiModelProperty("公告标题")
    private String title;

    @NotNull(message = "公告内容不能为空")
    @ApiModelProperty("公告内容markdown格式")
    private String contentMarkdown;

    @NotNull(message = "公告内容不能为空")
    @ApiModelProperty("公告内容html格式")
    private String contentHtml;


    @ApiModelProperty("公告可见性范围；0 - 所有人可见；1 - 只有注册用户可见；2 - 只有管理员可见")
    @Min(value = 0, message = "只能为0，1，2之间的数值")
    @Max(value = 2,message = "只能为0，1，2之间的数值")
    private Integer visibility;

    @ApiModelProperty("文章类型；0 - 教程；1 - 公告；")
    @Min(value = 0, message = "只能为0或1")
    @Max(value = 1,message = "只能为0或1")
    private Integer noticeType;

    public static Notice convertToNotice(NoticeModifyVo noticeAddVo)
    {
        Notice notice = new Notice();
        BeanUtils.copyProperties(noticeAddVo, notice);
        notice.setVisibility(NoticeVisibilityEnum.ofCode(noticeAddVo.getVisibility()));
        notice.setNoticeType(NoticeTypeEnum.ofCode(noticeAddVo.getNoticeType()));
        return notice;
    }

}
