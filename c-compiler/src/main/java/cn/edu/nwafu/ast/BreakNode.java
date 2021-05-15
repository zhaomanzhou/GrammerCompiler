package cn.edu.nwafu.ast;

public class BreakNode extends StmtNode {
    public BreakNode(Location loc) {
        super(loc);
    }

    protected void _dump(Dumper d) {
    }

    public boolean compatible(StmtNode program)
    {
        if(!(program instanceof  BreakNode))
            return false;
        return true;
    }
}
