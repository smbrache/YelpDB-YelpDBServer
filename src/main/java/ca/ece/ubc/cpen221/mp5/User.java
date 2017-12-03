package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {

    /* User ID value */
    private final String name;
    private final String url;
    private final String userId;
    private final String type;
    // RI: Immutable after initialization

    /* User review statistics */
    private int reviewCount;
    private double averageStars;

    private int[] votes;
    // RI: Index 0: 'Cool' comment votes
    //     Index 1: 'Useful' comment votes
    //     Index 2: 'Funny' comment votes
    //
    // NOTE: This format is the same as Review, but described in a different order
    // according to the provided JSON
    // Votes total can be any x, MIN_INTEGER < x < MAX_INTEGER

    /* User review collection */
    private List<Review> userReviews;
    // RI: userReviews is not null
    // userReviews.size() == reviewCount

    /**
     * Initializes a User object
     *
     * @param url String to web url of user web page
     * @param votes int array depicting quantity of cool, useful, and funny votes
     * @param reviewCount int number of reviews posted by the user
     * @param type String representation of member type (e.g. user, moderator)
     * @param userId String user ID
     * @param name String representation of user display name
     * @param averageStars double value of collective average rating in stars (1-5)
     */
    public User(String url, int[] votes, int reviewCount, String type, String userId, String name, double averageStars) {
        // Initialize immutable and preset data
        this.url = url;
        this.votes = votes;
        this.reviewCount = reviewCount;
        this.type = type;
        this.userId = userId;
        this.name = name;
        this.averageStars = averageStars;

        // Initialize userReviews
        userReviews = new ArrayList<>();
    }

    public void addReview(Review inputReview) {
        userReviews.add(inputReview);
    }

    /**
     * voteCool inputs a like or dislike to the 'Cool' votes section
     *
     * @param like true for like, false for dislike
     */
    public void voteCool(boolean like) {
        if (like)
            votes[0]++;
        else
            votes[0]--;
    }

    /**
     * voteHelpful inputs a like or dislike to the 'Helpful' votes section
     *
     * @param like true for like, false for dislike
     */
    public void voteUseful(boolean like) {
        if (like)
            votes[1]++;
        else
            votes[1]--;
    }

    /**
     * voteFunny inputs a like or dislike to the 'Funny' votes section
     *
     * @param like true for like, false for dislike
     */
    public void voteFunny(boolean like) {
        if (like)
            votes[2]++;
        else
            votes[2]--;
    }

    /**
     * User name getter function
     * @return case-sensitive String of the User name
     */
    public String getName() {
        return name;
    }

    /**
     * url getter function
     * @return url-grammar-specific String of the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * User ID getter function
     * @return String of user ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Entity classification getter function
     * @return lowercase String of entity type
     */
    public String getType() {
        return type;
    }

    /**
     * Number of associated restaurant reviews
     * @return int number of reviews
     */
    public int getReviewCount() {
        return reviewCount;
    }

    /**
     * User average star score getter function
     * @return double precision average star score
     */
    public double getAverageStars() {
        return averageStars;
    }

    /**
     * Review quality (cool, helpful, funny) vote score getter function
     * @return copy of int[] votes data
     */
    public int[] getVotes() {
        return Arrays.copyOf(votes, votes.length);
    }

    /**
     * All associated user reviews getter function
     * @return ArrayList copy of user-specific reviews
     */
    public List<Review> getUserReview() {
        return new ArrayList<>(userReviews);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User){
            // Both Restaurant IDs must match
            return (this.getUserId() == ((User) obj).getUserId());
        }
        return false;
    }
}
