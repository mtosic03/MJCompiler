// generated with ast extension for cup
// version 0.8
// 2/1/2026 0:0:47


package rs.ac.bg.etf.pp1.ast;

public class DesignatorListRest_dot extends DesignatorListRest {

    private IdentOrLength IdentOrLength;
    private DesignatorListRest DesignatorListRest;

    public DesignatorListRest_dot (IdentOrLength IdentOrLength, DesignatorListRest DesignatorListRest) {
        this.IdentOrLength=IdentOrLength;
        if(IdentOrLength!=null) IdentOrLength.setParent(this);
        this.DesignatorListRest=DesignatorListRest;
        if(DesignatorListRest!=null) DesignatorListRest.setParent(this);
    }

    public IdentOrLength getIdentOrLength() {
        return IdentOrLength;
    }

    public void setIdentOrLength(IdentOrLength IdentOrLength) {
        this.IdentOrLength=IdentOrLength;
    }

    public DesignatorListRest getDesignatorListRest() {
        return DesignatorListRest;
    }

    public void setDesignatorListRest(DesignatorListRest DesignatorListRest) {
        this.DesignatorListRest=DesignatorListRest;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentOrLength!=null) IdentOrLength.accept(visitor);
        if(DesignatorListRest!=null) DesignatorListRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentOrLength!=null) IdentOrLength.traverseTopDown(visitor);
        if(DesignatorListRest!=null) DesignatorListRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentOrLength!=null) IdentOrLength.traverseBottomUp(visitor);
        if(DesignatorListRest!=null) DesignatorListRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorListRest_dot(\n");

        if(IdentOrLength!=null)
            buffer.append(IdentOrLength.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorListRest!=null)
            buffer.append(DesignatorListRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorListRest_dot]");
        return buffer.toString();
    }
}
