package cn.edu.nwafu.ast;

public class ReturnNode extends StmtNode {
    protected ExprNode expr;

    public ReturnNode(Location loc, ExprNode expr) {
        super(loc);
        this.expr = expr;
    }

    public ExprNode expr() {
        return this.expr;
    }

    public void setExpr(ExprNode expr) {
        this.expr = expr;
    }

    protected void _dump(Dumper d) {
        d.printMember("expr", expr);
    }

}
