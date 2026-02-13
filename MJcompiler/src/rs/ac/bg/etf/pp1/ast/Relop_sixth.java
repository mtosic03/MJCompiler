// generated with ast extension for cup
// version 0.8
// 13/1/2026 17:9:29


package rs.ac.bg.etf.pp1.ast;

public class Relop_sixth extends Relop {

    public Relop_sixth () {
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
        buffer.append("Relop_sixth(\n");

        buffer.append(tab);
        buffer.append(") [Relop_sixth]");
        return buffer.toString();
    }
}
