package cn.edu.nwafu.ir;
import cn.edu.nwafu.entity.ConstantEntry;
import cn.edu.nwafu.asm.Type;
import cn.edu.nwafu.asm.Operand;
import cn.edu.nwafu.asm.ImmediateValue;
import cn.edu.nwafu.asm.MemoryReference;
import cn.edu.nwafu.asm.Symbol;

public class Str extends Expr {
    protected ConstantEntry entry;

    public Str(Type type, ConstantEntry entry) {
        super(type);
        this.entry = entry;
    }

    public ConstantEntry entry() { return entry; }

    public Symbol symbol() {
        return entry.symbol();
    }

    public boolean isConstant() { return true; }

    public MemoryReference memref() {
        return entry.memref();
    }

    public Operand address() {
        return entry.address();
    }

    public ImmediateValue asmValue() {
        return entry.address();
    }

    public <S,E> E accept(IRVisitor<S,E> visitor) {
        return visitor.visit(this);
    }

    protected void _dump(Dumper d) {
        d.printMember("entry", entry.toString());
    }
}
