package ca.ece.ubc.cpen221.mp5;

import java.util.Stack;

public class MP5DbQueryListener extends MP5DbBaseListener{

    /**
     * Rep Invariant: atomStack and operationStack must be non-null stacks of size greater or equal to 0.
     * The size of operationStack must be at least the same size as atomStack (or larger).
     *
     * Abstraction Function: atomStack and operationStack map to an order of operations involving the MP5Db - YelpDB.
     * Given two complete stacks (of atomStack and operationStack), one has to pop one element from the operationStack.
     *
     * If the element is an &&: expr = <currentDatabase> && topElement
     *
     * If the element is an ||: expr = <currentDatabase> && topElement1 + <currentDatabase> && topElement2
     */
    private Stack <String> atomStack;
    private Stack <String> operationStack;

    public MP5DbQueryListener() {
        // Initialize Stack elements
        this.atomStack = new Stack();
        this.operationStack = new Stack();
    }

    /**
     * Generates a copy of the current state atomStack and returns it.
     *
     * @return Stack<String> of structured query atoms
     */
    public Stack getAtomStack() {
        return copyStack(atomStack);
    }

    /**
     * Generates a copy of the current state operationStack and returns it.
     *
     * @return Stack<String> of structured query operations
     */
    public Stack getOperationStack() {
        return copyStack(operationStack);
    }

    /**
     * Copies an input stack and returns a new equivalent stack of it.
     *
     * @param inputStack to be copied
     *
     * @return identical specification, address independent stack
     */
    private Stack<String> copyStack(Stack inputStack) {
        return null;
    }

    /**
     * An 'or' node was exited. Add an 'or' operation to the operation stack.
     */
    public void exitOr(MP5DbParser.OrContext ctx) {
        // Debug
        System.err.println(ctx.getText());

        // Push to operationStack
        operationStack.push(ctx.getText());

    }

    /**
     * An 'and' node was exited. Add an 'and' operation to the operation stack.
     */
    public void exitAnd(MP5DbParser.AndContext ctx) {
        // Debug
        System.err.println(ctx.getText());

        // Push to operationStack
        operationStack.push(ctx.getText());
    }

    /**
     * The atomic operation 'in' was exited. Add data to the atom stack.
     */
    public void exitIn(MP5DbParser.InContext ctx) {
        // Debug
        System.err.println(ctx.getText());

        // Push to atomStack
        atomStack.push(ctx.getText());
    }

    /**
     * The atomic operation 'category' was exited. Add data to the atom stack.
     */
    public void exitCategory(MP5DbParser.CategoryContext ctx) {
        System.err.println(ctx.getText());

        // Push to atomStack
        atomStack.push(ctx.getText());
    }

    /**
     * The atomic operation 'rating' was exited. Add data to the atom stack.
     */
    public void exitRating(MP5DbParser.RatingContext ctx) {
        // Debug
        System.err.println(ctx.getText());

        // Push to atomStack
        atomStack.push(ctx.getText());
    }

    /**
     * The atomic operation 'parser' was exited. Add data to the atom stack.
     */
    public void exitPrice(MP5DbParser.PriceContext ctx) {
        // Debug
        System.err.println(ctx.getText());

        // Push to atomStack
        atomStack.push(ctx.getText());
    }

    /**
     * The atomic operation 'name' was exited. Add data to the atom stack.
     */
    public void exitName(MP5DbParser.NameContext ctx) {
        // Debug
        System.err.println(ctx.getText());

        // Push to atomStack
        atomStack.push(ctx.getText());
    }
}
