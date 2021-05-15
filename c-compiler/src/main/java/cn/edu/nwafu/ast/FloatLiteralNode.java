package cn.edu.nwafu.ast;
import cn.edu.nwafu.type.TypeRef;

/**
 * 整数字面量
 */
public class FloatLiteralNode extends LiteralNode {
    protected String value;

    public FloatLiteralNode(Location loc, TypeRef ref, String value) {
        super(loc, ref);
        this.value = value;
    }

    public String value() {
        return value;
    }

    protected void _dump(Dumper d) {
        d.printMember("typeNode", typeNode);
        d.printMember("value", value);
    }

    @Override
    boolean compatible(ExprNode programe)
    {
        if(!(programe instanceof FloatLiteralNode))
            return false;
        return value.equals(((FloatLiteralNode) programe).value);
    }
}
