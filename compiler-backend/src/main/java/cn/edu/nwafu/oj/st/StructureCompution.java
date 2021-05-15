package cn.edu.nwafu.oj.st;

import cn.edu.nwafu.ast.AST;
import cn.edu.nwafu.def.Parser;
import cn.edu.nwafu.parser.ParseException;
import cn.edu.nwafu.utils.Result;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/5/10 1:15 下午
 */
@Component
public class StructureCompution
{
    public Result compute(String problemFolder, String userCodeFolder) throws IOException, ParseException
    {
        try(
                FileInputStream defStream = new FileInputStream(problemFolder + "def.c");
                FileInputStream userCodeStream = new FileInputStream(userCodeFolder + "userCleanReplace.c");
        )
        {
            Parser defParser = new Parser(defStream);
            AST defAst = defParser.compilation_unit();
            cn.edu.nwafu.parser.Parser progPaser = new cn.edu.nwafu.parser.Parser(userCodeStream);
            AST progAst = progPaser.compilation_unit();
            return defAst.compatible(progAst);
        }catch (Exception e)
        {
            throw e;
        }
    }
}
