// generated with ast extension for cup
// version 0.8
// 3/1/2026 22:59:45


package rs.ac.bg.etf.pp1.ast;

public class SingleStatement_ninth extends SingleStatement {

    private DesignatorStatementZeroOne DesignatorStatementZeroOne;
    private ConditionZeroOne ConditionZeroOne;
    private DesignatorStatementZeroOne DesignatorStatementZeroOne1;
    private Statement Statement;

    public SingleStatement_ninth (DesignatorStatementZeroOne DesignatorStatementZeroOne, ConditionZeroOne ConditionZeroOne, DesignatorStatementZeroOne DesignatorStatementZeroOne1, Statement Statement) {
        this.DesignatorStatementZeroOne=DesignatorStatementZeroOne;
        if(DesignatorStatementZeroOne!=null) DesignatorStatementZeroOne.setParent(this);
        this.ConditionZeroOne=ConditionZeroOne;
        if(ConditionZeroOne!=null) ConditionZeroOne.setParent(this);
        this.DesignatorStatementZeroOne1=DesignatorStatementZeroOne1;
        if(DesignatorStatementZeroOne1!=null) DesignatorStatementZeroOne1.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public DesignatorStatementZeroOne getDesignatorStatementZeroOne() {
        return DesignatorStatementZeroOne;
    }

    public void setDesignatorStatementZeroOne(DesignatorStatementZeroOne DesignatorStatementZeroOne) {
        this.DesignatorStatementZeroOne=DesignatorStatementZeroOne;
    }

    public ConditionZeroOne getConditionZeroOne() {
        return ConditionZeroOne;
    }

    public void setConditionZeroOne(ConditionZeroOne ConditionZeroOne) {
        this.ConditionZeroOne=ConditionZeroOne;
    }

    public DesignatorStatementZeroOne getDesignatorStatementZeroOne1() {
        return DesignatorStatementZeroOne1;
    }

    public void setDesignatorStatementZeroOne1(DesignatorStatementZeroOne DesignatorStatementZeroOne1) {
        this.DesignatorStatementZeroOne1=DesignatorStatementZeroOne1;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStatementZeroOne!=null) DesignatorStatementZeroOne.accept(visitor);
        if(ConditionZeroOne!=null) ConditionZeroOne.accept(visitor);
        if(DesignatorStatementZeroOne1!=null) DesignatorStatementZeroOne1.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatementZeroOne!=null) DesignatorStatementZeroOne.traverseTopDown(visitor);
        if(ConditionZeroOne!=null) ConditionZeroOne.traverseTopDown(visitor);
        if(DesignatorStatementZeroOne1!=null) DesignatorStatementZeroOne1.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatementZeroOne!=null) DesignatorStatementZeroOne.traverseBottomUp(visitor);
        if(ConditionZeroOne!=null) ConditionZeroOne.traverseBottomUp(visitor);
        if(DesignatorStatementZeroOne1!=null) DesignatorStatementZeroOne1.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleStatement_ninth(\n");

        if(DesignatorStatementZeroOne!=null)
            buffer.append(DesignatorStatementZeroOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionZeroOne!=null)
            buffer.append(ConditionZeroOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStatementZeroOne1!=null)
            buffer.append(DesignatorStatementZeroOne1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleStatement_ninth]");
        return buffer.toString();
    }
}
