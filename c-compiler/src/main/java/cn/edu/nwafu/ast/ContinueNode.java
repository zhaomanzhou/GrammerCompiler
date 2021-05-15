package cn.edu.nwafu.ast;

public class ContinueNode extends StmtNode {
    public ContinueNode(Location loc) {
        super(loc);
    }

    protected void _dump(Dumper d) {
    }

    @Override
    public boolean compatible(StmtNode program)
    {
        if(!(program instanceof ContinueNode))
        {
            return false;
        }
        return true;
    }
}
