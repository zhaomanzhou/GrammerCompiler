package cn.edu.nwafu.ast;
import cn.edu.nwafu.entity.*;
import cn.edu.nwafu.utils.Result;

import java.util.List;
import java.util.ArrayList;
import java.io.PrintStream;
import java.util.Set;

//抽象语法数的根
public class AST extends Node {
    protected Location source;
    protected Declarations declarations;

    public AST(Location source, Declarations declarations) {
        super();
        this.source = source;
        this.declarations = declarations;
    }

    public Location location() {
        return source;
    }

    public List<TypeDefinition> types() {
        List<TypeDefinition> result = new ArrayList<TypeDefinition>();
        result.addAll(declarations.defstructs());
        result.addAll(declarations.defunions());
        result.addAll(declarations.typedefs());
        return result;
    }

    public List<Entity> entities() {
        List<Entity> result = new ArrayList<Entity>();
        result.addAll(declarations.funcdecls);
        result.addAll(declarations.vardecls);
        result.addAll(declarations.defvars);
        result.addAll(declarations.defuns);
        result.addAll(declarations.constants);
        return result;
    }

    public List<Entity> declarations() {
        List<Entity> result = new ArrayList<Entity>();
        result.addAll(declarations.funcdecls);
        result.addAll(declarations.vardecls);
        return result;
    }

    public List<Entity> definitions() {
        List<Entity> result = new ArrayList<Entity>();
        result.addAll(declarations.defvars);
        result.addAll(declarations.defuns);
        result.addAll(declarations.constants);
        return result;
    }

    public List<Constant> constants() {
        return declarations.constants();
    }

    public List<DefinedVariable> definedVariables() {
        return declarations.defvars();
    }

    public List<DefinedFunction> definedFunctions() {
        return declarations.defuns();
    }
    public List<UndefinedFunction> undefinedFunctions() {
        return declarations.funcdecls();
    }






    protected void _dump(Dumper d) {
        d.printNodeList("variables", definedVariables());
        d.printNodeList("deffunctions", undefinedFunctions());
        d.printNodeList("functions", definedFunctions());
    }

    public void dumpTokens(PrintStream s) {
        for (CflatToken t : source.token()) {
            printPair(t.kindName(), t.dumpedImage(), s);
        }
    }

    static final private int NUM_LEFT_COLUMNS = 24;

    private void printPair(String key, String value, PrintStream s) {
        s.print(key);
        for (int n = NUM_LEFT_COLUMNS - key.length(); n > 0; n--) {
            s.print(" ");
        }
        s.println(value);
    }

    public StmtNode getSingleMainStmt() {
        for (DefinedFunction f : definedFunctions()) {
            if (f.name().equals("main")) {
                if (f.body().stmts().isEmpty()) {
                    return null;
                }
                return f.body().stmts().get(0);
            }
        }
        return null;
    }

    public ExprNode getSingleMainExpr() {
        StmtNode stmt = getSingleMainStmt();
        if (stmt == null) {
            return null;
        }
        else if (stmt instanceof ExprStmtNode) {
            return ((ExprStmtNode)stmt).expr();
        }
        else if (stmt instanceof ReturnNode) {
            return ((ReturnNode)stmt).expr();
        }
        else {
            return null;
        }
    }


    public Result compatible(AST program)
    {
        List<StructNode> defstructs = declarations.defstructs();
        for(StructNode structNode: defstructs)
        {
            boolean finded = false;
            for(StructNode structNode2: program.declarations.defstructs)
            {
                if(structNode.compatible(structNode2).success)
                {
                    finded = true;
                    break;
                }
            }
            if(!finded)
            {
                return Result.ofFalse("Can't find def of structure " + structNode.name + "\n");
            }

        }


        Set<DefinedFunction> defuns = declarations.defuns;
        for(DefinedFunction defFun: defuns)
        {
            boolean finded = false;
            for(DefinedFunction progFun: program.declarations.defuns)
            {
                if(defFun.compatible(progFun).success)
                {
                    finded = true;
                    break;
                }
            }
            if(!finded)
            {
                 return Result.ofFalse("Can't find def of function " + defFun.name()  + "\n");
            }
        }

        return Result.ofSuccess();
    }
}
