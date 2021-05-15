package cn.edu.nwafu.type;

import cn.edu.nwafu.entity.VarName;

public class ArrayTypeRef extends TypeRef {
    protected TypeRef baseType;
    protected long length;
    static final protected long undefined = -1;

    public ArrayTypeRef(TypeRef baseType) {
        super(baseType.location());
        this.baseType = baseType;
        this.length = undefined;
    }

    public ArrayTypeRef(TypeRef baseType, long length) {
        super(baseType.location());
        if (length < 0) throw new Error("negative array length");
        this.baseType = baseType;
        this.length = length;
    }
    public ArrayTypeRef(TypeRef baseType, VarName varName) {
        super(baseType.location());
        if (length < 0) throw new Error("negative array length");
        if(varName.getArrayLen() > 0)
        {
            this.length = varName.getArrayLen();
        }
        if(varName.getSubVarName() != null)
        {
            this.baseType = new ArrayTypeRef(baseType, varName.getSubVarName());
        }
        {
            this.baseType = baseType;
        }
    }

    public boolean isArray() {
        return true;
    }

    public boolean equals(Object other) {
        return (other instanceof ArrayTypeRef) &&
            (length == ((ArrayTypeRef)other).length);
    }

    public TypeRef baseType() {
        return baseType;
    }

    public long length() {
        return length;
    }

    public boolean isLengthUndefined() {
        return (length == undefined);
    }

    public String toString() {
        return baseType.toString()
               + "["
               + (length == undefined ? "" : "" + length)
               + "]";
    }
}
