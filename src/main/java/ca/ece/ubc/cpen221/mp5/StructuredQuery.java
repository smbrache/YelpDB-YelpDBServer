package ca.ece.ubc.cpen221.mp5;

//import com.sun.org.apache.regexp.internal.RE;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StructuredQuery {

    /**
     * Rep Invariant: StructuredQuery is represented by an initializing immutable queryRestaurants that describes
     * the initial list of restaurants to filter from - this must not change and remain constant during the calculation
     * of the StructuredQuery result. The instructions and operations are detailed and controlled from the
     * operationInstructionList and operationDataList that must be non null, and the same size at the point before AND/OR
     * statements are executed (but not necessarily before).
     *
     * Abstraction Function: operationInstructionList and operationDataList map to a linear execution chain in the form:
     *
     * atom <-operation-> atom <-operation-> atom <-operation-> atom
     *
     * Each atom maps (in the same index) to the resulting subset of queryRestaurants. Once execution begins, AND/OR
     * operations shrink the tree performing reduction operations where:
     *
     * atom <-operation-> atom          is converted to:            node
     *
     * Execution terminates once the structure has the form:
     *
     * node
     */
    private final List<Restaurant> queryRestaurants;

    private List<String> operationInstructionList = new ArrayList<>();
    private List<List<Restaurant>> operationDataList = new ArrayList<>();

    private int operationPrecedenceMax;

    /**
     * StructuredQuery constructs and evaluates a MP5 structured query operation on a
     * set of restaurants stored in a YelpDB database.
     *
     * @param queryString is the query request represented as a single string
     * @param queryRestaurants is a set containing all restaurants to be evaluated
     */
    public StructuredQuery(String queryString, List<Restaurant> queryRestaurants) {
        this.queryRestaurants = new ArrayList<>(queryRestaurants);
        generateOperations(queryString);
        generateRestaurants();

        // printResults(); (Debug)
    }

    /**
     * Analyzes an input queryString and generates, a resulting instruction list based on the
     * described walking pattern, and tree. generateOperations will output an error message
     * to System.out if the string is formatted incorrectly.
     *
     * @param queryString a grammar-abiding string that represents the requested query.
     */
    private synchronized void generateOperations(String queryString) {
        // Base ANTLR4 components are initialized using the API
        CharStream stream = new ANTLRInputStream(queryString);
        MP5DbLexer lexer = new MP5DbLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        MP5DbParser parser = new MP5DbParser(tokens);

        // Generic error listeners are removed and replaced
        lexer.removeErrorListeners();
        parser.removeErrorListeners();
        parser.addErrorListener(MP5DbErrorListener.instance);

        try {
            ParseTree tree = parser.root();
            ParseTreeWalker walker = new ParseTreeWalker();
            MP5DbQueryListener listener = new MP5DbQueryListener();
            walker.walk(listener, tree);

            // Instructions are retrieved and recorded to the object field
            operationPrecedenceMax = listener.getParenthesisMax();
            operationInstructionList = listener.getOperationList();
        }
        catch (Exception ParseCancellationException) {
            System.out.println("ERR: INVALID_QUERY");
        }
    }

    /**
     * generateRestaurants performs the reduction operations as specified by the structured
     * query request.
     *
     * @modifies queryRestaurants as reduction operations are performed
     */
    private synchronized void generateRestaurants() {

        // Calculate each individual atom (filter reduction) as specified by the query
        for (int i = 0; i < operationInstructionList.size(); i++) {
            if (i % 2 == 0) {
                List<Restaurant> currRestaurantList = performReduction(parseAtom(operationInstructionList.get(i)), queryRestaurants);
                operationDataList.add(currRestaurantList);
            }
            else
                operationDataList.add(null);
        }

        // In precedence sequence order (high to low), perform AND/OR operations on the operationDataList
        int currPrecedence = operationPrecedenceMax;

        while (currPrecedence >= 0) {
            for (int i = 1; i < operationInstructionList.size(); i += 2) {
                if (Integer.parseInt(operationInstructionList.get(i).substring(0, 1)) == currPrecedence) {
                    // Perform AND/OR operation and replace the center element with the result
                    if (operationInstructionList.get(i).substring(1, 3).equals("||"))
                        operationDataList.set(i, narrowByOr(operationDataList.get(i - 1), operationDataList.get(i + 1)));
                    else
                        operationDataList.set(i, narrowByAnd(operationDataList.get(i - 1), operationDataList.get(i + 1)));

                    // Combine the Atom <-> Instruction <-> Atom branch into a single node
                    operationInstructionList.set(i, "ATOM");
                    operationInstructionList.remove(i - 1);
                    operationInstructionList.remove(i);
                    operationDataList.remove(i - 1);
                    operationDataList.remove(i);

                    // Shift the index back by two (due to the deletion of nodes, it now points to the next instruction)
                    i = i - 2;
                }
            }
            currPrecedence--;
        }
    }

    /**
     * narrowByOr performs an "OR" operation on two sets of restaurant data, and generates a resulting list that contains
     * elements that exist in at least one of the two input sets (a union of the two sets).
     *
     * NOTE: narrowByOr demonstrates reflexive arguments, swapping input restaurantListLeft, with restaurantListRight will
     * result in the same return list.
     *
     * @param restaurantListLeft is the first set of restaurant lists in the OR argument
     * @param restaurantListRight is the second set of restaurant lists in the OR argument
     * @return a list of restaurants that contain the union elements of the two input lists
     */
    private synchronized List<Restaurant> narrowByOr(List<Restaurant> restaurantListLeft, List<Restaurant> restaurantListRight) {
        List <Restaurant> orRestaurant = new ArrayList<>(restaurantListRight);

        for (Restaurant leftRestaurant : restaurantListLeft) {
            if (!restaurantListRight.contains(leftRestaurant))
                orRestaurant.add(leftRestaurant);
        }

        return orRestaurant;
    }

    /**
     * narrowByAnd performs an "AND" operation on two sets of restaurant data, and generates a resulting list that
     * only contains elements that exist both of the original sets.
     *
     * NOTE: narrowByAnd demonstrates reflexive arguments, swapping input restaurantListLeft, with restaurantListRight
     * will result in the same return list.
     *
     * @param restaurantListLeft is the first set of restaurant lists in the AND argument
     * @param restaurantListRight is the second set of restaurant lists in the AND argument
     * @return a list of restaurants that contain the similar elements of the two input lists
     */
    private synchronized List<Restaurant> narrowByAnd(List<Restaurant> restaurantListLeft, List<Restaurant> restaurantListRight) {
        List<Restaurant> andRestaurants = new ArrayList<>();

        // Left to right - add Restaurants that exist in both lists
        for (Restaurant leftRestaurant : restaurantListLeft) {
            if (restaurantListRight.contains(leftRestaurant))
                andRestaurants.add(leftRestaurant);
        }

        /* Right to left - add Restaurants that exist in both lists (redundant code)
        for (Restaurant rightRestaurant : restaurantListRight) {
            if (restaurantListLeft.contains(rightRestaurant) && !andRestaurants.contains(rightRestaurant))
                andRestaurants.add(rightRestaurant);
        }
        */

        return andRestaurants;
    }

    /**
     * performReduction takes a set of input restaurants, and reduces them using a filter category, and category-metric
     *
     * @param atomKey is an array key generated by parseAtom using a String atom statement
     * @param inputRestaurants is the set of restaurants to filter to
     * @return is the subset of restaurants that adhere to the filter category
     */
    private synchronized List<Restaurant> performReduction(String[] atomKey, List<Restaurant> inputRestaurants) {
        List<Restaurant> returnList = new ArrayList<>();

        switch(atomKey[0]) {
            case "1":   returnList = filterByIn(atomKey[1], inputRestaurants);
                        break;
            case "2":   returnList = filterByCategory(atomKey[1], inputRestaurants);
                        break;
            case "3":   returnList = filterByRating(inputRestaurants, Double.parseDouble(atomKey[2]), Integer.parseInt(atomKey[1]));
                        break;
            case "4":   returnList = filterByPrice(inputRestaurants, Integer.parseInt(atomKey[2]), Integer.parseInt(atomKey[1]));
                        break;
            case "5":   returnList = filterByName(atomKey[1], inputRestaurants);
                        break;
        }
        return returnList;
    }

    /**
     * parseAtom analyzes an inputAtom string and generates an array specifying the
     * appropriate operation and operation-specification
     *
     * @param inputAtom is the atom to analyze
     * @return a String[3] array arranged as:
     *      returnArray[0]: describes the operation; 1: In, 2: Category, 3: Rating, 4: Price, 5: Name
     *      returnArray[1]: describes the location, category, or name for [0] = 1, 2, 5
     *                      and describes the inequality operand (see filter by rating or category)
     *      returnArray[2]: is null for [0] = 1, 2, 5
     *                      and describes the inequality variable on a [1-5] scale
     */
    private synchronized String[] parseAtom(String inputAtom) {
        String[] atomKey = {null, null, null};

        switch(inputAtom.substring(0, 2).toLowerCase()) {
            case "in":  atomKey[0] = "1";
                        atomKey[1] = inputAtom.substring(3, inputAtom.length() - 1);
                        break;
            case "ca":  atomKey[0] = "2";
                        atomKey[1] = inputAtom.substring(9, inputAtom.length() - 1);
                        break;
            case "ra":  {    atomKey[0] = "3";
                             if (inputAtom.substring(6, 7).equals("<") && inputAtom.substring(7, 8).equals("=")) {
                                 atomKey[1] = "2";
                                 atomKey[2] = inputAtom.substring(8, inputAtom.length());
                             }
                             else if (inputAtom.substring(6, 7).equals("<")) {
                                 atomKey[1] = "1";
                                 atomKey[2] = inputAtom.substring(7, inputAtom.length());
                             }
                             else if (inputAtom.substring(6, 7).equals("=")) {
                                 atomKey[1] = "3";
                                 atomKey[2] = inputAtom.substring(7, inputAtom.length());
                             }
                             else if (inputAtom.substring(6, 7).equals(">") && inputAtom.substring(7, 8).equals("=")) {
                                 atomKey[1] = "4";
                                 atomKey[2] = inputAtom.substring(8, inputAtom.length());
                             }
                             else if (inputAtom.substring(6, 7).equals(">")) {
                                 atomKey[1] = "5";
                                 atomKey[2] = inputAtom.substring(7, inputAtom.length());
                             }
                             break;
                        }
            case "pr":  {    atomKey[0] = "4";
                             if (inputAtom.substring(5, 6).equals("<") && inputAtom.substring(6, 7).equals("=")) {
                                 atomKey[1] = "2";
                                 atomKey[2] = inputAtom.substring(7, inputAtom.length());
                             }
                             else if (inputAtom.substring(5, 6).equals("<")) {
                                 atomKey[1] = "1";
                                 atomKey[2] = inputAtom.substring(6, inputAtom.length());
                             }
                             else if (inputAtom.substring(5, 6).equals("=")) {
                                 atomKey[1] = "3";
                                 atomKey[2] = inputAtom.substring(6, inputAtom.length());
                             }
                             else if (inputAtom.substring(5, 6).equals(">") && inputAtom.substring(6, 7).equals("=")) {
                                 atomKey[1] = "4";
                                 atomKey[2] = inputAtom.substring(7, inputAtom.length());
                             }
                             else if (inputAtom.substring(5, 6).equals(">")) {
                                 atomKey[1] = "5";
                                 atomKey[2] = inputAtom.substring(6, inputAtom.length());
                             }
                             break;
                        }
            case "na":  atomKey[0] = "5";
                        atomKey[1] = inputAtom.substring(5, inputAtom.length() - 1);
                        break;
        }

        return atomKey;
    }

    /**
     * filterByIn is a StructuredQuery component that generates a subset of a provided
     * restaurant list based on a provided input location
     *
     * @param inputLocation is the location used to filter restaurants
     * @param inputList is the entire set based on the reduction
     * @return a subset containing only restaurants contained in inputList, and within inputLocation
     */
    private List<Restaurant> filterByIn(String inputLocation, List<Restaurant> inputList) {
        List<Restaurant> returnList = new ArrayList<>();

        inputList.stream()
            .filter(currRestaurant -> currRestaurant.containsNeighborhood(inputLocation))
            .forEach(returnList::add);

        return returnList;
    }

    /**
     * filterByCategory is a StructuredQuery component that generates a subset of a provided
     * restaurant list based on a provided input category
     *
     * @param inputCategory is the category used to filter restaurants
     * @param inputList is the entire set based on the reduction
     * @return a subset containing only restaurants contained in inputList, and within inputCategory
     */
    private synchronized List<Restaurant> filterByCategory(String inputCategory, List<Restaurant> inputList) {
        List<Restaurant> returnList = new ArrayList<>();

        inputList.stream()
            .filter(currRestaurant -> currRestaurant.containsCategory(inputCategory))
            .forEach(returnList::add);

        return returnList;
    }

    /**
     * filterByPrice is a StructuredQuery component that generates a subset of a provided
     * restaurant list based on a provided input rating (or rating-range)
     *
     * @param inputList is the complete set of all restaurants to be filtered
     * @param ratingVal is the double precision inequality value representing review rating
     * @param ineqCode is the inequality specifier
     *                 1: <     (less-than)
     *                 2: <=    (less-than or equal-to)
     *                 3: =     (equal-to)
     *                 4: >=    (greater-than or equal-to)
     *                 5: >     (greater-than)
     * @return a subset containing only restaurants contained within inputList, and the specified rating range
     */
    private synchronized List<Restaurant> filterByRating(List<Restaurant> inputList, double ratingVal, int ineqCode) {
        List<Restaurant> returnList = new ArrayList<>();

        switch (ineqCode) {
            case 1: inputList.stream()
                    .filter(currRestaurant -> currRestaurant.getStarScore() < ratingVal)
                    .forEach(returnList::add);
                break;
            case 2: inputList.stream()
                    .filter(currRestaurant -> currRestaurant.getStarScore() <= ratingVal)
                    .forEach(returnList::add);
                break;
            case 3: inputList.stream()
                    .filter(currRestaurant -> currRestaurant.getStarScore() == ratingVal)
                    .forEach(returnList::add);
                break;
            case 4: inputList.stream()
                    .filter(currRestaurant -> currRestaurant.getStarScore() >= ratingVal)
                    .forEach(returnList::add);
                break;
            case 5: inputList.stream()
                    .filter(currRestaurant -> currRestaurant.getStarScore() > ratingVal)
                    .forEach(returnList::add);
                break;
        }

        return returnList;
    }

    /**
     * filterByPrice is a StructuredQuery component that generates a subset of a provided
     * restaurant list based on a provided input price (or price-range)
     *
     * @param inputList is the complete set of all restaurants to be filtered
     * @param priceVal is the integer precision inequality value representing price rating
     * @param ineqCode is the inequality specifier
     *                 1: <     (less-than)
     *                 2: <=    (less-than or equal-to)
     *                 3: =     (equal-to)
     *                 4: >=    (greater-than or equal-to)
     *                 5: >     (greater-than)
     * @return a subset containing only restaurants contained within inputList, and the specified price range
     */
    private synchronized List<Restaurant> filterByPrice(List<Restaurant> inputList, int priceVal, int ineqCode) {
        List<Restaurant> returnList = new ArrayList<>();

        switch (ineqCode) {
            case 1: inputList.stream()
                        .filter(currRestaurant -> currRestaurant.getPriceScore() < priceVal)
                        .forEach(returnList::add);
                    break;
            case 2: inputList.stream()
                        .filter(currRestaurant -> currRestaurant.getPriceScore() <= priceVal)
                        .forEach(returnList::add);
                    break;
            case 3: inputList.stream()
                        .filter(currRestaurant -> currRestaurant.getPriceScore() == priceVal)
                        .forEach(returnList::add);
                    break;
            case 4: inputList.stream()
                        .filter(currRestaurant -> currRestaurant.getPriceScore() >= priceVal)
                        .forEach(returnList::add);
                    break;
            case 5: inputList.stream()
                        .filter(currRestaurant -> currRestaurant.getPriceScore() > priceVal)
                        .forEach(returnList::add);
                    break;
        }

        return returnList;
    }

    /**
     * filterByName is a StructuredQuery component that generates a subset of a provided
     * restaurant list based on a provided input name
     *
     * @param inputName is the name used to filter restaurants
     * @param inputList is the entire set based on the reduction
     * @return a subset containing only restaurants contained in inputList, and named inputName
     */
    private synchronized List<Restaurant> filterByName(String inputName, List<Restaurant> inputList) {
        List<Restaurant> returnList = new ArrayList<>();

        inputList.stream()
            .filter(currRestaurant -> currRestaurant.getName().equals(inputName))
            .forEach(returnList::add);

        return returnList;
    }

    /**
     * getResults generates a copy of the StructuredQuery results and returns it. Additionally, if
     * there are no matches, an error message will be sent to System.out.
     *
     * @return a list of all restaurants that fit the description of the StructuredQuery constructor
     * input
     */
    public synchronized List<Restaurant> getResults() throws IndexOutOfBoundsException {
        if (operationDataList.get(0).size() == 0)
            System.out.println("ERR: NO_MATCH");

        return new ArrayList<>(operationDataList.get(0));
    }

    /**
     * printResults prints a log of the StructuredQuery results/findings in System.out.
     */
    /*
    private synchronized void printResults() {
        System.out.println("Total results: " + operationDataList.get(0).size());
        for (Restaurant currRestaurant : operationDataList.get(0)) {
            System.out.println(currRestaurant.getName() + " was selected");

            System.out.print("Locations: ");
            for (String currLocation : currRestaurant.getNeighborhoods())
                System.out.print("[" + currLocation + "] ");

            System.out.print("| Categories: ");
            for (String currCategory : currRestaurant.getCategories())
                System.out.print("[" + currCategory + "] ");

            System.out.print("| AVG Review: " + currRestaurant.getStarScore() + " ");
            System.out.print("| Price: " + currRestaurant.getPriceScore());
            System.out.println("");
        }
    }
    */
}
