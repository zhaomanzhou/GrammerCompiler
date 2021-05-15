package cn.edu.nwafu.ast;

public class ForNode extends StmtNode {
    protected StmtNode init;
    protected ExprNode cond;
    protected StmtNode incr;
    protected StmtNode body;
    protected boolean ignoreInit;
    protected boolean ignoreCond;
    protected boolean ignoreIncr;

    public ForNode(Location loc, 
                   ExprNode init, ExprNode cond, ExprNode incr, StmtNode body) {
        super(loc);
        this.init = new ExprStmtNode(init.location(), init);
        this.cond = cond;
        this.incr = new ExprStmtNode(incr.location(), incr);
        this.body = body;
    }
    public ForNode(Location loc,
                   ExprNode init, ExprNode cond, ExprNode incr, StmtNode body,
                   boolean ignoreInit, boolean ignoreCond, boolean ignoreIncr) {
        super(loc);
        this.init = new ExprStmtNode(init.location(), init);
        this.cond = cond;
        this.incr = new ExprStmtNode(incr.location(), incr);
        this.body = body;
        this.ignoreInit = ignoreInit;
        this.ignoreCond = ignoreCond;
        this.ignoreIncr = ignoreIncr;
    }

    public StmtNode init() {
        return init;
    }

    public ExprNode cond() {
        return cond;
    }

    public StmtNode incr() {
        return incr;
    }

    public StmtNode body() {
        return body;
    }

    protected void _dump(Dumper d) {
        d.printMember("init", init);
        d.printMember("cond", cond);
        d.printMember("incr", incr);
        d.printMember("body", body);
    }

    @Override
    public boolean compatible(StmtNode program)
    {
        if(!(program instanceof ForNode))
        {
            return false;
        }
        ForNode pg = (ForNode)program;

        if(!ignoreInit)
        {
            try{
                final boolean compatible = init.compatible(pg.init);
                if(!compatible){
                    return false;
                }
            }catch (Exception e){
                return false;
            }
        }

        if(!ignoreCond)
        {
            try{
                final boolean compatible = init.compatible(pg.init);
                if(!compatible){
                    return false;
                }
            }catch (Exception e){
                return false;
            }
        }

        if(!ignoreIncr)
        {
            try{
                final boolean compatible = init.compatible(pg.init);
                if(!compatible){
                    return false;
                }
            }catch (Exception e){
                return false;
            }
        }

        return false;
    }
}
