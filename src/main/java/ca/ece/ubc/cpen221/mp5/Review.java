package ca.ece.ubc.cpen221.mp5;

import java.util.Arrays;

public class Review {

    /* User and date of review */
    private final String userId;
    private final String date;
    // RI: Immutable after initialization

    /* Quantitative internal review data */
    private final String type;
    private final String reviewId;
    private final String businessId;
    // RI: Immutable after initialization

    /* Qualitative review data */
    private String text;
    private int stars;

    /* Community review voting system */
    int[] votes;
    // RI: Index 0: 'Cool' comment votes
    //     Index 1: 'Useful' comment votes
    //     Index 2: 'Funny' comment votes
    //
    // Votes total can be any x, MIN_INTEGER < x < MAX_INTEGER

    /**
     * Initializes a Review object
     *
     * @param type String representation of contribution type (e.g. review, report)
     * @param businessId String business ID code
     * @param votes int array depicting quantity of cool, useful, and funny votes
     * @param reviewId String review ID
     * @param text String text contents of review
     * @param stars int user-provided score (1-5)
     * @param userId String user ID
     * @param date String date of review submission
     */
    public Review(String type, String businessId, int[] votes, String reviewId, String text, int stars, String userId, String date) {
        // Initialize immutable and preset data
        this.type = type;
        this.businessId = businessId;
        this.votes = votes;
        this.reviewId = reviewId;
        this.text = text;
        this.stars = stars;
        this.userId = userId;
        this.date = date;
    }

    /**
     * editText changes the text contents of review
     *
     * @param newReviewText new text for the review
     */
    public void editText(String newReviewText) {
        // newReviewText must not be null
        this.text = newReviewText;
    }

    /**
     * editStars changes the star score of the review
     *
     * @param newStars a new score assignment between (inclusive) 1 and 5
     * @return true is a valid newStars was provided (the stars score was changed)
     * false if an invalid newStars was provided (the stars score was not changed)
     */
    public boolean editStars(int newStars) {
        if (newStars >= 1 && newStars <= 5) {
            this.stars = newStars;
            return true;
        }
        return false;
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
     * Review post user ID getter function
     * @return String of user ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Date of review submission getter function
     * @return date-grammar-compliant String of review date
     */
    public String getDate() {
        return date;
    }

    /**
     * Submission type (e.g. review) getter function
     * @return lower-case String of submission type
     */
    public String getType() {
        return type;
    }

    /**
     * Review ID getter function
     * @return String of review ID
     */
    public String getReviewId() {
        return reviewId;
    }

    /**
     * Business ID getter function
     * @return String of business ID
     */
    public String getBusinessId() {
        return businessId;
    }

    /**
     * Review text contents getter function
     * @return String of review text submission
     */
    public String getText() {
        return text;
    }

    /**
     * User assigned restaurant 'star' score rating (1-5)
     * @return int star rating
     */
    public int getStars() {
        return stars;
    }

    /**
     * Review quality (cool, helpful, funny) vote score getter function
     * @return copy of int[] votes data
     */
    public int[] getVotes() {
        return Arrays.copyOf(votes, votes.length);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Review){
            // Both Restaurant IDs must match
            return (this.getReviewId() == ((Review) obj).getReviewId());
        }
        return false;
    }
}
