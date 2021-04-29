package com.idofast.admin.myll1.consructor;

import com.idofast.admin.javacc.CParser;
import com.idofast.admin.javacc.Token;
import com.idofast.admin.myll1.*;
import com.idofast.admin.myll1.stl.TermList;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/4/25 11:52 上午
 *
 * Grammar构造工具类
 */


public class GrammarInitializeUtil
{
    /**
     * 从产生式集合构造
     * @return
     *
     *  List<String> input = new ArrayList<>();
     *  input.add("E -> T J");
     *  input.add("J -> + T J");
     *  input.add("J -> ε");
     *  input.add("T -> F Y");
     *  input.add("Y -> * F Y");
     *  input.add("Y -> ε");
     *  input.add("F -> ( E )");
     *  input.add("F  -> i");
     *  input.add("Y -> ε");
     */
    public static Grammar initializeFromList(List<String> input)
    {
        //产生式集合
        List<Production> productionList = new ArrayList<>();

        TermList<TerminalTerm> terminalTermList = new TermList<>();
        TermList<NonTerminalTerm> nonTerminalTermList = new TermList<>();


        //求出非终结符
        for (String dervied : input)
        {
            String[] split = dervied.split("->");
            NonTerminalTerm nonTerminalTerm = new NonTerminalTerm();
            nonTerminalTerm.setName(split[0].trim());
            nonTerminalTermList.addWithoutDuplicate(nonTerminalTerm);
        }

        //根据上一步结果和产生式右部完善产生式
        for (String dervied : input)
        {
            String[] split = dervied.split("->");
            //产生式右部
            String ProductionRightPart = split[1].replace(" \t\n", "");

            //该产生式左部非终结符
            NonTerminalTerm derivedLeftTerm = nonTerminalTermList.getByName(split[0].trim());

            //对应的产生式
            Production production = new Production();
            production.setLeftTerm(nonTerminalTermList.getByName(split[0].trim()));
            productionList.add(production);

            //
            for (String rightTermUnitName : split[1].split(" "))
            {
                rightTermUnitName = rightTermUnitName.trim();
                //先判断是不是非终结符
                Term term = nonTerminalTermList.getByName(rightTermUnitName);
                //终结符
                if (term == null)
                {
                    //判断该非终结符是否之前已经解析过了
                    term = terminalTermList.getByName(rightTermUnitName);

                    //如果该终结符没有解析过，则生成一个并加入到终结符集
                    if(term == null)
                    {
                        term = genTerminalTerm(rightTermUnitName);
                        terminalTermList.addWithoutDuplicate((TerminalTerm) term);
                    }
                }
                production.addRightTerm(term);

            }
            derivedLeftTerm.addProduction(production);
        }

        Grammar grammar = new Grammar();
        grammar.setNonTerminalTermList(nonTerminalTermList.getTermList());
        grammar.setTerminalTermList(terminalTermList.getTermList());
        grammar.setProductionList(productionList);
        grammar.setStartTerm(nonTerminalTermList.getTermList().get(0));
        return grammar;
    }


    /**
     * 根据词法分析器生产对应的终结符
     */
    public static TerminalTerm genTerminalTerm(String name)
    {
        if(name.equals("ε"))
        {
            return TerminalTerm.Empty_Terminal_Term;
        }
        TerminalTerm terminalTerm = new TerminalTerm();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(name.getBytes());
        CParser parser = new CParser(byteArrayInputStream);
        Token token = parser.getNextToken();
        terminalTerm.setToken(token);
        return terminalTerm;
    }
}
