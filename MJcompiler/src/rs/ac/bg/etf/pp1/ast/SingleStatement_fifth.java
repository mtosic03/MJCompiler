// generated with ast extension for cup
// version 0.8
// 9/1/2026 18:41:42


package rs.ac.bg.etf.pp1.ast;

public class SingleStatement_fifth extends SingleStatement {

    private ZeroOneExpr ZeroOneExpr;

    public SingleStatement_fifth (ZeroOneExpr ZeroOneExpr) {
        this.ZeroOneExpr=ZeroOneExpr;
        if(ZeroOneExpr!=null) ZeroOneExpr.setParent(this);
    }

    public ZeroOneExpr getZeroOneExpr() {
        return ZeroOneExpr;
    }

    public void setZeroOneExpr(ZeroOneExpr ZeroOneExpr) {
        this.ZeroOneExpr=ZeroOneExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ZeroOneExpr!=null) ZeroOneExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ZeroOneExpr!=null) ZeroOneExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ZeroOneExpr!=null) ZeroOneExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleStatement_fifth(\n");

        if(ZeroOneExpr!=null)
            buffer.append(ZeroOneExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleStatement_fifth]");
        return buffer.toString();
    }
}
