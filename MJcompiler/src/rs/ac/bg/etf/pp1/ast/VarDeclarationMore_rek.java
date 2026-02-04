// generated with ast extension for cup
// version 0.8
// 3/1/2026 22:59:45


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarationMore_rek extends VarDeclarationMore {

    private VarDeclaration VarDeclaration;
    private VarDeclarationMore VarDeclarationMore;

    public VarDeclarationMore_rek (VarDeclaration VarDeclaration, VarDeclarationMore VarDeclarationMore) {
        this.VarDeclaration=VarDeclaration;
        if(VarDeclaration!=null) VarDeclaration.setParent(this);
        this.VarDeclarationMore=VarDeclarationMore;
        if(VarDeclarationMore!=null) VarDeclarationMore.setParent(this);
    }

    public VarDeclaration getVarDeclaration() {
        return VarDeclaration;
    }

    public void setVarDeclaration(VarDeclaration VarDeclaration) {
        this.VarDeclaration=VarDeclaration;
    }

    public VarDeclarationMore getVarDeclarationMore() {
        return VarDeclarationMore;
    }

    public void setVarDeclarationMore(VarDeclarationMore VarDeclarationMore) {
        this.VarDeclarationMore=VarDeclarationMore;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclaration!=null) VarDeclaration.accept(visitor);
        if(VarDeclarationMore!=null) VarDeclarationMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclaration!=null) VarDeclaration.traverseTopDown(visitor);
        if(VarDeclarationMore!=null) VarDeclarationMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclaration!=null) VarDeclaration.traverseBottomUp(visitor);
        if(VarDeclarationMore!=null) VarDeclarationMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarationMore_rek(\n");

        if(VarDeclaration!=null)
            buffer.append(VarDeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclarationMore!=null)
            buffer.append(VarDeclarationMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarationMore_rek]");
        return buffer.toString();
    }
}
