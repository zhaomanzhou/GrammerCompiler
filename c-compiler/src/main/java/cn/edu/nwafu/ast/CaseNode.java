package cn.edu.nwafu.ast;
import cn.edu.nwafu.asm.Label;

import java.util.*;

/**
 * 类型转换
 */
public class CaseNode extends StmtNode {
    protected Label label;
    protected List<ExprNode> values;
    protected BlockNode body;

    public CaseNode(Location loc, List<ExprNode> values, BlockNode body) {
        super(loc);
        this.values = values;
        this.body = body;
        this.label = new Label();
    }

    public List<ExprNode> values() {
        return values;
    }

    public boolean isDefault() {
        return values.isEmpty();
    }

    public BlockNode body() {
        return body;
    }

    public Label label() {
        return label;
    }

    protected void _dump(Dumper d) {
        d.printNodeList("values", values);
        d.printMember("body", body);
    }

    public <S,E> S accept(ASTVisitor<S,E> visitor) {
        return visitor.visit(this);
    }
}
