// generated with ast extension for cup
// version 0.8
// 5/1/2026 15:39:6


package rs.ac.bg.etf.pp1.ast;

public class DesignatorListRest_expr extends DesignatorListRest {

    private Expr Expr;
    private DesignatorListRest DesignatorListRest;

    public DesignatorListRest_expr (Expr Expr, DesignatorListRest DesignatorListRest) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.DesignatorListRest=DesignatorListRest;
        if(DesignatorListRest!=null) DesignatorListRest.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
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
        if(Expr!=null) Expr.accept(visitor);
        if(DesignatorListRest!=null) DesignatorListRest.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(DesignatorListRest!=null) DesignatorListRest.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(DesignatorListRest!=null) DesignatorListRest.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorListRest_expr(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorListRest!=null)
            buffer.append(DesignatorListRest.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorListRest_expr]");
        return buffer.toString();
    }
}
