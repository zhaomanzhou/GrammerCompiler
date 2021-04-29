package com.idofast.common.enums;

import com.idofast.common.enums.base.BaseAttributeConvert;
import com.idofast.common.enums.base.IBaseEnum;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/2/4 3:46 下午
 */
public enum OrderStatusEnum implements IBaseEnum<OrderStatusEnum>
{

    INITIAL_CREATED(0, "刚创建，还未调用付款"),
    WAIT_TO_SCAN(5,"创建了相应的支付宝链接，用户还未扫码"),
    WAIT_TO_PAY(10, "用户已扫码，但未付款"),
    CANCEL_USER(21, "用户已取消"),
    CANCEL_TIMEOUT(22, "超时自动关闭订单"),
    SUCCESS(30, "已支付");


    private final Integer code;
    private final String msg;

    OrderStatusEnum(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode()
    {
        return code;
    }

    @Override
    public String getMsg()
    {
        return msg;
    }

    public static OrderStatusEnum ofCode(Integer code){
        return (OrderStatusEnum) IBaseEnum.ofCodeII(code, OrderStatusEnum.class);
    }

    public static class Converter extends BaseAttributeConvert<OrderStatusEnum>
    {
        public Converter(){
            super(OrderStatusEnum.class);
        }
    }
}
