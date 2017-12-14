package ca.ece.ubc.cpen221.mp5;

// Generated from MP5Db.g4 by ANTLR 4.7

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MP5DbLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, LParen=13, RParen=14, NUM=15, STRING=16, 
		WORD=17, SPACE=18, STRINGSPACE=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "LParen", "RParen", "NUM", "STRING", "WORD", 
		"SPACE", "STRINGSPACE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'||'", "'&&'", "'>'", "'>='", "'<'", "'<='", "'='", "'in'", "'category'", 
		"'name'", "'rating'", "'price'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "LParen", "RParen", "NUM", "STRING", "WORD", "SPACE", "STRINGSPACE"
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


	public MP5DbLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MP5Db.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25y\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3"+
		"\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\7\21d\n\21"+
		"\f\21\16\21g\13\21\3\22\6\22j\n\22\r\22\16\22k\3\23\6\23o\n\23\r\23\16"+
		"\23p\3\23\3\23\3\24\6\24v\n\24\r\24\16\24w\2\2\25\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		"\3\2\5\3\2\63\67\4\2C\\c|\5\2\13\f\17\17\"\"\2|\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\3)\3\2\2\2\5,\3\2\2\2\7/\3\2\2\2\t\61\3\2\2\2\13\64\3\2\2\2\r\66"+
		"\3\2\2\2\179\3\2\2\2\21;\3\2\2\2\23>\3\2\2\2\25G\3\2\2\2\27L\3\2\2\2\31"+
		"S\3\2\2\2\33Y\3\2\2\2\35[\3\2\2\2\37]\3\2\2\2!_\3\2\2\2#i\3\2\2\2%n\3"+
		"\2\2\2\'u\3\2\2\2)*\7~\2\2*+\7~\2\2+\4\3\2\2\2,-\7(\2\2-.\7(\2\2.\6\3"+
		"\2\2\2/\60\7@\2\2\60\b\3\2\2\2\61\62\7@\2\2\62\63\7?\2\2\63\n\3\2\2\2"+
		"\64\65\7>\2\2\65\f\3\2\2\2\66\67\7>\2\2\678\7?\2\28\16\3\2\2\29:\7?\2"+
		"\2:\20\3\2\2\2;<\7k\2\2<=\7p\2\2=\22\3\2\2\2>?\7e\2\2?@\7c\2\2@A\7v\2"+
		"\2AB\7g\2\2BC\7i\2\2CD\7q\2\2DE\7t\2\2EF\7{\2\2F\24\3\2\2\2GH\7p\2\2H"+
		"I\7c\2\2IJ\7o\2\2JK\7g\2\2K\26\3\2\2\2LM\7t\2\2MN\7c\2\2NO\7v\2\2OP\7"+
		"k\2\2PQ\7p\2\2QR\7i\2\2R\30\3\2\2\2ST\7r\2\2TU\7t\2\2UV\7k\2\2VW\7e\2"+
		"\2WX\7g\2\2X\32\3\2\2\2YZ\7*\2\2Z\34\3\2\2\2[\\\7+\2\2\\\36\3\2\2\2]^"+
		"\t\2\2\2^ \3\2\2\2_e\5#\22\2`a\5\'\24\2ab\5#\22\2bd\3\2\2\2c`\3\2\2\2"+
		"dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2f\"\3\2\2\2ge\3\2\2\2hj\t\3\2\2ih\3\2\2"+
		"\2jk\3\2\2\2ki\3\2\2\2kl\3\2\2\2l$\3\2\2\2mo\t\4\2\2nm\3\2\2\2op\3\2\2"+
		"\2pn\3\2\2\2pq\3\2\2\2qr\3\2\2\2rs\b\23\2\2s&\3\2\2\2tv\t\4\2\2ut\3\2"+
		"\2\2vw\3\2\2\2wu\3\2\2\2wx\3\2\2\2x(\3\2\2\2\7\2ekpw\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}