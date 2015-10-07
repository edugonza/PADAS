// Generated from poql.g4 by ANTLR 4.5.1
package org.processmining.database.metamodel.poql;

  import java.util.List;
  import org.processmining.openslex.metamodel.SLEXMMCase;
  import org.processmining.openslex.metamodel.SLEXMMObject;
  import org.processmining.openslex.metamodel.SLEXMMObjectVersion;
  import org.processmining.openslex.metamodel.SLEXMMEvent;
  import org.processmining.openslex.metamodel.SLEXMMActivity;
  import org.processmining.openslex.metamodel.SLEXMMCase;
  import org.processmining.openslex.metamodel.SLEXMMClass; 

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link poqlParser}.
 */
public interface poqlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link poqlParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(poqlParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(poqlParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#things}.
	 * @param ctx the parse tree
	 */
	void enterThings(poqlParser.ThingsContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#things}.
	 * @param ctx the parse tree
	 */
	void exitThings(poqlParser.ThingsContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#objects}.
	 * @param ctx the parse tree
	 */
	void enterObjects(poqlParser.ObjectsContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#objects}.
	 * @param ctx the parse tree
	 */
	void exitObjects(poqlParser.ObjectsContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#cases}.
	 * @param ctx the parse tree
	 */
	void enterCases(poqlParser.CasesContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#cases}.
	 * @param ctx the parse tree
	 */
	void exitCases(poqlParser.CasesContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#events}.
	 * @param ctx the parse tree
	 */
	void enterEvents(poqlParser.EventsContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#events}.
	 * @param ctx the parse tree
	 */
	void exitEvents(poqlParser.EventsContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#classes}.
	 * @param ctx the parse tree
	 */
	void enterClasses(poqlParser.ClassesContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#classes}.
	 * @param ctx the parse tree
	 */
	void exitClasses(poqlParser.ClassesContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#versions}.
	 * @param ctx the parse tree
	 */
	void enterVersions(poqlParser.VersionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#versions}.
	 * @param ctx the parse tree
	 */
	void exitVersions(poqlParser.VersionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#activities}.
	 * @param ctx the parse tree
	 */
	void enterActivities(poqlParser.ActivitiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#activities}.
	 * @param ctx the parse tree
	 */
	void exitActivities(poqlParser.ActivitiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter(poqlParser.FilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter(poqlParser.FilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#filter_expression}.
	 * @param ctx the parse tree
	 */
	void enterFilter_expression(poqlParser.Filter_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#filter_expression}.
	 * @param ctx the parse tree
	 */
	void exitFilter_expression(poqlParser.Filter_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(poqlParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(poqlParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#allObjects}.
	 * @param ctx the parse tree
	 */
	void enterAllObjects(poqlParser.AllObjectsContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#allObjects}.
	 * @param ctx the parse tree
	 */
	void exitAllObjects(poqlParser.AllObjectsContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#allCases}.
	 * @param ctx the parse tree
	 */
	void enterAllCases(poqlParser.AllCasesContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#allCases}.
	 * @param ctx the parse tree
	 */
	void exitAllCases(poqlParser.AllCasesContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#allEvents}.
	 * @param ctx the parse tree
	 */
	void enterAllEvents(poqlParser.AllEventsContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#allEvents}.
	 * @param ctx the parse tree
	 */
	void exitAllEvents(poqlParser.AllEventsContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#allClasses}.
	 * @param ctx the parse tree
	 */
	void enterAllClasses(poqlParser.AllClassesContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#allClasses}.
	 * @param ctx the parse tree
	 */
	void exitAllClasses(poqlParser.AllClassesContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#allVersions}.
	 * @param ctx the parse tree
	 */
	void enterAllVersions(poqlParser.AllVersionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#allVersions}.
	 * @param ctx the parse tree
	 */
	void exitAllVersions(poqlParser.AllVersionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#allActivities}.
	 * @param ctx the parse tree
	 */
	void enterAllActivities(poqlParser.AllActivitiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#allActivities}.
	 * @param ctx the parse tree
	 */
	void exitAllActivities(poqlParser.AllActivitiesContext ctx);
}