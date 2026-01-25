// generated with ast extension for cup
// version 0.8
// 25/0/2026 0:33:27


package rs.ac.bg.etf.pp1.ast;

public class AssignNumConstMore_rek extends AssignNumConstMore {

    private String I1;
    private AssignNumConst AssignNumConst;
    private AssignNumConstMore AssignNumConstMore;

    public AssignNumConstMore_rek (String I1, AssignNumConst AssignNumConst, AssignNumConstMore AssignNumConstMore) {
        this.I1=I1;
        this.AssignNumConst=AssignNumConst;
        if(AssignNumConst!=null) AssignNumConst.setParent(this);
        this.AssignNumConstMore=AssignNumConstMore;
        if(AssignNumConstMore!=null) AssignNumConstMore.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public AssignNumConst getAssignNumConst() {
        return AssignNumConst;
    }

    public void setAssignNumConst(AssignNumConst AssignNumConst) {
        this.AssignNumConst=AssignNumConst;
    }

    public AssignNumConstMore getAssignNumConstMore() {
        return AssignNumConstMore;
    }

    public void setAssignNumConstMore(AssignNumConstMore AssignNumConstMore) {
        this.AssignNumConstMore=AssignNumConstMore;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AssignNumConst!=null) AssignNumConst.accept(visitor);
        if(AssignNumConstMore!=null) AssignNumConstMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AssignNumConst!=null) AssignNumConst.traverseTopDown(visitor);
        if(AssignNumConstMore!=null) AssignNumConstMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AssignNumConst!=null) AssignNumConst.traverseBottomUp(visitor);
        if(AssignNumConstMore!=null) AssignNumConstMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AssignNumConstMore_rek(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(AssignNumConst!=null)
            buffer.append(AssignNumConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AssignNumConstMore!=null)
            buffer.append(AssignNumConstMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AssignNumConstMore_rek]");
        return buffer.toString();
    }
}
