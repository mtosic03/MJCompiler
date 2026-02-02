// generated with ast extension for cup
// version 0.8
// 2/1/2026 0:0:47


package rs.ac.bg.etf.pp1.ast;

public class RelopExprZeroOne_epsilon extends RelopExprZeroOne {

    public RelopExprZeroOne_epsilon () {
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
        buffer.append("RelopExprZeroOne_epsilon(\n");

        buffer.append(tab);
        buffer.append(") [RelopExprZeroOne_epsilon]");
        return buffer.toString();
    }
}
