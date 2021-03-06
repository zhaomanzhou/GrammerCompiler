package cn.edu.nwafu.ast;
import cn.edu.nwafu.type.TypeRef;
import cn.edu.nwafu.entity.ConstantEntry;

/**
 * 字符串字面量
 */
public class StringLiteralNode extends LiteralNode {
    protected String value;
    protected ConstantEntry entry;

    public StringLiteralNode(Location loc, TypeRef ref, String value) {
        super(loc, ref);
        this.value = value;
    }

    public String value() {
        return value;
    }

    public ConstantEntry entry() {
        return entry;
    }

    public void setEntry(ConstantEntry ent) {
        entry = ent;
    }

    protected void _dump(Dumper d) {
        d.printMember("value", value);
    }

    public <S,E> E accept(ASTVisitor<S,E> visitor) {
        return visitor.visit(this);
    }
}
