// generated with ast extension for cup
// version 0.8
// 26/0/2026 19:11:14


package rs.ac.bg.etf.pp1.ast;

public class ZeroOneExpr_epsilon extends ZeroOneExpr {

    public ZeroOneExpr_epsilon () {
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
        buffer.append("ZeroOneExpr_epsilon(\n");

        buffer.append(tab);
        buffer.append(") [ZeroOneExpr_epsilon]");
        return buffer.toString();
    }
}
