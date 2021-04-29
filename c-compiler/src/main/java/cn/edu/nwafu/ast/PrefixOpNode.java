package cn.edu.nwafu.ast;

/**
 * 前置元算  ++  --
 */
public class PrefixOpNode extends UnaryArithmeticOpNode {
    public PrefixOpNode(String op, ExprNode expr) {
        super(op, expr);
    }

    public <S,E> E accept(ASTVisitor<S,E> visitor) {
        return visitor.visit(this);
    }
}
