// generated with ast extension for cup
// version 0.8
// 25/0/2026 11:33:6


package rs.ac.bg.etf.pp1.ast;

public class DesignatorRest_second extends DesignatorRest {

    private ActParsZeroOne ActParsZeroOne;

    public DesignatorRest_second (ActParsZeroOne ActParsZeroOne) {
        this.ActParsZeroOne=ActParsZeroOne;
        if(ActParsZeroOne!=null) ActParsZeroOne.setParent(this);
    }

    public ActParsZeroOne getActParsZeroOne() {
        return ActParsZeroOne;
    }

    public void setActParsZeroOne(ActParsZeroOne ActParsZeroOne) {
        this.ActParsZeroOne=ActParsZeroOne;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActParsZeroOne!=null) ActParsZeroOne.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActParsZeroOne!=null) ActParsZeroOne.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActParsZeroOne!=null) ActParsZeroOne.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorRest_second(\n");

        if(ActParsZeroOne!=null)
            buffer.append(ActParsZeroOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorRest_second]");
        return buffer.toString();
    }
}
