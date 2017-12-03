package ca.ece.ubc.cpen221.mp5.tests;

import ca.ece.ubc.cpen221.mp5.User;
import ca.ece.ubc.cpen221.mp5.YelpDB;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UserTest {
    private static YelpDB yDB;
    private static User reviewUser;
    private static User reviewUserDiff;
    private static User reviewUserSame;

    @BeforeClass
    public static void setUp() {
        try {
            yDB = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
        } catch (Exception IOException) {
            System.out.println("UserTest tried to initialize 1+ null data-set(s)");
        }
        reviewUser = yDB.searchUser("_NH7Cpq3qZkByP5xR4gXog");
        reviewUserDiff = yDB.searchUser("7RsdY4_1Bb_bCf5ZbK6tyQ");
        reviewUserSame = yDB.searchUser("_NH7Cpq3qZkByP5xR4gXog");
    }

    @Test
    public void test01() {
        // Testing getter methods
        assertEquals("Chris M.", reviewUser.getName());
        assertEquals("http://www.yelp.com/user_details?userid=_NH7Cpq3qZkByP5xR4gXog", reviewUser.getUrl());
        assertEquals("_NH7Cpq3qZkByP5xR4gXog", reviewUser.getUserId());
        assertEquals("user", reviewUser.getType());

        assertEquals(29, reviewUser.getReviewCount());
        assertEquals(3.89655172413793, reviewUser.getAverageStars());

        assertEquals("KVbFHXC7Zge998N8yZwqDA", reviewUser.getUserReview().get(0).getReviewId());
    }

    @Test
    public void test02() {
        // Testing equality
        assertEquals(true, reviewUser.equals(reviewUserSame));
        assertEquals(false, reviewUser.equals(reviewUserDiff));
        assertEquals(false, reviewUser.equals(567));
    }

    @Test
    public void test03() {
        assertEquals(14, reviewUser.getVotes()[0]);
        assertEquals(21, reviewUser.getVotes()[1]);
        assertEquals(35, reviewUser.getVotes()[2]);

        reviewUser.voteCool(true);
        reviewUser.voteUseful(false);
        reviewUser.voteFunny(true);

        assertEquals(15, reviewUser.getVotes()[0]);
        assertEquals(20, reviewUser.getVotes()[1]);
        assertEquals(36, reviewUser.getVotes()[2]);

        reviewUser.voteCool(false);
        reviewUser.voteUseful(true);
        reviewUser.voteFunny(false);

        assertEquals(14, reviewUser.getVotes()[0]);
        assertEquals(21, reviewUser.getVotes()[1]);
        assertEquals(35, reviewUser.getVotes()[2]);
    }
}
