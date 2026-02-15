// generated with ast extension for cup
// version 0.8
// 15/1/2026 12:45:7


package rs.ac.bg.etf.pp1.ast;

public class FactorSub_des extends FactorSub {

    private Designator Designator;
    private ActParsBracketsZeroOne ActParsBracketsZeroOne;

    public FactorSub_des (Designator Designator, ActParsBracketsZeroOne ActParsBracketsZeroOne) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.ActParsBracketsZeroOne=ActParsBracketsZeroOne;
        if(ActParsBracketsZeroOne!=null) ActParsBracketsZeroOne.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public ActParsBracketsZeroOne getActParsBracketsZeroOne() {
        return ActParsBracketsZeroOne;
    }

    public void setActParsBracketsZeroOne(ActParsBracketsZeroOne ActParsBracketsZeroOne) {
        this.ActParsBracketsZeroOne=ActParsBracketsZeroOne;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(ActParsBracketsZeroOne!=null) ActParsBracketsZeroOne.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(ActParsBracketsZeroOne!=null) ActParsBracketsZeroOne.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(ActParsBracketsZeroOne!=null) ActParsBracketsZeroOne.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorSub_des(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActParsBracketsZeroOne!=null)
            buffer.append(ActParsBracketsZeroOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorSub_des]");
        return buffer.toString();
    }
}
