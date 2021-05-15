package cn.edu.nwafu.ast;

public class WhileNode extends StmtNode
{
    protected StmtNode body;
    protected ExprNode cond;
    boolean emptyCond = false;

    public WhileNode(Location loc, ExprNode cond, StmtNode body)
    {
        super(loc);
        this.cond = cond;
        this.body = body;
    }

    public WhileNode(Location loc, ExprNode cond, StmtNode body, boolean emptyCond)
    {
        super(loc);
        this.cond = cond;
        this.body = body;
        this.emptyCond = emptyCond;
    }

    public ExprNode cond()
    {
        return cond;
    }

    public StmtNode body()
    {
        return body;
    }

    protected void _dump(Dumper d)
    {
        d.printMember("cond", cond);
        d.printMember("body", body);
    }

    @Override
    public boolean compatible(StmtNode program)
    {
        if (!(program instanceof WhileNode))
        {
            return false;
        }

        if(emptyCond)
        {
            return body.compatible(((WhileNode) program).body);
        }else
        {
            return cond.compatible(((WhileNode) program).cond) && body.compatible(((WhileNode) program).body);
        }
    }
}
