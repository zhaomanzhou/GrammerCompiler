package com.idofast.admin.controller.vo.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/2/7 11:25 下午
 */
@Data
@ApiModel("分页对象")
public class PageResponse<T>
{
    @ApiModelProperty("数据对象")
    private List<T> data;

    @ApiModelProperty("总数据量")
    private Long totalElement;

    @ApiModelProperty("总页数")
    private Integer pageNum;

    @ApiModelProperty("当前第几页;从1开始")
    private Integer currentPageNum;
}
