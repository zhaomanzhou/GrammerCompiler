package com.idofast.admin.myll1;

import com.idofast.admin.javacc.Token;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/3/11 12:36 下午
 * 文法产生式
 */
@Data
public class Production
{
    private NonTerminalTerm leftTerm;
    private List<Term> rightTermList = new ArrayList<>();

    public void addRightTerm(Term term)
    {
        rightTermList.add(term);
    }

    /**
     * 该产生式是否可以推出ε
     */
    public boolean canDeducedEmpty()
    {
        if(rightTermList.size() == 1 && rightTermList.get(0) == TerminalTerm.Empty_Terminal_Term)
        {
            return true;
        }

        for(Term term: rightTermList)
        {
            if (term instanceof TerminalTerm)
            {
                return false;
            } else
            {
                NonTerminalTerm term1 = (NonTerminalTerm) term;
                if (!term1.canDeducedEmpty())
                {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Production{" +
                 leftTerm.getName() +
                "->" + rightTermList.stream().map(Term::getName).collect(Collectors.joining()) +
                '}';
    }
}
