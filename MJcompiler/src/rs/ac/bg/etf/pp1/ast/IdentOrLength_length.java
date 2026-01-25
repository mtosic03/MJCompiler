// generated with ast extension for cup
// version 0.8
// 25/0/2026 11:33:7


package rs.ac.bg.etf.pp1.ast;

public class IdentOrLength_length extends IdentOrLength {

    public IdentOrLength_length () {
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
        buffer.append("IdentOrLength_length(\n");

        buffer.append(tab);
        buffer.append(") [IdentOrLength_length]");
        return buffer.toString();
    }
}
