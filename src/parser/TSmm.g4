grammar TSmm;	

program: CHAR_CONSTANT
       ;
// Fragmentos
fragment
BASE: [0-9]* '.' [0-9]*
    ;
fragment
EXPONENTE: [eE][+-]?[0-9]+
        ;
fragment
LETTER: [A-Za-z]+
     ;
fragment
MID_CHAR: '\\n'
       | '\\t'
       | '\\' [0-9][0-9][0-9]
       ;

 // Reglas lexicas

LINE_COMMENT: '//' ~[\r\n]* -> skip

             ;

BLOCK_COMMENT: '/*' .*? '*/' -> skip
             ;

WHITES: [ \n\t\r]+ -> skip
       ;

ID: (LETTER | '_')+ ( LETTER | [0-9] | '_')+
  ;

INT_CONSTANT: '0'
            | [1-9][0-9]*
            ;

REAL_CONSTANT: BASE EXPONENTE?
             | INT_CONSTANT EXPONENTE
             ;

CHAR_CONSTANT: '\'' (MID_CHAR | ~['\\]) '\''
             ;
