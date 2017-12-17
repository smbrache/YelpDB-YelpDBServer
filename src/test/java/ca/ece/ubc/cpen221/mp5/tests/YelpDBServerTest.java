package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.YelpDB;
import ca.ece.ubc.cpen221.mp5.YelpDBClient;
import ca.ece.ubc.cpen221.mp5.YelpDBServer;

public class YelpDBServerTest {

	YelpDBServer dbServer;
	YelpDBClient client1;
	YelpDBClient client2;
	YelpDBClient client3;
	YelpDBClient client4;
	boolean server = false;

	
	Thread testServer = new Thread (new Runnable() {
		public void run() {
			try {
				YelpDBServer server = new YelpDBServer(YelpDBServer.YELPDB_PORT);
				server.serve();
			} catch (IOException e) {
					
			}
		}
			
	});
	
//	Thread t1 = new Thread(new Runnable() {
//		public void run() {
//			YelpDBServer.main(null);
//			server = true;
//		}
//
//
//	});

	@Before
	public void setUp() {

		if (!server) {
			testServer.start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				
			}
			
			try {

				// Create multiple clients
				client1 = new YelpDBClient("localhost", YelpDBServer.YELPDB_PORT);
				client2 = new YelpDBClient("localhost", YelpDBServer.YELPDB_PORT);
				client3 = new YelpDBClient("localhost", YelpDBServer.YELPDB_PORT);
				client4 = new YelpDBClient("localhost", YelpDBServer.YELPDB_PORT);

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
			System.out.println(
					"ADDRESTAURANT {\"longitude\": 42.0, \"latitude\": 42.0, \"state\": \"state\", \"city\": \"city\", \"full_address\": \"full_address\", \"neighborhoods\": [\"Dank\", \"Memes\"], \"schools\": [\"school\"], \"name\": \"rest1\", \"categories\": [\"cat1\", \"cat2\"]}");
			client2.sendRequest(
					"ADDRESTAURANT {\"longitude\": 42.0, \"latitude\": 42.0, \"state\": \"state\", \"city\": \"city\", \"full_address\": \"full_address\", \"neighborhoods\": [\"Dank\", \"Memes\"], \"schools\": [\"school\"], \"name\": \"rest2\", \"categories\": [\"cat1\", \"cat2\"]}");
			System.out.println(
					"ADDRESTAURANT {\"longitude\": 42.0, \"latitude\": 42.0, \"state\": \"state\", \"city\": \"city\", \"full_address\": \"full_address\", \"neighborhoods\": [\"Dank\", \"Memes\"], \"schools\": [\"school\"], \"name\": \"rest2\", \"categories\": [\"cat1\", \"cat2\"]}");
			client3.sendRequest(
					"ADDRESTAURANT {\"longitude\": 42.0, \"latitude\": 42.0, \"state\": \"state\", \"city\": \"city\", \"full_address\": \"full_address\", \"neighborhoods\": [\"Dank\", \"Memes\"], \"schools\": [\"school\"], \"name\": \"rest3\", \"categories\": [\"cat1\", \"cat2\"]}");
			System.out.println(
					"ADDRESTAURANT {\"longitude\": 42.0, \"latitude\": 42.0, \"state\": \"state\", \"city\": \"city\", \"full_address\": \"full_address\", \"neighborhoods\": [\"Dank\", \"Memes\"], \"schools\": [\"school\"], \"name\": \"rest3\", \"categories\": [\"cat1\", \"cat2\"]}");
			client4.sendRequest(
					"ADDRESTAURANT {\"longitude\": 42.0, \"latitude\": 42.0, \"state\": \"state\", \"city\": \"city\", \"full_address\": \"full_address\", \"neighborhoods\": [\"Dank\", \"Memes\"], \"schools\": [\"school\"], \"name\": \"rest4\", \"categories\": [\"cat1\", \"cat2\"]}");
			System.out.println(
					"ADDRESTAURANT {\"longitude\": 42.0, \"latitude\": 42.0, \"state\": \"state\", \"city\": \"city\", \"full_address\": \"full_address\", \"neighborhoods\": [\"Dank\", \"Memes\"], \"schools\": [\"school\"], \"name\": \"rest4\", \"categories\": [\"cat1\", \"cat2\"]}");

			// collect the replies
			String addRestReply1 = client1.getReply();
			System.out.println("Added Restaurant JSON client1: " + addRestReply1);
			String addRestReply2 = client2.getReply();
			System.out.println("Added Restaurant JSON client2: " + addRestReply2);
			String addRestReply3 = client3.getReply();
			System.out.println("Added Restaurant JSON client3: " + addRestReply3);
			String addRestReply4 = client4.getReply();
			System.out.println("Added Restaurant JSON client4: " + addRestReply4);

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
			System.out.println(
					"ADDREVIEW {\"business_id\": \"rev1\", \"text\": \"text\", \"user_id\": \"rev1\", \"date\": \"date\"}");
			client2.sendRequest(
					"ADDREVIEW {\"business_id\": \"rev2\", \"text\": \"text\", \"user_id\": \"rev2\", \"date\": \"date\"}");
			System.out.println(
					"ADDREVIEW {\"business_id\": \"rev2\", \"text\": \"text\", \"user_id\": \"rev2\", \"date\": \"date\"}");
			client3.sendRequest(
					"ADDREVIEW {\"business_id\": \"rev3\", \"text\": \"text\", \"user_id\": \"rev3\", \"date\": \"date\"}");
			System.out.println(
					"ADDREVIEW {\"business_id\": \"rev3\", \"text\": \"text\", \"user_id\": \"rev3\", \"date\": \"date\"}");
			client4.sendRequest(
					"ADDREVIEW {\"business_id\": \"rev4\", \"text\": \"text\", \"user_id\": \"rev4\", \"date\": \"date\"}");
			System.out.println(
					"ADDREVIEW {\"business_id\": \"rev4\", \"text\": \"text\", \"user_id\": \"rev4\", \"date\": \"date\"}");

			// collect the replies
			String addReviewReply1 = client1.getReply();
			System.out.println("Added Review JSON: " + addReviewReply1);
			String addReviewReply2 = client2.getReply();
			System.out.println("Added Review JSON: " + addReviewReply2);
			String addReviewReply3 = client3.getReply();
			System.out.println("Added Review JSON: " + addReviewReply3);
			String addReviewReply4 = client4.getReply();
			System.out.println("Added Review JSON: " + addReviewReply4);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test03() {
		try {

			// Send ADDREVIEW requests for all clients, different users
			client1.sendRequest("ADDUSER {\"name\": \"user1\"}");
			System.out.println("ADDUSER {\"name\": \"user1\"}");
			client2.sendRequest("ADDUSER {\"name\": \"user2\"}");
			System.out.println("ADDUSER {\"name\": \"user2\"}");
			client3.sendRequest("ADDUSER {\"name\": \"user3\"}");
			System.out.println("ADDUSER {\"name\": \"user3\"}");
			client4.sendRequest("ADDUSER {\"name\": \"user4\"}");
			System.out.println("ADDUSER {\"name\": \"user4\"}");

			// collect the replies
			String addUserReply1 = client1.getReply();
			System.out.println("Added User JSON: " + addUserReply1);
			String addUserReply2 = client2.getReply();
			System.out.println("Added User JSON: " + addUserReply2);
			String addUserReply3 = client3.getReply();
			System.out.println("Added User JSON: " + addUserReply3);
			String addUserReply4 = client4.getReply();
			System.out.println("Added User JSON: " + addUserReply4);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test04() {
		try {
			// first restaurant in db, Cafe 3
			client1.sendRequest("GETRESTAURANT gclB3ED6uk6viWlolSb_uA");
			System.out.println("GETRESTAURANT gclB3ED6uk6viWlolSb_uA");
			// second restaurant in db, Jasmine Thai
			client2.sendRequest("GETRESTAURANT BJKIoQa5N2T_oDlLVf467Q");
			System.out.println("GETRESTAURANT BJKIoQa5N2T_oDlLVf467Q");
			// third restaurant in db, Fondue Fred
			client3.sendRequest("GETRESTAURANT h_we4E3zofRTf4G0JTEF0A");
			System.out.println("GETRESTAURANT h_we4E3zofRTf4G0JTEF0A");
			// fourth restaurant in db, Peppermint Grill
			client4.sendRequest("GETRESTAURANT FWadSZw0G7HsgKXq7gHTnw");
			System.out.println("GETRESTAURANT FWadSZw0G7HsgKXq7gHTnw");

			// collect the replies
			String getRestReply1 = client1.getReply();
			System.out.println("got Restaurant1 JSON: " + getRestReply1);
			String getRestReply2 = client2.getReply();
			System.out.println("got Restaurant2 JSON: " + getRestReply2);
			String getRestReply3 = client3.getReply();
			System.out.println("got Restaurant3 JSON: " + getRestReply3);
			String getRestReply4 = client4.getReply();
			System.out.println("got Restaurant4 JSON: " + getRestReply4);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test05() {
		try {
			// first review in reviews.json
			client1.sendRequest("GETREVIEW 0a-pCW4guXIlWNpVeBHChg");
			System.out.println("GETREVIEW 0a-pCW4guXIlWNpVeBHChg");
			// second review in reviews.json
			client2.sendRequest("GETREVIEW 0f8QNSVSocn40zr1tSSGRw");
			System.out.println("GETREVIEW 0f8QNSVSocn40zr1tSSGRw");
			// third review in reviews.json
			client3.sendRequest("GETREVIEW 0_-hM8bzfC8JPyDVvtmYqQ");
			System.out.println("GETREVIEW 0_-hM8bzfC8JPyDVvtmYqQ");
			// fourth review in reviews.json
			client4.sendRequest("GETREVIEW 0hrSPeBHXvtMV4aY0jzgPg");
			System.out.println("GETREVIEW 0hrSPeBHXvtMV4aY0jzgPg");

			// collect the replies
			String getReviewReply1 = client1.getReply();
			System.out.println("got Review1 JSON: " + getReviewReply1);
			String getReviewReply2 = client2.getReply();
			System.out.println("got Review2 JSON: " + getReviewReply2);
			String getReviewReply3 = client3.getReply();
			System.out.println("got Review3 JSON: " + getReviewReply3);
			String getReviewReply4 = client4.getReply();
			System.out.println("got Review4 JSON: " + getReviewReply4);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test06() {
		try {
			// first user in db, Chris M.
			client1.sendRequest("GETUSER _NH7Cpq3qZkByP5xR4gXog");
			System.out.println("GETUSER _NH7Cpq3qZkByP5xR4gXog");
			// second user in db, cat u.
			client2.sendRequest("GETUSER 7RsdY4_1Bb_bCf5ZbK6tyQ");
			System.out.println("GETUSER 7RsdY4_1Bb_bCf5ZbK6tyQ");
			// third user in db, Erin c.
			client3.sendRequest("GETUSER QScfKdcxsa7t5qfE0Ev0Cw");
			System.out.println("GETUSER QScfKdcxsa7t5qfE0Ev0Cw");
			// fourth user in db, Ronyde R.
			client4.sendRequest("GETUSER 9fMogxnnd0m9_FKSi-4AoQ");
			System.out.println("GETUSER 9fMogxnnd0m9_FKSi-4AoQ");

			// collect the replies
			String getUserReply1 = client1.getReply();
			System.out.println("got User1 JSON: " + getUserReply1);
			String getUserReply2 = client2.getReply();
			System.out.println("got User2 JSON: " + getUserReply2);
			String getUserReply3 = client3.getReply();
			System.out.println("got User3 JSON: " + getUserReply3);
			String getUserReply4 = client4.getReply();
			System.out.println("got User4 JSON: " + getUserReply4);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
