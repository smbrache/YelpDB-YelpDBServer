package ca.ece.ubc.cpen221.mp5.tests;

import ca.ece.ubc.cpen221.mp5.Review;
import ca.ece.ubc.cpen221.mp5.YelpDB;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ReviewTest {
    private static YelpDB yDB;
    private static Review reviewTest;
    private static Review reviewTestDiff;
    private static Review reviewTestSame;

    @BeforeClass
    public static void setUp() {
        try {
            yDB = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
        } catch (Exception IOException) {
            System.out.println("ReviewTest tried to initialize 1+ null data-set(s)");
        }
        reviewTest = yDB.searchReview("0a-pCW4guXIlWNpVeBHChg");
        reviewTestDiff = yDB.searchReview("0f8QNSVSocn40zr1tSSGRw");
        reviewTestSame = yDB.searchReview("0a-pCW4guXIlWNpVeBHChg");
    }

    @Test
    public void test01() {
        // Testing getter methods
        assertEquals("90wm_01FAIqhcgV_mPON9Q", reviewTest.getUserId());
        assertEquals("0a-pCW4guXIlWNpVeBHChg", reviewTest.getReviewId());
        assertEquals("1CBs84C-a-cuA3vncXVSAw", reviewTest.getBusinessId());

        assertEquals("2006-07-26", reviewTest.getDate());
        assertEquals("review", reviewTest.getType());

        assertEquals(true, reviewTest.equals(reviewTestSame));
        assertEquals(false, reviewTest.equals(reviewTestDiff));
        assertEquals(false, reviewTest.equals(567));
    }

    @Test
    public void test02() {
        // Testing vote system methods
        assertEquals(0, reviewTest.getVotes()[0]);
        assertEquals(0, reviewTest.getVotes()[1]);
        assertEquals(0, reviewTest.getVotes()[2]);

        reviewTest.voteCool(true);
        reviewTest.voteUseful(false);
        reviewTest.voteFunny(true);

        assertEquals(1, reviewTest.getVotes()[0]);
        assertEquals(-1, reviewTest.getVotes()[1]);
        assertEquals(1, reviewTest.getVotes()[2]);

        reviewTest.voteCool(false);
        reviewTest.voteUseful(true);
        reviewTest.voteFunny(false);

        assertEquals(0, reviewTest.getVotes()[0]);
        assertEquals(0, reviewTest.getVotes()[1]);
        assertEquals(0, reviewTest.getVotes()[2]);
    }

    @Test
    public void test03() {
        // Testing text and text-edit methods
        assertEquals("The pizza is terrible, but if you need a place to watch a game or just down some pitchers, this place works.--newline---newline-Oh, and the pasta is even worse than the pizza.", reviewTest.getText());
        reviewTest.editText("567");
        assertEquals("567", reviewTest.getText());
    }

    @Test
    public void test04() {
        // Testing stars, and edit stars methods
        assertEquals(2, reviewTest.getStars());
        assertEquals(false, reviewTest.editStars(10));
        assertEquals(false, reviewTest.editStars(-10));
        reviewTest.editStars(5);
        assertEquals(5, reviewTest.getStars());
    }
}
