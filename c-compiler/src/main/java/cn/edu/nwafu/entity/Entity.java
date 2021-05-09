package cn.edu.nwafu.entity;
import cn.edu.nwafu.ast.Dumpable;
import cn.edu.nwafu.ast.Dumper;
import cn.edu.nwafu.ast.TypeNode;
import cn.edu.nwafu.type.Type;
import cn.edu.nwafu.ast.Location;
import cn.edu.nwafu.ast.ExprNode;

abstract public class Entity
        implements Dumpable
{
    protected String name;
    //是否是static
    protected boolean isPrivate;
    protected TypeNode typeNode;


    public Entity(boolean priv, TypeNode type, String name) {
        this.name = name;
        this.isPrivate = priv;
        this.typeNode = type;
    }

    public String name() {
        return name;
    }




    public boolean isConstant() { return false; }

    public ExprNode value() {
        throw new Error("Entity#value");
    }

    public boolean isParameter() { return false; }

    public boolean isPrivate() {
        return isPrivate;
    }

    public TypeNode typeNode() {
        return typeNode;
    }

    public Type type() {
        return typeNode.type();
    }






    public Location location() {
        return typeNode.location();
    }


    public void dump(Dumper d) {
        d.printClass(this, location());
        _dump(d);
    }

    abstract protected void _dump(Dumper d);
}
