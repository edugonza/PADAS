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
		CASESOF=1, OBJECTSOF=2, EVENTSOF=3, CLASSESOF=4, VERSIONSOF=5, ACTIVITIESOF=6, 
		VERSIONSRELATEDTO=7, RELATIONSOF=8, RELATIONSHIPSOF=9, ACTIVITYINSTANCESOF=10, 
		ATTRIBUTESOF=11, ALLOBJECTS=12, ALLCASES=13, ALLEVENTS=14, ALLCLASSES=15, 
		ALLVERSIONS=16, ALLACTIVITIES=17, ALLRELATIONS=18, ALLRELATIONSHIPS=19, 
		ALLACTIVITYINSTANCES=20, ALLATTRIBUTES=21, ID=22, DATAMODEL_ID=23, NAME=24, 
		CLASS_ID=25, SOURCE=26, TARGET=27, OBJECT_ID=28, START_TIMESTAMP=29, END_TIMESTAMP=30, 
		SOURCE_OBJECT_VERSION_ID=31, TARGET_OBJECT_VERSION_ID=32, RELATIONSHIP_ID=33, 
		ACTIVITY_INSTANCE_ID=34, ORDERING=35, TIMESTAMP=36, LIFECYCLE=37, RESOURCE=38, 
		ACTIVITY_ID=39, PROCESS_ID=40, OPEN_PARENTHESIS=41, CLOSE_PARENTHESIS=42, 
		WHERE=43, EQUAL=44, DIFFERENT=45, EQUAL_OR_GREATER=46, EQUAL_OR_SMALLER=47, 
		GREATER=48, SMALLER=49, CONTAINS=50, AND=51, OR=52, NOT=53, CHANGED=54, 
		FROM=55, TO=56, STRING=57, IDATT=58, IDNOATT=59, WS=60;
	public static final int
		RULE_prog = 0, RULE_things = 1, RULE_objects = 2, RULE_cases = 3, RULE_events = 4, 
		RULE_classes = 5, RULE_versions = 6, RULE_activities = 7, RULE_relations = 8, 
		RULE_relationships = 9, RULE_activityinstances = 10, RULE_attributes = 11, 
		RULE_filter = 12, RULE_filter_expression = 13, RULE_node = 14, RULE_operator = 15, 
		RULE_ids = 16, RULE_id_version = 17, RULE_id_object = 18, RULE_id_class = 19, 
		RULE_id_relationship = 20, RULE_id_relation = 21, RULE_id_event = 22, 
		RULE_id_case = 23, RULE_id_activity_instance = 24, RULE_id_activity = 25, 
		RULE_id_attribute = 26, RULE_allObjects = 27, RULE_allCases = 28, RULE_allEvents = 29, 
		RULE_allClasses = 30, RULE_allVersions = 31, RULE_allActivities = 32, 
		RULE_allRelations = 33, RULE_allRelationships = 34, RULE_allActivityInstances = 35, 
		RULE_allAttributes = 36;
	public static final String[] ruleNames = {
		"prog", "things", "objects", "cases", "events", "classes", "versions", 
		"activities", "relations", "relationships", "activityinstances", "attributes", 
		"filter", "filter_expression", "node", "operator", "ids", "id_version", 
		"id_object", "id_class", "id_relationship", "id_relation", "id_event", 
		"id_case", "id_activity_instance", "id_activity", "id_attribute", "allObjects", 
		"allCases", "allEvents", "allClasses", "allVersions", "allActivities", 
		"allRelations", "allRelationships", "allActivityInstances", "allAttributes"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "CASESOF", "OBJECTSOF", "EVENTSOF", "CLASSESOF", "VERSIONSOF", "ACTIVITIESOF", 
		"VERSIONSRELATEDTO", "RELATIONSOF", "RELATIONSHIPSOF", "ACTIVITYINSTANCESOF", 
		"ATTRIBUTESOF", "ALLOBJECTS", "ALLCASES", "ALLEVENTS", "ALLCLASSES", "ALLVERSIONS", 
		"ALLACTIVITIES", "ALLRELATIONS", "ALLRELATIONSHIPS", "ALLACTIVITYINSTANCES", 
		"ALLATTRIBUTES", "ID", "DATAMODEL_ID", "NAME", "CLASS_ID", "SOURCE", "TARGET", 
		"OBJECT_ID", "START_TIMESTAMP", "END_TIMESTAMP", "SOURCE_OBJECT_VERSION_ID", 
		"TARGET_OBJECT_VERSION_ID", "RELATIONSHIP_ID", "ACTIVITY_INSTANCE_ID", 
		"ORDERING", "TIMESTAMP", "LIFECYCLE", "RESOURCE", "ACTIVITY_ID", "PROCESS_ID", 
		"OPEN_PARENTHESIS", "CLOSE_PARENTHESIS", "WHERE", "EQUAL", "DIFFERENT", 
		"EQUAL_OR_GREATER", "EQUAL_OR_SMALLER", "GREATER", "SMALLER", "CONTAINS", 
		"AND", "OR", "NOT", "CHANGED", "FROM", "TO", "STRING", "IDATT", "IDNOATT", 
		"WS"
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
		public List<Object> result;
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
			setState(74);
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

	public static class ThingsContext extends ParserRuleContext {
		public List<Object> list;
		public Class type;
		public CasesContext t1;
		public ObjectsContext t2;
		public EventsContext t3;
		public ClassesContext t4;
		public VersionsContext t5;
		public ActivitiesContext t6;
		public RelationsContext t7;
		public RelationshipsContext t8;
		public ActivityinstancesContext t9;
		public AttributesContext t10;
		public CasesContext cases() {
			return getRuleContext(CasesContext.class,0);
		}
		public ObjectsContext objects() {
			return getRuleContext(ObjectsContext.class,0);
		}
		public EventsContext events() {
			return getRuleContext(EventsContext.class,0);
		}
		public ClassesContext classes() {
			return getRuleContext(ClassesContext.class,0);
		}
		public VersionsContext versions() {
			return getRuleContext(VersionsContext.class,0);
		}
		public ActivitiesContext activities() {
			return getRuleContext(ActivitiesContext.class,0);
		}
		public RelationsContext relations() {
			return getRuleContext(RelationsContext.class,0);
		}
		public RelationshipsContext relationships() {
			return getRuleContext(RelationshipsContext.class,0);
		}
		public ActivityinstancesContext activityinstances() {
			return getRuleContext(ActivityinstancesContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
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
		enterRule(_localctx, 2, RULE_things);
		try {
			setState(107);
			switch (_input.LA(1)) {
			case CASESOF:
			case ALLCASES:
				enterOuterAlt(_localctx, 1);
				{
				setState(77);
				((ThingsContext)_localctx).t1 = cases(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t1.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t1.type; 
				}
				break;
			case OBJECTSOF:
			case ALLOBJECTS:
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
				((ThingsContext)_localctx).t2 = objects(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t2.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t2.type; 
				}
				break;
			case EVENTSOF:
			case ALLEVENTS:
				enterOuterAlt(_localctx, 3);
				{
				setState(83);
				((ThingsContext)_localctx).t3 = events(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t3.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t3.type; 
				}
				break;
			case CLASSESOF:
			case ALLCLASSES:
				enterOuterAlt(_localctx, 4);
				{
				setState(86);
				((ThingsContext)_localctx).t4 = classes(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t4.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t4.type; 
				}
				break;
			case VERSIONSOF:
			case VERSIONSRELATEDTO:
			case ALLVERSIONS:
				enterOuterAlt(_localctx, 5);
				{
				setState(89);
				((ThingsContext)_localctx).t5 = versions(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t5.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t5.type; 
				}
				break;
			case ACTIVITIESOF:
			case ALLACTIVITIES:
				enterOuterAlt(_localctx, 6);
				{
				setState(92);
				((ThingsContext)_localctx).t6 = activities(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t6.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t6.type; 
				}
				break;
			case RELATIONSOF:
			case ALLRELATIONS:
				enterOuterAlt(_localctx, 7);
				{
				setState(95);
				((ThingsContext)_localctx).t7 = relations(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t7.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t7.type; 
				}
				break;
			case RELATIONSHIPSOF:
			case ALLRELATIONSHIPS:
				enterOuterAlt(_localctx, 8);
				{
				setState(98);
				((ThingsContext)_localctx).t8 = relationships(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t8.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t8.type; 
				}
				break;
			case ACTIVITYINSTANCESOF:
			case ALLACTIVITYINSTANCES:
				enterOuterAlt(_localctx, 9);
				{
				setState(101);
				((ThingsContext)_localctx).t9 = activityinstances(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t9.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t9.type; 
				}
				break;
			case ATTRIBUTESOF:
			case ALLATTRIBUTES:
				enterOuterAlt(_localctx, 10);
				{
				setState(104);
				((ThingsContext)_localctx).t10 = attributes(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t10.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t10.type; 
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
		public List<Object> list;
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
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_objects, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			switch (_input.LA(1)) {
			case OBJECTSOF:
				{
				setState(110);
				match(OBJECTSOF);
				setState(111);
				match(OPEN_PARENTHESIS);
				setState(112);
				((ObjectsContext)_localctx).t1 = things();
				setState(113);
				match(CLOSE_PARENTHESIS);
				 ((ObjectsContext)_localctx).list =  poql.objectsOf(((ObjectsContext)_localctx).t1.list,((ObjectsContext)_localctx).t1.type); ((ObjectsContext)_localctx).type = SLEXMMObject.class; 
				}
				break;
			case ALLOBJECTS:
				{
				setState(116);
				((ObjectsContext)_localctx).t2 = allObjects();
				 ((ObjectsContext)_localctx).list =  ((ObjectsContext)_localctx).t2.list; ((ObjectsContext)_localctx).type =  ((ObjectsContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(127);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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
					setState(121);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(122);
					((ObjectsContext)_localctx).f = filter(ID_TYPE_OBJECT);
					 ((ObjectsContext)_localctx).list =  poql.filter(((ObjectsContext)_localctx).t3.list,((ObjectsContext)_localctx).t3.type,((ObjectsContext)_localctx).f.conditions); ((ObjectsContext)_localctx).type =  ((ObjectsContext)_localctx).t3.type; 
					}
					} 
				}
				setState(129);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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
		public List<Object> list;
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
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_cases, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			switch (_input.LA(1)) {
			case CASESOF:
				{
				setState(131);
				match(CASESOF);
				setState(132);
				match(OPEN_PARENTHESIS);
				setState(133);
				((CasesContext)_localctx).t1 = things();
				setState(134);
				match(CLOSE_PARENTHESIS);
				 ((CasesContext)_localctx).list =  poql.casesOf(((CasesContext)_localctx).t1.list,((CasesContext)_localctx).t1.type); ((CasesContext)_localctx).type = SLEXMMCase.class; 
				}
				break;
			case ALLCASES:
				{
				setState(137);
				((CasesContext)_localctx).t2 = allCases();
				 ((CasesContext)_localctx).list =  ((CasesContext)_localctx).t2.list; ((CasesContext)_localctx).type =  ((CasesContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(148);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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
					setState(142);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(143);
					((CasesContext)_localctx).f = filter(ID_TYPE_CASE);
					 ((CasesContext)_localctx).list =  poql.filter(((CasesContext)_localctx).t3.list,((CasesContext)_localctx).t3.type,((CasesContext)_localctx).f.conditions); ((CasesContext)_localctx).type =  ((CasesContext)_localctx).t3.type; 
					}
					} 
				}
				setState(150);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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
		public List<Object> list;
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
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_events, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			switch (_input.LA(1)) {
			case EVENTSOF:
				{
				setState(152);
				match(EVENTSOF);
				setState(153);
				match(OPEN_PARENTHESIS);
				setState(154);
				((EventsContext)_localctx).t1 = things();
				setState(155);
				match(CLOSE_PARENTHESIS);
				 ((EventsContext)_localctx).list =  poql.eventsOf(((EventsContext)_localctx).t1.list,((EventsContext)_localctx).t1.type); ((EventsContext)_localctx).type = SLEXMMEvent.class;
				}
				break;
			case ALLEVENTS:
				{
				setState(158);
				((EventsContext)_localctx).t2 = allEvents();
				 ((EventsContext)_localctx).list =  ((EventsContext)_localctx).t2.list; ((EventsContext)_localctx).type =  ((EventsContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(169);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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
					setState(163);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(164);
					((EventsContext)_localctx).f = filter(ID_TYPE_EVENT);
					 ((EventsContext)_localctx).list =  poql.filter(((EventsContext)_localctx).t3.list,((EventsContext)_localctx).t3.type,((EventsContext)_localctx).f.conditions); ((EventsContext)_localctx).type =  ((EventsContext)_localctx).t3.type; 
					}
					} 
				}
				setState(171);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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
		public List<Object> list;
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
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_classes, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			switch (_input.LA(1)) {
			case CLASSESOF:
				{
				setState(173);
				match(CLASSESOF);
				setState(174);
				match(OPEN_PARENTHESIS);
				setState(175);
				((ClassesContext)_localctx).t1 = things();
				setState(176);
				match(CLOSE_PARENTHESIS);
				 ((ClassesContext)_localctx).list =  poql.classesOf(((ClassesContext)_localctx).t1.list,((ClassesContext)_localctx).t1.type); ((ClassesContext)_localctx).type = SLEXMMClass.class;
				}
				break;
			case ALLCLASSES:
				{
				setState(179);
				((ClassesContext)_localctx).t2 = allClasses();
				 ((ClassesContext)_localctx).list =  ((ClassesContext)_localctx).t2.list; ((ClassesContext)_localctx).type =  ((ClassesContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(190);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
					setState(184);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(185);
					((ClassesContext)_localctx).f = filter(ID_TYPE_CLASS);
					 ((ClassesContext)_localctx).list =  poql.filter(((ClassesContext)_localctx).t3.list,((ClassesContext)_localctx).t3.type,((ClassesContext)_localctx).f.conditions); ((ClassesContext)_localctx).type =  ((ClassesContext)_localctx).t3.type; 
					}
					} 
				}
				setState(192);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
		public List<Object> list;
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
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_versions, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			switch (_input.LA(1)) {
			case VERSIONSOF:
				{
				setState(194);
				match(VERSIONSOF);
				setState(195);
				match(OPEN_PARENTHESIS);
				setState(196);
				((VersionsContext)_localctx).t1 = things();
				setState(197);
				match(CLOSE_PARENTHESIS);
				 ((VersionsContext)_localctx).list =  poql.versionsOf(((VersionsContext)_localctx).t1.list,((VersionsContext)_localctx).t1.type); ((VersionsContext)_localctx).type = SLEXMMObjectVersion.class;
				}
				break;
			case ALLVERSIONS:
				{
				setState(200);
				((VersionsContext)_localctx).t2 = allVersions();
				 ((VersionsContext)_localctx).list =  ((VersionsContext)_localctx).t2.list; ((VersionsContext)_localctx).type =  ((VersionsContext)_localctx).t2.type; 
				}
				break;
			case VERSIONSRELATEDTO:
				{
				setState(203);
				match(VERSIONSRELATEDTO);
				setState(204);
				match(OPEN_PARENTHESIS);
				setState(205);
				((VersionsContext)_localctx).t4 = versions(0);
				setState(206);
				match(CLOSE_PARENTHESIS);
				 ((VersionsContext)_localctx).list =  poql.versionsRelatedTo(((VersionsContext)_localctx).t4.list,((VersionsContext)_localctx).t4.type); ((VersionsContext)_localctx).type = SLEXMMObjectVersion.class; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(217);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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
					setState(211);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(212);
					((VersionsContext)_localctx).f = filter(ID_TYPE_VERSION);
					 ((VersionsContext)_localctx).list =  poql.filter(((VersionsContext)_localctx).t3.list,((VersionsContext)_localctx).t3.type,((VersionsContext)_localctx).f.conditions); ((VersionsContext)_localctx).type =  ((VersionsContext)_localctx).t3.type; 
					}
					} 
				}
				setState(219);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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
		public List<Object> list;
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
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_activities, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			switch (_input.LA(1)) {
			case ACTIVITIESOF:
				{
				setState(221);
				match(ACTIVITIESOF);
				setState(222);
				match(OPEN_PARENTHESIS);
				setState(223);
				((ActivitiesContext)_localctx).t1 = things();
				setState(224);
				match(CLOSE_PARENTHESIS);
				 ((ActivitiesContext)_localctx).list =  poql.activitiesOf(((ActivitiesContext)_localctx).t1.list,((ActivitiesContext)_localctx).t1.type); ((ActivitiesContext)_localctx).type = SLEXMMActivity.class;
				}
				break;
			case ALLACTIVITIES:
				{
				setState(227);
				((ActivitiesContext)_localctx).t2 = allActivities();
				 ((ActivitiesContext)_localctx).list =  ((ActivitiesContext)_localctx).t2.list; ((ActivitiesContext)_localctx).type =  ((ActivitiesContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(238);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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
					setState(232);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(233);
					((ActivitiesContext)_localctx).f = filter(ID_TYPE_ACTIVITY);
					 ((ActivitiesContext)_localctx).list =  poql.filter(((ActivitiesContext)_localctx).t3.list,((ActivitiesContext)_localctx).t3.type,((ActivitiesContext)_localctx).f.conditions); ((ActivitiesContext)_localctx).type =  ((ActivitiesContext)_localctx).t3.type; 
					}
					} 
				}
				setState(240);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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
		public List<Object> list;
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
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_relations, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			switch (_input.LA(1)) {
			case RELATIONSOF:
				{
				setState(242);
				match(RELATIONSOF);
				setState(243);
				match(OPEN_PARENTHESIS);
				setState(244);
				((RelationsContext)_localctx).t1 = things();
				setState(245);
				match(CLOSE_PARENTHESIS);
				 ((RelationsContext)_localctx).list =  poql.relationsOf(((RelationsContext)_localctx).t1.list,((RelationsContext)_localctx).t1.type); ((RelationsContext)_localctx).type = SLEXMMRelation.class;
				}
				break;
			case ALLRELATIONS:
				{
				setState(248);
				((RelationsContext)_localctx).t2 = allRelations();
				 ((RelationsContext)_localctx).list =  ((RelationsContext)_localctx).t2.list; ((RelationsContext)_localctx).type =  ((RelationsContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(259);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
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
					setState(253);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(254);
					((RelationsContext)_localctx).f = filter(ID_TYPE_RELATION);
					 ((RelationsContext)_localctx).list =  poql.filter(((RelationsContext)_localctx).t3.list,((RelationsContext)_localctx).t3.type,((RelationsContext)_localctx).f.conditions); ((RelationsContext)_localctx).type =  ((RelationsContext)_localctx).t3.type; 
					}
					} 
				}
				setState(261);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
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
		public List<Object> list;
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
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_relationships, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			switch (_input.LA(1)) {
			case RELATIONSHIPSOF:
				{
				setState(263);
				match(RELATIONSHIPSOF);
				setState(264);
				match(OPEN_PARENTHESIS);
				setState(265);
				((RelationshipsContext)_localctx).t1 = things();
				setState(266);
				match(CLOSE_PARENTHESIS);
				 ((RelationshipsContext)_localctx).list =  poql.relationshipsOf(((RelationshipsContext)_localctx).t1.list,((RelationshipsContext)_localctx).t1.type); ((RelationshipsContext)_localctx).type = SLEXMMRelationship.class;
				}
				break;
			case ALLRELATIONSHIPS:
				{
				setState(269);
				((RelationshipsContext)_localctx).t2 = allRelationships();
				 ((RelationshipsContext)_localctx).list =  ((RelationshipsContext)_localctx).t2.list; ((RelationshipsContext)_localctx).type =  ((RelationshipsContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(280);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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
					setState(274);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(275);
					((RelationshipsContext)_localctx).f = filter(ID_TYPE_RELATIONSHIP);
					 ((RelationshipsContext)_localctx).list =  poql.filter(((RelationshipsContext)_localctx).t3.list,((RelationshipsContext)_localctx).t3.type,((RelationshipsContext)_localctx).f.conditions); ((RelationshipsContext)_localctx).type =  ((RelationshipsContext)_localctx).t3.type; 
					}
					} 
				}
				setState(282);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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
		public List<Object> list;
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
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_activityinstances, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			switch (_input.LA(1)) {
			case ACTIVITYINSTANCESOF:
				{
				setState(284);
				match(ACTIVITYINSTANCESOF);
				setState(285);
				match(OPEN_PARENTHESIS);
				setState(286);
				((ActivityinstancesContext)_localctx).t1 = things();
				setState(287);
				match(CLOSE_PARENTHESIS);
				 ((ActivityinstancesContext)_localctx).list =  poql.activityInstancesOf(((ActivityinstancesContext)_localctx).t1.list,((ActivityinstancesContext)_localctx).t1.type); ((ActivityinstancesContext)_localctx).type = SLEXMMActivityInstance.class;
				}
				break;
			case ALLACTIVITYINSTANCES:
				{
				setState(290);
				((ActivityinstancesContext)_localctx).t2 = allActivityInstances();
				 ((ActivityinstancesContext)_localctx).list =  ((ActivityinstancesContext)_localctx).t2.list; ((ActivityinstancesContext)_localctx).type =  ((ActivityinstancesContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(301);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
					setState(295);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(296);
					((ActivityinstancesContext)_localctx).f = filter(ID_TYPE_ACTIVITY_INSTANCE);
					 ((ActivityinstancesContext)_localctx).list =  poql.filter(((ActivityinstancesContext)_localctx).t3.list,((ActivityinstancesContext)_localctx).t3.type,((ActivityinstancesContext)_localctx).f.conditions); ((ActivityinstancesContext)_localctx).type =  ((ActivityinstancesContext)_localctx).t3.type; 
					}
					} 
				}
				setState(303);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
		public List<Object> list;
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
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_attributes, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
			switch (_input.LA(1)) {
			case ATTRIBUTESOF:
				{
				setState(305);
				match(ATTRIBUTESOF);
				setState(306);
				match(OPEN_PARENTHESIS);
				setState(307);
				((AttributesContext)_localctx).t1 = things();
				setState(308);
				match(CLOSE_PARENTHESIS);
				 ((AttributesContext)_localctx).list =  poql.attributesOf(((AttributesContext)_localctx).t1.list,((AttributesContext)_localctx).t1.type); ((AttributesContext)_localctx).type = SLEXMMAttribute.class;
				}
				break;
			case ALLATTRIBUTES:
				{
				setState(311);
				((AttributesContext)_localctx).t2 = allAttributes();
				 ((AttributesContext)_localctx).list =  ((AttributesContext)_localctx).t2.list; ((AttributesContext)_localctx).type =  ((AttributesContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(322);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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
					setState(316);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(317);
					((AttributesContext)_localctx).f = filter(ID_TYPE_ATTRIBUTE);
					 ((AttributesContext)_localctx).list =  poql.filter(((AttributesContext)_localctx).t3.list,((AttributesContext)_localctx).t3.type,((AttributesContext)_localctx).f.conditions); ((AttributesContext)_localctx).type =  ((AttributesContext)_localctx).t3.type; 
					}
					} 
				}
				setState(324);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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
		enterRule(_localctx, 24, RULE_filter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			match(WHERE);
			setState(326);
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
		enterRule(_localctx, 26, RULE_filter_expression);
		try {
			setState(344);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(329);
				match(NOT);
				setState(330);
				((Filter_expressionContext)_localctx).f0 = filter_expression(_localctx.type_id);
				 ((Filter_expressionContext)_localctx).tree =  poql.createNotNode(((Filter_expressionContext)_localctx).f0.tree); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(333);
				match(OPEN_PARENTHESIS);
				setState(334);
				((Filter_expressionContext)_localctx).f1 = filter_expression(_localctx.type_id);
				setState(335);
				((Filter_expressionContext)_localctx).node = node();
				setState(336);
				((Filter_expressionContext)_localctx).f2 = filter_expression(_localctx.type_id);
				setState(337);
				match(CLOSE_PARENTHESIS);
				 ((Filter_expressionContext)_localctx).tree =  poql.createNode(((Filter_expressionContext)_localctx).f1.tree,((Filter_expressionContext)_localctx).f2.tree,((Filter_expressionContext)_localctx).node.node_id); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(340);
				((Filter_expressionContext)_localctx).ids = ids(_localctx.type_id);
				setState(341);
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
		enterRule(_localctx, 28, RULE_node);
		try {
			setState(350);
			switch (_input.LA(1)) {
			case AND:
				enterOuterAlt(_localctx, 1);
				{
				setState(346);
				match(AND);
				((NodeContext)_localctx).node_id =  FilterTree.NODE_AND; 
				}
				break;
			case OR:
				enterOuterAlt(_localctx, 2);
				{
				setState(348);
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
		enterRule(_localctx, 30, RULE_operator);
		try {
			setState(384);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(352);
				match(EQUAL);
				setState(353);
				((OperatorContext)_localctx).STRING = match(STRING);
				((OperatorContext)_localctx).operator_id =  FilterTree.OPERATOR_EQUAL; ((OperatorContext)_localctx).value =  (((OperatorContext)_localctx).STRING!=null?((OperatorContext)_localctx).STRING.getText():null); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(355);
				match(DIFFERENT);
				setState(356);
				((OperatorContext)_localctx).STRING = match(STRING);
				((OperatorContext)_localctx).operator_id =  FilterTree.OPERATOR_DIFFERENT; ((OperatorContext)_localctx).value =  (((OperatorContext)_localctx).STRING!=null?((OperatorContext)_localctx).STRING.getText():null); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(358);
				match(EQUAL_OR_GREATER);
				setState(359);
				((OperatorContext)_localctx).STRING = match(STRING);
				((OperatorContext)_localctx).operator_id =  FilterTree.OPERATOR_EQUAL_OR_GREATER_THAN; ((OperatorContext)_localctx).value =  (((OperatorContext)_localctx).STRING!=null?((OperatorContext)_localctx).STRING.getText():null); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(361);
				match(EQUAL_OR_SMALLER);
				setState(362);
				((OperatorContext)_localctx).STRING = match(STRING);
				((OperatorContext)_localctx).operator_id =  FilterTree.OPERATOR_EQUAL_OR_SMALLER_THAN; ((OperatorContext)_localctx).value =  (((OperatorContext)_localctx).STRING!=null?((OperatorContext)_localctx).STRING.getText():null); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(364);
				match(GREATER);
				setState(365);
				((OperatorContext)_localctx).STRING = match(STRING);
				((OperatorContext)_localctx).operator_id =  FilterTree.OPERATOR_GREATER_THAN; ((OperatorContext)_localctx).value =  (((OperatorContext)_localctx).STRING!=null?((OperatorContext)_localctx).STRING.getText():null); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(367);
				match(SMALLER);
				setState(368);
				((OperatorContext)_localctx).STRING = match(STRING);
				((OperatorContext)_localctx).operator_id =  FilterTree.OPERATOR_SMALLER_THAN; ((OperatorContext)_localctx).value =  (((OperatorContext)_localctx).STRING!=null?((OperatorContext)_localctx).STRING.getText():null); 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(370);
				match(CONTAINS);
				setState(371);
				((OperatorContext)_localctx).STRING = match(STRING);
				((OperatorContext)_localctx).operator_id =  FilterTree.OPERATOR_CONTAINS; ((OperatorContext)_localctx).value =  (((OperatorContext)_localctx).STRING!=null?((OperatorContext)_localctx).STRING.getText():null); 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(373);
				if (!(_localctx.type_id == ID_TYPE_VERSION && _localctx.att)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_VERSION && $att");
				setState(374);
				match(CHANGED);
				setState(377);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(375);
					match(FROM);
					setState(376);
					((OperatorContext)_localctx).f13 = match(STRING);
					}
					break;
				}
				setState(381);
				switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					setState(379);
					match(TO);
					setState(380);
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
		enterRule(_localctx, 32, RULE_ids);
		try {
			setState(426);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(386);
				if (!(_localctx.type_id == ID_TYPE_VERSION)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_VERSION");
				setState(387);
				((IdsContext)_localctx).i1 = id_version();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i1.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i1.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i1.id;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(390);
				if (!(_localctx.type_id == ID_TYPE_OBJECT)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_OBJECT");
				setState(391);
				((IdsContext)_localctx).i2 = id_object();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i2.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i2.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i2.id;
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(394);
				if (!(_localctx.type_id == ID_TYPE_CLASS)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_CLASS");
				setState(395);
				((IdsContext)_localctx).i3 = id_class();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i3.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i3.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i3.id;
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(398);
				if (!(_localctx.type_id == ID_TYPE_RELATIONSHIP)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_RELATIONSHIP");
				setState(399);
				((IdsContext)_localctx).i4 = id_relationship();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i4.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i4.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i4.id;
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(402);
				if (!(_localctx.type_id == ID_TYPE_RELATION)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_RELATION");
				setState(403);
				((IdsContext)_localctx).i5 = id_relation();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i5.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i5.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i5.id;
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(406);
				if (!(_localctx.type_id == ID_TYPE_EVENT)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_EVENT");
				setState(407);
				((IdsContext)_localctx).i6 = id_event();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i6.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i6.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i6.id;
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(410);
				if (!(_localctx.type_id == ID_TYPE_CASE)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_CASE");
				setState(411);
				((IdsContext)_localctx).i7 = id_case();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i7.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i7.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i7.id;
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(414);
				if (!(_localctx.type_id == ID_TYPE_ACTIVITY_INSTANCE)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_ACTIVITY_INSTANCE");
				setState(415);
				((IdsContext)_localctx).i8 = id_activity_instance();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i8.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i8.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i8.id;
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(418);
				if (!(_localctx.type_id == ID_TYPE_ACTIVITY)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_ACTIVITY");
				setState(419);
				((IdsContext)_localctx).i9 = id_activity();
				((IdsContext)_localctx).name =  ((IdsContext)_localctx).i9.name; ((IdsContext)_localctx).att =  ((IdsContext)_localctx).i9.att; ((IdsContext)_localctx).id =  ((IdsContext)_localctx).i9.id;
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(422);
				if (!(_localctx.type_id == ID_TYPE_ATTRIBUTE)) throw new FailedPredicateException(this, "$type_id == ID_TYPE_ATTRIBUTE");
				setState(423);
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
		enterRule(_localctx, 34, RULE_id_version);
		try {
			setState(438);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(428);
				((Id_versionContext)_localctx).ID = match(ID);
				((Id_versionContext)_localctx).name =  (((Id_versionContext)_localctx).ID!=null?((Id_versionContext)_localctx).ID.getText():null); ((Id_versionContext)_localctx).att =  false; ((Id_versionContext)_localctx).id =  (((Id_versionContext)_localctx).ID!=null?((Id_versionContext)_localctx).ID.getType():0);
				}
				break;
			case OBJECT_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(430);
				((Id_versionContext)_localctx).OBJECT_ID = match(OBJECT_ID);
				((Id_versionContext)_localctx).name =  (((Id_versionContext)_localctx).OBJECT_ID!=null?((Id_versionContext)_localctx).OBJECT_ID.getText():null); ((Id_versionContext)_localctx).att =  false; ((Id_versionContext)_localctx).id =  (((Id_versionContext)_localctx).OBJECT_ID!=null?((Id_versionContext)_localctx).OBJECT_ID.getType():0);
				}
				break;
			case START_TIMESTAMP:
				enterOuterAlt(_localctx, 3);
				{
				setState(432);
				((Id_versionContext)_localctx).START_TIMESTAMP = match(START_TIMESTAMP);
				((Id_versionContext)_localctx).name =  (((Id_versionContext)_localctx).START_TIMESTAMP!=null?((Id_versionContext)_localctx).START_TIMESTAMP.getText():null); ((Id_versionContext)_localctx).att =  false; ((Id_versionContext)_localctx).id =  (((Id_versionContext)_localctx).START_TIMESTAMP!=null?((Id_versionContext)_localctx).START_TIMESTAMP.getType():0);
				}
				break;
			case END_TIMESTAMP:
				enterOuterAlt(_localctx, 4);
				{
				setState(434);
				((Id_versionContext)_localctx).END_TIMESTAMP = match(END_TIMESTAMP);
				((Id_versionContext)_localctx).name =  (((Id_versionContext)_localctx).END_TIMESTAMP!=null?((Id_versionContext)_localctx).END_TIMESTAMP.getText():null); ((Id_versionContext)_localctx).att =  false; ((Id_versionContext)_localctx).id =  (((Id_versionContext)_localctx).END_TIMESTAMP!=null?((Id_versionContext)_localctx).END_TIMESTAMP.getType():0);
				}
				break;
			case IDATT:
				enterOuterAlt(_localctx, 5);
				{
				setState(436);
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
		enterRule(_localctx, 36, RULE_id_object);
		try {
			setState(444);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(440);
				((Id_objectContext)_localctx).ID = match(ID);
				((Id_objectContext)_localctx).name =  (((Id_objectContext)_localctx).ID!=null?((Id_objectContext)_localctx).ID.getText():null); ((Id_objectContext)_localctx).att =  false; ((Id_objectContext)_localctx).id =  (((Id_objectContext)_localctx).ID!=null?((Id_objectContext)_localctx).ID.getType():0);
				}
				break;
			case CLASS_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(442);
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
		enterRule(_localctx, 38, RULE_id_class);
		try {
			setState(452);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(446);
				((Id_classContext)_localctx).ID = match(ID);
				((Id_classContext)_localctx).name =  (((Id_classContext)_localctx).ID!=null?((Id_classContext)_localctx).ID.getText():null); ((Id_classContext)_localctx).att =  false; ((Id_classContext)_localctx).id =  (((Id_classContext)_localctx).ID!=null?((Id_classContext)_localctx).ID.getType():0);
				}
				break;
			case DATAMODEL_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(448);
				((Id_classContext)_localctx).DATAMODEL_ID = match(DATAMODEL_ID);
				((Id_classContext)_localctx).name =  (((Id_classContext)_localctx).DATAMODEL_ID!=null?((Id_classContext)_localctx).DATAMODEL_ID.getText():null); ((Id_classContext)_localctx).att =  false; ((Id_classContext)_localctx).id =  (((Id_classContext)_localctx).DATAMODEL_ID!=null?((Id_classContext)_localctx).DATAMODEL_ID.getType():0);
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 3);
				{
				setState(450);
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
		enterRule(_localctx, 40, RULE_id_relationship);
		try {
			setState(462);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(454);
				((Id_relationshipContext)_localctx).ID = match(ID);
				((Id_relationshipContext)_localctx).name =  (((Id_relationshipContext)_localctx).ID!=null?((Id_relationshipContext)_localctx).ID.getText():null); ((Id_relationshipContext)_localctx).att =  false; ((Id_relationshipContext)_localctx).id =  (((Id_relationshipContext)_localctx).ID!=null?((Id_relationshipContext)_localctx).ID.getType():0);
				}
				break;
			case SOURCE:
				enterOuterAlt(_localctx, 2);
				{
				setState(456);
				((Id_relationshipContext)_localctx).SOURCE = match(SOURCE);
				((Id_relationshipContext)_localctx).name =  (((Id_relationshipContext)_localctx).SOURCE!=null?((Id_relationshipContext)_localctx).SOURCE.getText():null); ((Id_relationshipContext)_localctx).att =  false; ((Id_relationshipContext)_localctx).id =  (((Id_relationshipContext)_localctx).SOURCE!=null?((Id_relationshipContext)_localctx).SOURCE.getType():0);
				}
				break;
			case TARGET:
				enterOuterAlt(_localctx, 3);
				{
				setState(458);
				((Id_relationshipContext)_localctx).TARGET = match(TARGET);
				((Id_relationshipContext)_localctx).name =  (((Id_relationshipContext)_localctx).TARGET!=null?((Id_relationshipContext)_localctx).TARGET.getText():null); ((Id_relationshipContext)_localctx).att =  false; ((Id_relationshipContext)_localctx).id =  (((Id_relationshipContext)_localctx).TARGET!=null?((Id_relationshipContext)_localctx).TARGET.getType():0);
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 4);
				{
				setState(460);
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
		enterRule(_localctx, 42, RULE_id_relation);
		try {
			setState(476);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(464);
				((Id_relationContext)_localctx).ID = match(ID);
				((Id_relationContext)_localctx).name =  (((Id_relationContext)_localctx).ID!=null?((Id_relationContext)_localctx).ID.getText():null); ((Id_relationContext)_localctx).att =  false; ((Id_relationContext)_localctx).id =  (((Id_relationContext)_localctx).ID!=null?((Id_relationContext)_localctx).ID.getType():0);
				}
				break;
			case SOURCE_OBJECT_VERSION_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(466);
				((Id_relationContext)_localctx).SOURCE_OBJECT_VERSION_ID = match(SOURCE_OBJECT_VERSION_ID);
				((Id_relationContext)_localctx).name =  (((Id_relationContext)_localctx).SOURCE_OBJECT_VERSION_ID!=null?((Id_relationContext)_localctx).SOURCE_OBJECT_VERSION_ID.getText():null); ((Id_relationContext)_localctx).att =  false; ((Id_relationContext)_localctx).id =  (((Id_relationContext)_localctx).SOURCE_OBJECT_VERSION_ID!=null?((Id_relationContext)_localctx).SOURCE_OBJECT_VERSION_ID.getType():0);
				}
				break;
			case TARGET_OBJECT_VERSION_ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(468);
				((Id_relationContext)_localctx).TARGET_OBJECT_VERSION_ID = match(TARGET_OBJECT_VERSION_ID);
				((Id_relationContext)_localctx).name =  (((Id_relationContext)_localctx).TARGET_OBJECT_VERSION_ID!=null?((Id_relationContext)_localctx).TARGET_OBJECT_VERSION_ID.getText():null); ((Id_relationContext)_localctx).att =  false; ((Id_relationContext)_localctx).id =  (((Id_relationContext)_localctx).TARGET_OBJECT_VERSION_ID!=null?((Id_relationContext)_localctx).TARGET_OBJECT_VERSION_ID.getType():0);
				}
				break;
			case RELATIONSHIP_ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(470);
				((Id_relationContext)_localctx).RELATIONSHIP_ID = match(RELATIONSHIP_ID);
				((Id_relationContext)_localctx).name =  (((Id_relationContext)_localctx).RELATIONSHIP_ID!=null?((Id_relationContext)_localctx).RELATIONSHIP_ID.getText():null); ((Id_relationContext)_localctx).att =  false; ((Id_relationContext)_localctx).id =  (((Id_relationContext)_localctx).RELATIONSHIP_ID!=null?((Id_relationContext)_localctx).RELATIONSHIP_ID.getType():0);
				}
				break;
			case START_TIMESTAMP:
				enterOuterAlt(_localctx, 5);
				{
				setState(472);
				((Id_relationContext)_localctx).START_TIMESTAMP = match(START_TIMESTAMP);
				((Id_relationContext)_localctx).name =  (((Id_relationContext)_localctx).START_TIMESTAMP!=null?((Id_relationContext)_localctx).START_TIMESTAMP.getText():null); ((Id_relationContext)_localctx).att =  false; ((Id_relationContext)_localctx).id =  (((Id_relationContext)_localctx).START_TIMESTAMP!=null?((Id_relationContext)_localctx).START_TIMESTAMP.getType():0);
				}
				break;
			case END_TIMESTAMP:
				enterOuterAlt(_localctx, 6);
				{
				setState(474);
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
		enterRule(_localctx, 44, RULE_id_event);
		try {
			setState(492);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(478);
				((Id_eventContext)_localctx).ID = match(ID);
				((Id_eventContext)_localctx).name =  (((Id_eventContext)_localctx).ID!=null?((Id_eventContext)_localctx).ID.getText():null); ((Id_eventContext)_localctx).att =  false; ((Id_eventContext)_localctx).id =  (((Id_eventContext)_localctx).ID!=null?((Id_eventContext)_localctx).ID.getType():0);
				}
				break;
			case ACTIVITY_INSTANCE_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(480);
				((Id_eventContext)_localctx).ACTIVITY_INSTANCE_ID = match(ACTIVITY_INSTANCE_ID);
				((Id_eventContext)_localctx).name =  (((Id_eventContext)_localctx).ACTIVITY_INSTANCE_ID!=null?((Id_eventContext)_localctx).ACTIVITY_INSTANCE_ID.getText():null); ((Id_eventContext)_localctx).att =  false; ((Id_eventContext)_localctx).id =  (((Id_eventContext)_localctx).ACTIVITY_INSTANCE_ID!=null?((Id_eventContext)_localctx).ACTIVITY_INSTANCE_ID.getType():0);
				}
				break;
			case ORDERING:
				enterOuterAlt(_localctx, 3);
				{
				setState(482);
				((Id_eventContext)_localctx).ORDERING = match(ORDERING);
				((Id_eventContext)_localctx).name =  (((Id_eventContext)_localctx).ORDERING!=null?((Id_eventContext)_localctx).ORDERING.getText():null); ((Id_eventContext)_localctx).att =  false; ((Id_eventContext)_localctx).id =  (((Id_eventContext)_localctx).ORDERING!=null?((Id_eventContext)_localctx).ORDERING.getType():0);
				}
				break;
			case TIMESTAMP:
				enterOuterAlt(_localctx, 4);
				{
				setState(484);
				((Id_eventContext)_localctx).TIMESTAMP = match(TIMESTAMP);
				((Id_eventContext)_localctx).name =  (((Id_eventContext)_localctx).TIMESTAMP!=null?((Id_eventContext)_localctx).TIMESTAMP.getText():null); ((Id_eventContext)_localctx).att =  false; ((Id_eventContext)_localctx).id =  (((Id_eventContext)_localctx).TIMESTAMP!=null?((Id_eventContext)_localctx).TIMESTAMP.getType():0);
				}
				break;
			case LIFECYCLE:
				enterOuterAlt(_localctx, 5);
				{
				setState(486);
				((Id_eventContext)_localctx).LIFECYCLE = match(LIFECYCLE);
				((Id_eventContext)_localctx).name =  (((Id_eventContext)_localctx).LIFECYCLE!=null?((Id_eventContext)_localctx).LIFECYCLE.getText():null); ((Id_eventContext)_localctx).att =  false; ((Id_eventContext)_localctx).id =  (((Id_eventContext)_localctx).LIFECYCLE!=null?((Id_eventContext)_localctx).LIFECYCLE.getType():0);
				}
				break;
			case RESOURCE:
				enterOuterAlt(_localctx, 6);
				{
				setState(488);
				((Id_eventContext)_localctx).RESOURCE = match(RESOURCE);
				((Id_eventContext)_localctx).name =  (((Id_eventContext)_localctx).RESOURCE!=null?((Id_eventContext)_localctx).RESOURCE.getText():null); ((Id_eventContext)_localctx).att =  false; ((Id_eventContext)_localctx).id =  (((Id_eventContext)_localctx).RESOURCE!=null?((Id_eventContext)_localctx).RESOURCE.getType():0);
				}
				break;
			case IDATT:
				enterOuterAlt(_localctx, 7);
				{
				setState(490);
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
		enterRule(_localctx, 46, RULE_id_case);
		try {
			setState(498);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(494);
				((Id_caseContext)_localctx).ID = match(ID);
				((Id_caseContext)_localctx).name =  (((Id_caseContext)_localctx).ID!=null?((Id_caseContext)_localctx).ID.getText():null); ((Id_caseContext)_localctx).att =  false; ((Id_caseContext)_localctx).id =  (((Id_caseContext)_localctx).ID!=null?((Id_caseContext)_localctx).ID.getType():0);
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(496);
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
		enterRule(_localctx, 48, RULE_id_activity_instance);
		try {
			setState(504);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(500);
				((Id_activity_instanceContext)_localctx).ID = match(ID);
				((Id_activity_instanceContext)_localctx).name =  (((Id_activity_instanceContext)_localctx).ID!=null?((Id_activity_instanceContext)_localctx).ID.getText():null); ((Id_activity_instanceContext)_localctx).att =  false; ((Id_activity_instanceContext)_localctx).id =  (((Id_activity_instanceContext)_localctx).ID!=null?((Id_activity_instanceContext)_localctx).ID.getType():0);
				}
				break;
			case ACTIVITY_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(502);
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
		enterRule(_localctx, 50, RULE_id_activity);
		try {
			setState(512);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(506);
				((Id_activityContext)_localctx).ID = match(ID);
				((Id_activityContext)_localctx).name =  (((Id_activityContext)_localctx).ID!=null?((Id_activityContext)_localctx).ID.getText():null); ((Id_activityContext)_localctx).att =  false; ((Id_activityContext)_localctx).id =  (((Id_activityContext)_localctx).ID!=null?((Id_activityContext)_localctx).ID.getType():0); 
				}
				break;
			case PROCESS_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(508);
				((Id_activityContext)_localctx).PROCESS_ID = match(PROCESS_ID);
				((Id_activityContext)_localctx).name =  (((Id_activityContext)_localctx).PROCESS_ID!=null?((Id_activityContext)_localctx).PROCESS_ID.getText():null); ((Id_activityContext)_localctx).att =  false; ((Id_activityContext)_localctx).id =  (((Id_activityContext)_localctx).PROCESS_ID!=null?((Id_activityContext)_localctx).PROCESS_ID.getType():0); 
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 3);
				{
				setState(510);
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
		enterRule(_localctx, 52, RULE_id_attribute);
		try {
			setState(520);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(514);
				((Id_attributeContext)_localctx).ID = match(ID);
				((Id_attributeContext)_localctx).name =  (((Id_attributeContext)_localctx).ID!=null?((Id_attributeContext)_localctx).ID.getText():null); ((Id_attributeContext)_localctx).att =  false; ((Id_attributeContext)_localctx).id =  (((Id_attributeContext)_localctx).ID!=null?((Id_attributeContext)_localctx).ID.getType():0); 
				}
				break;
			case CLASS_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(516);
				((Id_attributeContext)_localctx).CLASS_ID = match(CLASS_ID);
				((Id_attributeContext)_localctx).name =  (((Id_attributeContext)_localctx).CLASS_ID!=null?((Id_attributeContext)_localctx).CLASS_ID.getText():null); ((Id_attributeContext)_localctx).att =  false; ((Id_attributeContext)_localctx).id =  (((Id_attributeContext)_localctx).CLASS_ID!=null?((Id_attributeContext)_localctx).CLASS_ID.getType():0); 
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 3);
				{
				setState(518);
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
		public List<Object> list;
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
		enterRule(_localctx, 54, RULE_allObjects);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(522);
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
		public List<Object> list;
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
		enterRule(_localctx, 56, RULE_allCases);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(525);
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
		public List<Object> list;
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
		enterRule(_localctx, 58, RULE_allEvents);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(528);
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
		public List<Object> list;
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
		enterRule(_localctx, 60, RULE_allClasses);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(531);
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
		public List<Object> list;
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
		enterRule(_localctx, 62, RULE_allVersions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(534);
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
		public List<Object> list;
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
		enterRule(_localctx, 64, RULE_allActivities);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(537);
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
		public List<Object> list;
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
		enterRule(_localctx, 66, RULE_allRelations);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(540);
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
		public List<Object> list;
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
		enterRule(_localctx, 68, RULE_allRelationships);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(543);
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
		public List<Object> list;
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
		enterRule(_localctx, 70, RULE_allActivityInstances);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(546);
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
		public List<Object> list;
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
		enterRule(_localctx, 72, RULE_allAttributes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(549);
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
		case 2:
			return objects_sempred((ObjectsContext)_localctx, predIndex);
		case 3:
			return cases_sempred((CasesContext)_localctx, predIndex);
		case 4:
			return events_sempred((EventsContext)_localctx, predIndex);
		case 5:
			return classes_sempred((ClassesContext)_localctx, predIndex);
		case 6:
			return versions_sempred((VersionsContext)_localctx, predIndex);
		case 7:
			return activities_sempred((ActivitiesContext)_localctx, predIndex);
		case 8:
			return relations_sempred((RelationsContext)_localctx, predIndex);
		case 9:
			return relationships_sempred((RelationshipsContext)_localctx, predIndex);
		case 10:
			return activityinstances_sempred((ActivityinstancesContext)_localctx, predIndex);
		case 11:
			return attributes_sempred((AttributesContext)_localctx, predIndex);
		case 15:
			return operator_sempred((OperatorContext)_localctx, predIndex);
		case 16:
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3>\u022b\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3n\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\5\4z\n\4\3\4\3\4\3\4\3\4\7\4\u0080\n\4\f\4\16\4\u0083\13\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u008f\n\5\3\5\3\5\3\5\3\5\7"+
		"\5\u0095\n\5\f\5\16\5\u0098\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\5\6\u00a4\n\6\3\6\3\6\3\6\3\6\7\6\u00aa\n\6\f\6\16\6\u00ad\13\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00b9\n\7\3\7\3\7\3\7\3\7\7\7"+
		"\u00bf\n\7\f\7\16\7\u00c2\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00d4\n\b\3\b\3\b\3\b\3\b\7\b\u00da\n\b\f"+
		"\b\16\b\u00dd\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00e9\n"+
		"\t\3\t\3\t\3\t\3\t\7\t\u00ef\n\t\f\t\16\t\u00f2\13\t\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\5\n\u00fe\n\n\3\n\3\n\3\n\3\n\7\n\u0104\n\n\f\n"+
		"\16\n\u0107\13\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13"+
		"\u0113\n\13\3\13\3\13\3\13\3\13\7\13\u0119\n\13\f\13\16\13\u011c\13\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0128\n\f\3\f\3\f\3\f\3\f"+
		"\7\f\u012e\n\f\f\f\16\f\u0131\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\5\r\u013d\n\r\3\r\3\r\3\r\3\r\7\r\u0143\n\r\f\r\16\r\u0146\13\r\3"+
		"\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\5\17\u015b\n\17\3\20\3\20\3\20\3\20\5\20\u0161"+
		"\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u017c"+
		"\n\21\3\21\3\21\5\21\u0180\n\21\3\21\5\21\u0183\n\21\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u01ad\n\22\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u01b9\n\23\3\24\3\24\3\24"+
		"\3\24\5\24\u01bf\n\24\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u01c7\n\25\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u01d1\n\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u01df\n\27\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u01ef"+
		"\n\30\3\31\3\31\3\31\3\31\5\31\u01f5\n\31\3\32\3\32\3\32\3\32\5\32\u01fb"+
		"\n\32\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u0203\n\33\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\5\34\u020b\n\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37"+
		"\3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\3&\3&"+
		"\2\f\6\b\n\f\16\20\22\24\26\30\'\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668:<>@BDFHJ\2\2\u0253\2L\3\2\2\2\4m\3\2\2\2\6y\3\2"+
		"\2\2\b\u008e\3\2\2\2\n\u00a3\3\2\2\2\f\u00b8\3\2\2\2\16\u00d3\3\2\2\2"+
		"\20\u00e8\3\2\2\2\22\u00fd\3\2\2\2\24\u0112\3\2\2\2\26\u0127\3\2\2\2\30"+
		"\u013c\3\2\2\2\32\u0147\3\2\2\2\34\u015a\3\2\2\2\36\u0160\3\2\2\2 \u0182"+
		"\3\2\2\2\"\u01ac\3\2\2\2$\u01b8\3\2\2\2&\u01be\3\2\2\2(\u01c6\3\2\2\2"+
		"*\u01d0\3\2\2\2,\u01de\3\2\2\2.\u01ee\3\2\2\2\60\u01f4\3\2\2\2\62\u01fa"+
		"\3\2\2\2\64\u0202\3\2\2\2\66\u020a\3\2\2\28\u020c\3\2\2\2:\u020f\3\2\2"+
		"\2<\u0212\3\2\2\2>\u0215\3\2\2\2@\u0218\3\2\2\2B\u021b\3\2\2\2D\u021e"+
		"\3\2\2\2F\u0221\3\2\2\2H\u0224\3\2\2\2J\u0227\3\2\2\2LM\5\4\3\2MN\b\2"+
		"\1\2N\3\3\2\2\2OP\5\b\5\2PQ\b\3\1\2Qn\3\2\2\2RS\5\6\4\2ST\b\3\1\2Tn\3"+
		"\2\2\2UV\5\n\6\2VW\b\3\1\2Wn\3\2\2\2XY\5\f\7\2YZ\b\3\1\2Zn\3\2\2\2[\\"+
		"\5\16\b\2\\]\b\3\1\2]n\3\2\2\2^_\5\20\t\2_`\b\3\1\2`n\3\2\2\2ab\5\22\n"+
		"\2bc\b\3\1\2cn\3\2\2\2de\5\24\13\2ef\b\3\1\2fn\3\2\2\2gh\5\26\f\2hi\b"+
		"\3\1\2in\3\2\2\2jk\5\30\r\2kl\b\3\1\2ln\3\2\2\2mO\3\2\2\2mR\3\2\2\2mU"+
		"\3\2\2\2mX\3\2\2\2m[\3\2\2\2m^\3\2\2\2ma\3\2\2\2md\3\2\2\2mg\3\2\2\2m"+
		"j\3\2\2\2n\5\3\2\2\2op\b\4\1\2pq\7\4\2\2qr\7+\2\2rs\5\4\3\2st\7,\2\2t"+
		"u\b\4\1\2uz\3\2\2\2vw\58\35\2wx\b\4\1\2xz\3\2\2\2yo\3\2\2\2yv\3\2\2\2"+
		"z\u0081\3\2\2\2{|\f\4\2\2|}\5\32\16\2}~\b\4\1\2~\u0080\3\2\2\2\177{\3"+
		"\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082"+
		"\7\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085\b\5\1\2\u0085\u0086\7\3\2\2"+
		"\u0086\u0087\7+\2\2\u0087\u0088\5\4\3\2\u0088\u0089\7,\2\2\u0089\u008a"+
		"\b\5\1\2\u008a\u008f\3\2\2\2\u008b\u008c\5:\36\2\u008c\u008d\b\5\1\2\u008d"+
		"\u008f\3\2\2\2\u008e\u0084\3\2\2\2\u008e\u008b\3\2\2\2\u008f\u0096\3\2"+
		"\2\2\u0090\u0091\f\3\2\2\u0091\u0092\5\32\16\2\u0092\u0093\b\5\1\2\u0093"+
		"\u0095\3\2\2\2\u0094\u0090\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2"+
		"\2\2\u0096\u0097\3\2\2\2\u0097\t\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u009a"+
		"\b\6\1\2\u009a\u009b\7\5\2\2\u009b\u009c\7+\2\2\u009c\u009d\5\4\3\2\u009d"+
		"\u009e\7,\2\2\u009e\u009f\b\6\1\2\u009f\u00a4\3\2\2\2\u00a0\u00a1\5<\37"+
		"\2\u00a1\u00a2\b\6\1\2\u00a2\u00a4\3\2\2\2\u00a3\u0099\3\2\2\2\u00a3\u00a0"+
		"\3\2\2\2\u00a4\u00ab\3\2\2\2\u00a5\u00a6\f\3\2\2\u00a6\u00a7\5\32\16\2"+
		"\u00a7\u00a8\b\6\1\2\u00a8\u00aa\3\2\2\2\u00a9\u00a5\3\2\2\2\u00aa\u00ad"+
		"\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\13\3\2\2\2\u00ad"+
		"\u00ab\3\2\2\2\u00ae\u00af\b\7\1\2\u00af\u00b0\7\6\2\2\u00b0\u00b1\7+"+
		"\2\2\u00b1\u00b2\5\4\3\2\u00b2\u00b3\7,\2\2\u00b3\u00b4\b\7\1\2\u00b4"+
		"\u00b9\3\2\2\2\u00b5\u00b6\5> \2\u00b6\u00b7\b\7\1\2\u00b7\u00b9\3\2\2"+
		"\2\u00b8\u00ae\3\2\2\2\u00b8\u00b5\3\2\2\2\u00b9\u00c0\3\2\2\2\u00ba\u00bb"+
		"\f\3\2\2\u00bb\u00bc\5\32\16\2\u00bc\u00bd\b\7\1\2\u00bd\u00bf\3\2\2\2"+
		"\u00be\u00ba\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1"+
		"\3\2\2\2\u00c1\r\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c3\u00c4\b\b\1\2\u00c4"+
		"\u00c5\7\7\2\2\u00c5\u00c6\7+\2\2\u00c6\u00c7\5\4\3\2\u00c7\u00c8\7,\2"+
		"\2\u00c8\u00c9\b\b\1\2\u00c9\u00d4\3\2\2\2\u00ca\u00cb\5@!\2\u00cb\u00cc"+
		"\b\b\1\2\u00cc\u00d4\3\2\2\2\u00cd\u00ce\7\t\2\2\u00ce\u00cf\7+\2\2\u00cf"+
		"\u00d0\5\16\b\2\u00d0\u00d1\7,\2\2\u00d1\u00d2\b\b\1\2\u00d2\u00d4\3\2"+
		"\2\2\u00d3\u00c3\3\2\2\2\u00d3\u00ca\3\2\2\2\u00d3\u00cd\3\2\2\2\u00d4"+
		"\u00db\3\2\2\2\u00d5\u00d6\f\3\2\2\u00d6\u00d7\5\32\16\2\u00d7\u00d8\b"+
		"\b\1\2\u00d8\u00da\3\2\2\2\u00d9\u00d5\3\2\2\2\u00da\u00dd\3\2\2\2\u00db"+
		"\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\17\3\2\2\2\u00dd\u00db\3\2\2"+
		"\2\u00de\u00df\b\t\1\2\u00df\u00e0\7\b\2\2\u00e0\u00e1\7+\2\2\u00e1\u00e2"+
		"\5\4\3\2\u00e2\u00e3\7,\2\2\u00e3\u00e4\b\t\1\2\u00e4\u00e9\3\2\2\2\u00e5"+
		"\u00e6\5B\"\2\u00e6\u00e7\b\t\1\2\u00e7\u00e9\3\2\2\2\u00e8\u00de\3\2"+
		"\2\2\u00e8\u00e5\3\2\2\2\u00e9\u00f0\3\2\2\2\u00ea\u00eb\f\3\2\2\u00eb"+
		"\u00ec\5\32\16\2\u00ec\u00ed\b\t\1\2\u00ed\u00ef\3\2\2\2\u00ee\u00ea\3"+
		"\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1"+
		"\21\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00f4\b\n\1\2\u00f4\u00f5\7\n\2"+
		"\2\u00f5\u00f6\7+\2\2\u00f6\u00f7\5\4\3\2\u00f7\u00f8\7,\2\2\u00f8\u00f9"+
		"\b\n\1\2\u00f9\u00fe\3\2\2\2\u00fa\u00fb\5D#\2\u00fb\u00fc\b\n\1\2\u00fc"+
		"\u00fe\3\2\2\2\u00fd\u00f3\3\2\2\2\u00fd\u00fa\3\2\2\2\u00fe\u0105\3\2"+
		"\2\2\u00ff\u0100\f\3\2\2\u0100\u0101\5\32\16\2\u0101\u0102\b\n\1\2\u0102"+
		"\u0104\3\2\2\2\u0103\u00ff\3\2\2\2\u0104\u0107\3\2\2\2\u0105\u0103\3\2"+
		"\2\2\u0105\u0106\3\2\2\2\u0106\23\3\2\2\2\u0107\u0105\3\2\2\2\u0108\u0109"+
		"\b\13\1\2\u0109\u010a\7\13\2\2\u010a\u010b\7+\2\2\u010b\u010c\5\4\3\2"+
		"\u010c\u010d\7,\2\2\u010d\u010e\b\13\1\2\u010e\u0113\3\2\2\2\u010f\u0110"+
		"\5F$\2\u0110\u0111\b\13\1\2\u0111\u0113\3\2\2\2\u0112\u0108\3\2\2\2\u0112"+
		"\u010f\3\2\2\2\u0113\u011a\3\2\2\2\u0114\u0115\f\3\2\2\u0115\u0116\5\32"+
		"\16\2\u0116\u0117\b\13\1\2\u0117\u0119\3\2\2\2\u0118\u0114\3\2\2\2\u0119"+
		"\u011c\3\2\2\2\u011a\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b\25\3\2\2"+
		"\2\u011c\u011a\3\2\2\2\u011d\u011e\b\f\1\2\u011e\u011f\7\f\2\2\u011f\u0120"+
		"\7+\2\2\u0120\u0121\5\4\3\2\u0121\u0122\7,\2\2\u0122\u0123\b\f\1\2\u0123"+
		"\u0128\3\2\2\2\u0124\u0125\5H%\2\u0125\u0126\b\f\1\2\u0126\u0128\3\2\2"+
		"\2\u0127\u011d\3\2\2\2\u0127\u0124\3\2\2\2\u0128\u012f\3\2\2\2\u0129\u012a"+
		"\f\3\2\2\u012a\u012b\5\32\16\2\u012b\u012c\b\f\1\2\u012c\u012e\3\2\2\2"+
		"\u012d\u0129\3\2\2\2\u012e\u0131\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130"+
		"\3\2\2\2\u0130\27\3\2\2\2\u0131\u012f\3\2\2\2\u0132\u0133\b\r\1\2\u0133"+
		"\u0134\7\r\2\2\u0134\u0135\7+\2\2\u0135\u0136\5\4\3\2\u0136\u0137\7,\2"+
		"\2\u0137\u0138\b\r\1\2\u0138\u013d\3\2\2\2\u0139\u013a\5J&\2\u013a\u013b"+
		"\b\r\1\2\u013b\u013d\3\2\2\2\u013c\u0132\3\2\2\2\u013c\u0139\3\2\2\2\u013d"+
		"\u0144\3\2\2\2\u013e\u013f\f\3\2\2\u013f\u0140\5\32\16\2\u0140\u0141\b"+
		"\r\1\2\u0141\u0143\3\2\2\2\u0142\u013e\3\2\2\2\u0143\u0146\3\2\2\2\u0144"+
		"\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145\31\3\2\2\2\u0146\u0144\3\2\2"+
		"\2\u0147\u0148\7-\2\2\u0148\u0149\5\34\17\2\u0149\u014a\b\16\1\2\u014a"+
		"\33\3\2\2\2\u014b\u014c\7\67\2\2\u014c\u014d\5\34\17\2\u014d\u014e\b\17"+
		"\1\2\u014e\u015b\3\2\2\2\u014f\u0150\7+\2\2\u0150\u0151\5\34\17\2\u0151"+
		"\u0152\5\36\20\2\u0152\u0153\5\34\17\2\u0153\u0154\7,\2\2\u0154\u0155"+
		"\b\17\1\2\u0155\u015b\3\2\2\2\u0156\u0157\5\"\22\2\u0157\u0158\5 \21\2"+
		"\u0158\u0159\b\17\1\2\u0159\u015b\3\2\2\2\u015a\u014b\3\2\2\2\u015a\u014f"+
		"\3\2\2\2\u015a\u0156\3\2\2\2\u015b\35\3\2\2\2\u015c\u015d\7\65\2\2\u015d"+
		"\u0161\b\20\1\2\u015e\u015f\7\66\2\2\u015f\u0161\b\20\1\2\u0160\u015c"+
		"\3\2\2\2\u0160\u015e\3\2\2\2\u0161\37\3\2\2\2\u0162\u0163\7.\2\2\u0163"+
		"\u0164\7;\2\2\u0164\u0183\b\21\1\2\u0165\u0166\7/\2\2\u0166\u0167\7;\2"+
		"\2\u0167\u0183\b\21\1\2\u0168\u0169\7\60\2\2\u0169\u016a\7;\2\2\u016a"+
		"\u0183\b\21\1\2\u016b\u016c\7\61\2\2\u016c\u016d\7;\2\2\u016d\u0183\b"+
		"\21\1\2\u016e\u016f\7\62\2\2\u016f\u0170\7;\2\2\u0170\u0183\b\21\1\2\u0171"+
		"\u0172\7\63\2\2\u0172\u0173\7;\2\2\u0173\u0183\b\21\1\2\u0174\u0175\7"+
		"\64\2\2\u0175\u0176\7;\2\2\u0176\u0183\b\21\1\2\u0177\u0178\6\21\f\3\u0178"+
		"\u017b\78\2\2\u0179\u017a\79\2\2\u017a\u017c\7;\2\2\u017b\u0179\3\2\2"+
		"\2\u017b\u017c\3\2\2\2\u017c\u017f\3\2\2\2\u017d\u017e\7:\2\2\u017e\u0180"+
		"\7;\2\2\u017f\u017d\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u0181\3\2\2\2\u0181"+
		"\u0183\b\21\1\2\u0182\u0162\3\2\2\2\u0182\u0165\3\2\2\2\u0182\u0168\3"+
		"\2\2\2\u0182\u016b\3\2\2\2\u0182\u016e\3\2\2\2\u0182\u0171\3\2\2\2\u0182"+
		"\u0174\3\2\2\2\u0182\u0177\3\2\2\2\u0183!\3\2\2\2\u0184\u0185\6\22\r\3"+
		"\u0185\u0186\5$\23\2\u0186\u0187\b\22\1\2\u0187\u01ad\3\2\2\2\u0188\u0189"+
		"\6\22\16\3\u0189\u018a\5&\24\2\u018a\u018b\b\22\1\2\u018b\u01ad\3\2\2"+
		"\2\u018c\u018d\6\22\17\3\u018d\u018e\5(\25\2\u018e\u018f\b\22\1\2\u018f"+
		"\u01ad\3\2\2\2\u0190\u0191\6\22\20\3\u0191\u0192\5*\26\2\u0192\u0193\b"+
		"\22\1\2\u0193\u01ad\3\2\2\2\u0194\u0195\6\22\21\3\u0195\u0196\5,\27\2"+
		"\u0196\u0197\b\22\1\2\u0197\u01ad\3\2\2\2\u0198\u0199\6\22\22\3\u0199"+
		"\u019a\5.\30\2\u019a\u019b\b\22\1\2\u019b\u01ad\3\2\2\2\u019c\u019d\6"+
		"\22\23\3\u019d\u019e\5\60\31\2\u019e\u019f\b\22\1\2\u019f\u01ad\3\2\2"+
		"\2\u01a0\u01a1\6\22\24\3\u01a1\u01a2\5\62\32\2\u01a2\u01a3\b\22\1\2\u01a3"+
		"\u01ad\3\2\2\2\u01a4\u01a5\6\22\25\3\u01a5\u01a6\5\64\33\2\u01a6\u01a7"+
		"\b\22\1\2\u01a7\u01ad\3\2\2\2\u01a8\u01a9\6\22\26\3\u01a9\u01aa\5\66\34"+
		"\2\u01aa\u01ab\b\22\1\2\u01ab\u01ad\3\2\2\2\u01ac\u0184\3\2\2\2\u01ac"+
		"\u0188\3\2\2\2\u01ac\u018c\3\2\2\2\u01ac\u0190\3\2\2\2\u01ac\u0194\3\2"+
		"\2\2\u01ac\u0198\3\2\2\2\u01ac\u019c\3\2\2\2\u01ac\u01a0\3\2\2\2\u01ac"+
		"\u01a4\3\2\2\2\u01ac\u01a8\3\2\2\2\u01ad#\3\2\2\2\u01ae\u01af\7\30\2\2"+
		"\u01af\u01b9\b\23\1\2\u01b0\u01b1\7\36\2\2\u01b1\u01b9\b\23\1\2\u01b2"+
		"\u01b3\7\37\2\2\u01b3\u01b9\b\23\1\2\u01b4\u01b5\7 \2\2\u01b5\u01b9\b"+
		"\23\1\2\u01b6\u01b7\7<\2\2\u01b7\u01b9\b\23\1\2\u01b8\u01ae\3\2\2\2\u01b8"+
		"\u01b0\3\2\2\2\u01b8\u01b2\3\2\2\2\u01b8\u01b4\3\2\2\2\u01b8\u01b6\3\2"+
		"\2\2\u01b9%\3\2\2\2\u01ba\u01bb\7\30\2\2\u01bb\u01bf\b\24\1\2\u01bc\u01bd"+
		"\7\33\2\2\u01bd\u01bf\b\24\1\2\u01be\u01ba\3\2\2\2\u01be\u01bc\3\2\2\2"+
		"\u01bf\'\3\2\2\2\u01c0\u01c1\7\30\2\2\u01c1\u01c7\b\25\1\2\u01c2\u01c3"+
		"\7\31\2\2\u01c3\u01c7\b\25\1\2\u01c4\u01c5\7\32\2\2\u01c5\u01c7\b\25\1"+
		"\2\u01c6\u01c0\3\2\2\2\u01c6\u01c2\3\2\2\2\u01c6\u01c4\3\2\2\2\u01c7)"+
		"\3\2\2\2\u01c8\u01c9\7\30\2\2\u01c9\u01d1\b\26\1\2\u01ca\u01cb\7\34\2"+
		"\2\u01cb\u01d1\b\26\1\2\u01cc\u01cd\7\35\2\2\u01cd\u01d1\b\26\1\2\u01ce"+
		"\u01cf\7\32\2\2\u01cf\u01d1\b\26\1\2\u01d0\u01c8\3\2\2\2\u01d0\u01ca\3"+
		"\2\2\2\u01d0\u01cc\3\2\2\2\u01d0\u01ce\3\2\2\2\u01d1+\3\2\2\2\u01d2\u01d3"+
		"\7\30\2\2\u01d3\u01df\b\27\1\2\u01d4\u01d5\7!\2\2\u01d5\u01df\b\27\1\2"+
		"\u01d6\u01d7\7\"\2\2\u01d7\u01df\b\27\1\2\u01d8\u01d9\7#\2\2\u01d9\u01df"+
		"\b\27\1\2\u01da\u01db\7\37\2\2\u01db\u01df\b\27\1\2\u01dc\u01dd\7 \2\2"+
		"\u01dd\u01df\b\27\1\2\u01de\u01d2\3\2\2\2\u01de\u01d4\3\2\2\2\u01de\u01d6"+
		"\3\2\2\2\u01de\u01d8\3\2\2\2\u01de\u01da\3\2\2\2\u01de\u01dc\3\2\2\2\u01df"+
		"-\3\2\2\2\u01e0\u01e1\7\30\2\2\u01e1\u01ef\b\30\1\2\u01e2\u01e3\7$\2\2"+
		"\u01e3\u01ef\b\30\1\2\u01e4\u01e5\7%\2\2\u01e5\u01ef\b\30\1\2\u01e6\u01e7"+
		"\7&\2\2\u01e7\u01ef\b\30\1\2\u01e8\u01e9\7\'\2\2\u01e9\u01ef\b\30\1\2"+
		"\u01ea\u01eb\7(\2\2\u01eb\u01ef\b\30\1\2\u01ec\u01ed\7<\2\2\u01ed\u01ef"+
		"\b\30\1\2\u01ee\u01e0\3\2\2\2\u01ee\u01e2\3\2\2\2\u01ee\u01e4\3\2\2\2"+
		"\u01ee\u01e6\3\2\2\2\u01ee\u01e8\3\2\2\2\u01ee\u01ea\3\2\2\2\u01ee\u01ec"+
		"\3\2\2\2\u01ef/\3\2\2\2\u01f0\u01f1\7\30\2\2\u01f1\u01f5\b\31\1\2\u01f2"+
		"\u01f3\7\32\2\2\u01f3\u01f5\b\31\1\2\u01f4\u01f0\3\2\2\2\u01f4\u01f2\3"+
		"\2\2\2\u01f5\61\3\2\2\2\u01f6\u01f7\7\30\2\2\u01f7\u01fb\b\32\1\2\u01f8"+
		"\u01f9\7)\2\2\u01f9\u01fb\b\32\1\2\u01fa\u01f6\3\2\2\2\u01fa\u01f8\3\2"+
		"\2\2\u01fb\63\3\2\2\2\u01fc\u01fd\7\30\2\2\u01fd\u0203\b\33\1\2\u01fe"+
		"\u01ff\7*\2\2\u01ff\u0203\b\33\1\2\u0200\u0201\7\32\2\2\u0201\u0203\b"+
		"\33\1\2\u0202\u01fc\3\2\2\2\u0202\u01fe\3\2\2\2\u0202\u0200\3\2\2\2\u0203"+
		"\65\3\2\2\2\u0204\u0205\7\30\2\2\u0205\u020b\b\34\1\2\u0206\u0207\7\33"+
		"\2\2\u0207\u020b\b\34\1\2\u0208\u0209\7\32\2\2\u0209\u020b\b\34\1\2\u020a"+
		"\u0204\3\2\2\2\u020a\u0206\3\2\2\2\u020a\u0208\3\2\2\2\u020b\67\3\2\2"+
		"\2\u020c\u020d\7\16\2\2\u020d\u020e\b\35\1\2\u020e9\3\2\2\2\u020f\u0210"+
		"\7\17\2\2\u0210\u0211\b\36\1\2\u0211;\3\2\2\2\u0212\u0213\7\20\2\2\u0213"+
		"\u0214\b\37\1\2\u0214=\3\2\2\2\u0215\u0216\7\21\2\2\u0216\u0217\b \1\2"+
		"\u0217?\3\2\2\2\u0218\u0219\7\22\2\2\u0219\u021a\b!\1\2\u021aA\3\2\2\2"+
		"\u021b\u021c\7\23\2\2\u021c\u021d\b\"\1\2\u021dC\3\2\2\2\u021e\u021f\7"+
		"\24\2\2\u021f\u0220\b#\1\2\u0220E\3\2\2\2\u0221\u0222\7\25\2\2\u0222\u0223"+
		"\b$\1\2\u0223G\3\2\2\2\u0224\u0225\7\26\2\2\u0225\u0226\b%\1\2\u0226I"+
		"\3\2\2\2\u0227\u0228\7\27\2\2\u0228\u0229\b&\1\2\u0229K\3\2\2\2\'my\u0081"+
		"\u008e\u0096\u00a3\u00ab\u00b8\u00c0\u00d3\u00db\u00e8\u00f0\u00fd\u0105"+
		"\u0112\u011a\u0127\u012f\u013c\u0144\u015a\u0160\u017b\u017f\u0182\u01ac"+
		"\u01b8\u01be\u01c6\u01d0\u01de\u01ee\u01f4\u01fa\u0202\u020a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}