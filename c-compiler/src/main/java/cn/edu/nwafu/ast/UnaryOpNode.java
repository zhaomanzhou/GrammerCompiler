package cn.edu.nwafu.ast;
import cn.edu.nwafu.type.Type;


/**
 * 一元元算 + - ！～
 */
public class UnaryOpNode extends ExprNode {
    protected String operator;
    protected ExprNode expr;
    protected Type opType;

    public UnaryOpNode(String op, ExprNode expr) {
        this.operator = op;
        this.expr = expr;
    }

    public String operator() {
        return operator;
    }

    public Type type() {
        return expr.type();
    }

    public void setOpType(Type t) {
        this.opType = t;
    }

    public Type opType() {
        return opType;
    }

    public ExprNode expr() {
        return expr;
    }

    public void setExpr(ExprNode expr) {
        this.expr = expr;
    }

    public Location location() {
        return expr.location();
    }

    protected void _dump(Dumper d) {
        d.printMember("operator", operator);
        d.printMember("expr", expr);
    }

    @Override
    boolean compatible(ExprNode programe)
    {
        if(!(programe instanceof UnaryOpNode))
            return false;
        return expr.compatible(((UnaryOpNode) programe).expr);
    }
}
