package ca.ece.ubc.cpen221.mp5;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;

public class YelpDB implements MP5Db {

    List<Restaurant> restaurantAll;
    /* RI: restaurantAll (and filter categories) are never null. They can contain 0-n entries.
     * restaurantAll contains no duplicate elements. Any Restaurant element can appear in more than
     * one category, or location, but no more than one rating or price.
     *
     * AF: 0 <= restaurantAll.size() <= databaseCapacity
     * restaurantAll.size() >= restaurantByCategory.size(), restaurantByLocation.size(), restaurantByRating.size(), restaurantByPrice.size()
     */

    Map<String, List<Restaurant>> restaurantByCategory;
    Map<String, List<Restaurant>> restaurantByLocation;
    Map<Integer, List<Restaurant>> restaurantByRating;
    Map<Integer, List<Restaurant>> restaurantByPrice;

    List<User> userAll;
    /* RI: userAll is not null. It can contain 0-n entries. userAll contains no duplicate elements.
     *
     * AF: 0 <= userAll.size() <= databaseCapacity
     */

    public YelpDB() {
        // TODO: construct empty YelpDB Sam
    }

    public YelpDB(String inputRestaurantL, String inputReviewL, String inputUserL) {
        // TODO: construct initialized YelpDB Sam
    }

    private void parseRestaurantList() {
        // TODO: implement Restaurant JSON parser Sam
    }

    private void parseReviewList() {
        // TODO: implement Review JSON parser Sam
    }

    private void parseUserList() {
        // TODO: implement User JSON parser Sam
    }

    /**
     * Adds a new restaurant to all relevant category maps and restaurantAll list
     *
     * @param addedRestaurant is the Restaurant object to be added
     */
    private void addRestaurant(Restaurant addedRestaurant) {
        // addedRestaurant must not be null and not already present in the database
        // TODO: add Restaurant object data Connor
    }

    /**
     * Adds a review to the correct restaurant's review list, and user's review list
     *
     * POTENTIAL ISSUE: Need to decide on a protocol for a review with no correct user
     * available in the user database
     *
     * @param addedReview is the Review object to be associated with a restaurant and user
     */
    private void addReview(Review addedReview) {
        // addedReview must not be null and not already be present in the database
        // TODO: add Review object data Connor
    }

    /**
     * Adds a user to the user database
     *
     * @param addedUser is the User object to be added to the userAll list
     */
    private void addUser(User addedUser) {
        // addedUser must not be null and not already be present in the database
        // TODO: add User object data Connor
    }

    /**
     * Returns a list of Restaurant based on a provided restaurant category.
     *
     * NOTE: This will potentially have to be rewritten in the future to Stream/ParallelStream
     * format
     *
     * @param inputCategory is the String format representation of a restaurant category
     *
     * @return a List<Restaurant> containing all restaurants that are within the
     * requested category, or an empty list if there are none
     */
    public List<Restaurant> filterResByCat(String inputCategory) {
        // TODO: implement this Connor
        // Status: On hold till Structured Queries
        return null;
    }

    /**
     * Returns a list of Restaurant based on a provided restaurant location.
     *
     * NOTE: This will potentially have to be rewritten in the future to Stream/ParallelStream
     * format
     *
     * @param inputLocation is the String format representation of a restaurant location
     *
     * @return a List<Restaurant> containing all restaurants that are within the
     * requested location, or an empty list if there are none
     */
    public List<Restaurant> filterResByLoc(String inputLocation) {
        // TODO: implement this Connor
        // Status: On hold till Structured Queries
        return null;
    }

    /**
     * Returns a list of Restaurant that are in the requested inputRating score.
     *
     * NOTE: This will potentially have to be rewritten in the future to Stream/ParallelStream
     * format
     *
     * @param inputRating is the int representation of the rating score (1-5)
     *
     * @return a List<Restaurant> containing all restaurants that score the requested rating
     * or an empty list if there are none
     */
    public List<Restaurant> filterResByRat(int inputRating) {
        // TODO: implement this Connor
        // Status: On hold till Structured Queries
        return null;
    }

    /**
     * Returns a list of Restaurant that are in the requested inputPrice score.
     *
     * NOTE: This will potentially have to be rewritten in the future to Stream/ParallelStream
     * format
     *
     * @param inputPrice is the int representation of price score (1-5)
     *
     * @return a List<Restaurant> containing all restaurants that have a price score of the requested rating
     * or an empty list if there are none
     */
    public List<Restaurant> filterResByPri(int inputPrice) {
        // TODO: implement this Connor
        // Status: On hold till Structured Queries
        return null;
    }

    public Set getMatches(String queryString) {
        // To be implemented at a different MP stage
        return null;
    }

    public String kMeansClusters_json(int k) {
        // To be implemented at a different MP stage
        return null;
    }

    public ToDoubleBiFunction<MP5Db, String> getPredictorFunction(String user) {
        // To be implemented at a different MP stage
        return null;
    }
}
