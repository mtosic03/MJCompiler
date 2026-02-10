// generated with ast extension for cup
// version 0.8
// 9/1/2026 18:41:42


package rs.ac.bg.etf.pp1.ast;

public class CondFact implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private AddExpr AddExpr;
    private RelopExprZeroOne RelopExprZeroOne;

    public CondFact (AddExpr AddExpr, RelopExprZeroOne RelopExprZeroOne) {
        this.AddExpr=AddExpr;
        if(AddExpr!=null) AddExpr.setParent(this);
        this.RelopExprZeroOne=RelopExprZeroOne;
        if(RelopExprZeroOne!=null) RelopExprZeroOne.setParent(this);
    }

    public AddExpr getAddExpr() {
        return AddExpr;
    }

    public void setAddExpr(AddExpr AddExpr) {
        this.AddExpr=AddExpr;
    }

    public RelopExprZeroOne getRelopExprZeroOne() {
        return RelopExprZeroOne;
    }

    public void setRelopExprZeroOne(RelopExprZeroOne RelopExprZeroOne) {
        this.RelopExprZeroOne=RelopExprZeroOne;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AddExpr!=null) AddExpr.accept(visitor);
        if(RelopExprZeroOne!=null) RelopExprZeroOne.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AddExpr!=null) AddExpr.traverseTopDown(visitor);
        if(RelopExprZeroOne!=null) RelopExprZeroOne.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AddExpr!=null) AddExpr.traverseBottomUp(visitor);
        if(RelopExprZeroOne!=null) RelopExprZeroOne.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondFact(\n");

        if(AddExpr!=null)
            buffer.append(AddExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(RelopExprZeroOne!=null)
            buffer.append(RelopExprZeroOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFact]");
        return buffer.toString();
    }
}
