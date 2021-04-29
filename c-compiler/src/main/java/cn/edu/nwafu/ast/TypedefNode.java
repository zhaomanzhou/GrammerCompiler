package cn.edu.nwafu.ast;
import cn.edu.nwafu.type.Type;
import cn.edu.nwafu.type.TypeRef;
import cn.edu.nwafu.type.UserType;
import cn.edu.nwafu.type.UserTypeRef;

public class TypedefNode extends TypeDefinition {
    protected TypeNode real;

    public TypedefNode(Location loc, TypeRef real, String name) {
        super(loc, new UserTypeRef(name), name);
        this.real = new TypeNode(real);
    }

    public boolean isUserType() {
        return true;
    }

    public TypeNode realTypeNode() {
        return real;
    }

    public Type realType() {
        return real.type();
    }

    public TypeRef realTypeRef() {
        return real.typeRef();
    }

    // #@@range/definingType{
    public Type definingType() {
        return new UserType(name(), realTypeNode(), location());
    }
    // #@@}

    protected void _dump(Dumper d) {
        d.printMember("name", name);
        d.printMember("typeNode", typeNode);
    }

    public <T> T accept(DeclarationVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
