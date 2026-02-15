// generated with ast extension for cup
// version 0.8
// 15/1/2026 0:0:19


package rs.ac.bg.etf.pp1.ast;

public class FormParMore_rek extends FormParMore {

    private FormPars FormPars;
    private FormParMore FormParMore;

    public FormParMore_rek (FormPars FormPars, FormParMore FormParMore) {
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
        this.FormParMore=FormParMore;
        if(FormParMore!=null) FormParMore.setParent(this);
    }

    public FormPars getFormPars() {
        return FormPars;
    }

    public void setFormPars(FormPars FormPars) {
        this.FormPars=FormPars;
    }

    public FormParMore getFormParMore() {
        return FormParMore;
    }

    public void setFormParMore(FormParMore FormParMore) {
        this.FormParMore=FormParMore;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormPars!=null) FormPars.accept(visitor);
        if(FormParMore!=null) FormParMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
        if(FormParMore!=null) FormParMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        if(FormParMore!=null) FormParMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParMore_rek(\n");

        if(FormPars!=null)
            buffer.append(FormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParMore!=null)
            buffer.append(FormParMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParMore_rek]");
        return buffer.toString();
    }
}
