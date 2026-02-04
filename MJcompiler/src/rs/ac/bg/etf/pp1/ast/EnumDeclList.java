// generated with ast extension for cup
// version 0.8
// 3/1/2026 22:59:45


package rs.ac.bg.etf.pp1.ast;

public class EnumDeclList implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private EnumDeclName EnumDeclName;
    private EnumMemberList EnumMemberList;

    public EnumDeclList (EnumDeclName EnumDeclName, EnumMemberList EnumMemberList) {
        this.EnumDeclName=EnumDeclName;
        if(EnumDeclName!=null) EnumDeclName.setParent(this);
        this.EnumMemberList=EnumMemberList;
        if(EnumMemberList!=null) EnumMemberList.setParent(this);
    }

    public EnumDeclName getEnumDeclName() {
        return EnumDeclName;
    }

    public void setEnumDeclName(EnumDeclName EnumDeclName) {
        this.EnumDeclName=EnumDeclName;
    }

    public EnumMemberList getEnumMemberList() {
        return EnumMemberList;
    }

    public void setEnumMemberList(EnumMemberList EnumMemberList) {
        this.EnumMemberList=EnumMemberList;
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
        if(EnumDeclName!=null) EnumDeclName.accept(visitor);
        if(EnumMemberList!=null) EnumMemberList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumDeclName!=null) EnumDeclName.traverseTopDown(visitor);
        if(EnumMemberList!=null) EnumMemberList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumDeclName!=null) EnumDeclName.traverseBottomUp(visitor);
        if(EnumMemberList!=null) EnumMemberList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumDeclList(\n");

        if(EnumDeclName!=null)
            buffer.append(EnumDeclName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EnumMemberList!=null)
            buffer.append(EnumMemberList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumDeclList]");
        return buffer.toString();
    }
}
