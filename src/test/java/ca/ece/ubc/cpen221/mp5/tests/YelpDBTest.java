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
		JsonObject Obj = Json.createObjectBuilder()
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
		System.out.println(Obj);
		JsonObject parseRestaurant = null;
		try {
			parseRestaurant = db.parseRestaurantJSON("data/RestaurantTest01.json");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(parseRestaurant);
		assertEquals(Obj, parseRestaurant);				
	}
	
	// Test that parseReviewJSON creates correct JsonObject
	@Test
	public void test02() {
	}
	
	// Test that parseUserJSON creates correct JsonObject
	@Test
	public void test03() {
	}
	
	// Test that parseRestaurantJSON correctly throws IOException
	@Test
	public void test04() {
	}
	
	// Test that parseReviewJSON correctly throws IOException
	@Test
	public void test05() {
	}
	
	//Test that parseUSerJSON correctly throws IOException
	@Test
	public void test06() {
	}

}
