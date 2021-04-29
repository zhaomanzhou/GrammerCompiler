package com.idofast.common.enums;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/2/6 3:22 下午
 */
public enum  AlipayTradeStatus
{
    WAIT_BUYER_PAY("交易创建，等待买家付款"),
    TRADE_CLOSED("未付款交易超时关闭，或支付完成后全额退款"),
    TRADE_SUCCESS("交易支付成功"),
    TRADE_FINISHED("交易结束，不可退款");

    private String msg;

    AlipayTradeStatus(String msg)
    {
        this.msg = msg;
    }
}
