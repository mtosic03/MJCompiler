// generated with ast extension for cup
// version 0.8
// 13/1/2026 0:22:13


package rs.ac.bg.etf.pp1.ast;

public class EnumMember_assign extends EnumMember {

    private String I1;
    private Integer N2;

    public EnumMember_assign (String I1, Integer N2) {
        this.I1=I1;
        this.N2=N2;
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public Integer getN2() {
        return N2;
    }

    public void setN2(Integer N2) {
        this.N2=N2;
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
        buffer.append("EnumMember_assign(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        buffer.append(" "+tab+N2);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumMember_assign]");
        return buffer.toString();
    }
}
