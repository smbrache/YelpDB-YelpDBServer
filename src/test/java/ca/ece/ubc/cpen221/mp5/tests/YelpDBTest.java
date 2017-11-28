package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.YelpDB;

public class YelpDBTest {

	YelpDB db = new YelpDB();
	
	@Before
	public void setUp() {
	}

	// Test that parseRestaurantJSON creates correct JsonObject
	@Test
	public void test01() {
		JsonObject obj = Json.createObjectBuilder()
				.add("open", true)
				.add("url", "http://www.yelp.com/biz/cafe-3-berkeley")
				.add("longitude", -122.260408)
				.add("neighborhoods", Json.createArrayBuilder()
						.add("Telegraph Ave")
						.add("UC Campus Area"))
				.add("business_id", "gclB3ED6uk6viWlolSb_uA")
				.add("name", "Cafe 3")
				.add("categories", Json.createArrayBuilder()
						.add("Cafes")
						.add("Restaurants"))
				.add("state", "CA")
				.add("type", "business")
				.add("stars", 2.0)
				.add("city", "Berkeley")
				.add("full_address", "2400 Durant Ave\nTelegraph Ave\nBerkeley, CA 94701")
				.add("review_count", 9)
				.add("photo_url", "http://s3-media1.ak.yelpcdn.com/bphoto/AaHq1UzXiT6zDBUYrJ2NKA/ms.jpg")
				.add("schools", Json.createArrayBuilder()
						.add("University of California at Berkeley"))
				.add("latitude", 37.867417)
				.add("price", 1)
				.build();
		System.out.println(obj);
		JsonObject parseRestaurant = null;
		try {
			parseRestaurant = db.parseRestaurantJSON("data/RestaurantTest01.json");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(parseRestaurant);
		assertEquals(obj, parseRestaurant);				
	}
	
	// Test that parseReviewJSON creates correct JsonObject
	@Test
	public void test02() {
		JsonObject obj = Json.createObjectBuilder()
				.add("type", "review")
				.add("business_id", "1CBs84C-a-cuA3vncXVSAw")
				.add("votes", Json.createObjectBuilder()
						.add("cool", 0)
						.add("useful", 0)
						.add("funny", 0))
				.add("review_id", "0a-pCW4guXIlWNpVeBHChg")
				.add("text","The pizza is terrible, but if you need a place to watch a game or just down some pitchers, this place works.\n\nOh, and the pasta is even worse than the pizza." )
				.add("stars", 2)
				.add("user_id","90wm_01FAIqhcgV_mPON9Q")
				.add("date", "2006-07-26")
				.build();
		System.out.println(obj);
		JsonObject parseReview = null;
		try {
			parseReview = db.parseReviewJSON("data/ReviewTest01.json");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(parseReview);
		assertEquals(obj, parseReview);	
		
	}
	
	// Test that parseUserJSON creates correct JsonObject
	@Test
	public void test03() {
		JsonObject obj = Json.createObjectBuilder()
				.add("url", "http://www.yelp.com/user_details?userid=_NH7Cpq3qZkByP5xR4gXog")
				.add("votes", Json.createObjectBuilder()
						.add("cool", 14)
						.add("useful", 21)
						.add("funny", 35))
				.add("review_count", 29)
				.add("type", "user")
				.add("user_id", "_NH7Cpq3qZkByP5xR4gXog")
				.add("name", "Chris M.")
				.add("average_stars", 3.89655172413793)
				.build();
		System.out.println(obj);
		JsonObject parseUser = null;
		try {
			parseUser = db.parseUserJSON("data/UserTest01.json");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(parseUser);
		assertEquals(obj, parseUser);	
		
	}
	
	// Test that parseRestaurantJSON correctly throws IOException
	@Test
	public void test04() {
		try {
			JsonObject parseRestaurant = db.parseRestaurantJSON(null);
			fail("did not catch exception");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Test that parseReviewJSON correctly throws IOException
	@Test
	public void test05() {
		try {
			JsonObject parseReview = db.parseReviewJSON(null);
			fail("did not catch exception");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Test that parseUSerJSON correctly throws IOException
	@Test
	public void test06() {
		try {
			JsonObject parseUser = db.parseUserJSON(null);
			fail("did not catch exception");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
