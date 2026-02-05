// generated with ast extension for cup
// version 0.8
// 5/1/2026 15:39:6


package rs.ac.bg.etf.pp1.ast;

public class DesignatorListRest_epsilon extends DesignatorListRest {

    public DesignatorListRest_epsilon () {
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
        buffer.append("DesignatorListRest_epsilon(\n");

        buffer.append(tab);
        buffer.append(") [DesignatorListRest_epsilon]");
        return buffer.toString();
    }
}
