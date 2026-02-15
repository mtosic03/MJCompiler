package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor {
	private int mainPc;
	private int currentRelop;
	
	private Stack<List<Integer>> condFixupStack=new Stack<List<Integer>>();
	private Stack<Integer> elseFixupStack=new Stack<Integer>();
	
	private List<Integer> currentCondTermFalseJumps=new ArrayList<Integer>();
	private List<Integer> currentCondTermTrueJumps=new ArrayList<Integer>();
	
	private Stack<Integer> forCondAddrStack=new Stack<Integer>();
	private Stack<Integer> forIncrAddrStack=new Stack<Integer>();
	private Stack<Integer> forIncrFixupStack=new Stack<Integer>();
	private Stack<List<Integer>> breakFixupStack = new Stack<List<Integer>>();
	private Stack<Integer> caseSkipFixupStack = new Stack<Integer>();
	private Stack<Integer> ternaryFixupStack= new Stack<Integer>();
	public int getMainPc() {
		return this.mainPc;
	}

	// ULAZAK U METODU //
	@Override
	public void visit(MethodNameType_type methodNameType_type) {
		methodNameType_type.obj.setAdr(Code.pc); //zapamti adresu pocetka metode
		Code.put(Code.enter);
		Code.put(methodNameType_type.obj.getLevel()); // b1-formalni parametri
		Code.put(methodNameType_type.obj.getLocalSymbols().size()); // b2-lokalni i foramlni parametri
	}

	@Override
	public void visit(MethodNameType_void methodNameType_void) {
		if (methodNameType_void.getI1().equalsIgnoreCase("main")) {
			this.mainPc = Code.pc;
		}
		methodNameType_void.obj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(methodNameType_void.obj.getLevel()); // b1
		Code.put(methodNameType_void.obj.getLocalSymbols().size()); // b2

	}

	//izlazak iz metode
	@Override
	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	// PRINT STATEMENT//
	@Override
	public void visit(SingleStatement_seventh singleStatement_seventh) {
		if (singleStatement_seventh.getCommaNumConst() instanceof CommaNumConst_first) {
			int width = ((CommaNumConst_first) singleStatement_seventh.getCommaNumConst()).getN1();
			Code.loadConst(width);
		} else {
			Code.loadConst(0);
		}
		if (singleStatement_seventh.getExpr().struct.equals(Tab.charType)) {
			Code.put(Code.bprint);
		} else {
			Code.put(Code.print);
		}
	}

	@Override
	public void visit(FactorSub_num factorSub_num) {
		Code.loadConst(factorSub_num.getN1()); 
	}

	@Override
	public void visit(FactorSub_chr factorSub_chr) {
		Code.loadConst(factorSub_chr.getC1());
	}

	@Override
	public void visit(FactorSub_bool factorSub_bool) {
		Code.loadConst(factorSub_bool.getB1()); 
	}

	@Override
	public void visit(AddopTermList_rek addopTermList_rek) {
		if (addopTermList_rek.getAddop() instanceof Addop_plus) {
			Code.put(Code.add);
		} else if (addopTermList_rek.getAddop() instanceof Addop_minus) {
			Code.put(Code.sub);
		}
	}

	@Override
	public void visit(MulopFactorList_rek mulopFactorList_rek) {
		if (mulopFactorList_rek.getMulop() instanceof Mulop_mul) {
			Code.put(Code.mul);
		} else if (mulopFactorList_rek.getMulop() instanceof Mulop_div) {
			Code.put(Code.div);
		} else if(mulopFactorList_rek.getMulop() instanceof Mulop_percent) {
			Code.put(Code.rem);
		}else {
			Code.put(Code.add);
			Code.put(Code.dup);
			Code.put(Code.mul);
		}
	}

	@Override
	public void visit(Factor factor) {
		if (factor.getUnary() instanceof Unary_minus) {
			Code.put(Code.neg);
		}
	}

	@Override
	public void visit(FactorSub_des factorSub_des) {
	    Obj designatorObj=factorSub_des.getDesignator().obj;
	    //Pristup elementu niza
	    if(designatorObj.getKind()==Obj.Elem) {
	    	if (designatorObj.getType().equals(Tab.charType)) {
				Code.put(Code.baload);
			} else {
				Code.put(Code.aload);
			}
	    }else if(designatorObj.getKind()==Obj.Con || designatorObj.getKind()==Obj.Var) {
	    	if (designatorObj != null && designatorObj != Tab.noObj) {
				Code.load(designatorObj);
			}
	    }else if(designatorObj.getKind()==Obj.Meth) {
	    	// poziv metode u izrazu - rezultat ostaje na steku
	    	if(factorSub_des.getActParsBracketsZeroOne() instanceof ActParsBracketsZeroOne_brackets) {
	    		if(designatorObj == Tab.find("ord") || designatorObj == Tab.find("chr")) {return;}
	    		else if(designatorObj == Tab.find("len")) {
	    			Code.put(Code.arraylength);
	    		} else {
	    			// korisnicka metoda
	    			int callAdr = Code.pc;
	    			Code.put(Code.call);
	    			Code.put2(designatorObj.getAdr() - callAdr);
	    			// bez pop - rezultat se koristi kao vrednost izraza
	    		}
	    	}
	    }
	}
	
	
	@Override
	public void visit(SingleStatement_fifth singleStatement_fifth) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	// assign objekta
	@Override
	public void visit(DesignatorRest_first designatorRest_first) {
		DesignatorStatement parentStatement = (DesignatorStatement) designatorRest_first.getParent();
		Designator designator = parentStatement.getDesignator();

		if (designator instanceof Designator_arr) {
			Designator_arr desArr = (Designator_arr) designator;
			Obj arrayObj = desArr.getDesignatorArrName().obj;
			if (arrayObj.getType().getElemType().equals(Tab.charType)) {
				Code.put(Code.bastore);
			} else {
				Code.put(Code.astore);
			}
		} else {
			Obj designatorObj = designator.obj;
			Code.store(designatorObj);
		}
	}

	// inc
	@Override
	public void visit(DesignatorRest_third designatorRest_third) {
		Obj designatorObj = ((DesignatorStatement) designatorRest_third.getParent()).getDesignator().obj;

		if (designatorObj.getKind() == Obj.Elem) {
			Code.put(Code.dup2); // [arr_ref, index, arr_ref, index]
			Code.put(Code.aload); // [arr_ref, index, value]
			Code.loadConst(1); // [arr_ref, index, value, 1]
			Code.put(Code.add); // [arr_ref, index, value+1]
			Code.put(Code.astore); // []
		} else {
			// x++
			Code.load(designatorObj);
			Code.loadConst(1);
			Code.put(Code.add);
			Code.store(designatorObj);
		}
	}

	// dec
	@Override
	public void visit(DesignatorRest_fourth designatorRest_fourth) {
		Obj designatorObj = ((DesignatorStatement) designatorRest_fourth.getParent()).getDesignator().obj;

		if (designatorObj.getKind() == Obj.Elem) {
			Code.put(Code.dup2); // [arr_ref, index, arr_ref, index]
			Code.put(Code.aload); // [arr_ref, index, value]
			Code.loadConst(-1); // [arr_ref, index, value, 1]
			Code.put(Code.add); // [arr_ref, index, value+1]
			Code.put(Code.astore); // []
		} else {
			// x++
			Code.load(designatorObj);
			Code.loadConst(-1);
			Code.put(Code.add);
			Code.store(designatorObj);
		}
	}

	// kreiranje nizova(new)
	@Override
	public void visit(FactorSub_new factorSub_new) {
		Code.put(Code.newarray);
		if (factorSub_new.struct.getElemType().equals(Tab.charType)) {
			Code.put(0);
		} else {
			Code.put(1);
		}
	}

	// read metoda
	@Override
	public void visit(SingleStatement_sixth singleStatement_sixth) {
		Obj designatorObj = singleStatement_sixth.getDesignator().obj;
		if (designatorObj.getType().equals(Tab.charType)) {
			Code.put(Code.bread);
		} else {
			Code.put(Code.read);
		}
		Code.store(designatorObj);
	}

	@Override
	public void visit(DesignatorArrName designatorArrName) {
		Obj arrayObj = designatorArrName.obj;
		if (arrayObj != null && arrayObj != Tab.noObj) {
			Code.load(arrayObj);
		}
	}
	
	//pozivi metoda
	@Override 
	public void visit(DesignatorRest_second designatorRest_second) {
		Obj designatorObj=((DesignatorStatement)designatorRest_second.getParent()).getDesignator().obj;
		if(designatorObj ==Tab.find("ord") || designatorObj == Tab.find("chr")) {return;}
		else if(designatorObj == Tab.find("len")){
			Code.put(Code.arraylength);
		}else {
			int callAdr=Code.pc;
			Code.put(Code.call);
			Code.put2(designatorObj.getAdr()-callAdr); //razlika metode koju zovemo i metode u kojoj se nalazimo
			 if (designatorObj.getType() != Tab.noType) {
			      Code.put(Code.pop);    // povratna vrednost metode
			  }
		}
	}

	
	@Override
	public void visit(DesignatorMore_dotLength designatorMore_dotLength) {
		Code.load(designatorMore_dotLength.obj);
		Code.put(Code.arraylength);
	}

	
	// ============================================================
	// IF-ELSE IMPLEMENTACIJA
	// Bajtkod struktura:
	//   [uslov]
	//   falseJump → ELSE/KRAJ   (ako uslov nije ispunjen, skoci na else ili kraj)
	//   [then-blok]
	//   jump → KRAJ             (preskoci else blok)
	//   [else-blok]             ← falseJump pokazuje ovde
	//   KRAJ                    ← jump iz then-a pokazuje ovde
	// ============================================================

	@Override
	public void visit(CondFact condFact) {
		// Generise uslovni skok - ako uslov NIJE ispunjen, skace na else/kraj
		// Adresa skoka je nepoznata pa stavljamo 0 (fixup kasnije)
		if(condFact.getRelopExprZeroOne() instanceof RelopExprZeroOne_epsilon) {
			// sam bool izraz (npr. if(ind1)) - poredimo sa 0
			Code.loadConst(0);
			currentCondTermFalseJumps.add(Code.pc+1);
			Code.putFalseJump(Code.ne, 0);
		}
		else {
			// relacioni izraz (npr. if(a > b)) - koristimo relop
			currentCondTermFalseJumps.add(Code.pc+1);
			Code.putFalseJump(currentRelop, 0);
		}
	}

	@Override
	public void visit(ElseMarker elseMarker) {
		// Posecuje se UVEK (i sa i bez else), pa moramo proveriti da li else postoji
		SingleStatement_second parent = (SingleStatement_second) elseMarker.getParent();
		if (parent.getElseBlock() instanceof ElseBlock_first) {
			// Ima else - emituj jump preko else-a i fixup false jumpova
			// 1. jump → KRAJ (preskoci else blok, adresa nepoznata - fixup u SingleStatement_second)
			elseFixupStack.push(Code.pc+1);
			Code.putJump(0);
			// 2. Fixup svih false jumpova iz uslova → else blok pocinje OVDE (Code.pc)
			List<Integer> tmp=condFixupStack.pop();
			for(int i: tmp) {
				Code.fixup(i);
			}
		}
		// Nema else - ne radi nista, SingleStatement_second ce fixup-ovati
	}

	@Override
	public void visit(SingleStatement_second singleStatement_second) {
		// Posecuje se na KRAJU celokupnog if-else-a
		if(singleStatement_second.getElseBlock() instanceof ElseBlock_epsilon) {
			// Nema else - fixup false jumpova iz uslova → skacu OVDE (kraj if-a)
			List<Integer> tmp=condFixupStack.pop();
			for(int i: tmp) {
				Code.fixup(i);
			}
		}else {
			// Ima else - fixup jump-a iz then-a → preskace else, skace OVDE (kraj else-a)
			Code.fixup(elseFixupStack.pop());
		}
	}
	@Override
	public void visit(Relop_first relop_first) {
		currentRelop=Code.eq;
	}
	
	@Override
	public void visit(Relop_second relop_second) {
		currentRelop=Code.ne;
	}
	
	@Override
	public void visit(Relop_third relop_third) {
		currentRelop=Code.gt;
	}
	
	@Override
	public void visit(Relop_fourth relop_fourth) {
		currentRelop=Code.ge;
	}
	
	@Override
	public void visit(Relop_fifth relop_fifth) {
		currentRelop=Code.lt;
	}
	
	@Override
	public void visit(Relop_sixth relop_sixth) {
		currentRelop=Code.le;
	}
	
	// ============================================================
	// AND/OR LOGIKA U USLOVIMA
	// Condition = CondTerm1 || CondTerm2 || ...
	// CondTerm  = CondFact1 && CondFact2 && ...
	//
	// AND (&&): ako bilo koji CondFact padne → skoci na sledeci CondTerm (OR) ili else/kraj
	// OR  (||): ako ceo CondTerm prodje → skoci na then-blok (short-circuit)
	// ============================================================

	@Override
	public void visit(OrMarker orMarker) {
		// Posecuje se IZMEDJU dva CondTerm-a (npr. izmedju "a>0" i "b>0" u "a>0 || b>0")
		// 1. Prethodni CondTerm je prosao (true) → jump na then-blok (OR short-circuit)
		//    Adresa then-bloka nepoznata - fixup u visit(Condition)
		currentCondTermTrueJumps.add(Code.pc + 1);
		Code.putJump(0);
		// 2. Fixup false jumpova prethodnog CondTerma → sledeci CondTerm pocinje OVDE
		//    (ako je prethodni CondTerm pao, probaj sledeci)
		for (int addr : currentCondTermFalseJumps) {
			Code.fixup(addr);
		}
		// 3. Ocisti listu za sledeci CondTerm
		currentCondTermFalseJumps = new ArrayList<Integer>();
	}

	@Override
	public void visit(Condition condition) {
		// Posecuje se na KRAJU celog uslova, pre then-bloka
		// 1. Fixup svih OR true jumpova → then-blok pocinje OVDE (Code.pc)
		for (int addr : currentCondTermTrueJumps) {
			Code.fixup(addr);
		}
		currentCondTermTrueJumps = new ArrayList<Integer>();
		// 2. Sacuvaj false jumpove poslednjeg CondTerma na stek
		//    ElseMarker ili SingleStatement_second ce ih fixup-ovati na else/kraj
		condFixupStack.push(new ArrayList<Integer>(currentCondTermFalseJumps));
		currentCondTermFalseJumps = new ArrayList<Integer>();
	}
	
	
	// ============================================================
	// FOR PETLJA
	// Bajtkod struktura:
	//   [inicijalizacija]
	//   USLOV:                  ← ForCondMarker pamti ovu adresu
	//   [uslov]
	//   falseJump → IZLAZ      (ako uslov nije ispunjen, izadji iz petlje)
	//   jump → TELO            ← ForIncrMarker emituje ovaj jump (preskace inkrement)
	//   INKREMENT:              ← ForIncrMarker pamti ovu adresu
	//   [inkrement]
	//   jump → USLOV           ← ForBodyMarker emituje ovaj jump (vraca se na proveru)
	//   TELO:                   ← ForBodyMarker fixup-uje jump iz ForIncrMarker → ovde
	//   [telo petlje]
	//   jump → INKREMENT       ← SingleStatement_ninth emituje ovaj jump
	//   IZLAZ:                  ← SingleStatement_ninth fixup-uje falseJump → ovde
	// ============================================================

	@Override
	public void visit(ForCondMarker forCondMarker) {
		// Pamti adresu pocetka uslova - koristi se za jump nazad iz inkrementa
		forCondAddrStack.push(Code.pc);
		breakFixupStack.push(new ArrayList<Integer>());
	}

	@Override
	public void visit(ForIncrMarker forIncrMarker) {
		// 1. jump → TELO (preskoci inkrement, adresa tela nepoznata - fixup u ForBodyMarker)
		forIncrFixupStack.push(Code.pc+1);
		Code.putJump(0);
		// 2. Pamti adresu pocetka inkrementa - koristi se za jump nazad iz tela
		forIncrAddrStack.push(Code.pc);
	}

	@Override
	public void visit(ForBodyMarker forBodyMarker) {
		// Posecuje se POSLE inkrementa, PRE tela
		// 1. jump → USLOV (vrati se na proveru uslova posle inkrementa)
		Code.putJump(forCondAddrStack.pop());
		// 2. Fixup jump-a iz ForIncrMarker → TELO pocinje OVDE (Code.pc)
		Code.fixup(forIncrFixupStack.pop());
	}

	@Override
	public void visit(SingleStatement_ninth singleStatement_ninth) {
		// Posecuje se na KRAJU for petlje (posle tela)
		// 1. jump → INKREMENT (vrati se na inkrement pa odatle na uslov)
		Code.putJump(forIncrAddrStack.pop());
		// 2. Fixup false jumpova iz uslova → IZLAZ je OVDE (Code.pc)
		//    Samo ako postoji uslov (for moze biti bez uslova - beskonacna petlja)
		if (singleStatement_ninth.getConditionZeroOne() instanceof ConditionZeroOne_first) {
			List<Integer> tmp = condFixupStack.pop();
			for (int addr : tmp) {
				Code.fixup(addr);
			}
		}
		
		List<Integer>breakAddrs=breakFixupStack.pop();
		for(int breakAddr: breakAddrs) {
			Code.fixup(breakAddr);
		}
			
	}
	
	//Break
	
	@Override
	public void visit(SingleStatement_third singleStatement_third) {
		if(!breakFixupStack.empty()){
			breakFixupStack.peek().add(Code.pc+1);
			Code.putJump(0);
		}
			
	}
	
	
	// Contunue
	
	 @Override
	  public void visit(SingleStatement_fourth singleStatement_fourth) {
	      Code.putJump(forIncrAddrStack.peek());
	 }
	 
	 // ============================================================
	 // SWITCH/CASE IMPLEMENTACIJA (sa fall-through podrskom)
	 //
	 // Bajtkod struktura:
	 //   [expr]                          ← vrednost za poredjenje
	 //   dup, const 1, jne → C2_CMP     ← case 1 comparison
	 //   pop                             ← match! ukloni expr
	 //   [case 1 stmts]
	 //   jmp → C2_BODY                   ← fall-through: preskoci comparison
	 //   C2_CMP: dup, const 2, jne → C3  ← case 2 comparison
	 //   pop                             ← match! ukloni expr
	 //   C2_BODY:                        ← fall-through ulazi ovde
	 //   [case 2 stmts]
	 //   ...
	 //   jmp → BREAK_END                 ← normalan tok (expr vec pop-ovan)
	 //   NO_MATCH: pop                   ← nijedan case nije match-ovao
	 //   BREAK_END:                      ← break skace ovde
	 // ============================================================

	 @Override
	 public void visit(SingleStatement_eighth singleStatement_eighth) {
		 // Normalan tok / fall-through iz poslednjeg case-a: expr je vec pop-ovan
		 // Treba preskociti no-match pop
		 int skipPopAddr = Code.pc + 1;
		 Code.putJump(0);
		 // No-match: poslednji case jne skace OVDE
		 if(!caseSkipFixupStack.empty()) {
			 Code.fixup(caseSkipFixupStack.pop());
		 }
		 Code.put(Code.pop); // ukloni expr vrednost (nijedan case nije match-ovao)
		 // Break i skip-pop skacu OVDE (expr je vec ociscen)
		 Code.fixup(skipPopAddr);
		 List<Integer> list=breakFixupStack.pop();
		 for(int elem:list) {
			 Code.fixup(elem);
		 }
	 }

	 @Override
	 public void visit(SwitchMarker switchMarker) {
		 breakFixupStack.push(new ArrayList<Integer>());
	 }

	 @Override
	 public void visit(CaseMarker caseMarker) {
		 // Za 2+ case: emit fall-through jump (preskoci comparison sledeceg case-a)
		 if(!caseSkipFixupStack.empty()) {
			 // Fall-through iz prethodnog case-a: preskoci comparison i pop
			 int fallThroughAddr = Code.pc + 1;
			 Code.putJump(0);
			 // No-match iz prethodnog case-a skace OVDE (na comparison)
			 Code.fixup(caseSkipFixupStack.pop());
			 // Comparison
			 Code.put(Code.dup);
			 int num=((CaseList_first)caseMarker.getParent()).getN1();
			 Code.loadConst(num);
			 caseSkipFixupStack.push(Code.pc+1);
			 Code.putFalseJump(Code.eq, 0);
			 // Match! Pop expr vrednost
			 Code.put(Code.pop);
			 // Fall-through skace OVDE (posle pop-a, direktno na statements)
			 Code.fixup(fallThroughAddr);
		 } else {
			 // Prvi case: nema fall-through, samo comparison
			 Code.put(Code.dup);
			 int num=((CaseList_first)caseMarker.getParent()).getN1();
			 Code.loadConst(num);
			 caseSkipFixupStack.push(Code.pc+1);
			 Code.putFalseJump(Code.eq, 0);
			 // Match! Pop expr vrednost
			 Code.put(Code.pop);
		 }
	 }
	 
	 
	 //ternary operator
	 
	 @Override 
	 public void visit(TernaryMarker ternaryMarker) {
		 ternaryFixupStack.push(Code.pc+1);
		 Code.putJump(0);
		 List<Integer> fixupList=condFixupStack.pop();
		 for(int elem:fixupList) {
			 Code.fixup(elem);
		 }
	 }
	 
	 @Override
	 public void visit(Expr_ternary expr_ternary) {
		 Code.fixup(ternaryFixupStack.pop());
	 }
	 

		
}


