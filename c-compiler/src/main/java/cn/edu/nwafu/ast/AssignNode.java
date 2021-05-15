package cn.edu.nwafu.ast;

public class AssignNode extends AbstractAssignNode {
    public AssignNode(ExprNode lhs, ExprNode rhs) {
        super(lhs, rhs);
    }

    protected boolean ignoreRhs = false;

    @Override
    boolean compatible(ExprNode programe)
    {
        if(!(programe instanceof  AssignNode))
        {
            return false;
        }
        AssignNode pg = (AssignNode)programe;


        if(!lhs.compatible(((AssignNode) programe).lhs))
        {
            return false;
        }

        if(rhs != null)
        {
            return rhs.compatible(((AssignNode) programe).rhs);
        }
        return true;
    }
}
