package cn.edu.nwafu.ast;
import cn.edu.nwafu.exception.SemanticError;
import cn.edu.nwafu.type.FunctionType;
import cn.edu.nwafu.type.Type;
import java.util.*;

/**
 * 函数调用
 */
public class FuncallNode extends ExprNode {
    protected ExprNode expr;
    protected List<ExprNode> args;

    public FuncallNode(ExprNode expr, List<ExprNode> args) {
        this.expr = expr;
        this.args = args;
    }

    public ExprNode expr() {
        return expr;
    }

    /**
     * Returns a type of return value of the function which is refered
     * by expr.  This method expects expr.type().isCallable() is true.
     */
    public Type type() {
        try {
            return functionType().returnType();
        }
        catch (ClassCastException err) {
            throw new SemanticError(err.getMessage());
        }
    }

    /**
     * Returns a type of function which is refered by expr.
     * This method expects expr.type().isCallable() is true.
     */
    public FunctionType functionType() {
        return expr.type().getPointerType().baseType().getFunctionType();
    }

    public long numArgs() {
        return args.size();
    }

    public List<ExprNode> args() {
        return args;
    }

    // called from TypeChecker
    public void replaceArgs(List<ExprNode> args) {
        this.args = args;
    }

    public Location location() {
        return expr.location();
    }

    protected void _dump(Dumper d) {
        d.printMember("expr", expr);
        d.printNodeList("args", args);
    }

    @Override
    boolean compatible(ExprNode programe)
    {
        if(!(programe instanceof FuncallNode)) return false;

        if(!expr.compatible(((FuncallNode) programe).expr))
            return false;
        for(ExprNode arg : args)
        {
            boolean finded = false;
            for(ExprNode pgArg: ((FuncallNode) programe).args)
            {
                if(arg.compatible(pgArg))
                {
                    finded = true;
                    break;
                }
            }
            if(!finded)
            {
                return false;
            }
        }
        return true;
    }
}
