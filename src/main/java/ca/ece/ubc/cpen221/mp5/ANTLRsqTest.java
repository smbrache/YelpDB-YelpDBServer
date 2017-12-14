package ca.ece.ubc.cpen221.mp5;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class ANTLRsqTest {

    // This is a MP5 Part V test bench.
    // Once completed, it will serve as the centralized 'Structured Queries'
    // parse and result generator.
    //
    // Currently:
    // Used to demonstrate the use of the ANTLR4 interface
    // and test the functionality of structured queries
    //
    // TODO: Build order-of-operations stack interface for query processing
    // TODO: Generate parallelStream functions to separate data
    // TODO: Test main-format functionality
    // TODO: Refactor as object-oriented client application
    // TODO: Test object-format functionality

    public static void main(String[] args) {
        String inputString = "in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price <= 2";
        //String inputString = "HEFOIEFHOI";
        CharStream stream = new ANTLRInputStream(inputString);
        MP5DbLexer lexer = new MP5DbLexer(stream);
        lexer.removeErrorListeners();
        TokenStream tokens = new CommonTokenStream(lexer);
        MP5DbParser parser = new MP5DbParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(MP5DbErrorListener.instance);

        try {
            ParseTree tree = parser.root();
            ParseTreeWalker walker = new ParseTreeWalker();
            MP5DbListener listener = new MP5DbQueryListener();
            walker.walk(listener, tree);
        }
        catch (Exception ParseCancellationException) {
            System.out.println("Error: this request can't be parsed due to invalid syntax.");
        }
    }
}
