// generated with ast extension for cup
// version 0.8
// 15/1/2026 0:0:19


package rs.ac.bg.etf.pp1.ast;

public class OrCondTermList_rek extends OrCondTermList {

    private OrCondTermList OrCondTermList;
    private OrMarker OrMarker;
    private CondTerm CondTerm;

    public OrCondTermList_rek (OrCondTermList OrCondTermList, OrMarker OrMarker, CondTerm CondTerm) {
        this.OrCondTermList=OrCondTermList;
        if(OrCondTermList!=null) OrCondTermList.setParent(this);
        this.OrMarker=OrMarker;
        if(OrMarker!=null) OrMarker.setParent(this);
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
    }

    public OrCondTermList getOrCondTermList() {
        return OrCondTermList;
    }

    public void setOrCondTermList(OrCondTermList OrCondTermList) {
        this.OrCondTermList=OrCondTermList;
    }

    public OrMarker getOrMarker() {
        return OrMarker;
    }

    public void setOrMarker(OrMarker OrMarker) {
        this.OrMarker=OrMarker;
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OrCondTermList!=null) OrCondTermList.accept(visitor);
        if(OrMarker!=null) OrMarker.accept(visitor);
        if(CondTerm!=null) CondTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OrCondTermList!=null) OrCondTermList.traverseTopDown(visitor);
        if(OrMarker!=null) OrMarker.traverseTopDown(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OrCondTermList!=null) OrCondTermList.traverseBottomUp(visitor);
        if(OrMarker!=null) OrMarker.traverseBottomUp(visitor);
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OrCondTermList_rek(\n");

        if(OrCondTermList!=null)
            buffer.append(OrCondTermList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OrMarker!=null)
            buffer.append(OrMarker.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OrCondTermList_rek]");
        return buffer.toString();
    }
}
