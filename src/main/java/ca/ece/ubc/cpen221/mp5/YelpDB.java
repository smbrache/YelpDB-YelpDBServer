package ca.ece.ubc.cpen221.mp5;

import com.sun.org.apache.regexp.internal.RE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

//import com.sun.xml.internal.bind.v2.TODO;

import java.util.function.ToDoubleBiFunction;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class YelpDB extends AbstractMP5Db<Restaurant> {

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
	Set<Restaurant> restaurantAll;

	/**
	 * RI: reviewAll is not null. It can contain 0-n entries. reviewALl contains no
	 * duplicate elements
	 *
	 * AF: 0 <= reviewAll.size() <= databaseCapacity
	 */
	Set<Review> reviewAll;

	/**
	 * RI: userAll is not null. It can contain 0-n entries. userAll contains no
	 * duplicate elements.
	 *
	 * AF: 0 <= userAll.size() <= databaseCapacity
	 */
	Set<User> userAll;
	
	/**
	 * //Todo: Write for this?
	 * RI:
	 * 
	 * AF:
	 */
	Map<Restaurant, double[]> restCoordMap;
	
	/**
//	 * //Todo: Write for this?
	 * RI:
	 * 
	 * AF:
	 */
	//public for testing
	public Map<double[], Set<Restaurant>> centroidClusterMap;
	
	public YelpDB() {
		// TODO: test constructor
		this.restaurantAll = new HashSet<>();
		this.reviewAll = new HashSet<>();
		this.userAll = new HashSet<>();

		this.restCoordMap = new HashMap<Restaurant, double[]>();
	}

	/**
	 * Creates a new YelpDB initialized with data from three .JSON files.
	 * //todo: something about the restcoordmap centroid cluster map
	 * 
	 * @requires restaurantsJSON, reviewsJSON, usersJSON are not null, have valid
	 *           paths, and are in correct format
	 * 
	 * @param restaurantsJSON the restaurant .JSON file
	 *
	 * @param reviewsJSON the reviews .JSON file
	 *
	 * @param usersJSON the user .JSON file
	 *
	 * @throws IOException if any of the .JSON files are null, have invalid paths, or are in
	 * an incorrect format
	 */
	public YelpDB(String restaurantsJSON, String reviewsJSON, String usersJSON) throws IOException {
		// TODO: test constructor
		this.restaurantAll = new HashSet<>();
		this.reviewAll = new HashSet<>();
		this.userAll = new HashSet<>();
		this.restCoordMap = new HashMap<Restaurant, double[]>();
		this.centroidClusterMap = new HashMap<double[], Set<Restaurant>>();

		parseRestaurantJSON(restaurantsJSON);
		// double[] restCoords: restCoords[0] is x-coord (latitude)
		// 						restCoords[1] is y-coord (longitude)
		double[] restCoords = new double[2];
		
		// Map each restaurant to double[] containing its coordinates
		for (Restaurant r : this.restaurantAll) {
			restCoords[0] = r.getLatitude();
			restCoords[1] = r.getLongitude();
			this.restCoordMap.put(r, Arrays.copyOf(restCoords, restCoords.length));
		}

		parseUserJSON(usersJSON);
		parseReviewJSON(reviewsJSON);
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
	// Change Exception type to IllegalArgumentException and let java throw
	// exception if file path is invalid?
	// and just put in RI that file msut be a json but dont handle the case that the
	// file passed isn't a json?
	private void parseRestaurantJSON(String restaurantsJSON) throws IOException {
		// TODO: implement Restaurant JSON parser Sam

		if (restaurantsJSON == null) {
			throw new IOException();
		}

		// parse restaurants
		BufferedReader restaurantReader = new BufferedReader(new FileReader(restaurantsJSON));
		String line;
		while ((line = restaurantReader.readLine()) != null) {
			StringReader sr = new StringReader(line);
			JsonReader parseRestaurant = Json.createReader(sr);
			JsonObject restaurant = parseRestaurant.readObject();
			addRestaurant(restaurant);// remove
			//return restaurant;
		}
		restaurantReader.close();
		//return null;
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
	// Change Exception type to IllegalArgumentException and let java throw
	// exception if file path is invalid?
	// and just put in RI that file msut be a json but dont handle the case that the
	// file passed isn't a json?
	private void parseReviewJSON(String reviewsJSON) throws IOException {
		// TODO: implement Review JSON parser Sam

		if (reviewsJSON == null) {
			throw new IOException();
		}

		// parse reviews
		BufferedReader reviewReader = new BufferedReader(new FileReader(reviewsJSON));
		String line;
		while ((line = reviewReader.readLine()) != null) {
			StringReader sr = new StringReader(line);
			JsonReader parseReview = Json.createReader(sr);
			JsonObject review = parseReview.readObject();
			addReview(review);// remove
			//return review;
		}
		reviewReader.close();
		//return null;
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
	// Change Exception type to IllegalArgumentException and let java throw
	// exception if file path is invalid?
	// and just put in RI that file msut be a json but dont handle the case that the
	// file passed isn't a json?
	private void parseUserJSON(String usersJSON) throws IOException {
		// TODO: implement User JSON parser Sam

		if (usersJSON == null) {
			throw new IOException();
		}

		// parse users
		BufferedReader userReader = new BufferedReader(new FileReader(usersJSON));
		String line;
		while ((line = userReader.readLine()) != null) {
			StringReader sr = new StringReader(line);
			JsonReader parseUser = Json.createReader(sr);
			JsonObject user = parseUser.readObject();
			addUser(user);// remove
			//return user;
		}
		userReader.close();
		//return null;
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
		votes[0] = addedReview.getJsonObject("votes").getInt("cool");
		votes[1] = addedReview.getJsonObject("votes").getInt("useful");
		votes[2] = addedReview.getJsonObject("votes").getInt("funny");

		Review newReview = new Review(type, businessID, votes, reviewID, text, starScore, userID, date);

		if (!reviewAll.contains(newReview)) {
			reviewAll.add(newReview);
		}

		for (User currUser : userAll) {
			if (newReview.getUserId().equals(currUser.getUserId())) {
				currUser.addReview(newReview, true);
				break;
			}
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
		votes[0] = addedUser.getJsonObject("votes").getInt("cool");
		votes[1] = addedUser.getJsonObject("votes").getInt("useful");
		votes[2] = addedUser.getJsonObject("votes").getInt("funny");

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

	/**
	 * getMatches finds and returns a single-set Restaurant that matches
	 * the ID of the queryString
	 *
	 * @param queryString the restaurantID of the search target object
	 *
	 * @return a single-set reference to a Restaurant if it exists in the database
	 * and an empty-set if it does not
	 */
	public Set<Restaurant> getMatches(String queryString) {
		Set<Restaurant> returnSet = new HashSet<>();

		for (Restaurant currRestaurant : restaurantAll) {
			// If a match is found, add it and exit the loop
			if (currRestaurant.getBusinessId().equals(queryString)) {
				returnSet.add(currRestaurant);
				break;
			}
		}
		return returnSet;
	}

	public List<Restaurant> getRestaurantAll() {
		List<Restaurant> restaurantAllCopy = new ArrayList<Restaurant>();
		for (Restaurant r : this.restaurantAll) {
			restaurantAllCopy.add(r);
		}
		return restaurantAllCopy;
	}

	// Probably don't need these
	//
	// public List<Review> getReviewAll(){
	// List<Review> reviewAllCopy = new ArrayList<Review>();
	// for(Review r : this.reviewAll) {
	// reviewAllCopy.add(r);
	// }
	// return reviewAllCopy;
	// }
	//
	// public List<User> getUserAll(){
	// List<User> userAllCopy = new ArrayList<User>();
	// for(User r : this.userAll) {
	// userAllCopy.add(r);
	// }
	// return userAllCopy;
	// }

	public String kMeansClusters_json(int k) {
		
		List<Set<Restaurant>> kMeansClusters = this.kMeansClustering(k);
		String kMeansClustersJSON = "[";
		String rString = "";
		int clusterNumber = 1;
		for(Set<Restaurant> cluster : kMeansClusters) {
			for(Restaurant r : cluster) {
				rString += "{\"x\": " + r.getLatitude() + ", \"y\": " + r.getLongitude() + ", \"name\": \"" + r.getName() + "\", \"cluster\": " + clusterNumber + ", \"weight\": 1.0}, ";
			}
			clusterNumber++;
		}
		rString = rString.substring(0, rString.length() - 2);
		kMeansClustersJSON = rString + "]";
		
		return kMeansClustersJSON;
	}

	public List<Set<Restaurant>> kMeansClustering(int k) {

		List<Set<Restaurant>> kMeansClusters = new ArrayList<Set<Restaurant>>();
		List<double[]> centroidList = new ArrayList<double[]>();

		// double[] centroidCoords: centroidCoords[0] is x-coord (latitude)
		// 							centroidCoords[1] is y-coord (longitude)
		double[] centroidCoords = new double[2];
		List<Restaurant> allRestaurants = this.getRestaurantAll();
		Restaurant r;
		
		// Initialize centroid coordinates
		Collections.shuffle(new ArrayList<Restaurant>(this.restaurantAll));
		for(int i = 0; i < k; i++) {
			if (i < allRestaurants.size()) {
				r = allRestaurants.get(i);
				centroidCoords = this.restCoordMap.get(r);
				centroidList.add(centroidCoords);
			}
		}
		
		List<double[]> prevCentroidList;
		boolean centroidsChanged = true;
		
		do {
			prevCentroidList = centroidList;
			this.group(centroidList);
			centroidList = this.recalculateCentroids(centroidList);
			
			//Does this comparison work?
			if(prevCentroidList.equals(centroidList)) {
				centroidsChanged = false;
			}
		}while(centroidsChanged);
		
		for(Map.Entry<double[], Set<Restaurant>> centroidClusterMapEntry : this.centroidClusterMap.entrySet()) {
			if((centroidClusterMapEntry.getValue()).isEmpty()) {
				return this.kMeansClustering(k);
			} else {
				kMeansClusters.add(centroidClusterMapEntry.getValue());
			}
		}
		
		return kMeansClusters;
	}
	
	/**
	 * 
	 * @param centroidList
	 * @return
	 */
	private List<double[]> recalculateCentroids(List<double[]> centroidList) {
		centroidList.removeAll(centroidList);
		
		// double[] centroidCoords: centroidCoords[0] is x-coord (latitude)
		// 							centroidCoords[1] is y-coord (longitude)
		double[] centroidCoords = new double[2];
		double avgLat = 0;
		double avgLong = 0;
		double totLat = 0;
		double totLong = 0;

		for(Map.Entry<double[], Set<Restaurant>> centroidClusterEntry : centroidClusterMap.entrySet()) {
			Set<Restaurant> restSet = centroidClusterEntry.getValue();
			for(Restaurant resto : restSet) {
				totLat += resto.getLatitude();
				totLong += resto.getLongitude();
			}
			avgLat = totLat/(restSet.size());
			avgLong = totLong/(restSet.size());
			centroidCoords[0] = avgLat;
			centroidCoords[1] = avgLong;
			centroidList.add(centroidCoords);
		}
		return centroidList;
	}
	
	/**
	 * 
	 * @param centroidList
	 */
	private void group(List<double[]> centroidList){
		
		int iterations = 0;
		for(double[] centroid : centroidList) {
			centroidClusterMap.put(centroid, new HashSet<Restaurant>());
			iterations++;
		}
		
		double[] closestCentroid = null;
		for(Restaurant rest : this.restaurantAll) {
			double minDist = Double.MAX_VALUE;
			for(double [] centroid : centroidList) {
				if(Distance(restCoordMap.get(rest), centroid) < minDist) {
					minDist = Distance(restCoordMap.get(rest), centroid);
					closestCentroid = centroid;
				}
			}
			(centroidClusterMap.get(closestCentroid)).add(rest);
		}
	}

	/**
	 * Returns the distance between a restaurant and the centroid of a k-mean
	 * cluster.
	 * 
	 * @requires: restaurantCoords and centroid are not null
	 * 
	 * @param restaurantCoords
	 *            the cartesian coordinates of the restaurant
	 * @param centroid
	 *            the cartesian coordinates of the centroid
	 * @return the distance between the restaurant and the centroid
	 */
	//public for testing
	public static double Distance(double[] restaurantCoords, double[] centroid) {
		double distance;
		double xDist = Math.abs(centroid[0] - restaurantCoords[0]);
		double yDist = Math.abs(centroid[1] - restaurantCoords[1]);
		distance = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
		return distance;
	}


	/**
	 * Returns a predictor function that estimates a user's potential rating of a restaurant
	 * derived from the least squares linear regression formula
	 *
	 * @param user is a valid userID in the database
	 *
	 * @return a functional interface that estimates the potential user rating of a restaurant
	 * (clamped between 1-5) given a MP5Db<Restaurant> and String representation of businessID
	 * are supplied, else, an always 0 function if the userID does not exist
	 *
	 * @requires YelpDB database is complete and all reviews point to valid restaurants
	 */
	public ToDoubleBiFunction<MP5Db<Restaurant>, String> getPredictorFunction(String user) {
		User reqUser = searchUser(user);
		ToDoubleBiFunction<MP5Db<Restaurant>, String> leastSquaresRegression = (x,y) -> 0;

		// User cannot be found in the database
		if (reqUser == null)
			return leastSquaresRegression;

		List<Integer> allX = new ArrayList<>();
		List<Integer> allY = new ArrayList<>();
		double xTot = 0.0;
		double yTot = 0.0;

		System.out.println("Num Reviews " + reqUser.getUserReview().size());

		for (Review currReview : reqUser.getUserReview()) {
			// Retrieve the restaurant price score
			allX.add(searchRestaurant(currReview.getBusinessId()).getPriceScore());
			xTot += allX.get(allX.size() - 1);
			// Retrieve the user rating score
			allY.add(currReview.getStars());
			yTot += allY.get(allY.size() - 1);
			System.out.println("xCurr: " + allX.get(allX.size()-1) + " yTot: " + currReview.getStars());
		}
		System.out.println("xTot: " + xTot + " yTot: " + yTot);
		// No reviews for the userID
		if (xTot == 0)
			return leastSquaresRegression;

		// Reconvert xTot and yTot into mean(x) and mean(y)
		xTot = xTot/allX.size();
		yTot = yTot/allY.size();

		System.out.println("xTot: " + xTot + " yTot: " + yTot);

		// Calculate the sums of squares sXX, sXY
		double sXX = 0.0;
		double sXY = 0.0;
		for (int i = 0; i < allX.size(); i++) {
			sXX += Math.pow((allX.get(i) - xTot), 2.0);
			sXY += (allX.get(i) - xTot)*(allY.get(i) - yTot);
			System.out.println("Curr sXX: " + sXX + " Curr sXY " + sXY);
		}

		// Edge case to prevent division by zero on 0/0
		if (sXX == 0) sXX = 1;
		System.out.println("sXX: " + sXX + " sXY: " + sXY);

		// Calculate the linear coefficients
		double B = sXY/sXX;
		double A = yTot - B*xTot;

		leastSquaresRegression = (x,y) -> {
			double returnNum;
			double priceScore = 0.0;
			for (Restaurant currRestaurant: x.getMatches(y))
				priceScore = currRestaurant.getPriceScore();
			System.out.println("A: " + A + " priceScore: " + priceScore + " B: " + B);
			returnNum = Math.max(A * priceScore + B, 1.0);
			return Math.min(returnNum, 5.0);
		};

		return leastSquaresRegression;
	}

	/**
	 * Searches the userAll database for a match to reqUser
	 *
	 * @param reqUser the userID of the search target object
	 *
	 * @return by-reference User object if it exists, and null if it can't be found
	 */
	private User searchUser(String reqUser) {
		for (User currUser : userAll) {
			if (currUser.getUserId().equals(reqUser))
				return currUser;
		}
		return null;
	}

	/**
	 * Searches the restaurantAll database for a match to reqRestaurant
	 *
	 * @param reqRestaurant the userID of the search target object
	 *
	 * @return by-reference Restaurant object if it exists, and null if it can't be found
	 */
	private Restaurant searchRestaurant(String reqRestaurant) {
		for (Restaurant currRestaurant : restaurantAll) {
			if (currRestaurant.getBusinessId().equals(reqRestaurant))
				return currRestaurant;
		}
		return null;
	}

	public void addUser(User newUser) {
		if (!userAll.contains(newUser)) {
			userAll.add(newUser);
		}
	}
}
