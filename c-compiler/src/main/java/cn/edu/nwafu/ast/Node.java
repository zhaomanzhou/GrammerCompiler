package cn.edu.nwafu.ast;
import cn.edu.nwafu.utils.Result;

import java.io.PrintStream;

abstract public class Node implements Dumpable {
    public Node() {
    }

    abstract public Location location();

    public void dump() {
        dump(System.out);
    }

    public void dump(PrintStream s) {
        dump(new Dumper(s));
    }

    public void dump(Dumper d) {
        d.printClass(this, location());
        _dump(d);
    }

    abstract protected void _dump(Dumper d);

    public Result compatible(Node program){
        return Result.ofSuccess();
    }
}
