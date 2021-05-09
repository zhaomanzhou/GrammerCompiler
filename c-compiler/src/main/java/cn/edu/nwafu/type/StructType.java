package cn.edu.nwafu.type;
import cn.edu.nwafu.ast.Location;
import cn.edu.nwafu.ast.Slot;

import java.util.*;

public class StructType extends CompositeType {
    public StructType(String name, List<Slot> membs, Location loc) {
        super(name, membs, loc);
    }

    public boolean isStruct() { return true; }

    public boolean isSameType(Type other) {
        if (! other.isStruct()) return false;
        return equals(other.getStructType());
    }



    public String toString() {
        return "struct " + name;
    }
}
