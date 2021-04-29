package com.idofast.admin.domain.enumconvert;

import com.idofast.common.enums.base.IBaseEnum;
import com.idofast.common.enums.RoleEnum;

import javax.persistence.AttributeConverter;

public class RoleEnumConvert<T extends IBaseEnum> implements AttributeConverter<RoleEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(RoleEnum attribute)
    {
        return attribute.getCode();
    }

    @Override
    public RoleEnum convertToEntityAttribute(Integer dbData)
    {
        return RoleEnum.ofCode(dbData);
    }
}

