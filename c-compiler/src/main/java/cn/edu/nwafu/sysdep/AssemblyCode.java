package cn.edu.nwafu.sysdep;
import java.io.PrintStream;

public interface AssemblyCode {
    String toSource();
    void dump();
    void dump(PrintStream s);
}
