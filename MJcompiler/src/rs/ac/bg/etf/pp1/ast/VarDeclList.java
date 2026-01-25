// generated with ast extension for cup
// version 0.8
// 25/0/2026 0:33:27


package rs.ac.bg.etf.pp1.ast;

public class VarDeclList implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private String I2;
    private ZeroOneSqBracket ZeroOneSqBracket;
    private IdentList IdentList;

    public VarDeclList (Type Type, String I2, ZeroOneSqBracket ZeroOneSqBracket, IdentList IdentList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.I2=I2;
        this.ZeroOneSqBracket=ZeroOneSqBracket;
        if(ZeroOneSqBracket!=null) ZeroOneSqBracket.setParent(this);
        this.IdentList=IdentList;
        if(IdentList!=null) IdentList.setParent(this);
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

    public IdentList getIdentList() {
        return IdentList;
    }

    public void setIdentList(IdentList IdentList) {
        this.IdentList=IdentList;
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
        if(IdentList!=null) IdentList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ZeroOneSqBracket!=null) ZeroOneSqBracket.traverseTopDown(visitor);
        if(IdentList!=null) IdentList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ZeroOneSqBracket!=null) ZeroOneSqBracket.traverseBottomUp(visitor);
        if(IdentList!=null) IdentList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclList(\n");

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

        if(IdentList!=null)
            buffer.append(IdentList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclList]");
        return buffer.toString();
    }
}
