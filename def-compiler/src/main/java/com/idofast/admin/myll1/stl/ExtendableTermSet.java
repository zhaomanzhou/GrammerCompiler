package com.idofast.admin.myll1.stl;

import com.idofast.admin.myll1.Term;
import com.idofast.admin.myll1.TerminalTerm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/3/13 10:22 下午
 */
public class ExtendableTermSet
{
    //subSet中要去除的term
    private TerminalTerm excludedTerm;

    private Set<ExtendableTermSet> subSets = new HashSet<>();

    private Set<Term> termSet = new HashSet<>();

    public void addSubSet(ExtendableTermSet extendableSet)
    {
        subSets.add(extendableSet);
    }

    public void addTerm(Term term)
    {
        termSet.add(term);
    }

    public Set<Term> getAllTerms()
    {
        Set<Term> allTerms = new HashSet<>();
        for(ExtendableTermSet extendableTermSet: subSets)
        {
            allTerms.addAll(extendableTermSet.getAllTerms());
        }

        allTerms.remove(TerminalTerm.Empty_Terminal_Term);
        allTerms.addAll(termSet);

        return allTerms;
    }
}
