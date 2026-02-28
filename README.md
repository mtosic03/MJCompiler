# 🧩 MicroJava Compiler

A fully functional compiler for the **MicroJava** programming language, built as a university project at the School of Electrical Engineering, University of Belgrade (ETF). The compiler translates syntactically and semantically valid MicroJava source programs into bytecode executable on the MicroJava Virtual Machine (MJVM).

---

## 📌 About MicroJava

MicroJava is a simplified, Java-like programming language designed for educational purposes. It supports:
- Primitive types (`int`, `char`, `bool`)
- Arrays, enumerations, and inner classes
- Inheritance, polymorphism, and abstract classes
- Global/local variables, constants, and methods

---

## 🏗️ Compiler Phases

The compiler is implemented in four classic phases:

### 1. 🔍 Lexical Analysis (Scanner)
- Written as a `.flex` specification and processed with **JFlex**
- Recognizes identifiers, constants, keywords, operators, and comments
- Skips whitespace and comments
- Reports lexical errors with line and column information

### 2. 🌳 Syntactic Analysis (Parser)
- Written as a `.cup` specification using the **AST-CUP** generator (an extended version of CUP)
- Implements an **LALR(1) grammar** based on the MicroJava language specification
- Builds an **Abstract Syntax Tree (AST)** automatically from the grammar
- Supports **error recovery** for common syntax mistakes (missing semicolons, bad parameters, etc.)
- Reports syntax errors with line numbers and descriptive messages

### 3. ✅ Semantic Analysis
- Traverses the AST using the **Visitor pattern**
- Performs **symbol table** population and lookup using the provided `symboltable.jar` library
- Checks all contextual constraints: type compatibility, scope rules, return types, etc.
- Reports semantic errors (undeclared variables, type mismatches, etc.) with line numbers

### 4. ⚙️ Code Generation
- Also traverses the AST using the Visitor pattern
- Emits **MicroJava bytecode** instructions using the `mj-runtime.jar` library
- Outputs an executable `.obj` file for the MJVM

---

## 🛠️ Tools & Technologies

| Tool | Purpose |
|------|---------|
| Java 1.8 | Implementation language |
| JFlex | Lexer generator (from `.flex` spec) |
| AST-CUP | Parser + AST generator (from `.cup` spec) |
| symboltable.jar | Symbol table implementation |
| mj-runtime.jar | Code emission & MJVM execution |

---

## 📁 Project Structure

```
.
├── src/
│   ├── spec/
│   │   ├── mjlexer.flex           # Lexer specification
│   │   ├── mjparser.cup           # AST-CUP parser specification
│   │   └── mjparser_astbuild.cup  # Generated standard CUP spec
│   └── rs/ac/bg/etf/pp1/
│       ├── Compiler.java          # Main entry point
│       ├── SemanticAnalyzer.java  # Semantic analysis visitor
│       ├── CodeGenerator.java     # Code generation visitor
│       └── ast/                   # Auto-generated AST node classes
├── test/
│   ├── *.MJ                       # MicroJava test source files
│   ├── *.out                      # Expected standard output
│   └── *.err                      # Expected error output
└── lib/
    ├── cup_v10k.jar
    ├── symboltable-1-1.jar
    └── mj-runtime-1.1.jar
```

---

## 🚀 How to Run

### Compile a MicroJava program

```bash
java rs.ac.bg.etf.pp1.Compiler <input.mj> <output.obj>
```

### Run the compiled bytecode on MJVM

```bash
java -cp mj-runtime-1.1.jar rs.etf.pp1.mj.runtime.Run <output.obj>
```

### Redirect output for inspection

```bash
java rs.ac.bg.etf.pp1.Compiler input.mj output.obj > result.out 2> result.err
```

---

## 📄 Example MicroJava Program

```java
program Program
  const int pi = 3, e = 2;
  int a, b;
{
  void main()
    int i; int x[];
  {
    x = new int[5];
    for (i = 0; i < x.length; i++) {
      read(x[i]);
    }
    print(x[0] == 'e' ? e : pi);
  }
}
```

---
