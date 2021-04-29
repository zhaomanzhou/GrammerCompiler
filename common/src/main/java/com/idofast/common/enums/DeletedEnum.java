package com.idofast.common.enums;

import com.idofast.common.enums.base.BaseAttributeConvert;
import com.idofast.common.enums.base.IBaseEnum;

public enum  DeletedEnum implements IBaseEnum<DeletedEnum>
{
    NORMAL(0, "正常"),
    DELETED(1, "已删除");


    DeletedEnum(int code, String msg)
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


    public static DeletedEnum ofCode(Integer code){
        return (DeletedEnum) IBaseEnum.ofCodeII(code, DeletedEnum.class);
    }

    public static class Converter extends BaseAttributeConvert<DeletedEnum>
    {
        public Converter(){
            super(DeletedEnum.class);
        }
    }

}
