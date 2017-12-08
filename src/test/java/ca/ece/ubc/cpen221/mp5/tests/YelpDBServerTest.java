package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.YelpDB;
import ca.ece.ubc.cpen221.mp5.YelpDBServer;

public class YelpDBServerTest {

	YelpDB db;
	YelpDBServer dbServer;
	
	@Test
	public void test01() {
		try {
			db = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
			dbServer = new YelpDBServer(db, 1337);
			dbServer.serve();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
