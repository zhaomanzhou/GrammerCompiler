package cn.edu.nwafu.ast;

public class OpAssignNode extends AbstractAssignNode {
    protected String operator;

    public OpAssignNode(ExprNode lhs, String op, ExprNode rhs) {
        super(lhs, rhs);
        this.operator = op;
    }

    public String operator() {
        return operator;
    }


    boolean compatible(ExprNode programe)
    {
        if(!(programe instanceof  OpAssignNode))
        {
            if(operator.equals("---"))
            {
                if(!(programe instanceof AssignNode) )
                {
                    return false;
                }
                return lhs.compatible(((AssignNode) programe).lhs);
            }else
            {
                return false;
            }
        }
        OpAssignNode pg = (OpAssignNode)programe;
        if(!operator.equals("---"))
        {
            if(!operator.equals(pg.operator))
            {
                return false;
            }
        }

        if(!lhs.compatible(((OpAssignNode) programe).lhs))
        {
            return false;
        }

        if(rhs != null)
        {
            return rhs.compatible(((OpAssignNode) programe).rhs);
        }
        return true;
    }
}
