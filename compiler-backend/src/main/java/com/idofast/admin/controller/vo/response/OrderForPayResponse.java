package com.idofast.admin.controller.vo.response;

import com.idofast.common.enums.PayTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/2/4 5:08 下午
 */

@Data
@ApiModel("订单付款的Vo对象")
public class OrderForPayResponse
{
    @ApiModelProperty("系统订单Id")
    private Long orderId;

    @ApiModelProperty("支付宝交易Id")
    private String tradeNo;

    @ApiModelProperty("付款链接")
    private String payLink;

    @ApiModelProperty("订单支付方式")
    private PayTypeEnum payType;

    @ApiModelProperty("订单金额，单位分")
    private Long totalMoney;
}
