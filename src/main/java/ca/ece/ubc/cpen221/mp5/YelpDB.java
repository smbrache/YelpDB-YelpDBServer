package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

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
	 * RI: restCoordMap is not null, it can contain 0-n entries and no duplicates
	 * 
	 * AF: 0 <= number of entries is restCoordMap  = number of restaurants in the database <= database capacity
	 */
	Map<Restaurant, double[]> restCoordMap;

	/**
	 * RI: centroidClusterMap is not null, can contain 0-n entries and no duplicates
	 * 
	 * AF: 0 <= number of entries in centroidClusterMap 
	 */
	// public for testing
	public Map<double[], Set<Restaurant>> centroidClusterMap;

	/**
	 * Initializes an empty YelpDB.
	 */
	public YelpDB() {
		this.restaurantAll = new HashSet<>();
		this.reviewAll = new HashSet<>();
		this.userAll = new HashSet<>();
		this.restCoordMap = new HashMap<Restaurant, double[]>();
	}

	/**
	 * Creates a new YelpDB initialized with data from three .JSON files. 
	 * 
	 * @requires restaurantsJSON, reviewsJSON, usersJSON are not null, have valid
	 *           paths, and are in correct format
	 * 
	 * @param restaurantsJSON
	 *            the restaurant .JSON file
	 *
	 * @param reviewsJSON
	 *            the reviews .JSON file
	 *
	 * @param usersJSON
	 *            the user .JSON file
	 *
	 * @throws IOException
	 *             if any of the .JSON files are null, have invalid paths, or are
	 *             not .json files
	 */
	public YelpDB(String restaurantsJSON, String reviewsJSON, String usersJSON) throws IOException {
		this.restaurantAll = new HashSet<>();
		this.reviewAll = new HashSet<>();
		this.userAll = new HashSet<>();
		this.restCoordMap = new HashMap<Restaurant, double[]>();
		this.centroidClusterMap = new HashMap<double[], Set<Restaurant>>();

		parseRestaurantJSON(restaurantsJSON);
		// double[] restCoords: restCoords[0] is x-coord (latitude)
		// restCoords[1] is y-coord (longitude)
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
	 * @requires: restaurantsJSON is not null, is a .json file, and has valid path
	 * 
	 * @param restaurantsJSON
	 *            the restaurants.json file to be parsed
	 * @throws IOException
	 *             if file is null, is not a .json file, or has invalid path
	 */
	private void parseRestaurantJSON(String restaurantsJSON) throws IOException {

		if (restaurantsJSON == null || !(restaurantsJSON.substring(restaurantsJSON.length() - 5)).equals(".json")) {
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
			// return restaurant;
		}
		restaurantReader.close();
		// return null;
	}

	/**
	 * Parses reviews.json file: creates a new Review object for each review in the
	 * .json file, complete with all its data.
	 * 
	 * @requires: reviewsJSON is not null, is a .json file, and has valid path
	 * 
	 * @param reviewsJSON
	 *            the reviews.json file to be parsed
	 * @throws IOException
	 *             if file is null, is not a .json file, or has invalid path
	 */
	private void parseReviewJSON(String reviewsJSON) throws IOException {
		// TODO: implement Review JSON parser Sam

		if (reviewsJSON == null || !(reviewsJSON.substring(reviewsJSON.length() - 5)).equals(".json")) {
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
			// return review;
		}
		reviewReader.close();
		// return null;
	}

	/**
	 * Parses users.json file: creates a new User object for each user in the .json
	 * file, complete with all its data.
	 * 
	 * @requires: usersJSON is not null, is a .json file, and has valid path
	 * 
	 * @param usersJSON
	 *            the users.json file to be parsed
	 * @throws IOException
	 *             if file is null, is not a .json file, or has invalid path
	 */
	private void parseUserJSON(String usersJSON) throws IOException {

		if (usersJSON == null || !(usersJSON.substring(usersJSON.length() - 5)).equals(".json")) {
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
			// return user;
		}
		userReader.close();
		// return null;
	}

	/**
	 * Adds a new restaurant to all relevant category maps and restaurantAll list
	 *
	 * @param addedRestaurant
	 *            is the Restaurant object to be added
	 */
	private void addRestaurant(JsonObject addedRestaurant) {
		// addedRestaurant must not be null and not already present in the database

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
	 * RI: Review does not already exist in reviewAll
	 *
	 * @param addedReview
	 *            is the Review object to be associated with a restaurant and user
	 */
	private void addReview(JsonObject addedReview) {
		// addedReview must not be null and not already be present in the database

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
				currUser.addReview(newReview);
				break;
			}
		}

		for (Restaurant currRestaurant : restaurantAll) {
			if (newReview.getBusinessId().equals(currRestaurant.getBusinessId())) {
				currRestaurant.addReview(newReview);
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
	/*
	public List<Restaurant> filterResByCat(String inputCategory) {
		// To be implemented later
		return null;
	}
	*/

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
	/*
	public List<Restaurant> filterResByLoc(String inputLocation) {
		// Status: To be implemented later
		return null;
	}
	*/

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
	/*
	public List<Restaurant> filterResByRat(int inputRating) {
		// To be implemented later
		return null;
	}
	*/

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
	/*
	public List<Restaurant> filterResByPri(int inputPrice) {
		// To be implemented later
		return null;
	}
	*/

	/**
	 * getMatches finds and returns a single-set Restaurant that matches the ID of
	 * the queryString
	 *
	 * @param queryString
	 *            the restaurantID of the search target object
	 *
	 * @return a single-set reference to a Restaurant if it exists in the database
	 *         and an empty-set if it does not
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

	/**
	 * Returns a copy of the this.restaurantAll, the list of all restaurants in the
	 * database, with no references to this.restaurantAll.
	 * 
	 * @requires: this.restaurantAll is not null
	 * @effects: returns a copy of this.restaurantAll with no references to
	 *           this.restaurantAll
	 * 
	 * @return the list of Restaurants in the database
	 */
	public List<Restaurant> getRestaurantAll() {
		List<Restaurant> restaurantAllCopy = new ArrayList<Restaurant>();
		for (Restaurant r : this.restaurantAll) {
			restaurantAllCopy.add(r);
		}
		return restaurantAllCopy;
	}

	/**
	 * Puts Restaurants in the database into k clusters based on their location, and
	 * decribes the clustering in Json format.
	 * 
	 * @requires: k is not null, k <= the number of Restaurants in the database
	 * @effects: groups Restaurants into clusters based on location
	 * 
	 * @param k
	 *            the number of clusters to put Restaurants into
	 * 
	 * @return a String that describes the clustering in Json format
	 * 
	 */
	public String kMeansClusters_json(int k) {

		List<Set<Restaurant>> kMeansClusters = this.kMeansClustering(k);
		String kMeansClustersJSON = "[";
		String rString = "";
		int clusterNumber = 1;

		// Adds each Restaurant's information to the String in Json format
		for (Set<Restaurant> cluster : kMeansClusters) {
			for (Restaurant r : cluster) {
				rString += "{\"x\": " + r.getLatitude() + ", \"y\": " + r.getLongitude() + ", \"name\": \""
						+ r.getName() + "\", \"cluster\": " + clusterNumber + ", \"weight\": 1.0}, ";
			}
			clusterNumber++;
		}

		rString = rString.substring(0, rString.length() - 2);
		kMeansClustersJSON = rString + "]";

		return kMeansClustersJSON;
	}

	/**
	 * Puts all Restaurants in the database into k clusters based on their location.
	 * 
	 * @requires: k is not null, k <= the number of Restaurants in the database
	 * @effects: groups Restaurants into clusters based on location
	 * 
	 * @param k
	 *            the number of clusters to group Restaurants into
	 * @return a list of sets of Restaurants, each set represents a cluster
	 * 
	 */
	public List<Set<Restaurant>> kMeansClustering(int k) {

		List<Set<Restaurant>> kMeansClusters = new ArrayList<Set<Restaurant>>();
		List<double[]> centroidList = new ArrayList<double[]>();

		// double[] centroidCoords: centroidCoords[0] is x-coord (latitude)
		// centroidCoords[1] is y-coord (longitude)
		double[] centroidCoords = new double[2];
		List<Restaurant> allRestaurants = this.getRestaurantAll();
		Restaurant r;

		// Initialize centroid coordinates
		Collections.shuffle(allRestaurants);
		for (int i = 0; i < k; i++) {
			if (i < allRestaurants.size()) {
				r = allRestaurants.get(i);
				centroidCoords = this.restCoordMap.get(r);
				centroidList.add(centroidCoords);
			}
		}

		List<double[]> prevCentroidList;
		boolean centroidsChanged = true;
		boolean centroidsAreClosest = false;

		do {
			// keep track of the last set of centroids
			prevCentroidList = centroidList;

			// group restaurants around centroids
			this.group(centroidList);

			// recalculate centroid positions
			centroidList = this.recalculateCentroids(centroidList);

			// if the centroids' positions did not change after recalculation, set centroidsChanged to false
			if (prevCentroidList.equals(centroidList)) {
				centroidsChanged = false;
				centroidsAreClosest = this.centroidsAreClosest(centroidList);
			}
			
		} while (centroidsChanged && !centroidsAreClosest);

		for (Map.Entry<double[], Set<Restaurant>> centroidClusterMapEntry : this.centroidClusterMap.entrySet()) {
			// If the current clutser is empty, run kMeansClustering again
			// (the next time the method runs it will choose different initial centroids,
			// eventually a list of non-empty clusters will be returned)
			if ((centroidClusterMapEntry.getValue()).isEmpty()) {
				return this.kMeansClustering(k);
				// If the current cluster in not empty, add it to the list
			} else {
				kMeansClusters.add(centroidClusterMapEntry.getValue());
			}
			
		}

		return kMeansClusters;
	}

	/**
	 * Checks if the closest centroid to each restaurant is the centroid in its cluster.
	 * 
	 * @requires: centroidList is not null
	 * 
	 * @param centroidList the list of centroids to use to find which centroid is closest to each restaurant
	 * @return true if the closest centroid to each restaurant is the restaurant in its cluster
	 */
	public boolean centroidsAreClosest( List<double[]> centroidList){
		
		for (Map.Entry<double[], Set<Restaurant>> centroidClusterMapEntry : this.centroidClusterMap.entrySet()) {
			
			//for each restaurant in the cluster
			Set<Restaurant> restSet = centroidClusterMapEntry.getValue();
			for (Restaurant restaurant : restSet) {
				
				//find the closest centroid to the restaurant
				double[] closestCentroid = null;
				double minDist = Double.MAX_VALUE;
				double[] restCoords = new double[2];
				restCoords[0] = restaurant.getLatitude();
				restCoords[1] = restaurant.getLongitude();
				for (double[] centroid : centroidList) {
					if (YelpDB.Distance(restCoords, centroid) < minDist) {
						minDist = YelpDB.Distance(restCoords, centroid);
						closestCentroid = centroid;
					}
				}
				
				// if the closest centroid to a restaurant is not the centroid in its cluster return false
				if(!closestCentroid.equals(centroidClusterMapEntry.getKey())) {
					return false;
				}

			}
			
		}
		
		return true;
	}
	
	/**
	 * Changes the coordinates of each centroid in centroidList to be the average
	 * position of all the restaurants in each centroid's cluster.
	 * 
	 * @requires: centroidList is not null
	 * @effects: changes the coordinates of each centroid to be the average position
	 *           of the restaurants in each centroid's cluster
	 * 
	 * @modifies: centroidList
	 * 
	 * @param centroidList
	 *            the List of centroids to modify
	 * @return a List of the new centroid coordinates
	 */
	private List<double[]> recalculateCentroids(List<double[]> centroidList) {
		// Empty the List
		centroidList.removeAll(centroidList);

		// double[] centroidCoords: centroidCoords[0] is x-coord (latitude)
		// centroidCoords[1] is y-coord (longitude)
		double[] centroidCoords = new double[2];
		double avgLat = 0;
		double avgLong = 0;
		double totLat = 0;
		double totLong = 0;

		// Set the coordinates of each centroid to be the average position of all the
		// restaurants in the cluster
		for (Map.Entry<double[], Set<Restaurant>> centroidClusterEntry : centroidClusterMap.entrySet()) {
			Set<Restaurant> restSet = centroidClusterEntry.getValue();
			for (Restaurant resto : restSet) {
				totLat += resto.getLatitude();
				totLong += resto.getLongitude();
			}
			avgLat = totLat / (restSet.size());
			avgLong = totLong / (restSet.size());
			centroidCoords[0] = avgLat;
			centroidCoords[1] = avgLong;
			centroidList.add(centroidCoords);
		}
		return centroidList;
	}

	/**
	 * Put each restaurant in the cluster of the centroid it is closest to.
	 * 
	 * @requires: centroidList is not null
	 * @effects: puts each restaurant in the cluster of the centroid closest to it
	 * 
	 * @param centroidList
	 *            the List of existing centroids
	 */
	private void group(List<double[]> centroidList) {

		// Empty each centroid's cluster
		for (double[] centroid : centroidList) {
			centroidClusterMap.put(centroid, new HashSet<Restaurant>());
		}

		// Find the closest centroid to each restaurant and put the restaurant in that
		// centroid's cluster
		double[] closestCentroid = null;
		for (Restaurant rest : this.restaurantAll) {
			double minDist = Double.MAX_VALUE;
			for (double[] centroid : centroidList) {
				if (Distance(restCoordMap.get(rest), centroid) < minDist) {
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
	public static double Distance(double[] restaurantCoords, double[] centroid) {
		double distance;
		double xDist = Math.abs(centroid[0] - restaurantCoords[0]);
		double yDist = Math.abs(centroid[1] - restaurantCoords[1]);
		distance = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
		return distance;
	}

	/**
	 * Returns a predictor function that estimates a user's potential rating of a
	 * restaurant derived from the least squares linear regression formula
	 *
	 * @param user
	 *            is a valid userID in the database
	 *
	 * @return a functional interface that estimates the potential user rating of a
	 *         restaurant (clamped between 1-5) given a MP5Db<Restaurant> and String
	 *         representation of businessID are supplied, else, an always 0 function
	 *         if the userID does not exist
	 *
	 * @requires YelpDB database is complete and all reviews point to valid
	 *           restaurants
	 */
	public ToDoubleBiFunction<MP5Db<Restaurant>, String> getPredictorFunction(String user) {
		User reqUser = searchUser(user);
		ToDoubleBiFunction<MP5Db<Restaurant>, String> leastSquaresRegression = (x, y) -> 0;

		// User cannot be found in the database
		if (reqUser == null)
			return leastSquaresRegression;

		List<Integer> allX = new ArrayList<>();
		List<Integer> allY = new ArrayList<>();
		double xTot = 0.0;
		double yTot = 0.0;

		for (Review currReview : reqUser.getUserReview()) {
			// Retrieve the restaurant price score
			allX.add(searchRestaurant(currReview.getBusinessId()).getPriceScore());
			xTot += allX.get(allX.size() - 1);
			// Retrieve the user rating score
			allY.add(currReview.getStars());
			yTot += allY.get(allY.size() - 1);
		}
		// No reviews for the userID
		if (xTot == 0)
			return leastSquaresRegression;

		// Reconvert xTot and yTot into mean(x) and mean(y)
		xTot = xTot / allX.size();
		yTot = yTot / allY.size();

		// Calculate the sums of squares sXX, sXY
		double sXX = 0.0;
		double sXY = 0.0;
		for (int i = 0; i < allX.size(); i++) {
			sXX += Math.pow((allX.get(i) - xTot), 2.0);
			sXY += (allX.get(i) - xTot) * (allY.get(i) - yTot);
		}

		// Edge case to prevent division by zero on 0/0
		if (sXX == 0)
			sXX = 1;
		//System.out.println("sXX: " + sXX + " sXY: " + sXY);

		// Calculate the linear coefficients
		double B = sXY / sXX;
		double A = yTot - B * xTot;

		leastSquaresRegression = (x, y) -> {
			double returnNum;
			double priceScore = 0.0;
			for (Restaurant currRestaurant : x.getMatches(y))
				priceScore = currRestaurant.getPriceScore();
			//System.out.println("A: " + A + " priceScore: " + priceScore + " B: " + B);
			returnNum = Math.max(A + B * priceScore, 1.0);
			return Math.min(returnNum, 5.0);
		};

		return leastSquaresRegression;
	}



	/**
	 * Adds a Restaurant to restaurantAll if the Restaurant doesn't already exist.
	 * 
	 * @requires: newRestaurant is not null
	 *
	 * @effects: adds newRestaurant to restaurantAll if newRestaurant is not already in restaurantAll
	 * 
	 * @param newRestaurant the Restaurant to add.
	 */
	public void addRestaurant(Restaurant newRestaurant) {
		if (!restaurantAll.contains(newRestaurant)) {
			restaurantAll.add(newRestaurant);
		}
	}
	
	/**
	 * Adds a Review to reviewAll if the Review doesn't already exist.
	 * 
	 * @requires: newReview is not null
	 *
	 * @effects: adds newReview to reviewAll if newReview is not already in reviewAll
	 * 
	 * @param newReview the Review to add.
	 */
	public void addReview(Review newReview) {
		if (!reviewAll.contains(newReview)) {
			reviewAll.add(newReview);
		}
	}
	
	/**
	 * Adds a user to userAll if the user doesn't already exist.
	 * 
	 * @requires: newUser is not null
	 *
	 * @effects: adds newUser to userAll if newUser is not already in userAll
	 * 
	 * @param newUser the user to add.
	 */
	public void addUser(User newUser) {
		if (!userAll.contains(newUser)) {
			userAll.add(newUser);
		}
	}
	
	/**
	 * Searches the userAll database for a match to reqUser
	 *
	 * @param reqUserID the userID of the search target object
	 *
	 * @return by-reference User object if it exists, and null if it can't be found
	 */
	public User searchUser(String reqUserID) {
		for (User currUser : userAll) {
			if (currUser.getUserId().equals(reqUserID))
				return currUser;
		}
		return null;
	}

	/**
	 * Searches the restaurantAll database for a match to reqRestaurant
	 *
	 * @param reqBusinessID the businessID of the search target object
	 *
	 * @return by-reference Restaurant object if it exists, and null if it can't be
	 * found
	 */
	public Restaurant searchRestaurant(String reqBusinessID) {
		for (Restaurant currRestaurant : restaurantAll) {
			if (currRestaurant.getBusinessId().equals(reqBusinessID))
				return currRestaurant;
		}
		return null;
	}

	/**
	 * Searches the reviewAll database for a match to reqReview
	 *
	 * @param reqReviewID the reviewID of the search target object
	 *
	 * @return by-reference Review object if it exists, and null if it can't be
	 * found
	 */
	public Review searchReview(String reqReviewID) {
		for (Review currReview : reviewAll) {
			if (currReview.getReviewId().equals(reqReviewID))
				return currReview;
		}
		return null;
	}
	
	/**
	 * Gets the info of a Restaurant in the YelpDB, and returns it in a JSON format String. 
	 * 
	 * @param reqBusinessID the businessID of the Restuarant whose info to return
	 * @return the String in JSON format of the Restaurant's info
	 */
	public String getRestaurantJSON(String reqBusinessID) {
		Restaurant reqRest = this.searchRestaurant(reqBusinessID);
		
		//put String into JSON format
		String reqRestJSON = "";
		
		return null;
	}

	/**
	 * Gets the info of a User in the YelpDB, and returns it in a JSON format String.
	 * 
	 * @param reqUserID the userID of the User whose info to return
	 * @return the String in JSON format of the User's info
	 */
	public String getUserJSON(String reqUserID) {
		User reqUser = this.searchUser(reqUserID);
		
		//put String into JSON format
		String reqUserJSON = "";
		
		return null;
	}

	/**
	 * Gets the info of a Review in the YelpDB, and returns it in a JSON format String.
	 * 
	 * @param reqReviewID the reviewID of the Review whose info to return
	 * @return the String in JSON format of the Review's info 
	 */
	public String getReviewJSON(String reqReviewID) {
		 Review reqReview = this.searchReview(reqReviewID);
		 
		//put String into JSON format
		 String reqReviewJSON = "";
		 
		return null;
	}
}
