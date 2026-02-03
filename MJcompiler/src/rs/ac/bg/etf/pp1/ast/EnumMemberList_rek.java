// generated with ast extension for cup
// version 0.8
// 3/1/2026 20:10:20


package rs.ac.bg.etf.pp1.ast;

public class EnumMemberList_rek extends EnumMemberList {

    private EnumMember EnumMember;
    private EnumMemberList EnumMemberList;

    public EnumMemberList_rek (EnumMember EnumMember, EnumMemberList EnumMemberList) {
        this.EnumMember=EnumMember;
        if(EnumMember!=null) EnumMember.setParent(this);
        this.EnumMemberList=EnumMemberList;
        if(EnumMemberList!=null) EnumMemberList.setParent(this);
    }

    public EnumMember getEnumMember() {
        return EnumMember;
    }

    public void setEnumMember(EnumMember EnumMember) {
        this.EnumMember=EnumMember;
    }

    public EnumMemberList getEnumMemberList() {
        return EnumMemberList;
    }

    public void setEnumMemberList(EnumMemberList EnumMemberList) {
        this.EnumMemberList=EnumMemberList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EnumMember!=null) EnumMember.accept(visitor);
        if(EnumMemberList!=null) EnumMemberList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumMember!=null) EnumMember.traverseTopDown(visitor);
        if(EnumMemberList!=null) EnumMemberList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumMember!=null) EnumMember.traverseBottomUp(visitor);
        if(EnumMemberList!=null) EnumMemberList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumMemberList_rek(\n");

        if(EnumMember!=null)
            buffer.append(EnumMember.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EnumMemberList!=null)
            buffer.append(EnumMemberList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumMemberList_rek]");
        return buffer.toString();
    }
}
