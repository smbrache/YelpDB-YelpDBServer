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
        // TODO: construct empty YelpDB
    }

    public YelpDB(String inputRestaurantL, String inputReviewL, String inputUserL) {
        // TODO: implement data parser
    }

    // Functional Helper
    private void parseRestaurantList() {

    }

    // Functional Helper
    private void parseReviewList() {

    }

    // Functional Helper
    private void parseUserList() {

    }

    private void addRestaurant() {

    }

    private void addReview() {

    }

    private void addUser() {

    }

    public List<Restaurant> filterResByCat(String inputCategory) {
        return null;
    }

    public List<Restaurant> filterResByLoc(String inputLocation) {
        return null;
    }

    public List<Restaurant> filterResByRat(String inputRating) {
        return null;
    }

    public List<Restaurant> filterResByPri(String inputPrice) {
        return null;
    }

    public Set getMatches(String queryString) {
        return null;
    }

    public String kMeansClusters_json(int k) {
        return null;
    }

    public ToDoubleBiFunction<MP5Db, String> getPredictorFunction(String user) {
        return null;
    }
}
