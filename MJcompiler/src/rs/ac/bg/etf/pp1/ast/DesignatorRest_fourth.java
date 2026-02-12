// generated with ast extension for cup
// version 0.8
// 13/1/2026 0:22:13


package rs.ac.bg.etf.pp1.ast;

public class DesignatorRest_fourth extends DesignatorRest {

    public DesignatorRest_fourth () {
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
        buffer.append("DesignatorRest_fourth(\n");

        buffer.append(tab);
        buffer.append(") [DesignatorRest_fourth]");
        return buffer.toString();
    }
}
