package com.idofast.admin.util;

import org.junit.Test;

public class VcodeUtilTest
{

    @Test
    public void generateVCode()
    {
        for (int i = 0; i < 100000; i++)
        {
            System.out.println(VcodeUtil.generateVCode());


        }
    }
}