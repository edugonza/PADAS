// Generated from poql.g4 by ANTLR 4.5.1
package org.processmining.database.metamodel.poql;

  import java.util.List;
  import java.util.Set;
  import org.processmining.openslex.metamodel.SLEXMMCase;
  import org.processmining.openslex.metamodel.SLEXMMObject;
  import org.processmining.openslex.metamodel.SLEXMMObjectVersion;
  import org.processmining.openslex.metamodel.SLEXMMEvent;
  import org.processmining.openslex.metamodel.SLEXMMActivity;
  import org.processmining.openslex.metamodel.SLEXMMCase;
  import org.processmining.openslex.metamodel.SLEXMMClass;
  import org.processmining.openslex.metamodel.SLEXMMActivityInstance;
  import org.processmining.openslex.metamodel.SLEXMMRelation;
  import org.processmining.openslex.metamodel.SLEXMMRelationship;
  import org.processmining.openslex.metamodel.SLEXMMAttribute;

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
	 * Enter a parse tree produced by {@link poqlParser#set_operator}.
	 * @param ctx the parse tree
	 */
	void enterSet_operator(poqlParser.Set_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#set_operator}.
	 * @param ctx the parse tree
	 */
	void exitSet_operator(poqlParser.Set_operatorContext ctx);
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
	 * Enter a parse tree produced by {@link poqlParser#relations}.
	 * @param ctx the parse tree
	 */
	void enterRelations(poqlParser.RelationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#relations}.
	 * @param ctx the parse tree
	 */
	void exitRelations(poqlParser.RelationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#relationships}.
	 * @param ctx the parse tree
	 */
	void enterRelationships(poqlParser.RelationshipsContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#relationships}.
	 * @param ctx the parse tree
	 */
	void exitRelationships(poqlParser.RelationshipsContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#activityinstances}.
	 * @param ctx the parse tree
	 */
	void enterActivityinstances(poqlParser.ActivityinstancesContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#activityinstances}.
	 * @param ctx the parse tree
	 */
	void exitActivityinstances(poqlParser.ActivityinstancesContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#attributes}.
	 * @param ctx the parse tree
	 */
	void enterAttributes(poqlParser.AttributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#attributes}.
	 * @param ctx the parse tree
	 */
	void exitAttributes(poqlParser.AttributesContext ctx);
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
	 * Enter a parse tree produced by {@link poqlParser#node}.
	 * @param ctx the parse tree
	 */
	void enterNode(poqlParser.NodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#node}.
	 * @param ctx the parse tree
	 */
	void exitNode(poqlParser.NodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(poqlParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(poqlParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#ids}.
	 * @param ctx the parse tree
	 */
	void enterIds(poqlParser.IdsContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#ids}.
	 * @param ctx the parse tree
	 */
	void exitIds(poqlParser.IdsContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#id_version}.
	 * @param ctx the parse tree
	 */
	void enterId_version(poqlParser.Id_versionContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#id_version}.
	 * @param ctx the parse tree
	 */
	void exitId_version(poqlParser.Id_versionContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#id_object}.
	 * @param ctx the parse tree
	 */
	void enterId_object(poqlParser.Id_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#id_object}.
	 * @param ctx the parse tree
	 */
	void exitId_object(poqlParser.Id_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#id_class}.
	 * @param ctx the parse tree
	 */
	void enterId_class(poqlParser.Id_classContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#id_class}.
	 * @param ctx the parse tree
	 */
	void exitId_class(poqlParser.Id_classContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#id_relationship}.
	 * @param ctx the parse tree
	 */
	void enterId_relationship(poqlParser.Id_relationshipContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#id_relationship}.
	 * @param ctx the parse tree
	 */
	void exitId_relationship(poqlParser.Id_relationshipContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#id_relation}.
	 * @param ctx the parse tree
	 */
	void enterId_relation(poqlParser.Id_relationContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#id_relation}.
	 * @param ctx the parse tree
	 */
	void exitId_relation(poqlParser.Id_relationContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#id_event}.
	 * @param ctx the parse tree
	 */
	void enterId_event(poqlParser.Id_eventContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#id_event}.
	 * @param ctx the parse tree
	 */
	void exitId_event(poqlParser.Id_eventContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#id_case}.
	 * @param ctx the parse tree
	 */
	void enterId_case(poqlParser.Id_caseContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#id_case}.
	 * @param ctx the parse tree
	 */
	void exitId_case(poqlParser.Id_caseContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#id_activity_instance}.
	 * @param ctx the parse tree
	 */
	void enterId_activity_instance(poqlParser.Id_activity_instanceContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#id_activity_instance}.
	 * @param ctx the parse tree
	 */
	void exitId_activity_instance(poqlParser.Id_activity_instanceContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#id_activity}.
	 * @param ctx the parse tree
	 */
	void enterId_activity(poqlParser.Id_activityContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#id_activity}.
	 * @param ctx the parse tree
	 */
	void exitId_activity(poqlParser.Id_activityContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#id_attribute}.
	 * @param ctx the parse tree
	 */
	void enterId_attribute(poqlParser.Id_attributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#id_attribute}.
	 * @param ctx the parse tree
	 */
	void exitId_attribute(poqlParser.Id_attributeContext ctx);
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
	/**
	 * Enter a parse tree produced by {@link poqlParser#allRelations}.
	 * @param ctx the parse tree
	 */
	void enterAllRelations(poqlParser.AllRelationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#allRelations}.
	 * @param ctx the parse tree
	 */
	void exitAllRelations(poqlParser.AllRelationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#allRelationships}.
	 * @param ctx the parse tree
	 */
	void enterAllRelationships(poqlParser.AllRelationshipsContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#allRelationships}.
	 * @param ctx the parse tree
	 */
	void exitAllRelationships(poqlParser.AllRelationshipsContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#allActivityInstances}.
	 * @param ctx the parse tree
	 */
	void enterAllActivityInstances(poqlParser.AllActivityInstancesContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#allActivityInstances}.
	 * @param ctx the parse tree
	 */
	void exitAllActivityInstances(poqlParser.AllActivityInstancesContext ctx);
	/**
	 * Enter a parse tree produced by {@link poqlParser#allAttributes}.
	 * @param ctx the parse tree
	 */
	void enterAllAttributes(poqlParser.AllAttributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link poqlParser#allAttributes}.
	 * @param ctx the parse tree
	 */
	void exitAllAttributes(poqlParser.AllAttributesContext ctx);
}