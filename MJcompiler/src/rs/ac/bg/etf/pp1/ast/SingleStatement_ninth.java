// generated with ast extension for cup
// version 0.8
// 15/1/2026 12:45:7


package rs.ac.bg.etf.pp1.ast;

public class SingleStatement_ninth extends SingleStatement {

    private DesignatorStatementZeroOne DesignatorStatementZeroOne;
    private ForCondMarker ForCondMarker;
    private ConditionZeroOne ConditionZeroOne;
    private ForIncrMarker ForIncrMarker;
    private DesignatorStatementZeroOne DesignatorStatementZeroOne1;
    private ForBodyMarker ForBodyMarker;
    private Statement Statement;

    public SingleStatement_ninth (DesignatorStatementZeroOne DesignatorStatementZeroOne, ForCondMarker ForCondMarker, ConditionZeroOne ConditionZeroOne, ForIncrMarker ForIncrMarker, DesignatorStatementZeroOne DesignatorStatementZeroOne1, ForBodyMarker ForBodyMarker, Statement Statement) {
        this.DesignatorStatementZeroOne=DesignatorStatementZeroOne;
        if(DesignatorStatementZeroOne!=null) DesignatorStatementZeroOne.setParent(this);
        this.ForCondMarker=ForCondMarker;
        if(ForCondMarker!=null) ForCondMarker.setParent(this);
        this.ConditionZeroOne=ConditionZeroOne;
        if(ConditionZeroOne!=null) ConditionZeroOne.setParent(this);
        this.ForIncrMarker=ForIncrMarker;
        if(ForIncrMarker!=null) ForIncrMarker.setParent(this);
        this.DesignatorStatementZeroOne1=DesignatorStatementZeroOne1;
        if(DesignatorStatementZeroOne1!=null) DesignatorStatementZeroOne1.setParent(this);
        this.ForBodyMarker=ForBodyMarker;
        if(ForBodyMarker!=null) ForBodyMarker.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public DesignatorStatementZeroOne getDesignatorStatementZeroOne() {
        return DesignatorStatementZeroOne;
    }

    public void setDesignatorStatementZeroOne(DesignatorStatementZeroOne DesignatorStatementZeroOne) {
        this.DesignatorStatementZeroOne=DesignatorStatementZeroOne;
    }

    public ForCondMarker getForCondMarker() {
        return ForCondMarker;
    }

    public void setForCondMarker(ForCondMarker ForCondMarker) {
        this.ForCondMarker=ForCondMarker;
    }

    public ConditionZeroOne getConditionZeroOne() {
        return ConditionZeroOne;
    }

    public void setConditionZeroOne(ConditionZeroOne ConditionZeroOne) {
        this.ConditionZeroOne=ConditionZeroOne;
    }

    public ForIncrMarker getForIncrMarker() {
        return ForIncrMarker;
    }

    public void setForIncrMarker(ForIncrMarker ForIncrMarker) {
        this.ForIncrMarker=ForIncrMarker;
    }

    public DesignatorStatementZeroOne getDesignatorStatementZeroOne1() {
        return DesignatorStatementZeroOne1;
    }

    public void setDesignatorStatementZeroOne1(DesignatorStatementZeroOne DesignatorStatementZeroOne1) {
        this.DesignatorStatementZeroOne1=DesignatorStatementZeroOne1;
    }

    public ForBodyMarker getForBodyMarker() {
        return ForBodyMarker;
    }

    public void setForBodyMarker(ForBodyMarker ForBodyMarker) {
        this.ForBodyMarker=ForBodyMarker;
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
        if(ForCondMarker!=null) ForCondMarker.accept(visitor);
        if(ConditionZeroOne!=null) ConditionZeroOne.accept(visitor);
        if(ForIncrMarker!=null) ForIncrMarker.accept(visitor);
        if(DesignatorStatementZeroOne1!=null) DesignatorStatementZeroOne1.accept(visitor);
        if(ForBodyMarker!=null) ForBodyMarker.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatementZeroOne!=null) DesignatorStatementZeroOne.traverseTopDown(visitor);
        if(ForCondMarker!=null) ForCondMarker.traverseTopDown(visitor);
        if(ConditionZeroOne!=null) ConditionZeroOne.traverseTopDown(visitor);
        if(ForIncrMarker!=null) ForIncrMarker.traverseTopDown(visitor);
        if(DesignatorStatementZeroOne1!=null) DesignatorStatementZeroOne1.traverseTopDown(visitor);
        if(ForBodyMarker!=null) ForBodyMarker.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatementZeroOne!=null) DesignatorStatementZeroOne.traverseBottomUp(visitor);
        if(ForCondMarker!=null) ForCondMarker.traverseBottomUp(visitor);
        if(ConditionZeroOne!=null) ConditionZeroOne.traverseBottomUp(visitor);
        if(ForIncrMarker!=null) ForIncrMarker.traverseBottomUp(visitor);
        if(DesignatorStatementZeroOne1!=null) DesignatorStatementZeroOne1.traverseBottomUp(visitor);
        if(ForBodyMarker!=null) ForBodyMarker.traverseBottomUp(visitor);
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

        if(ForCondMarker!=null)
            buffer.append(ForCondMarker.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionZeroOne!=null)
            buffer.append(ConditionZeroOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForIncrMarker!=null)
            buffer.append(ForIncrMarker.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStatementZeroOne1!=null)
            buffer.append(DesignatorStatementZeroOne1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForBodyMarker!=null)
            buffer.append(ForBodyMarker.toString("  "+tab));
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
