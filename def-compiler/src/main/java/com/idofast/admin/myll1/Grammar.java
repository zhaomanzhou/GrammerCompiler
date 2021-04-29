package com.idofast.admin.myll1;


import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Grammar
{
    private List<TerminalTerm> terminalTermList;
    private List<NonTerminalTerm> nonTerminalTermList;
    private List<Production> productionList;
    private NonTerminalTerm startTerm;


    public void calFollow()
    {
        startTerm.addFollowTerm(TerminalTerm.End_Terminal_Term);
        for (Production prod : productionList)
        {
            List<Term> rightTermList = prod.getRightTermList();
            NonTerminalTerm leftTerm = prod.getLeftTerm();

            //2.
            for (int i = 0; i < rightTermList.size()-1; i++)
            {
                Term prveTerm = rightTermList.get(i);
                if(prveTerm instanceof TerminalTerm)
                {
                    continue;
                }
                Term nextTerm = rightTermList.get(i + 1);
                for(Term t: nextTerm.getFirstTerms())
                {
                    if(t != TerminalTerm.Empty_Terminal_Term)
                    {
                        ((NonTerminalTerm) prveTerm).addFollowTerm(t);
                    }
                }
            }

            //3.
            for (int i = rightTermList.size()-1; i > 0; i--)
            {
                Term term = rightTermList.get(i);
                if(term == TerminalTerm.Empty_Terminal_Term)
                {
                    continue;
                }
                if(term instanceof TerminalTerm)
                {
                    break;
                }
                ((NonTerminalTerm) term).addSubFollowSet(leftTerm);
            }

        }
    }

    public void calFirst()
    {

        for(Production prod: productionList)
        {
            List<Term> rightTermList = prod.getRightTermList();
            NonTerminalTerm leftTerm = prod.getLeftTerm();

            boolean loop = true;
            int i = 0;
            while (loop && i < rightTermList.size())
            {

                Term term = rightTermList.get(i);

                i++;
                if (term instanceof TerminalTerm)
                {
                    leftTerm.addFirstTerm(term);
                    loop = false;
                } else
                {
                    leftTerm.addSubFirstSet((NonTerminalTerm) term);
                    if(!((NonTerminalTerm) term).canDeducedEmpty())
                    {
                        loop = false;
                    }
                }
            }

            if (loop)
            {
                leftTerm.addFirstTerm(TerminalTerm.Empty_Terminal_Term);
            }
        }

    }

    @Override
    public String toString()
    {
        return "Grammar{" +
                "\nterminalTermList=" + terminalTermList.stream().map(TerminalTerm::getName).collect(Collectors.toList()) +
                "\nnonTerminalTermList=" + nonTerminalTermList.stream().map(NonTerminalTerm::getName).collect(Collectors.toList()) +
                "\nproductionList=" + productionList.stream().map(p -> p.getLeftTerm().getName() +
                "->" + p.getRightTermList().stream().map(Term::getName).collect(Collectors.joining())).collect(Collectors.toList()) +
                "\nstartTerm=" + startTerm +
                "\n}";
    }
}
