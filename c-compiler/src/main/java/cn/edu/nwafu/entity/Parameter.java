package cn.edu.nwafu.entity;

import cn.edu.nwafu.ast.Dumper;
import cn.edu.nwafu.ast.TypeNode;

public class Parameter extends DefinedVariable
{
    public Parameter(TypeNode type, String name)
    {
        super(false, type, name, null);
    }

    public boolean isParameter()
    {
        return true;
    }

    protected void _dump(Dumper d)
    {
        d.printMember("name", name);
        d.printMember("typeNode", typeNode);
    }

    public boolean compatible(Parameter program, boolean sameParam)
    {
        if (sameParam)
        {
            return name.equals(program.name) && typeNode.typeRef().equals(program.typeNode.typeRef());
        } else
        {
            return typeNode.typeRef().equals(program.typeNode.typeRef());
        }
    }
}
