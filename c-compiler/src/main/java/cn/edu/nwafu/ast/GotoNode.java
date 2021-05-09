package cn.edu.nwafu.ast;

public class GotoNode extends StmtNode {
    protected String target;

    public GotoNode(Location loc, String target) {
        super(loc);
        this.target = target;
    }

    public String target() {
        return target;
    }

    protected void _dump(Dumper d) {
        d.printMember("target", target);
    }

}
