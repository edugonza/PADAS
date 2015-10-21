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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class poqlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		UNION=1, INTERSECTION=2, EXCLUDING=3, CASESOF=4, OBJECTSOF=5, EVENTSOF=6, 
		CLASSESOF=7, VERSIONSOF=8, ACTIVITIESOF=9, VERSIONSRELATEDTO=10, RELATIONSOF=11, 
		RELATIONSHIPSOF=12, ACTIVITYINSTANCESOF=13, ATTRIBUTESOF=14, ALLOBJECTS=15, 
		ALLCASES=16, ALLEVENTS=17, ALLCLASSES=18, ALLVERSIONS=19, ALLACTIVITIES=20, 
		ALLRELATIONS=21, ALLRELATIONSHIPS=22, ALLACTIVITYINSTANCES=23, ALLATTRIBUTES=24, 
		ID=25, DATAMODEL_ID=26, NAME=27, CLASS_ID=28, SOURCE=29, TARGET=30, OBJECT_ID=31, 
		START_TIMESTAMP=32, END_TIMESTAMP=33, SOURCE_OBJECT_VERSION_ID=34, TARGET_OBJECT_VERSION_ID=35, 
		RELATIONSHIP_ID=36, ACTIVITY_INSTANCE_ID=37, ORDERING=38, TIMESTAMP=39, 
		LIFECYCLE=40, RESOURCE=41, ACTIVITY_ID=42, PROCESS_ID=43, OPEN_PARENTHESIS=44, 
		CLOSE_PARENTHESIS=45, WHERE=46, EQUAL=47, DIFFERENT=48, EQUAL_OR_GREATER=49, 
		EQUAL_OR_SMALLER=50, GREATER=51, SMALLER=52, CONTAINS=53, AND=54, OR=55, 
		NOT=56, CHANGED=57, FROM=58, TO=59, STRING=60, IDATT=61, IDNOATT=62, WS=63;
	public static final int
		RULE_prog = 0, RULE_set_operator = 1, RULE_things = 2, RULE_objects = 3, 
		RULE_cases = 4, RULE_events = 5, RULE_classes = 6, RULE_versions = 7, 
		RULE_activities = 8, RULE_relations = 9, RULE_relationships = 10, RULE_activityinstances = 11, 
		RULE_attributes = 12, RULE_filter = 13, RULE_filter_expression = 14, RULE_node = 15, 
		RULE_operator = 16, RULE_ids = 17, RULE_id_version = 18, RULE_id_object = 19, 
		RULE_id_class = 20, RULE_id_relationship = 21, RULE_id_relation = 22, 
		RULE_id_event = 23, RULE_id_case = 24, RULE_id_activity_instance = 25, 
		RULE_id_activity = 26, RULE_id_attribute = 27, RULE_allObjects = 28, RULE_allCases = 29, 
		RULE_allEvents = 30, RULE_allClasses = 31, RULE_allVersions = 32, RULE_allActivities = 33, 
		RULE_allRelations = 34, RULE_allRelationships = 35, RULE_allActivityInstances = 36, 
		RULE_allAttributes = 37;
	public static final String[] ruleNames = {
		"prog", "set_operator", "things", "objects", "cases", "events", "classes", 
		"versions", "activities", "relations", "relationships", "activityinstances", 
		"attributes", "filter", "filter_expression", "node", "operator", "ids", 
		"id_version", "id_object", "id_class", "id_relationship", "id_relation", 
		"id_event", "id_case", "id_activity_instance", "id_activity", "id_attribute", 
		"allObjects", "allCases", "allEvents", "allClasses", "allVersions", "allActivities", 
		"allRelations", "allRelationships", "allActivityInstances", "allAttributes"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "UNION", "INTERSECTION", "EXCLUDING", "CASESOF", "OBJECTSOF", "EVENTSOF", 
		"CLASSESOF", "VERSIONSOF", "ACTIVITIESOF", "VERSIONSRELATEDTO", "RELATIONSOF", 
		"RELATIONSHIPSOF", "ACTIVITYINSTANCESOF", "ATTRIBUTESOF", "ALLOBJECTS", 
		"ALLCASES", "ALLEVENTS", "ALLCLASSES", "ALLVERSIONS", "ALLACTIVITIES", 
		"ALLRELATIONS", "ALLRELATIONSHIPS", "ALLACTIVITYINSTANCES", "ALLATTRIBUTES", 
		"ID", "DATAMODEL_ID", "NAME", "CLASS_ID", "SOURCE", "TARGET", "OBJECT_ID", 
		"START_TIMESTAMP", "END_TIMESTAMP", "SOURCE_OBJECT_VERSION_ID", "TARGET_OBJECT_VERSION_ID", 
		"RELATIONSHIP_ID", "ACTIVITY_INSTANCE_ID", "ORDERING", "TIMESTAMP", "LIFECYCLE", 
		"RESOURCE", "ACTIVITY_ID", "PROCESS_ID", "OPEN_PARENTHESIS", "CLOSE_PARENTHESIS", 
		"WHERE", "EQUAL", "DIFFERENT", "EQUAL_OR_GREATER", "EQUAL_OR_SMALLER", 
		"GREATER", "SMALLER", "CONTAINS", "AND", "OR", "NOT", "CHANGED", "FROM", 
		"TO", "STRING", "IDATT", "IDNOATT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "poql.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	  
	  public POQLFunctions poql = new POQLFunctions();

	  public static final int ID_TYPE_OBJECT = 1;
	  public static final int ID_TYPE_EVENT = 2;
	  public static final int ID_TYPE_CLASS = 3;
	  public static final int ID_TYPE_VERSION = 4;
	  public static final int ID_TYPE_ACTIVITY = 5;
	  public static final int ID_TYPE_RELATION = 6;
	  public static final int ID_TYPE_RELATIONSHIP = 7;
	  public static final int ID_TYPE_ACTIVITY_INSTANCE = 8;
	  public static final int ID_TYPE_CASE = 9;
	  public static final int ID_TYPE_ATTRIBUTE = 10;
	  
	  @Override
	  public void notifyErrorListeners(Token offendingToken, String msg, RecognitionException ex)
	  {
	  	//IntervalSet expectedTokens = getExpectedTokensWithinCurrentRule();
	  	IntervalSet expectedTokens = getExpectedTokens();
		Set<Integer> set = expectedTokens.toSet();
		poql.computeSuggestions(offendingToken,set);
		
	    throw new RuntimeException(msg); 
	  }
	  

	public poqlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public Set<Object> result;
		public Class type;
		public ThingsContext t;
		public ThingsContext things() {
			return getRuleContext(ThingsContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			((ProgContext)_localctx).t = things();
			 ((ProgContext)_localctx).result =  ((ProgContext)_localctx).t.list; ((ProgContext)_localctx).type =  ((ProgContext)_localctx).t.type; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Set_operatorContext extends ParserRuleContext {
		public Integer type;
		public Token UNION;
		public Token INTERSECTION;
		public Token EXCLUDING;
		public TerminalNode UNION() { return getToken(poqlParser.UNION, 0); }
		public TerminalNode INTERSECTION() { return getToken(poqlParser.INTERSECTION, 0); }
		public TerminalNode EXCLUDING() { return getToken(poqlParser.EXCLUDING, 0); }
		public Set_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterSet_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitSet_operator(this);
		}
	}

	public final Set_operatorContext set_operator() throws RecognitionException {
		Set_operatorContext _localctx = new Set_operatorContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_set_operator);
		try {
			setState(85);
			switch (_input.LA(1)) {
			case UNION:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				((Set_operatorContext)_localctx).UNION = match(UNION);
				 ((Set_operatorContext)_localctx).type =  (((Set_operatorContext)_localctx).UNION!=null?((Set_operatorContext)_localctx).UNION.getType():0); 
				}
				break;
			case INTERSECTION:
				enterOuterAlt(_localctx, 2);
				{
				setState(81);
				((Set_operatorContext)_localctx).INTERSECTION = match(INTERSECTION);
				 ((Set_operatorContext)_localctx).type =  (((Set_operatorContext)_localctx).INTERSECTION!=null?((Set_operatorContext)_localctx).INTERSECTION.getType():0); 
				}
				break;
			case EXCLUDING:
				enterOuterAlt(_localctx, 3);
				{
				setState(83);
				((Set_operatorContext)_localctx).EXCLUDING = match(EXCLUDING);
				 ((Set_operatorContext)_localctx).type =  (((Set_operatorContext)_localctx).EXCLUDING!=null?((Set_operatorContext)_localctx).EXCLUDING.getType():0); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ThingsContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public CasesContext t1;
		public Set_operatorContext o;
		public CasesContext tt1;
		public ObjectsContext t2;
		public ObjectsContext tt2;
		public EventsContext t3;
		public EventsContext tt3;
		public ClassesContext t4;
		public ClassesContext tt4;
		public VersionsContext t5;
		public VersionsContext tt5;
		public ActivitiesContext t6;
		public ActivitiesContext tt6;
		public RelationsContext t7;
		public RelationsContext tt7;
		public RelationshipsContext t8;
		public RelationshipsContext tt8;
		public ActivityinstancesContext t9;
		public ActivityinstancesContext tt9;
		public AttributesContext t10;
		public AttributesContext tt10;
		public List<CasesContext> cases() {
			return getRuleContexts(CasesContext.class);
		}
		public CasesContext cases(int i) {
			return getRuleContext(CasesContext.class,i);
		}
		public Set_operatorContext set_operator() {
			return getRuleContext(Set_operatorContext.class,0);
		}
		public List<ObjectsContext> objects() {
			return getRuleContexts(ObjectsContext.class);
		}
		public ObjectsContext objects(int i) {
			return getRuleContext(ObjectsContext.class,i);
		}
		public List<EventsContext> events() {
			return getRuleContexts(EventsContext.class);
		}
		public EventsContext events(int i) {
			return getRuleContext(EventsContext.class,i);
		}
		public List<ClassesContext> classes() {
			return getRuleContexts(ClassesContext.class);
		}
		public ClassesContext classes(int i) {
			return getRuleContext(ClassesContext.class,i);
		}
		public List<VersionsContext> versions() {
			return getRuleContexts(VersionsContext.class);
		}
		public VersionsContext versions(int i) {
			return getRuleContext(VersionsContext.class,i);
		}
		public List<ActivitiesContext> activities() {
			return getRuleContexts(ActivitiesContext.class);
		}
		public ActivitiesContext activities(int i) {
			return getRuleContext(ActivitiesContext.class,i);
		}
		public List<RelationsContext> relations() {
			return getRuleContexts(RelationsContext.class);
		}
		public RelationsContext relations(int i) {
			return getRuleContext(RelationsContext.class,i);
		}
		public List<RelationshipsContext> relationships() {
			return getRuleContexts(RelationshipsContext.class);
		}
		public RelationshipsContext relationships(int i) {
			return getRuleContext(RelationshipsContext.class,i);
		}
		public List<ActivityinstancesContext> activityinstances() {
			return getRuleContexts(ActivityinstancesContext.class);
		}
		public ActivityinstancesContext activityinstances(int i) {
			return getRuleContext(ActivityinstancesContext.class,i);
		}
		public List<AttributesContext> attributes() {
			return getRuleContexts(AttributesContext.class);
		}
		public AttributesContext attributes(int i) {
			return getRuleContext(AttributesContext.class,i);
		}
		public ThingsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_things; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterThings(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitThings(this);
		}
	}

	public final ThingsContext things() throws RecognitionException {
		ThingsContext _localctx = new ThingsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_things);
		int _la;
		try {
			setState(167);
			switch (_input.LA(1)) {
			case CASESOF:
			case ALLCASES:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				((ThingsContext)_localctx).t1 = cases(0);
				setState(91);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNION) | (1L << INTERSECTION) | (1L << EXCLUDING))) != 0)) {
					{
					setState(88);
					((ThingsContext)_localctx).o = set_operator();
					setState(89);
					((ThingsContext)_localctx).tt1 = cases(0);
					}
				}

				 ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t1.type; if (((ThingsContext)_localctx).o != null) {((ThingsContext)_localctx).list =  poql.set_operation(((ThingsContext)_localctx).o.type,((ThingsContext)_localctx).t1.list,((ThingsContext)_localctx).tt1.list,_localctx.type);} else { ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t1.list; } 
				}
				break;
			case OBJECTSOF:
			case ALLOBJECTS:
				enterOuterAlt(_localctx, 2);
				{
				setState(95);
				((ThingsContext)_localctx).t2 = objects(0);
				setState(99);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNION) | (1L << INTERSECTION) | (1L << EXCLUDING))) != 0)) {
					{
					setState(96);
					((ThingsContext)_localctx).o = set_operator();
					setState(97);
					((ThingsContext)_localctx).tt2 = objects(0);
					}
				}

				 ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t2.type; if (((ThingsContext)_localctx).o != null) {((ThingsContext)_localctx).list =  poql.set_operation(((ThingsContext)_localctx).o.type,((ThingsContext)_localctx).t2.list,((ThingsContext)_localctx).tt2.list,_localctx.type);} else { ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t2.list; } 
				}
				break;
			case EVENTSOF:
			case ALLEVENTS:
				enterOuterAlt(_localctx, 3);
				{
				setState(103);
				((ThingsContext)_localctx).t3 = events(0);
				setState(107);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNION) | (1L << INTERSECTION) | (1L << EXCLUDING))) != 0)) {
					{
					setState(104);
					((ThingsContext)_localctx).o = set_operator();
					setState(105);
					((ThingsContext)_localctx).tt3 = events(0);
					}
				}

				 ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t3.type; if (((ThingsContext)_localctx).o != null) {((ThingsContext)_localctx).list =  poql.set_operation(((ThingsContext)_localctx).o.type,((ThingsContext)_localctx).t3.list,((ThingsContext)_localctx).tt3.list,_localctx.type);} else { ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t3.list; } 
				}
				break;
			case CLASSESOF:
			case ALLCLASSES:
				enterOuterAlt(_localctx, 4);
				{
				setState(111);
				((ThingsContext)_localctx).t4 = classes(0);
				setState(115);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNION) | (1L << INTERSECTION) | (1L << EXCLUDING))) != 0)) {
					{
					setState(112);
					((ThingsContext)_localctx).o = set_operator();
					setState(113);
					((ThingsContext)_localctx).tt4 = classes(0);
					}
				}

				 ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t4.type; if (((ThingsContext)_localctx).o != null) {((ThingsContext)_localctx).list =  poql.set_operation(((ThingsContext)_localctx).o.type,((ThingsContext)_localctx).t4.list,((ThingsContext)_localctx).tt4.list,_localctx.type);} else { ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t4.list; } 
				}
				break;
			case VERSIONSOF:
			case VERSIONSRELATEDTO:
			case ALLVERSIONS:
				enterOuterAlt(_localctx, 5);
				{
				setState(119);
				((ThingsContext)_localctx).t5 = versions(0);
				setState(123);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNION) | (1L << INTERSECTION) | (1L << EXCLUDING))) != 0)) {
					{
					setState(120);
					((ThingsContext)_localctx).o = set_operator();
					setState(121);
					((ThingsContext)_localctx).tt5 = versions(0);
					}
				}

				 ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t5.type; if (((ThingsContext)_localctx).o != null) {((ThingsContext)_localctx).list =  poql.set_operation(((ThingsContext)_localctx).o.type,((ThingsContext)_localctx).t5.list,((ThingsContext)_localctx).tt5.list,_localctx.type);} else { ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t5.list; } 
				}
				break;
			case ACTIVITIESOF:
			case ALLACTIVITIES:
				enterOuterAlt(_localctx, 6);
				{
				setState(127);
				((ThingsContext)_localctx).t6 = activities(0);
				setState(131);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNION) | (1L << INTERSECTION) | (1L << EXCLUDING))) != 0)) {
					{
					setState(128);
					((ThingsContext)_localctx).o = set_operator();
					setState(129);
					((ThingsContext)_localctx).tt6 = activities(0);
					}
				}

				 ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t6.type; if (((ThingsContext)_localctx).o != null) {((ThingsContext)_localctx).list =  poql.set_operation(((ThingsContext)_localctx).o.type,((ThingsContext)_localctx).t6.list,((ThingsContext)_localctx).tt6.list,_localctx.type);} else { ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t6.list; } 
				}
				break;
			case RELATIONSOF:
			case ALLRELATIONS:
				enterOuterAlt(_localctx, 7);
				{
				setState(135);
				((ThingsContext)_localctx).t7 = relations(0);
				setState(139);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNION) | (1L << INTERSECTION) | (1L << EXCLUDING))) != 0)) {
					{
					setState(136);
					((ThingsContext)_localctx).o = set_operator();
					setState(137);
					((ThingsContext)_localctx).tt7 = relations(0);
					}
				}

				 ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t7.type; if (((ThingsContext)_localctx).o != null) {((ThingsContext)_localctx).list =  poql.set_operation(((ThingsContext)_localctx).o.type,((ThingsContext)_localctx).t7.list,((ThingsContext)_localctx).tt7.list,_localctx.type);} else { ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t7.list; } 
				}
				break;
			case RELATIONSHIPSOF:
			case ALLRELATIONSHIPS:
				enterOuterAlt(_localctx, 8);
				{
				setState(143);
				((ThingsContext)_localctx).t8 = relationships(0);
				setState(147);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNION) | (1L << INTERSECTION) | (1L << EXCLUDING))) != 0)) {
					{
					setState(144);
					((ThingsContext)_localctx).o = set_operator();
					setState(145);
					((ThingsContext)_localctx).tt8 = relationships(0);
					}
				}

				 ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t8.type; if (((ThingsContext)_localctx).o != null) {((ThingsContext)_localctx).list =  poql.set_operation(((ThingsContext)_localctx).o.type,((ThingsContext)_localctx).t8.list,((ThingsContext)_localctx).tt8.list,_localctx.type);} else { ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t8.list; } 
				}
				break;
			case ACTIVITYINSTANCESOF:
			case ALLACTIVITYINSTANCES:
				enterOuterAlt(_localctx, 9);
				{
				setState(151);
				((ThingsContext)_localctx).t9 = activityinstances(0);
				setState(155);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNION) | (1L << INTERSECTION) | (1L << EXCLUDING))) != 0)) {
					{
					setState(152);
					((ThingsContext)_localctx).o = set_operator();
					setState(153);
					((ThingsContext)_localctx).tt9 = activityinstances(0);
					}
				}

				 ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t9.type; if (((ThingsContext)_localctx).o != null) {((ThingsContext)_localctx).list =  poql.set_operation(((ThingsContext)_localctx).o.type,((ThingsContext)_localctx).t9.list,((ThingsContext)_localctx).tt9.list,_localctx.type);} else { ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t9.list; } 
				}
				break;
			case ATTRIBUTESOF:
			case ALLATTRIBUTES:
				enterOuterAlt(_localctx, 10);
				{
				setState(159);
				((ThingsContext)_localctx).t10 = attributes(0);
				setState(163);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNION) | (1L << INTERSECTION) | (1L << EXCLUDING))) != 0)) {
					{
					setState(160);
					((ThingsContext)_localctx).o = set_operator();
					setState(161);
					((ThingsContext)_localctx).tt10 = attributes(0);
					}
				}

				 ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t10.type; if (((ThingsContext)_localctx).o != null) {((ThingsContext)_localctx).list =  poql.set_operation(((ThingsContext)_localctx).o.type,((ThingsContext)_localctx).t10.list,((ThingsContext)_localctx).tt10.list,_localctx.type);} else { ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t10.list; } 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectsContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public ObjectsContext t3;
		public ThingsContext t1;
		public AllObjectsContext t2;
		public FilterContext f;
		public TerminalNode OBJECTSOF() { return getToken(poqlParser.OBJECTSOF, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(poqlParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(poqlParser.CLOSE_PARENTHESIS, 0); }
		public ThingsContext things() {
			return getRuleContext(ThingsContext.class,0);
		}
		public AllObjectsContext allObjects() {
			return getRuleContext(AllObjectsContext.class,0);
		}
		public ObjectsContext objects() {
			return getRuleContext(ObjectsContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public ObjectsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objects; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterObjects(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitObjects(this);
		}
	}

	public final ObjectsContext objects() throws RecognitionException {
		return objects(0);
	}

	private ObjectsContext objects(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ObjectsContext _localctx = new ObjectsContext(_ctx, _parentState);
		ObjectsContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_objects, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			switch (_input.LA(1)) {
			case OBJECTSOF:
				{
				setState(170);
				match(OBJECTSOF);
				setState(171);
				match(OPEN_PARENTHESIS);
				setState(172);
				((ObjectsContext)_localctx).t1 = things();
				setState(173);
				match(CLOSE_PARENTHESIS);
				 ((ObjectsContext)_localctx).list =  poql.objectsOf(((ObjectsContext)_localctx).t1.list,((ObjectsContext)_localctx).t1.type); ((ObjectsContext)_localctx).type = SLEXMMObject.class; 
				}
				break;
			case ALLOBJECTS:
				{
				setState(176);
				((ObjectsContext)_localctx).t2 = allObjects();
				 ((ObjectsContext)_localctx).list =  ((ObjectsContext)_localctx).t2.list; ((ObjectsContext)_localctx).type =  ((ObjectsContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(187);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ObjectsContext(_parentctx, _parentState);
					_localctx.t3 = _prevctx;
					_localctx.t3 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_objects);
					setState(181);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(182);
					((ObjectsContext)_localctx).f = filter(ID_TYPE_OBJECT);
					 ((ObjectsContext)_localctx).list =  poql.filter(((ObjectsContext)_localctx).t3.list,((ObjectsContext)_localctx).t3.type,((ObjectsContext)_localctx).f.conditions); ((ObjectsContext)_localctx).type =  ((ObjectsContext)_localctx).t3.type; 
					}
					} 
				}
				setState(189);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CasesContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public CasesContext t3;
		public ThingsContext t1;
		public AllCasesContext t2;
		public FilterContext f;
		public TerminalNode CASESOF() { return getToken(poqlParser.CASESOF, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(poqlParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(poqlParser.CLOSE_PARENTHESIS, 0); }
		public ThingsContext things() {
			return getRuleContext(ThingsContext.class,0);
		}
		public AllCasesContext allCases() {
			return getRuleContext(AllCasesContext.class,0);
		}
		public CasesContext cases() {
			return getRuleContext(CasesContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public CasesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cases; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterCases(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitCases(this);
		}
	}

	public final CasesContext cases() throws RecognitionException {
		return cases(0);
	}

	private CasesContext cases(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CasesContext _localctx = new CasesContext(_ctx, _parentState);
		CasesContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_cases, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			switch (_input.LA(1)) {
			case CASESOF:
				{
				setState(191);
				match(CASESOF);
				setState(192);
				match(OPEN_PARENTHESIS);
				setState(193);
				((CasesContext)_localctx).t1 = things();
				setState(194);
				match(CLOSE_PARENTHESIS);
				 ((CasesContext)_localctx).list =  poql.casesOf(((CasesContext)_localctx).t1.list,((CasesContext)_localctx).t1.type); ((CasesContext)_localctx).type = SLEXMMCase.class; 
				}
				break;
			case ALLCASES:
				{
				setState(197);
				((CasesContext)_localctx).t2 = allCases();
				 ((CasesContext)_localctx).list =  ((CasesContext)_localctx).t2.list; ((CasesContext)_localctx).type =  ((CasesContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(208);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new CasesContext(_parentctx, _parentState);
					_localctx.t3 = _prevctx;
					_localctx.t3 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_cases);
					setState(202);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(203);
					((CasesContext)_localctx).f = filter(ID_TYPE_CASE);
					 ((CasesContext)_localctx).list =  poql.filter(((CasesContext)_localctx).t3.list,((CasesContext)_localctx).t3.type,((CasesContext)_localctx).f.conditions); ((CasesContext)_localctx).type =  ((CasesContext)_localctx).t3.type; 
					}
					} 
				}
				setState(210);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class EventsContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public EventsContext t3;
		public ThingsContext t1;
		public AllEventsContext t2;
		public FilterContext f;
		public TerminalNode EVENTSOF() { return getToken(poqlParser.EVENTSOF, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(poqlParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(poqlParser.CLOSE_PARENTHESIS, 0); }
		public ThingsContext things() {
			return getRuleContext(ThingsContext.class,0);
		}
		public AllEventsContext allEvents() {
			return getRuleContext(AllEventsContext.class,0);
		}
		public EventsContext events() {
			return getRuleContext(EventsContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public EventsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_events; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterEvents(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitEvents(this);
		}
	}

	public final EventsContext events() throws RecognitionException {
		return events(0);
	}

	private EventsContext events(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		EventsContext _localctx = new EventsContext(_ctx, _parentState);
		EventsContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_events, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			switch (_input.LA(1)) {
			case EVENTSOF:
				{
				setState(212);
				match(EVENTSOF);
				setState(213);
				match(OPEN_PARENTHESIS);
				setState(214);
				((EventsContext)_localctx).t1 = things();
				setState(215);
				match(CLOSE_PARENTHESIS);
				 ((EventsContext)_localctx).list =  poql.eventsOf(((EventsContext)_localctx).t1.list,((EventsContext)_localctx).t1.type); ((EventsContext)_localctx).type = SLEXMMEvent.class;
				}
				break;
			case ALLEVENTS:
				{
				setState(218);
				((EventsContext)_localctx).t2 = allEvents();
				 ((EventsContext)_localctx).list =  ((EventsContext)_localctx).t2.list; ((EventsContext)_localctx).type =  ((EventsContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(229);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new EventsContext(_parentctx, _parentState);
					_localctx.t3 = _prevctx;
					_localctx.t3 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_events);
					setState(223);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(224);
					((EventsContext)_localctx).f = filter(ID_TYPE_EVENT);
					 ((EventsContext)_localctx).list =  poql.filter(((EventsContext)_localctx).t3.list,((EventsContext)_localctx).t3.type,((EventsContext)_localctx).f.conditions); ((EventsContext)_localctx).type =  ((EventsContext)_localctx).t3.type; 
					}
					} 
				}
				setState(231);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ClassesContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public ClassesContext t3;
		public ThingsContext t1;
		public AllClassesContext t2;
		public FilterContext f;
		public TerminalNode CLASSESOF() { return getToken(poqlParser.CLASSESOF, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(poqlParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(poqlParser.CLOSE_PARENTHESIS, 0); }
		public ThingsContext things() {
			return getRuleContext(ThingsContext.class,0);
		}
		public AllClassesContext allClasses() {
			return getRuleContext(AllClassesContext.class,0);
		}
		public ClassesContext classes() {
			return getRuleContext(ClassesContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public ClassesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterClasses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitClasses(this);
		}
	}

	public final ClassesContext classes() throws RecognitionException {
		return classes(0);
	}

	private ClassesContext classes(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ClassesContext _localctx = new ClassesContext(_ctx, _parentState);
		ClassesContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_classes, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			switch (_input.LA(1)) {
			case CLASSESOF:
				{
				setState(233);
				match(CLASSESOF);
				setState(234);
				match(OPEN_PARENTHESIS);
				setState(235);
				((ClassesContext)_localctx).t1 = things();
				setState(236);
				match(CLOSE_PARENTHESIS);
				 ((ClassesContext)_localctx).list =  poql.classesOf(((ClassesContext)_localctx).t1.list,((ClassesContext)_localctx).t1.type); ((ClassesContext)_localctx).type = SLEXMMClass.class;
				}
				break;
			case ALLCLASSES:
				{
				setState(239);
				((ClassesContext)_localctx).t2 = allClasses();
				 ((ClassesContext)_localctx).list =  ((ClassesContext)_localctx).t2.list; ((ClassesContext)_localctx).type =  ((ClassesContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(250);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ClassesContext(_parentctx, _parentState);
					_localctx.t3 = _prevctx;
					_localctx.t3 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_classes);
					setState(244);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(245);
					((ClassesContext)_localctx).f = filter(ID_TYPE_CLASS);
					 ((ClassesContext)_localctx).list =  poql.filter(((ClassesContext)_localctx).t3.list,((ClassesContext)_localctx).t3.type,((ClassesContext)_localctx).f.conditions); ((ClassesContext)_localctx).type =  ((ClassesContext)_localctx).t3.type; 
					}
					} 
				}
				setState(252);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class VersionsContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public VersionsContext t3;
		public ThingsContext t1;
		public AllVersionsContext t2;
		public VersionsContext t4;
		public FilterContext f;
		public TerminalNode VERSIONSOF() { return getToken(poqlParser.VERSIONSOF, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(poqlParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(poqlParser.CLOSE_PARENTHESIS, 0); }
		public ThingsContext things() {
			return getRuleContext(ThingsContext.class,0);
		}
		public AllVersionsContext allVersions() {
			return getRuleContext(AllVersionsContext.class,0);
		}
		public TerminalNode VERSIONSRELATEDTO() { return getToken(poqlParser.VERSIONSRELATEDTO, 0); }
		public VersionsContext versions() {
			return getRuleContext(VersionsContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public VersionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_versions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterVersions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitVersions(this);
		}
	}

	public final VersionsContext versions() throws RecognitionException {
		return versions(0);
	}

	private VersionsContext versions(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		VersionsContext _localctx = new VersionsContext(_ctx, _parentState);
		VersionsContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_versions, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			switch (_input.LA(1)) {
			case VERSIONSOF:
				{
				setState(254);
				match(VERSIONSOF);
				setState(255);
				match(OPEN_PARENTHESIS);
				setState(256);
				((VersionsContext)_localctx).t1 = things();
				setState(257);
				match(CLOSE_PARENTHESIS);
				 ((VersionsContext)_localctx).list =  poql.versionsOf(((VersionsContext)_localctx).t1.list,((VersionsContext)_localctx).t1.type); ((VersionsContext)_localctx).type = SLEXMMObjectVersion.class;
				}
				break;
			case ALLVERSIONS:
				{
				setState(260);
				((VersionsContext)_localctx).t2 = allVersions();
				 ((VersionsContext)_localctx).list =  ((VersionsContext)_localctx).t2.list; ((VersionsContext)_localctx).type =  ((VersionsContext)_localctx).t2.type; 
				}
				break;
			case VERSIONSRELATEDTO:
				{
				setState(263);
				match(VERSIONSRELATEDTO);
				setState(264);
				match(OPEN_PARENTHESIS);
				setState(265);
				((VersionsContext)_localctx).t4 = versions(0);
				setState(266);
				match(CLOSE_PARENTHESIS);
				 ((VersionsContext)_localctx).list =  poql.versionsRelatedTo(((VersionsContext)_localctx).t4.list,((VersionsContext)_localctx).t4.type); ((VersionsContext)_localctx).type = SLEXMMObjectVersion.class; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(277);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new VersionsContext(_parentctx, _parentState);
					_localctx.t3 = _prevctx;
					_localctx.t3 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_versions);
					setState(271);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(272);
					((VersionsContext)_localctx).f = filter(ID_TYPE_VERSION);
					 ((VersionsContext)_localctx).list =  poql.filter(((VersionsContext)_localctx).t3.list,((VersionsContext)_localctx).t3.type,((VersionsContext)_localctx).f.conditions); ((VersionsContext)_localctx).type =  ((VersionsContext)_localctx).t3.type; 
					}
					} 
				}
				setState(279);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ActivitiesContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public ActivitiesContext t3;
		public ThingsContext t1;
		public AllActivitiesContext t2;
		public FilterContext f;
		public TerminalNode ACTIVITIESOF() { return getToken(poqlParser.ACTIVITIESOF, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(poqlParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(poqlParser.CLOSE_PARENTHESIS, 0); }
		public ThingsContext things() {
			return getRuleContext(ThingsContext.class,0);
		}
		public AllActivitiesContext allActivities() {
			return getRuleContext(AllActivitiesContext.class,0);
		}
		public ActivitiesContext activities() {
			return getRuleContext(ActivitiesContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public ActivitiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_activities; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterActivities(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitActivities(this);
		}
	}

	public final ActivitiesContext activities() throws RecognitionException {
		return activities(0);
	}

	private ActivitiesContext activities(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ActivitiesContext _localctx = new ActivitiesContext(_ctx, _parentState);
		ActivitiesContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_activities, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			switch (_input.LA(1)) {
			case ACTIVITIESOF:
				{
				setState(281);
				match(ACTIVITIESOF);
				setState(282);
				match(OPEN_PARENTHESIS);
				setState(283);
				((ActivitiesContext)_localctx).t1 = things();
				setState(284);
				match(CLOSE_PARENTHESIS);
				 ((ActivitiesContext)_localctx).list =  poql.activitiesOf(((ActivitiesContext)_localctx).t1.list,((ActivitiesContext)_localctx).t1.type); ((ActivitiesContext)_localctx).type = SLEXMMActivity.class;
				}
				break;
			case ALLACTIVITIES:
				{
				setState(287);
				((ActivitiesContext)_localctx).t2 = allActivities();
				 ((ActivitiesContext)_localctx).list =  ((ActivitiesContext)_localctx).t2.list; ((ActivitiesContext)_localctx).type =  ((ActivitiesContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(298);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ActivitiesContext(_parentctx, _parentState);
					_localctx.t3 = _prevctx;
					_localctx.t3 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_activities);
					setState(292);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(293);
					((ActivitiesContext)_localctx).f = filter(ID_TYPE_ACTIVITY);
					 ((ActivitiesContext)_localctx).list =  poql.filter(((ActivitiesContext)_localctx).t3.list,((ActivitiesContext)_localctx).t3.type,((ActivitiesContext)_localctx).f.conditions); ((ActivitiesContext)_localctx).type =  ((ActivitiesContext)_localctx).t3.type; 
					}
					} 
				}
				setState(300);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class RelationsContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public RelationsContext t3;
		public ThingsContext t1;
		public AllRelationsContext t2;
		public FilterContext f;
		public TerminalNode RELATIONSOF() { return getToken(poqlParser.RELATIONSOF, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(poqlParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(poqlParser.CLOSE_PARENTHESIS, 0); }
		public ThingsContext things() {
			return getRuleContext(ThingsContext.class,0);
		}
		public AllRelationsContext allRelations() {
			return getRuleContext(AllRelationsContext.class,0);
		}
		public RelationsContext relations() {
			return getRuleContext(RelationsContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public RelationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterRelations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitRelations(this);
		}
	}

	public final RelationsContext relations() throws RecognitionException {
		return relations(0);
	}

	private RelationsContext relations(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		RelationsContext _localctx = new RelationsContext(_ctx, _parentState);
		RelationsContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_relations, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			switch (_input.LA(1)) {
			case RELATIONSOF:
				{
				setState(302);
				match(RELATIONSOF);
				setState(303);
				match(OPEN_PARENTHESIS);
				setState(304);
				((RelationsContext)_localctx).t1 = things();
				setState(305);
				match(CLOSE_PARENTHESIS);
				 ((RelationsContext)_localctx).list =  poql.relationsOf(((RelationsContext)_localctx).t1.list,((RelationsContext)_localctx).t1.type); ((RelationsContext)_localctx).type = SLEXMMRelation.class;
				}
				break;
			case ALLRELATIONS:
				{
				setState(308);
				((RelationsContext)_localctx).t2 = allRelations();
				 ((RelationsContext)_localctx).list =  ((RelationsContext)_localctx).t2.list; ((RelationsContext)_localctx).type =  ((RelationsContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(319);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new RelationsContext(_parentctx, _parentState);
					_localctx.t3 = _prevctx;
					_localctx.t3 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_relations);
					setState(313);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(314);
					((RelationsContext)_localctx).f = filter(ID_TYPE_RELATION);
					 ((RelationsContext)_localctx).list =  poql.filter(((RelationsContext)_localctx).t3.list,((RelationsContext)_localctx).t3.type,((RelationsContext)_localctx).f.conditions); ((RelationsContext)_localctx).type =  ((RelationsContext)_localctx).t3.type; 
					}
					} 
				}
				setState(321);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class RelationshipsContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public RelationshipsContext t3;
		public ThingsContext t1;
		public AllRelationshipsContext t2;
		public FilterContext f;
		public TerminalNode RELATIONSHIPSOF() { return getToken(poqlParser.RELATIONSHIPSOF, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(poqlParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(poqlParser.CLOSE_PARENTHESIS, 0); }
		public ThingsContext things() {
			return getRuleContext(ThingsContext.class,0);
		}
		public AllRelationshipsContext allRelationships() {
			return getRuleContext(AllRelationshipsContext.class,0);
		}
		public RelationshipsContext relationships() {
			return getRuleContext(RelationshipsContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public RelationshipsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationships; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterRelationships(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitRelationships(this);
		}
	}

	public final RelationshipsContext relationships() throws RecognitionException {
		return relationships(0);
	}

	private RelationshipsContext relationships(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		RelationshipsContext _localctx = new RelationshipsContext(_ctx, _parentState);
		RelationshipsContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_relationships, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(332);
			switch (_input.LA(1)) {
			case RELATIONSHIPSOF:
				{
				setState(323);
				match(RELATIONSHIPSOF);
				setState(324);
				match(OPEN_PARENTHESIS);
				setState(325);
				((RelationshipsContext)_localctx).t1 = things();
				setState(326);
				match(CLOSE_PARENTHESIS);
				 ((RelationshipsContext)_localctx).list =  poql.relationshipsOf(((RelationshipsContext)_localctx).t1.list,((RelationshipsContext)_localctx).t1.type); ((RelationshipsContext)_localctx).type = SLEXMMRelationship.class;
				}
				break;
			case ALLRELATIONSHIPS:
				{
				setState(329);
				((RelationshipsContext)_localctx).t2 = allRelationships();
				 ((RelationshipsContext)_localctx).list =  ((RelationshipsContext)_localctx).t2.list; ((RelationshipsContext)_localctx).type =  ((RelationshipsContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(340);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new RelationshipsContext(_parentctx, _parentState);
					_localctx.t3 = _prevctx;
					_localctx.t3 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_relationships);
					setState(334);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(335);
					((RelationshipsContext)_localctx).f = filter(ID_TYPE_RELATIONSHIP);
					 ((RelationshipsContext)_localctx).list =  poql.filter(((RelationshipsContext)_localctx).t3.list,((RelationshipsContext)_localctx).t3.type,((RelationshipsContext)_localctx).f.conditions); ((RelationshipsContext)_localctx).type =  ((RelationshipsContext)_localctx).t3.type; 
					}
					} 
				}
				setState(342);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ActivityinstancesContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public ActivityinstancesContext t3;
		public ThingsContext t1;
		public AllActivityInstancesContext t2;
		public FilterContext f;
		public TerminalNode ACTIVITYINSTANCESOF() { return getToken(poqlParser.ACTIVITYINSTANCESOF, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(poqlParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(poqlParser.CLOSE_PARENTHESIS, 0); }
		public ThingsContext things() {
			return getRuleContext(ThingsContext.class,0);
		}
		public AllActivityInstancesContext allActivityInstances() {
			return getRuleContext(AllActivityInstancesContext.class,0);
		}
		public ActivityinstancesContext activityinstances() {
			return getRuleContext(ActivityinstancesContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public ActivityinstancesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_activityinstances; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterActivityinstances(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitActivityinstances(this);
		}
	}

	public final ActivityinstancesContext activityinstances() throws RecognitionException {
		return activityinstances(0);
	}

	private ActivityinstancesContext activityinstances(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ActivityinstancesContext _localctx = new ActivityinstancesContext(_ctx, _parentState);
		ActivityinstancesContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_activityinstances, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(353);
			switch (_input.LA(1)) {
			case ACTIVITYINSTANCESOF:
				{
				setState(344);
				match(ACTIVITYINSTANCESOF);
				setState(345);
				match(OPEN_PARENTHESIS);
				setState(346);
				((ActivityinstancesContext)_localctx).t1 = things();
				setState(347);
				match(CLOSE_PARENTHESIS);
				 ((ActivityinstancesContext)_localctx).list =  poql.activityInstancesOf(((ActivityinstancesContext)_localctx).t1.list,((ActivityinstancesContext)_localctx).t1.type); ((ActivityinstancesContext)_localctx).type = SLEXMMActivityInstance.class;
				}
				break;
			case ALLACTIVITYINSTANCES:
				{
				setState(350);
				((ActivityinstancesContext)_localctx).t2 = allActivityInstances();
				 ((ActivityinstancesContext)_localctx).list =  ((ActivityinstancesContext)_localctx).t2.list; ((ActivityinstancesContext)_localctx).type =  ((ActivityinstancesContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(361);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ActivityinstancesContext(_parentctx, _parentState);
					_localctx.t3 = _prevctx;
					_localctx.t3 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_activityinstances);
					setState(355);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(356);
					((ActivityinstancesContext)_localctx).f = filter(ID_TYPE_ACTIVITY_INSTANCE);
					 ((ActivityinstancesContext)_localctx).list =  poql.filter(((ActivityinstancesContext)_localctx).t3.list,((ActivityinstancesContext)_localctx).t3.type,((ActivityinstancesContext)_localctx).f.conditions); ((ActivityinstancesContext)_localctx).type =  ((ActivityinstancesContext)_localctx).t3.type; 
					}
					} 
				}
				setState(363);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AttributesContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public AttributesContext t3;
		public ThingsContext t1;
		public AllAttributesContext t2;
		public FilterContext f;
		public TerminalNode ATTRIBUTESOF() { return getToken(poqlParser.ATTRIBUTESOF, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(poqlParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(poqlParser.CLOSE_PARENTHESIS, 0); }
		public ThingsContext things() {
			return getRuleContext(ThingsContext.class,0);
		}
		public AllAttributesContext allAttributes() {
			return getRuleContext(AllAttributesContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public AttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitAttributes(this);
		}
	}

	public final AttributesContext attributes() throws RecognitionException {
		return attributes(0);
	}

	private AttributesContext attributes(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AttributesContext _localctx = new AttributesContext(_ctx, _parentState);
		AttributesContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_attributes, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			switch (_input.LA(1)) {
			case ATTRIBUTESOF:
				{
				setState(365);
				match(ATTRIBUTESOF);
				setState(366);
				match(OPEN_PARENTHESIS);
				setState(367);
				((AttributesContext)_localctx).t1 = things();
				setState(368);
				match(CLOSE_PARENTHESIS);
				 ((AttributesContext)_localctx).list =  poql.attributesOf(((AttributesContext)_localctx).t1.list,((AttributesContext)_localctx).t1.type); ((AttributesContext)_localctx).type = SLEXMMAttribute.class;
				}
				break;
			case ALLATTRIBUTES:
				{
				setState(371);
				((AttributesContext)_localctx).t2 = allAttributes();
				 ((AttributesContext)_localctx).list =  ((AttributesContext)_localctx).t2.list; ((AttributesContext)_localctx).type =  ((AttributesContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(382);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AttributesContext(_parentctx, _parentState);
					_localctx.t3 = _prevctx;
					_localctx.t3 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_attributes);
					setState(376);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(377);
					((AttributesContext)_localctx).f = filter(ID_TYPE_ATTRIBUTE);
					 ((AttributesContext)_localctx).list =  poql.filter(((AttributesContext)_localctx).t3.list,((AttributesContext)_localctx).t3.type,((AttributesContext)_localctx).f.conditions); ((AttributesContext)_localctx).type =  ((AttributesContext)_localctx).t3.type; 
					}
					} 
				}
				setState(384);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FilterContext extends ParserRuleContext {
		public int type_id;
		public FilterTree conditions;
		public Filter_expressionContext f;
		public TerminalNode WHERE() { return getToken(poqlParser.WHERE, 0); }
		public Filter_expressionContext filter_expression() {
			return getRuleContext(Filter_expressionContext.class,0);
		}
		public FilterContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public FilterContext(ParserRuleContext parent, int invokingState, int type_id) {
			super(parent, invokingState);
			this.type_id = type_id;
		}
		@Override public int getRuleIndex() { return RULE_filter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitFilter(this);
		}
	}

	public final FilterContext filter(int type_id) throws RecognitionException {
		FilterContext _localctx = new FilterContext(_ctx, getState(), type_id);
		enterRule(_localctx, 26, RULE_filter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(385);
			match(WHERE);
			setState(386);
			((FilterContext)_localctx).f = filter_expression(_localctx.type_id);
			 ((FilterContext)_localctx).conditions =  ((FilterContext)_localctx).f.tree; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Filter_expressionContext extends ParserRuleContext {
		public int type_id;
		public FilterTree tree;
		public Filter_expressionContext f0;
		public Filter_expressionContext f1;
		public NodeContext node;
		public Filter_expressionContext f2;
		public IdsContext ids;
		public OperatorContext operator;
		public TerminalNode NOT() { return getToken(poqlParser.NOT, 0); }
		public List<Filter_expressionContext> filter_expression() {
			return getRuleContexts(Filter_expressionContext.class);
		}
		public Filter_expressionContext filter_expression(int i) {
			return getRuleContext(Filter_expressionContext.class,i);
		}
		public TerminalNode OPEN_PARENTHESIS() { return getToken(poqlParser.OPEN_PARENTHESIS, 0); }
		public NodeContext node() {
			return getRuleContext(NodeContext.class,0);
		}
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(poqlParser.CLOSE_PARENTHESIS, 0); }
		public IdsContext ids() {
			return getRuleContext(IdsContext.class,0);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public Filter_expressionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Filter_expressionContext(ParserRuleContext parent, int invokingState, int type_id) {
			super(parent, invokingState);
			this.type_id = type_id;
		}
		@Override public int getRuleIndex() { return RULE_filter_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterFilter_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitFilter_expression(this);
		}
	}

	public final Filter_expressionContext filter_expression(int type_id) throws RecognitionException {
		Filter_expressionContext _localctx = new Filter_expressionContext(_ctx, getState(), type_id);
		enterRule(_localctx, 28, RULE_filter_expression);
		try {
			setState(404);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(389);
				match(NOT);
				setState(390);
				((Filter_expressionContext)_localctx).f0 = filter_expression(_localctx.type_id);
				 ((Filter_expressionContext)_localctx).tree =  poql.createNotNode(((Filter_expressionContext)_localctx).f0.tree); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(393);
				match(OPEN_PARENTHESIS);
				setState(394);
				((Filter_expressionContext)_localctx).f1 = filter_expression(_localctx.type_id);
				setState(395);
				((Filter_expressionContext)_localctx).node = node();
				setState(396);
				((Filter_expressionContext)_localctx).f2 = filter_expression(_localctx.type_id);
				setState(397);
				match(CLOSE_PARENTHESIS);
				 ((Filter_expressionContext)_localctx).tree =  poql.createNode(((Filter_expressionContext)_localctx).f1.tree,((Filter_expressionContext)_localctx).f2.tree,((Filter_expressionContext)_localctx).node.node_id); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(400);
				((Filter_expressionContext)_localctx).ids = ids(_localctx.type_id);
				setState(401);
				((Filter_expressionContext)_localctx).operator = operator(_localctx.type_id,((Filter_expressionContext)_localctx).ids.att);

						if (((Filter_expressionContext)_localctx).operator.operator_id == FilterTree.OPERATOR_CHANGED) {
							((Filter_expressionContext)_localctx).tree =  poql.createChangedTerminalFilter(((Filter_expressionContext)_localctx).ids.name,((Filter_expressionContext)_localctx).operator.valueFrom,((Filter_expressionContext)_localctx).operator.valueTo);
						} else {
							((Filter_expressionContext)_localctx).tree =  poql.createTerminalFilter(((Filter_expressionContext)_localctx).ids.id,((Filter_expressionContext)_localctx).ids.name,((Filter_expressionContext)_localctx).operator.value,((Filter_expressionContext)_localctx).operator.operator_id,((Filter_expressionContext)_localctx).ids.att);
						}
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeContext extends ParserRuleContext {
		public int node_id;
		public TerminalNode AND() { return getToken(poqlParser.AND, 0); }
		public TerminalNode OR() { return getToken(poqlParser.OR, 0); }
		public NodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_node; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitNode(this);
		}
	}

	public final NodeContext node() throws RecognitionException {
		NodeContext _localctx = new NodeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_node);
		try {
			setState(410);
			switch (_input.LA(1)) {
			case AND:
				enterOuterAlt(_localctx, 1);
				{
				setState(406);
				match(AND);
				((NodeContext)_localctx).node_id =  FilterTree.NODE_AND; 
				}
				break;
			case OR:
				enterOuterAlt(_localctx, 2);
				{
				setState(408);
				match(OR);
				((NodeContext)_localctx).node_id =  FilterTree.NODE_OR; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorContext extends ParserRuleContext {
		public int type_id;
		public boolean att;
		public int operator_id;
		public String value;
		public String valueFrom;
		public String valueTo;
		public Token STRING;
		public Token f13;
		public Token f14;
		public TerminalNode EQUAL() { return getToken(poqlParser.EQUAL, 0); }
		public List<TerminalNode> STRING() { return getTokens(poqlParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(poqlParser.STRING, i);
		}
		public TerminalNode DIFFERENT() { return getToken(poqlParser.DIFFERENT, 0); }
		public TerminalNode EQUAL_OR_GREATER() { return getToken(poqlParser.EQUAL_OR_GREATER, 0); }
		public TerminalNode EQUAL_OR_SMALLER() { return getToken(poqlParser.EQUAL_OR_SMALLER, 0); }
		public TerminalNode GREATER() { return getToken(poqlParser.GREATER, 0); }
		public TerminalNode SMALLER() { return getToken(poqlParser.SMALLER, 0); }
		public TerminalNode CONTAINS() { return getToken(poqlParser.CONTAINS, 0); }
		public TerminalNode CHANGED() { return getToken(poqlParser.CHANGED, 0); }
		public TerminalNode FROM() { return getToken(poqlParser.FROM, 0); }
		public TerminalNode TO() { return getToken(poqlParser.TO, 0); }
		public OperatorContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public OperatorContext(ParserRuleContext parent, int invokingState, int type_id, boolean att) {
			super(parent, invokingState);
			this.type_id = type_id;
			this.att = att;
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitOperator(this);
		}
	}

	public final OperatorContext operator(int type_id,boolean att) throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState(), type_id, att);
		enterRule(_localctx, 32, RULE_operator);
		try {
			setState(444);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(412);
				match(EQUAL);
				setState(413);
				((OperatorContext)_localctx).STRING = match(STRING);
				((OperatorContext)_localctx).operator_id =  FilterTree.OPERATOR_EQUAL; ((OperatorContext)_localctx).value =  (((OperatorContext)_localctx).STRING!=null?((OperatorContext)_localctx).STRING.getText():null); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(415);
				match(DIFFERENT);
				setState(416);
				((OperatorContext)_localctx).STRING = match(STRING);
				((OperatorContext)_localctx).operator_id =  FilterTree.OPERATOR_DIFFERENT; ((OperatorContext)_localctx).value =  (((OperatorContext)_localctx).STRING!=null?((OperatorContext)_localctx).STRING.getText():null); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(418);
				match(EQUAL_OR_GREATER);
				setState(419);
				((OperatorContext)_localctx).STRING = match(STRING);
				((OperatorContext)_localctx).operator_id =  FilterTree.OPERATOR_EQUAL_OR_GREATER_THAN; ((OperatorContext)_localctx).value =  (((OperatorContext)_localctx).STRING!=null?((OperatorContext)_localctx).STRING.getText():null); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(421);
				match(EQUAL_OR_SMALLER);
				setState(422);
				((OperatorContext)_localctx).STRING = match(STRING);
				((OperatorContext)_localctx).operator_id =  FilterTree.OPERATOR_EQUAL_OR_SMALLER_THAN; ((OperatorContext)_localctx).value =  (((OperatorContext)_localctx).STRING!=null?((OperatorContext)_localctx).STRING.getText():null); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(424);
				match(GREATER);
				setState(425);
				((OperatorContext)_localctx).STRING = match(STRING);
				((OperatorContext)_localctx).operator_id =  FilterTree.OPERATOR_GREATER_THAN; ((OperatorContext)_localctx).value =  (((OperatorContext)_localctx).STRING!=null?((OperatorContext)_localctx).STRING.getText():null); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(427);
				match(SMALLER);
				setState(428);
				((OperatorContext)_localctx).STRING = match(STRING);
				((OperatorContext)_localctx).operator_id =  FilterTree.OPERATOR_SMALLER_THAN; ((OperatorContext)_localctx).value =  (((OperatorContext)_localctx).STRING!=null?((OperatorContext)_localctx).STRING.getText():null); 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(430);
				match(CONTAINS);
				setState(431);
				((OperatorContext)_localctx).STRING = match(STRING);
				((OperatorContext)_localctx).operator_id =  FilterTree.OPERATOR_CONTAINS; ((OperatorContext)_localctx).value =  (((OperatorContext)_localctx).STRING!=null?((OperatorContext)_localctx).STRING.getText():null); 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(433);
				if (!(_localctx.type_id == ID_TYPE_VERSION && _localctx.att)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_VERSION && $att");
				setState(434);
				match(CHANGED);
				setState(437);
				switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
				case 1:
					{
					setState(435);
					match(FROM);
					setState(436);
					((OperatorContext)_localctx).f13 = match(STRING);
					}
					break;
				}
				setState(441);
				switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(439);
					match(TO);
					setState(440);
					((OperatorContext)_localctx).f14 = match(STRING);
					}
					break;
				}
				((OperatorContext)_localctx).operator_id =  FilterTree.OPERATOR_CHANGED; ((OperatorContext)_localctx).valueFrom =  (((OperatorContext)_localctx).f13!=null?((OperatorContext)_localctx).f13.getText():null); ((OperatorContext)_localctx).valueTo =  (((OperatorContext)_localctx).f14!=null?((OperatorContext)_localctx).f14.getText():null);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdsContext extends ParserRuleContext {
		public int type_id;
		public String name;
		public boolean att;
		public int id;
		public Id_versionContext i1;
		public Id_objectContext i2;
		public Id_classContext i3;
		public Id_relationshipContext i4;
		public Id_relationContext i5;
		public Id_eventContext i6;
		public Id_caseContext i7;
		public Id_activity_instanceContext i8;
		public Id_activityContext i9;
		public Id_attributeContext i10;
		public Id_versionContext id_version() {
			return getRuleContext(Id_versionContext.class,0);
		}
		public Id_objectContext id_object() {
			return getRuleContext(Id_objectContext.class,0);
		}
		public Id_classContext id_class() {
			return getRuleContext(Id_classContext.class,0);
		}
		public Id_relationshipContext id_relationship() {
			return getRuleContext(Id_relationshipContext.class,0);
		}
		public Id_relationContext id_relation() {
			return getRuleContext(Id_relationContext.class,0);
		}
		public Id_eventContext id_event() {
			return getRuleContext(Id_eventContext.class,0);
		}
		public Id_caseContext id_case() {
			return getRuleContext(Id_caseContext.class,0);
		}
		public Id_activity_instanceContext id_activity_instance() {
			return getRuleContext(Id_activity_instanceContext.class,0);
		}
		public Id_activityContext id_activity() {
			return getRuleContext(Id_activityContext.class,0);
		}
		public Id_attributeContext id_attribute() {
			return getRuleContext(Id_attributeContext.class,0);
		}
		public IdsContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public IdsContext(ParserRuleContext parent, int invokingState, int type_id) {
			super(parent, invokingState);
			this.type_id = type_id;
		}
		@Override public int getRuleIndex() { return RULE_ids; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterIds(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitIds(this);
		}
	}

	public final IdsContext ids(int type_id) throws RecognitionException {
		IdsContext _localctx = new IdsContext(_ctx, getState(), type_id);
		enterRule(_localctx, 34, RULE_ids);
		try {
			setState(486);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(446);
				if (!(_localctx.type_id == ID_TYPE_VERSION)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_VERSION");
				setState(447);
				((IdsContext)_localctx).i1 = id_version();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i1.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i1.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i1.id;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(450);
				if (!(_localctx.type_id == ID_TYPE_OBJECT)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_OBJECT");
				setState(451);
				((IdsContext)_localctx).i2 = id_object();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i2.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i2.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i2.id;
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(454);
				if (!(_localctx.type_id == ID_TYPE_CLASS)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_CLASS");
				setState(455);
				((IdsContext)_localctx).i3 = id_class();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i3.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i3.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i3.id;
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(458);
				if (!(_localctx.type_id == ID_TYPE_RELATIONSHIP)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_RELATIONSHIP");
				setState(459);
				((IdsContext)_localctx).i4 = id_relationship();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i4.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i4.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i4.id;
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(462);
				if (!(_localctx.type_id == ID_TYPE_RELATION)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_RELATION");
				setState(463);
				((IdsContext)_localctx).i5 = id_relation();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i5.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i5.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i5.id;
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(466);
				if (!(_localctx.type_id == ID_TYPE_EVENT)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_EVENT");
				setState(467);
				((IdsContext)_localctx).i6 = id_event();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i6.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i6.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i6.id;
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(470);
				if (!(_localctx.type_id == ID_TYPE_CASE)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_CASE");
				setState(471);
				((IdsContext)_localctx).i7 = id_case();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i7.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i7.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i7.id;
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(474);
				if (!(_localctx.type_id == ID_TYPE_ACTIVITY_INSTANCE)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_ACTIVITY_INSTANCE");
				setState(475);
				((IdsContext)_localctx).i8 = id_activity_instance();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i8.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i8.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i8.id;
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(478);
				if (!(_localctx.type_id == ID_TYPE_ACTIVITY)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_ACTIVITY");
				setState(479);
				((IdsContext)_localctx).i9 = id_activity();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i9.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i9.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i9.id;
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(482);
				if (!(_localctx.type_id == ID_TYPE_ATTRIBUTE)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_ATTRIBUTE");
				setState(483);
				((IdsContext)_localctx).i10 = id_attribute();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i10.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i10.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i10.id;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_versionContext extends ParserRuleContext {
		public String name;
		public boolean att;
		public int id;
		public Token ID;
		public Token OBJECT_ID;
		public Token START_TIMESTAMP;
		public Token END_TIMESTAMP;
		public Token IDATT;
		public TerminalNode ID() { return getToken(poqlParser.ID, 0); }
		public TerminalNode OBJECT_ID() { return getToken(poqlParser.OBJECT_ID, 0); }
		public TerminalNode START_TIMESTAMP() { return getToken(poqlParser.START_TIMESTAMP, 0); }
		public TerminalNode END_TIMESTAMP() { return getToken(poqlParser.END_TIMESTAMP, 0); }
		public TerminalNode IDATT() { return getToken(poqlParser.IDATT, 0); }
		public Id_versionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_version; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterId_version(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitId_version(this);
		}
	}

	public final Id_versionContext id_version() throws RecognitionException {
		Id_versionContext _localctx = new Id_versionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_id_version);
		try {
			setState(498);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(488);
				((Id_versionContext)_localctx).ID = match(ID);
				((Id_versionContext)_localctx).name =  (((Id_versionContext)_localctx).ID!=null?((Id_versionContext)_localctx).ID.getText():null); ((Id_versionContext)_localctx).att =  false; ((Id_versionContext)_localctx).id =  (((Id_versionContext)_localctx).ID!=null?((Id_versionContext)_localctx).ID.getType():0);
				}
				break;
			case OBJECT_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(490);
				((Id_versionContext)_localctx).OBJECT_ID = match(OBJECT_ID);
				((Id_versionContext)_localctx).name =  (((Id_versionContext)_localctx).OBJECT_ID!=null?((Id_versionContext)_localctx).OBJECT_ID.getText():null); ((Id_versionContext)_localctx).att =  false; ((Id_versionContext)_localctx).id =  (((Id_versionContext)_localctx).OBJECT_ID!=null?((Id_versionContext)_localctx).OBJECT_ID.getType():0);
				}
				break;
			case START_TIMESTAMP:
				enterOuterAlt(_localctx, 3);
				{
				setState(492);
				((Id_versionContext)_localctx).START_TIMESTAMP = match(START_TIMESTAMP);
				((Id_versionContext)_localctx).name =  (((Id_versionContext)_localctx).START_TIMESTAMP!=null?((Id_versionContext)_localctx).START_TIMESTAMP.getText():null); ((Id_versionContext)_localctx).att =  false; ((Id_versionContext)_localctx).id =  (((Id_versionContext)_localctx).START_TIMESTAMP!=null?((Id_versionContext)_localctx).START_TIMESTAMP.getType():0);
				}
				break;
			case END_TIMESTAMP:
				enterOuterAlt(_localctx, 4);
				{
				setState(494);
				((Id_versionContext)_localctx).END_TIMESTAMP = match(END_TIMESTAMP);
				((Id_versionContext)_localctx).name =  (((Id_versionContext)_localctx).END_TIMESTAMP!=null?((Id_versionContext)_localctx).END_TIMESTAMP.getText():null); ((Id_versionContext)_localctx).att =  false; ((Id_versionContext)_localctx).id =  (((Id_versionContext)_localctx).END_TIMESTAMP!=null?((Id_versionContext)_localctx).END_TIMESTAMP.getType():0);
				}
				break;
			case IDATT:
				enterOuterAlt(_localctx, 5);
				{
				setState(496);
				((Id_versionContext)_localctx).IDATT = match(IDATT);
				((Id_versionContext)_localctx).name =  (((Id_versionContext)_localctx).IDATT!=null?((Id_versionContext)_localctx).IDATT.getText():null); ((Id_versionContext)_localctx).att =  true;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_objectContext extends ParserRuleContext {
		public String name;
		public boolean att;
		public int id;
		public Token ID;
		public Token CLASS_ID;
		public TerminalNode ID() { return getToken(poqlParser.ID, 0); }
		public TerminalNode CLASS_ID() { return getToken(poqlParser.CLASS_ID, 0); }
		public Id_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterId_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitId_object(this);
		}
	}

	public final Id_objectContext id_object() throws RecognitionException {
		Id_objectContext _localctx = new Id_objectContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_id_object);
		try {
			setState(504);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(500);
				((Id_objectContext)_localctx).ID = match(ID);
				((Id_objectContext)_localctx).name =  (((Id_objectContext)_localctx).ID!=null?((Id_objectContext)_localctx).ID.getText():null); ((Id_objectContext)_localctx).att =  false; ((Id_objectContext)_localctx).id =  (((Id_objectContext)_localctx).ID!=null?((Id_objectContext)_localctx).ID.getType():0);
				}
				break;
			case CLASS_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(502);
				((Id_objectContext)_localctx).CLASS_ID = match(CLASS_ID);
				((Id_objectContext)_localctx).name =  (((Id_objectContext)_localctx).CLASS_ID!=null?((Id_objectContext)_localctx).CLASS_ID.getText():null); ((Id_objectContext)_localctx).att =  false; ((Id_objectContext)_localctx).id =  (((Id_objectContext)_localctx).CLASS_ID!=null?((Id_objectContext)_localctx).CLASS_ID.getType():0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_classContext extends ParserRuleContext {
		public String name;
		public boolean att;
		public int id;
		public Token ID;
		public Token DATAMODEL_ID;
		public Token NAME;
		public TerminalNode ID() { return getToken(poqlParser.ID, 0); }
		public TerminalNode DATAMODEL_ID() { return getToken(poqlParser.DATAMODEL_ID, 0); }
		public TerminalNode NAME() { return getToken(poqlParser.NAME, 0); }
		public Id_classContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_class; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterId_class(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitId_class(this);
		}
	}

	public final Id_classContext id_class() throws RecognitionException {
		Id_classContext _localctx = new Id_classContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_id_class);
		try {
			setState(512);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(506);
				((Id_classContext)_localctx).ID = match(ID);
				((Id_classContext)_localctx).name =  (((Id_classContext)_localctx).ID!=null?((Id_classContext)_localctx).ID.getText():null); ((Id_classContext)_localctx).att =  false; ((Id_classContext)_localctx).id =  (((Id_classContext)_localctx).ID!=null?((Id_classContext)_localctx).ID.getType():0);
				}
				break;
			case DATAMODEL_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(508);
				((Id_classContext)_localctx).DATAMODEL_ID = match(DATAMODEL_ID);
				((Id_classContext)_localctx).name =  (((Id_classContext)_localctx).DATAMODEL_ID!=null?((Id_classContext)_localctx).DATAMODEL_ID.getText():null); ((Id_classContext)_localctx).att =  false; ((Id_classContext)_localctx).id =  (((Id_classContext)_localctx).DATAMODEL_ID!=null?((Id_classContext)_localctx).DATAMODEL_ID.getType():0);
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 3);
				{
				setState(510);
				((Id_classContext)_localctx).NAME = match(NAME);
				((Id_classContext)_localctx).name =  (((Id_classContext)_localctx).NAME!=null?((Id_classContext)_localctx).NAME.getText():null); ((Id_classContext)_localctx).att =  false; ((Id_classContext)_localctx).id =  (((Id_classContext)_localctx).NAME!=null?((Id_classContext)_localctx).NAME.getType():0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_relationshipContext extends ParserRuleContext {
		public String name;
		public boolean att;
		public int id;
		public Token ID;
		public Token SOURCE;
		public Token TARGET;
		public Token NAME;
		public TerminalNode ID() { return getToken(poqlParser.ID, 0); }
		public TerminalNode SOURCE() { return getToken(poqlParser.SOURCE, 0); }
		public TerminalNode TARGET() { return getToken(poqlParser.TARGET, 0); }
		public TerminalNode NAME() { return getToken(poqlParser.NAME, 0); }
		public Id_relationshipContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_relationship; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterId_relationship(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitId_relationship(this);
		}
	}

	public final Id_relationshipContext id_relationship() throws RecognitionException {
		Id_relationshipContext _localctx = new Id_relationshipContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_id_relationship);
		try {
			setState(522);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(514);
				((Id_relationshipContext)_localctx).ID = match(ID);
				((Id_relationshipContext)_localctx).name =  (((Id_relationshipContext)_localctx).ID!=null?((Id_relationshipContext)_localctx).ID.getText():null); ((Id_relationshipContext)_localctx).att =  false; ((Id_relationshipContext)_localctx).id =  (((Id_relationshipContext)_localctx).ID!=null?((Id_relationshipContext)_localctx).ID.getType():0);
				}
				break;
			case SOURCE:
				enterOuterAlt(_localctx, 2);
				{
				setState(516);
				((Id_relationshipContext)_localctx).SOURCE = match(SOURCE);
				((Id_relationshipContext)_localctx).name =  (((Id_relationshipContext)_localctx).SOURCE!=null?((Id_relationshipContext)_localctx).SOURCE.getText():null); ((Id_relationshipContext)_localctx).att =  false; ((Id_relationshipContext)_localctx).id =  (((Id_relationshipContext)_localctx).SOURCE!=null?((Id_relationshipContext)_localctx).SOURCE.getType():0);
				}
				break;
			case TARGET:
				enterOuterAlt(_localctx, 3);
				{
				setState(518);
				((Id_relationshipContext)_localctx).TARGET = match(TARGET);
				((Id_relationshipContext)_localctx).name =  (((Id_relationshipContext)_localctx).TARGET!=null?((Id_relationshipContext)_localctx).TARGET.getText():null); ((Id_relationshipContext)_localctx).att =  false; ((Id_relationshipContext)_localctx).id =  (((Id_relationshipContext)_localctx).TARGET!=null?((Id_relationshipContext)_localctx).TARGET.getType():0);
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 4);
				{
				setState(520);
				((Id_relationshipContext)_localctx).NAME = match(NAME);
				((Id_relationshipContext)_localctx).name =  (((Id_relationshipContext)_localctx).NAME!=null?((Id_relationshipContext)_localctx).NAME.getText():null); ((Id_relationshipContext)_localctx).att =  false; ((Id_relationshipContext)_localctx).id =  (((Id_relationshipContext)_localctx).NAME!=null?((Id_relationshipContext)_localctx).NAME.getType():0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_relationContext extends ParserRuleContext {
		public String name;
		public boolean att;
		public int id;
		public Token ID;
		public Token SOURCE_OBJECT_VERSION_ID;
		public Token TARGET_OBJECT_VERSION_ID;
		public Token RELATIONSHIP_ID;
		public Token START_TIMESTAMP;
		public Token END_TIMESTAMP;
		public TerminalNode ID() { return getToken(poqlParser.ID, 0); }
		public TerminalNode SOURCE_OBJECT_VERSION_ID() { return getToken(poqlParser.SOURCE_OBJECT_VERSION_ID, 0); }
		public TerminalNode TARGET_OBJECT_VERSION_ID() { return getToken(poqlParser.TARGET_OBJECT_VERSION_ID, 0); }
		public TerminalNode RELATIONSHIP_ID() { return getToken(poqlParser.RELATIONSHIP_ID, 0); }
		public TerminalNode START_TIMESTAMP() { return getToken(poqlParser.START_TIMESTAMP, 0); }
		public TerminalNode END_TIMESTAMP() { return getToken(poqlParser.END_TIMESTAMP, 0); }
		public Id_relationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_relation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterId_relation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitId_relation(this);
		}
	}

	public final Id_relationContext id_relation() throws RecognitionException {
		Id_relationContext _localctx = new Id_relationContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_id_relation);
		try {
			setState(536);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(524);
				((Id_relationContext)_localctx).ID = match(ID);
				((Id_relationContext)_localctx).name =  (((Id_relationContext)_localctx).ID!=null?((Id_relationContext)_localctx).ID.getText():null); ((Id_relationContext)_localctx).att =  false; ((Id_relationContext)_localctx).id =  (((Id_relationContext)_localctx).ID!=null?((Id_relationContext)_localctx).ID.getType():0);
				}
				break;
			case SOURCE_OBJECT_VERSION_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(526);
				((Id_relationContext)_localctx).SOURCE_OBJECT_VERSION_ID = match(SOURCE_OBJECT_VERSION_ID);
				((Id_relationContext)_localctx).name =  (((Id_relationContext)_localctx).SOURCE_OBJECT_VERSION_ID!=null?((Id_relationContext)_localctx).SOURCE_OBJECT_VERSION_ID.getText():null); ((Id_relationContext)_localctx).att =  false; ((Id_relationContext)_localctx).id =  (((Id_relationContext)_localctx).SOURCE_OBJECT_VERSION_ID!=null?((Id_relationContext)_localctx).SOURCE_OBJECT_VERSION_ID.getType():0);
				}
				break;
			case TARGET_OBJECT_VERSION_ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(528);
				((Id_relationContext)_localctx).TARGET_OBJECT_VERSION_ID = match(TARGET_OBJECT_VERSION_ID);
				((Id_relationContext)_localctx).name =  (((Id_relationContext)_localctx).TARGET_OBJECT_VERSION_ID!=null?((Id_relationContext)_localctx).TARGET_OBJECT_VERSION_ID.getText():null); ((Id_relationContext)_localctx).att =  false; ((Id_relationContext)_localctx).id =  (((Id_relationContext)_localctx).TARGET_OBJECT_VERSION_ID!=null?((Id_relationContext)_localctx).TARGET_OBJECT_VERSION_ID.getType():0);
				}
				break;
			case RELATIONSHIP_ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(530);
				((Id_relationContext)_localctx).RELATIONSHIP_ID = match(RELATIONSHIP_ID);
				((Id_relationContext)_localctx).name =  (((Id_relationContext)_localctx).RELATIONSHIP_ID!=null?((Id_relationContext)_localctx).RELATIONSHIP_ID.getText():null); ((Id_relationContext)_localctx).att =  false; ((Id_relationContext)_localctx).id =  (((Id_relationContext)_localctx).RELATIONSHIP_ID!=null?((Id_relationContext)_localctx).RELATIONSHIP_ID.getType():0);
				}
				break;
			case START_TIMESTAMP:
				enterOuterAlt(_localctx, 5);
				{
				setState(532);
				((Id_relationContext)_localctx).START_TIMESTAMP = match(START_TIMESTAMP);
				((Id_relationContext)_localctx).name =  (((Id_relationContext)_localctx).START_TIMESTAMP!=null?((Id_relationContext)_localctx).START_TIMESTAMP.getText():null); ((Id_relationContext)_localctx).att =  false; ((Id_relationContext)_localctx).id =  (((Id_relationContext)_localctx).START_TIMESTAMP!=null?((Id_relationContext)_localctx).START_TIMESTAMP.getType():0);
				}
				break;
			case END_TIMESTAMP:
				enterOuterAlt(_localctx, 6);
				{
				setState(534);
				((Id_relationContext)_localctx).END_TIMESTAMP = match(END_TIMESTAMP);
				((Id_relationContext)_localctx).name =  (((Id_relationContext)_localctx).END_TIMESTAMP!=null?((Id_relationContext)_localctx).END_TIMESTAMP.getText():null); ((Id_relationContext)_localctx).att =  false; ((Id_relationContext)_localctx).id =  (((Id_relationContext)_localctx).END_TIMESTAMP!=null?((Id_relationContext)_localctx).END_TIMESTAMP.getType():0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_eventContext extends ParserRuleContext {
		public String name;
		public boolean att;
		public int id;
		public Token ID;
		public Token ACTIVITY_INSTANCE_ID;
		public Token ORDERING;
		public Token TIMESTAMP;
		public Token LIFECYCLE;
		public Token RESOURCE;
		public Token IDATT;
		public TerminalNode ID() { return getToken(poqlParser.ID, 0); }
		public TerminalNode ACTIVITY_INSTANCE_ID() { return getToken(poqlParser.ACTIVITY_INSTANCE_ID, 0); }
		public TerminalNode ORDERING() { return getToken(poqlParser.ORDERING, 0); }
		public TerminalNode TIMESTAMP() { return getToken(poqlParser.TIMESTAMP, 0); }
		public TerminalNode LIFECYCLE() { return getToken(poqlParser.LIFECYCLE, 0); }
		public TerminalNode RESOURCE() { return getToken(poqlParser.RESOURCE, 0); }
		public TerminalNode IDATT() { return getToken(poqlParser.IDATT, 0); }
		public Id_eventContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_event; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterId_event(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitId_event(this);
		}
	}

	public final Id_eventContext id_event() throws RecognitionException {
		Id_eventContext _localctx = new Id_eventContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_id_event);
		try {
			setState(552);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(538);
				((Id_eventContext)_localctx).ID = match(ID);
				((Id_eventContext)_localctx).name =  (((Id_eventContext)_localctx).ID!=null?((Id_eventContext)_localctx).ID.getText():null); ((Id_eventContext)_localctx).att =  false; ((Id_eventContext)_localctx).id =  (((Id_eventContext)_localctx).ID!=null?((Id_eventContext)_localctx).ID.getType():0);
				}
				break;
			case ACTIVITY_INSTANCE_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(540);
				((Id_eventContext)_localctx).ACTIVITY_INSTANCE_ID = match(ACTIVITY_INSTANCE_ID);
				((Id_eventContext)_localctx).name =  (((Id_eventContext)_localctx).ACTIVITY_INSTANCE_ID!=null?((Id_eventContext)_localctx).ACTIVITY_INSTANCE_ID.getText():null); ((Id_eventContext)_localctx).att =  false; ((Id_eventContext)_localctx).id =  (((Id_eventContext)_localctx).ACTIVITY_INSTANCE_ID!=null?((Id_eventContext)_localctx).ACTIVITY_INSTANCE_ID.getType():0);
				}
				break;
			case ORDERING:
				enterOuterAlt(_localctx, 3);
				{
				setState(542);
				((Id_eventContext)_localctx).ORDERING = match(ORDERING);
				((Id_eventContext)_localctx).name =  (((Id_eventContext)_localctx).ORDERING!=null?((Id_eventContext)_localctx).ORDERING.getText():null); ((Id_eventContext)_localctx).att =  false; ((Id_eventContext)_localctx).id =  (((Id_eventContext)_localctx).ORDERING!=null?((Id_eventContext)_localctx).ORDERING.getType():0);
				}
				break;
			case TIMESTAMP:
				enterOuterAlt(_localctx, 4);
				{
				setState(544);
				((Id_eventContext)_localctx).TIMESTAMP = match(TIMESTAMP);
				((Id_eventContext)_localctx).name =  (((Id_eventContext)_localctx).TIMESTAMP!=null?((Id_eventContext)_localctx).TIMESTAMP.getText():null); ((Id_eventContext)_localctx).att =  false; ((Id_eventContext)_localctx).id =  (((Id_eventContext)_localctx).TIMESTAMP!=null?((Id_eventContext)_localctx).TIMESTAMP.getType():0);
				}
				break;
			case LIFECYCLE:
				enterOuterAlt(_localctx, 5);
				{
				setState(546);
				((Id_eventContext)_localctx).LIFECYCLE = match(LIFECYCLE);
				((Id_eventContext)_localctx).name =  (((Id_eventContext)_localctx).LIFECYCLE!=null?((Id_eventContext)_localctx).LIFECYCLE.getText():null); ((Id_eventContext)_localctx).att =  false; ((Id_eventContext)_localctx).id =  (((Id_eventContext)_localctx).LIFECYCLE!=null?((Id_eventContext)_localctx).LIFECYCLE.getType():0);
				}
				break;
			case RESOURCE:
				enterOuterAlt(_localctx, 6);
				{
				setState(548);
				((Id_eventContext)_localctx).RESOURCE = match(RESOURCE);
				((Id_eventContext)_localctx).name =  (((Id_eventContext)_localctx).RESOURCE!=null?((Id_eventContext)_localctx).RESOURCE.getText():null); ((Id_eventContext)_localctx).att =  false; ((Id_eventContext)_localctx).id =  (((Id_eventContext)_localctx).RESOURCE!=null?((Id_eventContext)_localctx).RESOURCE.getType():0);
				}
				break;
			case IDATT:
				enterOuterAlt(_localctx, 7);
				{
				setState(550);
				((Id_eventContext)_localctx).IDATT = match(IDATT);
				((Id_eventContext)_localctx).name =  (((Id_eventContext)_localctx).IDATT!=null?((Id_eventContext)_localctx).IDATT.getText():null); ((Id_eventContext)_localctx).att =  true;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_caseContext extends ParserRuleContext {
		public String name;
		public boolean att;
		public int id;
		public Token ID;
		public Token NAME;
		public TerminalNode ID() { return getToken(poqlParser.ID, 0); }
		public TerminalNode NAME() { return getToken(poqlParser.NAME, 0); }
		public Id_caseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_case; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterId_case(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitId_case(this);
		}
	}

	public final Id_caseContext id_case() throws RecognitionException {
		Id_caseContext _localctx = new Id_caseContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_id_case);
		try {
			setState(558);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(554);
				((Id_caseContext)_localctx).ID = match(ID);
				((Id_caseContext)_localctx).name =  (((Id_caseContext)_localctx).ID!=null?((Id_caseContext)_localctx).ID.getText():null); ((Id_caseContext)_localctx).att =  false; ((Id_caseContext)_localctx).id =  (((Id_caseContext)_localctx).ID!=null?((Id_caseContext)_localctx).ID.getType():0);
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(556);
				((Id_caseContext)_localctx).NAME = match(NAME);
				((Id_caseContext)_localctx).name =  (((Id_caseContext)_localctx).NAME!=null?((Id_caseContext)_localctx).NAME.getText():null); ((Id_caseContext)_localctx).att =  false; ((Id_caseContext)_localctx).id =  (((Id_caseContext)_localctx).NAME!=null?((Id_caseContext)_localctx).NAME.getType():0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_activity_instanceContext extends ParserRuleContext {
		public String name;
		public boolean att;
		public int id;
		public Token ID;
		public Token ACTIVITY_ID;
		public TerminalNode ID() { return getToken(poqlParser.ID, 0); }
		public TerminalNode ACTIVITY_ID() { return getToken(poqlParser.ACTIVITY_ID, 0); }
		public Id_activity_instanceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_activity_instance; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterId_activity_instance(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitId_activity_instance(this);
		}
	}

	public final Id_activity_instanceContext id_activity_instance() throws RecognitionException {
		Id_activity_instanceContext _localctx = new Id_activity_instanceContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_id_activity_instance);
		try {
			setState(564);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(560);
				((Id_activity_instanceContext)_localctx).ID = match(ID);
				((Id_activity_instanceContext)_localctx).name =  (((Id_activity_instanceContext)_localctx).ID!=null?((Id_activity_instanceContext)_localctx).ID.getText():null); ((Id_activity_instanceContext)_localctx).att =  false; ((Id_activity_instanceContext)_localctx).id =  (((Id_activity_instanceContext)_localctx).ID!=null?((Id_activity_instanceContext)_localctx).ID.getType():0);
				}
				break;
			case ACTIVITY_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(562);
				((Id_activity_instanceContext)_localctx).ACTIVITY_ID = match(ACTIVITY_ID);
				((Id_activity_instanceContext)_localctx).name =  (((Id_activity_instanceContext)_localctx).ACTIVITY_ID!=null?((Id_activity_instanceContext)_localctx).ACTIVITY_ID.getText():null); ((Id_activity_instanceContext)_localctx).att =  false; ((Id_activity_instanceContext)_localctx).id =  (((Id_activity_instanceContext)_localctx).ACTIVITY_ID!=null?((Id_activity_instanceContext)_localctx).ACTIVITY_ID.getType():0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_activityContext extends ParserRuleContext {
		public String name;
		public boolean att;
		public int id;
		public Token ID;
		public Token PROCESS_ID;
		public Token NAME;
		public TerminalNode ID() { return getToken(poqlParser.ID, 0); }
		public TerminalNode PROCESS_ID() { return getToken(poqlParser.PROCESS_ID, 0); }
		public TerminalNode NAME() { return getToken(poqlParser.NAME, 0); }
		public Id_activityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_activity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterId_activity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitId_activity(this);
		}
	}

	public final Id_activityContext id_activity() throws RecognitionException {
		Id_activityContext _localctx = new Id_activityContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_id_activity);
		try {
			setState(572);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(566);
				((Id_activityContext)_localctx).ID = match(ID);
				((Id_activityContext)_localctx).name =  (((Id_activityContext)_localctx).ID!=null?((Id_activityContext)_localctx).ID.getText():null); ((Id_activityContext)_localctx).att =  false; ((Id_activityContext)_localctx).id =  (((Id_activityContext)_localctx).ID!=null?((Id_activityContext)_localctx).ID.getType():0); 
				}
				break;
			case PROCESS_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(568);
				((Id_activityContext)_localctx).PROCESS_ID = match(PROCESS_ID);
				((Id_activityContext)_localctx).name =  (((Id_activityContext)_localctx).PROCESS_ID!=null?((Id_activityContext)_localctx).PROCESS_ID.getText():null); ((Id_activityContext)_localctx).att =  false; ((Id_activityContext)_localctx).id =  (((Id_activityContext)_localctx).PROCESS_ID!=null?((Id_activityContext)_localctx).PROCESS_ID.getType():0); 
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 3);
				{
				setState(570);
				((Id_activityContext)_localctx).NAME = match(NAME);
				((Id_activityContext)_localctx).name =  (((Id_activityContext)_localctx).NAME!=null?((Id_activityContext)_localctx).NAME.getText():null); ((Id_activityContext)_localctx).att =  false; ((Id_activityContext)_localctx).id =  (((Id_activityContext)_localctx).NAME!=null?((Id_activityContext)_localctx).NAME.getType():0); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_attributeContext extends ParserRuleContext {
		public String name;
		public boolean att;
		public int id;
		public Token ID;
		public Token CLASS_ID;
		public Token NAME;
		public TerminalNode ID() { return getToken(poqlParser.ID, 0); }
		public TerminalNode CLASS_ID() { return getToken(poqlParser.CLASS_ID, 0); }
		public TerminalNode NAME() { return getToken(poqlParser.NAME, 0); }
		public Id_attributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterId_attribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitId_attribute(this);
		}
	}

	public final Id_attributeContext id_attribute() throws RecognitionException {
		Id_attributeContext _localctx = new Id_attributeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_id_attribute);
		try {
			setState(580);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(574);
				((Id_attributeContext)_localctx).ID = match(ID);
				((Id_attributeContext)_localctx).name =  (((Id_attributeContext)_localctx).ID!=null?((Id_attributeContext)_localctx).ID.getText():null); ((Id_attributeContext)_localctx).att =  false; ((Id_attributeContext)_localctx).id =  (((Id_attributeContext)_localctx).ID!=null?((Id_attributeContext)_localctx).ID.getType():0); 
				}
				break;
			case CLASS_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(576);
				((Id_attributeContext)_localctx).CLASS_ID = match(CLASS_ID);
				((Id_attributeContext)_localctx).name =  (((Id_attributeContext)_localctx).CLASS_ID!=null?((Id_attributeContext)_localctx).CLASS_ID.getText():null); ((Id_attributeContext)_localctx).att =  false; ((Id_attributeContext)_localctx).id =  (((Id_attributeContext)_localctx).CLASS_ID!=null?((Id_attributeContext)_localctx).CLASS_ID.getType():0); 
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 3);
				{
				setState(578);
				((Id_attributeContext)_localctx).NAME = match(NAME);
				((Id_attributeContext)_localctx).name =  (((Id_attributeContext)_localctx).NAME!=null?((Id_attributeContext)_localctx).NAME.getText():null); ((Id_attributeContext)_localctx).att =  false; ((Id_attributeContext)_localctx).id =  (((Id_attributeContext)_localctx).NAME!=null?((Id_attributeContext)_localctx).NAME.getType():0); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllObjectsContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public TerminalNode ALLOBJECTS() { return getToken(poqlParser.ALLOBJECTS, 0); }
		public AllObjectsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allObjects; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterAllObjects(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitAllObjects(this);
		}
	}

	public final AllObjectsContext allObjects() throws RecognitionException {
		AllObjectsContext _localctx = new AllObjectsContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_allObjects);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(582);
			match(ALLOBJECTS);
			 ((AllObjectsContext)_localctx).list =  poql.getAllObjects(); ((AllObjectsContext)_localctx).type = SLEXMMObject.class;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllCasesContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public TerminalNode ALLCASES() { return getToken(poqlParser.ALLCASES, 0); }
		public AllCasesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allCases; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterAllCases(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitAllCases(this);
		}
	}

	public final AllCasesContext allCases() throws RecognitionException {
		AllCasesContext _localctx = new AllCasesContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_allCases);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(585);
			match(ALLCASES);
			 ((AllCasesContext)_localctx).list =  poql.getAllCases(); ((AllCasesContext)_localctx).type = SLEXMMCase.class;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllEventsContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public TerminalNode ALLEVENTS() { return getToken(poqlParser.ALLEVENTS, 0); }
		public AllEventsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allEvents; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterAllEvents(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitAllEvents(this);
		}
	}

	public final AllEventsContext allEvents() throws RecognitionException {
		AllEventsContext _localctx = new AllEventsContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_allEvents);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(588);
			match(ALLEVENTS);
			 ((AllEventsContext)_localctx).list =  poql.getAllEvents(); ((AllEventsContext)_localctx).type = SLEXMMEvent.class;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllClassesContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public TerminalNode ALLCLASSES() { return getToken(poqlParser.ALLCLASSES, 0); }
		public AllClassesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allClasses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterAllClasses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitAllClasses(this);
		}
	}

	public final AllClassesContext allClasses() throws RecognitionException {
		AllClassesContext _localctx = new AllClassesContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_allClasses);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(591);
			match(ALLCLASSES);
			 ((AllClassesContext)_localctx).list =  poql.getAllClasses(); ((AllClassesContext)_localctx).type = SLEXMMClass.class;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllVersionsContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public TerminalNode ALLVERSIONS() { return getToken(poqlParser.ALLVERSIONS, 0); }
		public AllVersionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allVersions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterAllVersions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitAllVersions(this);
		}
	}

	public final AllVersionsContext allVersions() throws RecognitionException {
		AllVersionsContext _localctx = new AllVersionsContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_allVersions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(594);
			match(ALLVERSIONS);
			 ((AllVersionsContext)_localctx).list =  poql.getAllVersions(); ((AllVersionsContext)_localctx).type = SLEXMMObjectVersion.class;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllActivitiesContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public TerminalNode ALLACTIVITIES() { return getToken(poqlParser.ALLACTIVITIES, 0); }
		public AllActivitiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allActivities; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterAllActivities(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitAllActivities(this);
		}
	}

	public final AllActivitiesContext allActivities() throws RecognitionException {
		AllActivitiesContext _localctx = new AllActivitiesContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_allActivities);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(597);
			match(ALLACTIVITIES);
			 ((AllActivitiesContext)_localctx).list =  poql.getAllActivities(); ((AllActivitiesContext)_localctx).type = SLEXMMActivity.class;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllRelationsContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public TerminalNode ALLRELATIONS() { return getToken(poqlParser.ALLRELATIONS, 0); }
		public AllRelationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allRelations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterAllRelations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitAllRelations(this);
		}
	}

	public final AllRelationsContext allRelations() throws RecognitionException {
		AllRelationsContext _localctx = new AllRelationsContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_allRelations);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(600);
			match(ALLRELATIONS);
			 ((AllRelationsContext)_localctx).list =  poql.getAllRelations(); ((AllRelationsContext)_localctx).type = SLEXMMRelation.class;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllRelationshipsContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public TerminalNode ALLRELATIONSHIPS() { return getToken(poqlParser.ALLRELATIONSHIPS, 0); }
		public AllRelationshipsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allRelationships; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterAllRelationships(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitAllRelationships(this);
		}
	}

	public final AllRelationshipsContext allRelationships() throws RecognitionException {
		AllRelationshipsContext _localctx = new AllRelationshipsContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_allRelationships);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(603);
			match(ALLRELATIONSHIPS);
			 ((AllRelationshipsContext)_localctx).list =  poql.getAllRelationships(); ((AllRelationshipsContext)_localctx).type = SLEXMMRelationship.class;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllActivityInstancesContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public TerminalNode ALLACTIVITYINSTANCES() { return getToken(poqlParser.ALLACTIVITYINSTANCES, 0); }
		public AllActivityInstancesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allActivityInstances; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterAllActivityInstances(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitAllActivityInstances(this);
		}
	}

	public final AllActivityInstancesContext allActivityInstances() throws RecognitionException {
		AllActivityInstancesContext _localctx = new AllActivityInstancesContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_allActivityInstances);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(606);
			match(ALLACTIVITYINSTANCES);
			 ((AllActivityInstancesContext)_localctx).list =  poql.getAllActivityInstances(); ((AllActivityInstancesContext)_localctx).type = SLEXMMActivityInstance.class;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllAttributesContext extends ParserRuleContext {
		public Set<Object> list;
		public Class type;
		public TerminalNode ALLATTRIBUTES() { return getToken(poqlParser.ALLATTRIBUTES, 0); }
		public AllAttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allAttributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterAllAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitAllAttributes(this);
		}
	}

	public final AllAttributesContext allAttributes() throws RecognitionException {
		AllAttributesContext _localctx = new AllAttributesContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_allAttributes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(609);
			match(ALLATTRIBUTES);
			 ((AllAttributesContext)_localctx).list =  poql.getAllAttributes(); ((AllAttributesContext)_localctx).type = SLEXMMAttribute.class;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return objects_sempred((ObjectsContext)_localctx, predIndex);
		case 4:
			return cases_sempred((CasesContext)_localctx, predIndex);
		case 5:
			return events_sempred((EventsContext)_localctx, predIndex);
		case 6:
			return classes_sempred((ClassesContext)_localctx, predIndex);
		case 7:
			return versions_sempred((VersionsContext)_localctx, predIndex);
		case 8:
			return activities_sempred((ActivitiesContext)_localctx, predIndex);
		case 9:
			return relations_sempred((RelationsContext)_localctx, predIndex);
		case 10:
			return relationships_sempred((RelationshipsContext)_localctx, predIndex);
		case 11:
			return activityinstances_sempred((ActivityinstancesContext)_localctx, predIndex);
		case 12:
			return attributes_sempred((AttributesContext)_localctx, predIndex);
		case 16:
			return operator_sempred((OperatorContext)_localctx, predIndex);
		case 17:
			return ids_sempred((IdsContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean objects_sempred(ObjectsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean cases_sempred(CasesContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean events_sempred(EventsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean classes_sempred(ClassesContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean versions_sempred(VersionsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean activities_sempred(ActivitiesContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean relations_sempred(RelationsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean relationships_sempred(RelationshipsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean activityinstances_sempred(ActivityinstancesContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean attributes_sempred(AttributesContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean operator_sempred(OperatorContext _localctx, int predIndex) {
		switch (predIndex) {
		case 10:
			return _localctx.type_id == ID_TYPE_VERSION && _localctx.att;
		}
		return true;
	}
	private boolean ids_sempred(IdsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 11:
			return _localctx.type_id == ID_TYPE_VERSION;
		case 12:
			return _localctx.type_id == ID_TYPE_OBJECT;
		case 13:
			return _localctx.type_id == ID_TYPE_CLASS;
		case 14:
			return _localctx.type_id == ID_TYPE_RELATIONSHIP;
		case 15:
			return _localctx.type_id == ID_TYPE_RELATION;
		case 16:
			return _localctx.type_id == ID_TYPE_EVENT;
		case 17:
			return _localctx.type_id == ID_TYPE_CASE;
		case 18:
			return _localctx.type_id == ID_TYPE_ACTIVITY_INSTANCE;
		case 19:
			return _localctx.type_id == ID_TYPE_ACTIVITY;
		case 20:
			return _localctx.type_id == ID_TYPE_ATTRIBUTE;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3A\u0267\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\5\3X\n\3\3\4\3\4\3\4\3\4\5\4^\n\4\3\4\3\4\3\4\3\4\3\4\3\4\5"+
		"\4f\n\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4n\n\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4v"+
		"\n\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4~\n\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u0086"+
		"\n\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u008e\n\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4"+
		"\u0096\n\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u009e\n\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\5\4\u00a6\n\4\3\4\3\4\5\4\u00aa\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\5\5\u00b6\n\5\3\5\3\5\3\5\3\5\7\5\u00bc\n\5\f\5\16\5\u00bf\13"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u00cb\n\6\3\6\3\6\3\6\3"+
		"\6\7\6\u00d1\n\6\f\6\16\6\u00d4\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\5\7\u00e0\n\7\3\7\3\7\3\7\3\7\7\7\u00e6\n\7\f\7\16\7\u00e9\13\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00f5\n\b\3\b\3\b\3\b\3\b"+
		"\7\b\u00fb\n\b\f\b\16\b\u00fe\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0110\n\t\3\t\3\t\3\t\3\t\7\t\u0116\n"+
		"\t\f\t\16\t\u0119\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0125"+
		"\n\n\3\n\3\n\3\n\3\n\7\n\u012b\n\n\f\n\16\n\u012e\13\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u013a\n\13\3\13\3\13\3\13\3\13"+
		"\7\13\u0140\n\13\f\13\16\13\u0143\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\5\f\u014f\n\f\3\f\3\f\3\f\3\f\7\f\u0155\n\f\f\f\16\f\u0158"+
		"\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0164\n\r\3\r\3\r\3"+
		"\r\3\r\7\r\u016a\n\r\f\r\16\r\u016d\13\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\5\16\u0179\n\16\3\16\3\16\3\16\3\16\7\16\u017f\n"+
		"\16\f\16\16\16\u0182\13\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u0197\n\20\3\21"+
		"\3\21\3\21\3\21\5\21\u019d\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\5\22\u01b8\n\22\3\22\3\22\5\22\u01bc\n\22\3\22\5\22\u01bf"+
		"\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23"+
		"\u01e9\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u01f5"+
		"\n\24\3\25\3\25\3\25\3\25\5\25\u01fb\n\25\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\5\26\u0203\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u020d\n"+
		"\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u021b"+
		"\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\5\31\u022b\n\31\3\32\3\32\3\32\3\32\5\32\u0231\n\32\3\33\3\33\3"+
		"\33\3\33\5\33\u0237\n\33\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u023f\n\34"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u0247\n\35\3\36\3\36\3\36\3\37\3\37"+
		"\3\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\3"+
		"&\3\'\3\'\3\'\3\'\2\f\b\n\f\16\20\22\24\26\30\32(\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJL\2\2\u029a\2N\3\2\2"+
		"\2\4W\3\2\2\2\6\u00a9\3\2\2\2\b\u00b5\3\2\2\2\n\u00ca\3\2\2\2\f\u00df"+
		"\3\2\2\2\16\u00f4\3\2\2\2\20\u010f\3\2\2\2\22\u0124\3\2\2\2\24\u0139\3"+
		"\2\2\2\26\u014e\3\2\2\2\30\u0163\3\2\2\2\32\u0178\3\2\2\2\34\u0183\3\2"+
		"\2\2\36\u0196\3\2\2\2 \u019c\3\2\2\2\"\u01be\3\2\2\2$\u01e8\3\2\2\2&\u01f4"+
		"\3\2\2\2(\u01fa\3\2\2\2*\u0202\3\2\2\2,\u020c\3\2\2\2.\u021a\3\2\2\2\60"+
		"\u022a\3\2\2\2\62\u0230\3\2\2\2\64\u0236\3\2\2\2\66\u023e\3\2\2\28\u0246"+
		"\3\2\2\2:\u0248\3\2\2\2<\u024b\3\2\2\2>\u024e\3\2\2\2@\u0251\3\2\2\2B"+
		"\u0254\3\2\2\2D\u0257\3\2\2\2F\u025a\3\2\2\2H\u025d\3\2\2\2J\u0260\3\2"+
		"\2\2L\u0263\3\2\2\2NO\5\6\4\2OP\b\2\1\2P\3\3\2\2\2QR\7\3\2\2RX\b\3\1\2"+
		"ST\7\4\2\2TX\b\3\1\2UV\7\5\2\2VX\b\3\1\2WQ\3\2\2\2WS\3\2\2\2WU\3\2\2\2"+
		"X\5\3\2\2\2Y]\5\n\6\2Z[\5\4\3\2[\\\5\n\6\2\\^\3\2\2\2]Z\3\2\2\2]^\3\2"+
		"\2\2^_\3\2\2\2_`\b\4\1\2`\u00aa\3\2\2\2ae\5\b\5\2bc\5\4\3\2cd\5\b\5\2"+
		"df\3\2\2\2eb\3\2\2\2ef\3\2\2\2fg\3\2\2\2gh\b\4\1\2h\u00aa\3\2\2\2im\5"+
		"\f\7\2jk\5\4\3\2kl\5\f\7\2ln\3\2\2\2mj\3\2\2\2mn\3\2\2\2no\3\2\2\2op\b"+
		"\4\1\2p\u00aa\3\2\2\2qu\5\16\b\2rs\5\4\3\2st\5\16\b\2tv\3\2\2\2ur\3\2"+
		"\2\2uv\3\2\2\2vw\3\2\2\2wx\b\4\1\2x\u00aa\3\2\2\2y}\5\20\t\2z{\5\4\3\2"+
		"{|\5\20\t\2|~\3\2\2\2}z\3\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080\b\4\1"+
		"\2\u0080\u00aa\3\2\2\2\u0081\u0085\5\22\n\2\u0082\u0083\5\4\3\2\u0083"+
		"\u0084\5\22\n\2\u0084\u0086\3\2\2\2\u0085\u0082\3\2\2\2\u0085\u0086\3"+
		"\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\b\4\1\2\u0088\u00aa\3\2\2\2\u0089"+
		"\u008d\5\24\13\2\u008a\u008b\5\4\3\2\u008b\u008c\5\24\13\2\u008c\u008e"+
		"\3\2\2\2\u008d\u008a\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\3\2\2\2\u008f"+
		"\u0090\b\4\1\2\u0090\u00aa\3\2\2\2\u0091\u0095\5\26\f\2\u0092\u0093\5"+
		"\4\3\2\u0093\u0094\5\26\f\2\u0094\u0096\3\2\2\2\u0095\u0092\3\2\2\2\u0095"+
		"\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\b\4\1\2\u0098\u00aa\3\2"+
		"\2\2\u0099\u009d\5\30\r\2\u009a\u009b\5\4\3\2\u009b\u009c\5\30\r\2\u009c"+
		"\u009e\3\2\2\2\u009d\u009a\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\3\2"+
		"\2\2\u009f\u00a0\b\4\1\2\u00a0\u00aa\3\2\2\2\u00a1\u00a5\5\32\16\2\u00a2"+
		"\u00a3\5\4\3\2\u00a3\u00a4\5\32\16\2\u00a4\u00a6\3\2\2\2\u00a5\u00a2\3"+
		"\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\b\4\1\2\u00a8"+
		"\u00aa\3\2\2\2\u00a9Y\3\2\2\2\u00a9a\3\2\2\2\u00a9i\3\2\2\2\u00a9q\3\2"+
		"\2\2\u00a9y\3\2\2\2\u00a9\u0081\3\2\2\2\u00a9\u0089\3\2\2\2\u00a9\u0091"+
		"\3\2\2\2\u00a9\u0099\3\2\2\2\u00a9\u00a1\3\2\2\2\u00aa\7\3\2\2\2\u00ab"+
		"\u00ac\b\5\1\2\u00ac\u00ad\7\7\2\2\u00ad\u00ae\7.\2\2\u00ae\u00af\5\6"+
		"\4\2\u00af\u00b0\7/\2\2\u00b0\u00b1\b\5\1\2\u00b1\u00b6\3\2\2\2\u00b2"+
		"\u00b3\5:\36\2\u00b3\u00b4\b\5\1\2\u00b4\u00b6\3\2\2\2\u00b5\u00ab\3\2"+
		"\2\2\u00b5\u00b2\3\2\2\2\u00b6\u00bd\3\2\2\2\u00b7\u00b8\f\4\2\2\u00b8"+
		"\u00b9\5\34\17\2\u00b9\u00ba\b\5\1\2\u00ba\u00bc\3\2\2\2\u00bb\u00b7\3"+
		"\2\2\2\u00bc\u00bf\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be"+
		"\t\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0\u00c1\b\6\1\2\u00c1\u00c2\7\6\2\2"+
		"\u00c2\u00c3\7.\2\2\u00c3\u00c4\5\6\4\2\u00c4\u00c5\7/\2\2\u00c5\u00c6"+
		"\b\6\1\2\u00c6\u00cb\3\2\2\2\u00c7\u00c8\5<\37\2\u00c8\u00c9\b\6\1\2\u00c9"+
		"\u00cb\3\2\2\2\u00ca\u00c0\3\2\2\2\u00ca\u00c7\3\2\2\2\u00cb\u00d2\3\2"+
		"\2\2\u00cc\u00cd\f\3\2\2\u00cd\u00ce\5\34\17\2\u00ce\u00cf\b\6\1\2\u00cf"+
		"\u00d1\3\2\2\2\u00d0\u00cc\3\2\2\2\u00d1\u00d4\3\2\2\2\u00d2\u00d0\3\2"+
		"\2\2\u00d2\u00d3\3\2\2\2\u00d3\13\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d5\u00d6"+
		"\b\7\1\2\u00d6\u00d7\7\b\2\2\u00d7\u00d8\7.\2\2\u00d8\u00d9\5\6\4\2\u00d9"+
		"\u00da\7/\2\2\u00da\u00db\b\7\1\2\u00db\u00e0\3\2\2\2\u00dc\u00dd\5> "+
		"\2\u00dd\u00de\b\7\1\2\u00de\u00e0\3\2\2\2\u00df\u00d5\3\2\2\2\u00df\u00dc"+
		"\3\2\2\2\u00e0\u00e7\3\2\2\2\u00e1\u00e2\f\3\2\2\u00e2\u00e3\5\34\17\2"+
		"\u00e3\u00e4\b\7\1\2\u00e4\u00e6\3\2\2\2\u00e5\u00e1\3\2\2\2\u00e6\u00e9"+
		"\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\r\3\2\2\2\u00e9"+
		"\u00e7\3\2\2\2\u00ea\u00eb\b\b\1\2\u00eb\u00ec\7\t\2\2\u00ec\u00ed\7."+
		"\2\2\u00ed\u00ee\5\6\4\2\u00ee\u00ef\7/\2\2\u00ef\u00f0\b\b\1\2\u00f0"+
		"\u00f5\3\2\2\2\u00f1\u00f2\5@!\2\u00f2\u00f3\b\b\1\2\u00f3\u00f5\3\2\2"+
		"\2\u00f4\u00ea\3\2\2\2\u00f4\u00f1\3\2\2\2\u00f5\u00fc\3\2\2\2\u00f6\u00f7"+
		"\f\3\2\2\u00f7\u00f8\5\34\17\2\u00f8\u00f9\b\b\1\2\u00f9\u00fb\3\2\2\2"+
		"\u00fa\u00f6\3\2\2\2\u00fb\u00fe\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd"+
		"\3\2\2\2\u00fd\17\3\2\2\2\u00fe\u00fc\3\2\2\2\u00ff\u0100\b\t\1\2\u0100"+
		"\u0101\7\n\2\2\u0101\u0102\7.\2\2\u0102\u0103\5\6\4\2\u0103\u0104\7/\2"+
		"\2\u0104\u0105\b\t\1\2\u0105\u0110\3\2\2\2\u0106\u0107\5B\"\2\u0107\u0108"+
		"\b\t\1\2\u0108\u0110\3\2\2\2\u0109\u010a\7\f\2\2\u010a\u010b\7.\2\2\u010b"+
		"\u010c\5\20\t\2\u010c\u010d\7/\2\2\u010d\u010e\b\t\1\2\u010e\u0110\3\2"+
		"\2\2\u010f\u00ff\3\2\2\2\u010f\u0106\3\2\2\2\u010f\u0109\3\2\2\2\u0110"+
		"\u0117\3\2\2\2\u0111\u0112\f\3\2\2\u0112\u0113\5\34\17\2\u0113\u0114\b"+
		"\t\1\2\u0114\u0116\3\2\2\2\u0115\u0111\3\2\2\2\u0116\u0119\3\2\2\2\u0117"+
		"\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\21\3\2\2\2\u0119\u0117\3\2\2"+
		"\2\u011a\u011b\b\n\1\2\u011b\u011c\7\13\2\2\u011c\u011d\7.\2\2\u011d\u011e"+
		"\5\6\4\2\u011e\u011f\7/\2\2\u011f\u0120\b\n\1\2\u0120\u0125\3\2\2\2\u0121"+
		"\u0122\5D#\2\u0122\u0123\b\n\1\2\u0123\u0125\3\2\2\2\u0124\u011a\3\2\2"+
		"\2\u0124\u0121\3\2\2\2\u0125\u012c\3\2\2\2\u0126\u0127\f\3\2\2\u0127\u0128"+
		"\5\34\17\2\u0128\u0129\b\n\1\2\u0129\u012b\3\2\2\2\u012a\u0126\3\2\2\2"+
		"\u012b\u012e\3\2\2\2\u012c\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\23"+
		"\3\2\2\2\u012e\u012c\3\2\2\2\u012f\u0130\b\13\1\2\u0130\u0131\7\r\2\2"+
		"\u0131\u0132\7.\2\2\u0132\u0133\5\6\4\2\u0133\u0134\7/\2\2\u0134\u0135"+
		"\b\13\1\2\u0135\u013a\3\2\2\2\u0136\u0137\5F$\2\u0137\u0138\b\13\1\2\u0138"+
		"\u013a\3\2\2\2\u0139\u012f\3\2\2\2\u0139\u0136\3\2\2\2\u013a\u0141\3\2"+
		"\2\2\u013b\u013c\f\3\2\2\u013c\u013d\5\34\17\2\u013d\u013e\b\13\1\2\u013e"+
		"\u0140\3\2\2\2\u013f\u013b\3\2\2\2\u0140\u0143\3\2\2\2\u0141\u013f\3\2"+
		"\2\2\u0141\u0142\3\2\2\2\u0142\25\3\2\2\2\u0143\u0141\3\2\2\2\u0144\u0145"+
		"\b\f\1\2\u0145\u0146\7\16\2\2\u0146\u0147\7.\2\2\u0147\u0148\5\6\4\2\u0148"+
		"\u0149\7/\2\2\u0149\u014a\b\f\1\2\u014a\u014f\3\2\2\2\u014b\u014c\5H%"+
		"\2\u014c\u014d\b\f\1\2\u014d\u014f\3\2\2\2\u014e\u0144\3\2\2\2\u014e\u014b"+
		"\3\2\2\2\u014f\u0156\3\2\2\2\u0150\u0151\f\3\2\2\u0151\u0152\5\34\17\2"+
		"\u0152\u0153\b\f\1\2\u0153\u0155\3\2\2\2\u0154\u0150\3\2\2\2\u0155\u0158"+
		"\3\2\2\2\u0156\u0154\3\2\2\2\u0156\u0157\3\2\2\2\u0157\27\3\2\2\2\u0158"+
		"\u0156\3\2\2\2\u0159\u015a\b\r\1\2\u015a\u015b\7\17\2\2\u015b\u015c\7"+
		".\2\2\u015c\u015d\5\6\4\2\u015d\u015e\7/\2\2\u015e\u015f\b\r\1\2\u015f"+
		"\u0164\3\2\2\2\u0160\u0161\5J&\2\u0161\u0162\b\r\1\2\u0162\u0164\3\2\2"+
		"\2\u0163\u0159\3\2\2\2\u0163\u0160\3\2\2\2\u0164\u016b\3\2\2\2\u0165\u0166"+
		"\f\3\2\2\u0166\u0167\5\34\17\2\u0167\u0168\b\r\1\2\u0168\u016a\3\2\2\2"+
		"\u0169\u0165\3\2\2\2\u016a\u016d\3\2\2\2\u016b\u0169\3\2\2\2\u016b\u016c"+
		"\3\2\2\2\u016c\31\3\2\2\2\u016d\u016b\3\2\2\2\u016e\u016f\b\16\1\2\u016f"+
		"\u0170\7\20\2\2\u0170\u0171\7.\2\2\u0171\u0172\5\6\4\2\u0172\u0173\7/"+
		"\2\2\u0173\u0174\b\16\1\2\u0174\u0179\3\2\2\2\u0175\u0176\5L\'\2\u0176"+
		"\u0177\b\16\1\2\u0177\u0179\3\2\2\2\u0178\u016e\3\2\2\2\u0178\u0175\3"+
		"\2\2\2\u0179\u0180\3\2\2\2\u017a\u017b\f\3\2\2\u017b\u017c\5\34\17\2\u017c"+
		"\u017d\b\16\1\2\u017d\u017f\3\2\2\2\u017e\u017a\3\2\2\2\u017f\u0182\3"+
		"\2\2\2\u0180\u017e\3\2\2\2\u0180\u0181\3\2\2\2\u0181\33\3\2\2\2\u0182"+
		"\u0180\3\2\2\2\u0183\u0184\7\60\2\2\u0184\u0185\5\36\20\2\u0185\u0186"+
		"\b\17\1\2\u0186\35\3\2\2\2\u0187\u0188\7:\2\2\u0188\u0189\5\36\20\2\u0189"+
		"\u018a\b\20\1\2\u018a\u0197\3\2\2\2\u018b\u018c\7.\2\2\u018c\u018d\5\36"+
		"\20\2\u018d\u018e\5 \21\2\u018e\u018f\5\36\20\2\u018f\u0190\7/\2\2\u0190"+
		"\u0191\b\20\1\2\u0191\u0197\3\2\2\2\u0192\u0193\5$\23\2\u0193\u0194\5"+
		"\"\22\2\u0194\u0195\b\20\1\2\u0195\u0197\3\2\2\2\u0196\u0187\3\2\2\2\u0196"+
		"\u018b\3\2\2\2\u0196\u0192\3\2\2\2\u0197\37\3\2\2\2\u0198\u0199\78\2\2"+
		"\u0199\u019d\b\21\1\2\u019a\u019b\79\2\2\u019b\u019d\b\21\1\2\u019c\u0198"+
		"\3\2\2\2\u019c\u019a\3\2\2\2\u019d!\3\2\2\2\u019e\u019f\7\61\2\2\u019f"+
		"\u01a0\7>\2\2\u01a0\u01bf\b\22\1\2\u01a1\u01a2\7\62\2\2\u01a2\u01a3\7"+
		">\2\2\u01a3\u01bf\b\22\1\2\u01a4\u01a5\7\63\2\2\u01a5\u01a6\7>\2\2\u01a6"+
		"\u01bf\b\22\1\2\u01a7\u01a8\7\64\2\2\u01a8\u01a9\7>\2\2\u01a9\u01bf\b"+
		"\22\1\2\u01aa\u01ab\7\65\2\2\u01ab\u01ac\7>\2\2\u01ac\u01bf\b\22\1\2\u01ad"+
		"\u01ae\7\66\2\2\u01ae\u01af\7>\2\2\u01af\u01bf\b\22\1\2\u01b0\u01b1\7"+
		"\67\2\2\u01b1\u01b2\7>\2\2\u01b2\u01bf\b\22\1\2\u01b3\u01b4\6\22\f\3\u01b4"+
		"\u01b7\7;\2\2\u01b5\u01b6\7<\2\2\u01b6\u01b8\7>\2\2\u01b7\u01b5\3\2\2"+
		"\2\u01b7\u01b8\3\2\2\2\u01b8\u01bb\3\2\2\2\u01b9\u01ba\7=\2\2\u01ba\u01bc"+
		"\7>\2\2\u01bb\u01b9\3\2\2\2\u01bb\u01bc\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd"+
		"\u01bf\b\22\1\2\u01be\u019e\3\2\2\2\u01be\u01a1\3\2\2\2\u01be\u01a4\3"+
		"\2\2\2\u01be\u01a7\3\2\2\2\u01be\u01aa\3\2\2\2\u01be\u01ad\3\2\2\2\u01be"+
		"\u01b0\3\2\2\2\u01be\u01b3\3\2\2\2\u01bf#\3\2\2\2\u01c0\u01c1\6\23\r\3"+
		"\u01c1\u01c2\5&\24\2\u01c2\u01c3\b\23\1\2\u01c3\u01e9\3\2\2\2\u01c4\u01c5"+
		"\6\23\16\3\u01c5\u01c6\5(\25\2\u01c6\u01c7\b\23\1\2\u01c7\u01e9\3\2\2"+
		"\2\u01c8\u01c9\6\23\17\3\u01c9\u01ca\5*\26\2\u01ca\u01cb\b\23\1\2\u01cb"+
		"\u01e9\3\2\2\2\u01cc\u01cd\6\23\20\3\u01cd\u01ce\5,\27\2\u01ce\u01cf\b"+
		"\23\1\2\u01cf\u01e9\3\2\2\2\u01d0\u01d1\6\23\21\3\u01d1\u01d2\5.\30\2"+
		"\u01d2\u01d3\b\23\1\2\u01d3\u01e9\3\2\2\2\u01d4\u01d5\6\23\22\3\u01d5"+
		"\u01d6\5\60\31\2\u01d6\u01d7\b\23\1\2\u01d7\u01e9\3\2\2\2\u01d8\u01d9"+
		"\6\23\23\3\u01d9\u01da\5\62\32\2\u01da\u01db\b\23\1\2\u01db\u01e9\3\2"+
		"\2\2\u01dc\u01dd\6\23\24\3\u01dd\u01de\5\64\33\2\u01de\u01df\b\23\1\2"+
		"\u01df\u01e9\3\2\2\2\u01e0\u01e1\6\23\25\3\u01e1\u01e2\5\66\34\2\u01e2"+
		"\u01e3\b\23\1\2\u01e3\u01e9\3\2\2\2\u01e4\u01e5\6\23\26\3\u01e5\u01e6"+
		"\58\35\2\u01e6\u01e7\b\23\1\2\u01e7\u01e9\3\2\2\2\u01e8\u01c0\3\2\2\2"+
		"\u01e8\u01c4\3\2\2\2\u01e8\u01c8\3\2\2\2\u01e8\u01cc\3\2\2\2\u01e8\u01d0"+
		"\3\2\2\2\u01e8\u01d4\3\2\2\2\u01e8\u01d8\3\2\2\2\u01e8\u01dc\3\2\2\2\u01e8"+
		"\u01e0\3\2\2\2\u01e8\u01e4\3\2\2\2\u01e9%\3\2\2\2\u01ea\u01eb\7\33\2\2"+
		"\u01eb\u01f5\b\24\1\2\u01ec\u01ed\7!\2\2\u01ed\u01f5\b\24\1\2\u01ee\u01ef"+
		"\7\"\2\2\u01ef\u01f5\b\24\1\2\u01f0\u01f1\7#\2\2\u01f1\u01f5\b\24\1\2"+
		"\u01f2\u01f3\7?\2\2\u01f3\u01f5\b\24\1\2\u01f4\u01ea\3\2\2\2\u01f4\u01ec"+
		"\3\2\2\2\u01f4\u01ee\3\2\2\2\u01f4\u01f0\3\2\2\2\u01f4\u01f2\3\2\2\2\u01f5"+
		"\'\3\2\2\2\u01f6\u01f7\7\33\2\2\u01f7\u01fb\b\25\1\2\u01f8\u01f9\7\36"+
		"\2\2\u01f9\u01fb\b\25\1\2\u01fa\u01f6\3\2\2\2\u01fa\u01f8\3\2\2\2\u01fb"+
		")\3\2\2\2\u01fc\u01fd\7\33\2\2\u01fd\u0203\b\26\1\2\u01fe\u01ff\7\34\2"+
		"\2\u01ff\u0203\b\26\1\2\u0200\u0201\7\35\2\2\u0201\u0203\b\26\1\2\u0202"+
		"\u01fc\3\2\2\2\u0202\u01fe\3\2\2\2\u0202\u0200\3\2\2\2\u0203+\3\2\2\2"+
		"\u0204\u0205\7\33\2\2\u0205\u020d\b\27\1\2\u0206\u0207\7\37\2\2\u0207"+
		"\u020d\b\27\1\2\u0208\u0209\7 \2\2\u0209\u020d\b\27\1\2\u020a\u020b\7"+
		"\35\2\2\u020b\u020d\b\27\1\2\u020c\u0204\3\2\2\2\u020c\u0206\3\2\2\2\u020c"+
		"\u0208\3\2\2\2\u020c\u020a\3\2\2\2\u020d-\3\2\2\2\u020e\u020f\7\33\2\2"+
		"\u020f\u021b\b\30\1\2\u0210\u0211\7$\2\2\u0211\u021b\b\30\1\2\u0212\u0213"+
		"\7%\2\2\u0213\u021b\b\30\1\2\u0214\u0215\7&\2\2\u0215\u021b\b\30\1\2\u0216"+
		"\u0217\7\"\2\2\u0217\u021b\b\30\1\2\u0218\u0219\7#\2\2\u0219\u021b\b\30"+
		"\1\2\u021a\u020e\3\2\2\2\u021a\u0210\3\2\2\2\u021a\u0212\3\2\2\2\u021a"+
		"\u0214\3\2\2\2\u021a\u0216\3\2\2\2\u021a\u0218\3\2\2\2\u021b/\3\2\2\2"+
		"\u021c\u021d\7\33\2\2\u021d\u022b\b\31\1\2\u021e\u021f\7\'\2\2\u021f\u022b"+
		"\b\31\1\2\u0220\u0221\7(\2\2\u0221\u022b\b\31\1\2\u0222\u0223\7)\2\2\u0223"+
		"\u022b\b\31\1\2\u0224\u0225\7*\2\2\u0225\u022b\b\31\1\2\u0226\u0227\7"+
		"+\2\2\u0227\u022b\b\31\1\2\u0228\u0229\7?\2\2\u0229\u022b\b\31\1\2\u022a"+
		"\u021c\3\2\2\2\u022a\u021e\3\2\2\2\u022a\u0220\3\2\2\2\u022a\u0222\3\2"+
		"\2\2\u022a\u0224\3\2\2\2\u022a\u0226\3\2\2\2\u022a\u0228\3\2\2\2\u022b"+
		"\61\3\2\2\2\u022c\u022d\7\33\2\2\u022d\u0231\b\32\1\2\u022e\u022f\7\35"+
		"\2\2\u022f\u0231\b\32\1\2\u0230\u022c\3\2\2\2\u0230\u022e\3\2\2\2\u0231"+
		"\63\3\2\2\2\u0232\u0233\7\33\2\2\u0233\u0237\b\33\1\2\u0234\u0235\7,\2"+
		"\2\u0235\u0237\b\33\1\2\u0236\u0232\3\2\2\2\u0236\u0234\3\2\2\2\u0237"+
		"\65\3\2\2\2\u0238\u0239\7\33\2\2\u0239\u023f\b\34\1\2\u023a\u023b\7-\2"+
		"\2\u023b\u023f\b\34\1\2\u023c\u023d\7\35\2\2\u023d\u023f\b\34\1\2\u023e"+
		"\u0238\3\2\2\2\u023e\u023a\3\2\2\2\u023e\u023c\3\2\2\2\u023f\67\3\2\2"+
		"\2\u0240\u0241\7\33\2\2\u0241\u0247\b\35\1\2\u0242\u0243\7\36\2\2\u0243"+
		"\u0247\b\35\1\2\u0244\u0245\7\35\2\2\u0245\u0247\b\35\1\2\u0246\u0240"+
		"\3\2\2\2\u0246\u0242\3\2\2\2\u0246\u0244\3\2\2\2\u02479\3\2\2\2\u0248"+
		"\u0249\7\21\2\2\u0249\u024a\b\36\1\2\u024a;\3\2\2\2\u024b\u024c\7\22\2"+
		"\2\u024c\u024d\b\37\1\2\u024d=\3\2\2\2\u024e\u024f\7\23\2\2\u024f\u0250"+
		"\b \1\2\u0250?\3\2\2\2\u0251\u0252\7\24\2\2\u0252\u0253\b!\1\2\u0253A"+
		"\3\2\2\2\u0254\u0255\7\25\2\2\u0255\u0256\b\"\1\2\u0256C\3\2\2\2\u0257"+
		"\u0258\7\26\2\2\u0258\u0259\b#\1\2\u0259E\3\2\2\2\u025a\u025b\7\27\2\2"+
		"\u025b\u025c\b$\1\2\u025cG\3\2\2\2\u025d\u025e\7\30\2\2\u025e\u025f\b"+
		"%\1\2\u025fI\3\2\2\2\u0260\u0261\7\31\2\2\u0261\u0262\b&\1\2\u0262K\3"+
		"\2\2\2\u0263\u0264\7\32\2\2\u0264\u0265\b\'\1\2\u0265M\3\2\2\2\62W]em"+
		"u}\u0085\u008d\u0095\u009d\u00a5\u00a9\u00b5\u00bd\u00ca\u00d2\u00df\u00e7"+
		"\u00f4\u00fc\u010f\u0117\u0124\u012c\u0139\u0141\u014e\u0156\u0163\u016b"+
		"\u0178\u0180\u0196\u019c\u01b7\u01bb\u01be\u01e8\u01f4\u01fa\u0202\u020c"+
		"\u021a\u022a\u0230\u0236\u023e\u0246";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}