package com.idofast.admin.lexical;

import com.idofast.admin.javacc.CParser;
import com.idofast.admin.javacc.Token;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/3/10 10:21 下午
 */
public class LexicalAnalysis
{
    public static void main(String[] args)
    {
        CParser parser = new CParser(CParser.class.getClassLoader().getResourceAsStream("Basic.c"));
        Token nextToken = parser.getNextToken();
        while (nextToken != null && !nextToken.image.equals(""))
        {
            System.out.println(TokenUtil.getKindStr(nextToken.kind )+ " : " + nextToken.image);
            nextToken = parser.getNextToken();
        }
    }

}
