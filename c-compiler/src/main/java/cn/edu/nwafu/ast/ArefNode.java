package cn.edu.nwafu.ast;
import cn.edu.nwafu.type.ArrayType;
import cn.edu.nwafu.type.Type;

/**
 * 数组引用   arr[i]
 */
public class ArefNode extends LHSNode {
    private ExprNode expr, index;

    public ArefNode(ExprNode expr, ExprNode index) {
        this.expr = expr;
        this.index = index;
    }

    public ExprNode expr() { return expr; }
    public ExprNode index() { return index; }

    // isMultiDimension a[x][y][z] = true.
    // isMultiDimension a[x][y] = true.
    // isMultiDimension a[x] = false.
    public boolean isMultiDimension() {
        return (expr instanceof ArefNode) && !expr.origType().isPointer();
    }

    // Returns base expression of (multi-dimension) array.
    // e.g.  baseExpr of a[x][y][z] is a.
    public ExprNode baseExpr() {
        return isMultiDimension() ? ((ArefNode)expr).baseExpr() : expr;
    }



    public long length() {
        return ((ArrayType)expr.origType()).length();
    }

    protected Type origType() {
        return expr.origType().baseType();
    }

    public Location location() {
        return expr.location();
    }

    protected void _dump(Dumper d) {
        if (type != null) {
            d.printMember("type", type);
        }
        d.printMember("expr", expr);
        d.printMember("index", index);
    }

}
