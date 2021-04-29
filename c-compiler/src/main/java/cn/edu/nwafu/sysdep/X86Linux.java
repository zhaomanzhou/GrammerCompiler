package cn.edu.nwafu.sysdep;
import cn.edu.nwafu.utils.ErrorHandler;
import cn.edu.nwafu.type.TypeTable;
import cn.edu.nwafu.asm.Type;

public class X86Linux implements Platform {
    public TypeTable typeTable() {
        return TypeTable.ilp32();
    }

    public CodeGenerator codeGenerator(
            CodeGeneratorOptions opts, ErrorHandler h) {
        return new cn.edu.nwafu.sysdep.x86.CodeGenerator(
                opts, naturalType(), h);
    }

    private Type naturalType() {
        return Type.INT32;
    }

    public Assembler assembler(ErrorHandler h) {
        return new GNUAssembler(h);
    }

    public Linker linker(ErrorHandler h) {
        return new GNULinker(h);
    }
}
