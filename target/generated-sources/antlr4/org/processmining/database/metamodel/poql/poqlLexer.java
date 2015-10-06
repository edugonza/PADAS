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

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class poqlLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, CASESOF=7, OBJECTSOF=8, 
		EVENTSOF=9, CLASSESOF=10, VERSIONSOF=11, ACTIVITIESOF=12, OPEN_FUNCTION=13, 
		CLOSE_FUNCTION=14, WHERE=15, EQUAL=16, DIFFERENT=17, EQUAL_OR_GREATER=18, 
		EQUAL_OR_SMALLER=19, GREATER=20, SMALLER=21, CONTAINS=22, AND=23, OR=24, 
		STRING=25, ID=26, WS=27;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "CASESOF", "OBJECTSOF", 
		"EVENTSOF", "CLASSESOF", "VERSIONSOF", "ACTIVITIESOF", "OPEN_FUNCTION", 
		"CLOSE_FUNCTION", "WHERE", "EQUAL", "DIFFERENT", "EQUAL_OR_GREATER", "EQUAL_OR_SMALLER", 
		"GREATER", "SMALLER", "CONTAINS", "AND", "OR", "STRING", "ID", "WS"
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
		"GREATER", "SMALLER", "CONTAINS", "AND", "OR", "STRING", "ID", "WS"
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


	public poqlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "poql.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 24:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 setText(getText().substring(1, getText().length() - 1)); 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\35\u00f9\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\32\3\32\7\32\u00e6\n\32\f\32\16\32\u00e9\13\32\3\32\3\32\3\32\3\33"+
		"\6\33\u00ef\n\33\r\33\16\33\u00f0\3\34\6\34\u00f4\n\34\r\34\16\34\u00f5"+
		"\3\34\3\34\2\2\35\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\35\3\2\5\5\2\f\f\17\17$$\6\2..\62;aac|\5\2\13\f\17\17\"\"\u00fb"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\39\3\2\2\2\5D\3"+
		"\2\2\2\7M\3\2\2\2\tW\3\2\2\2\13b\3\2\2\2\rn\3\2\2\2\17|\3\2\2\2\21\u0084"+
		"\3\2\2\2\23\u008e\3\2\2\2\25\u0097\3\2\2\2\27\u00a1\3\2\2\2\31\u00ac\3"+
		"\2\2\2\33\u00b9\3\2\2\2\35\u00bb\3\2\2\2\37\u00bd\3\2\2\2!\u00c3\3\2\2"+
		"\2#\u00c6\3\2\2\2%\u00c9\3\2\2\2\'\u00cc\3\2\2\2)\u00cf\3\2\2\2+\u00d1"+
		"\3\2\2\2-\u00d3\3\2\2\2/\u00dc\3\2\2\2\61\u00e0\3\2\2\2\63\u00e3\3\2\2"+
		"\2\65\u00ee\3\2\2\2\67\u00f3\3\2\2\29:\7c\2\2:;\7n\2\2;<\7n\2\2<=\7Q\2"+
		"\2=>\7d\2\2>?\7l\2\2?@\7g\2\2@A\7e\2\2AB\7v\2\2BC\7u\2\2C\4\3\2\2\2DE"+
		"\7c\2\2EF\7n\2\2FG\7n\2\2GH\7E\2\2HI\7c\2\2IJ\7u\2\2JK\7g\2\2KL\7u\2\2"+
		"L\6\3\2\2\2MN\7c\2\2NO\7n\2\2OP\7n\2\2PQ\7G\2\2QR\7x\2\2RS\7g\2\2ST\7"+
		"p\2\2TU\7v\2\2UV\7u\2\2V\b\3\2\2\2WX\7c\2\2XY\7n\2\2YZ\7n\2\2Z[\7E\2\2"+
		"[\\\7n\2\2\\]\7c\2\2]^\7u\2\2^_\7u\2\2_`\7g\2\2`a\7u\2\2a\n\3\2\2\2bc"+
		"\7c\2\2cd\7n\2\2de\7n\2\2ef\7X\2\2fg\7g\2\2gh\7t\2\2hi\7u\2\2ij\7k\2\2"+
		"jk\7q\2\2kl\7p\2\2lm\7u\2\2m\f\3\2\2\2no\7c\2\2op\7n\2\2pq\7n\2\2qr\7"+
		"C\2\2rs\7e\2\2st\7v\2\2tu\7k\2\2uv\7x\2\2vw\7k\2\2wx\7v\2\2xy\7k\2\2y"+
		"z\7g\2\2z{\7u\2\2{\16\3\2\2\2|}\7e\2\2}~\7c\2\2~\177\7u\2\2\177\u0080"+
		"\7g\2\2\u0080\u0081\7u\2\2\u0081\u0082\7Q\2\2\u0082\u0083\7h\2\2\u0083"+
		"\20\3\2\2\2\u0084\u0085\7q\2\2\u0085\u0086\7d\2\2\u0086\u0087\7l\2\2\u0087"+
		"\u0088\7g\2\2\u0088\u0089\7e\2\2\u0089\u008a\7v\2\2\u008a\u008b\7u\2\2"+
		"\u008b\u008c\7Q\2\2\u008c\u008d\7h\2\2\u008d\22\3\2\2\2\u008e\u008f\7"+
		"g\2\2\u008f\u0090\7x\2\2\u0090\u0091\7g\2\2\u0091\u0092\7p\2\2\u0092\u0093"+
		"\7v\2\2\u0093\u0094\7u\2\2\u0094\u0095\7Q\2\2\u0095\u0096\7h\2\2\u0096"+
		"\24\3\2\2\2\u0097\u0098\7e\2\2\u0098\u0099\7n\2\2\u0099\u009a\7c\2\2\u009a"+
		"\u009b\7u\2\2\u009b\u009c\7u\2\2\u009c\u009d\7g\2\2\u009d\u009e\7u\2\2"+
		"\u009e\u009f\7Q\2\2\u009f\u00a0\7h\2\2\u00a0\26\3\2\2\2\u00a1\u00a2\7"+
		"x\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7t\2\2\u00a4\u00a5\7u\2\2\u00a5\u00a6"+
		"\7k\2\2\u00a6\u00a7\7q\2\2\u00a7\u00a8\7p\2\2\u00a8\u00a9\7u\2\2\u00a9"+
		"\u00aa\7Q\2\2\u00aa\u00ab\7h\2\2\u00ab\30\3\2\2\2\u00ac\u00ad\7c\2\2\u00ad"+
		"\u00ae\7e\2\2\u00ae\u00af\7v\2\2\u00af\u00b0\7k\2\2\u00b0\u00b1\7x\2\2"+
		"\u00b1\u00b2\7k\2\2\u00b2\u00b3\7v\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5"+
		"\7g\2\2\u00b5\u00b6\7u\2\2\u00b6\u00b7\7Q\2\2\u00b7\u00b8\7h\2\2\u00b8"+
		"\32\3\2\2\2\u00b9\u00ba\7*\2\2\u00ba\34\3\2\2\2\u00bb\u00bc\7+\2\2\u00bc"+
		"\36\3\2\2\2\u00bd\u00be\7y\2\2\u00be\u00bf\7j\2\2\u00bf\u00c0\7g\2\2\u00c0"+
		"\u00c1\7t\2\2\u00c1\u00c2\7g\2\2\u00c2 \3\2\2\2\u00c3\u00c4\7?\2\2\u00c4"+
		"\u00c5\7?\2\2\u00c5\"\3\2\2\2\u00c6\u00c7\7>\2\2\u00c7\u00c8\7@\2\2\u00c8"+
		"$\3\2\2\2\u00c9\u00ca\7?\2\2\u00ca\u00cb\7@\2\2\u00cb&\3\2\2\2\u00cc\u00cd"+
		"\7?\2\2\u00cd\u00ce\7>\2\2\u00ce(\3\2\2\2\u00cf\u00d0\7@\2\2\u00d0*\3"+
		"\2\2\2\u00d1\u00d2\7>\2\2\u00d2,\3\2\2\2\u00d3\u00d4\7e\2\2\u00d4\u00d5"+
		"\7q\2\2\u00d5\u00d6\7p\2\2\u00d6\u00d7\7v\2\2\u00d7\u00d8\7c\2\2\u00d8"+
		"\u00d9\7k\2\2\u00d9\u00da\7p\2\2\u00da\u00db\7u\2\2\u00db.\3\2\2\2\u00dc"+
		"\u00dd\7C\2\2\u00dd\u00de\7P\2\2\u00de\u00df\7F\2\2\u00df\60\3\2\2\2\u00e0"+
		"\u00e1\7Q\2\2\u00e1\u00e2\7T\2\2\u00e2\62\3\2\2\2\u00e3\u00e7\7$\2\2\u00e4"+
		"\u00e6\n\2\2\2\u00e5\u00e4\3\2\2\2\u00e6\u00e9\3\2\2\2\u00e7\u00e5\3\2"+
		"\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00ea\3\2\2\2\u00e9\u00e7\3\2\2\2\u00ea"+
		"\u00eb\7$\2\2\u00eb\u00ec\b\32\2\2\u00ec\64\3\2\2\2\u00ed\u00ef\t\3\2"+
		"\2\u00ee\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1"+
		"\3\2\2\2\u00f1\66\3\2\2\2\u00f2\u00f4\t\4\2\2\u00f3\u00f2\3\2\2\2\u00f4"+
		"\u00f5\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7\3\2"+
		"\2\2\u00f7\u00f8\b\34\3\2\u00f88\3\2\2\2\6\2\u00e7\u00f0\u00f5\4\3\32"+
		"\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}