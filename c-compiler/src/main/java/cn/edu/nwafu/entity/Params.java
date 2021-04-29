package cn.edu.nwafu.entity;
import cn.edu.nwafu.ast.Dumpable;
import cn.edu.nwafu.ast.Dumper;
import cn.edu.nwafu.type.ParamTypeRefs;
import cn.edu.nwafu.type.TypeRef;
import cn.edu.nwafu.ast.Location;
import java.util.List;
import java.util.ArrayList;

public class Params extends ParamSlots<Parameter>
        implements Dumpable
{
    public Params(Location loc, List<Parameter> paramDescs) {
        super(loc, paramDescs, false);
    }

    public List<Parameter> parameters() {
        return paramDescriptors;
    }

    public ParamTypeRefs parametersTypeRef() {
        List<TypeRef> typerefs = new ArrayList<TypeRef>();
        for (Parameter param : paramDescriptors) {
            typerefs.add(param.typeNode().typeRef());
        }
        return new ParamTypeRefs(location, typerefs, vararg);
    }

    public boolean equals(Object other) {
        return (other instanceof Params) && equals((Params)other);
    }

    public boolean equals(Params other) {
        return other.vararg == vararg
                && other.paramDescriptors.equals(paramDescriptors);
    }
    
    public void dump(Dumper d) {
        d.printNodeList("parameters", parameters());
    }
}
