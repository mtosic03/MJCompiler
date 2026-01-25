// generated with ast extension for cup
// version 0.8
// 25/0/2026 0:33:27


package rs.ac.bg.etf.pp1.ast;

public class IdentList_rek extends IdentList {

    private String I1;
    private ZeroOneSqBracket ZeroOneSqBracket;
    private IdentList IdentList;

    public IdentList_rek (String I1, ZeroOneSqBracket ZeroOneSqBracket, IdentList IdentList) {
        this.I1=I1;
        this.ZeroOneSqBracket=ZeroOneSqBracket;
        if(ZeroOneSqBracket!=null) ZeroOneSqBracket.setParent(this);
        this.IdentList=IdentList;
        if(IdentList!=null) IdentList.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public ZeroOneSqBracket getZeroOneSqBracket() {
        return ZeroOneSqBracket;
    }

    public void setZeroOneSqBracket(ZeroOneSqBracket ZeroOneSqBracket) {
        this.ZeroOneSqBracket=ZeroOneSqBracket;
    }

    public IdentList getIdentList() {
        return IdentList;
    }

    public void setIdentList(IdentList IdentList) {
        this.IdentList=IdentList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ZeroOneSqBracket!=null) ZeroOneSqBracket.accept(visitor);
        if(IdentList!=null) IdentList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ZeroOneSqBracket!=null) ZeroOneSqBracket.traverseTopDown(visitor);
        if(IdentList!=null) IdentList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ZeroOneSqBracket!=null) ZeroOneSqBracket.traverseBottomUp(visitor);
        if(IdentList!=null) IdentList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IdentList_rek(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(ZeroOneSqBracket!=null)
            buffer.append(ZeroOneSqBracket.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IdentList!=null)
            buffer.append(IdentList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IdentList_rek]");
        return buffer.toString();
    }
}
