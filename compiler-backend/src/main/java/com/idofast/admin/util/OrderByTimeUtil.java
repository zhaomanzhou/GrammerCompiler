package com.idofast.admin.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderByTimeUtil
{
    public static Long getOrderByCurTime()
    {
        LocalDateTime now = LocalDateTime.now();
        String order = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return Long.parseLong(order);
    }


    public static void main(String[] args)
    {
        System.out.println(getOrderByCurTime());
    }


}
