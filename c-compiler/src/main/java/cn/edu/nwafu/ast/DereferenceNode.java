package cn.edu.nwafu.ast;
import cn.edu.nwafu.type.Type;

/**
 * 指针引用(*ptr)
 */
public class DereferenceNode extends LHSNode {
    private ExprNode expr;

    public DereferenceNode(ExprNode expr) {
        this.expr = expr;
    }

    protected Type origType() {
        return expr.type().baseType();
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
        if (type != null) {
            d.printMember("type", type);
        }
        d.printMember("expr", expr);
    }

}
