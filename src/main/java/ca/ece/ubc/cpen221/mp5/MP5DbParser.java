package ca.ece.ubc.cpen221.mp5;

// Generated from MP5Db.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MP5DbParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, LParen=13, RParen=14, NUM=15, STRING=16, 
		WORD=17, SPACE=18, STRINGSPACE=19;
	public static final int
		RULE_root = 0, RULE_orExpr = 1, RULE_andExpr = 2, RULE_atom = 3, RULE_or = 4, 
		RULE_and = 5, RULE_ineq = 6, RULE_gt = 7, RULE_gte = 8, RULE_lt = 9, RULE_lte = 10, 
		RULE_eq = 11, RULE_in = 12, RULE_category = 13, RULE_name = 14, RULE_rating = 15, 
		RULE_price = 16;
	public static final String[] ruleNames = {
		"root", "orExpr", "andExpr", "atom", "or", "and", "ineq", "gt", "gte", 
		"lt", "lte", "eq", "in", "category", "name", "rating", "price"
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

	@Override
	public String getGrammarFileName() { return "MP5Db.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MP5DbParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
		}
		public TerminalNode EOF() { return getToken(MP5DbParser.EOF, 0); }
		public List<TerminalNode> SPACE() { return getTokens(MP5DbParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(MP5DbParser.SPACE, i);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			orExpr();
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(35);
				match(SPACE);
				}
				}
				setState(40);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(41);
			match(EOF);
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

	public static class OrExprContext extends ParserRuleContext {
		public List<AndExprContext> andExpr() {
			return getRuleContexts(AndExprContext.class);
		}
		public AndExprContext andExpr(int i) {
			return getRuleContext(AndExprContext.class,i);
		}
		public List<OrContext> or() {
			return getRuleContexts(OrContext.class);
		}
		public OrContext or(int i) {
			return getRuleContext(OrContext.class,i);
		}
		public OrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).exitOrExpr(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_orExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			andExpr();
			setState(49);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(44);
					or();
					setState(45);
					andExpr();
					}
					} 
				}
				setState(51);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
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

	public static class AndExprContext extends ParserRuleContext {
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public List<AndContext> and() {
			return getRuleContexts(AndContext.class);
		}
		public AndContext and(int i) {
			return getRuleContext(AndContext.class,i);
		}
		public AndExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).exitAndExpr(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_andExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			atom();
			setState(58);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(53);
					and();
					setState(54);
					atom();
					}
					} 
				}
				setState(60);
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
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public InContext in() {
			return getRuleContext(InContext.class,0);
		}
		public CategoryContext category() {
			return getRuleContext(CategoryContext.class,0);
		}
		public RatingContext rating() {
			return getRuleContext(RatingContext.class,0);
		}
		public PriceContext price() {
			return getRuleContext(PriceContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode LParen() { return getToken(MP5DbParser.LParen, 0); }
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MP5DbParser.RParen, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_atom);
		try {
			setState(70);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				in();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				category();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 3);
				{
				setState(63);
				rating();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 4);
				{
				setState(64);
				price();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 5);
				{
				setState(65);
				name();
				}
				break;
			case LParen:
				enterOuterAlt(_localctx, 6);
				{
				setState(66);
				match(LParen);
				setState(67);
				orExpr();
				setState(68);
				match(RParen);
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

	public static class OrContext extends ParserRuleContext {
		public List<TerminalNode> SPACE() { return getTokens(MP5DbParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(MP5DbParser.SPACE, i);
		}
		public OrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).enterOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).exitOr(this);
		}
	}

	public final OrContext or() throws RecognitionException {
		OrContext _localctx = new OrContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_or);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(72);
				match(SPACE);
				}
				}
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(78);
			match(T__0);
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(79);
				match(SPACE);
				}
				}
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class AndContext extends ParserRuleContext {
		public List<TerminalNode> SPACE() { return getTokens(MP5DbParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(MP5DbParser.SPACE, i);
		}
		public AndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).enterAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).exitAnd(this);
		}
	}

	public final AndContext and() throws RecognitionException {
		AndContext _localctx = new AndContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_and);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(85);
				match(SPACE);
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(91);
			match(T__1);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(92);
				match(SPACE);
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class IneqContext extends ParserRuleContext {
		public GtContext gt() {
			return getRuleContext(GtContext.class,0);
		}
		public GteContext gte() {
			return getRuleContext(GteContext.class,0);
		}
		public LtContext lt() {
			return getRuleContext(LtContext.class,0);
		}
		public LteContext lte() {
			return getRuleContext(LteContext.class,0);
		}
		public EqContext eq() {
			return getRuleContext(EqContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(MP5DbParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(MP5DbParser.SPACE, i);
		}
		public IneqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ineq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).enterIneq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).exitIneq(this);
		}
	}

	public final IneqContext ineq() throws RecognitionException {
		IneqContext _localctx = new IneqContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ineq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(98);
				match(SPACE);
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(109);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(104);
				gt();
				}
				break;
			case T__3:
				{
				setState(105);
				gte();
				}
				break;
			case T__4:
				{
				setState(106);
				lt();
				}
				break;
			case T__5:
				{
				setState(107);
				lte();
				}
				break;
			case T__6:
				{
				setState(108);
				eq();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(111);
				match(SPACE);
				}
				}
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class GtContext extends ParserRuleContext {
		public GtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).enterGt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).exitGt(this);
		}
	}

	public final GtContext gt() throws RecognitionException {
		GtContext _localctx = new GtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_gt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(T__2);
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

	public static class GteContext extends ParserRuleContext {
		public GteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gte; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).enterGte(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).exitGte(this);
		}
	}

	public final GteContext gte() throws RecognitionException {
		GteContext _localctx = new GteContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_gte);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(T__3);
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

	public static class LtContext extends ParserRuleContext {
		public LtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).enterLt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).exitLt(this);
		}
	}

	public final LtContext lt() throws RecognitionException {
		LtContext _localctx = new LtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_lt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(T__4);
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

	public static class LteContext extends ParserRuleContext {
		public LteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lte; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).enterLte(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).exitLte(this);
		}
	}

	public final LteContext lte() throws RecognitionException {
		LteContext _localctx = new LteContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_lte);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(T__5);
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

	public static class EqContext extends ParserRuleContext {
		public EqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).enterEq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).exitEq(this);
		}
	}

	public final EqContext eq() throws RecognitionException {
		EqContext _localctx = new EqContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_eq);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(T__6);
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

	public static class InContext extends ParserRuleContext {
		public TerminalNode LParen() { return getToken(MP5DbParser.LParen, 0); }
		public TerminalNode STRING() { return getToken(MP5DbParser.STRING, 0); }
		public TerminalNode RParen() { return getToken(MP5DbParser.RParen, 0); }
		public InContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_in; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).enterIn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).exitIn(this);
		}
	}

	public final InContext in() throws RecognitionException {
		InContext _localctx = new InContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_in);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(T__7);
			setState(128);
			match(LParen);
			setState(129);
			match(STRING);
			setState(130);
			match(RParen);
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

	public static class CategoryContext extends ParserRuleContext {
		public TerminalNode LParen() { return getToken(MP5DbParser.LParen, 0); }
		public TerminalNode STRING() { return getToken(MP5DbParser.STRING, 0); }
		public TerminalNode RParen() { return getToken(MP5DbParser.RParen, 0); }
		public CategoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).enterCategory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).exitCategory(this);
		}
	}

	public final CategoryContext category() throws RecognitionException {
		CategoryContext _localctx = new CategoryContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_category);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(T__8);
			setState(133);
			match(LParen);
			setState(134);
			match(STRING);
			setState(135);
			match(RParen);
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

	public static class NameContext extends ParserRuleContext {
		public TerminalNode LParen() { return getToken(MP5DbParser.LParen, 0); }
		public TerminalNode STRING() { return getToken(MP5DbParser.STRING, 0); }
		public TerminalNode RParen() { return getToken(MP5DbParser.RParen, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(T__9);
			setState(138);
			match(LParen);
			setState(139);
			match(STRING);
			setState(140);
			match(RParen);
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

	public static class RatingContext extends ParserRuleContext {
		public IneqContext ineq() {
			return getRuleContext(IneqContext.class,0);
		}
		public TerminalNode NUM() { return getToken(MP5DbParser.NUM, 0); }
		public RatingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rating; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).enterRating(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).exitRating(this);
		}
	}

	public final RatingContext rating() throws RecognitionException {
		RatingContext _localctx = new RatingContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_rating);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(T__10);
			setState(143);
			ineq();
			setState(144);
			match(NUM);
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

	public static class PriceContext extends ParserRuleContext {
		public IneqContext ineq() {
			return getRuleContext(IneqContext.class,0);
		}
		public TerminalNode NUM() { return getToken(MP5DbParser.NUM, 0); }
		public PriceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_price; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).enterPrice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MP5DbListener ) ((MP5DbListener)listener).exitPrice(this);
		}
	}

	public final PriceContext price() throws RecognitionException {
		PriceContext _localctx = new PriceContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_price);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(T__11);
			setState(147);
			ineq();
			setState(148);
			match(NUM);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\25\u0099\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\7\2\'\n\2\f\2\16\2*\13\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3\62\n\3\f"+
		"\3\16\3\65\13\3\3\4\3\4\3\4\3\4\7\4;\n\4\f\4\16\4>\13\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\5\5I\n\5\3\6\7\6L\n\6\f\6\16\6O\13\6\3\6\3\6\7"+
		"\6S\n\6\f\6\16\6V\13\6\3\7\7\7Y\n\7\f\7\16\7\\\13\7\3\7\3\7\7\7`\n\7\f"+
		"\7\16\7c\13\7\3\b\7\bf\n\b\f\b\16\bi\13\b\3\b\3\b\3\b\3\b\3\b\5\bp\n\b"+
		"\3\b\7\bs\n\b\f\b\16\bv\13\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\2\2\23\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"\2\2\2\u0099\2$\3\2\2\2\4-\3\2\2\2\6\66"+
		"\3\2\2\2\bH\3\2\2\2\nM\3\2\2\2\fZ\3\2\2\2\16g\3\2\2\2\20w\3\2\2\2\22y"+
		"\3\2\2\2\24{\3\2\2\2\26}\3\2\2\2\30\177\3\2\2\2\32\u0081\3\2\2\2\34\u0086"+
		"\3\2\2\2\36\u008b\3\2\2\2 \u0090\3\2\2\2\"\u0094\3\2\2\2$(\5\4\3\2%\'"+
		"\7\24\2\2&%\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2)+\3\2\2\2*(\3\2\2\2"+
		"+,\7\2\2\3,\3\3\2\2\2-\63\5\6\4\2./\5\n\6\2/\60\5\6\4\2\60\62\3\2\2\2"+
		"\61.\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\5\3\2\2\2\65"+
		"\63\3\2\2\2\66<\5\b\5\2\678\5\f\7\289\5\b\5\29;\3\2\2\2:\67\3\2\2\2;>"+
		"\3\2\2\2<:\3\2\2\2<=\3\2\2\2=\7\3\2\2\2><\3\2\2\2?I\5\32\16\2@I\5\34\17"+
		"\2AI\5 \21\2BI\5\"\22\2CI\5\36\20\2DE\7\17\2\2EF\5\4\3\2FG\7\20\2\2GI"+
		"\3\2\2\2H?\3\2\2\2H@\3\2\2\2HA\3\2\2\2HB\3\2\2\2HC\3\2\2\2HD\3\2\2\2I"+
		"\t\3\2\2\2JL\7\24\2\2KJ\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2NP\3\2\2"+
		"\2OM\3\2\2\2PT\7\3\2\2QS\7\24\2\2RQ\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2"+
		"\2\2U\13\3\2\2\2VT\3\2\2\2WY\7\24\2\2XW\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z"+
		"[\3\2\2\2[]\3\2\2\2\\Z\3\2\2\2]a\7\4\2\2^`\7\24\2\2_^\3\2\2\2`c\3\2\2"+
		"\2a_\3\2\2\2ab\3\2\2\2b\r\3\2\2\2ca\3\2\2\2df\7\24\2\2ed\3\2\2\2fi\3\2"+
		"\2\2ge\3\2\2\2gh\3\2\2\2ho\3\2\2\2ig\3\2\2\2jp\5\20\t\2kp\5\22\n\2lp\5"+
		"\24\13\2mp\5\26\f\2np\5\30\r\2oj\3\2\2\2ok\3\2\2\2ol\3\2\2\2om\3\2\2\2"+
		"on\3\2\2\2pt\3\2\2\2qs\7\24\2\2rq\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2"+
		"\2u\17\3\2\2\2vt\3\2\2\2wx\7\5\2\2x\21\3\2\2\2yz\7\6\2\2z\23\3\2\2\2{"+
		"|\7\7\2\2|\25\3\2\2\2}~\7\b\2\2~\27\3\2\2\2\177\u0080\7\t\2\2\u0080\31"+
		"\3\2\2\2\u0081\u0082\7\n\2\2\u0082\u0083\7\17\2\2\u0083\u0084\7\22\2\2"+
		"\u0084\u0085\7\20\2\2\u0085\33\3\2\2\2\u0086\u0087\7\13\2\2\u0087\u0088"+
		"\7\17\2\2\u0088\u0089\7\22\2\2\u0089\u008a\7\20\2\2\u008a\35\3\2\2\2\u008b"+
		"\u008c\7\f\2\2\u008c\u008d\7\17\2\2\u008d\u008e\7\22\2\2\u008e\u008f\7"+
		"\20\2\2\u008f\37\3\2\2\2\u0090\u0091\7\r\2\2\u0091\u0092\5\16\b\2\u0092"+
		"\u0093\7\21\2\2\u0093!\3\2\2\2\u0094\u0095\7\16\2\2\u0095\u0096\5\16\b"+
		"\2\u0096\u0097\7\21\2\2\u0097#\3\2\2\2\r(\63<HMTZagot";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}