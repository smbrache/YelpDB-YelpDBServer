# YelpDB Datatype Design

## Overview

We have three seperate classes created using YelpDB: Restaurant, Review, and User. They are used to store the JSON data from their respective .json files.
Other datatypes that we used in YelpDB are Maps and Lists. We use the Maps to categorize data on the restaurants and reviews. We use Lists to store all 
the User and Restaurant objects created. 

## Methods

* **parseRestaurants, parseUsers, parseReviews:** These methods scan and assemble each line of data in the .json files into relevant objects.
* **addRestaurant, addReview, addUser:** Store objects created in the parse methods in corresponding Lists/Maps.
* **filterRestbyCat, filterRestbyLocation, filterRestbyPrice, filterRestbyRating:** Map Restaurants to categories, locations, prices, and ratings.

## Representation

Two variable size Lists to store all Users and Restaurants, Maps to organize Restaurants by category, location, price, rating.

## Rep Invariants

The restaurantAll and userAll Lists are never null. They contain 0 to n entries, and contain no duplicate elements.
Any Restaurant object can appear in more than one category, or location, but only one rating and price.

## Abstraction Function

restaurantAll.size() >= restaurantByCategory.get(0 to (n-1)).size(), restaurantByRating.get(0 to (n-1)).size(), restaurantByLocation.get(0 to (n-1)).size(), restaurantByPrice.get(0 to (n-1)).size()

## Extensibility and Reuse

Our design and algorithms can be used for any review platform (with mild modifications to the JSON parser).
Currently we have implemented a Map representation for our datatype.