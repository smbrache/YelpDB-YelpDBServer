package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.Review;
import ca.ece.ubc.cpen221.mp5.User;
import ca.ece.ubc.cpen221.mp5.YelpDB;

public class YelpDBTest {

	YelpDB db = new YelpDB();

	// kMeansClustering test
	@Test
	public void test01() {
		try {
			db = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<double[]> centroidList = new ArrayList<double[]>();
		double[] centroidCoords = new double[2];

		// for each centroid-cluster pair
		for (Map.Entry<double[], Set<Restaurant>> clusterMapEntry : db.centroidClusterMap.entrySet()) {

			// For debugging:
			// print restaurants in the cluster
			// System.out.println("cluster: " + clusterMapEntry.getValue().toString());
			// System.out.println();

			// put the centroid in centroidList
			centroidCoords[0] = clusterMapEntry.getKey()[0];
			centroidCoords[1] = clusterMapEntry.getKey()[1];
			centroidList.add(Arrays.copyOf(centroidCoords, centroidCoords.length));

			// for each restaurant in the cluster
			Set<Restaurant> restSet = clusterMapEntry.getValue();
			for (Restaurant r : restSet) {

				// find the closest centroid to the restaurant
				double[] closestCentroid = null;
				double minDist = Double.MAX_VALUE;
				double[] restCoords = new double[2];
				restCoords[0] = r.getLatitude();
				restCoords[1] = r.getLongitude();
				for (double[] centroid : centroidList) {
					if (YelpDB.Distance(restCoords, centroid) < minDist) {
						minDist = YelpDB.Distance(restCoords, centroid);
						closestCentroid = centroid;
					}
				}

				// if the closest centroid to the restaurant is not the centroid of its cluster,
				// fail the test
				if (!(closestCentroid[0] == clusterMapEntry.getKey()[0])
						&& !(closestCentroid[1] == clusterMapEntry.getKey()[1])) {

					System.out.println("closest centroid to " + r.getName() + " is " + closestCentroid[0] + " "
							+ closestCentroid[1]);
					System.out.println("the centroid in its cluster is " + clusterMapEntry.getKey()[0] + " "
							+ clusterMapEntry.getKey()[1]);

					fail("closest centroid is not in this restaurant's cluster");
				}

			} // end for all restaurants

		} // end for all map entries

	}

	// Make sure passing YelpDB null objects results in thrown IOException
	@Test
	public void test02() {
		try {
			db = new YelpDB(null, "data/reviews.json", "data/users.json");
			fail("Did not throw exception");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			db = new YelpDB("data/restaurants.json", null, "data/users.json");
			fail("Did not throw exception");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			db = new YelpDB("data/restaurants.json", "data/reviews.json", null);
			fail("Did not throw exception");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Restaurant constructor and equals test
	@Test
	public void test03() {
		Restaurant r1 = new Restaurant(true, "www.ubc.ca", 1.337,
				new ArrayList<String>(Arrays.asList("Hood1", "Hood2")), "bizID", "name",
				new ArrayList<String>(Arrays.asList("cat1", "cat2")), "BC", "type", 5.0, "vancouver", "address", 5,
				"www.photoURL.com", new ArrayList<String>(Arrays.asList("UBC", "UVic", "SFU")), 1.337, 4);
		
		Restaurant r2 = new Restaurant(r1.isOpen(), r1.getUrl(), r1.getLongitude(), r1.getNeighborhoods(),
				r1.getBusinessId(), r1.getName(), r1.getCategories(), r1.getState(), r1.getType(), r1.getStarScore(),
				r1.getCity(), r1.getFullAddress(), r1.getReviewCount(), r1.getPhotoURL(), r1.getSchools(),
				r1.getLatitude(), r1.getPriceScore());
		
		assertTrue(r1.equals(r2));
	}

	// Review constructor and equals test
	@Test
	public void test04() {
		Review r1 = new Review("type", "bizID", new int[] { 1, 2, 3 }, "reviewID", "text", 5, "userID", "date");
		
		Review r2 = new Review(r1.getType(), r1.getBusinessId(), r1.getVotes(), r1.getReviewId(), r1.getText(),
				r1.getStars(), r1.getUserId(), r1.getDate());
		
		assertTrue(r1.equals(r2));
	}

	// User constructor and equals test
	@Test
	public void test05() {
		User u1 = new User("url", new int[] { 1, 2, 3 }, 5, "type", "userID", "name", 4.20);
		
		User u2 = new User(u1.getUrl(), u1.getVotes(), u1.getReviewCount(), u1.getType(), u1.getUserId(), u1.getName(),
				u1.getAverageStars());
		
		assertTrue(u1.equals(u2));
	}

	@Test
	public void test06() {
		// Testing null properties of YelpDB search functions
		assertEquals(new Restaurant(true, "null", 0.0, new ArrayList<String>(), "null", "null", new ArrayList<String>(), "null", "null", 0.0,
				"null", "null", 0, "null", new ArrayList<String>(), 0.0, 0), db.searchRestaurant("ABA"));
		
		int[] nullVotes = new int[3];
		nullVotes[0] = 0;
		nullVotes[1] = 0;
		nullVotes[2] = 0;
		assertEquals(new Review("null", "null", nullVotes, "null", "null", 0, "null", "null"), db.searchReview("BAB"));
		assertEquals(new User("null", nullVotes, 0, "null", "null", "null", 0.0), db.searchUser("BBB"));
	}

	@Test
	public void test07() {
		// Testing the addition of duplicates to a database
		YelpDB dupDatabase = new YelpDB();
		User dupUser = new User("a", new int[] { 1, 1, 1 }, 1, "user", "a", "A.", 1);

		// Add once - successful
		dupDatabase.addUser(dupUser);
		// Add twice - unsuccessful
		dupDatabase.addUser(dupUser);
	}

	@Test
	public void test08() {
		// Testing getMatches on an empty DB
		YelpDB emptyDB = new YelpDB();
		assertEquals(0, emptyDB.getMatches("10").size());
	}

	// Test that kMeansClusters_json() returns a correct and properly
	// structured String by analyzing console output
	@Test
	public void test09() {
		try {
			db = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String kMeans_json = db.kMeansClusters_json(10);
		System.out.println(kMeans_json);
	}
}
