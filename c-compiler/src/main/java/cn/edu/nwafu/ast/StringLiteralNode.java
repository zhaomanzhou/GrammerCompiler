package cn.edu.nwafu.ast;
import cn.edu.nwafu.type.TypeRef;

/**
 * 字符串字面量
 */
public class StringLiteralNode extends LiteralNode {
    protected String value;

    public StringLiteralNode(Location loc, TypeRef ref, String value) {
        super(loc, ref);
        this.value = value;
    }

    public String value() {
        return value;
    }



    protected void _dump(Dumper d) {
        d.printMember("value", value);
    }

    boolean compatible(ExprNode programe)
    {
        if(!(programe instanceof  StringLiteralNode))
            return false;
        return value.equals(((StringLiteralNode) programe).value);
    }
}
