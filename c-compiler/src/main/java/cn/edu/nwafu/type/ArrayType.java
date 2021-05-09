package cn.edu.nwafu.type;

public class ArrayType extends Type {
    protected Type baseType;
    protected long length;
    protected long pointerSize;
    static final protected long undefined = -1;

    public ArrayType(Type baseType, long pointerSize) {
        this(baseType, undefined, pointerSize);
    }

    public ArrayType(Type baseType, long length, long pointerSize) {
        this.baseType = baseType;
        this.length = length;
        this.pointerSize = pointerSize;
    }

    public boolean isArray() { return true; }

    public boolean isAllocatedArray() {
        return length != undefined &&
            (!baseType.isArray() || baseType.isAllocatedArray());
    }

    public boolean isIncompleteArray() {
        if (! baseType.isArray()) return false;
        return !baseType.isAllocatedArray();
    }

    public Type baseType() {
        return baseType;
    }

    public long length() {
        return length;
    }





    public boolean equals(Object other) {
        if (! (other instanceof ArrayType)) return false;
        ArrayType type = (ArrayType)other;
        return (baseType.equals(type.baseType) && length == type.length);
    }

    public boolean isSameType(Type other) {
        // length is not important
        if (!other.isPointer() && !other.isArray()) return false;
        return baseType.isSameType(other.baseType());
    }


    public boolean isCastableTo(Type target) {
        return target.isPointer() || target.isArray();
    }

    public String toString() {
        if (length < 0) {
            return baseType.toString() + "[]";
        }
        else {
            return baseType.toString() + "[" + length + "]";
        }
    }
}
