// generated with ast extension for cup
// version 0.8
// 25/0/2026 0:33:27


package rs.ac.bg.etf.pp1.ast;

public class ZeroOneSqBracket_brackets extends ZeroOneSqBracket {

    public ZeroOneSqBracket_brackets () {
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
        buffer.append("ZeroOneSqBracket_brackets(\n");

        buffer.append(tab);
        buffer.append(") [ZeroOneSqBracket_brackets]");
        return buffer.toString();
    }
}
