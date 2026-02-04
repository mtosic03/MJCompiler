// generated with ast extension for cup
// version 0.8
// 3/1/2026 22:59:45


package rs.ac.bg.etf.pp1.ast;

public class DesignatorRest_third extends DesignatorRest {

    public DesignatorRest_third () {
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
        buffer.append("DesignatorRest_third(\n");

        buffer.append(tab);
        buffer.append(") [DesignatorRest_third]");
        return buffer.toString();
    }
}
