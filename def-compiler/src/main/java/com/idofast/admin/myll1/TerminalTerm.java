package com.idofast.admin.myll1;

import com.idofast.admin.javacc.Token;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/3/11 12:37 下午
 */
@Data
public class TerminalTerm extends Term
{
    private Token token;

    @Override
    public Set<Term> getFirstTerms()
    {
        Set<Term> set = new HashSet<>();
        set.add(this);
        return set;
    }

    @Override
    public boolean isTerminalTerm()
    {
        return true;
    }

    @Override
    public String getName()
    {
        return token.image;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof TerminalTerm))
        {
            return false;
        }
        TerminalTerm that = (TerminalTerm) o;
        return token.image.equals(that.token.image) && token.kind == that.token.kind;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(token);
    }

    public static final TerminalTerm Empty_Terminal_Term;
    public static final TerminalTerm End_Terminal_Term;
    static {
        Token emptyToken = new Token(36, "ε");
        TerminalTerm emptyTerm = new TerminalTerm();
        emptyTerm.setToken(emptyToken);
        Empty_Terminal_Term = emptyTerm;

        Token endToken = new Token(37, "#");
        TerminalTerm endTerm = new TerminalTerm();
        endTerm.setToken(endToken);
        End_Terminal_Term = endTerm;


    }
}
