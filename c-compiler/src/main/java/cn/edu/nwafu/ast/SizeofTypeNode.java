package cn.edu.nwafu.ast;
import cn.edu.nwafu.type.Type;
import cn.edu.nwafu.type.TypeRef;

/**
 * 对类型的sizeof
 */
public class SizeofTypeNode extends ExprNode {
    protected TypeNode operand;
    protected TypeNode type;

    public SizeofTypeNode(TypeNode operand, TypeRef type) {
        this.operand = operand;
        this.type = new TypeNode(type);
    }

    public Type operand() {
        return operand.type();
    }

    public TypeNode operandTypeNode() {
        return operand;
    }

    public Type type() {
        return type.type();
    }

    public TypeNode typeNode() {
        return type;
    }

    public Location location() {
        return operand.location();
    }

    protected void _dump(Dumper d) {
        d.printMember("operand", type);
    }

    boolean compatible(ExprNode programe)
    {
        return false;
    }
}
