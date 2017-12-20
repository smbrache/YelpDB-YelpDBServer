package ca.ece.ubc.cpen221.mp5.tests;

import ca.ece.ubc.cpen221.mp5.StructuredQuery;
import ca.ece.ubc.cpen221.mp5.YelpDB;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StructuredQueryTest {

    // TODO: Fix the WORD definition bug in the .g4 file

    private static YelpDB yDB;

    @BeforeClass
    public static void setUp() {
        // Initialize a YelpDB for a StructuredQuery argument data set
        try {
            yDB = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
        } catch (Exception IOException) {
            System.out.println("StructuredQueryTest tried to initialize 1+ null data-set(s)");
        }
    }

    @Test
    public void test01() {
        // Single atom (no AND/OR operator) functionality on all reducing functions
        StructuredQuery sQ1a = new StructuredQuery("in(Telegraph Ave)", yDB.getRestaurantAll());
        // There are 76 entries in restaurants.json with 'in(Telegraph Ave)'
        assertEquals(76, sQ1a.getResults().size());

        StructuredQuery sQ1b = new StructuredQuery("category(Sushi Bars)", yDB.getRestaurantAll());
        // There are 3 entries in restaurants.json with 'category(Sushi Bars)'
        assertEquals(3, sQ1b.getResults().size());

        StructuredQuery sQ1c = new StructuredQuery("name(Jasmine Thai)", yDB.getRestaurantAll());
        // There is 1 entry in restaurants.json with 'name(Jasmine Thai)'
        assertEquals(1, sQ1c.getResults().size());

        StructuredQuery sQ1d = new StructuredQuery("name(Cafe 3)", yDB.getRestaurantAll());
        // There is 1 entry in restaurants.json with 'name(Cafe 3)'
        assertEquals(1, sQ1d.getResults().size());

        StructuredQuery sQ1e = new StructuredQuery("price = 3", yDB.getRestaurantAll());
        // There are 11 entries in restaurants.json with 'price = 3'
        assertEquals(11, sQ1e.getResults().size());
    }

    @Test
    public void test02() {
        // Single atom (no AND/OR operator) functionality on all inequality functions for price
        StructuredQuery sQ2a = new StructuredQuery("price < 3", yDB.getRestaurantAll());
        // There are 105 entries in restaurants.json with 'price < 3'
        assertEquals(115, sQ2a.getResults().size());

        StructuredQuery sQ2b = new StructuredQuery("price <= 3", yDB.getRestaurantAll());
        // There are 116 entries in restaurants.json with 'price <= 3'
        assertEquals(126, sQ2b.getResults().size());

        StructuredQuery sQ2c = new StructuredQuery("price = 3", yDB.getRestaurantAll());
        // There are 11 entries in restaurants.json with 'price = 3'
        assertEquals(11, sQ2c.getResults().size());

        StructuredQuery sQ2d = new StructuredQuery("price >= 3", yDB.getRestaurantAll());
        // There are 20 entries in restaurants.json with 'price >= 3'
        assertEquals(20, sQ2d.getResults().size());

        StructuredQuery sQ2e = new StructuredQuery("price > 3", yDB.getRestaurantAll());
        // There are 9 entries in restaurants.json with 'price > 3'
        assertEquals(9, sQ2e.getResults().size());
    }

    @Test
    public void test03() {
        // Single atom (no AND/OR operator) functionality on all inequality functions for rating
        StructuredQuery sQ3a = new StructuredQuery("rating < 3", yDB.getRestaurantAll());
        // There are  entries in restaurants.json with 'rating < 3'
        assertEquals( 27, sQ3a.getResults().size());

        // Single atom (no AND/OR operator) functionality on all inequality functions for rating
        StructuredQuery sQ3b = new StructuredQuery("rating <= 3", yDB.getRestaurantAll());
        // There are  entries in restaurants.json with 'rating < 3'
        assertEquals( 28, sQ3b.getResults().size());

        // Single atom (no AND/OR operator) functionality on all inequality functions for rating
        StructuredQuery sQ3c = new StructuredQuery("rating = 3", yDB.getRestaurantAll());
        // There are 30 entries in restaurants.json with 'rating < 3
        assertEquals( 1, sQ3c.getResults().size());

        // Single atom (no AND/OR operator) functionality on all inequality functions for rating
        StructuredQuery sQ3d = new StructuredQuery("rating >= 3", yDB.getRestaurantAll());
        // There are 120 entries in restaurants.json with 'rating < 3'
        assertEquals( 108, sQ3d.getResults().size());

        // Single atom (no AND/OR operator) functionality on all inequality functions for rating
        StructuredQuery sQ3e = new StructuredQuery("rating > 3", yDB.getRestaurantAll());
        // There are 90 entries in restaurants.json with 'rating < 3'
        assertEquals( 107, sQ3e.getResults().size());
    }

    @Test
    public void test04() {
        // Testing 2-atom queries

        // 2-atom OR
        StructuredQuery s04a = new StructuredQuery("price = 2 || price = 4", yDB.getRestaurantAll());
        // There are 70 entries in restaurants.json with 'price = 2 || price = 4'
        assertEquals(70, s04a.getResults().size());

        // 2-atom OR (reflexive test)
        StructuredQuery s04b = new StructuredQuery("price = 4 || price = 2", yDB.getRestaurantAll());
        // There are 70 entries in restaurants.json with 'price = 2 || price = 4'
        assertEquals(70, s04b.getResults().size());

        // 2-atom AND
        StructuredQuery s04c = new StructuredQuery("in(Telegraph Ave) && category(Chinese)", yDB.getRestaurantAll());
        // There are 7 entries in restaurants.json with 'in(Telegraph Ave) && category(Chinese)'
        assertEquals(7, s04c.getResults().size());

        // 2-atom AND (reflexive test)
        StructuredQuery s04d = new StructuredQuery("category(Chinese) && in(Telegraph Ave)", yDB.getRestaurantAll());
        // There are 7 entries in restaurants.json with 'in(Telegraph Ave) && category(Chinese)'
        assertEquals(7, s04d.getResults().size());
    }

    @Test
    public void test05() {
        // Testing n-atom queries of the same operand (AND/OR)

        // 5-atom OR
        StructuredQuery s05a = new StructuredQuery("price = 1 || price = 2 || price = 3 || price = 4 || price = 5", yDB.getRestaurantAll());
        // There are 135 entries in restaurants.json (as this query lists all possible price-scores)
        assertEquals(135, s05a.getResults().size());

        // 5-atom AND
        StructuredQuery s05b = new StructuredQuery("rating = 1 && rating = 2 && rating = 3 && rating = 4 && rating = 5", yDB.getRestaurantAll());
        // There are 0 entries in restaurants.json with 'rating = 1 && rating = 2 && rating = 3 && rating = 4 && rating = 5'
        assertEquals(0, s05b.getResults().size());
    }

    @Test
    public void test06() {
        // Testing parenthesis order of operation

        // A && (B || C) operation
        StructuredQuery s06a = new StructuredQuery("in(Telegraph Ave) && (category(Chinese) || category(Italian))", yDB.getRestaurantAll());
        // There are 9 entries in restaurants.json with 'in(Telegraph Ave) && (category(Chinese) || category(Italian))'
        assertEquals(9, s06a.getResults().size());

        // (A && B) || C operation
        StructuredQuery s06b = new StructuredQuery("(in(Telegraph Ave) && category(Chinese)) || category(Italian)", yDB.getRestaurantAll());
        // There are 10 entries in restaurants.json with 'in(Telegraph Ave) && (category(Chinese) || category(Italian))'
        assertEquals(10, s06b.getResults().size());
    }

    @Test
    public void test07() {
        // Practical Test 1
        StructuredQuery s07 = new StructuredQuery("in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price <= 2", yDB.getRestaurantAll());
        // There are 9 entries in restaurants.json with 'in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price <= 2'
        assertEquals(9, s07.getResults().size());
    }

    @Test
    public void test08() {
        // Practical Test 2
        StructuredQuery s08 = new StructuredQuery("in(Downtown Berkeley) || (price = 3 && in(UC Campus Area))", yDB.getRestaurantAll());
        // There are 20 entries in restaurants.json with 'in(Downtown Berkeley) || (price = 3 && in(UC Campus Area)'
        assertEquals(20, s08.getResults().size());
    }

    @Test
    public void test09() throws IndexOutOfBoundsException {
        // Invalid Query Test
        StructuredQuery s09 = new StructuredQuery("There is no way this is a valid query", yDB.getRestaurantAll());
        try {
            int throwsException = s09.getResults().size();
        }
        catch (Exception IndexOutOfBoundsException) {
            // Nothing to do here
        }
    }
}
