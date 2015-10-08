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
		CASESOF=1, OBJECTSOF=2, EVENTSOF=3, CLASSESOF=4, VERSIONSOF=5, ACTIVITIESOF=6, 
		VERSIONS_RELATED_TO=7, ALLOBJECTS=8, ALLCASES=9, ALLEVENTS=10, ALLCLASSES=11, 
		ALLVERSIONS=12, ALLACTIVITIES=13, OPEN_PARENTHESIS=14, CLOSE_PARENTHESIS=15, 
		WHERE=16, EQUAL=17, DIFFERENT=18, EQUAL_OR_GREATER=19, EQUAL_OR_SMALLER=20, 
		GREATER=21, SMALLER=22, CONTAINS=23, AND=24, OR=25, NOT=26, CHANGED=27, 
		FROM=28, TO=29, STRING=30, IDATT=31, IDNOATT=32, WS=33;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"CASESOF", "OBJECTSOF", "EVENTSOF", "CLASSESOF", "VERSIONSOF", "ACTIVITIESOF", 
		"VERSIONS_RELATED_TO", "ALLOBJECTS", "ALLCASES", "ALLEVENTS", "ALLCLASSES", 
		"ALLVERSIONS", "ALLACTIVITIES", "OPEN_PARENTHESIS", "CLOSE_PARENTHESIS", 
		"WHERE", "EQUAL", "DIFFERENT", "EQUAL_OR_GREATER", "EQUAL_OR_SMALLER", 
		"GREATER", "SMALLER", "CONTAINS", "AND", "OR", "NOT", "CHANGED", "FROM", 
		"TO", "STRING", "IDATT", "IDNOATT", "WS", "A", "B", "C", "D", "E", "F", 
		"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", 
		"U", "V", "W", "X", "Y", "Z"
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
		case 29:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 30:
			IDATT_action((RuleContext)_localctx, actionIndex);
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
	private void IDATT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			 setText(getText().substring(3, getText().length())); 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2#\u019a\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3"+
		"\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3"+
		"\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3"+
		"\36\3\37\3\37\7\37\u014c\n\37\f\37\16\37\u014f\13\37\3\37\3\37\3\37\3"+
		" \3 \3 \3 \3 \3 \3 \3!\6!\u015c\n!\r!\16!\u015d\3\"\6\"\u0161\n\"\r\""+
		"\16\"\u0162\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*"+
		"\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63"+
		"\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3"+
		"<\2\2=\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\35"+
		"9\36;\37= ?!A\"C#E\2G\2I\2K\2M\2O\2Q\2S\2U\2W\2Y\2[\2]\2_\2a\2c\2e\2g"+
		"\2i\2k\2m\2o\2q\2s\2u\2w\2\3\2\37\5\2\f\f\17\17$$\7\2..\62;C\\aac|\5\2"+
		"\13\f\17\17\"\"\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2II"+
		"ii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2"+
		"RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4"+
		"\2[[{{\4\2\\\\||\u0182\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2"+
		"\2\3y\3\2\2\2\5\u0081\3\2\2\2\7\u008b\3\2\2\2\t\u0094\3\2\2\2\13\u009e"+
		"\3\2\2\2\r\u00a9\3\2\2\2\17\u00b6\3\2\2\2\21\u00c8\3\2\2\2\23\u00d3\3"+
		"\2\2\2\25\u00dc\3\2\2\2\27\u00e6\3\2\2\2\31\u00f1\3\2\2\2\33\u00fd\3\2"+
		"\2\2\35\u010b\3\2\2\2\37\u010d\3\2\2\2!\u010f\3\2\2\2#\u0115\3\2\2\2%"+
		"\u0118\3\2\2\2\'\u011b\3\2\2\2)\u011e\3\2\2\2+\u0121\3\2\2\2-\u0123\3"+
		"\2\2\2/\u0125\3\2\2\2\61\u012e\3\2\2\2\63\u0132\3\2\2\2\65\u0135\3\2\2"+
		"\2\67\u0139\3\2\2\29\u0141\3\2\2\2;\u0146\3\2\2\2=\u0149\3\2\2\2?\u0153"+
		"\3\2\2\2A\u015b\3\2\2\2C\u0160\3\2\2\2E\u0166\3\2\2\2G\u0168\3\2\2\2I"+
		"\u016a\3\2\2\2K\u016c\3\2\2\2M\u016e\3\2\2\2O\u0170\3\2\2\2Q\u0172\3\2"+
		"\2\2S\u0174\3\2\2\2U\u0176\3\2\2\2W\u0178\3\2\2\2Y\u017a\3\2\2\2[\u017c"+
		"\3\2\2\2]\u017e\3\2\2\2_\u0180\3\2\2\2a\u0182\3\2\2\2c\u0184\3\2\2\2e"+
		"\u0186\3\2\2\2g\u0188\3\2\2\2i\u018a\3\2\2\2k\u018c\3\2\2\2m\u018e\3\2"+
		"\2\2o\u0190\3\2\2\2q\u0192\3\2\2\2s\u0194\3\2\2\2u\u0196\3\2\2\2w\u0198"+
		"\3\2\2\2yz\5I%\2z{\5E#\2{|\5i\65\2|}\5M\'\2}~\5i\65\2~\177\5a\61\2\177"+
		"\u0080\5O(\2\u0080\4\3\2\2\2\u0081\u0082\5a\61\2\u0082\u0083\5G$\2\u0083"+
		"\u0084\5W,\2\u0084\u0085\5M\'\2\u0085\u0086\5I%\2\u0086\u0087\5k\66\2"+
		"\u0087\u0088\5i\65\2\u0088\u0089\5a\61\2\u0089\u008a\5O(\2\u008a\6\3\2"+
		"\2\2\u008b\u008c\5M\'\2\u008c\u008d\5o8\2\u008d\u008e\5M\'\2\u008e\u008f"+
		"\5_\60\2\u008f\u0090\5k\66\2\u0090\u0091\5i\65\2\u0091\u0092\5a\61\2\u0092"+
		"\u0093\5O(\2\u0093\b\3\2\2\2\u0094\u0095\5I%\2\u0095\u0096\5[.\2\u0096"+
		"\u0097\5E#\2\u0097\u0098\5i\65\2\u0098\u0099\5i\65\2\u0099\u009a\5M\'"+
		"\2\u009a\u009b\5i\65\2\u009b\u009c\5a\61\2\u009c\u009d\5O(\2\u009d\n\3"+
		"\2\2\2\u009e\u009f\5o8\2\u009f\u00a0\5M\'\2\u00a0\u00a1\5g\64\2\u00a1"+
		"\u00a2\5i\65\2\u00a2\u00a3\5U+\2\u00a3\u00a4\5a\61\2\u00a4\u00a5\5_\60"+
		"\2\u00a5\u00a6\5i\65\2\u00a6\u00a7\5a\61\2\u00a7\u00a8\5O(\2\u00a8\f\3"+
		"\2\2\2\u00a9\u00aa\5E#\2\u00aa\u00ab\5I%\2\u00ab\u00ac\5k\66\2\u00ac\u00ad"+
		"\5U+\2\u00ad\u00ae\5o8\2\u00ae\u00af\5U+\2\u00af\u00b0\5k\66\2\u00b0\u00b1"+
		"\5U+\2\u00b1\u00b2\5M\'\2\u00b2\u00b3\5i\65\2\u00b3\u00b4\5a\61\2\u00b4"+
		"\u00b5\5O(\2\u00b5\16\3\2\2\2\u00b6\u00b7\5o8\2\u00b7\u00b8\5M\'\2\u00b8"+
		"\u00b9\5g\64\2\u00b9\u00ba\5i\65\2\u00ba\u00bb\5U+\2\u00bb\u00bc\5a\61"+
		"\2\u00bc\u00bd\5_\60\2\u00bd\u00be\5i\65\2\u00be\u00bf\5g\64\2\u00bf\u00c0"+
		"\5M\'\2\u00c0\u00c1\5[.\2\u00c1\u00c2\5E#\2\u00c2\u00c3\5k\66\2\u00c3"+
		"\u00c4\5M\'\2\u00c4\u00c5\5K&\2\u00c5\u00c6\5k\66\2\u00c6\u00c7\5a\61"+
		"\2\u00c7\20\3\2\2\2\u00c8\u00c9\5E#\2\u00c9\u00ca\5[.\2\u00ca\u00cb\5"+
		"[.\2\u00cb\u00cc\5a\61\2\u00cc\u00cd\5G$\2\u00cd\u00ce\5W,\2\u00ce\u00cf"+
		"\5M\'\2\u00cf\u00d0\5I%\2\u00d0\u00d1\5k\66\2\u00d1\u00d2\5i\65\2\u00d2"+
		"\22\3\2\2\2\u00d3\u00d4\5E#\2\u00d4\u00d5\5[.\2\u00d5\u00d6\5[.\2\u00d6"+
		"\u00d7\5I%\2\u00d7\u00d8\5E#\2\u00d8\u00d9\5i\65\2\u00d9\u00da\5M\'\2"+
		"\u00da\u00db\5i\65\2\u00db\24\3\2\2\2\u00dc\u00dd\5E#\2\u00dd\u00de\5"+
		"[.\2\u00de\u00df\5[.\2\u00df\u00e0\5M\'\2\u00e0\u00e1\5o8\2\u00e1\u00e2"+
		"\5M\'\2\u00e2\u00e3\5_\60\2\u00e3\u00e4\5k\66\2\u00e4\u00e5\5i\65\2\u00e5"+
		"\26\3\2\2\2\u00e6\u00e7\5E#\2\u00e7\u00e8\5[.\2\u00e8\u00e9\5[.\2\u00e9"+
		"\u00ea\5I%\2\u00ea\u00eb\5[.\2\u00eb\u00ec\5E#\2\u00ec\u00ed\5i\65\2\u00ed"+
		"\u00ee\5i\65\2\u00ee\u00ef\5M\'\2\u00ef\u00f0\5i\65\2\u00f0\30\3\2\2\2"+
		"\u00f1\u00f2\5E#\2\u00f2\u00f3\5[.\2\u00f3\u00f4\5[.\2\u00f4\u00f5\5o"+
		"8\2\u00f5\u00f6\5M\'\2\u00f6\u00f7\5g\64\2\u00f7\u00f8\5i\65\2\u00f8\u00f9"+
		"\5U+\2\u00f9\u00fa\5a\61\2\u00fa\u00fb\5_\60\2\u00fb\u00fc\5i\65\2\u00fc"+
		"\32\3\2\2\2\u00fd\u00fe\5E#\2\u00fe\u00ff\5[.\2\u00ff\u0100\5[.\2\u0100"+
		"\u0101\5E#\2\u0101\u0102\5I%\2\u0102\u0103\5k\66\2\u0103\u0104\5U+\2\u0104"+
		"\u0105\5o8\2\u0105\u0106\5U+\2\u0106\u0107\5k\66\2\u0107\u0108\5U+\2\u0108"+
		"\u0109\5M\'\2\u0109\u010a\5i\65\2\u010a\34\3\2\2\2\u010b\u010c\7*\2\2"+
		"\u010c\36\3\2\2\2\u010d\u010e\7+\2\2\u010e \3\2\2\2\u010f\u0110\5q9\2"+
		"\u0110\u0111\5S*\2\u0111\u0112\5M\'\2\u0112\u0113\5g\64\2\u0113\u0114"+
		"\5M\'\2\u0114\"\3\2\2\2\u0115\u0116\7?\2\2\u0116\u0117\7?\2\2\u0117$\3"+
		"\2\2\2\u0118\u0119\7>\2\2\u0119\u011a\7@\2\2\u011a&\3\2\2\2\u011b\u011c"+
		"\7?\2\2\u011c\u011d\7@\2\2\u011d(\3\2\2\2\u011e\u011f\7?\2\2\u011f\u0120"+
		"\7>\2\2\u0120*\3\2\2\2\u0121\u0122\7@\2\2\u0122,\3\2\2\2\u0123\u0124\7"+
		">\2\2\u0124.\3\2\2\2\u0125\u0126\5I%\2\u0126\u0127\5a\61\2\u0127\u0128"+
		"\5_\60\2\u0128\u0129\5k\66\2\u0129\u012a\5E#\2\u012a\u012b\5U+\2\u012b"+
		"\u012c\5_\60\2\u012c\u012d\5i\65\2\u012d\60\3\2\2\2\u012e\u012f\5E#\2"+
		"\u012f\u0130\5_\60\2\u0130\u0131\5K&\2\u0131\62\3\2\2\2\u0132\u0133\5"+
		"a\61\2\u0133\u0134\5g\64\2\u0134\64\3\2\2\2\u0135\u0136\5_\60\2\u0136"+
		"\u0137\5a\61\2\u0137\u0138\5k\66\2\u0138\66\3\2\2\2\u0139\u013a\5I%\2"+
		"\u013a\u013b\5S*\2\u013b\u013c\5E#\2\u013c\u013d\5_\60\2\u013d\u013e\5"+
		"Q)\2\u013e\u013f\5M\'\2\u013f\u0140\5K&\2\u01408\3\2\2\2\u0141\u0142\5"+
		"O(\2\u0142\u0143\5g\64\2\u0143\u0144\5a\61\2\u0144\u0145\5]/\2\u0145:"+
		"\3\2\2\2\u0146\u0147\5k\66\2\u0147\u0148\5a\61\2\u0148<\3\2\2\2\u0149"+
		"\u014d\7$\2\2\u014a\u014c\n\2\2\2\u014b\u014a\3\2\2\2\u014c\u014f\3\2"+
		"\2\2\u014d\u014b\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u0150\3\2\2\2\u014f"+
		"\u014d\3\2\2\2\u0150\u0151\7$\2\2\u0151\u0152\b\37\2\2\u0152>\3\2\2\2"+
		"\u0153\u0154\7c\2\2\u0154\u0155\7v\2\2\u0155\u0156\7\60\2\2\u0156\u0157"+
		"\3\2\2\2\u0157\u0158\5A!\2\u0158\u0159\b \3\2\u0159@\3\2\2\2\u015a\u015c"+
		"\t\3\2\2\u015b\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015b\3\2\2\2\u015d"+
		"\u015e\3\2\2\2\u015eB\3\2\2\2\u015f\u0161\t\4\2\2\u0160\u015f\3\2\2\2"+
		"\u0161\u0162\3\2\2\2\u0162\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0164"+
		"\3\2\2\2\u0164\u0165\b\"\4\2\u0165D\3\2\2\2\u0166\u0167\t\5\2\2\u0167"+
		"F\3\2\2\2\u0168\u0169\t\6\2\2\u0169H\3\2\2\2\u016a\u016b\t\7\2\2\u016b"+
		"J\3\2\2\2\u016c\u016d\t\b\2\2\u016dL\3\2\2\2\u016e\u016f\t\t\2\2\u016f"+
		"N\3\2\2\2\u0170\u0171\t\n\2\2\u0171P\3\2\2\2\u0172\u0173\t\13\2\2\u0173"+
		"R\3\2\2\2\u0174\u0175\t\f\2\2\u0175T\3\2\2\2\u0176\u0177\t\r\2\2\u0177"+
		"V\3\2\2\2\u0178\u0179\t\16\2\2\u0179X\3\2\2\2\u017a\u017b\t\17\2\2\u017b"+
		"Z\3\2\2\2\u017c\u017d\t\20\2\2\u017d\\\3\2\2\2\u017e\u017f\t\21\2\2\u017f"+
		"^\3\2\2\2\u0180\u0181\t\22\2\2\u0181`\3\2\2\2\u0182\u0183\t\23\2\2\u0183"+
		"b\3\2\2\2\u0184\u0185\t\24\2\2\u0185d\3\2\2\2\u0186\u0187\t\25\2\2\u0187"+
		"f\3\2\2\2\u0188\u0189\t\26\2\2\u0189h\3\2\2\2\u018a\u018b\t\27\2\2\u018b"+
		"j\3\2\2\2\u018c\u018d\t\30\2\2\u018dl\3\2\2\2\u018e\u018f\t\31\2\2\u018f"+
		"n\3\2\2\2\u0190\u0191\t\32\2\2\u0191p\3\2\2\2\u0192\u0193\t\33\2\2\u0193"+
		"r\3\2\2\2\u0194\u0195\t\34\2\2\u0195t\3\2\2\2\u0196\u0197\t\35\2\2\u0197"+
		"v\3\2\2\2\u0198\u0199\t\36\2\2\u0199x\3\2\2\2\6\2\u014d\u015d\u0162\5"+
		"\3\37\2\3 \3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}