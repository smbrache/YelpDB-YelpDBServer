package ca.ece.ubc.cpen221.mp5.tests;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.YelpDB;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class RestaurantTest {
    private static YelpDB yDB;
    private static Restaurant restaurantTest;
    private static Restaurant restaurantTestDiff;
    private static Restaurant restaurantTestSame;

    @BeforeClass
    public static void setUp() {
        try {
            yDB = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
        }
        catch (Exception IOException) {
            System.out.println("RestaurantTest tried to initialize 1+ null data-set(s)");
        }
        restaurantTest = yDB.searchRestaurant("gclB3ED6uk6viWlolSb_uA");
        restaurantTestDiff = yDB.searchRestaurant("BJKIoQa5N2T_oDlLVf467Q");
        restaurantTestSame = yDB.searchRestaurant("gclB3ED6uk6viWlolSb_uA");
    }

    @Test
    public void test01() {
        // Test all containing methods
        assertEquals(false, restaurantTest.containsNeighborhood("567"));
        assertEquals(true, restaurantTest.containsNeighborhood("Telegraph Ave"));
        assertEquals(true, restaurantTest.getNeighborhoods().contains("Telegraph Ave"));

        assertEquals(false, restaurantTest.containsCategory("567"));
        assertEquals(true, restaurantTest.containsCategory("Cafes"));
        assertEquals(true, restaurantTest.getCategories().contains("Cafes"));

        // Test all getter methods
        assertTrue(restaurantTest.isOpen());

        assertEquals(37.867417, restaurantTest.getLatitude());
        assertEquals(-122.260408, restaurantTest.getLongitude());
        assertEquals("CA", restaurantTest.getState());
        assertEquals("Berkeley", restaurantTest.getCity());
        assertEquals("2400 Durant Ave, Telegraph Ave, Berkeley, CA 94701", restaurantTest.getFullAddress());
        assertEquals(true, restaurantTest.getSchools().contains("University of California at Berkeley"));

        assertEquals("Cafe 3", restaurantTest.getName());
        assertEquals("business", restaurantTest.getType());

        assertEquals("http://www.yelp.com/biz/cafe-3-berkeley", restaurantTest.getUrl());
        assertEquals("http://s3-media1.ak.yelpcdn.com/bphoto/AaHq1UzXiT6zDBUYrJ2NKA/ms.jpg", restaurantTest.getPhotoURL());
        assertEquals("gclB3ED6uk6viWlolSb_uA", restaurantTest.getBusinessId());

        assertEquals(9, restaurantTest.getReviewCount());
        assertEquals(1, restaurantTest.getPriceScore());
        assertEquals(2.111111111111111, restaurantTest.getStarScore());

        // Test review storage
        assertEquals("53glrLS3YJGEucPd1XdH1Q", restaurantTest.getRestaurantReviews().get(0).getReviewId());

        // Test equality
        assertEquals(false, restaurantTest.equals(restaurantTestDiff));
        assertEquals(false, restaurantTest.equals(567));
        assertEquals(true, restaurantTest.equals(restaurantTestSame));

        // toString() method test
        assertEquals("Cafe 3", restaurantTest.toString());
    }
}
