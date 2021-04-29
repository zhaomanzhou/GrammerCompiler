package com.idofast.admin.myll1;

import com.idofast.admin.javacc.CParser;
import com.idofast.admin.javacc.Token;
import com.idofast.admin.myll1.consructor.GrammarInitializeUtil;
import com.idofast.admin.myll1.stl.TermList;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/3/11 8:47 下午
 */
public class GrammarUtil
{


    public static void main(String[] args) throws IOException
    {
        List<String> input = new ArrayList<>();
        input.add("E -> T J");
        input.add("J -> + T J");
        input.add("J -> ε");
        input.add("T -> F Y");
        input.add("Y -> * F Y");
        input.add("Y -> ε");
        input.add("F ->( E )");
        input.add("F -> i");
        input.add("Y -> ε");
        Grammar grammar = GrammarInitializeUtil.initializeFromList(input);

//        for(NonTerminalTerm term: grammar.getNonTerminalTermList())
//        {
//            System.out.println(term.getName() + "---" + term.getFollowTerms().stream().map(Term::getName).collect(Collectors.joining(", ")));
//        }

        System.out.println(grammar);

    }



}
