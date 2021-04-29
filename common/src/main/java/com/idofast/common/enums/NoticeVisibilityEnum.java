package com.idofast.common.enums;

import com.idofast.common.enums.base.BaseAttributeConvert;
import com.idofast.common.enums.base.IBaseEnum;

public enum NoticeVisibilityEnum implements IBaseEnum<NoticeVisibilityEnum>
{
    ALL(0, "所有人可见"),
    USER(1, "所有注册用户可见"),
    ADMIN(2,"只有管理员可见")
    ;
    NoticeVisibilityEnum(int code, String msg)
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


    public static NoticeVisibilityEnum ofCode(Integer code){
        return (NoticeVisibilityEnum) IBaseEnum.ofCodeII(code, NoticeVisibilityEnum.class);
    }

    public static class Converter extends BaseAttributeConvert<NoticeVisibilityEnum>
    {
        public Converter(){
            super(NoticeVisibilityEnum.class);
        }
    }
}
