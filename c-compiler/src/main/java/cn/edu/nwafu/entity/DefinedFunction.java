package cn.edu.nwafu.entity;
import cn.edu.nwafu.ast.Dumper;
import cn.edu.nwafu.ast.StmtNode;
import cn.edu.nwafu.ast.TypeNode;
import cn.edu.nwafu.ast.BlockNode;
import cn.edu.nwafu.type.FunctionTypeRef;
import cn.edu.nwafu.utils.Result;

import java.util.List;

public class DefinedFunction extends Function {
    protected Params params;
    protected BlockNode body;
    protected boolean ignoreParam;
    protected boolean sameParamName;

    public DefinedFunction(boolean priv, TypeNode type,
            String name, Params params, BlockNode body) {
        super(priv, type, name);
        this.params = params;
        this.body = body;
    }

    public DefinedFunction(boolean priv, TypeNode type,
                           String name, Params params, BlockNode body, Boolean ignoreParam, boolean sameParamName) {
        super(priv, type, name);
        this.params = params;
        this.body = body;
        this.ignoreParam = ignoreParam;
        this.sameParamName = sameParamName;
    }

    public boolean isDefined() {
        return true;
    }

    public List<Parameter> parameters() {
        return params.parameters();
    }

    public BlockNode body() {
        return body;
    }





    /**
     * Returns function local variables.
     * Does NOT include paramters.
     * Does NOT include static local variables.
     */

    protected void _dump(Dumper d) {
        d.printMember("name", name);
        d.printMember("isPrivate", isPrivate);
        d.printMember("params", params);
        d.printMember("typeNode", typeNode);
        d.printMember("body", body);
    }


    public Result compatible(DefinedFunction program)
    {
        if(!program.name.equals(name))
        {
            return Result.ofFalse("name is not same ");
        }
        //返回值类型
        if(!((FunctionTypeRef)typeNode.typeRef()).returnType().equals(((FunctionTypeRef)program.typeNode.typeRef()).returnType()))
        {
            return Result.ofFalse("return type is not same");
        }

        //函数参数匹配： 精确匹配，不能多，不能少
        if(!ignoreParam)
        {
            if(!params.compatible(program.params, sameParamName))
            {
                return Result.ofFalse("param name is not same");
            }
        }


        List<DefinedVariable> variables = body.variables();
        for(DefinedVariable definedVariable: variables)
        {
            boolean finded = false;
            for(DefinedVariable progVar: program.body.variables())
            {
                if(definedVariable.compatible(progVar))
                {
                    finded = true;
                    break;
                }
            }
            if(!finded)
            {
                return Result.ofFalse("can't find define of variable " + definedVariable.name );
            }
        }




        int i = 0;
        int j = 0;

        while (i < body.stmts().size())
        {
            if(j == program.body.stmts().size()) break;
            for (;j < program.body.stmts().size(); )
            {
                if(body.stmts().get(i).compatible(program.body.stmts().get(j)))
                {
                    i++;
                    j++;
                    break;
                }else
                {
                    j++;
                }
            }

        }

        if(i < body.stmts().size())
        {
            return Result.ofFalse("can't find define of statement of " + body.stmts().get(i));
        }




        return Result.ofSuccess();
    }

}
