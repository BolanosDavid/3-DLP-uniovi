// Generated from C:/Users/UO302313/IdeaProjects/3-DLP-uniovi/src/parser/TSmm.g4 by ANTLR 4.13.2
package parser;

    import java.util.*;
    import ast.*;
    import ast.base.*;
    import ast.definitions.*;
    import ast.expressions.*;
    import ast.statements.*;
    import ast.types.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TSmmParser}.
 */
public interface TSmmListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TSmmParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(TSmmParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(TSmmParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#definitions}.
	 * @param ctx the parse tree
	 */
	void enterDefinitions(TSmmParser.DefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#definitions}.
	 * @param ctx the parse tree
	 */
	void exitDefinitions(TSmmParser.DefinitionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#var_definitions}.
	 * @param ctx the parse tree
	 */
	void enterVar_definitions(TSmmParser.Var_definitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#var_definitions}.
	 * @param ctx the parse tree
	 */
	void exitVar_definitions(TSmmParser.Var_definitionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#func_definitions}.
	 * @param ctx the parse tree
	 */
	void enterFunc_definitions(TSmmParser.Func_definitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#func_definitions}.
	 * @param ctx the parse tree
	 */
	void exitFunc_definitions(TSmmParser.Func_definitionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#func_body}.
	 * @param ctx the parse tree
	 */
	void enterFunc_body(TSmmParser.Func_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#func_body}.
	 * @param ctx the parse tree
	 */
	void exitFunc_body(TSmmParser.Func_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(TSmmParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(TSmmParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#main_definition}.
	 * @param ctx the parse tree
	 */
	void enterMain_definition(TSmmParser.Main_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#main_definition}.
	 * @param ctx the parse tree
	 */
	void exitMain_definition(TSmmParser.Main_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(TSmmParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(TSmmParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(TSmmParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(TSmmParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(TSmmParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(TSmmParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(TSmmParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(TSmmParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#simple_type}.
	 * @param ctx the parse tree
	 */
	void enterSimple_type(TSmmParser.Simple_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#simple_type}.
	 * @param ctx the parse tree
	 */
	void exitSimple_type(TSmmParser.Simple_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#array_type}.
	 * @param ctx the parse tree
	 */
	void enterArray_type(TSmmParser.Array_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#array_type}.
	 * @param ctx the parse tree
	 */
	void exitArray_type(TSmmParser.Array_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#recordfield}.
	 * @param ctx the parse tree
	 */
	void enterRecordfield(TSmmParser.RecordfieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#recordfield}.
	 * @param ctx the parse tree
	 */
	void exitRecordfield(TSmmParser.RecordfieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#record_type}.
	 * @param ctx the parse tree
	 */
	void enterRecord_type(TSmmParser.Record_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#record_type}.
	 * @param ctx the parse tree
	 */
	void exitRecord_type(TSmmParser.Record_typeContext ctx);
}