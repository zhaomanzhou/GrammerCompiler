package com.idofast.common.common;


public class RedisPrefixConst {
    public static String TOKEN_PREFIX = "token-";

    public static String withTokenPrefix(String token)
    {
        return TOKEN_PREFIX + token;
    }
}
