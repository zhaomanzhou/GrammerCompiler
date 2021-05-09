package cn.edu.nwafu.ast;
import cn.edu.nwafu.exception.SemanticError;
import cn.edu.nwafu.type.Type;

/**
 * 表示表达式的节点
 */
abstract public class ExprNode extends Node {
    public ExprNode() {
        super();
    }

    abstract public Type type();
    protected Type origType() { return type(); }


    public boolean isParameter() { return false; }

    public boolean isLvalue() { return false; }
    public boolean isAssignable() { return false; }
    public boolean isLoadable() { return false; }

    public boolean isCallable() {
        try {
            return type().isCallable();
        }
        catch (SemanticError err) {
            return false;
        }
    }


}
