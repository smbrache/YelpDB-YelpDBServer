package ca.ece.ubc.cpen221.mp5.tests;

import ca.ece.ubc.cpen221.mp5.MP5Db;
import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.User;
import ca.ece.ubc.cpen221.mp5.YelpDB;

import static junit.framework.TestCase.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.function.ToDoubleBiFunction;

public class YelpDBLSRTest {

    private static YelpDB yDB;

    @BeforeClass
    public static void setUp() throws IOException {
        try {
            yDB = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
        }
        catch (Exception IOException) {
            System.out.println("YelpDBLSRTest tried to initialize 1+ null data-set(s).");
        }
    }

    @Test
    public void test01() {
        // Tests saving the Fcn as a variable and reusing it

        // Chris M. has 1 review with a price-score: 1, and assigned-score: 4
        // sXX = 0.0 (set to 1.0), sXY = 0 | A = 4, B = 0
        // Linear Function: Y = 4
        ToDoubleBiFunction<MP5Db<Restaurant>, String> testFcn = yDB.getPredictorFunction("_NH7Cpq3qZkByP5xR4gXog");

        // Provided Restaurant Price Score: 1
        // Expected Output: 4
        assertEquals(4.0, testFcn.applyAsDouble(yDB, "gclB3ED6uk6viWlolSb_uA"));

        // Provided Restaurant Price Score: 2
        // Expected Output: 4
        assertEquals(4.0, testFcn.applyAsDouble(yDB, "9N684D-RFrQC0V4K0XvdxQ"));
    }

    @Test
    public void test02() {
        // Sample case of 2 reviews

        // Erin C. has 2 reviews with price-scores: 3, 2, and assigned-scores: 2, 1
        // sXX = 0.5, sXY = 0.5 | A = -1.0, B = 1
        // Linear Function Y = -1 + X
        // Provided Restaurant Price Score: 3
        // Expected Output: 2
        assertEquals(2.0, yDB.getPredictorFunction("QScfKdcxsa7t5qfE0Ev0Cw").applyAsDouble(yDB, "h_we4E3zofRTf4G0JTEF0A"));
    }

    @Test
    public void test03() {
        // Sample case of 25 reviews

        // Charles L. has 25 reviews
        // sXX = 6.56, sXY = -3.28 | A = 4, B = -0.5
        // Linear Function Y = 4 - 0.5X
        // Provided Restaurant Price Score: 2
        // Expected Output: 3

        assertEquals(3.0, yDB.getPredictorFunction("Vw7Zi0EXqHmhru78zyFxaQ").applyAsDouble(yDB, "gOp_w9qmLq6B8YRypTPp8g"));
    }

    @Test
    public void test04() {
        // Sample case of 10 reviews

        // Brian S. has 10 reviews
        // sXX = 2.90, sXY = -0.79 | A = 2.6207 B = 0.2759
        // Linear Function Y = 2.6207 + 0.2759X
        // Provided Restaurant Price Score: 2
        // Expected Output: 3.1725
        assertEquals(3.1724137931034484, yDB.getPredictorFunction("3eNXSh82_4UThqhJBwkrbA").applyAsDouble(yDB, "PkSoDMwb3y3-lRsqaKqSGw"));
    }

    @Test
    public void test05() {
        // Test a null userID to return a 0.0 function
        assertEquals(0.0, yDB.getPredictorFunction("xyzABC").applyAsDouble(yDB, "gOp_w9qmLq6B8YRypTPp8g"));
    }

    @Test
    public void test06() {
        // Insert a user (who has no reviews) to return a 0.0 function
        int[] voteArray = {1,2,3};
        User noReviewUser = new User("123", voteArray, 0, "user", "aaa111", "Dickinson", 3.5);
        yDB.addUser(noReviewUser);
        assertEquals(0.0, yDB.getPredictorFunction("aaa111").applyAsDouble(yDB, "gOp_w9qmLq6B8YRypTPp8g"));
    }

}
