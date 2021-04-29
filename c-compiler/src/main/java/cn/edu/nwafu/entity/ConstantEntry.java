package cn.edu.nwafu.entity;
import cn.edu.nwafu.asm.MemoryReference;
import cn.edu.nwafu.asm.Symbol;
import cn.edu.nwafu.asm.ImmediateValue;

public class ConstantEntry {
    protected String value;
    protected Symbol symbol;
    protected MemoryReference memref;
    protected ImmediateValue address;

    public ConstantEntry(String val) {
        value = val;
    }

    public String value() {
        return value;
    }

    public void setSymbol(Symbol sym) {
        this.symbol = sym;
    }

    public Symbol symbol() {
        if (symbol == null) {
            throw new Error("must not happen: symbol == null");
        }
        return symbol;
    }

    public void setMemref(MemoryReference mem) {
        this.memref = mem;
    }

    public MemoryReference memref() {
        if (this.memref == null) {
            throw new Error("must not happen: memref == null");
        }
        return this.memref;
    }

    public void setAddress(ImmediateValue imm) {
        this.address = imm;
    }

    public ImmediateValue address() {
        return this.address;
    }
}
