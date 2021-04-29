package cn.edu.nwafu.sysdep;

import cn.edu.nwafu.ir.IR;

public interface CodeGenerator {
    AssemblyCode generate(IR ir);
}
