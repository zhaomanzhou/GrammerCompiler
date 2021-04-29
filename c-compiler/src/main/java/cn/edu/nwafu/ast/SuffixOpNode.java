package cn.edu.nwafu.ast;

/**
 * 后置运算符   ++  --
 */
public class SuffixOpNode extends UnaryArithmeticOpNode {
    public SuffixOpNode(String op, ExprNode expr) {
        super(op, expr);
    }

    public <S,E> E accept(ASTVisitor<S,E> visitor) {
        return visitor.visit(this);
    }
}
