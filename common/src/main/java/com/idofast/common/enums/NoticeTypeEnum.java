package com.idofast.common.enums;

import com.idofast.common.enums.base.BaseAttributeConvert;
import com.idofast.common.enums.base.IBaseEnum;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2020/12/19 12:43 下午
 *
 * 公告类型
 */

public enum  NoticeTypeEnum implements IBaseEnum<NoticeTypeEnum>
{


    INSTRUCTION(0, "安装教程"),  //教程
    NOTIFICATION(1, "通知公告"), //通知
    KNOWLEDGE(2, "科普"),       //科普
    ;
    NoticeTypeEnum(int code, String msg)
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


    public static NoticeTypeEnum ofCode(Integer code){
        return (NoticeTypeEnum) IBaseEnum.ofCodeII(code, NoticeTypeEnum.class);
    }

    public static class Converter extends BaseAttributeConvert<NoticeTypeEnum>
    {
        public Converter(){
            super(NoticeTypeEnum.class);
        }
    }
}
