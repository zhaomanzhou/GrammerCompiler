package cn.edu.nwafu.ast;
import cn.edu.nwafu.exception.SemanticError;
import cn.edu.nwafu.type.Type;
import cn.edu.nwafu.type.CompositeType;
import cn.edu.nwafu.type.PointerType;

/**
 * 通过指针访问数组成员
 */
public class PtrMemberNode extends LHSNode {
    public ExprNode expr;
    public String member;

    public PtrMemberNode(ExprNode expr, String member) {
        this.expr = expr;
        this.member = member;
    }

    public CompositeType dereferedCompositeType() {
        try {
            PointerType pt = expr.type().getPointerType();
            return pt.baseType().getCompositeType();
        }
        catch (ClassCastException err) {
            throw new SemanticError(err.getMessage());
        }
    }

    public Type dereferedType() {
        try {
            PointerType pt = expr.type().getPointerType();
            return pt.baseType();
        }
        catch (ClassCastException err) {
            throw new SemanticError(err.getMessage());
        }
    }

    public ExprNode expr() {
        return expr;
    }

    public String member() {
        return member;
    }



    protected Type origType() {
        return dereferedCompositeType().memberType(member);
    }

    public Location location() {
        return expr.location();
    }

    protected void _dump(Dumper d) {
        if (type != null) {
            d.printMember("type", type);
        }
        d.printMember("expr", expr);
        d.printMember("member", member);
    }

    boolean compatible(ExprNode programe)
    {
        return false;
    }
}
