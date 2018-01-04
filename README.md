
Databases, Statistical Learning, Servers and Queries 
===

This code is from a project completed in December 2017 done with a partner. It deals with multiple aspects of software construction:
+ managing complex Abstract Data Types (ADTs);
+ multithreading and the client-server pattern;
+ query parsing and execution.

It also touches upon rudimentary methods for statistical inference and learning, namely k-means clustering and least squares regression.

## First Part - YelpDB

We worked with data from the [Yelp Academic Dataset](https://www.yelp.com/academic_dataset) in JSON format that contained information on some restaurants, reviews of the restaurants, and users who wrote those reviews to create an in-memory database (a "YelpDB"), and do some data analysis.

### Details

First, we parsed the JSON data to create an in-memory database (a `YelpDB`) with all restaurants, users and reviews. Then, we wrote methods to statistically analyze the data using k-means clustering and least squares regression. K-means clustering was used to group the restaurants into clusters within a city based on their longitude and latitude. This ideally allows us to group restaurants that are in the same neighbourhood in a city without knowledge of the neighbourhoods in a city. This [visualization](http://tech.nitoyon.com/en/blog/2013/11/07/k-means/) is a good way to understand how the k-means algorithm works. 

Least squares regression was used to predict what rating out of five a user might give to a new restaurant based on their reviews of other restaurants and the price rating of those other restaurants. This [interactive visual explanation](http://setosa.io/ev/ordinary-least-squares-regression/) helps to explain exactly what least squares regression is. In this explanation the price score for a restaurant would be the x-axis and the rating would be the y-axis. 

## Second Part - YelpDBServer

The next part of the assignment was to implement a multi-threaded server (a server that can handle multiple connections at once) application (a "YelpDBServer"), that wraps a YelpDB instance. This server allows its connected clients to get the JSON format information of any restaurant, review, or user in the YelpDB, as well as add a new restaurant, review, or user to the YelpDB.

In addition to the YelpDBServer, a YelpDBClient class was written to allow us to test the functionality of the YelpDBServer. This class was used only to send requests to the server and receive their respective replies from the server.

### Details

The interesting aspect of this part is that when multiple clients are making requests to change the database potential data races and other concurrency-related conflicts are needed to be dealt with so as to assure correct server behavior.

## Third Part - Structured Queries

The final part of this project was to support structured queries over the database. The request-response pattern is again handled by the YelpDBServer. 

A query sent to the server string may be: 

`QUERY in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price <= 2`. 

This query string represents a query to obtain a list of Chinese and Italian restaurants in the Telegraph Avenue neighbourhood that have a price range of 1-2. For the query string above, The server would respond to this query with a list of restaurants in JSON notation. 

### Details

ANTLR was used to develop the grammar, lexer, parser, and listeners used by the server to interpret the structured queries. 

## Testing

We thoroughly tested our project using JUnit test cases and Jacoco test coverage reports to ensure our programs ran as desired.

## My Contributions

While I understand how each part of the project works, my partner and I divided the work for the project up so we would be able to complete it more efficiently. My contributions to the project are summarized below.

* Wrote the YelpDB class and each of its methods excluding the least squares regression methods
* Wrote the YelpDBServer class, its methods, and all methods it used in YelpDB 
* Wrote the YelpDBClient class and its methods
* Integrated the functionality of the StructuredQuery class into the YelpDBServer class: allowed for clients to send a query to the server and receive an appropriate reply
* Wrote thorough test cases for all methods I wrote to ensure correct program behavior 
* Wrote deterministic specifications for all methods I coded and added explanatory comments in complex sections of code

