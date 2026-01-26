package rs.ac.bg.etf.pp1;

import java.io.Console;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemAnalyzer extends VisitorAdaptor {
	
	private boolean errorDetected=false;
	Logger log=Logger.getLogger(getClass());
	private Obj currentProgram;
	private Struct currentType;
	private int constant;
	private Struct constantType;
	private Struct boolType=Tab.find("bool").getType();
	private Obj mainMethod;
	private Obj currentMethod;
	private Obj currentEnum;
	private int enumCurrentValue;
	private boolean firstEnumMember;
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected=true;
		StringBuilder msg=new StringBuilder(message);
		int line=(info==null)?0:info.getLine();
		if(line!=0) 
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}
	
	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg=new StringBuilder(message);
		int line=(info==null)?0:info.getLine();
		if(line!=0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public boolean passed() {
		return !errorDetected;
	}
	
	// SEMANTIC PASS CODE //
	
	@Override
	public void visit(ProgramName programName) {
		currentProgram = Tab.insert(Obj.Prog, programName.getI1(), Tab.noType);
		Tab.openScope();
	}
	
	@Override
	public void visit(Program program) {
		Tab.chainLocalSymbols(currentProgram);
		Tab.closeScope();
		currentProgram=null;
		if(mainMethod==null || mainMethod.getLevel()>0) {
			report_error("Program nema adekvatnu main metodu",program);
		}
	}
	
	// CONST DECLARATIONS //
	
	@Override
	public void visit(ConstDecl constDecl) {
		Obj constObj=Tab.find(constDecl.getI1());
		if(constObj != Tab.noObj) {
			report_error("Dvostruka definicija konstante "+constDecl.getI1(),constDecl);
		}else {
			if(constantType.assignableTo(currentType)) {
				constObj=Tab.insert(Obj.Con, constDecl.getI1(), currentType);
				constObj.setAdr(constant);
			}else {
				report_error("Neadekvatna dodela konstanti "+constDecl.getI1(),constDecl);
			}
		}
		
	}
	
	@Override
	public void visit(Constant_number constant_number) {
		constant=constant_number.getN1();
		constantType=Tab.intType;
	}
	
	@Override
	public void visit(Constant_character constant_character) {
		constant=constant_character.getC1();
		constantType=Tab.charType;
	}
	
	@Override
	public void visit(Constant_bool constant_bool) {
		constant=constant_bool.getB1();
		constantType=boolType;
	}
	
	// VAR DECLARATIONS //
	
	@Override 
	public void visit(VarDeclaration_elem varDeclaration_elem) {
		Obj varObj=null;
		if(currentMethod==null) {
			varObj=Tab.find(varDeclaration_elem.getI1());
		}else {
			varObj=Tab.currentScope().findSymbol(varDeclaration_elem.getI1());
		}
		if(varObj==null || varObj == Tab.noObj) {
			varObj=Tab.insert(Obj.Var, varDeclaration_elem.getI1(), currentType);
		}else{
			report_error("Dvostruka definicija promenljive "+varDeclaration_elem.getI1(),varDeclaration_elem);
		}
	}
	
	@Override 
	public void visit(VarDeclaration_arr varDeclaration_arr) {
		Obj varObj=null;
		if(currentMethod==null) {
			varObj=Tab.find(varDeclaration_arr.getI1());
		}else {
			varObj=Tab.currentScope().findSymbol(varDeclaration_arr.getI1());
		}
		if(varObj==null || varObj == Tab.noObj) {
			varObj=Tab.insert(Obj.Var, varDeclaration_arr.getI1(), new Struct(Struct.Array,currentType));
		}else{
			report_error("Dvostruka definicija promenljive "+varDeclaration_arr.getI1(),varDeclaration_arr);
		}
	}
	
	// ENUM DECLARATIONS ----proveriti da li se lepo cuva u tabeli simbola---- //
	

	@Override 
	public void visit(EnumDeclName enumDeclName) {
	    Obj enumObj = Tab.find(enumDeclName.getI1());
	    if(enumObj != Tab.noObj) {
	        report_error("Dvostruka deklaracija enuma", enumDeclName);
	        currentEnum = null;
	    } else {
	        currentEnum = Tab.insert(Obj.Type, enumDeclName.getI1(), new Struct(Struct.Enum));
	        Tab.openScope();
	        enumCurrentValue = 0;
	    }
	}

	@Override
	public void visit(EnumMember_num enumMember_num) {
	    Obj existing = Tab.currentScope().findSymbol(enumMember_num.getI1());
	    if(existing != null && existing != Tab.noObj) {
	        report_error("Dvostruka deklaracija elementa enuma", enumMember_num);
	    } else {
	        Obj enumMember = Tab.insert(Obj.Con, enumMember_num.getI1(), Tab.intType);
	        enumMember.setAdr(enumCurrentValue);
	        enumCurrentValue++;
	    }
	}

	@Override
	public void visit(EnumMember_assign enumMember_assign) {
	    Obj existing = Tab.currentScope().findSymbol(enumMember_assign.getI1());
	    if(existing != null && existing != Tab.noObj) {
	        report_error("Dvostruka deklaracija elementa enuma", enumMember_assign);
	    } else {
	        Obj enumMember = Tab.insert(Obj.Con, enumMember_assign.getI1(), Tab.intType);
	        enumMember.setAdr(enumMember_assign.getN2());
	        enumCurrentValue = enumMember_assign.getN2() + 1;
	    }
	}

	@Override 
	public void visit(EnumDeclList enumDeclList) {
		if(currentEnum != null) {
	        Tab.chainLocalSymbols(currentEnum);
	        Tab.closeScope();
	        currentEnum = null;
	    }
	}
	
	// METHOD DECLARATIONS (fali logika za proveru da li postoji return) //
	@Override
	public void visit(MethodNameType_void methodNameType_void) {
		currentMethod=Tab.insert(Obj.Meth,methodNameType_void.getI1(),Tab.noType);
		Tab.openScope();
		if(methodNameType_void.getI1().equalsIgnoreCase("main"))
			mainMethod=currentMethod;
		
	}
	
	@Override
	public void visit(MethodNameType_type methodNameType_type) {
		currentMethod=Tab.insert(Obj.Meth,methodNameType_type.getI2(),currentType);
		//logika koja proverava da li methodNameType_type ima povratnu vrednost - mozda obraditi kada budem pisao statement return??
		Tab.openScope();
	}
	
	@Override
	public void visit(MethodDecl methodDecl) {
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		currentMethod=null;
	}
	
	// FORM PARAMETER DECLARATION //
	
	
	@Override 
	public void visit(FormPars_var formPars_var) {
		Obj varObj=null;
		if(currentMethod==null) {
			report_error("Semanticka greska", formPars_var);
		}else {
			varObj=Tab.currentScope().findSymbol(formPars_var.getI2());
		}
		if(varObj==null || varObj == Tab.noObj) {
			varObj=Tab.insert(Obj.Var, formPars_var.getI2(), currentType);
			varObj.setFpPos(1);
			currentMethod.setLevel(currentMethod.getLevel()+1);
		}else{
			report_error("Dvostruka definicija formalnog parametra "+formPars_var.getI2(),formPars_var);
		}
	}
	
	@Override 
	public void visit(FormPars_arr formPars_arr) {
		Obj varObj=null;
		if(currentMethod==null) {
			report_error("Semanticka greska",formPars_arr);
		}else {
			varObj=Tab.currentScope().findSymbol(formPars_arr.getI2());
		}
		if(varObj==null || varObj == Tab.noObj) {
			varObj=Tab.insert(Obj.Var, formPars_arr.getI2(), new Struct(Struct.Array,currentType));
			varObj.setFpPos(1);
			currentMethod.setLevel(currentMethod.getLevel()+1);
		}else{
			report_error("Dvostruka definicija promenljive "+formPars_arr.getI2(),formPars_arr);
		}
	}
	
	
	// TYPE DECLARATION //
	@Override 
	public void visit(Type type) {
		Obj typeObj=Tab.find(type.getI1());
		if(typeObj == Tab.noObj) {
			report_error("Nepostojeci tip podatka "+type.getI1(),type);
			currentType=Tab.noType;
		}else if(typeObj.getKind()!=Obj.Type) {
			report_error("Neadekvatan tip podatka "+type.getI1(),type);
			currentType=Tab.noType;
		}else {
			currentType=typeObj.getType();
		}
	}
	
}

