package ca.ece.ubc.cpen221.mp5.tests;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.StructuredQuery;
import ca.ece.ubc.cpen221.mp5.YelpDB;
import ca.ece.ubc.cpen221.mp5.YelpDBClient;
import ca.ece.ubc.cpen221.mp5.YelpDBServer;

public class YelpDBServerTest {

	YelpDBServer dbServer;
	YelpDBClient client1;
	YelpDBClient client2;
	YelpDBClient client3;
	YelpDBClient client4;
	YelpDBClient client5;
	boolean server = false;

	private static YelpDB yDB;

	Thread testServer = new Thread(new Runnable() {
		public void run() {
			try {
				YelpDBServer server = new YelpDBServer(YelpDBServer.YELPDB_PORT);
				server.serve();
			} catch (IOException e) {

			}
		}

	});

	@BeforeClass
	public static void setUp1() {
		// Initialize a YelpDB for a StructuredQuery argument data set
		try {
			yDB = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
		} catch (Exception IOException) {
			System.out.println("StructuredQueryTest tried to initialize 1+ null data-set(s)");
		}
	}

	@Before
	public void setUp2() {

		// start the server thread and create clients if server is not already started
		if (!server) {
			testServer.start();
			server = true;

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				// Create multiple clients
				client1 = new YelpDBClient("localhost", YelpDBServer.YELPDB_PORT);
				client2 = new YelpDBClient("localhost", YelpDBServer.YELPDB_PORT);
				client3 = new YelpDBClient("localhost", YelpDBServer.YELPDB_PORT);
				client4 = new YelpDBClient("localhost", YelpDBServer.YELPDB_PORT);
				client5 = new YelpDBClient("localhost", YelpDBServer.YELPDB_PORT);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Test
	public void test01() {

		try {

			// Send ADDRESTAURANT requests for all clients, different restaurants
			client1.sendRequest(
					"ADDRESTAURANT {\"longitude\": 42.0, \"latitude\": 42.0, \"state\": \"state\", \"city\": \"city\", \"full_address\": \"full_address\", \"neighborhoods\": [\"Dank\", \"Memes\"], \"schools\": [\"school\"], \"name\": \"rest1\", \"categories\": [\"cat1\", \"cat2\"]}");
			client2.sendRequest(
					"ADDRESTAURANT {\"longitude\": 42.0, \"latitude\": 42.0, \"state\": \"state\", \"city\": \"city\", \"full_address\": \"full_address\", \"neighborhoods\": [\"Dank\", \"Memes\"], \"schools\": [\"school\"], \"name\": \"rest2\", \"categories\": [\"cat1\", \"cat2\"]}");
			client3.sendRequest(
					"ADDRESTAURANT {\"longitude\": 42.0, \"latitude\": 42.0, \"state\": \"state\", \"city\": \"city\", \"full_address\": \"full_address\", \"neighborhoods\": [\"Dank\", \"Memes\"], \"schools\": [\"school\"], \"name\": \"rest3\", \"categories\": [\"cat1\", \"cat2\"]}");
			client4.sendRequest(
					"ADDRESTAURANT {\"longitude\": 42.0, \"latitude\": 42.0, \"state\": \"state\", \"city\": \"city\", \"full_address\": \"full_address\", \"neighborhoods\": [\"Dank\", \"Memes\"], \"schools\": [\"school\"], \"name\": \"rest4\", \"categories\": [\"cat1\", \"cat2\"]}");

			// collect the replies
			String addRestReply1 = client1.getReply();
			assertTrue(addRestReply1.equals(
					"{\"open\": true, \"url\": \"http://www.yelp.com/biz/rest1\", \"longitude\": 42.0, \"neighborhoods\": [\"Dank\", \"Memes\"], \"business_id\": \"rest1\", \"name\": \"rest1\", \"categories\": [\"cat1\", \"cat2\"], \"state\": \"state\", \"type\": \"business\", \"stars\": 0.0, \"city\": \"city\", \"full_address\": \"full_address\", \"review_count\": 0, \"photo_url\": \"http://s3-media1.ak.yelpcdn.com/bphoto/rest1/ms.jpg\", \"schools\": [\"school\"], \"latitude\": 42.0, \"price\": 0}"));
			String addRestReply2 = client2.getReply();
			assertTrue(addRestReply2.equals(
					"{\"open\": true, \"url\": \"http://www.yelp.com/biz/rest2\", \"longitude\": 42.0, \"neighborhoods\": [\"Dank\", \"Memes\"], \"business_id\": \"rest2\", \"name\": \"rest2\", \"categories\": [\"cat1\", \"cat2\"], \"state\": \"state\", \"type\": \"business\", \"stars\": 0.0, \"city\": \"city\", \"full_address\": \"full_address\", \"review_count\": 0, \"photo_url\": \"http://s3-media1.ak.yelpcdn.com/bphoto/rest2/ms.jpg\", \"schools\": [\"school\"], \"latitude\": 42.0, \"price\": 0}"));
			String addRestReply3 = client3.getReply();
			assertTrue(addRestReply3.equals(
					"{\"open\": true, \"url\": \"http://www.yelp.com/biz/rest3\", \"longitude\": 42.0, \"neighborhoods\": [\"Dank\", \"Memes\"], \"business_id\": \"rest3\", \"name\": \"rest3\", \"categories\": [\"cat1\", \"cat2\"], \"state\": \"state\", \"type\": \"business\", \"stars\": 0.0, \"city\": \"city\", \"full_address\": \"full_address\", \"review_count\": 0, \"photo_url\": \"http://s3-media1.ak.yelpcdn.com/bphoto/rest3/ms.jpg\", \"schools\": [\"school\"], \"latitude\": 42.0, \"price\": 0}"));
			String addRestReply4 = client4.getReply();
			assertTrue(addRestReply4.equals(
					"{\"open\": true, \"url\": \"http://www.yelp.com/biz/rest4\", \"longitude\": 42.0, \"neighborhoods\": [\"Dank\", \"Memes\"], \"business_id\": \"rest4\", \"name\": \"rest4\", \"categories\": [\"cat1\", \"cat2\"], \"state\": \"state\", \"type\": \"business\", \"stars\": 0.0, \"city\": \"city\", \"full_address\": \"full_address\", \"review_count\": 0, \"photo_url\": \"http://s3-media1.ak.yelpcdn.com/bphoto/rest4/ms.jpg\", \"schools\": [\"school\"], \"latitude\": 42.0, \"price\": 0}"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test02() {
		try {

			// Send ADDREVIEW requests for all clients, different reviews
			client1.sendRequest(
					"ADDREVIEW {\"business_id\": \"rev1\", \"text\": \"text\", \"user_id\": \"rev1\", \"date\": \"date\"}");
			client2.sendRequest(
					"ADDREVIEW {\"business_id\": \"rev2\", \"text\": \"text\", \"user_id\": \"rev2\", \"date\": \"date\"}");
			client3.sendRequest(
					"ADDREVIEW {\"business_id\": \"rev3\", \"text\": \"text\", \"user_id\": \"rev3\", \"date\": \"date\"}");
			client4.sendRequest(
					"ADDREVIEW {\"business_id\": \"rev4\", \"text\": \"text\", \"user_id\": \"rev4\", \"date\": \"date\"}");

			// collect the replies
			String addReviewReply1 = client1.getReply();
			assertTrue(addReviewReply1.equals(
					"{\"type\": \"review\", \"business_id\": \"rev1\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"review_id\": \"rev1--rev1\", \"text\": \"text\", \"stars\": 0, \"user_id\": \"rev1\", \"date\": \"date\"}"));
			String addReviewReply2 = client2.getReply();
			assertTrue(addReviewReply2.equals(
					"{\"type\": \"review\", \"business_id\": \"rev2\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"review_id\": \"rev2--rev2\", \"text\": \"text\", \"stars\": 0, \"user_id\": \"rev2\", \"date\": \"date\"}"));
			String addReviewReply3 = client3.getReply();
			assertTrue(addReviewReply3.equals(
					"{\"type\": \"review\", \"business_id\": \"rev3\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"review_id\": \"rev3--rev3\", \"text\": \"text\", \"stars\": 0, \"user_id\": \"rev3\", \"date\": \"date\"}"));
			String addReviewReply4 = client4.getReply();
			assertTrue(addReviewReply4.equals(
					"{\"type\": \"review\", \"business_id\": \"rev4\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"review_id\": \"rev4--rev4\", \"text\": \"text\", \"stars\": 0, \"user_id\": \"rev4\", \"date\": \"date\"}"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test03() {
		try {

			// Send ADDREVIEW requests for all clients, different users
			client1.sendRequest("ADDUSER {\"name\": \"user1\"}");
			client2.sendRequest("ADDUSER {\"name\": \"user2\"}");
			client3.sendRequest("ADDUSER {\"name\": \"user3\"}");
			client4.sendRequest("ADDUSER {\"name\": \"user4\"}");

			// collect the replies
			String addUserReply1 = client1.getReply();
			assertTrue(addUserReply1.equals(
					"{\"url\": \"http://www.yelp.com/user_details?userid=user1\", \"votes\": {\"funny\": 0, \"useful\": 0, \"cool\": 0}, \"review_count\": 0, \"type\": \"user\", \"user_id\": \"user1\", \"name\": \"user1\", \"average_stars\": 0.0}"));
			String addUserReply2 = client2.getReply();
			assertTrue(addUserReply2.equals(
					"{\"url\": \"http://www.yelp.com/user_details?userid=user2\", \"votes\": {\"funny\": 0, \"useful\": 0, \"cool\": 0}, \"review_count\": 0, \"type\": \"user\", \"user_id\": \"user2\", \"name\": \"user2\", \"average_stars\": 0.0}"));
			String addUserReply3 = client3.getReply();
			assertTrue(addUserReply3.equals(
					"{\"url\": \"http://www.yelp.com/user_details?userid=user3\", \"votes\": {\"funny\": 0, \"useful\": 0, \"cool\": 0}, \"review_count\": 0, \"type\": \"user\", \"user_id\": \"user3\", \"name\": \"user3\", \"average_stars\": 0.0}"));
			String addUserReply4 = client4.getReply();
			assertTrue(addUserReply4.equals(
					"{\"url\": \"http://www.yelp.com/user_details?userid=user4\", \"votes\": {\"funny\": 0, \"useful\": 0, \"cool\": 0}, \"review_count\": 0, \"type\": \"user\", \"user_id\": \"user4\", \"name\": \"user4\", \"average_stars\": 0.0}"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test04() {
		try {
			// first restaurant in db, Cafe 3
			client1.sendRequest("GETRESTAURANT gclB3ED6uk6viWlolSb_uA");
			// second restaurant in db, Jasmine Thai
			client2.sendRequest("GETRESTAURANT BJKIoQa5N2T_oDlLVf467Q");
			// third restaurant in db, Fondue Fred
			client3.sendRequest("GETRESTAURANT h_we4E3zofRTf4G0JTEF0A");
			// fourth restaurant in db, Peppermint Grill
			client4.sendRequest("GETRESTAURANT FWadSZw0G7HsgKXq7gHTnw");

			// collect the replies
			String getRestReply1 = client1.getReply();
			assertTrue(getRestReply1.equals(
					"{\"open\": true, \"url\": \"http://www.yelp.com/biz/cafe-3-berkeley\", \"longitude\": -122.260408, \"neighborhoods\": [\"Telegraph Ave\", \"UC Campus Area\"], \"business_id\": \"gclB3ED6uk6viWlolSb_uA\", \"name\": \"Cafe 3\", \"categories\": [\"Cafes\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 2.111111111111111, \"city\": \"Berkeley\", \"full_address\": \"2400 Durant Ave, Telegraph Ave, Berkeley, CA 94701\", \"review_count\": 9, \"photo_url\": \"http://s3-media1.ak.yelpcdn.com/bphoto/AaHq1UzXiT6zDBUYrJ2NKA/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.867417, \"price\": 1}"));
			String getRestReply2 = client2.getReply();
			assertTrue(getRestReply2.equals(
					"{\"open\": true, \"url\": \"http://www.yelp.com/biz/jasmine-thai-berkeley\", \"longitude\": -122.2602981, \"neighborhoods\": [\"UC Campus Area\"], \"business_id\": \"BJKIoQa5N2T_oDlLVf467Q\", \"name\": \"Jasmine Thai\", \"categories\": [\"Thai\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 2.980392156862745, \"city\": \"Berkeley\", \"full_address\": \"1805 Euclid Ave, UC Campus Area, Berkeley, CA 94709\", \"review_count\": 51, \"photo_url\": \"http://s3-media2.ak.yelpcdn.com/bphoto/ZwTUUb-6jkuzMDBBsUV6Eg/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8759615, \"price\": 2}"));
			String getRestReply3 = client3.getReply();
			assertTrue(getRestReply3.equals(
					"{\"open\": true, \"url\": \"http://www.yelp.com/biz/fondue-fred-berkeley\", \"longitude\": -122.25894, \"neighborhoods\": [\"UC Campus Area\"], \"business_id\": \"h_we4E3zofRTf4G0JTEF0A\", \"name\": \"Fondue Fred\", \"categories\": [\"Fondue\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 2.8895705521472395, \"city\": \"Berkeley\", \"full_address\": \"2556 Telegraph Ave, UC Campus Area, Berkeley, CA 94704\", \"review_count\": 163, \"photo_url\": \"http://s3-media1.ak.yelpcdn.com/bphoto/07PJUIzisU--faHrNi3vTw/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.863919, \"price\": 3}"));
			String getRestReply4 = client4.getReply();
			assertTrue(getRestReply4.equals(
					"{\"open\": true, \"url\": \"http://www.yelp.com/biz/peppermint-grill-berkeley\", \"longitude\": -122.2598181, \"neighborhoods\": [\"UC Campus Area\"], \"business_id\": \"FWadSZw0G7HsgKXq7gHTnw\", \"name\": \"Peppermint Grill\", \"categories\": [\"American (Traditional)\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 2.4375, \"city\": \"Berkeley\", \"full_address\": \"2505 Hearst Ave, Ste B, UC Campus Area, Berkeley, CA 94709\", \"review_count\": 16, \"photo_url\": \"http://s3-media1.ak.yelpcdn.com/assets/2/www/img/924a6444ca6c/gfx/blank_biz_medium.gif\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8751965, \"price\": 2}"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test05() {
		try {
			// first review in reviews.json
			client1.sendRequest("GETREVIEW 0a-pCW4guXIlWNpVeBHChg");
			// second review in reviews.json
			client2.sendRequest("GETREVIEW 0f8QNSVSocn40zr1tSSGRw");
			// third review in reviews.json
			client3.sendRequest("GETREVIEW 0_-hM8bzfC8JPyDVvtmYqQ");
			// fourth review in reviews.json
			client4.sendRequest("GETREVIEW 0hrSPeBHXvtMV4aY0jzgPg");

			// collect the replies
			String getReviewReply1 = client1.getReply();
			assertTrue(getReviewReply1.equals(
					"{\"type\": \"review\", \"business_id\": \"1CBs84C-a-cuA3vncXVSAw\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"review_id\": \"0a-pCW4guXIlWNpVeBHChg\", \"text\": \"The pizza is terrible, but if you need a place to watch a game or just down some pitchers, this place works.--newline---newline-Oh, and the pasta is even worse than the pizza.\", \"stars\": 2, \"user_id\": \"90wm_01FAIqhcgV_mPON9Q\", \"date\": \"2006-07-26\"}"));
			String getReviewReply2 = client2.getReply();
			assertTrue(getReviewReply2.equals(
					"{\"type\": \"review\", \"business_id\": \"1CBs84C-a-cuA3vncXVSAw\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"review_id\": \"0f8QNSVSocn40zr1tSSGRw\", \"text\": \"Food here is very consistent and good for a quick lunch or dinner. I usually order the garlic bread and salad; however, their pizza, especially the bbq chicken, is pretty good too.\", \"stars\": 4, \"user_id\": \"wr3JF-LruJ9LBwQTuw7aUg\", \"date\": \"2012-01-28\"}"));
			String getReviewReply3 = client3.getReply();
			assertTrue(getReviewReply3.equals(
					"{\"type\": \"review\", \"business_id\": \"1CBs84C-a-cuA3vncXVSAw\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"review_id\": \"0_-hM8bzfC8JPyDVvtmYqQ\", \"text\": \"Meh.--newline---newline-I got the Chicken Parmesan. The noodles and sauce both seemed to be sub-par quality. Chicken wasn't too bad though. I wish I would have gotten pizza or something, but I thought the Chicken Parm would be better.--newline---newline-The place is well decorated, however. So that was nice. --newline---newline-It's convenient. But it's college food and not at its best.\", \"stars\": 3, \"user_id\": \"T4zi4ocSDmUgHomFOVT4yw\", \"date\": \"2011-08-24\"}"));
			String getReviewReply4 = client4.getReply();
			assertTrue(getReviewReply4.equals(
					"{\"type\": \"review\", \"business_id\": \"1CBs84C-a-cuA3vncXVSAw\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"review_id\": \"0hrSPeBHXvtMV4aY0jzgPg\", \"text\": \"A cheap place to stop in and get lunch if you have class on the north side.  I miss the other location.  A lot of times they will have a lunch special available and it's in your best interest to take advantage of it when you're tired of eating Fat Slice.\", \"stars\": 3, \"user_id\": \"1mdwR2US8Z_CBWYuFMWWNg\", \"date\": \"2007-12-27\"}"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test06() {
		try {
			// first user in db, Chris M.
			client1.sendRequest("GETUSER _NH7Cpq3qZkByP5xR4gXog");
			// second user in db, cat u.
			client2.sendRequest("GETUSER 7RsdY4_1Bb_bCf5ZbK6tyQ");
			// third user in db, Erin c.
			client3.sendRequest("GETUSER QScfKdcxsa7t5qfE0Ev0Cw");
			// fourth user in db, Ronyde R.
			client4.sendRequest("GETUSER 9fMogxnnd0m9_FKSi-4AoQ");

			// collect the replies
			String getUserReply1 = client1.getReply();
			assertTrue(getUserReply1.equals(
					"{\"url\": \"http://www.yelp.com/user_details?userid=_NH7Cpq3qZkByP5xR4gXog\", \"votes\": {\"funny\": 35, \"useful\": 21, \"cool\": 14}, \"review_count\": 29, \"type\": \"user\", \"user_id\": \"_NH7Cpq3qZkByP5xR4gXog\", \"name\": \"Chris M.\", \"average_stars\": 3.89655172413793}"));
			String getUserReply2 = client2.getReply();
			assertTrue(getUserReply2.equals(
					"{\"url\": \"http://www.yelp.com/user_details?userid=7RsdY4_1Bb_bCf5ZbK6tyQ\", \"votes\": {\"funny\": 3, \"useful\": 25, \"cool\": 4}, \"review_count\": 21, \"type\": \"user\", \"user_id\": \"7RsdY4_1Bb_bCf5ZbK6tyQ\", \"name\": \"cat u.\", \"average_stars\": 3.0}"));
			String getUserReply3 = client3.getReply();
			assertTrue(getUserReply3.equals(
					"{\"url\": \"http://www.yelp.com/user_details?userid=QScfKdcxsa7t5qfE0Ev0Cw\", \"votes\": {\"funny\": 3, \"useful\": 17, \"cool\": 4}, \"review_count\": 37, \"type\": \"user\", \"user_id\": \"QScfKdcxsa7t5qfE0Ev0Cw\", \"name\": \"Erin C.\", \"average_stars\": 3.83783783783784}"));
			String getUserReply4 = client4.getReply();
			assertTrue(getUserReply4.equals(
					"{\"url\": \"http://www.yelp.com/user_details?userid=9fMogxnnd0m9_FKSi-4AoQ\", \"votes\": {\"funny\": 0, \"useful\": 0, \"cool\": 0}, \"review_count\": 1, \"type\": \"user\", \"user_id\": \"9fMogxnnd0m9_FKSi-4AoQ\", \"name\": \"Ronyde R.\", \"average_stars\": 5.0}"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Single atom (no AND/OR operator) functionality on all reducing functions via
	// YelpDBServer
	@Test
	public void test07() {

		// Get the expected answer sets: first create StructuredQuery, then put the
		// result in a Set, and finally change each Restaurant in the Set to its
		// characteristic Json String and put all the Json Strings in a qiResultSet
		StructuredQuery sQ1a = new StructuredQuery("in(Telegraph Ave)", yDB.getRestaurantAll());
		Set<Restaurant> q1Result = new HashSet<Restaurant>((sQ1a.getResults()));
		Set<String> q1ResultJsons = new HashSet<String>();
		for (Restaurant r : q1Result) {
			q1ResultJsons.add(yDB.getRestaurantJSON(r.getBusinessId()));
		}

		StructuredQuery sQ1b = new StructuredQuery("category(Sushi Bars)", yDB.getRestaurantAll());
		Set<Restaurant> q2Result = new HashSet<Restaurant>((sQ1b.getResults()));
		Set<String> q2ResultJsons = new HashSet<String>();
		for (Restaurant r : q2Result) {
			q2ResultJsons.add(yDB.getRestaurantJSON(r.getBusinessId()));
		}

		StructuredQuery sQ1c = new StructuredQuery("name(Jasmine Thai)", yDB.getRestaurantAll());
		Set<Restaurant> q3Result = new HashSet<Restaurant>((sQ1c.getResults()));
		Set<String> q3ResultJsons = new HashSet<String>();
		for (Restaurant r : q3Result) {
			q3ResultJsons.add(yDB.getRestaurantJSON(r.getBusinessId()));
		}

		StructuredQuery sQ1d = new StructuredQuery("name(Cafe 3)", yDB.getRestaurantAll());
		Set<Restaurant> q4Result = new HashSet<Restaurant>((sQ1d.getResults()));
		Set<String> q4ResultJsons = new HashSet<String>();
		for (Restaurant r : q4Result) {
			q4ResultJsons.add(yDB.getRestaurantJSON(r.getBusinessId()));
		}

		StructuredQuery sQ1e = new StructuredQuery("price = 3", yDB.getRestaurantAll());
		Set<Restaurant> q5Result = new HashSet<Restaurant>((sQ1e.getResults()));
		Set<String> q5ResultJsons = new HashSet<String>();
		for (Restaurant r : q5Result) {
			q5ResultJsons.add(yDB.getRestaurantJSON(r.getBusinessId()));
		}

		try {
			// Send queries to server
			client1.sendRequest("QUERY in(Telegraph Ave)");
			client2.sendRequest("QUERY category(Sushi Bars)");
			client3.sendRequest("QUERY name(Jasmine Thai)");
			client4.sendRequest("QUERY name(Cafe 3)");
			client5.sendRequest("QUERY price = 3");

			// Break the reply up into individual Json Strings and store them in an array
			String client1Reply = client1.getReply();
			String[] client1ReplyArr = client1Reply.substring(1, client1Reply.length() - 1).split("}, ");
			String[] c1ReplyArr = new String[client1ReplyArr.length];
			for (int i = 0; i < c1ReplyArr.length; i++) {
				if (!(client1ReplyArr[i].charAt(client1ReplyArr[i].length() - 1) == '}')) {
					c1ReplyArr[i] = client1ReplyArr[i] + "}";
				} else {
					c1ReplyArr[i] = client1ReplyArr[i];
				}
			}
			// Convert the array to a Set and check if it is the same Set as the one
			// returned by the corresponding StructuredQuery
			Set<String> getClient1Reply = new HashSet<String>(Arrays.asList(c1ReplyArr));
			assertTrue(getClient1Reply.equals(q1ResultJsons));

			// Break the reply up into individual Json Strings and store them in an array
			String client2Reply = client2.getReply();
			String[] client2ReplyArr = client2Reply.substring(1, client2Reply.length() - 1).split("}, ");
			String[] c2ReplyArr = new String[client2ReplyArr.length];
			for (int i = 0; i < c2ReplyArr.length; i++) {
				if (!(client2ReplyArr[i].charAt(client2ReplyArr[i].length() - 1) == '}')) {
					c2ReplyArr[i] = client2ReplyArr[i] + "}";
				} else {
					c2ReplyArr[i] = client2ReplyArr[i];
				}
			}
			// Convert the array to a Set and check if it is the same Set as the one
			// returned by the corresponding StructuredQuery
			Set<String> getClient2Reply = new HashSet<String>(Arrays.asList(c2ReplyArr));
			assertTrue(getClient2Reply.equals(q2ResultJsons));

			// Break the reply up into individual Json Strings and store them in an array
			String client3Reply = client3.getReply();
			String[] client3ReplyArr = client3Reply.substring(1, client3Reply.length() - 1).split("}, ");
			String[] c3ReplyArr = new String[client3ReplyArr.length];
			for (int i = 0; i < c3ReplyArr.length; i++) {
				if (!(client3ReplyArr[i].charAt(client3ReplyArr[i].length() - 1) == '}')) {
					c3ReplyArr[i] = client3ReplyArr[i] + "}";
				} else {
					c3ReplyArr[i] = client3ReplyArr[i];
				}
			}
			// Convert the array to a Set and check if it is the same Set as the one
			// returned by the corresponding StructuredQuery
			Set<String> getClient3Reply = new HashSet<String>(Arrays.asList(c3ReplyArr));
			assertTrue(getClient3Reply.equals(q3ResultJsons));

			// Break the reply up into individual Json Strings and store them in an array
			String client4Reply = client4.getReply();
			String[] client4ReplyArr = client4Reply.substring(1, client4Reply.length() - 1).split("}, ");
			String[] c4ReplyArr = new String[client4ReplyArr.length];
			for (int i = 0; i < c4ReplyArr.length; i++) {
				if (!(client4ReplyArr[i].charAt(client4ReplyArr[i].length() - 1) == '}')) {
					c4ReplyArr[i] = client4ReplyArr[i] + "}";
				} else {
					c4ReplyArr[i] = client4ReplyArr[i];
				}
			}
			// Convert the array to a Set and check if it is the same Set as the one
			// returned by the corresponding StructuredQuery
			Set<String> getClient4Reply = new HashSet<String>(Arrays.asList(c4ReplyArr));
			assertTrue(getClient4Reply.equals(q4ResultJsons));

			// Break the reply up into individual Json Strings and store them in an array
			String client5Reply = client5.getReply();
			String[] client5ReplyArr = client5Reply.substring(1, client5Reply.length() - 1).split("}, ");
			String[] c5ReplyArr = new String[client5ReplyArr.length];
			for (int i = 0; i < c5ReplyArr.length; i++) {
				if (!(client5ReplyArr[i].charAt(client5ReplyArr[i].length() - 1) == '}')) {
					c5ReplyArr[i] = client5ReplyArr[i] + "}";
				} else {
					c5ReplyArr[i] = client5ReplyArr[i];
				}
			}
			// Convert the array to a Set and check if it is the same Set as the one
			// returned by the corresponding StructuredQuery
			Set<String> getClient5Reply = new HashSet<String>(Arrays.asList(c5ReplyArr));
			assertTrue(getClient5Reply.equals(q5ResultJsons));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Practical Test 1 via YelpDBServer
	@Test
	public void test08() {

		StructuredQuery s07 = new StructuredQuery(
				"in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price <= 2", yDB.getRestaurantAll());
		Set<Restaurant> queryResult = new HashSet<Restaurant>(s07.getResults());
		Set<String> queryResultJsons = new HashSet<String>();
		for (Restaurant r : queryResult) {
			queryResultJsons.add(yDB.getRestaurantJSON(r.getBusinessId()));
		}

		try {
			// Send query to server
			client1.sendRequest("QUERY in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price <= 2");

			// Break the reply up into individual Json Strings and store them in an array,
			// doing some formatting along the way
			String client1Reply = client1.getReply();
			String[] client1ReplyArr = client1Reply.substring(1, client1Reply.length() - 1).split("}, ");
			String[] c1ReplyArr = new String[client1ReplyArr.length];
			for (int i = 0; i < c1ReplyArr.length; i++) {
				if (!(client1ReplyArr[i].charAt(client1ReplyArr[i].length() - 1) == '}')) {
					c1ReplyArr[i] = client1ReplyArr[i] + "}";
				} else {
					c1ReplyArr[i] = client1ReplyArr[i];
				}
			}
			// Convert the array to a Set and check if it is the same Set as the one
			// returned by the corresponding StructuredQuery
			Set<String> getClient1Reply = new HashSet<String>(Arrays.asList(c1ReplyArr));
			assertTrue(getClient1Reply.equals(queryResultJsons));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Invalid Query Test
	@Test
	public void test09() {
        StructuredQuery s09 = new StructuredQuery("There is no way this is a valid query", yDB.getRestaurantAll());
        try {
            Set<Restaurant> throwsException = new HashSet<Restaurant> (s09.getResults());
        }
        catch (Exception e) {
            // Nothing to do here
        }
	}
	
	// Invalid RequestTest
	@Test
	public void test10() {
        try {
            client1.sendRequest("There is no way this is a valid request");
        }
        catch (Exception e) {
            // Nothing to do here
        }
	}
}
