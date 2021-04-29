package com.idofast.common.enums.base;


import javax.persistence.AttributeConverter;

public class BaseAttributeConvert<T extends IBaseEnum<T>> implements AttributeConverter<T, Integer>
{
    private final Class<? extends IBaseEnum<T>> clazz;

    public BaseAttributeConvert(Class<? extends IBaseEnum<T>> clazz)
    {
        this.clazz = clazz;
    }

    @Override
    public Integer convertToDatabaseColumn(T attribute)
    {
        return attribute.getCode();
    }



    @Override
    public T convertToEntityAttribute(Integer dbData)
    {
        return (T) IBaseEnum.ofCodeII(dbData, clazz);
    }


}
