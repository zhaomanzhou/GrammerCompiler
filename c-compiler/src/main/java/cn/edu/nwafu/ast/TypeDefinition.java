package cn.edu.nwafu.ast;
import cn.edu.nwafu.type.Type;
import cn.edu.nwafu.type.TypeRef;

abstract public class TypeDefinition extends Node {
    protected String name;
    protected Location location;
    protected TypeNode typeNode;

    public TypeDefinition(Location loc, TypeRef ref, String name) {
        this.name = name;
        this.location = loc;
        this.typeNode = new TypeNode(ref);
    }

    public String name() {
        return name;
    }

    public Location location() {
        return location;
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


}
