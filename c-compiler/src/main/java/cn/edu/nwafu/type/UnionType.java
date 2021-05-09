package cn.edu.nwafu.type;
import cn.edu.nwafu.ast.Slot;
import cn.edu.nwafu.ast.Location;
import java.util.*;

public class UnionType extends CompositeType {
    public UnionType(String name, List<Slot> membs, Location loc) {
        super(name, membs, loc);
    }

    public boolean isUnion() { return true; }

    public boolean isSameType(Type other) {
        if (! other.isUnion()) return false;
        return equals(other.getUnionType());
    }



    public String toString() {
        return "union " + name;
    }
}
