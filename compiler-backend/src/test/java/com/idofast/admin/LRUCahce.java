package com.idofast.admin;

import org.junit.Test;

import java.util.LinkedHashMap;

public class LRUCahce
{
    @Test
    public void test()
    {
        int x1 = 0b1;
        int x2 = 0b10;
        int x3 = 0b100;
        int x4 = 0b1000;

        int osDevice = x1 | x4;

        System.out.println("contain 1 " + (osDevice & x1));
        System.out.println("contain 2 " + (osDevice & x2));
        System.out.println("contain 3 " + (osDevice & x3));
        System.out.println("contain 3 " + (osDevice & x4));


        
    }
}
