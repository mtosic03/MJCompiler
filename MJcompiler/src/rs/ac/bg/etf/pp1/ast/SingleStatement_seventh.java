// generated with ast extension for cup
// version 0.8
// 5/1/2026 15:39:6


package rs.ac.bg.etf.pp1.ast;

public class SingleStatement_seventh extends SingleStatement {

    private Expr Expr;
    private CommaNumConst CommaNumConst;

    public SingleStatement_seventh (Expr Expr, CommaNumConst CommaNumConst) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.CommaNumConst=CommaNumConst;
        if(CommaNumConst!=null) CommaNumConst.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public CommaNumConst getCommaNumConst() {
        return CommaNumConst;
    }

    public void setCommaNumConst(CommaNumConst CommaNumConst) {
        this.CommaNumConst=CommaNumConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(CommaNumConst!=null) CommaNumConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(CommaNumConst!=null) CommaNumConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(CommaNumConst!=null) CommaNumConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleStatement_seventh(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CommaNumConst!=null)
            buffer.append(CommaNumConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleStatement_seventh]");
        return buffer.toString();
    }
}
