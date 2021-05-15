package cn.edu.nwafu.ast;
import cn.edu.nwafu.type.TypeRef;

/**
 * 整数字面量
 */
public class IntegerLiteralNode extends LiteralNode {
    protected long value;

    public IntegerLiteralNode(Location loc, TypeRef ref, long value) {
        super(loc, ref);
        this.value = value;
    }

    public long value() {
        return value;
    }

    protected void _dump(Dumper d) {
        d.printMember("typeNode", typeNode);
        d.printMember("value", value);
    }

    boolean compatible(ExprNode programe)
    {
        if(!(programe instanceof IntegerLiteralNode))
            return false;
        return value == ((IntegerLiteralNode) programe).value;
    }
}
