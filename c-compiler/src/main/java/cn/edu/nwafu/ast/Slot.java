package cn.edu.nwafu.ast;
import cn.edu.nwafu.type.Type;
import cn.edu.nwafu.type.TypeRef;

/**
 * 结构体，联合体内成员
 */
public class Slot extends Node {
    protected TypeNode typeNode;
    protected String name;
    protected long offset;

    public Slot(TypeNode t, String n) {
        typeNode = t;
        name = n;
        offset = Type.sizeUnknown;
    }

    public TypeNode typeNode() {
        return typeNode;
    }

    public TypeRef typeRef() {
        return typeNode.typeRef();
    }

    public Type type() {
        return typeNode.type();
    }

    public String name() {
        return name;
    }



    public Location location() {
        return typeNode.location();
    }

    protected void _dump(Dumper d) {
        d.printMember("name", name);
        d.printMember("typeNode", typeNode);
    }

    public boolean compatible(Slot program, boolean exactParam)
    {
        if(exactParam)
        return typeNode.typeRef.equals(program.typeRef()) && name.equals(program.name);
        else
            return typeNode.typeRef.equals(program.typeRef());
    }
}
