package cn.edu.nwafu.type;
import cn.edu.nwafu.ast.Location;

public class FloatTypeRef extends TypeRef {

    static public FloatTypeRef floatTypeRef() {
        return new FloatTypeRef("float");
    }
    static public FloatTypeRef doubleTypeRef() {
        return new FloatTypeRef("double");
    }

    static public FloatTypeRef floatTypeRef(Location loc) {
        return new FloatTypeRef("float", loc);
    }
    static public FloatTypeRef doubleTypeRef(Location loc) {
        return new FloatTypeRef("double", loc);
    }
    protected String name;

    public FloatTypeRef(String name) {
        this(name, null);
    }

    public FloatTypeRef(String name, Location loc) {
        super(loc);
        this.name = name;
    }

    public String name() {
        return name;
    }

    public boolean equals(Object other) {
        if (! (other instanceof FloatTypeRef)) return false;
        FloatTypeRef ref = (FloatTypeRef)other;
        return name.equals(ref.name);
    }

    public String toString() {
        return name;
    }
}
