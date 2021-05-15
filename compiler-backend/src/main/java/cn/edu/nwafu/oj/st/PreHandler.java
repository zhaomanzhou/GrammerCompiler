package cn.edu.nwafu.oj.st;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/5/10 1:29 下午
 * 预处理，去掉头部的#include，然后用gcc处理
 */
@Component
public class PreHandler
{
    public void prehandle(String folderPath) throws IOException, InterruptedException
    {
        Scanner sc = new Scanner(new FileInputStream(folderPath + "user.c"));
        PrintWriter pw = new PrintWriter(new FileOutputStream(folderPath + "userClean.c"));

        while (sc.hasNextLine())
        {
            String s = sc.nextLine();
            if(!s.replace(" ", "").startsWith("#include"))
            {
                pw.println(s);
            }
        }

        sc.close();
        pw.close();
        Process exec = Runtime.getRuntime().exec("gcc -P -E  -o  "+folderPath + "userCleanReplace.c "+ folderPath +"userClean.c");
        boolean b = exec.waitFor(5, TimeUnit.SECONDS);
        if(!b)
        {
            throw new InterruptedIOException("gcc -P -E 调用超时");
        }
    }
}
