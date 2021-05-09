package cn.edu.nwafu.type;
import cn.edu.nwafu.exception.SemanticError;

/**
 * 类型的定义
 *
 * struct point { int x; int y; } 代表类型的定义
 *
 * struct point  类型的名称(TypeRef)
 *
 *
 */
public abstract class Type {
    static final public long sizeUnknown = -1;



    abstract public boolean isSameType(Type other);

    public boolean isVoid() { return false; }
    public boolean isInt() { return false; }
    public boolean isInteger() { return false; }
    public boolean isSigned()
            { throw new Error("#isSigned for non-integer type"); }
    public boolean isPointer() { return false; }
    public boolean isArray() { return false; }
    public boolean isCompositeType() { return false; }
    public boolean isStruct() { return false; }
    public boolean isUnion() { return false; }
    public boolean isUserType() { return false; }
    public boolean isFunction() { return false; }

    // Ability methods (unary)
    public boolean isAllocatedArray() { return false; }
    public boolean isIncompleteArray() { return false; }
    public boolean isScalar() { return false; }
    public boolean isCallable() { return false; }



    public Type baseType() {
        throw new SemanticError("#baseType called for undereferable type");
    }

    // Cast methods
    public IntegerType getIntegerType() { return (IntegerType)this; }
    public PointerType getPointerType() { return (PointerType)this; }
    public FunctionType getFunctionType() { return (FunctionType)this; }
    public StructType getStructType() { return (StructType)this; }
    public UnionType getUnionType() { return (UnionType)this; }
    public CompositeType getCompositeType() { return (CompositeType)this; }
    public ArrayType getArrayType() { return (ArrayType)this; }
}
