package com.idofast.common.enums;

import com.idofast.common.enums.base.BaseAttributeConvert;
import com.idofast.common.enums.base.IBaseEnum;

public enum OsDeviceEnum implements IBaseEnum<OsDeviceEnum>
{
    ANDROID(0b01, "安卓"),
    IOS(0b010, "IOS"),
    WIN10(0b0100, "win10"),
    WIN7(0b01000, "win7"),
    MAC(0b010000, "苹果电脑");

    OsDeviceEnum(int code, String msg)
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


    public static OsDeviceEnum ofCode(Integer code){
        return (OsDeviceEnum) IBaseEnum.ofCodeII(code, OsDeviceEnum.class);
    }

    public static class Converter extends BaseAttributeConvert<OsDeviceEnum>
    {
        public Converter(){
            super(OsDeviceEnum.class);
        }
    }
}
