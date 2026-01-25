package rs.ac.bg.etf.pp1;

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
		Obj varObj=Tab.find(varDeclaration_elem.getI1());
		if(varObj != Tab.noObj) {
			report_error("Dvostruka definicija promenljive "+varDeclaration_elem.getI1(),varDeclaration_elem);
		}else {
			varObj=Tab.insert(Obj.Var, varDeclaration_elem.getI1(), currentType);
		}
	}
	
	@Override 
	public void visit(VarDeclaration_arr varDeclaration_arr) {
		Obj varObj=Tab.find(varDeclaration_arr.getI1());
		if(varObj != Tab.noObj) {
			report_error("Dvostruka definicija promenljive "+varDeclaration_arr.getI1(),varDeclaration_arr);
		}else {
			varObj=Tab.insert(Obj.Var, varDeclaration_arr.getI1(), new Struct(Struct.Array,currentType));
		}
	}
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

