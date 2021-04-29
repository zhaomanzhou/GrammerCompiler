package com.idofast.admin.lexical;

import com.idofast.admin.javacc.CParser;
import com.idofast.admin.javacc.CParserConstants;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/3/10 10:24 下午
 */
public class TokenUtil
{
    /**
     * token的kind为数字，该方法通过反射获取kind对应的字符串
     * @param kind
     * @return
     */
    public static String getKindStr(int kind)
    {
        Field[] fields = CParserConstants.class.getFields();
        for (int i = 0; i < fields.length; i++)
        {
            try
            {
                if(fields[i].get(null).equals(kind))
                {
                    return fields[i].getName();
                }
            } catch (IllegalAccessException e)
            {
                return null;
            }
        }
        return null;
    }


}
