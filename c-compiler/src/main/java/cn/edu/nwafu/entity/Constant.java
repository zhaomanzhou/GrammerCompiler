package cn.edu.nwafu.entity;
import cn.edu.nwafu.ast.Dumper;
import cn.edu.nwafu.ast.TypeNode;
import cn.edu.nwafu.ast.ExprNode;


/**
 * 以const 开头定义的变量
 */
public class Constant extends Entity {
    private TypeNode type;
    private String name;
    private ExprNode value;

    public Constant(TypeNode type, String name, ExprNode value) {
        super(true, type, name);
        this.value = value;
    }

    public boolean isAssignable() { return false; }
    public boolean isDefined() { return true; }
    public boolean isInitialized() { return true; }
    public boolean isConstant() { return true; }

    public ExprNode value() { return value; }

    protected void _dump(Dumper d) {
        d.printMember("name", name);
        d.printMember("typeNode", typeNode);
        d.printMember("value", value);
    }

}
