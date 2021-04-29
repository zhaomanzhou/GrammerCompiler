package com.idofast.admin.myll1;

import com.idofast.admin.myll1.stl.ExtendableTermSet;

import java.util.*;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/3/11 12:37 下午
 */

public class NonTerminalTerm extends Term
{
    //终结符名称
    private String name;



    //该非终结符可以推出的项
    private List<Production> productionList = new ArrayList<>();


    private List<Term> firstTermList = new ArrayList<>();
    //first 集
    private ExtendableTermSet firstTermSet = new ExtendableTermSet();

    private ExtendableTermSet followTermSet = new ExtendableTermSet();


    public void addSubFollowSet(NonTerminalTerm nonTerminalTerm)
    {
        followTermSet.addSubSet(nonTerminalTerm.firstTermSet);
    }

    public void addFollowTerm(Term term)
    {
        followTermSet.addTerm(term);
    }

    public Set<Term> getFollowTerms()
    {
        return followTermSet.getAllTerms();
    }


    public void addSubFirstSet(NonTerminalTerm nonTerminalTerm)
    {
        firstTermSet.addSubSet(nonTerminalTerm.firstTermSet);
    }

    public void addFirstTerm(Term term)
    {
        firstTermSet.addTerm(term);
    }

    public Set<Term> getFirstTerms()
    {
        return firstTermSet.getAllTerms();
    }

    @Override
    public boolean isTerminalTerm()
    {
        return false;
    }

    @Override
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void addProduction(Production production)
    {
        productionList.add(production);
    }


    public List<Production> getProductionList()
    {
        return productionList;
    }

    /**
     *
     * @return
     */
    public boolean canDeducedEmpty()
    {
        for(Production p: productionList)
        {
            if(p.canDeducedEmpty())
            {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof NonTerminalTerm))
        {
            return false;
        }
        NonTerminalTerm that = (NonTerminalTerm) o;
        return Objects.equals(name, that.name) ;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, productionList);
    }

    @Override
    public String toString()
    {
        return "NonTerminalTerm{" +
                 name +
                '}';
    }
}
