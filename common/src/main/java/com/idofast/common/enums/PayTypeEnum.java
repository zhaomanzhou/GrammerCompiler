package com.idofast.common.enums;

import com.idofast.common.enums.base.BaseAttributeConvert;
import com.idofast.common.enums.base.IBaseEnum;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/2/4 2:18 下午
 */
public enum  PayTypeEnum implements IBaseEnum<PayTypeEnum>
{

    Alipay(0, "支付宝"),
    Wechat(1,"微信支付");



    PayTypeEnum(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    private final Integer code;
    private final String msg;

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

    public static PayTypeEnum ofCode(Integer code){
        return (PayTypeEnum) IBaseEnum.ofCodeII(code, PayTypeEnum.class);
    }

    public static class Converter extends BaseAttributeConvert<PayTypeEnum>
    {
        public Converter(){
            super(PayTypeEnum.class);
        }
    }
}
