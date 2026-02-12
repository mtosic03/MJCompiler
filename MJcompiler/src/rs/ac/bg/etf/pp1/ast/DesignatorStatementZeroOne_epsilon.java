// generated with ast extension for cup
// version 0.8
// 10/1/2026 14:16:30


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementZeroOne_epsilon extends DesignatorStatementZeroOne {

    public DesignatorStatementZeroOne_epsilon () {
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
        buffer.append("DesignatorStatementZeroOne_epsilon(\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementZeroOne_epsilon]");
        return buffer.toString();
    }
}
