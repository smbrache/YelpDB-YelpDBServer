package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

//import com.sun.xml.internal.bind.v2.TODO;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class YelpDB implements MP5Db {

	List<Restaurant> restaurantAll;
	/**
	 * RI: restaurantAll (and filter categories) are never null. They can contain
	 * 0-n entries. restaurantAll contains no duplicate elements. Any Restaurant
	 * element can appear in more than one category, or location, but no more than
	 * one rating or price.
	 *
	 * AF: 0 <= restaurantAll.size() <= databaseCapacity restaurantAll.size() >=
	 * restaurantByCategory.size(), restaurantByLocation.size(),
	 * restaurantByRating.size(), restaurantByPrice.size()
	 */

	Map<String, List<Restaurant>> restaurantByCategory;
	Map<String, List<Restaurant>> restaurantByLocation;
	Map<Integer, List<Restaurant>> restaurantByRating;
	Map<Integer, List<Restaurant>> restaurantByPrice;

	/**
	 * RI: userAll is not null. It can contain 0-n entries. userAll contains no
	 * duplicate elements.
	 *
	 * AF: 0 <= userAll.size() <= databaseCapacity
	 */
	List<User> userAll;

	List<Review> reviewAll;

	public YelpDB() {
		// TODO: construct empty YelpDB Sam
		this.restaurantAll = new ArrayList<Restaurant>();
		this.restaurantByCategory = new HashMap<String, List<Restaurant>>();
		this.restaurantByLocation = new HashMap<String, List<Restaurant>>();
		this.restaurantByPrice = new HashMap<Integer, List<Restaurant>>();
		this.restaurantByRating = new HashMap<Integer, List<Restaurant>>();
		this.reviewAll = new ArrayList<Review>();
		this.userAll = new ArrayList<User>();
	}

	/**
	 * Creates a new YelpDB initialized with data from three .json files.
	 * 
	 * @requires restaurantsJSON, reviewsJSON, usersJSON are not null, have valid
	 *           paths, and are in correct format
	 * 
	 * @param restaurantsJSON
	 *            the .json file containing restaurant specific data
	 * @param reviewsJSON
	 *            the .json file containing review specific data
	 * @param usersJSON
	 *            the .json file containing user specific data
	 * @throws IOException
	 *             if any of the .json files are null, have invalid paths, or are in
	 *             incorrect format
	 */
	public YelpDB(String restaurantsJSON, String reviewsJSON, String usersJSON) throws IOException {
		// TODO: construct initialized YelpDB Sam
		this.restaurantAll = new ArrayList<Restaurant>();
		this.restaurantByCategory = new HashMap<String, List<Restaurant>>();
		this.restaurantByLocation = new HashMap<String, List<Restaurant>>();
		this.restaurantByPrice = new HashMap<Integer, List<Restaurant>>();
		this.restaurantByRating = new HashMap<Integer, List<Restaurant>>();
		this.reviewAll = new ArrayList<Review>();
		this.userAll = new ArrayList<User>();
		parseRestaurantJSON(restaurantsJSON);
		parseReviewJSON(reviewsJSON);
		parseUserJSON(usersJSON);

	}

	/**
	 * Parses restaurants.json file: creates a new Restaurant object for each
	 * restaurant in the .json file, complete with all its data.
	 * 
	 * @requires: restaurantsJSON is not null, is in correct format, and has valid
	 *            path
	 * 
	 * @param restaurantsJSON
	 *            the restaurants.json file to be parsed
	 * @throws IOException
	 *             if file is null, is in incorrect format, or has invalid path
	 */
	// Changed to public and added return value for testing**************************************************
	public JsonObject parseRestaurantJSON(String restaurantsJSON) throws IOException {
		// TODO: implement Restaurant JSON parser Sam

		// parse restaurants
		BufferedReader restaurantReader = new BufferedReader(new FileReader(restaurantsJSON));
		String line;
		while ((line = restaurantReader.readLine()) != null) {
			StringReader sr = new StringReader(line);
			JsonReader parseRestaurant = Json.createReader(sr);
			JsonObject restaurant = parseRestaurant.readObject();
			addRestaurant(restaurant);// remove
			return restaurant;
		}
		restaurantReader.close();
		return null;// remove
	}

	/**
	 * Parses reviews.json file: creates a new Review object for each review in the
	 * .json file, complete with all its data.
	 * 
	 * @requires: reviewsJSON is not null, is in correct format, and has valid path
	 * 
	 * @param reviewsJSON
	 *            the reviews.json file to be parsed
	 * @throws IOException
	 *             if file is null, is in incorrect format, or has invalid path
	 */
	// Changed to public and added return value for testing**************************************************
	public JsonObject parseReviewJSON(String reviewsJSON) throws IOException {
		// TODO: implement Review JSON parser Sam

		// parse reviews
		BufferedReader reviewReader = new BufferedReader(new FileReader(reviewsJSON));
		String line;
		while ((line = reviewReader.readLine()) != null) {
			StringReader sr = new StringReader(line);
			JsonReader parseReview = Json.createReader(sr);
			JsonObject review = parseReview.readObject();
			addReview(review);// remove
			return review;
		}
		reviewReader.close();
		return null;// remove
	}

	/**
	 * Parses users.json file: creates a new User object for each user in the .json
	 * file, complete with all its data.
	 * 
	 * @requires: usersJSON is not null, is in correct format, and has valid path
	 * 
	 * @param usersJSON
	 *            the users.json file to be parsed
	 * @throws IOException
	 *             if file is null, is in incorrect format, or has invalid path
	 */
	// Changed to public and added return value for testing**************************************************
	public JsonObject parseUserJSON(String usersJSON) throws IOException {
		// TODO: implement User JSON parser Sam

		// parse users
		BufferedReader userReader = new BufferedReader(new FileReader(usersJSON));
		String line;
		while ((line = userReader.readLine()) != null) {
			StringReader sr = new StringReader(line);
			JsonReader parseUser = Json.createReader(sr);
			JsonObject user = parseUser.readObject();
			addUser(user);// remove
			return user;
		}
		userReader.close();
		return null;// remove
	}

	/**
	 * Adds a new restaurant to all relevant category maps and restaurantAll list
	 *
	 * @param addedRestaurant
	 *            is the Restaurant object to be added
	 */
	private void addRestaurant(JsonObject addedRestaurant) {
		// addedRestaurant must not be null and not already present in the database
		// TODO: add Restaurant object data Connor
		// TODO: add restaurants to relevant maps Sam

		String type = addedRestaurant.getString("type");
		boolean isOpen = addedRestaurant.getBoolean("open");
		String url = addedRestaurant.getString("url");
		double longitude = addedRestaurant.getJsonNumber("longitude").doubleValue();
		double latitude = addedRestaurant.getJsonNumber("latitude").doubleValue();
		String state = addedRestaurant.getString("state");
		String city = addedRestaurant.getString("city");
		String fullAddress = addedRestaurant.getString("full_address");
		String businessID = addedRestaurant.getString("business_id");
		String name = addedRestaurant.getString("name");
		double starScore = addedRestaurant.getJsonNumber("stars").doubleValue();
		int reviewCount = addedRestaurant.getInt("review_count");
		String photoURL = addedRestaurant.getString("photo_url");
		int priceScore = addedRestaurant.getInt("price");

		JsonArray schoolsArray = addedRestaurant.getJsonArray("schools");
		JsonArray categoriesArray = addedRestaurant.getJsonArray("categories");
		JsonArray neighborhoodsArray = addedRestaurant.getJsonArray("neighborhoods");

		List<String> schools = new ArrayList<String>();
		for (int index = 0; index < schoolsArray.size(); index++) {
			schools.add(schoolsArray.getString(index));
		}

		List<String> categories = new ArrayList<String>();
		for (int index = 0; index < categoriesArray.size(); index++) {
			categories.add(categoriesArray.getString(index));
		}

		List<String> neighborhoods = new ArrayList<String>();
		for (int index = 0; index < neighborhoodsArray.size(); index++) {
			neighborhoods.add(neighborhoodsArray.getString(index));
		}

		Restaurant newRestaurant = new Restaurant(isOpen, url, longitude, neighborhoods, businessID, name, categories,
				state, type, starScore, city, fullAddress, reviewCount, photoURL, schools, latitude, priceScore);

		// Do we need to verify how contains works?/Override hashCode or equals methods?
		if (!this.restaurantAll.contains(newRestaurant)) {
			 this.restaurantAll.add(newRestaurant);
		}

	}

	/**
	 * Adds a review to the correct restaurant's review list, user's review list,
	 * and the reviewAll list.
	 *
	 * POTENTIAL ISSUE: Need to decide on a protocol for a review with no correct
	 * user available in the user database
	 * 
	 * RI: Review does not already exist in reviewAll
	 *
	 * @param addedReview
	 *            is the Review object to be associated with a restaurant and user
	 */
	private void addReview(JsonObject addedReview) {
		// addedReview must not be null and not already be present in the database
		// TODO: add Review object data Connor
		// TODO: add review to correct restaurant's review list and user's review list
		// Sam

		String type = addedReview.getString("type");
		String businessID = addedReview.getString("business_id");
		String reviewID = addedReview.getString("review_id");
		String text = addedReview.getString("text");
		int starScore = addedReview.getInt("stars");
		String userID = addedReview.getString("user_id");
		String date = addedReview.getString("date");
		int[] votes = new int[3];
		votes[0] = addedReview.getInt("cool");
		votes[1] = addedReview.getInt("useful");
		votes[2] = addedReview.getInt("funny");

		Review newReview = new Review(type, businessID, votes, reviewID, text, starScore, userID, date);

		if (!reviewAll.contains(newReview)) {
			reviewAll.add(newReview);
		}

	}

	/**
	 * Adds a user to the user database
	 *
	 * RI: User does not already exist in userAll
	 *
	 * @param addedUser
	 *            is the User object to be added to the userAll list
	 */
	private void addUser(JsonObject addedUser) {
		// addedUser must not be null and not already be present in the database
		// TODO: add User object data Connor

		String url = addedUser.getString("url");
		int reviewCount = addedUser.getInt("review_count");
		String type = addedUser.getString("type");
		String userID = addedUser.getString("user_id");
		String name = addedUser.getString("name");
		double avgStars = addedUser.getJsonNumber("average_stars").doubleValue();
		int[] votes = new int[3];
		votes[0] = addedUser.getInt("cool");
		votes[1] = addedUser.getInt("useful");
		votes[2] = addedUser.getInt("funny");

		User newUser = new User(url, votes, reviewCount, type, userID, name, avgStars);
		
		if (!userAll.contains(newUser)) {
			userAll.add(newUser);
		}
	}

	/**
	 * Returns a list of Restaurant based on a provided restaurant category.
	 *
	 * NOTE: This will potentially have to be rewritten in the future to
	 * Stream/ParallelStream format
	 *
	 * @param inputCategory
	 *            is the String format representation of a restaurant category
	 *
	 * @return a List<Restaurant> containing all restaurants that are within the
	 *         requested category, or an empty list if there are none
	 */
	public List<Restaurant> filterResByCat(String inputCategory) {
		// TODO: implement this Connor
		// Status: On hold till Structured Queries
		return null;
	}

	/**
	 * Returns a list of Restaurant based on a provided restaurant location.
	 *
	 * NOTE: This will potentially have to be rewritten in the future to
	 * Stream/ParallelStream format
	 *
	 * @param inputLocation
	 *            is the String format representation of a restaurant location
	 *
	 * @return a List<Restaurant> containing all restaurants that are within the
	 *         requested location, or an empty list if there are none
	 */
	public List<Restaurant> filterResByLoc(String inputLocation) {
		// TODO: implement this Connor
		// Status: On hold till Structured Queries
		return null;
	}

	/**
	 * Returns a list of Restaurant that are in the requested inputRating score.
	 *
	 * NOTE: This will potentially have to be rewritten in the future to
	 * Stream/ParallelStream format
	 *
	 * @param inputRating
	 *            is the int representation of the rating score (1-5)
	 *
	 * @return a List<Restaurant> containing all restaurants that score the
	 *         requested rating or an empty list if there are none
	 */
	public List<Restaurant> filterResByRat(int inputRating) {
		// TODO: implement this Connor
		// Status: On hold till Structured Queries
		return null;
	}

	/**
	 * Returns a list of Restaurant that are in the requested inputPrice score.
	 *
	 * NOTE: This will potentially have to be rewritten in the future to
	 * Stream/ParallelStream format
	 *
	 * @param inputPrice
	 *            is the int representation of price score (1-5)
	 *
	 * @return a List<Restaurant> containing all restaurants that have a price score
	 *         of the requested rating or an empty list if there are none
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
