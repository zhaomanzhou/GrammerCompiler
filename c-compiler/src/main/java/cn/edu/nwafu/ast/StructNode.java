package cn.edu.nwafu.ast;
import cn.edu.nwafu.type.StructType;
import cn.edu.nwafu.type.Type;
import cn.edu.nwafu.type.TypeRef;
import cn.edu.nwafu.utils.Result;
import cn.edu.nwafu.utils.SameLevel;

import java.util.*;

public class StructNode extends CompositeTypeDefinition {
    protected boolean exactParam;
    public StructNode(Location loc, TypeRef ref, String name, List<Slot> membs) {
        super(loc, ref, name, membs);
        this.exactParam = false;
    }
    public StructNode(Location loc, TypeRef ref, String name, List<Slot> membs, boolean exactParam) {
        super(loc, ref, name, membs);
        this.exactParam = exactParam;
    }
    public StructNode(Location loc, TypeRef ref, String name, List<Slot> membs, SameLevel sameLevel) {
        super(loc, ref, name, membs);
        this.sameLevel = sameLevel;
    }

    public String kind() {
        return "struct";
    }

    public boolean isStruct() {
        return true;
    }


    public SameLevel sameLevel = SameLevel.CONTAIN;

    public Result compatible(StructNode program)
    {
        if(!name.equals(program.name)){

            return Result.ofFalse("Structure name is not equal, expect: " + name + ", actual: " + program.name + "\n");
        }
        if(sameLevel == SameLevel.CONTAIN)
        {
            for(Slot defSlot: members)
            {
                boolean finded = false;
                for(Slot pSlot: program.members)
                {
                    if(defSlot.compatible(pSlot, exactParam))
                    {
                        finded = true;
                        break;
                    }
                }
                if(!finded)
                {
                    return Result.ofFalse("cant't find field " + defSlot.name + " of type " + defSlot.typeRef() + " in structure " + name + "\n");
                }
            }
        }
        return Result.ofSuccess();
    }
}
