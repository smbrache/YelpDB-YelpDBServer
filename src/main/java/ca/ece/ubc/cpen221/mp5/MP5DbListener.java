package ca.ece.ubc.cpen221.mp5;

// Generated from MP5Db.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MP5DbParser}.
 */
public interface MP5DbListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MP5DbParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(MP5DbParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link MP5DbParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(MP5DbParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link MP5DbParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(MP5DbParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MP5DbParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(MP5DbParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MP5DbParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(MP5DbParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MP5DbParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(MP5DbParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MP5DbParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(MP5DbParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link MP5DbParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(MP5DbParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link MP5DbParser#or}.
	 * @param ctx the parse tree
	 */
	void enterOr(MP5DbParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by {@link MP5DbParser#or}.
	 * @param ctx the parse tree
	 */
	void exitOr(MP5DbParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by {@link MP5DbParser#and}.
	 * @param ctx the parse tree
	 */
	void enterAnd(MP5DbParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by {@link MP5DbParser#and}.
	 * @param ctx the parse tree
	 */
	void exitAnd(MP5DbParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by {@link MP5DbParser#ineq}.
	 * @param ctx the parse tree
	 */
	void enterIneq(MP5DbParser.IneqContext ctx);
	/**
	 * Exit a parse tree produced by {@link MP5DbParser#ineq}.
	 * @param ctx the parse tree
	 */
	void exitIneq(MP5DbParser.IneqContext ctx);
	/**
	 * Enter a parse tree produced by {@link MP5DbParser#gt}.
	 * @param ctx the parse tree
	 */
	void enterGt(MP5DbParser.GtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MP5DbParser#gt}.
	 * @param ctx the parse tree
	 */
	void exitGt(MP5DbParser.GtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MP5DbParser#gte}.
	 * @param ctx the parse tree
	 */
	void enterGte(MP5DbParser.GteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MP5DbParser#gte}.
	 * @param ctx the parse tree
	 */
	void exitGte(MP5DbParser.GteContext ctx);
	/**
	 * Enter a parse tree produced by {@link MP5DbParser#lt}.
	 * @param ctx the parse tree
	 */
	void enterLt(MP5DbParser.LtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MP5DbParser#lt}.
	 * @param ctx the parse tree
	 */
	void exitLt(MP5DbParser.LtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MP5DbParser#lte}.
	 * @param ctx the parse tree
	 */
	void enterLte(MP5DbParser.LteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MP5DbParser#lte}.
	 * @param ctx the parse tree
	 */
	void exitLte(MP5DbParser.LteContext ctx);
	/**
	 * Enter a parse tree produced by {@link MP5DbParser#eq}.
	 * @param ctx the parse tree
	 */
	void enterEq(MP5DbParser.EqContext ctx);
	/**
	 * Exit a parse tree produced by {@link MP5DbParser#eq}.
	 * @param ctx the parse tree
	 */
	void exitEq(MP5DbParser.EqContext ctx);
	/**
	 * Enter a parse tree produced by {@link MP5DbParser#in}.
	 * @param ctx the parse tree
	 */
	void enterIn(MP5DbParser.InContext ctx);
	/**
	 * Exit a parse tree produced by {@link MP5DbParser#in}.
	 * @param ctx the parse tree
	 */
	void exitIn(MP5DbParser.InContext ctx);
	/**
	 * Enter a parse tree produced by {@link MP5DbParser#category}.
	 * @param ctx the parse tree
	 */
	void enterCategory(MP5DbParser.CategoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MP5DbParser#category}.
	 * @param ctx the parse tree
	 */
	void exitCategory(MP5DbParser.CategoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link MP5DbParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(MP5DbParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MP5DbParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(MP5DbParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MP5DbParser#rating}.
	 * @param ctx the parse tree
	 */
	void enterRating(MP5DbParser.RatingContext ctx);
	/**
	 * Exit a parse tree produced by {@link MP5DbParser#rating}.
	 * @param ctx the parse tree
	 */
	void exitRating(MP5DbParser.RatingContext ctx);
	/**
	 * Enter a parse tree produced by {@link MP5DbParser#price}.
	 * @param ctx the parse tree
	 */
	void enterPrice(MP5DbParser.PriceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MP5DbParser#price}.
	 * @param ctx the parse tree
	 */
	void exitPrice(MP5DbParser.PriceContext ctx);
}