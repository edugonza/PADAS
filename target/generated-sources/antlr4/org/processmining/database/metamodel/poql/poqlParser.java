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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, CASESOF=7, OBJECTSOF=8, 
		EVENTSOF=9, CLASSESOF=10, VERSIONSOF=11, ACTIVITIESOF=12, OPEN_FUNCTION=13, 
		CLOSE_FUNCTION=14, WHERE=15, EQUAL=16, DIFFERENT=17, EQUAL_OR_GREATER=18, 
		EQUAL_OR_SMALLER=19, GREATER=20, SMALLER=21, CONTAINS=22, AND=23, OR=24, 
		STRING=25, IDATT=26, IDNOATT=27, WS=28;
	public static final int
		RULE_prog = 0, RULE_things = 1, RULE_things_no_object = 2, RULE_things_no_case = 3, 
		RULE_things_no_event = 4, RULE_things_no_class = 5, RULE_things_no_version = 6, 
		RULE_things_no_activity = 7, RULE_objects = 8, RULE_cases = 9, RULE_events = 10, 
		RULE_classes = 11, RULE_versions = 12, RULE_activities = 13, RULE_filter = 14, 
		RULE_filter_expression = 15, RULE_id = 16, RULE_allObjects = 17, RULE_allCases = 18, 
		RULE_allEvents = 19, RULE_allClasses = 20, RULE_allVersions = 21, RULE_allActivities = 22;
	public static final String[] ruleNames = {
		"prog", "things", "things_no_object", "things_no_case", "things_no_event", 
		"things_no_class", "things_no_version", "things_no_activity", "objects", 
		"cases", "events", "classes", "versions", "activities", "filter", "filter_expression", 
		"id", "allObjects", "allCases", "allEvents", "allClasses", "allVersions", 
		"allActivities"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'allObjects'", "'allCases'", "'allEvents'", "'allClasses'", "'allVersions'", 
		"'allActivities'", "'casesOf'", "'objectsOf'", "'eventsOf'", "'classesOf'", 
		"'versionsOf'", "'activitiesOf'", "'('", "')'", "'where'", "'=='", "'<>'", 
		"'=>'", "'=<'", "'>'", "'<'", "'contains'", "'AND'", "'OR'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "CASESOF", "OBJECTSOF", "EVENTSOF", 
		"CLASSESOF", "VERSIONSOF", "ACTIVITIESOF", "OPEN_FUNCTION", "CLOSE_FUNCTION", 
		"WHERE", "EQUAL", "DIFFERENT", "EQUAL_OR_GREATER", "EQUAL_OR_SMALLER", 
		"GREATER", "SMALLER", "CONTAINS", "AND", "OR", "STRING", "IDATT", "IDNOATT", 
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
			setState(46);
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
			setState(67);
			switch (_input.LA(1)) {
			case T__1:
			case CASESOF:
				enterOuterAlt(_localctx, 1);
				{
				setState(49);
				((ThingsContext)_localctx).t1 = cases(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t1.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t1.type; 
				}
				break;
			case T__0:
			case OBJECTSOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				((ThingsContext)_localctx).t2 = objects(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t2.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t2.type; 
				}
				break;
			case T__2:
			case EVENTSOF:
				enterOuterAlt(_localctx, 3);
				{
				setState(55);
				((ThingsContext)_localctx).t3 = events(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t3.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t3.type; 
				}
				break;
			case T__3:
			case CLASSESOF:
				enterOuterAlt(_localctx, 4);
				{
				setState(58);
				((ThingsContext)_localctx).t4 = classes(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t4.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t4.type; 
				}
				break;
			case T__4:
			case VERSIONSOF:
				enterOuterAlt(_localctx, 5);
				{
				setState(61);
				((ThingsContext)_localctx).t5 = versions(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t5.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t5.type; 
				}
				break;
			case T__5:
			case ACTIVITIESOF:
				enterOuterAlt(_localctx, 6);
				{
				setState(64);
				((ThingsContext)_localctx).t6 = activities(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t6.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t6.type; 
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

	public static class Things_no_objectContext extends ParserRuleContext {
		public List<Object> list;
		public Class type;
		public CasesContext t1;
		public EventsContext t2;
		public ClassesContext t3;
		public VersionsContext t4;
		public ActivitiesContext t5;
		public CasesContext cases() {
			return getRuleContext(CasesContext.class,0);
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
		public Things_no_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_things_no_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterThings_no_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitThings_no_object(this);
		}
	}

	public final Things_no_objectContext things_no_object() throws RecognitionException {
		Things_no_objectContext _localctx = new Things_no_objectContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_things_no_object);
		try {
			setState(84);
			switch (_input.LA(1)) {
			case T__1:
			case CASESOF:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				((Things_no_objectContext)_localctx).t1 = cases(0);
				 ((Things_no_objectContext)_localctx).list =  ((Things_no_objectContext)_localctx).t1.list; ((Things_no_objectContext)_localctx).type =  ((Things_no_objectContext)_localctx).t1.type; 
				}
				break;
			case T__2:
			case EVENTSOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				((Things_no_objectContext)_localctx).t2 = events(0);
				 ((Things_no_objectContext)_localctx).list =  ((Things_no_objectContext)_localctx).t2.list; ((Things_no_objectContext)_localctx).type =  ((Things_no_objectContext)_localctx).t2.type; 
				}
				break;
			case T__3:
			case CLASSESOF:
				enterOuterAlt(_localctx, 3);
				{
				setState(75);
				((Things_no_objectContext)_localctx).t3 = classes(0);
				 ((Things_no_objectContext)_localctx).list =  ((Things_no_objectContext)_localctx).t3.list; ((Things_no_objectContext)_localctx).type =  ((Things_no_objectContext)_localctx).t3.type; 
				}
				break;
			case T__4:
			case VERSIONSOF:
				enterOuterAlt(_localctx, 4);
				{
				setState(78);
				((Things_no_objectContext)_localctx).t4 = versions(0);
				 ((Things_no_objectContext)_localctx).list =  ((Things_no_objectContext)_localctx).t4.list; ((Things_no_objectContext)_localctx).type =  ((Things_no_objectContext)_localctx).t4.type; 
				}
				break;
			case T__5:
			case ACTIVITIESOF:
				enterOuterAlt(_localctx, 5);
				{
				setState(81);
				((Things_no_objectContext)_localctx).t5 = activities(0);
				 ((Things_no_objectContext)_localctx).list =  ((Things_no_objectContext)_localctx).t5.list; ((Things_no_objectContext)_localctx).type =  ((Things_no_objectContext)_localctx).t5.type; 
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

	public static class Things_no_caseContext extends ParserRuleContext {
		public List<Object> list;
		public Class type;
		public ObjectsContext t1;
		public EventsContext t2;
		public ClassesContext t3;
		public VersionsContext t4;
		public ActivitiesContext t5;
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
		public Things_no_caseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_things_no_case; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterThings_no_case(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitThings_no_case(this);
		}
	}

	public final Things_no_caseContext things_no_case() throws RecognitionException {
		Things_no_caseContext _localctx = new Things_no_caseContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_things_no_case);
		try {
			setState(101);
			switch (_input.LA(1)) {
			case T__0:
			case OBJECTSOF:
				enterOuterAlt(_localctx, 1);
				{
				setState(86);
				((Things_no_caseContext)_localctx).t1 = objects(0);
				 ((Things_no_caseContext)_localctx).list =  ((Things_no_caseContext)_localctx).t1.list; ((Things_no_caseContext)_localctx).type =  ((Things_no_caseContext)_localctx).t1.type; 
				}
				break;
			case T__2:
			case EVENTSOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				((Things_no_caseContext)_localctx).t2 = events(0);
				 ((Things_no_caseContext)_localctx).list =  ((Things_no_caseContext)_localctx).t2.list; ((Things_no_caseContext)_localctx).type =  ((Things_no_caseContext)_localctx).t2.type; 
				}
				break;
			case T__3:
			case CLASSESOF:
				enterOuterAlt(_localctx, 3);
				{
				setState(92);
				((Things_no_caseContext)_localctx).t3 = classes(0);
				 ((Things_no_caseContext)_localctx).list =  ((Things_no_caseContext)_localctx).t3.list; ((Things_no_caseContext)_localctx).type =  ((Things_no_caseContext)_localctx).t3.type; 
				}
				break;
			case T__4:
			case VERSIONSOF:
				enterOuterAlt(_localctx, 4);
				{
				setState(95);
				((Things_no_caseContext)_localctx).t4 = versions(0);
				 ((Things_no_caseContext)_localctx).list =  ((Things_no_caseContext)_localctx).t4.list; ((Things_no_caseContext)_localctx).type =  ((Things_no_caseContext)_localctx).t4.type; 
				}
				break;
			case T__5:
			case ACTIVITIESOF:
				enterOuterAlt(_localctx, 5);
				{
				setState(98);
				((Things_no_caseContext)_localctx).t5 = activities(0);
				 ((Things_no_caseContext)_localctx).list =  ((Things_no_caseContext)_localctx).t5.list; ((Things_no_caseContext)_localctx).type =  ((Things_no_caseContext)_localctx).t5.type; 
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

	public static class Things_no_eventContext extends ParserRuleContext {
		public List<Object> list;
		public Class type;
		public CasesContext t1;
		public ObjectsContext t2;
		public ClassesContext t3;
		public VersionsContext t4;
		public ActivitiesContext t5;
		public CasesContext cases() {
			return getRuleContext(CasesContext.class,0);
		}
		public ObjectsContext objects() {
			return getRuleContext(ObjectsContext.class,0);
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
		public Things_no_eventContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_things_no_event; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterThings_no_event(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitThings_no_event(this);
		}
	}

	public final Things_no_eventContext things_no_event() throws RecognitionException {
		Things_no_eventContext _localctx = new Things_no_eventContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_things_no_event);
		try {
			setState(118);
			switch (_input.LA(1)) {
			case T__1:
			case CASESOF:
				enterOuterAlt(_localctx, 1);
				{
				setState(103);
				((Things_no_eventContext)_localctx).t1 = cases(0);
				 ((Things_no_eventContext)_localctx).list =  ((Things_no_eventContext)_localctx).t1.list; ((Things_no_eventContext)_localctx).type =  ((Things_no_eventContext)_localctx).t1.type; 
				}
				break;
			case T__0:
			case OBJECTSOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(106);
				((Things_no_eventContext)_localctx).t2 = objects(0);
				 ((Things_no_eventContext)_localctx).list =  ((Things_no_eventContext)_localctx).t2.list; ((Things_no_eventContext)_localctx).type =  ((Things_no_eventContext)_localctx).t2.type; 
				}
				break;
			case T__3:
			case CLASSESOF:
				enterOuterAlt(_localctx, 3);
				{
				setState(109);
				((Things_no_eventContext)_localctx).t3 = classes(0);
				 ((Things_no_eventContext)_localctx).list =  ((Things_no_eventContext)_localctx).t3.list; ((Things_no_eventContext)_localctx).type =  ((Things_no_eventContext)_localctx).t3.type; 
				}
				break;
			case T__4:
			case VERSIONSOF:
				enterOuterAlt(_localctx, 4);
				{
				setState(112);
				((Things_no_eventContext)_localctx).t4 = versions(0);
				 ((Things_no_eventContext)_localctx).list =  ((Things_no_eventContext)_localctx).t4.list; ((Things_no_eventContext)_localctx).type =  ((Things_no_eventContext)_localctx).t4.type; 
				}
				break;
			case T__5:
			case ACTIVITIESOF:
				enterOuterAlt(_localctx, 5);
				{
				setState(115);
				((Things_no_eventContext)_localctx).t5 = activities(0);
				 ((Things_no_eventContext)_localctx).list =  ((Things_no_eventContext)_localctx).t5.list; ((Things_no_eventContext)_localctx).type =  ((Things_no_eventContext)_localctx).t5.type; 
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

	public static class Things_no_classContext extends ParserRuleContext {
		public List<Object> list;
		public Class type;
		public CasesContext t1;
		public ObjectsContext t2;
		public EventsContext t3;
		public VersionsContext t4;
		public ActivitiesContext t5;
		public CasesContext cases() {
			return getRuleContext(CasesContext.class,0);
		}
		public ObjectsContext objects() {
			return getRuleContext(ObjectsContext.class,0);
		}
		public EventsContext events() {
			return getRuleContext(EventsContext.class,0);
		}
		public VersionsContext versions() {
			return getRuleContext(VersionsContext.class,0);
		}
		public ActivitiesContext activities() {
			return getRuleContext(ActivitiesContext.class,0);
		}
		public Things_no_classContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_things_no_class; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterThings_no_class(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitThings_no_class(this);
		}
	}

	public final Things_no_classContext things_no_class() throws RecognitionException {
		Things_no_classContext _localctx = new Things_no_classContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_things_no_class);
		try {
			setState(135);
			switch (_input.LA(1)) {
			case T__1:
			case CASESOF:
				enterOuterAlt(_localctx, 1);
				{
				setState(120);
				((Things_no_classContext)_localctx).t1 = cases(0);
				 ((Things_no_classContext)_localctx).list =  ((Things_no_classContext)_localctx).t1.list; ((Things_no_classContext)_localctx).type =  ((Things_no_classContext)_localctx).t1.type; 
				}
				break;
			case T__0:
			case OBJECTSOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				((Things_no_classContext)_localctx).t2 = objects(0);
				 ((Things_no_classContext)_localctx).list =  ((Things_no_classContext)_localctx).t2.list; ((Things_no_classContext)_localctx).type =  ((Things_no_classContext)_localctx).t2.type; 
				}
				break;
			case T__2:
			case EVENTSOF:
				enterOuterAlt(_localctx, 3);
				{
				setState(126);
				((Things_no_classContext)_localctx).t3 = events(0);
				 ((Things_no_classContext)_localctx).list =  ((Things_no_classContext)_localctx).t3.list; ((Things_no_classContext)_localctx).type =  ((Things_no_classContext)_localctx).t3.type; 
				}
				break;
			case T__4:
			case VERSIONSOF:
				enterOuterAlt(_localctx, 4);
				{
				setState(129);
				((Things_no_classContext)_localctx).t4 = versions(0);
				 ((Things_no_classContext)_localctx).list =  ((Things_no_classContext)_localctx).t4.list; ((Things_no_classContext)_localctx).type =  ((Things_no_classContext)_localctx).t4.type; 
				}
				break;
			case T__5:
			case ACTIVITIESOF:
				enterOuterAlt(_localctx, 5);
				{
				setState(132);
				((Things_no_classContext)_localctx).t5 = activities(0);
				 ((Things_no_classContext)_localctx).list =  ((Things_no_classContext)_localctx).t5.list; ((Things_no_classContext)_localctx).type =  ((Things_no_classContext)_localctx).t5.type; 
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

	public static class Things_no_versionContext extends ParserRuleContext {
		public List<Object> list;
		public Class type;
		public CasesContext t1;
		public ObjectsContext t2;
		public EventsContext t3;
		public ClassesContext t4;
		public ActivitiesContext t5;
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
		public ActivitiesContext activities() {
			return getRuleContext(ActivitiesContext.class,0);
		}
		public Things_no_versionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_things_no_version; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterThings_no_version(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitThings_no_version(this);
		}
	}

	public final Things_no_versionContext things_no_version() throws RecognitionException {
		Things_no_versionContext _localctx = new Things_no_versionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_things_no_version);
		try {
			setState(152);
			switch (_input.LA(1)) {
			case T__1:
			case CASESOF:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				((Things_no_versionContext)_localctx).t1 = cases(0);
				 ((Things_no_versionContext)_localctx).list =  ((Things_no_versionContext)_localctx).t1.list; ((Things_no_versionContext)_localctx).type =  ((Things_no_versionContext)_localctx).t1.type; 
				}
				break;
			case T__0:
			case OBJECTSOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(140);
				((Things_no_versionContext)_localctx).t2 = objects(0);
				 ((Things_no_versionContext)_localctx).list =  ((Things_no_versionContext)_localctx).t2.list; ((Things_no_versionContext)_localctx).type =  ((Things_no_versionContext)_localctx).t2.type; 
				}
				break;
			case T__2:
			case EVENTSOF:
				enterOuterAlt(_localctx, 3);
				{
				setState(143);
				((Things_no_versionContext)_localctx).t3 = events(0);
				 ((Things_no_versionContext)_localctx).list =  ((Things_no_versionContext)_localctx).t3.list; ((Things_no_versionContext)_localctx).type =  ((Things_no_versionContext)_localctx).t3.type; 
				}
				break;
			case T__3:
			case CLASSESOF:
				enterOuterAlt(_localctx, 4);
				{
				setState(146);
				((Things_no_versionContext)_localctx).t4 = classes(0);
				 ((Things_no_versionContext)_localctx).list =  ((Things_no_versionContext)_localctx).t4.list; ((Things_no_versionContext)_localctx).type =  ((Things_no_versionContext)_localctx).t4.type; 
				}
				break;
			case T__5:
			case ACTIVITIESOF:
				enterOuterAlt(_localctx, 5);
				{
				setState(149);
				((Things_no_versionContext)_localctx).t5 = activities(0);
				 ((Things_no_versionContext)_localctx).list =  ((Things_no_versionContext)_localctx).t5.list; ((Things_no_versionContext)_localctx).type =  ((Things_no_versionContext)_localctx).t5.type; 
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

	public static class Things_no_activityContext extends ParserRuleContext {
		public List<Object> list;
		public Class type;
		public CasesContext t1;
		public ObjectsContext t2;
		public EventsContext t3;
		public ClassesContext t4;
		public VersionsContext t5;
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
		public Things_no_activityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_things_no_activity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterThings_no_activity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitThings_no_activity(this);
		}
	}

	public final Things_no_activityContext things_no_activity() throws RecognitionException {
		Things_no_activityContext _localctx = new Things_no_activityContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_things_no_activity);
		try {
			setState(169);
			switch (_input.LA(1)) {
			case T__1:
			case CASESOF:
				enterOuterAlt(_localctx, 1);
				{
				setState(154);
				((Things_no_activityContext)_localctx).t1 = cases(0);
				 ((Things_no_activityContext)_localctx).list =  ((Things_no_activityContext)_localctx).t1.list; ((Things_no_activityContext)_localctx).type =  ((Things_no_activityContext)_localctx).t1.type; 
				}
				break;
			case T__0:
			case OBJECTSOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(157);
				((Things_no_activityContext)_localctx).t2 = objects(0);
				 ((Things_no_activityContext)_localctx).list =  ((Things_no_activityContext)_localctx).t2.list; ((Things_no_activityContext)_localctx).type =  ((Things_no_activityContext)_localctx).t2.type; 
				}
				break;
			case T__2:
			case EVENTSOF:
				enterOuterAlt(_localctx, 3);
				{
				setState(160);
				((Things_no_activityContext)_localctx).t3 = events(0);
				 ((Things_no_activityContext)_localctx).list =  ((Things_no_activityContext)_localctx).t3.list; ((Things_no_activityContext)_localctx).type =  ((Things_no_activityContext)_localctx).t3.type; 
				}
				break;
			case T__3:
			case CLASSESOF:
				enterOuterAlt(_localctx, 4);
				{
				setState(163);
				((Things_no_activityContext)_localctx).t4 = classes(0);
				 ((Things_no_activityContext)_localctx).list =  ((Things_no_activityContext)_localctx).t4.list; ((Things_no_activityContext)_localctx).type =  ((Things_no_activityContext)_localctx).t4.type; 
				}
				break;
			case T__4:
			case VERSIONSOF:
				enterOuterAlt(_localctx, 5);
				{
				setState(166);
				((Things_no_activityContext)_localctx).t5 = versions(0);
				 ((Things_no_activityContext)_localctx).list =  ((Things_no_activityContext)_localctx).t5.list; ((Things_no_activityContext)_localctx).type =  ((Things_no_activityContext)_localctx).t5.type; 
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
		public Things_no_objectContext t1;
		public AllObjectsContext t2;
		public FilterContext f;
		public TerminalNode OBJECTSOF() { return getToken(poqlParser.OBJECTSOF, 0); }
		public TerminalNode OPEN_FUNCTION() { return getToken(poqlParser.OPEN_FUNCTION, 0); }
		public TerminalNode CLOSE_FUNCTION() { return getToken(poqlParser.CLOSE_FUNCTION, 0); }
		public Things_no_objectContext things_no_object() {
			return getRuleContext(Things_no_objectContext.class,0);
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
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_objects, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			switch (_input.LA(1)) {
			case OBJECTSOF:
				{
				setState(172);
				match(OBJECTSOF);
				setState(173);
				match(OPEN_FUNCTION);
				setState(174);
				((ObjectsContext)_localctx).t1 = things_no_object();
				setState(175);
				match(CLOSE_FUNCTION);
				 ((ObjectsContext)_localctx).list =  poql.objectsOf(((ObjectsContext)_localctx).t1.list,((ObjectsContext)_localctx).t1.type); ((ObjectsContext)_localctx).type = SLEXMMObject.class; 
				}
				break;
			case T__0:
				{
				setState(178);
				((ObjectsContext)_localctx).t2 = allObjects();
				 ((ObjectsContext)_localctx).list =  ((ObjectsContext)_localctx).t2.list; ((ObjectsContext)_localctx).type =  ((ObjectsContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(189);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
					setState(183);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(184);
					((ObjectsContext)_localctx).f = filter();
					 ((ObjectsContext)_localctx).list =  poql.filter(((ObjectsContext)_localctx).t3.list,((ObjectsContext)_localctx).t3.type,((ObjectsContext)_localctx).f.conditions); ((ObjectsContext)_localctx).type =  ((ObjectsContext)_localctx).t3.type; 
					}
					} 
				}
				setState(191);
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

	public static class CasesContext extends ParserRuleContext {
		public List<Object> list;
		public Class type;
		public CasesContext t3;
		public Things_no_caseContext t1;
		public AllCasesContext t2;
		public FilterContext f;
		public TerminalNode CASESOF() { return getToken(poqlParser.CASESOF, 0); }
		public TerminalNode OPEN_FUNCTION() { return getToken(poqlParser.OPEN_FUNCTION, 0); }
		public TerminalNode CLOSE_FUNCTION() { return getToken(poqlParser.CLOSE_FUNCTION, 0); }
		public Things_no_caseContext things_no_case() {
			return getRuleContext(Things_no_caseContext.class,0);
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
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_cases, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			switch (_input.LA(1)) {
			case CASESOF:
				{
				setState(193);
				match(CASESOF);
				setState(194);
				match(OPEN_FUNCTION);
				setState(195);
				((CasesContext)_localctx).t1 = things_no_case();
				setState(196);
				match(CLOSE_FUNCTION);
				 ((CasesContext)_localctx).list =  poql.casesOf(((CasesContext)_localctx).t1.list,((CasesContext)_localctx).t1.type); ((CasesContext)_localctx).type = SLEXMMCase.class; 
				}
				break;
			case T__1:
				{
				setState(199);
				((CasesContext)_localctx).t2 = allCases();
				 ((CasesContext)_localctx).list =  ((CasesContext)_localctx).t2.list; ((CasesContext)_localctx).type =  ((CasesContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(210);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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
					setState(204);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(205);
					((CasesContext)_localctx).f = filter();
					 ((CasesContext)_localctx).list =  poql.filter(((CasesContext)_localctx).t3.list,((CasesContext)_localctx).t3.type,((CasesContext)_localctx).f.conditions); ((CasesContext)_localctx).type =  ((CasesContext)_localctx).t3.type; 
					}
					} 
				}
				setState(212);
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

	public static class EventsContext extends ParserRuleContext {
		public List<Object> list;
		public Class type;
		public EventsContext t3;
		public Things_no_eventContext t1;
		public AllEventsContext t2;
		public FilterContext f;
		public TerminalNode EVENTSOF() { return getToken(poqlParser.EVENTSOF, 0); }
		public TerminalNode OPEN_FUNCTION() { return getToken(poqlParser.OPEN_FUNCTION, 0); }
		public TerminalNode CLOSE_FUNCTION() { return getToken(poqlParser.CLOSE_FUNCTION, 0); }
		public Things_no_eventContext things_no_event() {
			return getRuleContext(Things_no_eventContext.class,0);
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
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_events, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			switch (_input.LA(1)) {
			case EVENTSOF:
				{
				setState(214);
				match(EVENTSOF);
				setState(215);
				match(OPEN_FUNCTION);
				setState(216);
				((EventsContext)_localctx).t1 = things_no_event();
				setState(217);
				match(CLOSE_FUNCTION);
				 ((EventsContext)_localctx).list =  poql.eventsOf(((EventsContext)_localctx).t1.list,((EventsContext)_localctx).t1.type); ((EventsContext)_localctx).type = SLEXMMEvent.class;
				}
				break;
			case T__2:
				{
				setState(220);
				((EventsContext)_localctx).t2 = allEvents();
				 ((EventsContext)_localctx).list =  ((EventsContext)_localctx).t2.list; ((EventsContext)_localctx).type =  ((EventsContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(231);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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
					setState(225);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(226);
					((EventsContext)_localctx).f = filter();
					 ((EventsContext)_localctx).list =  poql.filter(((EventsContext)_localctx).t3.list,((EventsContext)_localctx).t3.type,((EventsContext)_localctx).f.conditions); ((EventsContext)_localctx).type =  ((EventsContext)_localctx).t3.type; 
					}
					} 
				}
				setState(233);
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

	public static class ClassesContext extends ParserRuleContext {
		public List<Object> list;
		public Class type;
		public ClassesContext t3;
		public Things_no_classContext t1;
		public AllClassesContext t2;
		public FilterContext f;
		public TerminalNode CLASSESOF() { return getToken(poqlParser.CLASSESOF, 0); }
		public TerminalNode OPEN_FUNCTION() { return getToken(poqlParser.OPEN_FUNCTION, 0); }
		public TerminalNode CLOSE_FUNCTION() { return getToken(poqlParser.CLOSE_FUNCTION, 0); }
		public Things_no_classContext things_no_class() {
			return getRuleContext(Things_no_classContext.class,0);
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
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_classes, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			switch (_input.LA(1)) {
			case CLASSESOF:
				{
				setState(235);
				match(CLASSESOF);
				setState(236);
				match(OPEN_FUNCTION);
				setState(237);
				((ClassesContext)_localctx).t1 = things_no_class();
				setState(238);
				match(CLOSE_FUNCTION);
				 ((ClassesContext)_localctx).list =  poql.classesOf(((ClassesContext)_localctx).t1.list,((ClassesContext)_localctx).t1.type); ((ClassesContext)_localctx).type = SLEXMMClass.class;
				}
				break;
			case T__3:
				{
				setState(241);
				((ClassesContext)_localctx).t2 = allClasses();
				 ((ClassesContext)_localctx).list =  ((ClassesContext)_localctx).t2.list; ((ClassesContext)_localctx).type =  ((ClassesContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(252);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
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
					setState(246);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(247);
					((ClassesContext)_localctx).f = filter();
					 ((ClassesContext)_localctx).list =  poql.filter(((ClassesContext)_localctx).t3.list,((ClassesContext)_localctx).t3.type,((ClassesContext)_localctx).f.conditions); ((ClassesContext)_localctx).type =  ((ClassesContext)_localctx).t3.type; 
					}
					} 
				}
				setState(254);
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

	public static class VersionsContext extends ParserRuleContext {
		public List<Object> list;
		public Class type;
		public VersionsContext t3;
		public Things_no_versionContext t1;
		public AllVersionsContext t2;
		public FilterContext f;
		public TerminalNode VERSIONSOF() { return getToken(poqlParser.VERSIONSOF, 0); }
		public TerminalNode OPEN_FUNCTION() { return getToken(poqlParser.OPEN_FUNCTION, 0); }
		public TerminalNode CLOSE_FUNCTION() { return getToken(poqlParser.CLOSE_FUNCTION, 0); }
		public Things_no_versionContext things_no_version() {
			return getRuleContext(Things_no_versionContext.class,0);
		}
		public AllVersionsContext allVersions() {
			return getRuleContext(AllVersionsContext.class,0);
		}
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
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_versions, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			switch (_input.LA(1)) {
			case VERSIONSOF:
				{
				setState(256);
				match(VERSIONSOF);
				setState(257);
				match(OPEN_FUNCTION);
				setState(258);
				((VersionsContext)_localctx).t1 = things_no_version();
				setState(259);
				match(CLOSE_FUNCTION);
				 ((VersionsContext)_localctx).list =  poql.versionsOf(((VersionsContext)_localctx).t1.list,((VersionsContext)_localctx).t1.type); ((VersionsContext)_localctx).type = SLEXMMObjectVersion.class;
				}
				break;
			case T__4:
				{
				setState(262);
				((VersionsContext)_localctx).t2 = allVersions();
				 ((VersionsContext)_localctx).list =  ((VersionsContext)_localctx).t2.list; ((VersionsContext)_localctx).type =  ((VersionsContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(273);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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
					setState(267);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(268);
					((VersionsContext)_localctx).f = filter();
					 ((VersionsContext)_localctx).list =  poql.filter(((VersionsContext)_localctx).t3.list,((VersionsContext)_localctx).t3.type,((VersionsContext)_localctx).f.conditions); ((VersionsContext)_localctx).type =  ((VersionsContext)_localctx).t3.type; 
					}
					} 
				}
				setState(275);
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

	public static class ActivitiesContext extends ParserRuleContext {
		public List<Object> list;
		public Class type;
		public ActivitiesContext t3;
		public Things_no_activityContext t1;
		public AllActivitiesContext t2;
		public FilterContext f;
		public TerminalNode ACTIVITIESOF() { return getToken(poqlParser.ACTIVITIESOF, 0); }
		public TerminalNode OPEN_FUNCTION() { return getToken(poqlParser.OPEN_FUNCTION, 0); }
		public TerminalNode CLOSE_FUNCTION() { return getToken(poqlParser.CLOSE_FUNCTION, 0); }
		public Things_no_activityContext things_no_activity() {
			return getRuleContext(Things_no_activityContext.class,0);
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
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_activities, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			switch (_input.LA(1)) {
			case ACTIVITIESOF:
				{
				setState(277);
				match(ACTIVITIESOF);
				setState(278);
				match(OPEN_FUNCTION);
				setState(279);
				((ActivitiesContext)_localctx).t1 = things_no_activity();
				setState(280);
				match(CLOSE_FUNCTION);
				 ((ActivitiesContext)_localctx).list =  poql.activitiesOf(((ActivitiesContext)_localctx).t1.list,((ActivitiesContext)_localctx).t1.type); ((ActivitiesContext)_localctx).type = SLEXMMActivity.class;
				}
				break;
			case T__5:
				{
				setState(283);
				((ActivitiesContext)_localctx).t2 = allActivities();
				 ((ActivitiesContext)_localctx).list =  ((ActivitiesContext)_localctx).t2.list; ((ActivitiesContext)_localctx).type =  ((ActivitiesContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(294);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
					setState(288);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(289);
					((ActivitiesContext)_localctx).f = filter();
					 ((ActivitiesContext)_localctx).list =  poql.filter(((ActivitiesContext)_localctx).t3.list,((ActivitiesContext)_localctx).t3.type,((ActivitiesContext)_localctx).f.conditions); ((ActivitiesContext)_localctx).type =  ((ActivitiesContext)_localctx).t3.type; 
					}
					} 
				}
				setState(296);
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

	public static class FilterContext extends ParserRuleContext {
		public FilterTree conditions;
		public Filter_expressionContext f;
		public TerminalNode WHERE() { return getToken(poqlParser.WHERE, 0); }
		public Filter_expressionContext filter_expression() {
			return getRuleContext(Filter_expressionContext.class,0);
		}
		public FilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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

	public final FilterContext filter() throws RecognitionException {
		FilterContext _localctx = new FilterContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_filter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			match(WHERE);
			setState(298);
			((FilterContext)_localctx).f = filter_expression(0);
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
		public FilterTree tree;
		public Filter_expressionContext f1;
		public Filter_expressionContext f3;
		public IdContext f5;
		public Token STRING;
		public IdContext f6;
		public IdContext f7;
		public IdContext f8;
		public IdContext f9;
		public IdContext f10;
		public IdContext f11;
		public Filter_expressionContext f2;
		public Filter_expressionContext f4;
		public TerminalNode EQUAL() { return getToken(poqlParser.EQUAL, 0); }
		public TerminalNode STRING() { return getToken(poqlParser.STRING, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode DIFFERENT() { return getToken(poqlParser.DIFFERENT, 0); }
		public TerminalNode EQUAL_OR_GREATER() { return getToken(poqlParser.EQUAL_OR_GREATER, 0); }
		public TerminalNode EQUAL_OR_SMALLER() { return getToken(poqlParser.EQUAL_OR_SMALLER, 0); }
		public TerminalNode GREATER() { return getToken(poqlParser.GREATER, 0); }
		public TerminalNode SMALLER() { return getToken(poqlParser.SMALLER, 0); }
		public TerminalNode CONTAINS() { return getToken(poqlParser.CONTAINS, 0); }
		public TerminalNode AND() { return getToken(poqlParser.AND, 0); }
		public List<Filter_expressionContext> filter_expression() {
			return getRuleContexts(Filter_expressionContext.class);
		}
		public Filter_expressionContext filter_expression(int i) {
			return getRuleContext(Filter_expressionContext.class,i);
		}
		public TerminalNode OR() { return getToken(poqlParser.OR, 0); }
		public Filter_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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

	public final Filter_expressionContext filter_expression() throws RecognitionException {
		return filter_expression(0);
	}

	private Filter_expressionContext filter_expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Filter_expressionContext _localctx = new Filter_expressionContext(_ctx, _parentState);
		Filter_expressionContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_filter_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(337);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(302);
				((Filter_expressionContext)_localctx).f5 = id();
				setState(303);
				match(EQUAL);
				setState(304);
				((Filter_expressionContext)_localctx).STRING = match(STRING);
				 ((Filter_expressionContext)_localctx).tree =  poql.createEqualTerminalFilter(((Filter_expressionContext)_localctx).f5.name,(((Filter_expressionContext)_localctx).STRING!=null?((Filter_expressionContext)_localctx).STRING.getText():null),((Filter_expressionContext)_localctx).f5.att); 
				}
				break;
			case 2:
				{
				setState(307);
				((Filter_expressionContext)_localctx).f6 = id();
				setState(308);
				match(DIFFERENT);
				setState(309);
				((Filter_expressionContext)_localctx).STRING = match(STRING);
				 ((Filter_expressionContext)_localctx).tree =  poql.createDifferentTerminalFilter(((Filter_expressionContext)_localctx).f6.name,(((Filter_expressionContext)_localctx).STRING!=null?((Filter_expressionContext)_localctx).STRING.getText():null),((Filter_expressionContext)_localctx).f6.att); 
				}
				break;
			case 3:
				{
				setState(312);
				((Filter_expressionContext)_localctx).f7 = id();
				setState(313);
				match(EQUAL_OR_GREATER);
				setState(314);
				((Filter_expressionContext)_localctx).STRING = match(STRING);
				 ((Filter_expressionContext)_localctx).tree =  poql.createEqualOrGreaterTerminalFilter(((Filter_expressionContext)_localctx).f7.name,(((Filter_expressionContext)_localctx).STRING!=null?((Filter_expressionContext)_localctx).STRING.getText():null),((Filter_expressionContext)_localctx).f7.att); 
				}
				break;
			case 4:
				{
				setState(317);
				((Filter_expressionContext)_localctx).f8 = id();
				setState(318);
				match(EQUAL_OR_SMALLER);
				setState(319);
				((Filter_expressionContext)_localctx).STRING = match(STRING);
				 ((Filter_expressionContext)_localctx).tree =  poql.createEqualOrSmallerTerminalFilter(((Filter_expressionContext)_localctx).f8.name,(((Filter_expressionContext)_localctx).STRING!=null?((Filter_expressionContext)_localctx).STRING.getText():null),((Filter_expressionContext)_localctx).f8.att); 
				}
				break;
			case 5:
				{
				setState(322);
				((Filter_expressionContext)_localctx).f9 = id();
				setState(323);
				match(GREATER);
				setState(324);
				((Filter_expressionContext)_localctx).STRING = match(STRING);
				 ((Filter_expressionContext)_localctx).tree =  poql.createGreaterTerminalFilter(((Filter_expressionContext)_localctx).f9.name,(((Filter_expressionContext)_localctx).STRING!=null?((Filter_expressionContext)_localctx).STRING.getText():null),((Filter_expressionContext)_localctx).f9.att); 
				}
				break;
			case 6:
				{
				setState(327);
				((Filter_expressionContext)_localctx).f10 = id();
				setState(328);
				match(SMALLER);
				setState(329);
				((Filter_expressionContext)_localctx).STRING = match(STRING);
				 ((Filter_expressionContext)_localctx).tree =  poql.createSmallerTerminalFilter(((Filter_expressionContext)_localctx).f10.name,(((Filter_expressionContext)_localctx).STRING!=null?((Filter_expressionContext)_localctx).STRING.getText():null),((Filter_expressionContext)_localctx).f10.att); 
				}
				break;
			case 7:
				{
				setState(332);
				((Filter_expressionContext)_localctx).f11 = id();
				setState(333);
				match(CONTAINS);
				setState(334);
				((Filter_expressionContext)_localctx).STRING = match(STRING);
				 ((Filter_expressionContext)_localctx).tree =  poql.createContainsTerminalFilter(((Filter_expressionContext)_localctx).f11.name,(((Filter_expressionContext)_localctx).STRING!=null?((Filter_expressionContext)_localctx).STRING.getText():null),((Filter_expressionContext)_localctx).f11.att); 
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(351);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(349);
					switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
					case 1:
						{
						_localctx = new Filter_expressionContext(_parentctx, _parentState);
						_localctx.f1 = _prevctx;
						_localctx.f1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_filter_expression);
						setState(339);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(340);
						match(AND);
						setState(341);
						((Filter_expressionContext)_localctx).f2 = filter_expression(10);
						 ((Filter_expressionContext)_localctx).tree =  poql.createAndNode(((Filter_expressionContext)_localctx).f1.tree,((Filter_expressionContext)_localctx).f2.tree); 
						}
						break;
					case 2:
						{
						_localctx = new Filter_expressionContext(_parentctx, _parentState);
						_localctx.f3 = _prevctx;
						_localctx.f3 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_filter_expression);
						setState(344);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(345);
						match(OR);
						setState(346);
						((Filter_expressionContext)_localctx).f4 = filter_expression(9);
						 ((Filter_expressionContext)_localctx).tree =  poql.createOrNode(((Filter_expressionContext)_localctx).f3.tree,((Filter_expressionContext)_localctx).f4.tree); 
						}
						break;
					}
					} 
				}
				setState(353);
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

	public static class IdContext extends ParserRuleContext {
		public String name;
		public boolean att;
		public Token IDATT;
		public Token IDNOATT;
		public TerminalNode IDATT() { return getToken(poqlParser.IDATT, 0); }
		public TerminalNode IDNOATT() { return getToken(poqlParser.IDNOATT, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitId(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_id);
		try {
			setState(358);
			switch (_input.LA(1)) {
			case IDATT:
				enterOuterAlt(_localctx, 1);
				{
				setState(354);
				((IdContext)_localctx).IDATT = match(IDATT);
				((IdContext)_localctx).name =  (((IdContext)_localctx).IDATT!=null?((IdContext)_localctx).IDATT.getText():null); ((IdContext)_localctx).att =  true;
				}
				break;
			case IDNOATT:
				enterOuterAlt(_localctx, 2);
				{
				setState(356);
				((IdContext)_localctx).IDNOATT = match(IDNOATT);
				((IdContext)_localctx).name =  (((IdContext)_localctx).IDNOATT!=null?((IdContext)_localctx).IDNOATT.getText():null); ((IdContext)_localctx).att =  false;
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
		enterRule(_localctx, 34, RULE_allObjects);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			match(T__0);
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
		enterRule(_localctx, 36, RULE_allCases);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(363);
			match(T__1);
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
		enterRule(_localctx, 38, RULE_allEvents);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			match(T__2);
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
		enterRule(_localctx, 40, RULE_allClasses);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			match(T__3);
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
		enterRule(_localctx, 42, RULE_allVersions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			match(T__4);
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
		enterRule(_localctx, 44, RULE_allActivities);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			match(T__5);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 8:
			return objects_sempred((ObjectsContext)_localctx, predIndex);
		case 9:
			return cases_sempred((CasesContext)_localctx, predIndex);
		case 10:
			return events_sempred((EventsContext)_localctx, predIndex);
		case 11:
			return classes_sempred((ClassesContext)_localctx, predIndex);
		case 12:
			return versions_sempred((VersionsContext)_localctx, predIndex);
		case 13:
			return activities_sempred((ActivitiesContext)_localctx, predIndex);
		case 15:
			return filter_expression_sempred((Filter_expressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean objects_sempred(ObjectsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
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
	private boolean filter_expression_sempred(Filter_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 9);
		case 7:
			return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\36\u017d\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\5\3F\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\5\4W\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\5\5h\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\5\6y\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\5\7\u008a\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\5\b\u009b\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\5\t\u00ac\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n"+
		"\u00b8\n\n\3\n\3\n\3\n\3\n\7\n\u00be\n\n\f\n\16\n\u00c1\13\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00cd\n\13\3\13\3\13\3\13"+
		"\3\13\7\13\u00d3\n\13\f\13\16\13\u00d6\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\5\f\u00e2\n\f\3\f\3\f\3\f\3\f\7\f\u00e8\n\f\f\f\16\f\u00eb"+
		"\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00f7\n\r\3\r\3\r\3"+
		"\r\3\r\7\r\u00fd\n\r\f\r\16\r\u0100\13\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\5\16\u010c\n\16\3\16\3\16\3\16\3\16\7\16\u0112\n"+
		"\16\f\16\16\16\u0115\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\5\17\u0121\n\17\3\17\3\17\3\17\3\17\7\17\u0127\n\17\f\17\16\17\u012a"+
		"\13\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21"+
		"\u0154\n\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u0160"+
		"\n\21\f\21\16\21\u0163\13\21\3\22\3\22\3\22\3\22\5\22\u0169\n\22\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27"+
		"\3\30\3\30\3\30\3\30\2\t\22\24\26\30\32\34 \31\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\2\2\u0197\2\60\3\2\2\2\4E\3\2\2\2\6V\3\2\2\2"+
		"\bg\3\2\2\2\nx\3\2\2\2\f\u0089\3\2\2\2\16\u009a\3\2\2\2\20\u00ab\3\2\2"+
		"\2\22\u00b7\3\2\2\2\24\u00cc\3\2\2\2\26\u00e1\3\2\2\2\30\u00f6\3\2\2\2"+
		"\32\u010b\3\2\2\2\34\u0120\3\2\2\2\36\u012b\3\2\2\2 \u0153\3\2\2\2\"\u0168"+
		"\3\2\2\2$\u016a\3\2\2\2&\u016d\3\2\2\2(\u0170\3\2\2\2*\u0173\3\2\2\2,"+
		"\u0176\3\2\2\2.\u0179\3\2\2\2\60\61\5\4\3\2\61\62\b\2\1\2\62\3\3\2\2\2"+
		"\63\64\5\24\13\2\64\65\b\3\1\2\65F\3\2\2\2\66\67\5\22\n\2\678\b\3\1\2"+
		"8F\3\2\2\29:\5\26\f\2:;\b\3\1\2;F\3\2\2\2<=\5\30\r\2=>\b\3\1\2>F\3\2\2"+
		"\2?@\5\32\16\2@A\b\3\1\2AF\3\2\2\2BC\5\34\17\2CD\b\3\1\2DF\3\2\2\2E\63"+
		"\3\2\2\2E\66\3\2\2\2E9\3\2\2\2E<\3\2\2\2E?\3\2\2\2EB\3\2\2\2F\5\3\2\2"+
		"\2GH\5\24\13\2HI\b\4\1\2IW\3\2\2\2JK\5\26\f\2KL\b\4\1\2LW\3\2\2\2MN\5"+
		"\30\r\2NO\b\4\1\2OW\3\2\2\2PQ\5\32\16\2QR\b\4\1\2RW\3\2\2\2ST\5\34\17"+
		"\2TU\b\4\1\2UW\3\2\2\2VG\3\2\2\2VJ\3\2\2\2VM\3\2\2\2VP\3\2\2\2VS\3\2\2"+
		"\2W\7\3\2\2\2XY\5\22\n\2YZ\b\5\1\2Zh\3\2\2\2[\\\5\26\f\2\\]\b\5\1\2]h"+
		"\3\2\2\2^_\5\30\r\2_`\b\5\1\2`h\3\2\2\2ab\5\32\16\2bc\b\5\1\2ch\3\2\2"+
		"\2de\5\34\17\2ef\b\5\1\2fh\3\2\2\2gX\3\2\2\2g[\3\2\2\2g^\3\2\2\2ga\3\2"+
		"\2\2gd\3\2\2\2h\t\3\2\2\2ij\5\24\13\2jk\b\6\1\2ky\3\2\2\2lm\5\22\n\2m"+
		"n\b\6\1\2ny\3\2\2\2op\5\30\r\2pq\b\6\1\2qy\3\2\2\2rs\5\32\16\2st\b\6\1"+
		"\2ty\3\2\2\2uv\5\34\17\2vw\b\6\1\2wy\3\2\2\2xi\3\2\2\2xl\3\2\2\2xo\3\2"+
		"\2\2xr\3\2\2\2xu\3\2\2\2y\13\3\2\2\2z{\5\24\13\2{|\b\7\1\2|\u008a\3\2"+
		"\2\2}~\5\22\n\2~\177\b\7\1\2\177\u008a\3\2\2\2\u0080\u0081\5\26\f\2\u0081"+
		"\u0082\b\7\1\2\u0082\u008a\3\2\2\2\u0083\u0084\5\32\16\2\u0084\u0085\b"+
		"\7\1\2\u0085\u008a\3\2\2\2\u0086\u0087\5\34\17\2\u0087\u0088\b\7\1\2\u0088"+
		"\u008a\3\2\2\2\u0089z\3\2\2\2\u0089}\3\2\2\2\u0089\u0080\3\2\2\2\u0089"+
		"\u0083\3\2\2\2\u0089\u0086\3\2\2\2\u008a\r\3\2\2\2\u008b\u008c\5\24\13"+
		"\2\u008c\u008d\b\b\1\2\u008d\u009b\3\2\2\2\u008e\u008f\5\22\n\2\u008f"+
		"\u0090\b\b\1\2\u0090\u009b\3\2\2\2\u0091\u0092\5\26\f\2\u0092\u0093\b"+
		"\b\1\2\u0093\u009b\3\2\2\2\u0094\u0095\5\30\r\2\u0095\u0096\b\b\1\2\u0096"+
		"\u009b\3\2\2\2\u0097\u0098\5\34\17\2\u0098\u0099\b\b\1\2\u0099\u009b\3"+
		"\2\2\2\u009a\u008b\3\2\2\2\u009a\u008e\3\2\2\2\u009a\u0091\3\2\2\2\u009a"+
		"\u0094\3\2\2\2\u009a\u0097\3\2\2\2\u009b\17\3\2\2\2\u009c\u009d\5\24\13"+
		"\2\u009d\u009e\b\t\1\2\u009e\u00ac\3\2\2\2\u009f\u00a0\5\22\n\2\u00a0"+
		"\u00a1\b\t\1\2\u00a1\u00ac\3\2\2\2\u00a2\u00a3\5\26\f\2\u00a3\u00a4\b"+
		"\t\1\2\u00a4\u00ac\3\2\2\2\u00a5\u00a6\5\30\r\2\u00a6\u00a7\b\t\1\2\u00a7"+
		"\u00ac\3\2\2\2\u00a8\u00a9\5\32\16\2\u00a9\u00aa\b\t\1\2\u00aa\u00ac\3"+
		"\2\2\2\u00ab\u009c\3\2\2\2\u00ab\u009f\3\2\2\2\u00ab\u00a2\3\2\2\2\u00ab"+
		"\u00a5\3\2\2\2\u00ab\u00a8\3\2\2\2\u00ac\21\3\2\2\2\u00ad\u00ae\b\n\1"+
		"\2\u00ae\u00af\7\n\2\2\u00af\u00b0\7\17\2\2\u00b0\u00b1\5\6\4\2\u00b1"+
		"\u00b2\7\20\2\2\u00b2\u00b3\b\n\1\2\u00b3\u00b8\3\2\2\2\u00b4\u00b5\5"+
		"$\23\2\u00b5\u00b6\b\n\1\2\u00b6\u00b8\3\2\2\2\u00b7\u00ad\3\2\2\2\u00b7"+
		"\u00b4\3\2\2\2\u00b8\u00bf\3\2\2\2\u00b9\u00ba\f\3\2\2\u00ba\u00bb\5\36"+
		"\20\2\u00bb\u00bc\b\n\1\2\u00bc\u00be\3\2\2\2\u00bd\u00b9\3\2\2\2\u00be"+
		"\u00c1\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\23\3\2\2"+
		"\2\u00c1\u00bf\3\2\2\2\u00c2\u00c3\b\13\1\2\u00c3\u00c4\7\t\2\2\u00c4"+
		"\u00c5\7\17\2\2\u00c5\u00c6\5\b\5\2\u00c6\u00c7\7\20\2\2\u00c7\u00c8\b"+
		"\13\1\2\u00c8\u00cd\3\2\2\2\u00c9\u00ca\5&\24\2\u00ca\u00cb\b\13\1\2\u00cb"+
		"\u00cd\3\2\2\2\u00cc\u00c2\3\2\2\2\u00cc\u00c9\3\2\2\2\u00cd\u00d4\3\2"+
		"\2\2\u00ce\u00cf\f\3\2\2\u00cf\u00d0\5\36\20\2\u00d0\u00d1\b\13\1\2\u00d1"+
		"\u00d3\3\2\2\2\u00d2\u00ce\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2"+
		"\2\2\u00d4\u00d5\3\2\2\2\u00d5\25\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00d8"+
		"\b\f\1\2\u00d8\u00d9\7\13\2\2\u00d9\u00da\7\17\2\2\u00da\u00db\5\n\6\2"+
		"\u00db\u00dc\7\20\2\2\u00dc\u00dd\b\f\1\2\u00dd\u00e2\3\2\2\2\u00de\u00df"+
		"\5(\25\2\u00df\u00e0\b\f\1\2\u00e0\u00e2\3\2\2\2\u00e1\u00d7\3\2\2\2\u00e1"+
		"\u00de\3\2\2\2\u00e2\u00e9\3\2\2\2\u00e3\u00e4\f\3\2\2\u00e4\u00e5\5\36"+
		"\20\2\u00e5\u00e6\b\f\1\2\u00e6\u00e8\3\2\2\2\u00e7\u00e3\3\2\2\2\u00e8"+
		"\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\27\3\2\2"+
		"\2\u00eb\u00e9\3\2\2\2\u00ec\u00ed\b\r\1\2\u00ed\u00ee\7\f\2\2\u00ee\u00ef"+
		"\7\17\2\2\u00ef\u00f0\5\f\7\2\u00f0\u00f1\7\20\2\2\u00f1\u00f2\b\r\1\2"+
		"\u00f2\u00f7\3\2\2\2\u00f3\u00f4\5*\26\2\u00f4\u00f5\b\r\1\2\u00f5\u00f7"+
		"\3\2\2\2\u00f6\u00ec\3\2\2\2\u00f6\u00f3\3\2\2\2\u00f7\u00fe\3\2\2\2\u00f8"+
		"\u00f9\f\3\2\2\u00f9\u00fa\5\36\20\2\u00fa\u00fb\b\r\1\2\u00fb\u00fd\3"+
		"\2\2\2\u00fc\u00f8\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe"+
		"\u00ff\3\2\2\2\u00ff\31\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0102\b\16\1"+
		"\2\u0102\u0103\7\r\2\2\u0103\u0104\7\17\2\2\u0104\u0105\5\16\b\2\u0105"+
		"\u0106\7\20\2\2\u0106\u0107\b\16\1\2\u0107\u010c\3\2\2\2\u0108\u0109\5"+
		",\27\2\u0109\u010a\b\16\1\2\u010a\u010c\3\2\2\2\u010b\u0101\3\2\2\2\u010b"+
		"\u0108\3\2\2\2\u010c\u0113\3\2\2\2\u010d\u010e\f\3\2\2\u010e\u010f\5\36"+
		"\20\2\u010f\u0110\b\16\1\2\u0110\u0112\3\2\2\2\u0111\u010d\3\2\2\2\u0112"+
		"\u0115\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\33\3\2\2"+
		"\2\u0115\u0113\3\2\2\2\u0116\u0117\b\17\1\2\u0117\u0118\7\16\2\2\u0118"+
		"\u0119\7\17\2\2\u0119\u011a\5\20\t\2\u011a\u011b\7\20\2\2\u011b\u011c"+
		"\b\17\1\2\u011c\u0121\3\2\2\2\u011d\u011e\5.\30\2\u011e\u011f\b\17\1\2"+
		"\u011f\u0121\3\2\2\2\u0120\u0116\3\2\2\2\u0120\u011d\3\2\2\2\u0121\u0128"+
		"\3\2\2\2\u0122\u0123\f\3\2\2\u0123\u0124\5\36\20\2\u0124\u0125\b\17\1"+
		"\2\u0125\u0127\3\2\2\2\u0126\u0122\3\2\2\2\u0127\u012a\3\2\2\2\u0128\u0126"+
		"\3\2\2\2\u0128\u0129\3\2\2\2\u0129\35\3\2\2\2\u012a\u0128\3\2\2\2\u012b"+
		"\u012c\7\21\2\2\u012c\u012d\5 \21\2\u012d\u012e\b\20\1\2\u012e\37\3\2"+
		"\2\2\u012f\u0130\b\21\1\2\u0130\u0131\5\"\22\2\u0131\u0132\7\22\2\2\u0132"+
		"\u0133\7\33\2\2\u0133\u0134\b\21\1\2\u0134\u0154\3\2\2\2\u0135\u0136\5"+
		"\"\22\2\u0136\u0137\7\23\2\2\u0137\u0138\7\33\2\2\u0138\u0139\b\21\1\2"+
		"\u0139\u0154\3\2\2\2\u013a\u013b\5\"\22\2\u013b\u013c\7\24\2\2\u013c\u013d"+
		"\7\33\2\2\u013d\u013e\b\21\1\2\u013e\u0154\3\2\2\2\u013f\u0140\5\"\22"+
		"\2\u0140\u0141\7\25\2\2\u0141\u0142\7\33\2\2\u0142\u0143\b\21\1\2\u0143"+
		"\u0154\3\2\2\2\u0144\u0145\5\"\22\2\u0145\u0146\7\26\2\2\u0146\u0147\7"+
		"\33\2\2\u0147\u0148\b\21\1\2\u0148\u0154\3\2\2\2\u0149\u014a\5\"\22\2"+
		"\u014a\u014b\7\27\2\2\u014b\u014c\7\33\2\2\u014c\u014d\b\21\1\2\u014d"+
		"\u0154\3\2\2\2\u014e\u014f\5\"\22\2\u014f\u0150\7\30\2\2\u0150\u0151\7"+
		"\33\2\2\u0151\u0152\b\21\1\2\u0152\u0154\3\2\2\2\u0153\u012f\3\2\2\2\u0153"+
		"\u0135\3\2\2\2\u0153\u013a\3\2\2\2\u0153\u013f\3\2\2\2\u0153\u0144\3\2"+
		"\2\2\u0153\u0149\3\2\2\2\u0153\u014e\3\2\2\2\u0154\u0161\3\2\2\2\u0155"+
		"\u0156\f\13\2\2\u0156\u0157\7\31\2\2\u0157\u0158\5 \21\f\u0158\u0159\b"+
		"\21\1\2\u0159\u0160\3\2\2\2\u015a\u015b\f\n\2\2\u015b\u015c\7\32\2\2\u015c"+
		"\u015d\5 \21\13\u015d\u015e\b\21\1\2\u015e\u0160\3\2\2\2\u015f\u0155\3"+
		"\2\2\2\u015f\u015a\3\2\2\2\u0160\u0163\3\2\2\2\u0161\u015f\3\2\2\2\u0161"+
		"\u0162\3\2\2\2\u0162!\3\2\2\2\u0163\u0161\3\2\2\2\u0164\u0165\7\34\2\2"+
		"\u0165\u0169\b\22\1\2\u0166\u0167\7\35\2\2\u0167\u0169\b\22\1\2\u0168"+
		"\u0164\3\2\2\2\u0168\u0166\3\2\2\2\u0169#\3\2\2\2\u016a\u016b\7\3\2\2"+
		"\u016b\u016c\b\23\1\2\u016c%\3\2\2\2\u016d\u016e\7\4\2\2\u016e\u016f\b"+
		"\24\1\2\u016f\'\3\2\2\2\u0170\u0171\7\5\2\2\u0171\u0172\b\25\1\2\u0172"+
		")\3\2\2\2\u0173\u0174\7\6\2\2\u0174\u0175\b\26\1\2\u0175+\3\2\2\2\u0176"+
		"\u0177\7\7\2\2\u0177\u0178\b\27\1\2\u0178-\3\2\2\2\u0179\u017a\7\b\2\2"+
		"\u017a\u017b\b\30\1\2\u017b/\3\2\2\2\31EVgx\u0089\u009a\u00ab\u00b7\u00bf"+
		"\u00cc\u00d4\u00e1\u00e9\u00f6\u00fe\u010b\u0113\u0120\u0128\u0153\u015f"+
		"\u0161\u0168";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}