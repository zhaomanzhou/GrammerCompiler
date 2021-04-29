package com.idofast.common.enums;

import com.idofast.common.enums.base.BaseAttributeConvert;
import com.idofast.common.enums.base.IBaseEnum;

public enum  UserStatusEnum implements IBaseEnum<UserStatusEnum>
{
    NORMAL(0, "正常"),
    FORBIDDEN(2, "封禁");

    UserStatusEnum(int code, String msg)
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


    public static UserStatusEnum ofCode(Integer code){
        return (UserStatusEnum) IBaseEnum.ofCodeII(code, UserStatusEnum.class);
    }

    public static class Converter extends BaseAttributeConvert<UserStatusEnum>
    {
        public Converter(){
            super(UserStatusEnum.class);
        }
    }
}
