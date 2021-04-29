package cn.edu.nwafu.ast;
import cn.edu.nwafu.type.Type;

/**
 * 三元运算符  b? a: c;
 */
public class CondExprNode extends ExprNode {
    protected ExprNode cond, thenExpr, elseExpr;

    public CondExprNode(ExprNode cond, ExprNode t, ExprNode e) {
        super();
        this.cond = cond;
        this.thenExpr = t;
        this.elseExpr = e;
    }

    public Type type() {
        return thenExpr.type();
    }

    public ExprNode cond() {
        return cond;
    }

    public ExprNode thenExpr() {
        return thenExpr;
    }

    public void setThenExpr(ExprNode expr) {
        this.thenExpr = expr;
    }

    public ExprNode elseExpr() {
        return elseExpr;
    }

    public void setElseExpr(ExprNode expr) {
        this.elseExpr = expr;
    }

    public Location location() {
        return cond.location();
    }

    protected void _dump(Dumper d) {
        d.printMember("cond", cond);
        d.printMember("thenExpr", thenExpr);
        d.printMember("elseExpr", elseExpr);
    }

    public <S,E> E accept(ASTVisitor<S,E> visitor) {
        return visitor.visit(this);
    }
}
