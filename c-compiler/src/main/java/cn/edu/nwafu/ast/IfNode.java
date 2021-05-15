package cn.edu.nwafu.ast;

public class IfNode extends StmtNode {
    protected ExprNode cond;
    protected StmtNode thenBody;
    protected StmtNode elseBody;
    boolean emptyCond = false;

    public IfNode(Location loc, ExprNode c, StmtNode t, StmtNode e) {
        super(loc);
        this.cond = c;
        this.thenBody = t;
        this.elseBody = e;
    }

    public IfNode(Location loc, ExprNode c, StmtNode t, StmtNode e, boolean emptyCond) {
        super(loc);
        this.cond = c;
        this.thenBody = t;
        this.elseBody = e;
        this.emptyCond = emptyCond;
    }

    public ExprNode cond() {
        return cond;
    }

    public StmtNode thenBody() {
        return thenBody;
    }

    public StmtNode elseBody() {
        return elseBody;
    }

    protected void _dump(Dumper d) {
        d.printMember("cond", cond);
        d.printMember("thenBody", thenBody);
        d.printMember("elseBody", elseBody);
    }

    public boolean compatible(StmtNode program)
    {
        if(!(program instanceof IfNode))
            return false;
        if(!emptyCond)
        {
            if(!cond.compatible(((IfNode) program).cond))
                return false;
        }
        if(!thenBody.compatible(((IfNode) program).thenBody))
            return false;
        if(elseBody != null)
        {
            if(((IfNode) program).elseBody == null)
            {
                return false;
            }
            return elseBody.compatible(((IfNode) program).elseBody);
        }

        return true;
    }
}
