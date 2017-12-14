package ca.ece.ubc.cpen221.mp5;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class MP5DbErrorListener extends BaseErrorListener {

    // Custom invalid syntax exception for the MP5Db 'Part V: Structured Queries' ANTLR4 interface
    // Code was referenced from: https://stackoverflow.com/questions/18132078/handling-errors-in-antlr4

    public static final MP5DbErrorListener instance = new MP5DbErrorListener();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) throws ParseCancellationException {
        throw new ParseCancellationException("Invalid syntax found at: line " + line + ":" + charPositionInLine + " " + msg);
    }
}
