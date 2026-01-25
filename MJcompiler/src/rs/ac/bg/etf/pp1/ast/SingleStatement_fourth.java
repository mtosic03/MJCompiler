// generated with ast extension for cup
// version 0.8
// 25/0/2026 0:33:27


package rs.ac.bg.etf.pp1.ast;

public class SingleStatement_fourth extends SingleStatement {

    public SingleStatement_fourth () {
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
        buffer.append("SingleStatement_fourth(\n");

        buffer.append(tab);
        buffer.append(") [SingleStatement_fourth]");
        return buffer.toString();
    }
}
