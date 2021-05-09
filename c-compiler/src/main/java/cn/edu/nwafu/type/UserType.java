package cn.edu.nwafu.type;
import cn.edu.nwafu.ast.Location;
import cn.edu.nwafu.ast.TypeNode;

/**
 * 用户自定义类型
 * typeof  开始的定义
 */
public class UserType extends NamedType {
    protected TypeNode real;

    public UserType(String name, TypeNode real, Location loc) {
        super(name, loc);
        this.real = real;
    }

    public Type realType() {
        return real.type();
    }

    public String toString() {
        return name;
    }

    //
    // Forward methods to real type.
    //


    public boolean isVoid() { return realType().isVoid(); }
    public boolean isInt() { return realType().isInt(); }
    public boolean isInteger() { return realType().isInteger(); }
    public boolean isSigned() { return realType().isSigned(); }
    public boolean isPointer() { return realType().isPointer(); }
    public boolean isArray() { return realType().isArray(); }
    public boolean isAllocatedArray() { return realType().isAllocatedArray(); }
    public boolean isCompositeType() { return realType().isCompositeType(); }
    public boolean isStruct() { return realType().isStruct(); }
    public boolean isUnion() { return realType().isUnion(); }
    public boolean isUserType() { return true; }
    public boolean isFunction() { return realType().isFunction(); }

    public boolean isCallable() { return realType().isCallable(); }
    public boolean isScalar() { return realType().isScalar(); }

    public Type baseType() { return realType().baseType(); }

    public boolean isSameType(Type other) {
        return realType().isSameType(other);
    }



    public IntegerType getIntegerType() { return realType().getIntegerType(); }
    public CompositeType getCompositeType() { return realType().getCompositeType(); }
    public StructType getStructType() { return realType().getStructType(); }
    public UnionType getUnionType() { return realType().getUnionType(); }
    public ArrayType getArrayType() { return realType().getArrayType(); }
    public PointerType getPointerType() { return realType().getPointerType(); }
    public FunctionType getFunctionType() { return realType().getFunctionType(); }
}
