package com.idofast.common.enums;

import com.idofast.common.enums.base.BaseAttributeConvert;
import com.idofast.common.enums.base.IBaseEnum;

public enum NoticeStatusEnum implements IBaseEnum<NoticeStatusEnum>
{
    DRAFT(0, "未发布"),
    PUBLISHED(1, "已发布"),
    DOWN(2, "下架"),

    ;
    NoticeStatusEnum(int code, String msg)
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


    public static NoticeStatusEnum ofCode(Integer code){
        return (NoticeStatusEnum) IBaseEnum.ofCodeII(code, NoticeStatusEnum.class);
    }

    public static class Converter extends BaseAttributeConvert<NoticeStatusEnum>
    {
        public Converter(){
            super(NoticeStatusEnum.class);
        }
    }
}
