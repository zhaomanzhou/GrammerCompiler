package cn.edu.nwafu.oj.st;

import cn.edu.nwafu.ast.AST;
import cn.edu.nwafu.def.Parser;
import cn.edu.nwafu.parser.ParseException;
import cn.edu.nwafu.utils.Result;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        if(!new File(problemFolder + "def.c").exists()){
            return Result.ofSuccess();
        }
        if(Files.readAllBytes(Paths.get(problemFolder , "def.c")).length == 0)
        {
            return Result.ofSuccess();
        }
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
