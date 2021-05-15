package cn.edu.nwafu.ast;
import cn.edu.nwafu.entity.DefinedVariable;
import cn.edu.nwafu.utils.Result;

import java.util.*;

public class BlockNode extends StmtNode {
    protected List<DefinedVariable> variables;
    protected List<StmtNode> stmts;
    protected List<StmtNode> forbiddenStmts;

    public BlockNode(Location loc, List<DefinedVariable> vars, List<StmtNode> stmts) {
        super(loc);
        this.variables = vars;
        this.stmts = stmts;
    }

    public BlockNode(Location loc, List<DefinedVariable> vars, List<StmtNode> stmts, List<StmtNode> forbiddenStmts) {
        super(loc);
        this.variables = vars;
        this.stmts = stmts;
        this.forbiddenStmts = forbiddenStmts;
    }

    public List<DefinedVariable> variables() {
        return variables;
    }

    public List<StmtNode> stmts() {
        return stmts;
    }



    protected void _dump(Dumper d) {
        d.printNodeList("variables", variables);
        d.printNodeList("stmts", stmts);
    }

    @Override
    public boolean compatible(StmtNode program)
    {
        if(!(program instanceof BlockNode))
            return false;
        for(DefinedVariable definedVariable: variables)
        {
            boolean finded = false;
            for(DefinedVariable progVar: ((BlockNode) program).variables())
            {
                if(definedVariable.compatible(progVar))
                {
                    finded = true;
                    break;
                }
            }
            if(!finded)
            {
                return false;
            }
        }




        int i = 0;
        int j = 0;

        while (i < stmts.size())
        {
            if(j == ((BlockNode) program).stmts.size()) break;
            for (;j < ((BlockNode) program).stmts.size(); )
            {
                if(stmts.get(i).compatible(((BlockNode) program).stmts.get(j)))
                {
                    i++;
                    j++;
                    break;
                }else
                {
                    j++;
                }
            }

        }

        if(i <stmts.size())
        {
            return false;
        }

        return true;
    }
}
