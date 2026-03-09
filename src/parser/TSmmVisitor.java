// Generated from C:/Users/UO302313/IdeaProjects/3-DLP-uniovi/src/parser/TSmm.g4 by ANTLR 4.13.2
package parser;

    import java.util.*;
    import ast.*;
    import ast.base.*;
    import ast.definitions.*;
    import ast.expressions.*;
    import ast.statements.*;
    import ast.types.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TSmmParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TSmmVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TSmmParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(TSmmParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#definitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinitions(TSmmParser.DefinitionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#var_definitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_definitions(TSmmParser.Var_definitionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#func_definitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_definitions(TSmmParser.Func_definitionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#func_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_body(TSmmParser.Func_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(TSmmParser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#main_definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain_definition(TSmmParser.Main_definitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(TSmmParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(TSmmParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(TSmmParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(TSmmParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#simple_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_type(TSmmParser.Simple_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#array_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_type(TSmmParser.Array_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#recordfield}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordfield(TSmmParser.RecordfieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#record_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecord_type(TSmmParser.Record_typeContext ctx);
}