package com.idofast.admin;

import com.idofast.common.enums.EmailTypeEnum;
import org.junit.Test;

public class EmailTypeEnumTest
{

    @Test
    public void getCode()
    {
        System.out.println(EmailTypeEnum.ACCOUNT_EXPIRE.toString());
    }
}