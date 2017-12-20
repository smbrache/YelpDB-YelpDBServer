package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private boolean isOpen;

    /* Geo-location value */
    private final double longitude;
    private final double latitude;
    // RI: Immutable after initialization

    /* Business ID value */
    private final String name;
    private final String url;
    private final String businessId;
    private final String photoURL;
    private final String type;
    // RI: Immutable after initialization

    /* Business location value */
    private final String state;
    private final String city;
    private final String fullAddress;
    private final List<String> schools;
    private final List<String> neighborhoods;
    // RI: Immutable after initialization

    /* Type filter value */
    private final List<String> categories;
    // RI: Immutable after initialization

    /* Ranking filter value (user-review driven statistic) */
    private int reviewCount;
    private double starScore;
    private final int priceScore;

    /* Review collection */
    private final List<Review> restaurantReviews;
    // RI: userReviews is not null
    // userReviews.size() == reviewCount

    /**
     * Initializes a Restaurant object
     *
     * @param isOpen boolean indication of restaurant open/closed status
     * @param url String representation of the url
     * @param longitude double precision longitude geographic description
     * @param neighborhoods List<String> collection of related neighborhoods
     * @param businessId String business ID code
     * @param name String case-sensitive business name (String)
     * @param categories List<String> collection food category relations
     * @param state String of state in two-character uppercase abbreviation
     * @param type String representation of organization type (e.g. business, charity)
     * @param starScore double precision star/score ranking (based on a 1-5 point scale)
     * @param city String representation of the city
     * @param fullAddress String representation of the full address
     * @param reviewCount int number of reviews provided during database initialization
     * @param photoURL String representation of the photo url
     * @param schools String representation of related school
     * @param latitude double precision latitude geographic description
     * @param priceScore int price ranking (based on a 1-5 point scale)
     */
	public Restaurant(boolean isOpen, String url, double longitude, List<String> neighborhoods, String businessId,
			String name, List<String> categories, String state, String type, double starScore, String city,
			String fullAddress, int reviewCount, String photoURL, List<String> schools, double latitude, int priceScore) {
    	
        // Initialize immutable and preset data
        this.type = type;
        this.isOpen = isOpen;
        this.url = url;

        this.longitude = longitude;
        this.latitude = latitude;
        this.state = state;
        this.city = city;
        this.fullAddress = fullAddress;
        this.neighborhoods = neighborhoods;
        this.schools = schools;

        this.businessId = businessId;
        this.name = name;
        this.categories = categories;

        // this.starScore = starScore;
        this.starScore = 0;
        // this.reviewCount = reviewCount;
        this.reviewCount = 0;
        this.photoURL = photoURL;
        this.priceScore = priceScore;

        // Initialize restaurantReviews
        restaurantReviews = new ArrayList<>();
    }

    /**
     * containsNeighborhood checks if the Restaurant object is a part of a
     * specified neighborhood
     *
     * @param inputNeighborhood case-sensitive/space-sensitive String representation of the neighborhood
     * @return true if restaurant is a part of the neighborhood, false otherwise
     */
    public boolean containsNeighborhood(String inputNeighborhood) {
        return (neighborhoods.contains(inputNeighborhood));
    }

    /**
     * containsCategory checks if the Restaurant specializes in a category of
     * food
     *
     * @param inputCategory case-sensitive/space-sensitive String representaiton of the food category
     * @return true if the restaurant serves the input food category, false otherwise
     */
    public boolean containsCategory(String inputCategory) {
        return (categories.contains(inputCategory));
    }

    /**
     * inputReview adds a Review object to the restaurantReviews collection
     * and increments reviewCount by one
     *
     * @param inputReview Review object to add to the restaurantReviews collection
     */
    public void addReview(Review inputReview) {
        // inputReview must not be null
        restaurantReviews.add(inputReview);

        // Adjust database values
        double tempReviewTotal = starScore*reviewCount;
        tempReviewTotal += inputReview.getStars();
        reviewCount++;

        starScore = tempReviewTotal/reviewCount;
    }

    /**
     * isOpen status getter function
     * @return true if Restaurant is open - otherwise, false
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Longitude geo-location value
     * @return double precision longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Latitude geo-location value
     * @return double precision latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Restaurant name getter function
     * @return case-sensitive String of the Restaurant name
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
     * Business ID getter function
     * @return String of business ID
     */
    public String getBusinessId() {
        return businessId;
    }

    /**
     * Photo url getter function
     * @return url-grammar-specific String of photo url
     */
    public String getPhotoURL() {
        return photoURL;
    }

    /**
     * Organization classification getter function
     * @return lowercase String of organization type
     */
    public String getType() {
        return type;
    }

    /**
     * USA-state getter function
     * @return two character abbreviated representation of USA-state
     */
    public String getState() {
        return state;
    }

    /**
     * City getter function
     * @return case-sensitive String of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Full address getter function
     * @return full-address-grammar compliant String of the full address
     */
    public String getFullAddress() {
        return fullAddress;
    }

    /**
     * Associated school getter function
     * @return case-sensitive String of school
     */
    public List<String> getSchools() {
        return new ArrayList<>(schools);
    }

    /**
     * all associated neighborhood collection getter function
     * @return ArrayList copy of associated neighborhoods
     */
    public List<String> getNeighborhoods() {
        return new ArrayList<>(neighborhoods);
    }

    /**
     * all associated restaurant category collection getter function
     * @return ArrayList copy of associated food categories
     */
    public List<String> getCategories() {
        return new ArrayList<>(categories);
    }

    /**
     * number of associated restaurant reviews
     * @return int number of reviews
     */
    public int getReviewCount() {
        return reviewCount;
    }

    /**
     * "star" score of the restaurant
     * @return double precision review score (1-5)
     */
    public double getStarScore() {
        return starScore;
    }

    /**
     * price score of the restaurant
     * @return int precision price score (1-5)
     */
    public int getPriceScore() {
        return priceScore;
    }

    /**
     * all associated restaurant reviews getter function
     * @return ArrayList copy of restaurant-specific reviews
     */
    public List<Review> getRestaurantReviews() {
        return new ArrayList<>(restaurantReviews);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Restaurant){
            // Both Restaurant IDs must match
            return (this.getBusinessId() == ((Restaurant) obj).getBusinessId());
        }
        return false;
    }
    
    @Override
    public String toString() {
    	return name;
    }
}
