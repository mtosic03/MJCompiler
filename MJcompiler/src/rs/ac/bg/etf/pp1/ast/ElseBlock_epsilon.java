// generated with ast extension for cup
// version 0.8
// 25/0/2026 11:33:6


package rs.ac.bg.etf.pp1.ast;

public class ElseBlock_epsilon extends ElseBlock {

    public ElseBlock_epsilon () {
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
        buffer.append("ElseBlock_epsilon(\n");

        buffer.append(tab);
        buffer.append(") [ElseBlock_epsilon]");
        return buffer.toString();
    }
}
