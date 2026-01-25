// generated with ast extension for cup
// version 0.8
// 25/0/2026 0:33:27


package rs.ac.bg.etf.pp1.ast;

public class FormPars implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private String I2;
    private ZeroOneSqBracket ZeroOneSqBracket;
    private TypeIdentList TypeIdentList;

    public FormPars (Type Type, String I2, ZeroOneSqBracket ZeroOneSqBracket, TypeIdentList TypeIdentList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.I2=I2;
        this.ZeroOneSqBracket=ZeroOneSqBracket;
        if(ZeroOneSqBracket!=null) ZeroOneSqBracket.setParent(this);
        this.TypeIdentList=TypeIdentList;
        if(TypeIdentList!=null) TypeIdentList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
    }

    public ZeroOneSqBracket getZeroOneSqBracket() {
        return ZeroOneSqBracket;
    }

    public void setZeroOneSqBracket(ZeroOneSqBracket ZeroOneSqBracket) {
        this.ZeroOneSqBracket=ZeroOneSqBracket;
    }

    public TypeIdentList getTypeIdentList() {
        return TypeIdentList;
    }

    public void setTypeIdentList(TypeIdentList TypeIdentList) {
        this.TypeIdentList=TypeIdentList;
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
        if(Type!=null) Type.accept(visitor);
        if(ZeroOneSqBracket!=null) ZeroOneSqBracket.accept(visitor);
        if(TypeIdentList!=null) TypeIdentList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ZeroOneSqBracket!=null) ZeroOneSqBracket.traverseTopDown(visitor);
        if(TypeIdentList!=null) TypeIdentList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ZeroOneSqBracket!=null) ZeroOneSqBracket.traverseBottomUp(visitor);
        if(TypeIdentList!=null) TypeIdentList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormPars(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I2);
        buffer.append("\n");

        if(ZeroOneSqBracket!=null)
            buffer.append(ZeroOneSqBracket.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TypeIdentList!=null)
            buffer.append(TypeIdentList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormPars]");
        return buffer.toString();
    }
}
