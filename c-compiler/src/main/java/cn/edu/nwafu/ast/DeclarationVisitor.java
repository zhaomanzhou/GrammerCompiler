package cn.edu.nwafu.ast;

public interface DeclarationVisitor<T> {
    public T visit(StructNode struct);
    public T visit(UnionNode union);
    public T visit(TypedefNode typedef);
}
