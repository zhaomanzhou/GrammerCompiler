package com.idofast.admin.myll1.stl;

import com.idofast.admin.myll1.Term;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/3/11 9:48 下午
 *
 * 一个list集合，提供了一些方法方便初始化时求出终结符和非终结符
 */
public class TermList<T extends Term>
{
    private List<T> termList = new ArrayList<>();

    public void addWithoutDuplicate(T term)
    {
        if(!termList.contains(term))
        {
            termList.add(term);
        }
    }

    public T getByName(String name)
    {
        for(T term: termList)
        {
            if(term.getName().equals(name))
            {
                return term;
            }
        }
        return null;
    }

    public List<T> getTermList()
    {
        return termList;
    }
}
