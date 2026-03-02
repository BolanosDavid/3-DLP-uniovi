grammar TSmm;	
@header{
    import ast.base.*;
    import ast.definitions.*;
    import ast.expressions.*;
    import ast.program.*;
    import ast.statements.*;
    import ast.types.*;
}

// Reglas sintactica (minusculas)
program returns [Program ast] locals [List<Definition> defs = new ArrayList<>()]:
    (definitions {$defs.addAll($definitions.ast);})* EOF {$ast = new Program($defs);}
    ;
definitions returns [List<Definition> ast = new ArrayList<>()]:
    (d1=var_definitions{$ast.addAll($d1.ast);}|d2=func_definitions{$ast.add($d2.ast);})* d3=main_definition {$ast.add($d3.ast);}
    ;

var_definitions returns[List<VarDefinition> ast = new ArrayList<>()]
    locals[List<Variable> variables = new ArrayList<>()]:
    'let' id1=ID{$variables.add(new Variable($id1.getLine(),
                                            $id1.getCharPositionInLine()+1,
                                            $id1.text));}
    (',' id2=ID{$variables.add(new Variable($id2.getLine(),
                                            $id2.getCharPositionInLine()+1,
                                            $id2.text));} )*
    ':' t1=type ';'
    {$variables.forEach(v ->$ast
                            .add(new VarDefinition(v.getLine(),
                                                   v.getColumn(),
                                                   v.getName(),
                                                   $t1.ast)));
    }
    ;
func_definitions returns[FuncDefinition ast]
    locals[List<VarDefinition> params = new ArrayList<>(),
           List<Type> paramTypes = new ArrayList<>(),
           Type returnType]:
    'function' id=ID '(' p=parameters{$params = $p.ast;} ')' ':'
    (st=simple_type{$returnType = $st.ast;} | 'void'{$returnType = VoidType.getInstance();})
    '{' body=func_body '}'
    {
        $params.forEach(param -> $paramTypes.add(param.getType()));
        FunctionType funcType = new FunctionType($paramTypes, $returnType);
        $ast = new FuncDefinition($id.getLine(),
                                  $id.getCharPositionInLine()+1,
                                  $id.text,
                                  funcType,
                                  $params,
                                  $body.ast);
    }
    ;


func_body returns[List<Statement> ast = new ArrayList<>()]:
    (v=var_definitions{$ast.addAll($v.ast);} | s=statement{$ast.add($s.ast);})*
    ;

parameters returns[List<VarDefinition> ast = new ArrayList<>()]:
    id1=ID ':' t1=simple_type {$ast.add(new VarDefinition($id1.getLine(), $id1.getCharPositionInLine()+1, $id1.text, $t1.ast));}
    (',' id2=ID ':' t2=simple_type {$ast.add(new VarDefinition($id2.getLine(), $id2.getCharPositionInLine()+1, $id2.text, $t2.ast));})*
    | /* vacío - sin parámetros */
    ;

/**
    Esta regla sintactica se usa para reconocer una función main obligatoria
    (Se añade también en program para poder hacerla obligatoria)
    Esto también se podría hacer en el semántico
*/
main_definition returns[FuncDefinition ast]:
    'function' m='main' '(' ')' ':' 'void' '{' body=func_body '}'
    {
        FunctionType funcType = new FunctionType(new ArrayList<>(), VoidType.getInstance());
        $ast = new FuncDefinition($m.getLine(),
                                  $m.getCharPositionInLine()+1,
                                  "main",
                                  funcType,
                                  new ArrayList<>(),
                                  $body.ast);
    }
    ;


statement returns [Statement ast] locals[List<Expression> exp = new ArrayList<>(), List<Statement> elseStmts = new ArrayList<>()]:
           'log' e1=expression{$exp.add($e1.ast);}(','e2=expression{$exp.add($e2.ast);} )*';'
           {$ast = new LogStatement($e1.ast.getLine(),$e1.ast.getColumn(),$exp);}
          |'input' e1=expression{$exp.add($e1.ast);}(','e2=expression{$exp.add($e2.ast);} )*';'
           {$ast = new InputStatement($e1.ast.getLine(),$e1.ast.getColumn(),$exp);}
          | e1=expression '=' e2=expression';'
           {$ast = new Assignment($e1.ast.getLine(),$e1.ast.getColumn(),$e1.ast,$e2.ast);}
          | 'if' '(' e1=expression ')' b1=block ('else' b2=block{$elseStmts.addAll($b2.ast);})?
           {$ast = new IfStatement($e1.ast.getLine(),$e1.ast.getColumn(),$e1.ast,$b1.ast,$elseStmts);}
          | 'while' '(' e1=expression ')' b1=block
           {$ast = new WhileStatement($e1.ast.getLine(),$e1.ast.getColumn(),$e1.ast,$b1.ast);}
          |'return' e1=expression';'
           {$ast = new ReturnStatement($e1.ast.getLine(),$e1.ast.getColumn(),$e1.ast);}
          | id=ID'('(e1=expression{$exp.add($e1.ast);}(','e2=expression{$exp.add($e2.ast);} )*)?')'';'
           {$ast = new Invocation($id.getLine(),$id.getCharPositionInLine()+1,new Variable($id.getLine(),$id.getCharPositionInLine()+1,$id.text),$exp);}
          ;

expression returns [Expression ast]:
          ID'('expression(','expression)*')'
          | '('expression')'
          | expression'['expression']'
          | expression'.'ID
          /**
            Aquí podríamos identificar que el casteo solo permite tipos simples tanto en el
            sintactico como en el semantico. Para hacerlo en el sintactico, basta con cambiar
            esta regla para que en vez de aceptar 'type' acepte simple type
          */
          | '('e1=expression 'as' t1=type')' {$ast = new Cast($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast, $t1.ast);}
          | '-' e1=expression {$ast = new UnaryMinus($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast);}
          | '!' e1=expression {$ast = new UnaryNot($e1.ast.getLine(), $e1.ast.getColumn(), $e1.ast);}
          | e1=expression op=('/'|'*'| '%') e2=expression {$ast = new Arithmetic($e1.ast.getLine(),$e1.ast.getColumn(),$e1.ast,$e2.ast,$op.text);}
          //Siempre se sacan la linea y columna de la primera expresion
          | e1=expression op=('+'|'-') e2=expression {$ast = new Arithmetic($e1.ast.getLine(),$e1.ast.getColumn(),$e1.ast,$e2.ast,$op.text);}
          | e1=expression op=('>'|'>='|'<'|'<='|'!='|'==') e2=expression {$ast = new Comparison($e1.ast.getLine(),$e1.ast.getColumn(),$e1.ast,$e2.ast,$op.text);}
          | e1=expression op=('&&'|'||') e2=expression {$ast = new Arithmetic($e1.ast.getLine(),$e1.ast.getColumn(),$e1.ast,$e2.ast,$op.text);}
          | i=ID
          | i=INT_CONSTANT {$ast = new IntLiteral($i.getLine(),$i.getCharPositionInLine()+1,LexerHelper.lexemeToInt($i.text));}
          | i=REAL_CONSTANT {$ast = new NumberLiteral($i.getLine(),$i.getCharPositionInLine()+1,LexerHelper.lexemeToReal($i.text));}
          | i=CHAR_CONSTANT {$ast = new CharLiteral($i.getLine(),$i.getCharPositionInLine()+1,LexerHelper.lexemeToChar($i.text));}
           ;

type returns[Type ast]:
    s=simple_type {$ast = $s.ast;}
    | a=array_type {$ast = $a.ast;}
    | r=record_type {$ast = $r.ast;}
    ;
block returns[List<Statement> ast = new ArrayList<>()]:
     s1=statement {$ast.add($s1.ast);}
     | '{' (s2=statement{$ast.add($s2.ast);})* '}'
     ;
simple_type returns[Type ast]:
            'int' {$ast = IntType.getInstance();}
            | 'number' {$ast = NumberType.getInstance();}
            | 'char' {$ast = CharType.getInstance();}
            ;


array_type returns[Type ast]:
('['i1=INT_CONSTANT']') t1=simple_type {$ast = new ArrayType(LexerHelper.lexemeToInt($i1.text), $t1.ast);}
                             ;
recordfield returns [RecordField ast]:
    'let' i1=ID ':' t1=type ';'
    { $ast = new RecordField($i1.getLine(), $i1.getCharPositionInLine()+1, $i1.text, $t1.ast); }
    ;

record_type returns [RecordType ast] locals[List<RecordField> fields = new ArrayList<>()]:
'[' (r=recordfield{$fields.add($r.ast);})* ']' { $ast = new RecordType($fields); }
                                    ;
// Reglas lexicas (mayusculas)
// Fragmentos
fragment
BASE: [0-9]* '.' [0-9]*
    ;
fragment
EXPONENTE: [eE][+-]?[0-9]+
        ;
fragment
LETTER: [A-Za-z]
     ;
fragment
MID_CHAR: '\\n'
       | '\\t'
       | '\\' [0-9][0-9][0-9]
       ;

LINE_COMMENT: '//' ~[\r\n]* -> skip

             ;

BLOCK_COMMENT: '/*' .*? '*/' -> skip
             ;


WHITES: [ \n\t\r]+ -> skip
       ;

ID: (LETTER | '_') (LETTER | [0-9] | '_')*
  ;

INT_CONSTANT: '0'
            | [1-9][0-9]*
            ;

REAL_CONSTANT: BASE EXPONENTE?
             | INT_CONSTANT EXPONENTE
             ;

CHAR_CONSTANT: '\'' (MID_CHAR | ~['\\]) '\''
             ;
