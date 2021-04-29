package com.idofast.admin.controller.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/2/7 8:45 下午
 */

@Data
@ApiModel("订单查询对象")
public class OrderSearchRequest
{
    @ApiModelProperty("查询指定id的订单")
    private Long id;

    @ApiModelProperty("查询指定用户的id")
    private Long userId;

    @ApiModelProperty("查询指定邮箱的用户，跟userId只需要填一个")
    private String userEmail;

    @ApiModelProperty("指定订单时间大于该时间")
    private Long startTime;

    @ApiModelProperty("指定订单时间小于该时间")
    private Long endTime;

    @ApiModelProperty(value = "指定订单的状态", dataType = "List")
    private List<Integer> orderStatusList;

    @ApiModelProperty("当前页")
    @NotNull(message = "当前页不能为空")
    private Integer currentPage;

    @ApiModelProperty("每页大小")
    @NotNull(message = "每页大小不能为空")
    private Integer pageSize;


}
