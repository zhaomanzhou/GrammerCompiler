package cn.edu.nwafu.sysdep;
import cn.edu.nwafu.type.TypeTable;
import cn.edu.nwafu.utils.ErrorHandler;

public interface Platform {
    TypeTable typeTable();
    CodeGenerator codeGenerator(CodeGeneratorOptions opts, ErrorHandler h);
    Assembler assembler(ErrorHandler h);
    Linker linker(ErrorHandler h);
}
