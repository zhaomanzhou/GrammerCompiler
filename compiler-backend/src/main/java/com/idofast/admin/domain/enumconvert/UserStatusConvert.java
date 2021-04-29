package com.idofast.admin.domain.enumconvert;

import com.idofast.common.enums.UserStatusEnum;

import javax.persistence.AttributeConverter;

public class UserStatusConvert implements AttributeConverter<UserStatusEnum, Integer>
{
    @Override
    public Integer convertToDatabaseColumn(UserStatusEnum attribute)
    {
        return attribute.getCode();
    }

    @Override
    public UserStatusEnum convertToEntityAttribute(Integer dbData)
    {
        return UserStatusEnum.ofCode(dbData);
    }
}
