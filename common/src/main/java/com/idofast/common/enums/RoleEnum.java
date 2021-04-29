package com.idofast.common.enums;

import com.idofast.common.enums.base.BaseAttributeConvert;
import com.idofast.common.enums.base.IBaseEnum;

public enum RoleEnum implements IBaseEnum<RoleEnum>
{
    ADMIN(0, "admin"),
    PLAIN(1,"user");


    RoleEnum(int code, String msg)
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


    public static RoleEnum ofCode(Integer code){
        return (RoleEnum) IBaseEnum.ofCodeII(code, RoleEnum.class);
    }

    public static class Converter extends BaseAttributeConvert<RoleEnum>
    {
        public Converter(){
            super(RoleEnum.class);
        }
    }

}