// generated with ast extension for cup
// version 0.8
// 15/1/2026 0:0:19


package rs.ac.bg.etf.pp1.ast;

public class MethodDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private MethodNameType MethodNameType;
    private FormParList FormParList;
    private VarDeclMore VarDeclMore;
    private StatementList StatementList;

    public MethodDecl (MethodNameType MethodNameType, FormParList FormParList, VarDeclMore VarDeclMore, StatementList StatementList) {
        this.MethodNameType=MethodNameType;
        if(MethodNameType!=null) MethodNameType.setParent(this);
        this.FormParList=FormParList;
        if(FormParList!=null) FormParList.setParent(this);
        this.VarDeclMore=VarDeclMore;
        if(VarDeclMore!=null) VarDeclMore.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public MethodNameType getMethodNameType() {
        return MethodNameType;
    }

    public void setMethodNameType(MethodNameType MethodNameType) {
        this.MethodNameType=MethodNameType;
    }

    public FormParList getFormParList() {
        return FormParList;
    }

    public void setFormParList(FormParList FormParList) {
        this.FormParList=FormParList;
    }

    public VarDeclMore getVarDeclMore() {
        return VarDeclMore;
    }

    public void setVarDeclMore(VarDeclMore VarDeclMore) {
        this.VarDeclMore=VarDeclMore;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
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
        if(MethodNameType!=null) MethodNameType.accept(visitor);
        if(FormParList!=null) FormParList.accept(visitor);
        if(VarDeclMore!=null) VarDeclMore.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodNameType!=null) MethodNameType.traverseTopDown(visitor);
        if(FormParList!=null) FormParList.traverseTopDown(visitor);
        if(VarDeclMore!=null) VarDeclMore.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodNameType!=null) MethodNameType.traverseBottomUp(visitor);
        if(FormParList!=null) FormParList.traverseBottomUp(visitor);
        if(VarDeclMore!=null) VarDeclMore.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDecl(\n");

        if(MethodNameType!=null)
            buffer.append(MethodNameType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParList!=null)
            buffer.append(FormParList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclMore!=null)
            buffer.append(VarDeclMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDecl]");
        return buffer.toString();
    }
}
