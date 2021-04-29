package com.idofast.common.enums.base;


public interface IBaseEnum<T extends IBaseEnum<T>>
{
    Integer getCode();

    String getMsg();


    static IBaseEnum  ofCodeII(Integer code, Class<? extends IBaseEnum> clazz){
        if(code == null)
        {
            return null;
        }
        IBaseEnum[] enumConstants = clazz.getEnumConstants();
        for(IBaseEnum e: enumConstants){
            if(e.getCode().equals(code)){
                return e;
            }
        }

        throw new UnsupportedOperationException("枚举转化异常。不存在的code：" + code);
    }




}
