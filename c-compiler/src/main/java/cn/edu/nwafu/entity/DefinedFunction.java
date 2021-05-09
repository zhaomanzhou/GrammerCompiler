package cn.edu.nwafu.entity;
import cn.edu.nwafu.ast.Dumper;
import cn.edu.nwafu.ast.TypeNode;
import cn.edu.nwafu.ast.BlockNode;
import java.util.List;

public class DefinedFunction extends Function {
    protected Params params;
    protected BlockNode body;

    public DefinedFunction(boolean priv, TypeNode type,
            String name, Params params, BlockNode body) {
        super(priv, type, name);
        this.params = params;
        this.body = body;
    }

    public boolean isDefined() {
        return true;
    }

    public List<Parameter> parameters() {
        return params.parameters();
    }

    public BlockNode body() {
        return body;
    }





    /**
     * Returns function local variables.
     * Does NOT include paramters.
     * Does NOT include static local variables.
     */

    protected void _dump(Dumper d) {
        d.printMember("name", name);
        d.printMember("isPrivate", isPrivate);
        d.printMember("params", params);
        d.printMember("typeNode", typeNode);
        d.printMember("body", body);
    }

}
