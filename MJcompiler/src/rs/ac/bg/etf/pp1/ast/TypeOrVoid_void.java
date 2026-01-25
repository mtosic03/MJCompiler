// generated with ast extension for cup
// version 0.8
// 25/0/2026 11:33:6


package rs.ac.bg.etf.pp1.ast;

public class TypeOrVoid_void extends TypeOrVoid {

    public TypeOrVoid_void () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TypeOrVoid_void(\n");

        buffer.append(tab);
        buffer.append(") [TypeOrVoid_void]");
        return buffer.toString();
    }
}
