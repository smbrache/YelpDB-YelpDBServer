package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MP5DbQueryListener extends MP5DbBaseListener{

    /**
     * Rep Invariant: operationList must be a non-null list of size greater or equal to 0 during initialization
     * After evaluation, operationList size must be equal or greater to one, and odd. operationList must contain
     * atomic strings (such as in(Location), or category(Genre), and operation strings (such as 1&& or 2||).
     * At all times, operationList must alternate between an atomic string and operation string.
     *
     * Abstraction Function: an atomic string maps to an atomic statement in the form function(function_specifier).
     * The operation strings map to a precedence value (denoted by parenthesisCount) in the form <precedence_value><OPERATION>
     */
    private List<String> operationList;

    private int parenthesisCount = 0;
    private int parenthesisMax = 0;

    public MP5DbQueryListener() {
        this.operationList = new ArrayList<>();
    }

    /**
     * Generates a copy of the current state operationList and returns it.
     *
     * @return the list of structured query operations
     */
    public synchronized List<String> getOperationList() {
        return new ArrayList<>(operationList);
    }


    public synchronized int getParenthesisMax() {
        return parenthesisMax;
    }

    /**
     * An 'or' node was exited. Add an 'or' operation to the operation stack.
     */
    public synchronized void exitOr(MP5DbParser.OrContext ctx) {
        // Debug
        // System.err.println(ctx.getText());

        // Generate operationStack string
        String operationString = "";

        operationString = operationString + parenthesisCount;
        operationString = operationString + ctx.getText();

        if (parenthesisCount > parenthesisMax)
            parenthesisMax = parenthesisCount;

        operationList.add(operationString);


    }

    /**
     * An 'and' node was exited. Add an 'and' operation to the operation stack.
     */
    public synchronized void exitAnd(MP5DbParser.AndContext ctx) {
        // Debug
        // System.err.println(ctx.getText());


        // Generate operationStack string
        String operationString = "";

        operationString = operationString + parenthesisCount;
        operationString = operationString + ctx.getText();

        if (parenthesisCount > parenthesisMax)
            parenthesisMax = parenthesisCount;

        operationList.add(operationString);
    }

    /**
     * The atomic operation 'in' was exited. Add data to the atom stack.
     */
    public synchronized void exitIn(MP5DbParser.InContext ctx) {
        // Debug
        // System.err.println(ctx.getText());

        // Add to atomStack
        operationList.add(ctx.getText());
    }

    /**
     * The atomic operation 'category' was exited. Add data to the atom stack.
     */
    public synchronized void exitCategory(MP5DbParser.CategoryContext ctx) {
        // Debug
        // System.err.println(ctx.getText());

        // Add to atomStack
        operationList.add(ctx.getText());
    }

    /**
     * The atomic operation 'rating' was exited. Add data to the atom stack.
     */
    public synchronized void exitRating(MP5DbParser.RatingContext ctx) {
        // Debug
        // System.err.println(ctx.getText());

        // Add to atomStack
        operationList.add(ctx.getText());
    }

    /**
     * The atomic operation 'parser' was exited. Add data to the atom stack.
     */
    public synchronized void exitPrice(MP5DbParser.PriceContext ctx) {
        // Debug
        // System.err.println(ctx.getText());

        // Add to atomStack
        operationList.add(ctx.getText());
    }

    /**
     * The atomic operation 'name' was exited. Add data to the atom stack.
     */
    public synchronized void exitName(MP5DbParser.NameContext ctx) {
        // Debug
        // System.err.println(ctx.getText());

        // Add to atomStack
        operationList.add(ctx.getText());
    }

    public synchronized void exitAndExpr(MP5DbParser.AndExprContext ctx) {
        // Debug
        // System.err.println("Exit AndExpr " + ctx.getText());

        parenthesisCount--;
    }

    public synchronized void exitOrExpr(MP5DbParser.OrExprContext ctx) {
        // Debug
        // System.err.println("Exit OrExpr " + ctx.getText());

        parenthesisCount--;
    }

    public synchronized void enterAndExpr(MP5DbParser.AndExprContext ctx) {
        // Debug
        // System.err.println("Enter AndExpr " + ctx.getText());

        parenthesisCount++;
    }

    public synchronized void enterOrExpr(MP5DbParser.OrExprContext ctx) {
        // Debug
        // System.err.println("Enter OrExpr " + ctx.getText());

        parenthesisCount++;
    }

    /**
     * Send a debug log to console outlining the contents of atomList, and operationList
     */
    /*
    public synchronized void checkContents() {
        System.out.println("Current Operation List:");
        System.out.println("parenthesisMax = " + parenthesisMax);
        for (String currOperation: operationList) {
            System.out.println(currOperation);
        }
    }
    */
}
