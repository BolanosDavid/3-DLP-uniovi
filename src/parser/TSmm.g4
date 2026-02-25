grammar TSmm;	

// Reglas sintactica (minusculas)
program: (var_definition|func_definition)* main_definition EOF
       ;

var_definition:'let' ID (',' ID)* ':' type ';'
              ;
func_definition: 'function' ID '(' parameters ')' ':' (simple_type | 'void') '{' func_body '}'
               ;
func_body: (var_definition|statement)*
    ;
parameters: ID ':' simple_type (',' ID ':' simple_type)*
          | (ID ':' simple_type)?
          ;
/**
    Esta regla sintactica se usa para reconocer una función main obligatoria
    (Se añade también en program para poder hacerla obligatoria)
    Esto también se podría hacer en el semántico
*/
main_definition: 'function' 'main' '(' ')' ':' 'void' '{'func_body'}'
               ;
statement: 'log'expression(','expression)*';' //escritura
          |'input'expression(','expression)*';' //lectura
          /** Para cuando asignamos algo a una variable, podemos también detectar primero que
          * la primera expresion es asignable: una variable, por ejemplo. Podemos usar esta regla:
          * l_value: ID ('['expression']')* //Asignacion a Arays
                   | ID'.'expression //Asignacion a fields
                   | ID //Asignacion a variable
                   ;
          */
          | expression '=' expression';' //asignacion
          | 'if' '(' expression ')' block ('else' block)? //sentencia condicional
          | 'while' '(' expression ')' block // sentencia while
          |'return' expression';' //return
          |ID'('(expression(','expression)*)?')'';' //procedimiento
          ;
expression: ID'('expression(','expression)*')'
          | '('expression')'
          | expression'['expression']'
          | expression'.'ID
          /**
            Aquí podríamos identificar que el casteo solo permite tipos simples tanto en el
            sintactico como en el semantico. Para hacerlo en el sintactico, basta con cambiar
            esta regla para que en vez de aceptar 'type' acepte simple type
          */
          | '('expression 'as' type')'
          | '-' expression
          | '!' expression
          | expression ('/'|'*'| '%') expression
          | expression ('+'|'-') expression
          | expression ('>'|'>='|'<'|'<='|'!='|'==') expression
          | expression ('&&'|'||') expression
          | ID
          | INT_CONSTANT
          | REAL_CONSTANT
          | CHAR_CONSTANT
           ;

type: simple_type
    | array_type
    | record_type
    ;
block: statement
     | '{' statement* '}'
     ;
simple_type: 'int'
            | 'number'
            | 'char'
            ;


array_type: ('['INT_CONSTANT']')+simple_type
                  ;
record_type:'['recordfield*']'
                   ;
recordfield:'let' ID (',' ID)* ':' type ';'
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
