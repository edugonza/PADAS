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
		CASESOF=1, OBJECTSOF=2, EVENTSOF=3, CLASSESOF=4, VERSIONSOF=5, ACTIVITIESOF=6, 
		VERSIONS_RELATED_TO=7, ALLOBJECTS=8, ALLCASES=9, ALLEVENTS=10, ALLCLASSES=11, 
		ALLVERSIONS=12, ALLACTIVITIES=13, OPEN_PARENTHESIS=14, CLOSE_PARENTHESIS=15, 
		WHERE=16, EQUAL=17, DIFFERENT=18, EQUAL_OR_GREATER=19, EQUAL_OR_SMALLER=20, 
		GREATER=21, SMALLER=22, CONTAINS=23, AND=24, OR=25, NOT=26, CHANGED=27, 
		FROM=28, TO=29, STRING=30, IDATT=31, IDNOATT=32, WS=33;
	public static final int
		RULE_prog = 0, RULE_things = 1, RULE_objects = 2, RULE_cases = 3, RULE_events = 4, 
		RULE_classes = 5, RULE_versions = 6, RULE_activities = 7, RULE_filter = 8, 
		RULE_filter_versions = 9, RULE_filter_expression = 10, RULE_filter_terminal = 11, 
		RULE_filter_expression_versions = 12, RULE_filter_terminal_changed = 13, 
		RULE_id = 14, RULE_allObjects = 15, RULE_allCases = 16, RULE_allEvents = 17, 
		RULE_allClasses = 18, RULE_allVersions = 19, RULE_allActivities = 20;
	public static final String[] ruleNames = {
		"prog", "things", "objects", "cases", "events", "classes", "versions", 
		"activities", "filter", "filter_versions", "filter_expression", "filter_terminal", 
		"filter_expression_versions", "filter_terminal_changed", "id", "allObjects", 
		"allCases", "allEvents", "allClasses", "allVersions", "allActivities"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "'('", "')'", null, "'=='", "'<>'", "'=>'", "'=<'", "'>'", 
		"'<'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "CASESOF", "OBJECTSOF", "EVENTSOF", "CLASSESOF", "VERSIONSOF", "ACTIVITIESOF", 
		"VERSIONS_RELATED_TO", "ALLOBJECTS", "ALLCASES", "ALLEVENTS", "ALLCLASSES", 
		"ALLVERSIONS", "ALLACTIVITIES", "OPEN_PARENTHESIS", "CLOSE_PARENTHESIS", 
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

	  @Override
	  public void notifyErrorListeners(Token offendingToken, String msg, RecognitionException ex)
	  {
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
			setState(42);
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
			setState(63);
			switch (_input.LA(1)) {
			case CASESOF:
			case ALLCASES:
				enterOuterAlt(_localctx, 1);
				{
				setState(45);
				((ThingsContext)_localctx).t1 = cases(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t1.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t1.type; 
				}
				break;
			case OBJECTSOF:
			case ALLOBJECTS:
				enterOuterAlt(_localctx, 2);
				{
				setState(48);
				((ThingsContext)_localctx).t2 = objects(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t2.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t2.type; 
				}
				break;
			case EVENTSOF:
			case ALLEVENTS:
				enterOuterAlt(_localctx, 3);
				{
				setState(51);
				((ThingsContext)_localctx).t3 = events(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t3.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t3.type; 
				}
				break;
			case CLASSESOF:
			case ALLCLASSES:
				enterOuterAlt(_localctx, 4);
				{
				setState(54);
				((ThingsContext)_localctx).t4 = classes(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t4.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t4.type; 
				}
				break;
			case VERSIONSOF:
			case VERSIONS_RELATED_TO:
			case ALLVERSIONS:
				enterOuterAlt(_localctx, 5);
				{
				setState(57);
				((ThingsContext)_localctx).t5 = versions(0);
				 ((ThingsContext)_localctx).list =  ((ThingsContext)_localctx).t5.list; ((ThingsContext)_localctx).type =  ((ThingsContext)_localctx).t5.type; 
				}
				break;
			case ACTIVITIESOF:
			case ALLACTIVITIES:
				enterOuterAlt(_localctx, 6);
				{
				setState(60);
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
			setState(75);
			switch (_input.LA(1)) {
			case OBJECTSOF:
				{
				setState(66);
				match(OBJECTSOF);
				setState(67);
				match(OPEN_PARENTHESIS);
				setState(68);
				((ObjectsContext)_localctx).t1 = things();
				setState(69);
				match(CLOSE_PARENTHESIS);
				 ((ObjectsContext)_localctx).list =  poql.objectsOf(((ObjectsContext)_localctx).t1.list,((ObjectsContext)_localctx).t1.type); ((ObjectsContext)_localctx).type = SLEXMMObject.class; 
				}
				break;
			case ALLOBJECTS:
				{
				setState(72);
				((ObjectsContext)_localctx).t2 = allObjects();
				 ((ObjectsContext)_localctx).list =  ((ObjectsContext)_localctx).t2.list; ((ObjectsContext)_localctx).type =  ((ObjectsContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(83);
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
					setState(77);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(78);
					((ObjectsContext)_localctx).f = filter();
					 ((ObjectsContext)_localctx).list =  poql.filter(((ObjectsContext)_localctx).t3.list,((ObjectsContext)_localctx).t3.type,((ObjectsContext)_localctx).f.conditions); ((ObjectsContext)_localctx).type =  ((ObjectsContext)_localctx).t3.type; 
					}
					} 
				}
				setState(85);
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
			setState(96);
			switch (_input.LA(1)) {
			case CASESOF:
				{
				setState(87);
				match(CASESOF);
				setState(88);
				match(OPEN_PARENTHESIS);
				setState(89);
				((CasesContext)_localctx).t1 = things();
				setState(90);
				match(CLOSE_PARENTHESIS);
				 ((CasesContext)_localctx).list =  poql.casesOf(((CasesContext)_localctx).t1.list,((CasesContext)_localctx).t1.type); ((CasesContext)_localctx).type = SLEXMMCase.class; 
				}
				break;
			case ALLCASES:
				{
				setState(93);
				((CasesContext)_localctx).t2 = allCases();
				 ((CasesContext)_localctx).list =  ((CasesContext)_localctx).t2.list; ((CasesContext)_localctx).type =  ((CasesContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(104);
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
					setState(98);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(99);
					((CasesContext)_localctx).f = filter();
					 ((CasesContext)_localctx).list =  poql.filter(((CasesContext)_localctx).t3.list,((CasesContext)_localctx).t3.type,((CasesContext)_localctx).f.conditions); ((CasesContext)_localctx).type =  ((CasesContext)_localctx).t3.type; 
					}
					} 
				}
				setState(106);
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
			setState(117);
			switch (_input.LA(1)) {
			case EVENTSOF:
				{
				setState(108);
				match(EVENTSOF);
				setState(109);
				match(OPEN_PARENTHESIS);
				setState(110);
				((EventsContext)_localctx).t1 = things();
				setState(111);
				match(CLOSE_PARENTHESIS);
				 ((EventsContext)_localctx).list =  poql.eventsOf(((EventsContext)_localctx).t1.list,((EventsContext)_localctx).t1.type); ((EventsContext)_localctx).type = SLEXMMEvent.class;
				}
				break;
			case ALLEVENTS:
				{
				setState(114);
				((EventsContext)_localctx).t2 = allEvents();
				 ((EventsContext)_localctx).list =  ((EventsContext)_localctx).t2.list; ((EventsContext)_localctx).type =  ((EventsContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(125);
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
					setState(119);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(120);
					((EventsContext)_localctx).f = filter();
					 ((EventsContext)_localctx).list =  poql.filter(((EventsContext)_localctx).t3.list,((EventsContext)_localctx).t3.type,((EventsContext)_localctx).f.conditions); ((EventsContext)_localctx).type =  ((EventsContext)_localctx).t3.type; 
					}
					} 
				}
				setState(127);
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
			setState(138);
			switch (_input.LA(1)) {
			case CLASSESOF:
				{
				setState(129);
				match(CLASSESOF);
				setState(130);
				match(OPEN_PARENTHESIS);
				setState(131);
				((ClassesContext)_localctx).t1 = things();
				setState(132);
				match(CLOSE_PARENTHESIS);
				 ((ClassesContext)_localctx).list =  poql.classesOf(((ClassesContext)_localctx).t1.list,((ClassesContext)_localctx).t1.type); ((ClassesContext)_localctx).type = SLEXMMClass.class;
				}
				break;
			case ALLCLASSES:
				{
				setState(135);
				((ClassesContext)_localctx).t2 = allClasses();
				 ((ClassesContext)_localctx).list =  ((ClassesContext)_localctx).t2.list; ((ClassesContext)_localctx).type =  ((ClassesContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(146);
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
					setState(140);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(141);
					((ClassesContext)_localctx).f = filter();
					 ((ClassesContext)_localctx).list =  poql.filter(((ClassesContext)_localctx).t3.list,((ClassesContext)_localctx).t3.type,((ClassesContext)_localctx).f.conditions); ((ClassesContext)_localctx).type =  ((ClassesContext)_localctx).t3.type; 
					}
					} 
				}
				setState(148);
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
		public Filter_versionsContext f;
		public TerminalNode VERSIONSOF() { return getToken(poqlParser.VERSIONSOF, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(poqlParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(poqlParser.CLOSE_PARENTHESIS, 0); }
		public ThingsContext things() {
			return getRuleContext(ThingsContext.class,0);
		}
		public AllVersionsContext allVersions() {
			return getRuleContext(AllVersionsContext.class,0);
		}
		public TerminalNode VERSIONS_RELATED_TO() { return getToken(poqlParser.VERSIONS_RELATED_TO, 0); }
		public VersionsContext versions() {
			return getRuleContext(VersionsContext.class,0);
		}
		public Filter_versionsContext filter_versions() {
			return getRuleContext(Filter_versionsContext.class,0);
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
			setState(165);
			switch (_input.LA(1)) {
			case VERSIONSOF:
				{
				setState(150);
				match(VERSIONSOF);
				setState(151);
				match(OPEN_PARENTHESIS);
				setState(152);
				((VersionsContext)_localctx).t1 = things();
				setState(153);
				match(CLOSE_PARENTHESIS);
				 ((VersionsContext)_localctx).list =  poql.versionsOf(((VersionsContext)_localctx).t1.list,((VersionsContext)_localctx).t1.type); ((VersionsContext)_localctx).type = SLEXMMObjectVersion.class;
				}
				break;
			case ALLVERSIONS:
				{
				setState(156);
				((VersionsContext)_localctx).t2 = allVersions();
				 ((VersionsContext)_localctx).list =  ((VersionsContext)_localctx).t2.list; ((VersionsContext)_localctx).type =  ((VersionsContext)_localctx).t2.type; 
				}
				break;
			case VERSIONS_RELATED_TO:
				{
				setState(159);
				match(VERSIONS_RELATED_TO);
				setState(160);
				match(OPEN_PARENTHESIS);
				setState(161);
				((VersionsContext)_localctx).t4 = versions(0);
				setState(162);
				match(CLOSE_PARENTHESIS);
				 ((VersionsContext)_localctx).list =  poql.versionsRelatedTo(((VersionsContext)_localctx).t4.list,((VersionsContext)_localctx).t4.type); ((VersionsContext)_localctx).type = SLEXMMObjectVersion.class; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(173);
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
					setState(167);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(168);
					((VersionsContext)_localctx).f = filter_versions();
					 ((VersionsContext)_localctx).list =  poql.filter(((VersionsContext)_localctx).t3.list,((VersionsContext)_localctx).t3.type,((VersionsContext)_localctx).f.conditions); ((VersionsContext)_localctx).type =  ((VersionsContext)_localctx).t3.type; 
					}
					} 
				}
				setState(175);
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
			setState(186);
			switch (_input.LA(1)) {
			case ACTIVITIESOF:
				{
				setState(177);
				match(ACTIVITIESOF);
				setState(178);
				match(OPEN_PARENTHESIS);
				setState(179);
				((ActivitiesContext)_localctx).t1 = things();
				setState(180);
				match(CLOSE_PARENTHESIS);
				 ((ActivitiesContext)_localctx).list =  poql.activitiesOf(((ActivitiesContext)_localctx).t1.list,((ActivitiesContext)_localctx).t1.type); ((ActivitiesContext)_localctx).type = SLEXMMActivity.class;
				}
				break;
			case ALLACTIVITIES:
				{
				setState(183);
				((ActivitiesContext)_localctx).t2 = allActivities();
				 ((ActivitiesContext)_localctx).list =  ((ActivitiesContext)_localctx).t2.list; ((ActivitiesContext)_localctx).type =  ((ActivitiesContext)_localctx).t2.type; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(194);
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
					setState(188);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(189);
					((ActivitiesContext)_localctx).f = filter();
					 ((ActivitiesContext)_localctx).list =  poql.filter(((ActivitiesContext)_localctx).t3.list,((ActivitiesContext)_localctx).t3.type,((ActivitiesContext)_localctx).f.conditions); ((ActivitiesContext)_localctx).type =  ((ActivitiesContext)_localctx).t3.type; 
					}
					} 
				}
				setState(196);
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
		enterRule(_localctx, 16, RULE_filter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			match(WHERE);
			setState(198);
			((FilterContext)_localctx).f = filter_expression();
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

	public static class Filter_versionsContext extends ParserRuleContext {
		public FilterTree conditions;
		public Filter_expression_versionsContext f;
		public TerminalNode WHERE() { return getToken(poqlParser.WHERE, 0); }
		public Filter_expression_versionsContext filter_expression_versions() {
			return getRuleContext(Filter_expression_versionsContext.class,0);
		}
		public Filter_versionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter_versions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterFilter_versions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitFilter_versions(this);
		}
	}

	public final Filter_versionsContext filter_versions() throws RecognitionException {
		Filter_versionsContext _localctx = new Filter_versionsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_filter_versions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(WHERE);
			setState(202);
			((Filter_versionsContext)_localctx).f = filter_expression_versions();
			 ((Filter_versionsContext)_localctx).conditions =  ((Filter_versionsContext)_localctx).f.tree; 
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
		public Filter_expressionContext f0;
		public Filter_expressionContext f1;
		public Filter_expressionContext f2;
		public Filter_expressionContext f3;
		public Filter_expressionContext f4;
		public Filter_terminalContext f5;
		public TerminalNode NOT() { return getToken(poqlParser.NOT, 0); }
		public List<Filter_expressionContext> filter_expression() {
			return getRuleContexts(Filter_expressionContext.class);
		}
		public Filter_expressionContext filter_expression(int i) {
			return getRuleContext(Filter_expressionContext.class,i);
		}
		public TerminalNode OPEN_PARENTHESIS() { return getToken(poqlParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode AND() { return getToken(poqlParser.AND, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(poqlParser.CLOSE_PARENTHESIS, 0); }
		public TerminalNode OR() { return getToken(poqlParser.OR, 0); }
		public Filter_terminalContext filter_terminal() {
			return getRuleContext(Filter_terminalContext.class,0);
		}
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
		Filter_expressionContext _localctx = new Filter_expressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_filter_expression);
		try {
			setState(226);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(205);
				match(NOT);
				setState(206);
				((Filter_expressionContext)_localctx).f0 = filter_expression();
				 ((Filter_expressionContext)_localctx).tree =  poql.createNotNode(((Filter_expressionContext)_localctx).f0.tree); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(209);
				match(OPEN_PARENTHESIS);
				setState(210);
				((Filter_expressionContext)_localctx).f1 = filter_expression();
				setState(211);
				match(AND);
				setState(212);
				((Filter_expressionContext)_localctx).f2 = filter_expression();
				setState(213);
				match(CLOSE_PARENTHESIS);
				 ((Filter_expressionContext)_localctx).tree =  poql.createAndNode(((Filter_expressionContext)_localctx).f1.tree,((Filter_expressionContext)_localctx).f2.tree); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(216);
				match(OPEN_PARENTHESIS);
				setState(217);
				((Filter_expressionContext)_localctx).f3 = filter_expression();
				setState(218);
				match(OR);
				setState(219);
				((Filter_expressionContext)_localctx).f4 = filter_expression();
				setState(220);
				match(CLOSE_PARENTHESIS);
				 ((Filter_expressionContext)_localctx).tree =  poql.createOrNode(((Filter_expressionContext)_localctx).f3.tree,((Filter_expressionContext)_localctx).f4.tree); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(223);
				((Filter_expressionContext)_localctx).f5 = filter_terminal();
				 ((Filter_expressionContext)_localctx).tree =  ((Filter_expressionContext)_localctx).f5.tree; 
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

	public static class Filter_terminalContext extends ParserRuleContext {
		public FilterTree tree;
		public IdContext f5;
		public Token STRING;
		public IdContext f6;
		public IdContext f7;
		public IdContext f8;
		public IdContext f9;
		public IdContext f10;
		public IdContext f11;
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
		public Filter_terminalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter_terminal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterFilter_terminal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitFilter_terminal(this);
		}
	}

	public final Filter_terminalContext filter_terminal() throws RecognitionException {
		Filter_terminalContext _localctx = new Filter_terminalContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_filter_terminal);
		try {
			setState(263);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(228);
				((Filter_terminalContext)_localctx).f5 = id();
				setState(229);
				match(EQUAL);
				setState(230);
				((Filter_terminalContext)_localctx).STRING = match(STRING);
				 ((Filter_terminalContext)_localctx).tree =  poql.createEqualTerminalFilter(((Filter_terminalContext)_localctx).f5.name,(((Filter_terminalContext)_localctx).STRING!=null?((Filter_terminalContext)_localctx).STRING.getText():null),((Filter_terminalContext)_localctx).f5.att); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(233);
				((Filter_terminalContext)_localctx).f6 = id();
				setState(234);
				match(DIFFERENT);
				setState(235);
				((Filter_terminalContext)_localctx).STRING = match(STRING);
				 ((Filter_terminalContext)_localctx).tree =  poql.createDifferentTerminalFilter(((Filter_terminalContext)_localctx).f6.name,(((Filter_terminalContext)_localctx).STRING!=null?((Filter_terminalContext)_localctx).STRING.getText():null),((Filter_terminalContext)_localctx).f6.att); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(238);
				((Filter_terminalContext)_localctx).f7 = id();
				setState(239);
				match(EQUAL_OR_GREATER);
				setState(240);
				((Filter_terminalContext)_localctx).STRING = match(STRING);
				 ((Filter_terminalContext)_localctx).tree =  poql.createEqualOrGreaterTerminalFilter(((Filter_terminalContext)_localctx).f7.name,(((Filter_terminalContext)_localctx).STRING!=null?((Filter_terminalContext)_localctx).STRING.getText():null),((Filter_terminalContext)_localctx).f7.att); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(243);
				((Filter_terminalContext)_localctx).f8 = id();
				setState(244);
				match(EQUAL_OR_SMALLER);
				setState(245);
				((Filter_terminalContext)_localctx).STRING = match(STRING);
				 ((Filter_terminalContext)_localctx).tree =  poql.createEqualOrSmallerTerminalFilter(((Filter_terminalContext)_localctx).f8.name,(((Filter_terminalContext)_localctx).STRING!=null?((Filter_terminalContext)_localctx).STRING.getText():null),((Filter_terminalContext)_localctx).f8.att); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(248);
				((Filter_terminalContext)_localctx).f9 = id();
				setState(249);
				match(GREATER);
				setState(250);
				((Filter_terminalContext)_localctx).STRING = match(STRING);
				 ((Filter_terminalContext)_localctx).tree =  poql.createGreaterTerminalFilter(((Filter_terminalContext)_localctx).f9.name,(((Filter_terminalContext)_localctx).STRING!=null?((Filter_terminalContext)_localctx).STRING.getText():null),((Filter_terminalContext)_localctx).f9.att); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(253);
				((Filter_terminalContext)_localctx).f10 = id();
				setState(254);
				match(SMALLER);
				setState(255);
				((Filter_terminalContext)_localctx).STRING = match(STRING);
				 ((Filter_terminalContext)_localctx).tree =  poql.createSmallerTerminalFilter(((Filter_terminalContext)_localctx).f10.name,(((Filter_terminalContext)_localctx).STRING!=null?((Filter_terminalContext)_localctx).STRING.getText():null),((Filter_terminalContext)_localctx).f10.att); 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(258);
				((Filter_terminalContext)_localctx).f11 = id();
				setState(259);
				match(CONTAINS);
				setState(260);
				((Filter_terminalContext)_localctx).STRING = match(STRING);
				 ((Filter_terminalContext)_localctx).tree =  poql.createContainsTerminalFilter(((Filter_terminalContext)_localctx).f11.name,(((Filter_terminalContext)_localctx).STRING!=null?((Filter_terminalContext)_localctx).STRING.getText():null),((Filter_terminalContext)_localctx).f11.att); 
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

	public static class Filter_expression_versionsContext extends ParserRuleContext {
		public FilterTree tree;
		public Filter_expression_versionsContext f0;
		public Filter_expression_versionsContext f1;
		public Filter_expression_versionsContext f2;
		public Filter_expression_versionsContext f3;
		public Filter_expression_versionsContext f4;
		public Filter_terminalContext f5;
		public Filter_terminal_changedContext f6;
		public TerminalNode NOT() { return getToken(poqlParser.NOT, 0); }
		public List<Filter_expression_versionsContext> filter_expression_versions() {
			return getRuleContexts(Filter_expression_versionsContext.class);
		}
		public Filter_expression_versionsContext filter_expression_versions(int i) {
			return getRuleContext(Filter_expression_versionsContext.class,i);
		}
		public TerminalNode OPEN_PARENTHESIS() { return getToken(poqlParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode AND() { return getToken(poqlParser.AND, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(poqlParser.CLOSE_PARENTHESIS, 0); }
		public TerminalNode OR() { return getToken(poqlParser.OR, 0); }
		public Filter_terminalContext filter_terminal() {
			return getRuleContext(Filter_terminalContext.class,0);
		}
		public Filter_terminal_changedContext filter_terminal_changed() {
			return getRuleContext(Filter_terminal_changedContext.class,0);
		}
		public Filter_expression_versionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter_expression_versions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterFilter_expression_versions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitFilter_expression_versions(this);
		}
	}

	public final Filter_expression_versionsContext filter_expression_versions() throws RecognitionException {
		Filter_expression_versionsContext _localctx = new Filter_expression_versionsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_filter_expression_versions);
		try {
			setState(289);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(265);
				match(NOT);
				setState(266);
				((Filter_expression_versionsContext)_localctx).f0 = filter_expression_versions();
				 ((Filter_expression_versionsContext)_localctx).tree =  poql.createNotNode(((Filter_expression_versionsContext)_localctx).f0.tree); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(269);
				match(OPEN_PARENTHESIS);
				setState(270);
				((Filter_expression_versionsContext)_localctx).f1 = filter_expression_versions();
				setState(271);
				match(AND);
				setState(272);
				((Filter_expression_versionsContext)_localctx).f2 = filter_expression_versions();
				setState(273);
				match(CLOSE_PARENTHESIS);
				 ((Filter_expression_versionsContext)_localctx).tree =  poql.createAndNode(((Filter_expression_versionsContext)_localctx).f1.tree,((Filter_expression_versionsContext)_localctx).f2.tree); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(276);
				match(OPEN_PARENTHESIS);
				setState(277);
				((Filter_expression_versionsContext)_localctx).f3 = filter_expression_versions();
				setState(278);
				match(OR);
				setState(279);
				((Filter_expression_versionsContext)_localctx).f4 = filter_expression_versions();
				setState(280);
				match(CLOSE_PARENTHESIS);
				 ((Filter_expression_versionsContext)_localctx).tree =  poql.createOrNode(((Filter_expression_versionsContext)_localctx).f3.tree,((Filter_expression_versionsContext)_localctx).f4.tree); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(283);
				((Filter_expression_versionsContext)_localctx).f5 = filter_terminal();
				 ((Filter_expression_versionsContext)_localctx).tree =  ((Filter_expression_versionsContext)_localctx).f5.tree; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(286);
				((Filter_expression_versionsContext)_localctx).f6 = filter_terminal_changed();
				 ((Filter_expression_versionsContext)_localctx).tree =  ((Filter_expression_versionsContext)_localctx).f6.tree; 
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

	public static class Filter_terminal_changedContext extends ParserRuleContext {
		public FilterTree tree;
		public IdContext f12;
		public Token f13;
		public Token f14;
		public TerminalNode CHANGED() { return getToken(poqlParser.CHANGED, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode FROM() { return getToken(poqlParser.FROM, 0); }
		public TerminalNode TO() { return getToken(poqlParser.TO, 0); }
		public List<TerminalNode> STRING() { return getTokens(poqlParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(poqlParser.STRING, i);
		}
		public Filter_terminal_changedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter_terminal_changed; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).enterFilter_terminal_changed(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof poqlListener ) ((poqlListener)listener).exitFilter_terminal_changed(this);
		}
	}

	public final Filter_terminal_changedContext filter_terminal_changed() throws RecognitionException {
		Filter_terminal_changedContext _localctx = new Filter_terminal_changedContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_filter_terminal_changed);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			((Filter_terminal_changedContext)_localctx).f12 = id();
			setState(292);
			match(CHANGED);
			setState(295);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(293);
				match(FROM);
				setState(294);
				((Filter_terminal_changedContext)_localctx).f13 = match(STRING);
				}
				break;
			}
			setState(299);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(297);
				match(TO);
				setState(298);
				((Filter_terminal_changedContext)_localctx).f14 = match(STRING);
				}
				break;
			}
			 ((Filter_terminal_changedContext)_localctx).tree =  poql.createChangedTerminalFilter(((Filter_terminal_changedContext)_localctx).f12.name,(((Filter_terminal_changedContext)_localctx).f13!=null?((Filter_terminal_changedContext)_localctx).f13.getText():null),(((Filter_terminal_changedContext)_localctx).f14!=null?((Filter_terminal_changedContext)_localctx).f14.getText():null)); 
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
		enterRule(_localctx, 28, RULE_id);
		try {
			setState(307);
			switch (_input.LA(1)) {
			case IDATT:
				enterOuterAlt(_localctx, 1);
				{
				setState(303);
				((IdContext)_localctx).IDATT = match(IDATT);
				((IdContext)_localctx).name =  (((IdContext)_localctx).IDATT!=null?((IdContext)_localctx).IDATT.getText():null); ((IdContext)_localctx).att =  true;
				}
				break;
			case IDNOATT:
				enterOuterAlt(_localctx, 2);
				{
				setState(305);
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
		enterRule(_localctx, 30, RULE_allObjects);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
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
		enterRule(_localctx, 32, RULE_allCases);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
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
		enterRule(_localctx, 34, RULE_allEvents);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
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
		enterRule(_localctx, 36, RULE_allClasses);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
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
		enterRule(_localctx, 38, RULE_allVersions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
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
		enterRule(_localctx, 40, RULE_allActivities);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3#\u014a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3B\n\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4N\n\4\3\4\3\4\3\4\3\4\7\4T\n\4\f"+
		"\4\16\4W\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5c\n\5\3\5\3\5"+
		"\3\5\3\5\7\5i\n\5\f\5\16\5l\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\5\6x\n\6\3\6\3\6\3\6\3\6\7\6~\n\6\f\6\16\6\u0081\13\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u008d\n\7\3\7\3\7\3\7\3\7\7\7\u0093\n\7"+
		"\f\7\16\7\u0096\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\5\b\u00a8\n\b\3\b\3\b\3\b\3\b\7\b\u00ae\n\b\f\b\16\b\u00b1"+
		"\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00bd\n\t\3\t\3\t\3"+
		"\t\3\t\7\t\u00c3\n\t\f\t\16\t\u00c6\13\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\5\f\u00e5\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u010a\n\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\5\16\u0124\n\16\3\17\3\17\3\17\3\17\5\17"+
		"\u012a\n\17\3\17\3\17\5\17\u012e\n\17\3\17\3\17\3\20\3\20\3\20\3\20\5"+
		"\20\u0136\n\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\2\b\6\b\n\f\16\20\27\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2\2\u0156\2,\3\2\2\2\4A\3\2\2\2"+
		"\6M\3\2\2\2\bb\3\2\2\2\nw\3\2\2\2\f\u008c\3\2\2\2\16\u00a7\3\2\2\2\20"+
		"\u00bc\3\2\2\2\22\u00c7\3\2\2\2\24\u00cb\3\2\2\2\26\u00e4\3\2\2\2\30\u0109"+
		"\3\2\2\2\32\u0123\3\2\2\2\34\u0125\3\2\2\2\36\u0135\3\2\2\2 \u0137\3\2"+
		"\2\2\"\u013a\3\2\2\2$\u013d\3\2\2\2&\u0140\3\2\2\2(\u0143\3\2\2\2*\u0146"+
		"\3\2\2\2,-\5\4\3\2-.\b\2\1\2.\3\3\2\2\2/\60\5\b\5\2\60\61\b\3\1\2\61B"+
		"\3\2\2\2\62\63\5\6\4\2\63\64\b\3\1\2\64B\3\2\2\2\65\66\5\n\6\2\66\67\b"+
		"\3\1\2\67B\3\2\2\289\5\f\7\29:\b\3\1\2:B\3\2\2\2;<\5\16\b\2<=\b\3\1\2"+
		"=B\3\2\2\2>?\5\20\t\2?@\b\3\1\2@B\3\2\2\2A/\3\2\2\2A\62\3\2\2\2A\65\3"+
		"\2\2\2A8\3\2\2\2A;\3\2\2\2A>\3\2\2\2B\5\3\2\2\2CD\b\4\1\2DE\7\4\2\2EF"+
		"\7\20\2\2FG\5\4\3\2GH\7\21\2\2HI\b\4\1\2IN\3\2\2\2JK\5 \21\2KL\b\4\1\2"+
		"LN\3\2\2\2MC\3\2\2\2MJ\3\2\2\2NU\3\2\2\2OP\f\3\2\2PQ\5\22\n\2QR\b\4\1"+
		"\2RT\3\2\2\2SO\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2V\7\3\2\2\2WU\3\2"+
		"\2\2XY\b\5\1\2YZ\7\3\2\2Z[\7\20\2\2[\\\5\4\3\2\\]\7\21\2\2]^\b\5\1\2^"+
		"c\3\2\2\2_`\5\"\22\2`a\b\5\1\2ac\3\2\2\2bX\3\2\2\2b_\3\2\2\2cj\3\2\2\2"+
		"de\f\3\2\2ef\5\22\n\2fg\b\5\1\2gi\3\2\2\2hd\3\2\2\2il\3\2\2\2jh\3\2\2"+
		"\2jk\3\2\2\2k\t\3\2\2\2lj\3\2\2\2mn\b\6\1\2no\7\5\2\2op\7\20\2\2pq\5\4"+
		"\3\2qr\7\21\2\2rs\b\6\1\2sx\3\2\2\2tu\5$\23\2uv\b\6\1\2vx\3\2\2\2wm\3"+
		"\2\2\2wt\3\2\2\2x\177\3\2\2\2yz\f\3\2\2z{\5\22\n\2{|\b\6\1\2|~\3\2\2\2"+
		"}y\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\13\3\2"+
		"\2\2\u0081\177\3\2\2\2\u0082\u0083\b\7\1\2\u0083\u0084\7\6\2\2\u0084\u0085"+
		"\7\20\2\2\u0085\u0086\5\4\3\2\u0086\u0087\7\21\2\2\u0087\u0088\b\7\1\2"+
		"\u0088\u008d\3\2\2\2\u0089\u008a\5&\24\2\u008a\u008b\b\7\1\2\u008b\u008d"+
		"\3\2\2\2\u008c\u0082\3\2\2\2\u008c\u0089\3\2\2\2\u008d\u0094\3\2\2\2\u008e"+
		"\u008f\f\3\2\2\u008f\u0090\5\22\n\2\u0090\u0091\b\7\1\2\u0091\u0093\3"+
		"\2\2\2\u0092\u008e\3\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\r\3\2\2\2\u0096\u0094\3\2\2\2\u0097\u0098\b\b\1\2"+
		"\u0098\u0099\7\7\2\2\u0099\u009a\7\20\2\2\u009a\u009b\5\4\3\2\u009b\u009c"+
		"\7\21\2\2\u009c\u009d\b\b\1\2\u009d\u00a8\3\2\2\2\u009e\u009f\5(\25\2"+
		"\u009f\u00a0\b\b\1\2\u00a0\u00a8\3\2\2\2\u00a1\u00a2\7\t\2\2\u00a2\u00a3"+
		"\7\20\2\2\u00a3\u00a4\5\16\b\2\u00a4\u00a5\7\21\2\2\u00a5\u00a6\b\b\1"+
		"\2\u00a6\u00a8\3\2\2\2\u00a7\u0097\3\2\2\2\u00a7\u009e\3\2\2\2\u00a7\u00a1"+
		"\3\2\2\2\u00a8\u00af\3\2\2\2\u00a9\u00aa\f\3\2\2\u00aa\u00ab\5\24\13\2"+
		"\u00ab\u00ac\b\b\1\2\u00ac\u00ae\3\2\2\2\u00ad\u00a9\3\2\2\2\u00ae\u00b1"+
		"\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\17\3\2\2\2\u00b1"+
		"\u00af\3\2\2\2\u00b2\u00b3\b\t\1\2\u00b3\u00b4\7\b\2\2\u00b4\u00b5\7\20"+
		"\2\2\u00b5\u00b6\5\4\3\2\u00b6\u00b7\7\21\2\2\u00b7\u00b8\b\t\1\2\u00b8"+
		"\u00bd\3\2\2\2\u00b9\u00ba\5*\26\2\u00ba\u00bb\b\t\1\2\u00bb\u00bd\3\2"+
		"\2\2\u00bc\u00b2\3\2\2\2\u00bc\u00b9\3\2\2\2\u00bd\u00c4\3\2\2\2\u00be"+
		"\u00bf\f\3\2\2\u00bf\u00c0\5\22\n\2\u00c0\u00c1\b\t\1\2\u00c1\u00c3\3"+
		"\2\2\2\u00c2\u00be\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c5\21\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00c8\7\22\2"+
		"\2\u00c8\u00c9\5\26\f\2\u00c9\u00ca\b\n\1\2\u00ca\23\3\2\2\2\u00cb\u00cc"+
		"\7\22\2\2\u00cc\u00cd\5\32\16\2\u00cd\u00ce\b\13\1\2\u00ce\25\3\2\2\2"+
		"\u00cf\u00d0\7\34\2\2\u00d0\u00d1\5\26\f\2\u00d1\u00d2\b\f\1\2\u00d2\u00e5"+
		"\3\2\2\2\u00d3\u00d4\7\20\2\2\u00d4\u00d5\5\26\f\2\u00d5\u00d6\7\32\2"+
		"\2\u00d6\u00d7\5\26\f\2\u00d7\u00d8\7\21\2\2\u00d8\u00d9\b\f\1\2\u00d9"+
		"\u00e5\3\2\2\2\u00da\u00db\7\20\2\2\u00db\u00dc\5\26\f\2\u00dc\u00dd\7"+
		"\33\2\2\u00dd\u00de\5\26\f\2\u00de\u00df\7\21\2\2\u00df\u00e0\b\f\1\2"+
		"\u00e0\u00e5\3\2\2\2\u00e1\u00e2\5\30\r\2\u00e2\u00e3\b\f\1\2\u00e3\u00e5"+
		"\3\2\2\2\u00e4\u00cf\3\2\2\2\u00e4\u00d3\3\2\2\2\u00e4\u00da\3\2\2\2\u00e4"+
		"\u00e1\3\2\2\2\u00e5\27\3\2\2\2\u00e6\u00e7\5\36\20\2\u00e7\u00e8\7\23"+
		"\2\2\u00e8\u00e9\7 \2\2\u00e9\u00ea\b\r\1\2\u00ea\u010a\3\2\2\2\u00eb"+
		"\u00ec\5\36\20\2\u00ec\u00ed\7\24\2\2\u00ed\u00ee\7 \2\2\u00ee\u00ef\b"+
		"\r\1\2\u00ef\u010a\3\2\2\2\u00f0\u00f1\5\36\20\2\u00f1\u00f2\7\25\2\2"+
		"\u00f2\u00f3\7 \2\2\u00f3\u00f4\b\r\1\2\u00f4\u010a\3\2\2\2\u00f5\u00f6"+
		"\5\36\20\2\u00f6\u00f7\7\26\2\2\u00f7\u00f8\7 \2\2\u00f8\u00f9\b\r\1\2"+
		"\u00f9\u010a\3\2\2\2\u00fa\u00fb\5\36\20\2\u00fb\u00fc\7\27\2\2\u00fc"+
		"\u00fd\7 \2\2\u00fd\u00fe\b\r\1\2\u00fe\u010a\3\2\2\2\u00ff\u0100\5\36"+
		"\20\2\u0100\u0101\7\30\2\2\u0101\u0102\7 \2\2\u0102\u0103\b\r\1\2\u0103"+
		"\u010a\3\2\2\2\u0104\u0105\5\36\20\2\u0105\u0106\7\31\2\2\u0106\u0107"+
		"\7 \2\2\u0107\u0108\b\r\1\2\u0108\u010a\3\2\2\2\u0109\u00e6\3\2\2\2\u0109"+
		"\u00eb\3\2\2\2\u0109\u00f0\3\2\2\2\u0109\u00f5\3\2\2\2\u0109\u00fa\3\2"+
		"\2\2\u0109\u00ff\3\2\2\2\u0109\u0104\3\2\2\2\u010a\31\3\2\2\2\u010b\u010c"+
		"\7\34\2\2\u010c\u010d\5\32\16\2\u010d\u010e\b\16\1\2\u010e\u0124\3\2\2"+
		"\2\u010f\u0110\7\20\2\2\u0110\u0111\5\32\16\2\u0111\u0112\7\32\2\2\u0112"+
		"\u0113\5\32\16\2\u0113\u0114\7\21\2\2\u0114\u0115\b\16\1\2\u0115\u0124"+
		"\3\2\2\2\u0116\u0117\7\20\2\2\u0117\u0118\5\32\16\2\u0118\u0119\7\33\2"+
		"\2\u0119\u011a\5\32\16\2\u011a\u011b\7\21\2\2\u011b\u011c\b\16\1\2\u011c"+
		"\u0124\3\2\2\2\u011d\u011e\5\30\r\2\u011e\u011f\b\16\1\2\u011f\u0124\3"+
		"\2\2\2\u0120\u0121\5\34\17\2\u0121\u0122\b\16\1\2\u0122\u0124\3\2\2\2"+
		"\u0123\u010b\3\2\2\2\u0123\u010f\3\2\2\2\u0123\u0116\3\2\2\2\u0123\u011d"+
		"\3\2\2\2\u0123\u0120\3\2\2\2\u0124\33\3\2\2\2\u0125\u0126\5\36\20\2\u0126"+
		"\u0129\7\35\2\2\u0127\u0128\7\36\2\2\u0128\u012a\7 \2\2\u0129\u0127\3"+
		"\2\2\2\u0129\u012a\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u012c\7\37\2\2\u012c"+
		"\u012e\7 \2\2\u012d\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f\3\2"+
		"\2\2\u012f\u0130\b\17\1\2\u0130\35\3\2\2\2\u0131\u0132\7!\2\2\u0132\u0136"+
		"\b\20\1\2\u0133\u0134\7\"\2\2\u0134\u0136\b\20\1\2\u0135\u0131\3\2\2\2"+
		"\u0135\u0133\3\2\2\2\u0136\37\3\2\2\2\u0137\u0138\7\n\2\2\u0138\u0139"+
		"\b\21\1\2\u0139!\3\2\2\2\u013a\u013b\7\13\2\2\u013b\u013c\b\22\1\2\u013c"+
		"#\3\2\2\2\u013d\u013e\7\f\2\2\u013e\u013f\b\23\1\2\u013f%\3\2\2\2\u0140"+
		"\u0141\7\r\2\2\u0141\u0142\b\24\1\2\u0142\'\3\2\2\2\u0143\u0144\7\16\2"+
		"\2\u0144\u0145\b\25\1\2\u0145)\3\2\2\2\u0146\u0147\7\17\2\2\u0147\u0148"+
		"\b\26\1\2\u0148+\3\2\2\2\25AMUbjw\177\u008c\u0094\u00a7\u00af\u00bc\u00c4"+
		"\u00e4\u0109\u0123\u0129\u012d\u0135";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}