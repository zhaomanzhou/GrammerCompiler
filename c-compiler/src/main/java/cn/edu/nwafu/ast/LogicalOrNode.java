package cn.edu.nwafu.ast;

public class LogicalOrNode extends BinaryOpNode {
    public LogicalOrNode(ExprNode left, ExprNode right) {
        super(left, "||", right);
    }

}
