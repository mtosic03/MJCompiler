// generated with ast extension for cup
// version 0.8
// 15/1/2026 12:45:7


package rs.ac.bg.etf.pp1.ast;

public class SingleStatement_eighth extends SingleStatement {

    private Expr Expr;
    private SwitchMarker SwitchMarker;
    private CaseList CaseList;

    public SingleStatement_eighth (Expr Expr, SwitchMarker SwitchMarker, CaseList CaseList) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.SwitchMarker=SwitchMarker;
        if(SwitchMarker!=null) SwitchMarker.setParent(this);
        this.CaseList=CaseList;
        if(CaseList!=null) CaseList.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public SwitchMarker getSwitchMarker() {
        return SwitchMarker;
    }

    public void setSwitchMarker(SwitchMarker SwitchMarker) {
        this.SwitchMarker=SwitchMarker;
    }

    public CaseList getCaseList() {
        return CaseList;
    }

    public void setCaseList(CaseList CaseList) {
        this.CaseList=CaseList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(SwitchMarker!=null) SwitchMarker.accept(visitor);
        if(CaseList!=null) CaseList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(SwitchMarker!=null) SwitchMarker.traverseTopDown(visitor);
        if(CaseList!=null) CaseList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(SwitchMarker!=null) SwitchMarker.traverseBottomUp(visitor);
        if(CaseList!=null) CaseList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleStatement_eighth(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SwitchMarker!=null)
            buffer.append(SwitchMarker.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CaseList!=null)
            buffer.append(CaseList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleStatement_eighth]");
        return buffer.toString();
    }
}
