package cn.edu.nwafu.sysdep;
import cn.edu.nwafu.exception.IPCException;

public interface Assembler {
    void assemble(String srcPath, String destPath,
            AssemblerOptions opts) throws IPCException;
}
