package rs.ac.bg.etf.pp1;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
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
	
	private boolean hasReturn;
	
	private Set<Integer> currentCases=null;
	int nVars;
	 	


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
		this.nVars=Tab.currentScope().getnVars();
		Tab.chainLocalSymbols(currentProgram);
		Tab.closeScope();
		currentProgram=null;
		//provera da li main postoji i da li je main globalna(level=0)
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
			report_error("Dvostruka definicija niza "+varDeclaration_arr.getI1(),varDeclaration_arr);
		}
	}
	
	// ENUM DECLARATIONS //
	

	@Override
	public void visit(EnumDeclName enumDeclName) {
	    Obj enumObj = Tab.find(enumDeclName.getI1());
	    if(enumObj != Tab.noObj) {
	        report_error("Dvostruka deklaracija enum-a", enumDeclName);
	        currentEnum = null;
	    } else {
	        currentEnum = Tab.insert(Obj.Type, enumDeclName.getI1(), new Struct(Struct.Enum));
	        Tab.openScope();
	        enumCurrentValue = 0;
	    }
	}

	@Override
	public void visit(EnumMember_num enumMember_num) {
	    if(currentEnum == null) return;
	    Obj existing = Tab.currentScope().findSymbol(enumMember_num.getI1());
	    if(existing != null && existing != Tab.noObj) {
	        report_error("Dvostruka deklaracija enum konstante", enumMember_num);
	    } else {
	        Obj c = Tab.insert(Obj.Con, enumMember_num.getI1(), Tab.intType);
	        c.setAdr(enumCurrentValue++);
	    }
	}

	@Override
	public void visit(EnumMember_assign enumMember_assign) {
	    if(currentEnum == null) return;
	    Obj existing = Tab.currentScope().findSymbol(enumMember_assign.getI1());
	    if(existing != null && existing != Tab.noObj) {
	        report_error("Dvostruka deklaracija enum konstante", enumMember_assign);
	    } else {
	        Obj c = Tab.insert(Obj.Con, enumMember_assign.getI1(), Tab.intType);
	        c.setAdr(enumMember_assign.getN2());
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


	
	// METHOD DECLARATIONS //
	
	@Override
	public void visit(MethodNameType_void methodNameType_void) {
		String methName=methodNameType_void.getI1();
		if(Tab.currentScope().findSymbol(methName)!=null) {
			report_error("Dvostruka definicija metode "+methName,methodNameType_void);
			methodNameType_void.obj=currentMethod=Tab.noObj;
		}else {
			methodNameType_void.obj=currentMethod=Tab.insert(Obj.Meth,methName,Tab.noType);
			Tab.openScope();
			hasReturn=false;
			
			if(methName.equals("main")) {
				mainMethod=currentMethod;
			}
		}
	}
	@Override
	public void visit(MethodNameType_type methodNameType_type) {
		String methName=methodNameType_type.getI2();
		if(Tab.currentScope().findSymbol(methName)!=null) {
			report_error("Dvostruka definicija metode "+methName,methodNameType_type);
			methodNameType_type.obj=currentMethod=Tab.noObj;
		}else {
			methodNameType_type.obj=currentMethod=Tab.insert(Obj.Meth,methName,currentType);
			hasReturn=false;
			Tab.openScope();
		}
		
	}
	
	@Override
	public void visit(MethodDecl methodDecl) {
		if (currentMethod.getType() != Tab.noType) {
	        if (!hasReturn) {
	            report_error("Metoda koja nije void mora imati return deklaraciju",methodDecl);
	        }
	    }
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		currentMethod=null;
		hasReturn=false;
	}
	
	@Override
	public void visit(SingleStatement_fifth  singleStatement_fifth ) {
		hasReturn=true;
		if(currentMethod==null) {
			report_error("Return se mora nalaziti unutar funkcije", singleStatement_fifth);
		}
		if(singleStatement_fifth.getZeroOneExpr() instanceof ZeroOneExpr_first) {
			ZeroOneExpr_first zeroOneExpr=(ZeroOneExpr_first)singleStatement_fifth.getZeroOneExpr();
			Struct exprType=zeroOneExpr.getExpr().struct;
			
			if(currentMethod.getType()==Tab.noType) {
				report_error("Void metoda ne moze imati return sa izrazom",singleStatement_fifth);
			}
			if(exprType!=null && !exprType.assignableTo(currentMethod.getType())) {
				report_error("Tip return statementa nije kompatibilan sa povratnom vrednoscu funkcije", singleStatement_fifth);
			}
		}else {
			if(currentMethod.getType()!=Tab.noType) {
				report_error("Metoda koja nije void mora imati povratnu vrednost",singleStatement_fifth);
			}
		}
		
	}
	
	// FORM PARAMETER DECLARATION //
	
	
	@Override 
	public void visit(FormPars_var formPars_var) {
	    Obj varObj = null;
	    if(currentMethod == null) {
	        report_error("Semanticka greska", formPars_var);
	        return;  
	    } else {
	        varObj = Tab.currentScope().findSymbol(formPars_var.getI2());
	    }
	    
	    if(varObj == null || varObj == Tab.noObj) {
	        int currentParamNumber = currentMethod.getLevel() + 1;
	        varObj = Tab.insert(Obj.Var, formPars_var.getI2(), currentType);
	        varObj.setFpPos(currentParamNumber); 
	        currentMethod.setLevel(currentParamNumber); 
	        //svaki put uvecamo level, kako bi naredni parametar imao za jefan veci fpPos
	    } else {
	        report_error("Dvostruka definicija formalnog parametra " + formPars_var.getI2(), formPars_var);
	    }
	}
	
	@Override 
	public void visit(FormPars_arr formPars_arr) {
	    Obj varObj = null;
	    if(currentMethod == null) {
	        report_error("Semanticka greska", formPars_arr);
	        return;  
	    } else {
	        varObj = Tab.currentScope().findSymbol(formPars_arr.getI2());
	    }
	    
	    if(varObj == null || varObj == Tab.noObj) {
	        int currentParamNumber = currentMethod.getLevel() + 1;
	        
	        varObj = Tab.insert(Obj.Var, formPars_arr.getI2(), new Struct(Struct.Array, currentType));
	        varObj.setFpPos(currentParamNumber); 
	        
	        currentMethod.setLevel(currentParamNumber); 
	    } else {
	        report_error("Dvostruka definicija formalnog parametra niza " + formPars_arr.getI2(), formPars_arr);
	    }
	}
	
	
	// TYPE DECLARATION //
	@Override 
	public void visit(Type type) {
		Obj typeObj=Tab.find(type.getI1());
		if(typeObj == Tab.noObj) {
			report_error("Nepostojeci tip podatka "+type.getI1(),type);
			type.struct= currentType =Tab.noType; 
		}else if(typeObj.getKind()!=Obj.Type) {
			report_error("Neadekvatan tip podatka "+type.getI1(),type);
			type.struct= currentType=Tab.noType;
		}else {
			type.struct= currentType=typeObj.getType();
			if (currentType.getKind() == Struct.Enum) {
	              currentType = Tab.intType;
	          }
		}
	}
	
	// *****KONTEKSNI USLOVI***** //
	
	// ===== DESIGNATOR  ===== //
	
	// ===== DESIGNATOR VISITORS ===== //

	@Override
	public void visit(DesignatorArrName designatorArrName) {
	    Obj arrayObj = Tab.find(designatorArrName.getI1());
	    
	    if(arrayObj == Tab.noObj) {
	        report_error("Nedeklarisan simbol " + designatorArrName.getI1(), designatorArrName);
	        designatorArrName.obj = Tab.noObj;
	    } else if(arrayObj.getType() == null || arrayObj.getType().getKind() != Struct.Array) {
	        report_error("Simbol " + designatorArrName.getI1() + " nije deklarisan kao niz", designatorArrName);
	        designatorArrName.obj = Tab.noObj;
	    } else {
	        designatorArrName.obj = arrayObj;
	    }
	}

	@Override
	public void visit(Designator_var designator_var) {
	    Obj baseObj = Tab.find(designator_var.getI1());
	    
	    if(baseObj == Tab.noObj) {
	        report_error("Nedeklarisan simbol " + designator_var.getI1(), designator_var);
	        designator_var.obj = Tab.noObj;
	        return;
	    }
	    
	    if(baseObj.getKind() != Obj.Var && baseObj.getKind() != Obj.Con && 
	       baseObj.getKind() != Obj.Type && baseObj.getKind() != Obj.Meth) {
	        report_error("Neadekvatan tip objekta (" + designator_var.getI1() + ")", designator_var);
	        designator_var.obj = Tab.noObj;
	        return;
	    }
	    
	    designator_var.obj = processDesignatorMore(baseObj, designator_var.getDesignatorMore());
	    
	    if(designator_var.obj != null && designator_var.obj != Tab.noObj) {
	        String kindName = getKindName(designator_var.obj);
	        if(!kindName.equals(".")) {
	            report_info("Pristup " + kindName + ": " + designator_var.getI1(), designator_var);
	        }
	    }
	}
	
	private String getKindName(Obj obj) {
	    switch (obj.getKind()) {
	        case Obj.Con: 
	            return "konstanti";
	        case Obj.Var:
	            if(obj.getFpPos() > 0) return "formalnom parametru";
	            if(currentMethod != null) return "lokalnoj/globalnoj promenljivoj";
	            return "globalnoj promenljivoj";
	        case Obj.Elem:
	            return "elementu niza";
	        case Obj.Meth:
	            return ".";
	        case Obj.Type:
	            return ".";
	        case Obj.Fld:
	            return "polju";
	        default:
	            return ".";
	    }
	}

	@Override
	public void visit(Designator_arr designator_arr) {
	    Obj arrayObj = designator_arr.getDesignatorArrName().obj;
	    
	    if(arrayObj == null || arrayObj == Tab.noObj) {
	        designator_arr.obj = Tab.noObj;
	        return;
	    }
	    
	    Struct indexType = designator_arr.getExpr().struct;
	    if(indexType == null || !indexType.equals(Tab.intType)) {
	        report_error("Indeks niza mora biti int tipa", designator_arr);
	    }
	    
	    Obj elemObj = new Obj(Obj.Elem, "elem", arrayObj.getType().getElemType());
	    designator_arr.obj = processDesignatorMore(elemObj, designator_arr.getDesignatorMore());
	    
	    if(designator_arr.obj != null && designator_arr.obj != Tab.noObj) {
	        report_info("Pristup elementu niza: " + arrayObj.getName(), designator_arr);
	    }
	}

	private Obj processDesignatorMore(Obj baseObj, DesignatorMore more) {
	    if(more instanceof DesignatorMore_epsilon) {
	        return baseObj;
	    }
	    
	    if(more instanceof DesignatorMore_dotIdent) {
	        DesignatorMore_dotIdent dotIdent = (DesignatorMore_dotIdent)more;
	        String fieldName = dotIdent.getI1();
	        
	        if(baseObj.getKind() == Obj.Type && baseObj.getType().getKind() == Struct.Enum) {
	            for(Obj member : baseObj.getLocalSymbols()) {
	                if(member.getName().equals(fieldName)) {
	                    return processDesignatorMore(member, dotIdent.getDesignatorMore());
	                }
	            }
	            report_error("Polje '" + fieldName + "' ne postoji u enum-u", dotIdent);
	            return Tab.noObj;
	        }
	        
	        report_error("Pristup polju nije dozvoljen", dotIdent);
	        return Tab.noObj;
	    }
	    
	    if(more instanceof DesignatorMore_dotLength) {
	        if(baseObj.getType() == null || baseObj.getType().getKind() != Struct.Array) {
	            report_error("Pristup '.length' je dozvoljen samo za nizove", more);
	            return Tab.noObj;
	        }
	        ((DesignatorMore_dotLength)more).obj = baseObj;
	        return new Obj(Obj.Fld, "length", Tab.intType);
	    }
	    
	    return baseObj;
	}
	
	// FACTOR SUB //
	@Override
	public void visit(FactorSub_chr factorSub_chr) {
		factorSub_chr.struct=Tab.charType;
	}
	
	@Override
	public void visit(FactorSub_num factorSub_num) {
		factorSub_num.struct=Tab.intType;
	}
	
	@Override
	public void visit(FactorSub_bool factorSub_bool) {
		factorSub_bool.struct=boolType;
	}
	
	@Override
	public void visit(FactorSub_des factorSub_des) {
		 Obj designatorObj = factorSub_des.getDesignator().obj;
		    
		    if(designatorObj != null && designatorObj != Tab.noObj) {
		        // Proveri da li je poziv metode (ima ActPars)
		        if(factorSub_des.getActParsBracketsZeroOne() instanceof ActParsBracketsZeroOne_brackets) {
		        	 report_info("Poziv funkcije: " + designatorObj.getName(), factorSub_des);
		            // Poziv metode u izrazu
		            if(designatorObj.getKind() != Obj.Meth) {
		                report_error("Poziv metode je moguc samo nad metodom", factorSub_des);
		                factorSub_des.struct = Tab.noType;
		                return;
		            }
		            
		            // Proveri argumente
		            ActParsBracketsZeroOne_brackets actParsBrackets = 
		                (ActParsBracketsZeroOne_brackets)factorSub_des.getActParsBracketsZeroOne();
		            checkMethodArguments(designatorObj, actParsBrackets.getActParsZeroOne(), factorSub_des);
		            
		            // Tip izraza je povratni tip metode
		            factorSub_des.struct = designatorObj.getType();
		        } else {
		            // Samo Designator (ne poziv metode)
		            factorSub_des.struct = designatorObj.getType();
		        }
		    } else {
		        factorSub_des.struct = Tab.noType;
		    }
	}
	//-------------------------------------CHECKPOINT-----------------------------------------
	
	// LOGIKA ZA PROVERAVANJE ARGUMENATA U FUNKCIJAMA  >>>>>>>>>>>
	
	private void checkMethodArguments(Obj method, ActParsZeroOne actParsZeroOne, SyntaxNode errorNode) {
	    // Broj formalnih parametara
	    int formalCount = method.getLevel();
	    
	    // Prikupi formalne tipove
	    List<Struct> formalTypes = new ArrayList<>();
	    for(int i = 0; i < formalCount; i++) {
	        formalTypes.add(null);
	    }
	    
	    for(Obj local : method.getLocalSymbols()) {
	        if(local.getFpPos() > 0 && local.getFpPos() <= formalCount) {
	            formalTypes.set(local.getFpPos() - 1, local.getType());
	        }
	    }
	    
	    // Prikupi stvarne tipove
	    List<Struct> actualTypes = collectActualArguments(actParsZeroOne);
	    
	    // Proveri broj
	    if(actualTypes.size() != formalTypes.size()) {
	        report_error("Neodgovarajuci broj argumenata u pozivu metode " + method.getName() + 
	                     ": ocekivano " + formalTypes.size() + ", pronadjeno " + actualTypes.size(), 
	                     errorNode);
	        return;
	    }
	    
	    // Proveri tipove
	    for(int i = 0; i < actualTypes.size(); i++) {
	        if(actualTypes.get(i) != null && formalTypes.get(i) != null && 
	           !actualTypes.get(i).assignableTo(formalTypes.get(i))) {
	            report_error("Tip argumenta nije kompatibilan pri pozivu metode " + 
	                         method.getName(), errorNode);
	        }
	    }
	}

	private List<Struct> collectActualArguments(ActParsZeroOne actParsZeroOne) {
	    List<Struct> types = new ArrayList<>();
	    
	    if(actParsZeroOne instanceof ActParsZeroOne_epsilon) {
	        return types;
	    }
	    
	    ActParsZeroOne_ActPars actParsNode = (ActParsZeroOne_ActPars)actParsZeroOne;
	    collectActParsTypes(actParsNode.getActPars(), types);
	    
	    return types;
	}

	private void collectActParsTypes(ActPars actPars, List<Struct> types) {
	    types.add(actPars.getExpr().struct);
	    collectCommaExprList(actPars.getCommaExprList(), types);
	}

	private void collectCommaExprList(CommaExprList list, List<Struct> types) {
	    if(list instanceof CommaExprList_epsilon) {
	        return;
	    }
	    
	    CommaExprList_rek rekList = (CommaExprList_rek)list;
	    types.add(rekList.getExpr().struct);
	    collectCommaExprList(rekList.getCommaExprList(), types);
	}
	
	// <<<<<<<<<<<< LOGIKA ZA PROVERAVANJE ARGUMENATA U FUNKCIJAMA 
	
	@Override 
	public void visit(FactorSub_new factorSub_new) {
		
		Struct exprType = factorSub_new.getExpr().struct;
		if(exprType == null || !exprType.equals(Tab.intType)) {
			report_error("Velicina niza mora biti int tipa", factorSub_new);
		}

		Type typeNode = factorSub_new.getType();
		Obj typeObj = Tab.find(typeNode.getI1());
		
		Struct arrayElemType;
		if(typeObj == Tab.noObj || typeObj.getKind() != Obj.Type) {
			arrayElemType = Tab.noType;
		} else {
			arrayElemType = typeObj.getType();
		}

		factorSub_new.struct = new Struct(Struct.Array, arrayElemType);
	}
	
	@Override
	public void visit(FactorSub_expr factorSub_expr) {
		factorSub_expr.struct = factorSub_expr.getExpr().struct;
	}
	
	// FACTOR //
	
	@Override
	public void visit(Factor factor) {
		if(factor.getUnary() instanceof Unary_minus) {
			if(factor.getFactorSub().struct != null && factor.getFactorSub().struct.equals(Tab.intType)) {
				factor.struct=Tab.intType;
			}else {
				report_error("Negacija ne int vrednosti",factor);
				factor.struct=Tab.noType;
			}
		}else {
			factor.struct=factor.getFactorSub().struct;
		}
	}
	
	// TERM //
	
	@Override
	public void visit(MulopFactorList_factor mulopFactorList_factor) {
		mulopFactorList_factor.struct = mulopFactorList_factor.getFactor().struct;
	}
	
	@Override
	public void visit(MulopFactorList_rek mulopFactorList_rek) {
		Struct leftType = mulopFactorList_rek.getMulopFactorList().struct;
		Struct rightType = mulopFactorList_rek.getFactor().struct;
		
		if(leftType != null && leftType.equals(Tab.intType) && 
		   rightType != null && rightType.equals(Tab.intType)) {
			mulopFactorList_rek.struct = Tab.intType;
		} else {
			report_error("Operandi multiplikativne operacije moraju biti int tipa", mulopFactorList_rek);
			mulopFactorList_rek.struct = Tab.noType;
		}
	}
	
	@Override
	public void visit(Term term) {
		term.struct = term.getMulopFactorList().struct;
	}
	
	// ADDEXPR //
	
	
	@Override
	public void visit(AddopTermList_rek addopTermList_rek) {
		Struct leftType = addopTermList_rek.getAddopTermList().struct;
		Struct rightType = addopTermList_rek.getTerm().struct;
		
		// Ako je left null, znači da je ovo prvi term u listi
		if(leftType == null) {
			addopTermList_rek.struct = rightType;
		}
		else if(leftType.equals(Tab.intType) && rightType != null && rightType.equals(Tab.intType)) {
			addopTermList_rek.struct = Tab.intType;
		} else {
			report_error("Operandi aditivne operacije moraju biti int tipa", addopTermList_rek);
			addopTermList_rek.struct = Tab.noType;
		}
	}
	
	@Override
	public void visit(AddExpr_addop addExpr_addop) {
		Struct termType = addExpr_addop.getTerm().struct;
		Struct listType = addExpr_addop.getAddopTermList().struct;
		
		// Kada je AddopTermList epsilon, listType je null
		// U tom slucaju rezultat je samo Term
		if(listType == null) {
			addExpr_addop.struct = termType;
		} else {
			// Ako postoji lista, ona vec sadrzi rezultat operacija
			addExpr_addop.struct = listType;
		}
	}
	
	// EXPR //
	
	@Override
	public void visit(Expr_normal expr_normal) {
		expr_normal.struct = expr_normal.getAddExpr().struct;
	}
	
	@Override
	public void visit(Expr_ternary expr_ternary) {
		// Condition ? Expr : Expr
		Struct trueExpr = expr_ternary.getExpr().struct;
		Struct falseExpr = expr_ternary.getExpr1().struct;
		
		if(trueExpr != null && falseExpr != null && trueExpr.equals(falseExpr)) {
			expr_ternary.struct = trueExpr;
		} else {
			report_error("Izrazi u ternarnom operatoru moraju biti istog tipa", expr_ternary);
			expr_ternary.struct = Tab.noType;
		}
	}
	
	// CONDITION //
	
	@Override
	public void visit(CondFact condFact) {
		if(condFact.getRelopExprZeroOne() instanceof RelopExprZeroOne_relopExpr) {
			RelopExprZeroOne_relopExpr relopExpr = (RelopExprZeroOne_relopExpr)condFact.getRelopExprZeroOne();
			
			Struct leftType = condFact.getAddExpr().struct;
			Struct rightType = relopExpr.getAddExpr().struct;
			
			if(leftType != null && rightType != null && !leftType.compatibleWith(rightType)) {
				report_error("Tipovi u relacionoj operaciji nisu kompatibilni", condFact);
			}
			
			// Provera za reference - samo == i !=
			if(leftType != null && rightType != null && 
			   (leftType.isRefType() || rightType.isRefType())) {
				Relop relop = relopExpr.getRelop();
				if(!(relop instanceof Relop_first || relop instanceof Relop_second)) {
					report_error("Sa referentnim tipovima mogu se koristiti samo == i !=", condFact);
				}
			}
			condFact.struct = boolType;
		}else {
			Struct exprType=condFact.getAddExpr().struct;
			if(exprType!=null && !exprType.equals(boolType)) {
				report_error("Uslov mora biti bool tipa",condFact);
				condFact.struct=Tab.noType;
			}else {
				condFact.struct=boolType;
			}
		}
	}
	
	@Override
	public void visit(CondTerm condTerm) {
		condTerm.struct=boolType;
	}
	
	@Override
	public void visit(Condition condition) {
		condition.struct=boolType;
	}
	
	
	// DESIGNATOR STATEMENT //
	
	@Override
	public void visit(DesignatorRest_first designatorRest_first) {
		// Assignop Expr - dodela
		Obj designatorObj = ((DesignatorStatement)designatorRest_first.getParent()).getDesignator().obj;
		Struct exprType = designatorRest_first.getExpr().struct;
		
		if(designatorObj == null || designatorObj == Tab.noObj) {
			return;
		}
		
		// Designator mora biti Var, Elem ili Fld
		if(designatorObj.getKind() != Obj.Var && designatorObj.getKind() != Obj.Elem && 
		   designatorObj.getKind() != Obj.Fld) {
			report_error("Dodela je moguca samo promenljivoj, elementu niza ili polju objekta", designatorRest_first);
			return;
		}
		
		// Provera kompatibilnosti tipova
		Struct destType = designatorObj.getType();
		if(exprType != null && destType != null && !exprType.assignableTo(destType)) {
			report_error("Tip izraza nije kompatibilan sa tipom designatora pri dodeli", designatorRest_first);
		}
	}
	
	@Override
	public void visit(DesignatorRest_third designatorRest_third) {
		Obj designatorObj = ((DesignatorStatement)designatorRest_third.getParent()).getDesignator().obj;
		
		if(designatorObj == null || designatorObj == Tab.noObj) {
			return;
		}
		
		if(designatorObj.getKind() != Obj.Var && designatorObj.getKind() != Obj.Elem && 
		   designatorObj.getKind() != Obj.Fld) {
			report_error("Inkrement je moguc samo nad promenljivom, elementom niza ili poljem objekta", designatorRest_third);
			return;
		}
		
		if(designatorObj.getType() == null || !designatorObj.getType().equals(Tab.intType)) {
			report_error("Inkrement je moguc samo nad int tipom", designatorRest_third);
		}
		
	}
	
	@Override
	public void visit(DesignatorRest_fourth designatorRest_fourth) {
		Obj designatorObj = ((DesignatorStatement)designatorRest_fourth.getParent()).getDesignator().obj;
		
		if(designatorObj == null || designatorObj == Tab.noObj) {
			return;
		}
		
		if(designatorObj.getKind() != Obj.Var && designatorObj.getKind() != Obj.Elem && 
		   designatorObj.getKind() != Obj.Fld) {
			report_error("Dekrement je moguc samo nad promenljivom, elementom niza ili poljem objekta", designatorRest_fourth);
			return;
		}
		
		if(designatorObj.getType() == null || !designatorObj.getType().equals(Tab.intType)) {
			report_error("Dekrement je moguc samo nad int tipom", designatorRest_fourth);
		}
	}
	
	@Override
	public void visit(DesignatorRest_second designatorRest_second) {
	    // Poziv metode kao statement
	    Obj designatorObj = ((DesignatorStatement)designatorRest_second.getParent()).getDesignator().obj;
	    
	    if(designatorObj == null || designatorObj == Tab.noObj) {
	        return;
	    }
	    
	    if(designatorObj.getKind() != Obj.Meth) {
	        report_error("Poziv metode je moguc samo nad metodom", designatorRest_second);
	        return;
	    }
	    
	    if(designatorObj != null && designatorObj != Tab.noObj && designatorObj.getKind() == Obj.Meth) {
	        report_info("Poziv funkcije: " + designatorObj.getName(), designatorRest_second);
	    }
	    checkMethodArguments(designatorObj, designatorRest_second.getActParsZeroOne(), designatorRest_second);
	}
	
	// READ STATEMENT //
	
	@Override
	public void visit(SingleStatement_sixth singleStatement_sixth) {
		// read(Designator)
		Obj designatorObj = singleStatement_sixth.getDesignator().obj;
		
		if(designatorObj == null || designatorObj == Tab.noObj) {
			return;
		}
		
		if(designatorObj.getKind() != Obj.Var && designatorObj.getKind() != Obj.Elem && 
		   designatorObj.getKind() != Obj.Fld) {
			report_error("Read je moguc samo nad promenljivom, elementom niza ili poljem objekta", singleStatement_sixth);
			return;
		}
		
		Struct type = designatorObj.getType();
		if(type == null || (!type.equals(Tab.intType) && !type.equals(Tab.charType) && !type.equals(boolType))) {
			report_error("Read je moguc samo nad int, char ili bool tipom", singleStatement_sixth);
		}
	}
	
	// PRINT STATEMENT //
	
	@Override
	public void visit(SingleStatement_seventh singleStatement_seventh) {
		// print(Expr [, numConst])
		Struct exprType = singleStatement_seventh.getExpr().struct;
		if(exprType == null || (!exprType.equals(Tab.intType) && !exprType.equals(Tab.charType) && !exprType.equals(boolType))) {
			report_error("Print je moguc samo nad int, char ili bool tipom", singleStatement_seventh);
		}
	}
	
	// BREAK AND CONTINUE
	
	private boolean insideLoopOrSwitch(SyntaxNode node) {
		SyntaxNode parent=node.getParent();
		
		while(parent!=null) {
			if(parent instanceof SingleStatement_ninth || parent instanceof SingleStatement_eighth) {
	                return true;
	        }
            if(parent instanceof MethodDecl) {
                return false;
            }
            
            parent = parent.getParent();
		}
		return false;
	}
	
	@Override 
	public void visit(SingleStatement_third singleStatement_third) {
		if(!insideLoopOrSwitch(singleStatement_third)) {
			report_error("break je moguce koristiti samo unutar for i switch",singleStatement_third);
		}
	}
	

	private boolean insideForLoop(SyntaxNode node) {
		SyntaxNode parent=node.getParent();
		
		while(parent!=null) {
			if(parent instanceof SingleStatement_ninth ) {
	            return true;
	        }
            if(parent instanceof MethodDecl) {
                return false;
            }
            
            parent = parent.getParent();
		}
		return false;
	}
	
	@Override 
	public void visit(SingleStatement_fourth singleStatement_fourth) {
		if(!insideForLoop(singleStatement_fourth)) {
			report_error("continue je moguce koristiti samo unutar for petlje",singleStatement_fourth);
		}
	}

	// IF
	@Override
	public void visit(SingleStatement_second singleStatement_second) {
		Struct condType=singleStatement_second.getCondition().struct;
		if(condType==null || !condType.equals(boolType)) {
			report_error("Condition u if mora biti bool tipa", singleStatement_second);
		}
	}
	
	// SWITCH
	
	@Override 
	public void visit(SingleStatement_eighth singleStatement_eighth) {
		Struct swExpr=singleStatement_eighth.getExpr().struct;
		
		if(swExpr==null || !swExpr.equals(Tab.intType)) {
			report_error("Expr unutar switch-a mora biti int tipa",singleStatement_eighth);
		}
		currentCases=null;
	}
	
	@Override
	public void visit(CaseList_first caseList_first) {
		if(currentCases == null) {
	        currentCases = new HashSet<Integer>();
	    }
		int num=caseList_first.getN1();
		if(!currentCases.contains(num)){
			currentCases.add(num);
		}else {
			report_error("Ne moze postojati vise case grana sa istom konstantom", caseList_first);
		}
	}
	
	//FOR
	
	@Override 
	public void visit(SingleStatement_ninth singleStatement_ninth) {
		if(singleStatement_ninth.getConditionZeroOne() instanceof ConditionZeroOne_first) {
			ConditionZeroOne_first cond=(ConditionZeroOne_first)singleStatement_ninth.getConditionZeroOne();
			Struct condType=cond.getCondition().struct;
			if(condType==null || !condType.equals(boolType)) {
				report_error("Condition u for petlji mora biti bool tipa", cond);
			}
		}
	}
	
	
}