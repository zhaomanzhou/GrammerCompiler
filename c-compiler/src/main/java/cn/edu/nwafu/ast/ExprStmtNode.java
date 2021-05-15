package cn.edu.nwafu.ast;

public class ExprStmtNode extends StmtNode {
    protected ExprNode expr;

    public ExprStmtNode(Location loc, ExprNode expr) {
        super(loc);
        this.expr = expr;
    }

    public ExprNode expr() {
        return expr;
    }

    public void setExpr(ExprNode expr) {
        this.expr = expr;
    }

    protected void _dump(Dumper d) {
        d.printMember("expr", expr);
    }




    @Override
    public boolean compatible(StmtNode program)
    {
        if(!(program instanceof ExprStmtNode))
            return false;

        return expr.compatible(((ExprStmtNode)program).expr);
    }
}
