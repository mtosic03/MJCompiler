// generated with ast extension for cup
// version 0.8
// 3/1/2026 22:59:45


package rs.ac.bg.etf.pp1.ast;

public class EnumMemberList_first extends EnumMemberList {

    private EnumMember EnumMember;

    public EnumMemberList_first (EnumMember EnumMember) {
        this.EnumMember=EnumMember;
        if(EnumMember!=null) EnumMember.setParent(this);
    }

    public EnumMember getEnumMember() {
        return EnumMember;
    }

    public void setEnumMember(EnumMember EnumMember) {
        this.EnumMember=EnumMember;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EnumMember!=null) EnumMember.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumMember!=null) EnumMember.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumMember!=null) EnumMember.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumMemberList_first(\n");

        if(EnumMember!=null)
            buffer.append(EnumMember.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumMemberList_first]");
        return buffer.toString();
    }
}
