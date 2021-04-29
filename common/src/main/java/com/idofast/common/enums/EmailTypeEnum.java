package com.idofast.common.enums;

import com.idofast.common.enums.base.BaseAttributeConvert;
import com.idofast.common.enums.base.IBaseEnum;

public enum EmailTypeEnum implements IBaseEnum<EmailTypeEnum>
{
    REGISTER_VCODE(1, "注册验证码"),
    ACCOUNT_EXPIRE(2, "帐号过期提醒");


    EmailTypeEnum(int code, String msg)
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


    public static EmailTypeEnum ofCode(Integer code){
        return (EmailTypeEnum) IBaseEnum.ofCodeII(code, EmailTypeEnum.class);
    }

    public static class Converter extends BaseAttributeConvert<EmailTypeEnum>
    {
        public Converter(){
            super(EmailTypeEnum.class);
        }
    }


}
