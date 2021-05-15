package cn.edu.nwafu.admin.domain.enumconvert;


import com.idofast.common.enums.DeletedEnum;

import javax.persistence.AttributeConverter;

public class DeletedEnumConvert implements AttributeConverter<DeletedEnum, Integer>
{
    @Override
    public Integer convertToDatabaseColumn(DeletedEnum attribute)
    {
        return attribute.getCode();
    }

    @Override
    public DeletedEnum convertToEntityAttribute(Integer dbData)
    {
        return DeletedEnum.ofCode(dbData);
    }
}
