// generated with ast extension for cup
// version 0.8
// 13/1/2026 0:22:13


package rs.ac.bg.etf.pp1.ast;

public class SingleStatement_second extends SingleStatement {

    private Condition Condition;
    private Statement Statement;
    private ElseMarker ElseMarker;
    private ElseBlock ElseBlock;

    public SingleStatement_second (Condition Condition, Statement Statement, ElseMarker ElseMarker, ElseBlock ElseBlock) {
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.ElseMarker=ElseMarker;
        if(ElseMarker!=null) ElseMarker.setParent(this);
        this.ElseBlock=ElseBlock;
        if(ElseBlock!=null) ElseBlock.setParent(this);
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public ElseMarker getElseMarker() {
        return ElseMarker;
    }

    public void setElseMarker(ElseMarker ElseMarker) {
        this.ElseMarker=ElseMarker;
    }

    public ElseBlock getElseBlock() {
        return ElseBlock;
    }

    public void setElseBlock(ElseBlock ElseBlock) {
        this.ElseBlock=ElseBlock;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Condition!=null) Condition.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(ElseMarker!=null) ElseMarker.accept(visitor);
        if(ElseBlock!=null) ElseBlock.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(ElseMarker!=null) ElseMarker.traverseTopDown(visitor);
        if(ElseBlock!=null) ElseBlock.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(ElseMarker!=null) ElseMarker.traverseBottomUp(visitor);
        if(ElseBlock!=null) ElseBlock.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleStatement_second(\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseMarker!=null)
            buffer.append(ElseMarker.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseBlock!=null)
            buffer.append(ElseBlock.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleStatement_second]");
        return buffer.toString();
    }
}
