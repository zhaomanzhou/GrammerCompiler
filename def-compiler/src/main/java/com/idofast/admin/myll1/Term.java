package com.idofast.admin.myll1;

import java.util.Set;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/3/11 12:36 下午
 */
public abstract class Term
{
    public abstract boolean isTerminalTerm();
    public abstract String getName();
    public abstract Set<Term> getFirstTerms();
}
